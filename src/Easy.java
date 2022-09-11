package src;

import java.util.Properties;

/**
 * Represents level Easy
 */
public class Easy extends Difficulty{
    protected Tetris tetris;
    protected RandomFactory factory;
    private final boolean canRotate = true;

    public boolean getCanRotate(){
        return canRotate;
    }

    public Easy(Tetris tetris) {
        super(tetris);
        this.tetris = tetris;
    }

    public int setSpeed(int score){
        int slowDown = 5;
        if (score > 10)
            slowDown = 4;
        if (score > 20)
            slowDown = 3;
        if (score > 30)
            slowDown = 2;
        if (score > 40)
            slowDown = 1;
        if (score > 50)
            slowDown = 0;
        return slowDown;
    }


    //change speed
    //disable rotate function - make canRotate - false


}
