import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstScreen extends JFrame {
    JButton op1, op2, s1, s2, s3, s4, s5, s6, ex;
    JLabel intro;

    public FirstScreen (String title){
        super(title);
        setLayout(null);

        intro = new JLabel("<html><font size=5><p align=center size=18pt>Данная программа реализует игру Бридж-Ит, " +
                "целью которой является соединить противолежащие игрового поля стороны ломаной линией: для " +
                "<font color=blue>синего</font> игрока - верхнюю и нижнюю, для <font color=red>красного</font> - " +
                "левую и правую.<br><br>Пожалуйста, выберете оппонента и размер игрового поля:</p></font></html>");
        intro.setSize(752, 128);
        intro.setLocation(24, 16);
        add(intro);


        op1 = new JButton("<html><font size=5>компьютер</font></html>");
        op1.setSize(192, 64);
        op1.setLocation(154, 192);
        add(op1);

        op2 = new JButton("<html><font size=5>человек</font></html>");
        op2.setSize(192, 64);
        op2.setLocation(154, 272);
        add(op2);


        s1 = new JButton("<html><font size=5>2</font></html>");
        s1.setSize(64, 64);
        s1.setLocation(426, 192);
        add(s1);

        s2 = new JButton("<html><font size=5>3</font></html>");
        s2.setSize(64, 64);
        s2.setLocation(506, 192);
        add(s2);

        s3 = new JButton("<html><font size=5>4</font></html>");
        s3.setSize(64, 64);
        s3.setLocation(586, 192);
        add(s3);

        s4 = new JButton("<html><font size=5>5</font></html>");
        s4.setSize(64, 64);
        s4.setLocation(426, 272);
        add(s4);

        s5 = new JButton("<html><font size=5>6</font></html>");
        s5.setSize(64, 64);
        s5.setLocation(506, 272);
        add(s5);

        s6 = new JButton("<html><font size=5>7</font></html>");
        s6.setSize(64, 64);
        s6.setLocation(586, 272);
        add(s6);


        ex = new JButton("<html><font size=6>В Ы Х О Д</font></html>");
        ex.setSize(752, 64);
        ex.setLocation(21, 368);
        add(ex);
    }

}
