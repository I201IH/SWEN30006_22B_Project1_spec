package src.difficulty;

import src.Tetris;

/**
 * Represents the difficulty of a game
 */
public abstract class Difficulty {
    /**
     * A tetris game
     */


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
