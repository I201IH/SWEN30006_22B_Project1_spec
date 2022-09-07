package src;

import java.util.Properties;
import java.util.Random;

public class Madness extends Tetris{
    public Madness(TetrisGameCallback gameCallback, Properties properties) {
        super(gameCallback, properties);
    }

    @Override
    public void act(){

    }


    private boolean canRotate = false;


    public int setSpeed(int score){
        int slowDown = (int) (5 * 0.5);
        if (score > 10)
            slowDown = (int) (4 * 0.5);
        if (score > 20)
            slowDown = (int) (3 * 0.5);
        if (score > 30)
            slowDown = (int) (2 * 0.5);
        if (score > 40)
            slowDown = (int) (1 * 0.5);
        if (score > 50)
            slowDown = 0;
        slowDown = randomSpeed(slowDown);

        return slowDown;
    }

    public int randomSpeed(int currentSpeed){
        Random r = new Random();
        int speed = (int) (r.nextInt(currentSpeed+1));
        return speed;
    }

    //score and speed
}
