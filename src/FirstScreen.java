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


        op[0] = new JButton("<html><font size=5>компьютер</font></html>");
        op[0].setSize(192, 64);
        op[0].setLocation(154, 192);
        op[0].setBackground(Color.LIGHT_GRAY);
        add(op[0]);
        op[0].addActionListener(new OpChoice(op, cond, 0, 0));

        op[1] = new JButton("<html><font size=5>человек</font></html>");
        op[1].setSize(192, 64);
        op[1].setLocation(154, 272);
        op[1].setBackground(Color.LIGHT_GRAY);
        add(op[1]);
        op[1].addActionListener(new OpChoice(op, cond, 1, 0));


        s[0] = new JButton("<html><font size=5>2</font></html>");
        s[0].setSize(64, 64);
        s[0].setLocation(426, 192);
        s[0].setBackground(Color.LIGHT_GRAY);
        add(s[0]);
        s[0].addActionListener(new OpChoice(s, cond, 0, 1));

        s[1] = new JButton("<html><font size=5>3</font></html>");
        s[1].setSize(64, 64);
        s[1].setLocation(506, 192);
        s[1].setBackground(Color.LIGHT_GRAY);
        add(s[1]);
        s[1].addActionListener(new OpChoice(s, cond, 1, 1));


        s[2] = new JButton("<html><font size=5>4</font></html>");
        s[2].setSize(64, 64);
        s[2].setLocation(586, 192);
        s[2].setBackground(Color.LIGHT_GRAY);
        add(s[2]);
        s[2].addActionListener(new OpChoice(s, cond, 2, 1));

        s[3] = new JButton("<html><font size=5>5</font></html>");
        s[3].setSize(64, 64);
        s[3].setLocation(426, 272);
        s[3].setBackground(Color.LIGHT_GRAY);
        add(s[3]);
        s[3].addActionListener(new OpChoice(s, cond, 3, 1));

        s[4] = new JButton("<html><font size=5>6</font></html>");
        s[4].setSize(64, 64);
        s[4].setLocation(506, 272);
        s[4].setBackground(Color.LIGHT_GRAY);
        add(s[4]);
        s[4].addActionListener(new OpChoice(s, cond, 4, 1));

        s[5] = new JButton("<html><font size=5>7</font></html>");
        s[5].setSize(64, 64);
        s[5].setLocation(586, 272);
        s[5].setBackground(Color.LIGHT_GRAY);
        add(s[5]);
        s[5].addActionListener(new OpChoice(s, cond, 5, 1));


        ex = new JButton("<html><font size=6>В Ы Х О Д</font></html>");
        ex.setSize(752, 64);
        ex.setLocation(21, 368);
        add(ex);
        ex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите выйти?", "ВЫХОД", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
    }

    public int[] PushChoice() {
        return cond;
    }

}
