import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OpChoice implements ActionListener {
    JButton b[];
    int m[], i, t;


    public OpChoice(JButton button[], int condition[], int num, int type)
    {
        b = button;
        m = condition;
        i = num;
        t = type;
    }



    //Функция обработчика событий для индикации нажатых пользователем кнопок на экране стартового меню
    //--------------------
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (m[t] != i+2) {
            b[i].setBackground(Color.GRAY);
            if (m[t] != 0)
                b[m[t]-2].setBackground(Color.LIGHT_GRAY);
            m[t] = i+2;
        } else {
            b[i].setBackground(Color.LIGHT_GRAY);
            m[t] = 0;
        }
    }
    //--------------------

}



