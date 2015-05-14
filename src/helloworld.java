import javax.swing.*;

import static java.lang.Thread.sleep;

public class helloworld {
    static int start[]= new int[2];


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
            while (p.VictoryCheck() == '0');
            if (p.VictoryCheck() == '1')
                JOptionPane.showMessageDialog(null, "Победил синий игрок");
            else JOptionPane.showMessageDialog(null, "Победил красный игрок");
            p.dispose();

            Thread.sleep(100);

        }

    }



}
