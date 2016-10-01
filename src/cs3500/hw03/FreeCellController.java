package cs3500.hw03;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreeCellModel;
import cs3500.hw02.IFreeCellModel;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Created by jowenfan on 9/29/16.
 */
public class FreeCellController implements IFreeCellController<Card> {
  // Define fields
  private Readable rd;
  private Appendable ap;
  private IFreeCellModel<Card> model;


  /**
   * Constructor of FreeCellController.
   */
  public FreeCellController(Readable rd, Appendable ap) {
    this.rd = rd;
    this.ap = ap;
  }


  public void playGame(List<Card> deck, IFreeCellModel<Card> model, int numCascades,
                       int numOpens, boolean shuffle) {
    this.model = model;
    model.startGame(deck, numCascades, numOpens, shuffle);
    try {
      ap.append(model.getGameState());
    }
    catch (IOException e0) {}
    run();
  }

  private void run() {
    Scanner s1 = new Scanner(rd);
    while(!model.isGameOver()) {
      try {
        ap.append("\n");
        ap.append("Enter your move: " +
                "(SourceType, PileIndex, CardIndex, DestType, PileIndex)");
        String ST = s1.next();
      }

    }
  }

}
