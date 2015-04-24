import javax.swing.*;

public class helloworld {
    public static void main(String[] args) {
        FirstScreen f = new FirstScreen("Bridge-It");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 480);
        System.out.println("Hello world");
    }
}
