import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstScreen extends JFrame {
    JButton op1, op2, s1, s2, s3, s4, s5, s6;
    JTextArea intro;

    public FirstScreen (String title){
        super(title);
        setLayout(new FlowLayout());
        intro = new JTextArea("������ ��������� ��������� ���� �����-��, ����� ������� �������� ��������� �������������� �������� ���� ������� ������� ������: ��� ������ ������ - ������� � ������, ��� �������� - ����� � ������.\n" +
                "\n" +
                "����������, �������� ��������� � ������ �������� ����:");
        op1 = new JButton("���������");
        op2 = new JButton("�������");
        s1 = new JButton("2");
        s2 = new JButton("3");
        s3 = new JButton("4");
        s4 = new JButton("5");
        s5 = new JButton("6");
        s6 = new JButton("7");
        add(intro);
        add(op1);
        add(op2);
        add(s1);
        add(s2);
        add(s3);
        add(s4);
        add(s5);
        add(s6);
    }

}
