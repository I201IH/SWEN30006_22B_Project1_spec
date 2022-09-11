package src;

import java.util.ArrayList;
import java.util.Random;

public class RandomFactory{
    private final int seed = 30006;
    private int bound =0;
    TetrisPieceFactory[] factories;
    Random r = new Random(seed);

    public RandomFactory(TetrisPieceFactory[] factories){
        this.factories = factories;
    }


    public TetrisPiece create(int bound) {
        setBound(bound);
        int rnd = r.nextInt(bound);
        return factories[rnd].create();
    }

    public void setBound(int bound){
        this.bound = bound;
    }
}
