package src.factory;

import src.piece.TetrisPiece;

import java.util.Random;

/**
 * This class creates tetrisPiece randomly based on difficulty, using factory pattern
 */
public class RandomFactory{
    private final int seed = 30006;
    private int bound = 0;
    TetrisPieceFactory[] factories;
    Random r = new Random(seed);

    /**
     * This constructor used to select random pieces
     * @param factories
     */
    public RandomFactory(TetrisPieceFactory[] factories){
        this.factories = factories;
    }

    /**
     *  This method used to create factories array
     * @param bound
     * @return
     */
    public TetrisPiece create(int bound) {
        setBound(bound);
        int rnd = r.nextInt(bound);
        return factories[rnd].create();
    }

    /**
     * This method used to set the bound of array based on different difficulty
     * @param bound int number of piece +1 in current difficulty
     */
    public void setBound(int bound){
        this.bound = bound;
    }
}
