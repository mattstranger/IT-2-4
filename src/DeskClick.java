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

        //проверка на свободность клетки, по которой кликнул игрок и отображение хода
        if (x1>=0  && y1>=0) {
            if (d[x1][y1] == '0') {
                d[x1][y1] = p;
                if (p == '1') {
                    if (y1 < l)
                        for (int i = x1*6+2; i < x1*6+7; i++)
                            c[i][y1*6+4].setBackground(Color.blue);
                    else
                        for (int j = (y1-l)*6+5; j < (y1-l)*6+10; j++)
                            c[x1*6+7][j].setBackground(Color.blue);

                    //вызов функции хода ИИ при выборе режима "компьютер"
                    if (o == 2)
                        AIMove();

                } else {
                    if (y1 < l)
                        for (int j = y1*6+2; j < y1*6+7; j++)
                            c[x1*6+4][j].setBackground(Color.red);
                    else
                        for (int i = x1*6+5; i < x1*6+10; i++)
                            c[i][(y1-l)*6+7].setBackground(Color.red);
                }

                //отображение чей сейчас ход и его номера через счетчик
                s++;
                if (s%2 == 0) {
                    PlayScreen.wplayer.setText(BridgeIt.message[4]+BridgeIt.color[0]+BridgeIt.playa[0]+BridgeIt.message[3]);
                    p = '1';
                } else {
                    PlayScreen.wplayer.setText(BridgeIt.message[4]+BridgeIt.color[1]+BridgeIt.playa[1]+BridgeIt.message[3]);
                    p = '2';
                }
                PlayScreen.step.setText(BridgeIt.message[2] + (s/2+1) + BridgeIt.message[3]);

                //отображение хода ИИ на поле и обновление счетчика
                if (o == 2 && p == '2')
                {
                    s++;
                    if (y2 < l)
                        for (int j = y2*6+2; j < y2*6+7; j++)
                            c[x2*6+4][j].setBackground(Color.red);
                    else
                        for (int i = x2*6+5; i < x2*6+10; i++)
                            c[i][(y2-l)*6+7].setBackground(Color.red);
                    PlayScreen.wplayer.setText(BridgeIt.message[4]+BridgeIt.color[0]+BridgeIt.playa[0]+BridgeIt.message[3]);
                    p = '1';
                    PlayScreen.step.setText(BridgeIt.message[2] + (s/2+1) + BridgeIt.message[3]);
                }
            }
        }
        BridgeIt.victory = PlayScreen.victoryCheck(d, BridgeIt.start[1], 0);
    }
    //--------------------



    //Функция хода ИИ
    //--------------------
    char[][] AIMove ()
    {
        int i, j;
        boolean b = false;
        char[][] t;

        t = new char[l][];
        for (i=0; i<l-1; i++)
        {
            t[i] = new char [2*l-1];
            for (j=0; j<2*l-1; j++)
                t[i][j] = d[i][j];
        }
        t[i] = new char [l];
        for (j=0; j<l; j++)
            t[i][j] = d[i][j];

        // проверка на возможность победного хода игрока
        for (i=0; i<l-1; i++) {
            for (j = 0; j < 2*l-1; j++)
                if (t[i][j] == '0') {
                    t[i][j] = '1';
                    if (PlayScreen.victoryCheck(t, l, 1) == '1') {
                        return moveMarker(i, j);
                    } else t[i][j] = '0';
                }
        }
        for (j = 0; j < l; j++)
            if (t[i][j] == '0') {
                t[i][j] = '1';
                if (PlayScreen.victoryCheck(t, l, 1) == '1') {
                    return moveMarker(i, j);
                } else t[i][j] = '0';
            }

        // проверка на возможность победного хода ИИ
        for (i=0; i<l-1; i++) {
            for (j = 0; j < 2*l-1; j++)
                if (t[i][j] == '0') {
                    t[i][j] = '2';
                    if (PlayScreen.victoryCheck(t, l, 2) == '2') {
                        return moveMarker(i, j);
                    } else t[i][j] = '0';
                }
        }
        for (j = 0; j < l; j++)
            if (t[i][j] == '0') {
                t[i][j] = '2';
                if (PlayScreen.victoryCheck(t, l, 2) == '2') {
                    return moveMarker(i, j);
                } else t[i][j] = '0';
            }

        t = null;

        // условия, проверяемые, если ход игрока был в области основных клеток
        if (y1<l) {
            for (i = 0; i < l; i++)
                if (d[i][y1] == '2') {
                    b = true;
                    break;
                }
            if (!b) {
                if (x1 != l-1 && d[x1 + 1][y1] == '0') {
                    return moveMarker(x1+1, y1);
                } else if (d[x1 - 1][y1] == '0'){
                    return moveMarker(x1-1, y1);
                }
            }

            // проверка на возможность соединения ломаной отрезков ИИ вокруг точки последнего хода игрока
            if (x1>0 && y1>0 && d[x1][y1-1]=='2' && d[x1-1][y1]=='2' && d[x1-1][y1+l-1]=='0') {
                return moveMarker(x1-1, y1+l-1);
            }
            if (x1<l-1 && y1>0 && d[x1][y1-1]=='2' && d[x1+1][y1]=='2' && d[x1][y1+l-1]=='0') {
                return moveMarker(x1, y1+l-1);
            }
            if (x1>0 && y1<l-1 && d[x1][y1+1]=='2' && d[x1-1][y1]=='2' && d[x1-1][y1+l]=='0') {
                return moveMarker(x1-1, y1+l);
            }
            if (x1<l-1 && y1<l-1 && d[x1][y1+1]=='2' && d[x1+1][y1]=='2' && d[x1][y1+l]=='0') {
                return moveMarker(x1, y1+l);
            }

            // проверка на возможность перекрытия возможного соединения игрока вокруг точки его последнего хода
            if (x1>0 && y1>0 && d[x1-1][y1-1]=='1' && d[x1-1][y1+l-1]=='0') {
                return moveMarker(x1-1, y1+l-1);
            }
            if (x1<l-1 && y1>0 && d[x1+1][y1-1]=='1' && d[x1][y1+l-1]=='0') {
                return moveMarker(x1, y1+l-1);
            }
            if (x1>0 && y1<l-1 && d[x1-1][y1+1]=='1'  && d[x1-1][y1+l]=='0') {
                return moveMarker(x1-1, y1+l);
            }
            if (x1<l-1 && y1<l-1 && d[x1+1][y1+1]=='1' && d[x1][y1+l]=='0') {
                return moveMarker(x1, y1+l);
            }

        // условия, проверяемые, если ход игрока был в области дополнительных клеток
        } else {

            // проверка на возможность соединения ломаной отрезков ИИ вокруг точки последнего хода игрока
            if(d[x1][y1-l] == '2' && d[x1][y1-l+1] == '0') {
                return moveMarker(x1, y1-l+1);
            }
            if(d[x1][y1-l] == '0' && d[x1][y1-l+1] == '2') {
                return moveMarker(x1, y1-l);
            }
            if(d[x1+1][y1-l] == '2' && d[x1+1][y1-l+1] == '0') {
                return moveMarker(x1+1, y1-l+1);
            }
            if(d[x1+1][y1-l] == '0' && d[x1+1][y1-l+1] == '2') {
                return moveMarker(x1+1, y1-l);
            }

            // проверка на возможность перекрытия возможного соединения игрока вокруг точки его последнего хода
            if(d[x1][y1-l] == '0') {
                return moveMarker(x1, y1-l);
            }
            if(d[x1][y1-l+1] == '0') {
                return moveMarker(x1, y1-l+1);
            }
            if(d[x1+1][y1-l] == '0') {
                return moveMarker(x1+1, y1-l);
            }
            if(d[x1+1][y1-l+1] == '0') {
                return moveMarker(x1+1, y1-l+1);
            }
        }

        // проверка на наличие разорванной на один отрезок прямой
        for (i=0; i<l; i++)
            for (j=0; j<l-2; j++)
                if (d[i][j] == '2'  &&  d[i][j+2] == '2'  &&  d[i][j+1] == '0' ) {
                    return moveMarker(i, j+1);
                }

        // проверка на наличие разорванной на один отрезок ломаной
        for (i=0; i<l; i++)
            for (j=0; j<l-1; j++)
            {
                if (i < l-1)
                    if  (d[i][j] == '2'  &&  d[i+1][j+1] == '2'  &&  d[i][j+l] == '0' ) {
                        return moveMarker(i, j+l);
                    }
                if (i > 0)
                    if (d[i][j] == '2'  &&  d[i-1][j+1] == '2'  &&  d[i-1][j+l] == '0' ) {
                        return moveMarker(i-1, j+l);
                    }
            }

        // проверка на возможность продолжения прямой влево и вправо
        if (y2 != 0  &&  y2 != l-1 && y2<l)
        {
            if (l-y2 > y2+1  &&  d[x2][y2-1] == '0') {
                return moveMarker(x2, y2-1);
            }
            if (l-y2 < y2+1  &&  d[x2][y2+1] == '0') {
                return moveMarker(x2, y2+1);
            }
            else if (d[x2][y2+1] == '0') {
                return moveMarker(x2, y2+1);
            }
                else if (d[x2][y2-1] == '0') {
                    return moveMarker(x2, y2-1);
                }
        } else if (y2 == 0) {
            for (j=1; j<l; j++)
                if (d[x2][j] == '0') {
                    return moveMarker(x2, j);
                }
            }
            else {
                for (j=l-1; j>=0; j--)
                    if (d[x2][j] == '0') {
                        return moveMarker(x2, j);
                    }
        }

        // проверка на наличие неперекрытых с обеих сторон прямых игрока
        for (i=0; i<l; i++)
            for (j = 0; j<l; j++)
                if (d[i][j] == '1')
                    if (i>0 && d[i-1][j] == '0') {
                        return moveMarker(i-1, j);
                    } else if (i<l-1 && d[i+1][j] == '0') {
                        return moveMarker(i+1, j);
                    }

        return d;

    }
    //--------------------



    //Функция отметки хода ИИ
    //--------------------
    char[][] moveMarker (int x, int y){
        d[x][y] = '2';
        x2 = x;
        y2 = y;
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
