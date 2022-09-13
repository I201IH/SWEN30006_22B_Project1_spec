package src.piece;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;
import src.Tetris;
import src.TetroBlock;

import java.util.ArrayList;

/**
 * Represents a tetris piece as a parent class for all the other specific pieces
 */
public abstract class TetrisPiece extends Actor {

    /**
     * Location of a block
     */
    private Location[][] r = new Location[4][4];
    /**
     * A tetris game
     */
    protected Tetris tetris;
    /**
     * Whether a block can rotate
     */
    protected boolean canRotate;
    private boolean ptn = false;


    /**
     * Constructor to create an object of class TetrisPiece
     * @param tetris
     * @param canRotate
     */
    TetrisPiece(Tetris tetris, boolean canRotate){
        super();
        this.tetris = tetris;
        this.canRotate = canRotate;
    }

    /**
     * Whether a game is starting
     */
    private boolean isStarting = true;
    /**
     * Rotation id of a block
     */
    protected int rotId = 0;
    private int nb;
    protected ArrayList<TetroBlock> blocks = new ArrayList<TetroBlock>();
    private Actor nextTetrisBlock = null;
    private String autoBlockMove = "";
    private int autoBlockIndex = 0;
    public void setAutoBlockMove(String autoBlockMove) {
        this.autoBlockMove = autoBlockMove;
    }

    public boolean setCanRotate(boolean canRotate){
        return canRotate;
    }

    /**
     * The game is called in a run loop, this method for a block is called every 1/30 seconds as the starting point
     */

    public void act()
    {
        if (isStarting) {
            for (TetroBlock a : blocks) {
                Location loc =
                        new Location(getX() + a.getRelLoc(0).x, getY() + a.getRelLoc(0).y);
                gameGrid.addActor(a, loc);
            }
            isStarting = false;
            nb = 0;
        } else if (nb >= blocks.size() && canAutoPlay()) {
            autoMove();
        } else
        {
            setDirection(90);
            if (nb == 1)
                nextTetrisBlock = tetris.createRandomTetrisBlock();
            if (!advance())
            {
                if (nb == 0)  // Game is over when tetrisBlock cannot fall down
                    tetris.gameOver();
                else
                {
                    setActEnabled(false);
                    gameGrid.addActor(nextTetrisBlock, new Location(6, 0));
                    tetris.setCurrentTetrisBlock(nextTetrisBlock);
                }
            }
            nb++;
        }
    }

    /**
     * Based on the input in the properties file, the block can move automatically
     */
    private void autoMove() {
        String moveString = autoBlockMove.substring(autoBlockIndex, autoBlockIndex + 1);
        switch (moveString) {
            case "L":
                left();
                break;
            case "R":
                right();
                break;
            case "T":
                if (canRotate){
                    rotate();
                }
                break;
            case "D":
                drop();
                break;
        }
        autoBlockIndex++;
    }

    /**
     * Check if the block can be played automatically based on the properties file
     * @return boolean Determines if a block can be automatically played if property auto is true
     */
    //
    private boolean canAutoPlay() {
        if (autoBlockMove != null && !autoBlockMove.equals("")) {
            if (autoBlockMove.length() > autoBlockIndex) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Display the image of the block
     * @param gg
     * @param location
     */
    void display(GameGrid gg, Location location)
    {
        for (TetroBlock a : blocks)
        {
            Location loc =
                    new Location(location.x + a.getRelLoc(0).x, location.y + a.getRelLoc(0).y);
            gg.addActor(a, loc);
        }
    }

    /**
     * Move the block left, right, drop and rotate the block
     */
    public void left()
    {
        if (isStarting)
            return;
        setDirection(180);
        advance();
    }

    /**
     * Move the block right
     */
    public void right()
    {
        if (isStarting)
            return;
        setDirection(0);
        advance();
    }

    /**
     * Rotate the block
     */
    public void rotate()
    {
        if (isStarting)
            return;

        int oldRotId = rotId; // Save it
        rotId++;
        if (rotId == 4)
            rotId = 0;
        if (canRotate){
            if (canRotate(rotId))
            {
                for (TetroBlock a : blocks)
                {
                    Location loc = new Location(getX() + a.getRelLoc(rotId).x, getY() + a.getRelLoc(rotId).y);
                    a.setLocation(loc);
                }
            }
        }
        else
            rotId = oldRotId;  // Restore

    }


    /**
     * Determines if a block can be rotated
     * @param rotId
     * @return boolean true if a block can be rotated
     */
    private boolean canRotate(int rotId)
    {
        // Check for every rotated tetroBlock within the tetrisBlock
        for (TetroBlock a : blocks)
        {
            Location loc =
                    new Location(getX() + a.getRelLoc(rotId).x, getY() + a.getRelLoc(rotId).y);
            if (!gameGrid.isInGrid(loc))  // outside grid->not permitted
                return false;
            TetroBlock block =
                    (TetroBlock)(gameGrid.getOneActorAt(loc, TetroBlock.class));
            if (blocks.contains(block))  // in same tetrisBlock->skip
                break;
            if (block != null)  // Another tetroBlock->not permitted
                return false;
        }
        return true;
    }

    /**
     * Drop the block
     */
    public void drop()
    {
        if (isStarting)
            return;
        setSlowDown(0);
    }

    /**
     * Logic to check if the block has been removed (as winning a line) or drop to the bottom
     * @return boolean true if the block has been removed (as winning a line) or drop to the bottom
     */
    private boolean advance()
    {
        boolean canMove = false;
        for (TetroBlock a: blocks) {
            if (!a.isRemoved()) {
                canMove = true;
            }
        }
        for (TetroBlock a : blocks)
        {
            if (a.isRemoved())
                continue;
            if (!gameGrid.isInGrid(a.getNextMoveLocation()))
            {
                canMove = false;
                break;
            }
        }

        for (TetroBlock a : blocks)
        {
            if (a.isRemoved())
                continue;
            TetroBlock block =
                    (TetroBlock)(gameGrid.getOneActorAt(a.getNextMoveLocation(),
                            TetroBlock.class));
            if (block != null && !blocks.contains(block))
            {
                canMove = false;
                break;
            }
        }

        if (canMove)
        {
            move();
            return true;
        }
        return false;
    }


    /**
     * Override Actor.setDirection()
     * @param dir
     */
    public void setDirection(double dir)
    {
        super.setDirection(dir);
        for (TetroBlock a : blocks)
            a.setDirection(dir);
    }

    /**
     * Override Actor.move()
     */
    public void move()
    {
        if (isRemoved())
            return;
        super.move();
        for (TetroBlock a : blocks)
        {
            if (a.isRemoved())
                break;
            a.move();
        }
        String word = toString();
        System.out.println(word);
    }

    /**
     * Override Actor.removeSelf()
     */
    public void removeSelf()
    {
        super.removeSelf();
        for (TetroBlock a : blocks)
            a.removeSelf();
    }

    /**
     * Get the rotation id of a block
     * @return The rotation id of a block
     */
    public int getRotId(){
        return this.rotId;
    }
}
