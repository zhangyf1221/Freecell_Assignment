////
//// DO NOT MODIFY THIS FILE
////
//// You don't need to submit it, but you should make sure it compiles.
//// Further explanation appears below.
////


import cs3500.hw02.PileType;
import cs3500.hw03.IFreeCellController;
import cs3500.hw03.FreeCellController;
import cs3500.hw02.IFreeCellModel;
import cs3500.hw02.FreeCellModel;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

/**
 * This class is provided to check that your code implements the expected API.
 * If your code compiles with an unmodified version of this class, then it very
 * likely will also compile with the tests that we use to evaluate your code.
 */
public class Hw03TypeChecks {

  // This doesn't really need to be a dynamic method, since it doesn't use `this`
  static void checkSignatures() {
    Reader stringReader;
    StringBuffer out;
        
    checkNewModel(new FreeCellModel(), new FreeCellModel().getDeck());
    stringReader = new StringReader("C1 8 F1 q");
    out = new StringBuffer();
    checkNewController(new FreeCellModel(), new FreeCellModel().getDeck(),
            new FreeCellController(stringReader, out));
  }

  // This doesn't really need to be a dynamic method, since it doesn't use `this`
  static <K> void checkNewController(IFreeCellModel<K> model, List<K> deck,
                                     IFreeCellController<K> controller) {
    String input = "4 3";

    controller.playGame(deck, model, 7, 4, false);
  }

  static <K> void checkNewModel(IFreeCellModel<K> model, List<K> deck) {
    List<K> initialDeck = model.getDeck();
    model.startGame(initialDeck, 7, 4, false);
    model.move(PileType.CASCADE, 0, 7, PileType.FOUNDATION, 0);
    String result = model.getGameState();
    boolean done = model.isGameOver();
  }

  private Hw03TypeChecks() {
    throw new RuntimeException("Don't instantiate this: use it as a reference");
  }
}
