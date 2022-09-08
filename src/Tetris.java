package src;// Tetris.java

import ch.aplu.jgamegrid.*;

import java.security.Key;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class Tetris extends JFrame implements GGActListener {

    private Actor currentBlock = null;  // Currently active block
    private Actor blockPreview = null;   // block in preview window
    private int score = 0;
    private int slowDown = 5;
    private Random random = new Random(0);

    private TetrisGameCallback gameCallback;

    private RandomFactory factory = new RandomFactory(new TetrisPieceFactory[]{
            new I(this),
            new J(this),
            new L(this),
            new O(this),
            //new P(this),
            //new Q(this),
            new S(this),
            new T(this),
            new Z(this),
            //new Plus(this)
    });


    private boolean isAuto = false;

    private String difficulty = null;


    private int seed = 30006;
    // For testing mode, the block will be moved automatically based on the blockActions.
    // L is for Left, R is for Right, T is for turning (rotating), and D for down
    private String [] blockActions = new String[10];
    private int blockActionIndex = 0;

    // Initialise object
    private void initWithProperties(Properties properties) {
        this.seed = Integer.parseInt(properties.getProperty("seed", "30006"));
        random = new Random(seed);
        isAuto = Boolean.parseBoolean(properties.getProperty("isAuto"));
        String blockActionProperty = properties.getProperty("autoBlockActions", "");
        blockActions = blockActionProperty.split(",");
        difficulty = properties.getProperty("difficulty", "easy");
    }

    public Tetris(TetrisGameCallback gameCallback, Properties properties) {
        // Initialise value

        initWithProperties(properties);
        this.gameCallback = gameCallback;
        blockActionIndex = 0;
        System.out.println("LOcation");
        // Set up the UI components. No need to modify the UI Components
        tetrisComponents = new TetrisComponents();
        tetrisComponents.initComponents(this);
        gameGrid1.addActListener(this);
        gameGrid1.setSimulationPeriod(getSimulationTime());

        // Add the first block to start
        System.out.println("Error");

        currentBlock = createRandomTetrisBlock();
        gameGrid1.addActor(currentBlock, new Location(6, 0));
        gameGrid1.doRun();

        // Do not lose keyboard focus when clicking this window
        gameGrid2.setFocusable(false);
        setTitle("SWEN30006 Tetris Madness");
        score = 0;
        showScore(score);
        slowDown = 5;
    }

    // create a block and assign to a preview mode
    Actor createRandomTetrisBlock() {
        if (blockPreview != null)
            blockPreview.removeSelf();

        // If the game is in auto test mode, then the block will be moved according to the blockActions
        String currentBlockMove = "";
        if (blockActions.length > blockActionIndex) {
            currentBlockMove = blockActions[blockActionIndex];
        }

        blockActionIndex++;
        /*
        Actor t = new I(this);
        int rnd;

        if (difficulty.equals("easy")){
            rnd = random.nextInt(7);
        }
        else {
            rnd = random.nextInt(10);
        }
        System.out.println(difficulty);

         */




//        TetrisPiece testPiece = factory.create();
//        System.out.println(testPiece.getClass().getName());
//        t = testPiece;
//        if (isAuto) {
//            ((TetrisPiece) t).setAutoBlockMove(currentBlockMove);
//        }

        /*
        TetrisPiece preview = testPiece;
        //I[rnd] I = new pieceList[rnd](this);
        preview.display(gameGrid2, new Location(2, 1));
        blockPreview = preview;*/

        /*
        public void setPreview(TetrisPiece t){
            t.display(gameGrid2, new Location(2, 1));
            blockPreview = t;
        }

         */



        // Randomly select, but test 2 does not follow
        //random in constructor=seed
        int bound = 7;
        Actor currentPiece = factory.create(bound);
        if (isAuto) {
            ((TetrisPiece)currentPiece).setAutoBlockMove(currentBlockMove);
            System.out.println(currentPiece.getClass().getName());
        }

        RandomFactory factory2 = new RandomFactory(new TetrisPieceFactory[]{
                new I(this),
                new J(this),
                new L(this),
                new O(this),
                new S(this),
                new T(this),
                new Z(this),
                /* Add if difficulty != "easy"
                //new P(this),
                //new Q(this),
                //new Plus(this)
                 */
        });
        TetrisPiece preview = factory2.create(bound);
        while (!preview.getClass().getName().equals(currentPiece.getClass().getName())){
            TetrisPiece test2 = factory2.create(bound);
            preview = test2;
        }
        //System.out.println(preview.getClass().getName());
        //System.out.println(factory2.getSeed());
        preview.display(gameGrid2, new Location(2, 1));
        blockPreview = preview;
/*
        Actor t = null;
        int rnd = random.nextInt(7);
        System.out.println("random = "+ random);
        System.out.println("rnd = "+rnd);
        switch (rnd) {
            case 0:
                t = new I(this);
                if (isAuto) {
                    ((I) t).setAutoBlockMove(currentBlockMove);
                }

                I previewI = new I(this);
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
        }



        /*
        switch (rnd) {
            case 0: {
                t = new I(this);
                I preview = new I(this);
                break;
            }
            case 1: {
                t = new J(this);
                J preview = new J(this);
                break;
            }
            case 2:
            {
                t = new L(this);
                L preview = new L(this);
                break;
            }
            case 3:
            {
                t = new O(this);
                O preview = new O(this);
                break;
            }
            case 4:
            {
                t = new S(this);
                S preview = new S(this);
                break;
            }
            case 5:
            {
                t = new T(this);
                T preview = new T(this);
                break;
            }

            case 6:
            {
                t = new Z(this);
                Z preview = new Z(this);
                break;
            }
            case 7:
            {
                t = new P(this);
                P preview = new P(this);
                break;
            }
            case 8:
            {
                t = new Plus(this);
                Plus preview = new Plus(this);
                break;
            }
            default:
            {
                t = new Q(this);
                Q preview = new Q(this);

            }

        }

        if (isAuto) {
            ((TetrisPiece) t).setAutoBlockMove(currentBlockMove);
        }
        preview.display(gameGrid2, new Location(2, 1));
        blockPreview = preview;


         */

        // Show preview tetrisBlock
        //t.setSlowDown(slowDown);
       // return t;
        //Show preview tetrisBlock
        currentPiece.setSlowDown(slowDown);
        return currentPiece;

    }

    void setCurrentTetrisBlock(Actor t) {
        gameCallback.changeOfBlock(currentBlock);
        currentBlock = t;
    }

    // Handle user input to move block. Arrow left to move left, Arrow right to move right, Arrow up to rotate and
    // Arrow down for going down
    private void moveBlock(int keyEvent) {
        switch (keyEvent) {
            case KeyEvent.VK_UP:
                ((TetrisPiece) currentBlock).rotate();
                break;
            case KeyEvent.VK_LEFT:
                ((TetrisPiece) currentBlock).left();
                break;
            case KeyEvent.VK_RIGHT:
                ((TetrisPiece) currentBlock).right();
                break;
            case KeyEvent.VK_DOWN:
                ((TetrisPiece) currentBlock).drop();
                break;
            default:
                return;
        }
    }

    public void act() {
        removeFilledLine();
        moveBlock(gameGrid1.getKeyCode());
    }
    private void removeFilledLine() {
        for (int y = 0; y < gameGrid1.nbVertCells; y++) {
            boolean isLineComplete = true;
            TetroBlock[] blocks = new TetroBlock[gameGrid1.nbHorzCells];   // One line
            // Calculate if a line is complete
            for (int x = 0; x < gameGrid1.nbHorzCells; x++) {
                blocks[x] =
                        (TetroBlock) gameGrid1.getOneActorAt(new Location(x, y), TetroBlock.class);
                if (blocks[x] == null) {
                    isLineComplete = false;
                    break;
                }
            }
            if (isLineComplete) {
                // If a line is complete, we remove the component block of the shape that belongs to that line
                for (int x = 0; x < gameGrid1.nbHorzCells; x++)
                    gameGrid1.removeActor(blocks[x]);
                ArrayList<Actor> allBlocks = gameGrid1.getActors(TetroBlock.class);
                for (Actor a : allBlocks) {
                    int z = a.getY();
                    if (z < y)
                        a.setY(z + 1);
                }
                gameGrid1.refresh();
                score++;
                gameCallback.changeOfScore(score);
                showScore(score);
                /*
                // Set speed of tetrisBlocks
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

                 */
                setSpeed(score);
            }
        }
    }

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

    // Show Score and Game Over

    private void showScore(final int score) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                scoreText.setText(score + " points");
            }
        });

    }

    void gameOver() {
        gameGrid1.addActor(new Actor("sprites/gameover.gif"), new Location(5, 5));
        gameGrid1.doPause();
        if (isAuto) {
            System.exit(0);
        }
    }

    // Start a new game
    public void startBtnActionPerformed(java.awt.event.ActionEvent evt)
    {
        gameGrid1.doPause();
        gameGrid1.removeAllActors();
        gameGrid2.removeAllActors();
        gameGrid1.refresh();
        gameGrid2.refresh();
        gameGrid2.delay(getDelayTime());
        blockActionIndex = 0;
        currentBlock = createRandomTetrisBlock();
        gameGrid1.addActor(currentBlock, new Location(6, 0));
        gameGrid1.doRun();
        gameGrid1.requestFocus();
        score = 0;
        showScore(score);
        slowDown = 5;
    }

    //randomly select from the TetrisPiece
    /*
    public Actor randomPiece(){

    }

     */

    // Different speed for manual and auto mode
    private int getSimulationTime() {
        if (isAuto) {
            return 10;
        } else {
            return 100;
        }
    }

    private int getDelayTime() {
        if (isAuto) {
            return 200;
        } else {
            return 2000;
        }
    }

    // AUTO GENERATED - do not modify//GEN-BEGIN:variables
    public ch.aplu.jgamegrid.GameGrid gameGrid1;
    public ch.aplu.jgamegrid.GameGrid gameGrid2;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField scoreText;
    public javax.swing.JButton startBtn;
    private TetrisComponents tetrisComponents;
    // End of variables declaration//GEN-END:variables

}
