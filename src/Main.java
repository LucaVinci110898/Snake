import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame("Snake");
        GamePlay gp = new GamePlay();

        window.setVisible(true);
        window.setResizable(false);
        window.setBounds(10, 10, 600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(gp);



    }

}
