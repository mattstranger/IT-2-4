import javax.swing.*;

import static java.lang.Thread.sleep;

public class helloworld {
    static int start[]= new int[2];
    static boolean newg = false;


    public static void main(String[] args) throws InterruptedException {
        while (true) {
            start[0] = 0;
            start[1] = 0;
            FirstScreen f = new FirstScreen("Bridge-It");
            f.setVisible(true);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setSize(800, 480);
            f.setResizable(false);
            f.setLocationRelativeTo(null);
            while (start[0] == 0 || start[1] == 0)
                start = f.PushChoice();
            f.dispose();

            Thread.sleep(100);

            PlayScreen p = new PlayScreen("Bridge-It", start[0], start[1]);
            p.setVisible(true);
            p.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            p.setSize(800, 480);
            p.setResizable(false);
            p.setLocationRelativeTo(null);
            while (p.VictoryCheck() == '0' && !newg);
            if (!newg) {
                if (p.VictoryCheck() == '1')
                    JOptionPane.showMessageDialog(null, "<html>Победил <font color=blue>синий</font> игрок</html>", "Игра окончена", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "<html>Победил <font color=red>красный</font> игрок</html>", "Игра окончена", JOptionPane.INFORMATION_MESSAGE);
                if (JOptionPane.showConfirmDialog(null, "Еще партейку?", "НОВАЯ ИГРА", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                    System.exit(0);
            }
            p.dispose();
            newg = false;

            Thread.sleep(100);

        }

    }



}
