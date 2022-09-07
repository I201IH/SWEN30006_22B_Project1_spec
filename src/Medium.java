package src;

import java.util.Properties;

public class Medium extends Tetris{

    public Medium(TetrisGameCallback gameCallback, Properties properties) {
        super(gameCallback, properties);
    }
    @Override
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
