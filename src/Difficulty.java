package src;

public abstract class Difficulty {
    private Tetris tetris;
    //private boolean canRotate;
    Difficulty (Tetris tetris){
        this.tetris = tetris;
    }

    public abstract int setSpeed(int score);
    public abstract boolean getCanRotate();

}
