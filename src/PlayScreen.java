import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.abs;
import static java.lang.System.exit;

public class PlayScreen extends JFrame {
    JButton ng, ex;
    JLabel step, wplayer;
    JPanel field, gzone, cell[][];
    int stepn = 0, i, j;
    boolean c = true;
    static char desk[][];


    public PlayScreen(String title, final int level) {
        super(title);
        setLayout(null);

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


        step = new JLabel("<html><font size=5>ХОД: " + stepn + "</font></html>");
        step.setSize(272, 64);
        step.setLocation(504, 16);
        add(step);

        if (c) wplayer = new JLabel("<html><font size=5>Сейчас ходит <font color=blue>синий</font> игрок</font></html>");
        else wplayer = new JLabel("<html><font size=5>Сейчас ходит <font color=red>красный</font> игрок</font></html>");
        wplayer.setSize(272, 64);
        wplayer.setLocation(504, 80);
        add(wplayer);


        ng = new JButton("<html><font size=5>НОВАЯ ИГРА</font></html>");
        ng.setSize(272, 64);
        ng.setLocation(504, 288);
        add(ng);
        ng.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите начать новую игру?") == JOptionPane.YES_OPTION)
                    setVisible(false);
            }
        });

        ex = new JButton("<html><font size=6>В Ы Х О Д</font></html>");
        ex.setSize(272, 64);
        ex.setLocation(504, 368);
        add(ex);
        ex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите выйти?") == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

        gzone.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getX()-15)%60>0 && (e.getX()-15)%60<60 && ((e.getY()-15)%60>abs(e.getX()-45)%60) && ((e.getY()-15)%60<60-abs(e.getX()-45)%60) && (e.getX()-15)/60<level && (e.getY()-15)/60<level)
                    for (int i=((e.getY()-15)/60)*6+2; i<((e.getY()-15)/60)*6+7; i++)
                        cell[i][((e.getX()-15)/60)*6+4].setBackground(Color.blue);
                else if ((e.getX()-45)%60>0 && (e.getX()-45)%60<60 && ((e.getY()-45)%60>abs(e.getX()-75)%60) && ((e.getY()-75)%60<60-abs(e.getX()-75)%60) && (e.getX()-45)/60<level-1 && (e.getY()-45)/60<level-1)
                    for (int j=((e.getX()-45)/60) *6+5; j<((e.getX()-45)/60)*6+10; j++)
                        cell[((e.getY()-45)/60)*6+7][j].setBackground(Color.blue);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public static char[][] StartGame (int range)
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
}


