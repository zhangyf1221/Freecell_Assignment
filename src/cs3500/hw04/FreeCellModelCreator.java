package cs3500.hw04;

/**
 * Created by GentleFan on 10/10/2016.
 */
import cs3500.hw02.FreeCellModel;

/**
 * The factory class to build a FreeCellModel.
 */
public class FreeCellModelCreator {
  public enum GameType {
    /**
     * Only allows player to move one card in the cascade pile each time.
     */
    SINGLEMOVE,
    /**
     * Allows player to move multiple cards in one time but has two conditions.
     * 1. These cards to be moved should form a valid build.
     * 2. These cards should form a build with the last card in the destination cascade pile.
     */
    MULTIMOVE;
  }

  /**
   * Create a regular single move FreeCell model or a multimove FreeCell model with given type.
   * @param type type of game which is singlemove or multimove
   * @return FreeCellModel with given game type
   */
  public static FreeCellModel create(GameType type) {
    switch (type) {
      case MULTIMOVE:
        return new FreeCellMultimoveModel();
      case SINGLEMOVE:
        return new FreeCellModel();
      default:
        throw new IllegalArgumentException("Invalid game type.");
    }
  }

}
