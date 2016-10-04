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
    model.startGame(deck, numCascades, numOpens, shuffle);
    this.model = model;
    try {
      ap.append(model.getGameState());
    } catch (IOException e0) {
      System.out.print(e0);
    }
    run();
  }

  private void run() {
    Scanner s = new Scanner(rd);
    boolean inProgress = true;
    while (inProgress) {
      while (!model.isGameOver()) {
        try {
          ap.append("\n");
          ap.append("Enter your move: (SourceType + Index, " +
                  "CardIndex, DestType + Index)\n");


          String source = s.next();
          int index = s.nextInt();
          String dest = s.next();
          int si;
          int di;

          PileType sourceType;
          PileType destType;

          boolean isToExit = false;

          if (source.length() == 2) {
            char st = source.charAt(0);
            char rawSourceIndex = source.charAt(1);
            si = Character.getNumericValue(rawSourceIndex);

            switch (st) {
              case 'O':
                sourceType = PileType.OPEN;
                break;
              case 'C':
                sourceType = PileType.CASCADE;
                break;
              case 'F':
                sourceType = PileType.FOUNDATION;
                break;
              default:
                throw new IllegalArgumentException("Invalid source pile type");
            }
          } else {
            if (source == "Q" || source == "q") {
              isToExit = true;
              sourceType = PileType.CASCADE;
              si = 1;
            } else {
              throw new InputMismatchException();
            }
          }

          if (dest.length() == 2) {
            char dt = dest.charAt(0);
            char rawDestIndex = dest.charAt(1);
            di = Character.getNumericValue(rawDestIndex);

            switch (dt) {
              case 'O':
                destType = PileType.OPEN;
                break;
              case 'C':
                destType = PileType.CASCADE;
                break;
              case 'F':
                destType = PileType.FOUNDATION;
                break;
              default:
                throw new IllegalArgumentException("Invalid destination pile type");
            }
          } else {
            if (dest == "Q" || dest == "q") {
              isToExit = true;
              destType = PileType.CASCADE;
              di = 1;
            } else {
              throw new InputMismatchException();
            }
          }

          if (isToExit) {
            try {
              ap.append("Game quit prematurely.");
              inProgress = false;
            } catch (IOException e4) {
            }
          } else {
            try {
              model.move(sourceType, si - 1, index - 1, destType, di - 1);
              ap.append(model.getGameState());
            } catch (IOException e5) {
            }
          }
        } catch (IOException e3) {
        }
      }
    }
  }

  public static void main(String[] args) {
    FreeCellController controller =
            new FreeCellController(new InputStreamReader(System.in), System.out);
    FreeCellModel model = new FreeCellModel();
    Scanner s = new Scanner(controller.rd);

    boolean inProgress = true;
    boolean callExit1;
    boolean callExit2;
    boolean callExit3;
    boolean isToExit;


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
          callExit1 = true;
          numCascade = 1;
        } else {
          numCascade = Integer.parseInt(cascade);
          callExit1 = false;
        }

        if (open == "Q" || open == "q") {
          callExit2 = true;
          numOpen = 1;
        } else {
          numOpen = Integer.parseInt(open);
          callExit2 = false;
        }

        switch (shuffle) {
          case "Q":
            callExit3 = true;
            inProgress = false;
            shf = false;
            break;
          case "q":
            callExit3 = true;
            inProgress = false;
            shf = false;
            break;
          case "true" :
            shf = true;
            callExit3 = false;
            break;
          case "false" :
            shf = false;
            callExit3 = false;
            break;
          default:
            throw new InputMismatchException("Invalid input!!");
        }

        isToExit = (callExit1 || callExit2 || callExit3);

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
              controller.playGame(deck, model, numCascade, numOpen, shf);
              controller.ap.append(model.getGameState());
            } catch (IOException e6) {
              controller.ap.append("Could not start game.");
            }
          }
        }
        catch (InputMismatchException e4) {
          controller.ap.append("Invalid input!\n");
        }
      }
      catch (IOException e3) {}
    }
  }
}
