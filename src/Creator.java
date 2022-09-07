package src;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location;

import java.util.Properties;

/*public class Creator extends Tetris{

    String difficulty;
    boolean isAuto;
    public Creator(TetrisGameCallback gameCallback, Properties properties, String difficulty, boolean isAuto) {
        super(gameCallback, properties);
        this.difficulty = difficulty;
        this.isAuto = isAuto;
    }

    Actor createRandomTetrisBlock() {
        if (blockPreview != null)
            blockPreview.removeSelf();

        // If the game is in auto test mode, then the block will be moved according to the blockActions
        String currentBlockMove = "";
        if (blockActions.length > blockActionIndex) {
            currentBlockMove = blockActions[blockActionIndex];
        }

        blockActionIndex++;
        Actor t = null;
        int rnd;

        if (difficulty.equals("easy")){
            rnd = random.nextInt(7);
        }
        else {
            rnd = random.nextInt(10);
        }
        System.out.println(difficulty);

        J j = new J(this);


        Actor pieceList[] = [new I(this), new J(this), new L(this), new O(this), new S(this),
                new T(this), new Z(this), new P(this), new Plus(this), new Q(this)];



        switch (rnd) {
            case 0:
                t = new I(this);
                if (isAuto) {
                    ((I) t).setAutoBlockMove(currentBlockMove);
                }
                I previewI = new I(this);
                //I[rnd] I = new pieceList[rnd](this);
                previewI.display(gameGrid2, new Location(2, 1));
                blockPreview = previewI;
                break;
            case 1:
                t = new J(this);
                if (isAuto) {
                    ((J) t).setAutoBlockMove(currentBlockMove);
                }
                J previewJ = new J(this);
                previewJ.display(gameGrid2, new Location(2, 1));
                blockPreview = previewJ;
                break;
            case 2:
                t = new L(this);
                if (isAuto) {
                    ((L) t).setAutoBlockMove(currentBlockMove);
                }
                L previewL = new L(this);
                previewL.display(gameGrid2, new Location(2, 1));
                blockPreview = previewL;
                break;
            case 3:
                t = new O(this);
                if (isAuto) {
                    ((O) t).setAutoBlockMove(currentBlockMove);
                }
                O previewO = new O(this);
                previewO.display(gameGrid2, new Location(2, 1));
                blockPreview = previewO;
                break;
            case 4:
                t = new S(this);
                if (isAuto) {
                    ((S) t).setAutoBlockMove(currentBlockMove);
                }
                S previewS = new S(this);
                previewS.display(gameGrid2, new Location(2, 1));
                blockPreview = previewS;
                break;
            case 5:
                t = new T(this);
                if (isAuto) {
                    ((T) t).setAutoBlockMove(currentBlockMove);
                }
                T previewT = new T(this);
                previewT.display(gameGrid2, new Location(2, 1));
                blockPreview = previewT;
                break;
            case 6:
                t = new Z(this);
                if (isAuto) {
                    ((Z) t).setAutoBlockMove(currentBlockMove);
                }
                Z previewZ = new Z(this);
                previewZ.display(gameGrid2, new Location(2, 1));
                blockPreview = previewZ;
                break;
            case 7:
                t = new P(this);
                if (isAuto) {
                    ((P) t).setAutoBlockMove(currentBlockMove);
                }
                P previewP = new P(this);
                previewP.display(gameGrid2, new Location(2, 1));
                blockPreview = previewP;
                break;
            case 8:
                t = new Plus(this);
                if (isAuto) {
                    ((Plus) t).setAutoBlockMove(currentBlockMove);
                }
                Plus previewPlus = new Plus(this);
                previewPlus.display(gameGrid2, new Location(2, 1));
                blockPreview = previewPlus;
                break;
            case 9:
                t = new Q(this);
                if (isAuto) {
                    ((Q) t).setAutoBlockMove(currentBlockMove);
                }
                Q previewQ = new Q(this);
                previewQ.display(gameGrid2, new Location(2, 1));
                blockPreview = previewQ;
                break;
        }
        // Show preview tetrisBlock

        t.setSlowDown(slowDown);
        return t;
    }
}*/
