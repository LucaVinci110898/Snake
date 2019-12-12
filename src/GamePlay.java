import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements ActionListener, KeyListener {

    static private final int DELAY = 150;

    private Timer timer;
    private Snake snake;
    private Food food;

    private int score;
    private boolean play;
    private boolean perso;
    private int mangiati;

    public GamePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        snake = new Snake();
        timer = new Timer(DELAY, this);
        food  = new Food();
        score = 0;
        play  = false;
        perso = false;
        mangiati = 0;

        timer.start();
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);

        //Sfondo
        g2d.setColor(Color.black);
        g2d.fillRect(0,0, 584, 561);

        //Bordi
        g2d.setColor(Color.green);
        g2d.fillRect(0,17,2,544);
        g2d.fillRect(0,17,584,2);
        g2d.fillRect(582,17,2,544);
        g2d.fillRect(0,559,584,2);

        //Score
        g2d.setColor(Color.white);
        g2d.setFont(new Font("serif", Font.BOLD, 20));
        g2d.drawString("Score: "+score, 490, 15);
        g2d.drawString("Mangiati: "+mangiati, 10, 15);
        g2d.drawString("Lung.: "+snake.getLen(), 250, 15);

        food.drawFood(g2d);
        snake.drawSnake(g2d);

        if(!play && perso){
            g2d.setColor(Color.red);
            g2d.setFont(new Font("serif", Font.BOLD, 40));
            g2d.drawString("Hai Perso", 210, 300);
            g2d.setFont(new Font("serif", Font.BOLD, 20));
            g2d.drawString("Premi Invio per ricominciare", 160, 320);
        }

        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play) {
            snake.moveSnake();
            if (snake.rilevaCollisoniFood(food)) {
                score += 5;
                mangiati++;
            }
            if (snake.rilevaCollisioniSnake()){
                play = false;
                perso = true;
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                snake = new Snake();
                score = 0;
                mangiati = 0;
                play = true;
            }
            else
                play = true;
        }
        if(play) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                snake.setSnakeDirX(0);
                snake.setSnakeDirY(-20);
                snake.setOrientamento(Snake.Orientamento.NORD);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                snake.setSnakeDirX(0);
                snake.setSnakeDirY(20);
                snake.setOrientamento(Snake.Orientamento.SUD);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                snake.setSnakeDirX(20);
                snake.setSnakeDirY(0);
                snake.setOrientamento(Snake.Orientamento.EST);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                snake.setSnakeDirX(-20);
                snake.setSnakeDirY(0);
                snake.setOrientamento(Snake.Orientamento.OVEST);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
