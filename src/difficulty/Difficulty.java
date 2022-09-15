package src.difficulty;

import src.Tetris;

/**
 * Workshop 4 Friday 9:00, Team 12
 * Yi Wei 1166107
 * Thanh Nguyen Pham 1166068
 * Ian Han 1180762
 */

/**
 * Represents the difficulty of a game
 */
public abstract class Difficulty {
    /**
     * Constructors to create object of class Difficulty
     * @param
     */
    Difficulty (){
    }

    /**
     * Set the speed of a game
     * @param score
     */
    public abstract int setSpeed(int score);

    /**
     * Determines if the blocks can rotate
     */
    public abstract boolean getCanRotate();
}
