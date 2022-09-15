package src.difficulty;

import src.Tetris;

/**
 * Workshop 4 Friday 9:00, Team 12
 * Yi Wei 1166107
 * Thanh Nguyen Pham 1166068
 * Ian Han 1180762
 */

/**
 * Represents level Medium
 */
public class Medium extends Difficulty {
    private final boolean canRotate = true;
    public Medium() {}

    /** This is a getter to get the CanRotate Boolean value
     *
     * @return boolean This returns the canRotate value in the Medium class
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
        int slowDown = (int) (5 * 0.8);
        if (score > 10)
            slowDown = (int) (4 * 0.8);
        if (score > 20)
            slowDown = (int) (3 * 0.8);
        if (score > 30)
            slowDown = (int) (2 * 0.8);
        if (score > 40)
            slowDown = (int) (1 * 0.8);
        if (score > 50)
            slowDown = 0;
        return slowDown;
    }
}
