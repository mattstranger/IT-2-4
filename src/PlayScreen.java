import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.abs;

public class PlayScreen extends JFrame {
    JButton ng, ex;
    static JLabel step, cplayer;
    JLabel wstep, wplayer;
    JPanel field, gzone, cell[][];

    int i;
    int j;
    char player = '1';
    char desk[][];


    public PlayScreen(String title, int opponent, int level) {
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


        wstep = new JLabel("<html><font size=5>ХОД: </font></html>");
        wstep.setSize(272, 64);
        wstep.setLocation(504, 16);
        add(wstep);

        step = new JLabel("<html><font size=5>1</font></html>");
        step.setSize(222, 64);
        step.setLocation(554, 16);
        add(step);


        wplayer = new JLabel("<html><font size=5>Сейчас ходит игрок</font></html>");
        wplayer.setSize(272, 64);
        wplayer.setLocation(504, 80);
        add(wplayer);

        cplayer = new JLabel("<html><font size=5 color=blue>1</font></html>");
        cplayer.setSize(86, 64);
        cplayer.setLocation(690, 80);
        add(cplayer);


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
}

