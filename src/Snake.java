import java.awt.*;
import java.util.ArrayList;

public class Snake {

    static class SnakeNode{
        private int snakeNodePosX;
        private int snakeNodePosY;

        public SnakeNode(int snakePosX, int snakePosY){
            this.snakeNodePosX = snakePosX;
            this.snakeNodePosY = snakePosY;
        }

        public int getSnakeNodePosX() {
            return snakeNodePosX;
        }

        public void setSnakeNodePosX(int snakePosX) {
            this.snakeNodePosX = snakePosX;
        }

        public int getSnakeNodePosY() {
            return snakeNodePosY;
        }

        public void setSnakeNodePosY(int snakePosY) {
            this.snakeNodePosY = snakePosY;
        }
    }

    public enum Orientamento {NORD, EST, SUD, OVEST}

    private ArrayList<SnakeNode> snake;
    private int snakeDirX;
    private int snakeDirY;
    private int len;
    private Orientamento orientamento;

    public Snake(){
        snake = new ArrayList<>();
        snake.add(new SnakeNode((2+(4*20)), (19+(24*20))));
        snake.add(new SnakeNode((2+(3*20)), (19+(24*20))));
        snake.add(new SnakeNode((2+(2*20)), (19+(24*20))));
        snake.add(new SnakeNode((2+(1*20)), (19+(24*20))));
        len = 4;
        snakeDirX = 20;
        snakeDirY = 0;
        orientamento = Orientamento.EST;
    }

    public void drawSnake(Graphics2D g){
        g.setColor(Color.white);
        int i = 0;
        while(i < len) {
            int x = snake.get(i).snakeNodePosX;
            int y = snake.get(i).snakeNodePosY;
            g.fillRect(x, y, 20, 20);
            i++;
        }
    }

    public void moveSnake(){
        SnakeNode head = getSnakeHead();
        int precX = head.getSnakeNodePosX();
        int precY = head.getSnakeNodePosY();
        head.setSnakeNodePosX(head.getSnakeNodePosX() + snakeDirX);
        head.setSnakeNodePosY(head.getSnakeNodePosY() + snakeDirY);
        int i = 1;
        while(i < len){
            SnakeNode att = snake.get(i);
            int suppX = att.getSnakeNodePosX();
            int suppY = att.getSnakeNodePosY();
            att.setSnakeNodePosX(precX);
            att.setSnakeNodePosY(precY);
            precX = suppX;
            precY = suppY;
            i++;
        }
    }

    private void addToSnake(){
        SnakeNode newSnakeNode;
        SnakeNode lastSnakeNode = getLastNode();
        if(orientamento == Orientamento.NORD){
            newSnakeNode = new SnakeNode(lastSnakeNode.getSnakeNodePosX(), lastSnakeNode.getSnakeNodePosY() + 20);
            snake.add(newSnakeNode);
        }
        if(orientamento == Orientamento.EST){
            newSnakeNode = new SnakeNode(lastSnakeNode.getSnakeNodePosX() - 20, lastSnakeNode.getSnakeNodePosY());
            snake.add(newSnakeNode);
        }
        if(orientamento == Orientamento.SUD){
            newSnakeNode = new SnakeNode(lastSnakeNode.getSnakeNodePosX(), lastSnakeNode.getSnakeNodePosY() - 20);
            snake.add(newSnakeNode);
        }
        if(orientamento == Orientamento.OVEST){
            newSnakeNode = new SnakeNode(lastSnakeNode.getSnakeNodePosX() + 20, lastSnakeNode.getSnakeNodePosY());
            snake.add(newSnakeNode);
        }
        len++;
    }

    public boolean rilevaCollisoniFood(Food f){
        SnakeNode head = getSnakeHead();
        if(new Rectangle(head.snakeNodePosX, head.snakeNodePosY, 20, 20).intersects
                (new Rectangle(f.getFoodPosX(), f.getFoodPosY(), 20, 20))){
            f.spawnFood();
            addToSnake();
            return true;
        }
        else
            return false;
    }

    public boolean rilevaCollisioniSnake(){
        SnakeNode head = getSnakeHead();
        for(int i = 1; i < len; i++){
            int x = snake.get(i).getSnakeNodePosX();
            int y = snake.get(i).getSnakeNodePosY();
            Rectangle supp = new Rectangle(x, y, 20, 20);
            if(new Rectangle(head.getSnakeNodePosX(), head.getSnakeNodePosY(), 20, 20).intersects
                    (supp)){
                return true;
            }
        }
        return false;
    }

    public SnakeNode getSnakeHead(){
        return snake.get(0);
    }

    public SnakeNode getLastNode(){
        return snake.get(len-1);
    }

    public int getSnakeDirX() {
        return snakeDirX;
    }

    public void setSnakeDirX(int snakeDirX) {
        this.snakeDirX = snakeDirX;
    }

    public int getSnakeDirY() {
        return snakeDirY;
    }

    public void setSnakeDirY(int snakeDirY) {
        this.snakeDirY = snakeDirY;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public Orientamento getOrientamento(){
        return orientamento;
    }

    public void setOrientamento(Orientamento orientamento){
        this.orientamento = orientamento;
    }
}
