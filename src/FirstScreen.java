import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstScreen extends JFrame {
    JButton ex;
    JLabel intro;
    int cond[] = new int[2];
    JButton op[] = new JButton[2];
    JButton s[] = new JButton[6];


    public FirstScreen (String title) {
        super(title);
        setLayout(null);


        intro = new JLabel("<html><font size=5><p align=center>Данная программа реализует игру Бридж-Ит, " +
                "целью которой является соединить противолежащие игрового поля стороны ломаной линией: для " +
                "<font color=blue>синего</font> игрока - верхнюю и нижнюю, для <font color=red>красного</font> - " +
                "левую и правую.<br><br>Пожалуйста, выберете оппонента и размер игрового поля:</p></font></html>");
        intro.setSize(752, 128);
        intro.setLocation(24, 16);
        add(intro);

        addModeButton(op, 0, 0);
        addModeButton(op, 1, 0);

        addModeButton(s, 0, 1);
        addModeButton(s, 1, 1);
        addModeButton(s, 2, 1);
        addModeButton(s, 3, 1);
        addModeButton(s, 4, 1);
        addModeButton(s, 5, 1);

        ex = new JButton("<html><font size=6>В Ы Х О Д</font></html>");
        ex.setSize(752, 64);
        ex.setLocation(21, 368);
        add(ex);
        ex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, BridgeIt.message[1], BridgeIt.title[3],
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
    }



    //Функция возврата выбраных пользователем режима игры и размера поля
    //--------------------
    public int[] pushChoice() {
        return cond;
    }
    //--------------------



    //Функция добавления на экран кнопок выбора режима игры
    //--------------------
    void addModeButton (JButton[] btn, int num, int grp){
        if (grp == 0) {
            if (num == 0)
                btn[num] = new JButton("<html><font size=5>компьютер</font></html>");

            else
                btn[num] = new JButton("<html><font size=5>человек</font></html>");
            btn[num].setSize(192, 64);
            btn[num].setLocation(154, 192+80*num);
        }
        else {
            btn[num] = new JButton("<html><font size=5>"+(num+2)+"</font></html>");
            btn[num].setSize(64, 64);
            btn[num].setLocation(426+80*(num%3), 192+80*(num/3));
        }

        btn[num].setBackground(Color.LIGHT_GRAY);
        add(btn[num]);
        btn[num].addActionListener(new OpChoice(btn, cond, num, grp));
    }
    //--------------------

}
