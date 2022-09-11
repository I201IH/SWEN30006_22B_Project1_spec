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
    private int slowDown = 1;
    private Random random = new Random(0);
    private LinkedHashMap<String, Integer> count = new LinkedHashMap<>();
    protected Difficulty diff = new Easy(this);
    protected Easy easy;
    protected Medium medium = new Medium(this);
    protected Madness madness = new Madness(this);
    private boolean canRotated = true;



    private TetrisGameCallback gameCallback;


    private RandomFactory factory = new RandomFactory(new TetrisPieceFactory[]{
            new I(this, diff.getCanRotate()),
            new J(this, diff.getCanRotate()),
            new L(this, diff.getCanRotate()),
            new O(this, diff.getCanRotate()),
            new S(this, diff.getCanRotate()),
            new T(this, diff.getCanRotate()),
            new Z(this, diff.getCanRotate()),
            new P(this, diff.getCanRotate()),
            new Q(this, diff.getCanRotate()),
            new Plus(this, diff.getCanRotate())
    });

    private RandomFactory factory2 = new RandomFactory(new TetrisPieceFactory[]{
            new I(this, diff.getCanRotate()),
            new J(this, diff.getCanRotate()),
            new L(this, diff.getCanRotate()),
            new O(this, diff.getCanRotate()),
            new S(this, diff.getCanRotate()),
            new T(this, diff.getCanRotate()),
            new Z(this, diff.getCanRotate()),
            new P(this, diff.getCanRotate()),
            new Q(this, diff.getCanRotate()),
            new Plus(this, diff.getCanRotate())
            //if (difficulty != "easy") {
            //new P(this),
            //new Q(this),
            //new Plus(this)
            //}
    });

    RandomFactory factoryDiff = new RandomFactory(new TetrisPieceFactory[]{
            new I(this, diff.getCanRotate()),
            new J(this, diff.getCanRotate()),
            new L(this, diff.getCanRotate()),
            new O(this, diff.getCanRotate()),
            new S(this, diff.getCanRotate()),
            new T(this, diff.getCanRotate()),
            new Z(this, diff.getCanRotate()),
            new P(this, diff.getCanRotate()),
            new Q(this, diff.getCanRotate()),
            new Plus(this, diff.getCanRotate())
    });

    RandomFactory factoryDiff2 = new RandomFactory(new TetrisPieceFactory[]{
            new I(this, diff.getCanRotate()),
            new J(this, diff.getCanRotate()),
            new L(this, diff.getCanRotate()),
            new O(this, diff.getCanRotate()),
            new S(this, diff.getCanRotate()),
            new T(this, diff.getCanRotate()),
            new Z(this, diff.getCanRotate()),
            new P(this, diff.getCanRotate()),
            new Q(this, diff.getCanRotate()),
            new Plus(this, diff.getCanRotate())
    });




    private boolean isAuto = false;

    private String difficulty = "madness";
    private Statistics statistics;

    private int seed = 30006;
    // For testing mode, the block will be moved automatically based on the blockActions.
    // L is for Left, R is for Right, T is for turning (rotating), and D for down
    private String [] blockActions = new String[10];
    private int blockActionIndex = 0;

    public Difficulty selectDiff(String difficulty){
        switch (difficulty){
            case ("easy"):
                diff = new Easy(this);
                //System.out.println("easy value is "+ diff.getCanRotate());
                break;
            case("medium"):
                diff = new Medium(this);
                //System.out.println("medium value is "+ diff.getCanRotate());
                break;
            case("madness"):
                diff = new Madness(this);
                //System.out.println("madness value is "+ diff.getCanRotate());
                break;
        }
        return diff;
    }

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
        // Set up the UI components. No need to modify the UI Components
        tetrisComponents = new TetrisComponents();
        tetrisComponents.initComponents(this);
        gameGrid1.addActListener(this);
        gameGrid1.setSimulationPeriod(getSimulationTime());

        // Add the first block to start
        currentBlock = createRandomTetrisBlock();
        gameGrid1.addActor(currentBlock, new Location(6, 0));
        gameGrid1.doRun();

        // Do not lose keyboard focus when clicking this window
        gameGrid2.setFocusable(false);
        setTitle("SWEN30006 Tetris Madness");
        showScore(score);

        reset();

        statistics =  new Statistics(score, difficulty, count);
        diff = selectDiff(difficulty);
        easy = new Easy(this);
        medium = new Medium(this);
        madness = new Madness(this);

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

        RandomFactory current;
        RandomFactory current2;
        int bound = 7;

        if (difficulty.equals("easy")){
            bound = 7;
            current = factory;
            current2 = factory2;
        }
        else{
            bound = 10;
            current = factoryDiff;
            current2 = factoryDiff2;
        }

        // Randomly select, but test 2 does not follow
        //random in constructor=seed
        //easy = new Easy(this);
       // Actor currentPiece  = chooseFactory(difficulty).create(bound);


        Actor currentPiece = current.create(bound);
        //System.out.println("Test Error " + chooseFactory(difficulty).getSeed());
        //Actor currentPiece = factory.create(bound);
        if (isAuto) {
            ((TetrisPiece)currentPiece).setAutoBlockMove(currentBlockMove);
        }


        TetrisPiece preview = current2.create(bound);

        while (!preview.getClass().getName().equals(currentPiece.getClass().getName())){
            TetrisPiece test2 = current2.create(bound);
            //System.out.println("factory 2 's rnd is " + current2.getSeed());
            preview = test2;
        }

        calculateNumBlocks(preview);

        preview.display(gameGrid2, new Location(2, 1));
        blockPreview = preview;

        //Show preview tetrisBlock
        currentPiece.setSlowDown(slowDown);
        return currentPiece;

    }

    void setCurrentTetrisBlock(Actor t) {
        gameCallback.changeOfBlock(currentBlock);
        currentBlock = t;
    }

    private void calculateNumBlocks(TetrisPiece preview) {
        String blockName = preview.getClass().getSimpleName();
        if (!count.containsKey(blockName)) {
            count.put(blockName, 1);
        } else {
            count.put(blockName,
                    count.get(blockName) + 1);
        }
    }

    // Handle user input to move block. Arrow left to move left, Arrow right to move right, Arrow up to rotate and
    // Arrow down for going down
    private void moveBlock(int keyEvent) {
        switch (keyEvent) {
            case KeyEvent.VK_UP:
                if (diff.getCanRotate()){
                    ((TetrisPiece) currentBlock).rotate();
                }
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
                slowDown = diff.setSpeed(score);
            }
        }
    }

    /*
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

     */

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
        statistics.printStatistics(score, count);
        if (isAuto) {
            System.exit(0);
        }
    }

    private void reset() {
        count = new LinkedHashMap<>();
        count.put("I", 0);
        count.put("J", 0);
        count.put("L", 0);
        count.put("O", 0);
        count.put("S", 0);
        count.put("T", 0);
        count.put("Z", 0);
        count.put("+", 0);
        count.put("P", 0);
        count.put("Q", 0);
        score = 0;
        slowDown = 5;
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
        showScore(score);
        reset();
        //statistics.resetScore();
    }


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
