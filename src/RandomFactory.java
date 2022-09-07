package src;

import java.util.ArrayList;
import java.util.Random;

public class RandomFactory implements TetrisPieceFactory{
    TetrisPieceFactory[] factories;
    Random r = new Random();

    public RandomFactory(TetrisPieceFactory[] factories){
        this.factories = factories;
    }

    @Override
    public TetrisPiece create() {
        int rnd = r.nextInt(7);
        return factories[rnd].create();
    }
}
