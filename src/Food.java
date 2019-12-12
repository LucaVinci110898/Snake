import java.awt.*;
import java.util.Random;

public class Food {

    private int foodPosX;
    private int foodPosY;


    public Food() {
        spawnFood();
    }

    public void spawnFood(){
        Random random = new Random();
        int x = random.nextInt(28);
        int y = random.nextInt(26);
        foodPosX = 2 + (x * 20);
        foodPosY = 19 + (y * 20);
    }

    public void drawFood(Graphics2D g){
        g.setColor(Color.red);
        g.fillRect(foodPosX, foodPosY, 20, 20);
    }

    public int getFoodPosX() {
        return foodPosX;
    }

    public void setFoodPosX(int foodPosX) {
        this.foodPosX = foodPosX;
    }

    public int getFoodPosY() {
        return foodPosY;
    }

    public void setFoodPosY(int foodPosY) {
        this.foodPosY = foodPosY;
    }
}
