import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static java.lang.Math.abs;

public class DeskClick implements MouseMotionListener, MouseListener {
    JPanel c[][];
    int l, x, y, s;
    char d[][], p;


    public DeskClick(JPanel cell[][], char desk[][],  int level, char player, int step)
    {
        c = cell;
        d = desk;
        l = level;
        p = player;
        s = step;
    }

    public void mousePressed(MouseEvent e) {
        if (p != '2') {
            if ((e.getX() - 15) % 60 > 0 && (e.getX() - 15) % 60 < 60 && (e.getY() - 15) % 60 > abs(e.getX() - 45) % 60
                    && (e.getY() - 15) % 60 < 60 - abs(e.getX() - 45) % 60 && (e.getX() - 15) / 60 < l && (e.getY() - 15) / 60 < l) {
                x = (e.getY() - 15) / 60;
                y = (e.getX() - 15) / 60;
            } else if ((e.getX() - 45) % 60 > 0 && (e.getX() - 45) % 60 < 60 && (e.getY() - 45) % 60 > abs(e.getX() - 75) % 60
                    && (e.getY() - 45) % 60 < 60 - abs(e.getX() - 75) % 60 && (e.getX() - 45) / 60 < l - 1 && (e.getY() - 45) / 60 < l - 1) {
                x = (e.getY() - 45) / 60;
                y = (e.getX() - 45) / 60 + l;
            }
        }

        System.out.println(x);
        System.out.println(y);

        if (d[x][y] == '0'){
            d[x][y] = p;
            switch (p) {
                case '1': {
                    if (y < l)
                        for (int i = x * 6 + 2; i < x * 6 + 7; i++)
                            c[i][y * 6 + 4].setBackground(Color.blue);
                    else
                        for (int j = (y-l) * 6 + 5; j < (y-l) * 6 + 10; j++)
                            c[x * 6 + 7][j].setBackground(Color.blue);
                    break;
                }
                case '3': {
                    if (y < l)
                        for (int j = y * 6 + 2; j < y * 6 + 7; j++)
                            c[x * 6 + 4][j].setBackground(Color.red);
                    else
                        for (int i = x * 6 + 5; i < x * 6 + 10; i++)
                            c[i][(y-l) * 6 + 7].setBackground(Color.red);
                    break;
                }
                default: {
                    break;
                }
            }
            s++;
            if (s%2 == 0)
                p = '1';
            else
                p = '3';
            PlayScreen.stepn = s;
            PlayScreen.step.setText("<html><font size=5>унд: " + (s/2+1) + "</font></html>");
            //System.out.println(PlayScreen.stepn);
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
