// O.java
package src.piece;
import ch.aplu.jgamegrid.*;
import src.Tetris;
import src.factory.TetrisPieceFactory;
import src.TetroBlock;

/**
 * Workshop 4 Friday 9:00, Team 12
 * Yi Wei 1166107
 * Thanh Nguyen Pham 1166068
 * Ian Han 1180762
 */

public class O extends TetrisPiece implements TetrisPieceFactory
{
  private final int blockId = 3;
  private Location[][] r = new Location[4][4];
  private final String blockName = "O";
  private Tetris tetris;

  public O(Tetris tetris, boolean canRotated)
  {
    super(tetris,canRotated);
    this.tetris = tetris;
    // rotId 0
    r[0][0] = new Location(new Location(0, 0));
    r[1][0] = new Location(new Location(1, 0));
    r[2][0] = new Location(new Location(1, 1));
    r[3][0] = new Location(new Location(0, 1));
    // rotId 1
    r[0][1] = new Location(new Location(0, 0));
    r[1][1] = new Location(new Location(1, 0));
    r[2][1] = new Location(new Location(1, 1));
    r[3][1] = new Location(new Location(0, 1));
    // rotId 2
    r[0][2] = new Location(new Location(0, 0));
    r[1][2] = new Location(new Location(1, 0));
    r[2][2] = new Location(new Location(1, 1));
    r[3][2] = new Location(new Location(0, 1));
    // rotId 3
    r[0][3] = new Location(new Location(0, 0));
    r[1][3] = new Location(new Location(1, 0));
    r[2][3] = new Location(new Location(1, 1));
    r[3][3] = new Location(new Location(0, 1));

    for (int i = 0; i < r.length; i++)
      blocks.add(new TetroBlock(blockId, r[i]));
  }

  public String toString() {
    return "For testing, do not change: Block: " + blockName + ". Location: " + blocks + ". Rotation: " + rotId;}

  @Override
  public TetrisPiece create() {
    return new O(tetris,canRotate);
  }
}
