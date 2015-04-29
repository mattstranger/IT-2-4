import javax.swing.*;

import static java.lang.Thread.sleep;

public class helloworld {
    static int start[]= new int[2];


    public static void main(String[] args) throws InterruptedException {
        FirstScreen f = new FirstScreen("Bridge-It");
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setSize(800, 480);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        while (start[0] == 0 || start[1] == 0)
            start = f.PushChoice();
        f.setVisible(false);

        Thread.sleep(100);

        PlayScreen p = new PlayScreen("Bridge-It", start[1]);
        p.setVisible(true);
        p.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        p.setSize(800, 480);
        p.setResizable(false);
        p.setLocationRelativeTo(null);

    }



}
