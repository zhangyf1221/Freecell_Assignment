package cs3500.hw03;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreeCellModel;
import cs3500.hw02.IFreeCellModel;
import cs3500.hw02.PileType;

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
    } catch (IOException e0) {
    }
    run();
  }

  private void run() {
    Scanner s = new Scanner(rd);
    while (!model.isGameOver()) {
      try {
        ap.append("\n");
        ap.append("Enter your move: (SourceType, SourceType Index, " +
                "CardIndex, DestType, DestTypeIndex)");
        ap.append("\n");

        String ST = s.next();

        boolean isToExit = false;

        PileType sourceType;
        PileType destType;

        switch (ST) {
          case "OPEN":
            sourceType = PileType.OPEN;
            break;
          case "CASCADE":
            sourceType = PileType.CASCADE;
            break;
          case "Foundation":
            sourceType = PileType.FOUNDATION;
            break;
          case "Q":
            isToExit = true;
            sourceType = PileType.CASCADE;
            break;
          case "q":
            isToExit = true;
            sourceType = PileType.CASCADE;
            break;
          default:
            throw new IllegalArgumentException("Invalid source pile type");
        }


        int SI = s.nextInt();
        int CI = s.nextInt();
        String DT = s.next();
        int DI = s.nextInt();

        switch (DT) {
          case "OPEN":
            destType = PileType.OPEN;
            break;
          case "CASCADE":
            destType = PileType.CASCADE;
            break;
          case "Foundation":
            destType = PileType.FOUNDATION;
            break;
          default:
            throw new IllegalArgumentException("Invalid destination pile type");
        }

        if (isToExit) {
          try {
            ap.append("Game quit prematurely.");
          } catch (IOException e4) {
          }
        } else {
          try {
            model.move(sourceType, SI, CI, destType, DI);
            ap.append(model.getGameState());

          } catch (IllegalArgumentException e1) {
            try {
              ap.append("Invalid move. Try again.");
            } catch (IOException e2) {
            }
          }
        }
      } catch (IOException e3) {
      }
    }
  }

  public static void main(String[] args) {
    FreeCellController controller =
            new FreeCellController(new InputStreamReader(System.in), System.out);
    FreeCellModel model = new FreeCellModel();
    Scanner s = new Scanner(controller.rd);

    boolean inProgress = true;
    boolean isToExit = false;

    while (inProgress) {
      try {
        controller.ap.append("Enter number of cascade pile, number of open pile " +
                "and shuffle or not");
        String cascade = s.next();
        String open = s.next();
        String shuffle = s.next();
        int numCascade;
        int numOpen;
        boolean shf;

        if (cascade == "Q" || cascade == "q") {
          isToExit = true;
          numCascade = Integer.parseInt(cascade);
        } else {
          numCascade = Integer.parseInt(cascade);
        }

        if (open == "Q" || open == "q") {
          isToExit = true;
          numOpen = Integer.parseInt(open);
        } else {
          numOpen = Integer.parseInt(open);
        }


        if (shuffle == "q" || shuffle == "Q") {
          isToExit = true;
          shf = false;
        }
        else {
          if (shuffle == "true") {
            shf = true;
          } else {
            shf = false;
          }
        }

        try {
          if (isToExit) {
            try {
              controller.ap.append("Game quit prematurely.");
              inProgress = false;
            } catch (IOException e5) {}
          }
          else {
            try {
              List<Card> deck = model.getDeck();
              controller.playGame(deck, model, numCascade, numOpen,shf);
              controller.ap.append(model.getGameState());
            } catch (IOException e6) {
            }
          }
        }
        catch (IllegalArgumentException e4){}
      }
      catch (IOException e3) {}
    }
  }
}
