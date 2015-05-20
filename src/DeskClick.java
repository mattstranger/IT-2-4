import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static java.lang.Math.abs;

public class DeskClick implements MouseMotionListener, MouseListener {
    JPanel c[][];
    int l, x1=-1, y1=-1, x2 = -1, y2 = -1, s, o;
    char d[][], p = '1';


    public DeskClick(JPanel cell[][], char desk[][],  int level, int opponent, int step)
    {
        c = cell;
        d = desk;
        l = level;
        s = step;
        o = opponent;
    }



    //Функция обработчика нажатия мыши по игровому полю, осуществляющая ход игрока при благоприятных условиях
    //--------------------
    public void mousePressed(MouseEvent e) {
        if    ((e.getX() - 15) % 60 > 0                     //проверка координат по нажатию мыши
                && (e.getX() - 15) % 60 < 60
                && (e.getY() - 15) % 60 > abs(e.getX() - 45) % 60
                && (e.getY() - 15) % 60 < 60 - abs(e.getX() - 45) % 60
                && (e.getX() - 15) / 60 < l && (e.getY() - 15) / 60 < l) {
            x1 = (e.getY() - 15) / 60;
            y1 = (e.getX() - 15) / 60;
        } else if ((e.getX() - 45) % 60 > 0
                    && (e.getX() - 45) % 60 < 60
                    && (e.getY() - 45) % 60 > abs(e.getX() - 75) % 60
                    && (e.getY() - 45) % 60 < 60 - abs(e.getX() - 75) % 60
                    && (e.getX() - 45) / 60 < l - 1
                    && (e.getY() - 45) / 60 < l - 1) {
                x1 = (e.getY() - 45) / 60;
                y1 = (e.getX() - 45) / 60 + l;
            }

        if (x1 >= 0  && y1>=0) {
            if (d[x1][y1] == '0') {                            //проверка на свободность ячейки
                d[x1][y1] = p;
                if (p == '1') {
                    if (y1 < l)
                        for (int i = x1 * 6 + 2; i < x1 * 6 + 7; i++)
                            c[i][y1 * 6 + 4].setBackground(Color.blue);
                    else
                        for (int j = (y1 - l) * 6 + 5; j < (y1 - l) * 6 + 10; j++)
                            c[x1 * 6 + 7][j].setBackground(Color.blue);
                    if (o == 2)                                 //вызов функции хода ИИ при выборе режима "компьютер"
                        AIMove();
                } else {
                    if (y1 < l)
                        for (int j = y1 * 6 + 2; j < y1 * 6 + 7; j++)
                            c[x1 * 6 + 4][j].setBackground(Color.red);
                    else
                        for (int i = x1 * 6 + 5; i < x1 * 6 + 10; i++)
                            c[i][(y1 - l) * 6 + 7].setBackground(Color.red);
                }

                s++;                                            //отображение чей сейчас ход и его номера через счетчик
                if (s % 2 == 0) {
                    PlayScreen.wplayer.setText("<html><font size=5>Сейчас ходит игрок <font size=6 color=blue>1</font></font></html>");
                    p = '1';
                } else {
                    PlayScreen.wplayer.setText("<html><font size=5>Сейчас ходит игрок <font size=6 color=red>2</font></font></html>");
                    p = '2';
                }
                PlayScreen.step.setText("<html><font size=5>ХОД: " + (s / 2 + 1) + "</font></html>");
                if (o == 2 && p == '2')                       //отображение хода ИИ на поле и обновление счетчика
                {
                    s++;
                    if (y2 < l)
                        for (int j = y2 * 6 + 2; j < y2 * 6 + 7; j++)
                            c[x2 * 6 + 4][j].setBackground(Color.red);
                    else
                        for (int i = x2 * 6 + 5; i < x2 * 6 + 10; i++)
                            c[i][(y2 - l) * 6 + 7].setBackground(Color.red);
                    PlayScreen.wplayer.setText("<html><font size=5>Сейчас ходит игрок <font size=6 color=blue>1</font></font></html>");
                    p = '1';
                    PlayScreen.step.setText("<html><font size=5>ХОД: " + (s / 2 + 1) + "</font></html>");
                }
            }
        }
    }
    //--------------------



    //Функция хода ИИ
    //--------------------
    char[][] AIMove ()
    {
        int i, j;
        boolean b = false;

        if (x2 == -1  &&  y2 == -1)                 // условия для первого хода бота
        {
            if (x1 > l-1-x1)
                x2 = 0;
            else
                x2 = l-1;
            if (y1 < l)
                y2 = y1;
            else
                y2 = y1-l;
            d[x2][y2] = '2';
            return d;
        }


        if (y1 < l)                            // условия рассматриваемые лишь если игрок связывал
        {                                          // точки вертикально
            for (i=0; i<l; i++)
                if (d[i][y1] == '2')
                {
                    b = true;
                    break;
                }

            if (!b)
            {
                if (d[x2][y1] == '0')
                {
                    d[x2][y1] = '2';
                    y2 = y1;
                }
                else
                {
                    if (x2 != l-1 && d[x2+1][y1] == '0')
                    {
                        x2 = x2+1;
                        y2 = y1;
                    }
                    else if (x2 != 0 && d[x2-1][y1] == '0')
                    {
                        x2 = x2-1;
                        y2 = y1;
                    }
                    d[x2][y1] = '2';
                }

                return d;
            }
        }


        for (i=0; i<l; i++)                    // проверка на наличие разорванной на один отрезок прямой
            for (j=0; j<l-2; j++)
                if (d[i][j] == '2'  &&  d[i][j+2] == '2'  &&  d[i][j+1] == '0' )
                {
                    d[i][j+1] = '2';
                    x2 = i;
                    y2 = j+1;
                    return d;
                }

        for (i=0; i<l; i++)                   // проверка на наличие разорванной на один отрезок ломаной
            for (j=0; j<l-1; j++)
            {
                if (i < l-1)
                    if  (d[i][j] == '2'  &&  d[i+1][j+1] == '2'  &&  d[i][j+l] == '0' )
                    {
                        d[i][j+l] = '2';
                        x2 = i;
                        y2 = j+l;
                        return d;
                    }
                if (i > 0)
                    if (d[i][j] == '2'  &&  d[i-1][j+1] == '2'  &&  d[i-1][j+l] == '0' )
                    {
                        d[i-1][j+l] = '2';
                        x2 = i-1;
                        y2 = j+l;
                        return d;
                    }
            }

        if (y2 != 0  &&  y2 != l-1)            // проверка на возможность продолжения прямой влево и вправо
        {
            if (l-y2 > y2+1  &&  d[x2][y2-1] == '0')
            {
                d[x2][y2-1] = '2';
                y2 = y2-1;
                return d;
            }
            if (l-y2 < y2+1  &&  d[x2][y2+1] == '0')
            {
                d[x2][y2+1] = '2';
                y2 = y2+1;
                return d;
            }
            else
            if (d[x2][y2+1] == '0')
            {
                d[x2][y2+1] = '2';
                y2 = y2+1;
                return d;
            }
            else
            if (d[x2][y2-1] == '0')
            {
                d[x2][y2-1] = '2';
                y2 = y2-1;
                return d;
            }
        }
        else
        if (y2 == 0)
        {
            for (j=1; j<l; j++)
                if (d[x2][j] == '0')
                {
                    d[x2][j] = '2';
                    y2 = j;
                    return d;
                }
        }
        else
        {
            for (j=l-1; j>=0; j--)
                if (d[x2][j] == '0')
                {
                    d[x2][j] = '2';
                    y2 = j;
                    return d;
                }
        }

        for (i=0; i<l; i++)
            if (d[i][y1] == '0')
            {
                x2 = i;
                y2 = y1;
                d[x2][y2] = '2';
                return d;
            }

        return d;
    }
    //--------------------



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
