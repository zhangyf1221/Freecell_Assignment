package cs3500.hw03;
import java.util.List;
import cs3500.hw02.IFreeCellModel;

/**
 * Created by jowenfan on 9/29/16.
 */

/**
 * This is the interface of the Freecell controller.
 */

public interface IFreeCellController<K> {
  /**
   * The method starts a new game with given deck, FreeCellModel, number of cascade pile,
   * number of open pile and decide to shuffle the deck or not.
   * @param deck list of cards that given to play the game
   * @param model a freecell game model
   * @param numCascades number of cascade pile
   * @param numOpens number of open pile
   * @param shuffle shuffle the given deck or not
   */
  void playGame(List<K> deck, IFreeCellModel<K> model, int numCascades,
                int numOpens, boolean shuffle);
}
