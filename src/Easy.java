package src;

import java.util.Properties;

public class Easy extends Difficulty{
    protected Tetris tetris;
    protected RandomFactory factory;
    private final boolean canRotate = true;

    public boolean getCanRotate(){
        return canRotate;
    }

    public Easy(Tetris tetris) {
        super(tetris);
        this.tetris = tetris;
        /*
        factory = new RandomFactory(new TetrisPieceFactory[]{
                new I(this.tetris),
                new J(this.tetris),
                new L(this.tetris),
                new O(this.tetris),
                new S(this.tetris),
                new T(this.tetris),
                new Z(this.tetris),
        });

         */


        //super(gameCallback, properties);
    }


    /*
    protected RandomFactory factory = new RandomFactory(new TetrisPieceFactory[]{
            new I(tetris),
            new J(tetris),
            new L(tetris),
            new O(tetris),
            new S(tetris),
            new T(tetris),
            new Z(tetris),
    });

     */


/*
    protected RandomFactory factory2 = new RandomFactory(new TetrisPieceFactory[]{
            new I(tetris),
            new J(tetris),
            new L(tetris),
            new O(tetris),
            new S(tetris),
            new T(tetris),
            new Z(tetris),
    });

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


    //change speed
    //disable rotate function - make canRotate - false


}
