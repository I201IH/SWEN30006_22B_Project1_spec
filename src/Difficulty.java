package src;

/**
 * Represents the difficulty of a game
 */
public abstract class Difficulty {
    /**
     * A tetris game
     */
    private Tetris tetris;


    /**
     * Constructors to create object of class Difficulty
     * @param tetris
     */
    Difficulty (Tetris tetris){
        this.tetris = tetris;
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
