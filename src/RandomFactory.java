package src;

import java.util.ArrayList;
import java.util.Random;

public class RandomFactory{
    private int seed = 0;
    private int bound =0;
    TetrisPieceFactory[] factories;
    Random r = new Random(30006);

    public RandomFactory(TetrisPieceFactory[] factories){
        this.factories = factories;
    }


    public TetrisPiece create(int bound) {
        setBound(bound);
        int rnd = r.nextInt(bound);
        //setSeed(rnd);
        return factories[rnd].create();
    }

    public void setBound(int bound){
        this.bound = bound;
    }
    //method help find error
    public void setSeed(int seedTest){
        seed = seedTest;
    }
    //method help find error
    public int getSeed(){
        return seed;
    }
}
