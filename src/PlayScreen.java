import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.abs;

public class PlayScreen extends JFrame {
    JButton ng, ex;
    static JLabel step, wplayer;
    JPanel field, gzone, cell[][];

    int i, j, range;
    char desk[][];


    public PlayScreen(String title, int opponent, int level) throws InterruptedException {
        super(title);
        setLayout(null);

        //System.out.println(o);
        //Thread.sleep(10000);

        range = level;
        desk = StartGame(level);

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


        step = new JLabel("<html><font size=5>ХОД: 1</font></html>");
        step.setSize(272, 64);
        step.setLocation(504, 16);
        add(step);


        wplayer = new JLabel("<html><font size=5>Сейчас ходит игрок <font size=6 color=blue>1</font></font></html>");
        wplayer.setSize(272, 64);
        wplayer.setLocation(504, 80);
        add(wplayer);


        ng = new JButton("<html><font size=5>НОВАЯ ИГРА</font></html>");
        ng.setSize(272, 64);
        ng.setLocation(504, 288);
        add(ng);
        ng.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите начать новую игру?", "НОВАЯ ИГРА", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    helloworld.newg = true;
                }
            }
        });

        ex = new JButton("<html><font size=6>В Ы Х О Д</font></html>");
        ex.setSize(272, 64);
        ex.setLocation(504, 368);
        add(ex);
        ex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите выйти?", "ВЫХОД", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

        gzone.addMouseListener(new DeskClick(cell, desk, level, opponent, 0));
    }



    public char[][] StartGame (int range)
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



    char VictoryCheck () {
        int j;
        boolean g1 = false, g2 = false;


        for (j=0; j<range; j++)
        {
            if (desk[0][j] == '1')
                g1 = Checker (desk, '1', 0, j, j, range);
            if (g1)
                return '1';
        }

        for (j=0; j<range; j++)
        {
            if (desk[j][0] == '2')
                g2 = Checker (desk, '2', j, 0, j, range);
            if (g2)
                return '2';
        }


       /* for (int k=0; k<range; k++)
            System.out.println(desk[k]);*/

        return '0';
    }


    boolean Checker (char[][] board, char gamer, int x, int y, int from, int range)
    {
        int j, k;
        boolean b = false;

        if (gamer == '1')
        {
            if (board[x][y] != '1')
                return false;
            else
            {

                if (x == range-1)
                    return true;
                else

                if (y < range)
                    if (y != 0)
                        if (y != range-1)
                            b = Checker (board, gamer, x+1, y, y, range) ||
                                    Checker (board, gamer, x, y+range-1, y, range) ||
                                    Checker (board, gamer, x, y+range, y, range);
                        else
                            b = Checker (board, gamer, x+1, y, y, range) ||
                                    Checker (board, gamer, x, y+range-1, y, range);
                    else
                        b = Checker (board, gamer, x+1, y, y, range) ||
                                Checker (board, gamer, x, y+range, y, range);

                else

                if (y != range)
                    if (y != 2*(range-1))
                        if (from < range)
                            b = Checker (board, gamer, x+1, y-range+1, y, range) ||
                                    Checker (board, gamer, x+1, y-range, y, range) ||
                                    Checker (board, gamer, x, y-1, y, range) ||
                                    Checker (board, gamer, x, y+1, y, range);
                        else
                        if (from == y+1)
                            b = Checker (board, gamer, x+1, y-range+1, y, range) ||
                                    Checker (board, gamer, x+1, y-range, y, range) ||
                                    Checker (board, gamer, x, y-1, y, range);
                        else
                            b = Checker (board, gamer, x+1, y-range+1, y, range) ||
                                    Checker (board, gamer, x+1, y-range, y, range) ||
                                    Checker (board, gamer, x, y+1, y, range);
                    else
                    {
                        if (from < range)
                            b = Checker (board, gamer, x+1, y-range+1, y, range) ||
                                    Checker (board, gamer, x+1, y-range, y, range) ||
                                    Checker (board, gamer, x, y-1, y, range);
                        else
                        if (from == y-1)
                            b = Checker (board, gamer, x+1, y-range+1, y, range) ||
                                    Checker (board, gamer, x+1, y-range, y, range);
                    }
                else
                if (from < range)
                    b = Checker (board, gamer, x+1, y-range+1, y, range) ||
                            Checker (board, gamer, x+1, y-range, y, range) ||
                            Checker (board, gamer, x, y+1, y, range);
                else
                if (from == y+1)
                    b = Checker (board, gamer, x+1, y-range+1, y, range) ||
                            Checker (board, gamer, x+1, y-range, y, range);
            }
        }

        else
        {
            if (board[x][y] == '0'  ||  board[x][y] == '1')
                return false;
            else
            {
                if (y == range-1)
                    return true;
                else
                if (y < range)
                    if (x != 0)
                        if (x != range-1)
                            b = Checker (board, gamer, x, y+1, y, range) ||
                                    Checker (board, gamer, x-1, y+range, x, range) ||
                                    Checker (board, gamer, x, y+range, x, range);
                        else
                            b = Checker (board, gamer, x, y+1, x, range) ||
                                    Checker (board, gamer, x-1, y+range, x, range);
                    else
                        b = Checker (board, gamer, x, y+1, x, range) ||
                                Checker (board, gamer, x, y+range, x, range);

                else
                if (x != 0)
                    if (x != range-2)
                    {
                        if (from == x-1)
                            b = Checker (board, gamer, x+1, y-range+1, x, range) ||
                                    Checker (board, gamer, x, y-range+1, x, range) ||
                                    Checker (board, gamer, x+1, y, x, range);
                        else
                        if (from == x+1)
                            b = Checker (board, gamer, x+1, y-range+1, x, range) ||
                                    Checker (board, gamer, x, y-range+1, x, range) ||
                                    Checker (board, gamer, x-1, y, x, range);
                    }
                    else
                    {
                        if (from == x-1)
                            b = Checker (board, gamer, x+1, y-range+1, x, range) ||
                                    Checker (board, gamer, x, y-range+1, x, range);
                        else
                            b = Checker (board, gamer, x+1, y-range+1, x, range) ||
                                    Checker (board, gamer, x, y-range+1, x, range) ||
                                    Checker (board, gamer, x-1, y, x, range);
                    }
                else
                if (from == x+1)
                    b = Checker (board, gamer, x+1, y-range+1, x, range) ||
                            Checker (board, gamer, x, y-range+1, x, range);
                else
                    b = Checker (board, gamer, x+1, y-range+1, x, range) ||
                            Checker (board, gamer, x, y-range+1, x, range) ||
                            Checker (board, gamer, x+1, y, x, range);
            }
        }
        return b;
    }

}

