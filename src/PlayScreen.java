import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PlayScreen extends JFrame {
    JButton ng, ex;
    static JLabel step, wplayer;
    JPanel field, gzone, cell[][];

    int i, j, range;
    char desk[][];


    public PlayScreen(String title, int opponent, int level) throws InterruptedException {
        super(title);
        setLayout(null);

        range = level;
        desk = startGame(level);

        field = new JPanel();
        field.setSize(480, 450);
        field.setLocation(0, 0);
        field.setBackground(Color.white);
        add(field);
        field.setLayout(null);

        gzone = new JPanel();
        gzone.setSize(level * 60 + 30, level * 60 + 30);
        gzone.setLocation(225 - 30 * level, 210 - 30 * level);
        field.add(gzone);

        gzone.setLayout(new GridLayout(3 * (2 * level + 1), 3 * (2 * level + 1), 0, 0));
        cell = new JPanel[3 * (2 * level + 1)][3 * (2 * level + 1)];

        for (i = 0; i < 3 * (2 * level + 1); i++)
            for (j = 0; j < 3 * (2 * level + 1); j++)
                if ((i + 5) % 6 == 0) {
                    if ((j + 2) % 6 == 0) {
                        cell[i][j] = new JPanel();
                        cell[i][j].setBackground(Color.blue);
                        gzone.add(cell[i][j]);
                    } else {
                        cell[i][j] = new JPanel();
                        cell[i][j].setBackground(Color.white);
                        gzone.add(cell[i][j]);
                    }
                } else if ((i + 2) % 6 == 0) {
                    if ((j + 5) % 6 == 0) {
                        cell[i][j] = new JPanel();
                        cell[i][j].setBackground(Color.red);
                        gzone.add(cell[i][j]);
                    } else {
                        cell[i][j] = new JPanel();
                        cell[i][j].setBackground(Color.white);
                        gzone.add(cell[i][j]);
                    }
                } else {
                    cell[i][j] = new JPanel();
                    cell[i][j].setBackground(Color.white);
                    gzone.add(cell[i][j]);
                }


        step = new JLabel(BridgeIt.message[2]+'1'+BridgeIt.message[3]);
        step.setSize(272, 64);
        step.setLocation(504, 16);
        add(step);


        wplayer = new JLabel(BridgeIt.message[4]+BridgeIt.color[0]+BridgeIt.playa[0]+BridgeIt.message[3]);
        wplayer.setSize(272, 64);
        wplayer.setLocation(504, 80);
        add(wplayer);


        ng = new JButton("<html><font size=5>НОВАЯ ИГРА</font></html>");
        ng.setSize(272, 64);
        ng.setLocation(504, 288);
        add(ng);
        ng.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, BridgeIt.message[0], BridgeIt.title[2],
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    BridgeIt.newgame = true;
                }
            }
        });

        ex = new JButton("<html><font size=6>В Ы Х О Д</font></html>");
        ex.setSize(272, 64);
        ex.setLocation(504, 368);
        add(ex);
        ex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, BridgeIt.message[1], BridgeIt.title[3],
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

        gzone.addMouseListener(new DeskClick(cell, desk, level, opponent, 0));
    }



    //Функция первичной инициализации матрицы, хранящей состояния ячееек игрового поля
    //--------------------
    public char[][] startGame (int range)
    {
        char temp[][];
        int i, j;

        temp = new char[range][];
        for (i=0; i<range-1; i++)
        {
            temp[i] = new char [2*range-1];
            for (j=0; j<2*range-1; j++)
                temp[i][j] = '0';
        }
        temp[i] = new char [range];
        for (j=0; j<range; j++)
            temp[i][j] = '0';
        return temp;
    }
    //--------------------



    //Функция проверки на победу 1 и 2 игроков поочередно
    //--------------------
    public static char victoryCheck (char[][] board, int range, int mode) {
        int j;
        boolean g1 = false, g2 = false, checked[][];

        if (mode == 1 || mode == 0) {
            checked = createArrayChecks(range);
            for (j = 0; j < range; j++) {
                if (board[0][j] == '1')
                    g1 = checker(board, '1', 0, j, range, checked);
                if (g1) {
                    checked = null;
                    return '1';
                }
            }
        }

        if (mode == 2 || mode == 0) {
            checked = createArrayChecks(range);
            for (j = 0; j < range; j++) {
                if (board[j][0] == '2')
                    g2 = checker(board, '2', j, 0, range, checked);
                if (g2) {
                    checked = null;
                    return '2';
                }
            }
        }

        checked = null;
        return '0';
    }
    //--------------------



    //Функция проверки соединения сторон ломаной (для 1 игрока - верхней и нижней, для 2 - левой и правой)
    //--------------------
    public static boolean checker (char[][] board, char gamer, int x, int y, int range, boolean[][] checked)
    {
        boolean b = false;

        if (gamer == '1') {
            if (board[x][y] != '1')
                return false;
            else {
                checked[x][y]= true;
                if (x == range-1)
                    return true;
                else
                if (y < range){
                    if (x != range-1 && !checked[x+1][y])
                        b = b || checker(board, gamer, x+1, y, range, checked);
                    if (x != 0 && !checked[x-1][y])
                        b = b || checker(board, gamer, x-1, y, range, checked);
                    if (x != range-1 && y != 0 && !checked[x][y+range-1])
                        b = b || checker(board, gamer, x, y+range-1, range, checked);
                    if (x != range-1 && y != range-1 && !checked[x][y+range])
                        b = b || checker(board, gamer, x, y+range, range, checked);
                    if (x != 0 && y != 0 && !checked[x-1][y+range-1])
                        b = b || checker(board, gamer, x-1, y+range-1, range, checked);
                    if (x != 0 && y != range-1 && !checked[x-1][y+range])
                        b = b || checker(board, gamer, x-1, y+range, range, checked);
                }
                else {
                    if (!checked[x][y-range])
                        b = b || checker(board, gamer, x, y-range, range, checked);
                    if (!checked[x][y-range+1])
                        b = b || checker(board, gamer, x, y-range+1, range, checked);
                    if (!checked[x+1][y-range])
                        b = b || checker(board, gamer, x+1, y-range, range, checked);
                    if (!checked[x+1][y-range+1])
                        b = b || checker(board, gamer, x+1, y-range+1, range, checked);
                    if (y != range && !checked[x][y-1])
                        b = b || checker(board, gamer, x, y-1, range, checked);
                    if (y != 2*range-2 && !checked[x][y+1])
                        b = b || checker(board, gamer, x, y+1, range, checked);
                }
            }
        }

        else {
            if (board[x][y] != '2')
                return false;
            else {
                checked[x][y]= true;
                if (y == range-1)
                    return true;
                else
                if (y < range){
                    if (y != range-1 && !checked[x][y+1])
                        b = b || checker(board, gamer, x, y+1, range, checked);
                    if (y != 0 && !checked[x][y-1])
                        b = b || checker(board, gamer, x, y-1, range, checked);
                    if (x != range-1 && y != 0 && !checked[x][y+range-1])
                        b = b || checker(board, gamer, x, y+range-1, range, checked);
                    if (x != range-1 && y != range-1 && !checked[x][y+range])
                        b = b || checker(board, gamer, x, y+range, range, checked);
                    if (x != 0 && y != 0 && !checked[x-1][y+range-1])
                        b = b || checker(board, gamer, x-1, y+range-1, range, checked);
                    if (x != 0 && y != range-1 && !checked[x-1][y+range])
                        b = b || checker(board, gamer, x-1, y+range, range, checked);
                }
                else {
                    if (!checked[x][y-range])
                        b = b || checker(board, gamer, x, y-range, range, checked);
                    if (!checked[x][y-range+1])
                        b = b || checker(board, gamer, x, y-range+1, range, checked);
                    if (!checked[x+1][y-range])
                        b = b || checker(board, gamer, x+1, y-range, range, checked);
                    if (!checked[x+1][y-range+1])
                        b = b || checker(board, gamer, x+1, y-range+1, range, checked);
                    if (x != 0 && !checked[x-1][y])
                        b = b || checker(board, gamer, x-1, y, range, checked);
                    if (x != range-2 && !checked[x+1][y])
                        b = b || checker(board, gamer, x+1, y, range, checked);
                }
            }
        }

        return b;
    }
    //--------------------


    //Функция, создающая массив для отметки уже проверенных клеток игрового поля
    //--------------------
    static boolean[][] createArrayChecks (int range){
        int i,j;
        boolean[][] checked;

        checked = new boolean[range][];
        for (i=0; i<range-1; i++)
        {
            checked[i] = new boolean [2*range-1];
            for (j=0; j<2*range-1; j++)
                checked[i][j] = false;
        }
        checked[i] = new boolean[range];
        for (j=0; j<range; j++)
            checked[i][j] = false;

        return checked;
    }
    //--------------------
}

