package src.difficulty;

import src.factory.RandomFactory;
import src.Tetris;

/**
 * Workshop 4 Friday 9:00, Team 12
 * Yi Wei 1166107
 * Thanh Nguyen Pham 1166068
 * Ian Han 1180762
 */

/**
 * Represents level Easy
 */
public class Easy extends Difficulty {
    private final boolean canRotate = true;
    public boolean getCanRotate(){
        return canRotate;
    }

    public Easy() {}

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
}
