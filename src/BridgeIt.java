import javax.swing.*;

public class BridgeIt {
    static int start[]= new int[2];
    static boolean newg = false;
    static String[] playa = new String[2];

    static String[] title = {   "Bridge-It",
                                "Авторизация",
                                "НОВАЯ ИГРА",
                                "ВЫХОД",
                                "Игра окончена" };

    static String[] message = { "Вы уверены, что хотите начать новую игру?",
                                "Вы уверены, что хотите выйти?",
                                "<html><font size=5>ХОД: ",
                                "</font></html>",
                                "<html><font size=5>Сейчас ходит игрок <font color=blue>",
                                "<html><font size=5>Сейчас ходит игрок <font color=red>",
                                "<html>Победил <font color=blue>",
                                "<html>Победил <font color=red>",
                                "<html>Представьтесь, <font color=blue>синий</font> игрок:</html>",
                                "<html>Представьтесь, <font color=red>красный</font> игрок:</html>" };


    public static void main(String[] args) throws InterruptedException {
        while (true) {
            start[0] = 0;
            start[1] = 0;
            FirstScreen f = new FirstScreen(title[0]);
            f.setVisible(true);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setSize(800, 480);
            f.setResizable(false);
            f.setLocationRelativeTo(null);
            while (start[0] == 0 || start[1] == 0)
                start = f.pushChoice();
            playa[0] = JOptionPane.showInputDialog(null, message[8], title[1], JOptionPane.QUESTION_MESSAGE);
            if (start[0] == 3)
                playa[1] = JOptionPane.showInputDialog(null, message[9], title[1], JOptionPane.QUESTION_MESSAGE);
            else playa[1] = "Computer";
            f.dispose();

            Thread.sleep(100);

            PlayScreen p = new PlayScreen(title[0]+" // level "+start[1]+"; "+playa[0]+" vs "+playa[1], start[0], start[1]);
            p.setVisible(true);
            p.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            p.setSize(800, 480);
            p.setResizable(false);
            p.setLocationRelativeTo(null);
            while (p.victoryCheck(p.desk, start[1], 0) == '0' && !newg);
            if (!newg) {
                if (p.victoryCheck(p.desk, start[1], 0) == '1')
                    JOptionPane.showMessageDialog(null, message[6]+playa[0]+message[3], title[4],
                            JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, message[7]+playa[1]+message[3], title[4],
                            JOptionPane.INFORMATION_MESSAGE);
                if (JOptionPane.showConfirmDialog(null, "Еще партейку?", title[2],
                        JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                    System.exit(0);
            }
            p.dispose();
            newg = false;

            Thread.sleep(100);

        }

    }



}
