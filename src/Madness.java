package src;

import java.util.Random;

public class Madness extends Difficulty{
    private final boolean canRotate = false;
    protected Tetris tetris;

    public Madness(Tetris tetris) {
        super(tetris);
        this.tetris = tetris;
    }

    public boolean getCanRotate(){
        return canRotate;
    }

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
        int speed = (int) (r.nextInt(currentSpeed+1) + currentSpeed);
        return speed;
    }

    //score and speed
}
