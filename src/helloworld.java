import javax.swing.*;

public class helloworld {
    public static void main(String[] args) {
        FirstScreen f = new FirstScreen("Bridge-It");
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(800, 480);
        f.setResizable(false);
        f.setLocationRelativeTo(null);

        PlayScreen p = new PlayScreen("Bridge-It", 7);
        p.setVisible(true);
        p.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        p.setSize(800, 480);
        p.setResizable(false);
        p.setLocationRelativeTo(null);

    }
}
