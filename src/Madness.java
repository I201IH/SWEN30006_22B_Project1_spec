package src;

import java.util.Random;

public class Madness extends Difficulty{
    private final boolean canRotate = false;
    protected Tetris tetris;

    public Madness(Tetris tetris) {
        super(tetris);
        this.tetris = tetris;
    }

    public boolean getCanRotate(){
        //System.out.println("Madness in getCanRotate is "+ canRotate);
        return canRotate;
    }

    /*
    protected RandomFactory factory = new RandomFactory(new TetrisPieceFactory[]{
            new I(tetris, canRotate),
            new J(tetris, canRotate),
            new L(tetris, canRotate),
            new O(tetris, canRotate),
            new S(tetris, canRotate),
            new T(tetris, canRotate),
            new Z(tetris, canRotate),
            new P(tetris, canRotate),
            new Q(tetris, canRotate),
            new Plus(tetris, canRotate)
    });

    protected RandomFactory factory2 = new RandomFactory(new TetrisPieceFactory[]{
            new I(tetris, false),
            new J(tetris),
            new L(tetris),
            new O(tetris),
            new S(tetris),
            new T(tetris),
            new Z(tetris),
            new P(tetris),
            new Q(tetris),
            new Plus(tetris)
    });


     */


    public int setSpeed(int score){
        int slowDown = (int) (5 * 0.5);
        if (score > 10)
            slowDown = (int) (4 * 0.5);
        if (score > 20)
            slowDown = (int) (3 * 0.5);
        if (score > 30)
            slowDown = (int) (2 * 0.5);
        if (score > 40)
            slowDown = (int) (1 * 0.5);
        if (score > 50)
            slowDown = 0;
        //System.out.println("previous: " + slowDown);
        slowDown = randomSpeed(slowDown);
        //System.out.println("after random: " +slowDown);
        return slowDown;
    }

    public int randomSpeed(int currentSpeed){
        Random r = new Random();
        int speed = (int) (r.nextInt(currentSpeed+1) + currentSpeed);
        return speed;
    }

    //score and speed
}
