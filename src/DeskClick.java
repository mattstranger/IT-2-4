import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static java.lang.Math.abs;

public class DeskClick implements MouseMotionListener, MouseListener {
    JPanel c[][];
    int l, x, y, s, o;
    char d[][], p = '1';


    public DeskClick(JPanel cell[][], char desk[][],  int level, int opponent, int step)
    {
        c = cell;
        d = desk;
        l = level;
        s = step;
        o = opponent;
    }

    public void mousePressed(MouseEvent e) {
        //if (o == 3 || (o == 2 && p =='1')) {
            if      ((e.getX() - 15) % 60 > 0
                    && (e.getX() - 15) % 60 < 60
                    && (e.getY() - 15) % 60 > abs(e.getX() - 45) % 60
                    && (e.getY() - 15) % 60 < 60 - abs(e.getX() - 45) % 60
                    && (e.getX() - 15) / 60 < l && (e.getY() - 15) / 60 < l) {
                x = (e.getY() - 15) / 60;
                y = (e.getX() - 15) / 60;
            } else if ((e.getX() - 45) % 60 > 0
                    && (e.getX() - 45) % 60 < 60
                    && (e.getY() - 45) % 60 > abs(e.getX() - 75) % 60
                    && (e.getY() - 45) % 60 < 60 - abs(e.getX() - 75) % 60
                    && (e.getX() - 45) / 60 < l - 1
                    && (e.getY() - 45) / 60 < l - 1) {
                x = (e.getY() - 45) / 60;
                y = (e.getX() - 45) / 60 + l;
            }

       // }
        //System.out.println(x);
        //System.out.println(y);



        if (d[x][y] == '0') {
            d[x][y] = p;
            if (p == '1') {
                if (y < l)
                    for (int i = x * 6 + 2; i < x * 6 + 7; i++)
                        c[i][y * 6 + 4].setBackground(Color.blue);
                else
                    for (int j = (y - l) * 6 + 5; j < (y - l) * 6 + 10; j++)
                        c[x * 6 + 7][j].setBackground(Color.blue);
            }
            else {
                if (y < l)
                    for (int j = y * 6 + 2; j < y * 6 + 7; j++)
                        c[x * 6 + 4][j].setBackground(Color.red);
                else
                    for (int i = x * 6 + 5; i < x * 6 + 10; i++)
                        c[i][(y - l) * 6 + 7].setBackground(Color.red);
            }

            s++;
            if (s % 2 == 0) {
                PlayScreen.cplayer.setText("<html><font size=5 color=blue>1</font></html>");
                p = '1';
            } else {
                PlayScreen.cplayer.setText("<html><font size=5 color=red>2</font></html>");
                if (o == 3) p = '3';
                else p = '2';
            }
            PlayScreen.step.setText("<html><font size=5>" + (s / 2 + 1) + "</font></html>");

        }



        for (int k=0; k<l; k++)
            System.out.println(d[k]);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
