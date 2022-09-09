package src;


public class Medium extends Difficulty{

    protected Tetris tetris;
    private final boolean canRotate = true;
    public Medium(Tetris tetris) {
        super(tetris);
        this.tetris = tetris;
    }

    public boolean getCanRotate(){
        return canRotate;
    }
    //create random piece
    /*
    protected RandomFactory factory = new RandomFactory(new TetrisPieceFactory[]{
            new I(tetris),
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

    /*
    protected RandomFactory factory2 = new RandomFactory(new TetrisPieceFactory[]{
            new I(tetris),
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
