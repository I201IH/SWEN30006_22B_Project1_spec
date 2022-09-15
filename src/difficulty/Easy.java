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

    public Easy() {}

    /** This is a getter to get the CanRotate Boolean value
     *
     * @return boolean This returns the canRotate value in the Madness class
     */
    public boolean getCanRotate(){
        return canRotate;
    }

    /** This method is used to set the speed based on the scores
     *
     * @param score this is the score of player gets in current round
     * @return int This returns the modified speed after setSpeed method
     */
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
