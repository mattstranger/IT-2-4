import javax.swing.*;

public class BridgeIt {
    static int start[]= new int[2];
    static boolean newgame = false, auth;
    static char victory = '0';
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
                                "<html><font size=5>Сейчас ходит игрок <font color=",
                                "<html>Победил игрок <font color=",
                                "<html>Представьтесь, <font color=",
                                "</font> игрок:</html>",
                                "<html>Хотите сменить никнейм, <font color=",
                                "</font>?</html>" };

    static String[] color = {   "blue>",
                                "red>",
                                "синий",
                                "красный"
    };


    public static void main(String[] args) throws InterruptedException {
        while (true) {
            start[0] = 0;
            start[1] = 0;

            FirstScreen startmenu = new FirstScreen(title[0]);
            makeNewWindow(startmenu, 800, 480);
            while (start[0] == 0 || start[1] == 0){
                Thread.sleep(100);
                start = startmenu.pushChoice();}
            auth = false;
            playa[0] = authorization(playa[0], color[0], color[2]);
            if (start[0] == 3)
                playa[1] = authorization(playa[1], color[1], color[3]);
            else
                playa[1] = "◊Computer◊";
            startmenu.dispose();

            Thread.sleep(100);

            PlayScreen gamewindow = new PlayScreen(title[0]+"  //  level " +start[1]+";  "+playa[0]+" vs "+playa[1], start[0], start[1]);
            makeNewWindow(gamewindow, 800, 480);
            while (true)
            {
                Thread.sleep(100);
                if (!newgame) {
                    if (victory != '0') {
                        if (victory == '1')
                            JOptionPane.showMessageDialog(null, message[5] + color[0] + playa[0] + message[3], title[4],
                                    JOptionPane.INFORMATION_MESSAGE);
                        else if (victory == '2')
                            JOptionPane.showMessageDialog(null, message[5] + color[1] + playa[1] + message[3], title[4],
                                    JOptionPane.INFORMATION_MESSAGE);
                        if (JOptionPane.showConfirmDialog(null, "Еще партейку?", title[2],
                                JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                            System.exit(0);
                        else break;
                    }
                } else break;
            }
            gamewindow.desk = null;
            gamewindow.dispose();
            newgame = false;
            victory = '0';

            Thread.sleep(100);

        }

    }



    //Функция построения окна интерфейса
    //--------------------
    static void makeNewWindow (JFrame window, int sizeX, int sizeY){
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(sizeX, sizeY);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
    }
    //--------------------



    //Функция авторизации игрока
    //--------------------
    static String authorization (String player, String color, String colorname){
        String name = player;
        auth = false;
        if (player != null && player != "◊Computer◊") {
            if (JOptionPane.showConfirmDialog(null, message[8]+color+player+message[9], title[1],
                    JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
                auth = true;
        }
        if (!auth)
            name = JOptionPane.showInputDialog(null, message[6]+color+colorname+message[7], title[1], JOptionPane.QUESTION_MESSAGE);
        if ("".equals(name) || name == null)
            if (color == "blue>")
                return "1";
            else
                return "2";
        return name;
    }
    //--------------------

}
