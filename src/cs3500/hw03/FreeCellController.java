package cs3500.hw03;

import java.util.List;

import cs3500.hw02.Card;
//import cs3500.hw02.FreeCellModel;
import cs3500.hw02.IFreeCellModel;
import cs3500.hw02.PileType;

import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;



/**
 * Created by jowenfan on 9/29/16.
 */
public class FreeCellController implements IFreeCellController<Card> {
  // Define fields
  private Readable rd;
  private Appendable ap;
  private IFreeCellModel<Card> model;
  private boolean inProgress = true;


  /**
   * Constructor of FreeCellController.
   */
  public FreeCellController(Readable rd, Appendable ap) {
    try {
      Objects.requireNonNull(rd);
      Objects.requireNonNull(ap);
    } catch (NullPointerException e) {
      throw new IllegalStateException("Readable or appendable can't be empty");
    }

    this.rd = rd;
    this.ap = ap;
  }


  /**
   * The method starts a new game with given deck, FreeCellModel, number of cascade pile,
   * number of open pile and decide to shuffle the deck or not.
   *
   * @param deck        list of cards that given to play the game
   * @param model       a freecell game model
   * @param numCascades number of cascade pile
   * @param numOpens    number of open pile
   * @param shuffle     shuffle the given deck or not
   */
  public void playGame(List<Card> deck, IFreeCellModel<Card> model, int numCascades,
                       int numOpens, boolean shuffle) {

    if (deck == null) {
      throw new IllegalArgumentException("Deck can't be null");
    }

    if (model == null) {
      throw new IllegalArgumentException("Model can't be empty");
    }

    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
      this.model = model;
      try {
        ap.append(model.getGameState());
      } catch (IOException e0) {
        System.out.print(e0);
      }
      try {
        run();
      } catch (IllegalArgumentException e1) {
        System.out.print("hahah");
      }

    } catch (IllegalStateException e4) {
      try {
        ap.append("Could not start game.");
        inProgress = false;
      } catch (IOException e5) {
        System.out.print(e5);
      }
    }

  }

  /**
   * Helper method for main. Get input from scanner and output move.
   */
  private void run() {
    Scanner s = new Scanner(rd);
    boolean isToExit = !inProgress;

    if (model.isGameOver()) {
      try {
        inProgress = false;
        ap.append(model.getGameState());
        ap.append("Game over.");
      } catch (IOException e000) {
        System.out.print(e000);
      }
    }

    while (inProgress) {

      try {
        ap.append("\n");
        ap.append("Enter your move: (SourceType + Index, " +
                "CardIndex, DestType + Index)\n");

        String source = s.next();

        int index;
        int si;
        int di;

        PileType sourceType;
        PileType destType;

        String st = source.substring(0, 1);
        String rawSourceIndex;

        if (st.equals("Q") || st.equals("q")) {
          isToExit = true;
          sourceType = PileType.OPEN;
          si = 1;
        } else {
          switch (st) {
            case "O":
              sourceType = PileType.OPEN;
              rawSourceIndex = source.substring(1, source.length());
              try {
                si = Integer.parseInt(rawSourceIndex);
              } catch (NumberFormatException e0) {
                throw new IllegalArgumentException("Invalid source pile index input");
              }
              break;
            case "C":
              sourceType = PileType.CASCADE;
              rawSourceIndex = source.substring(1, source.length());
              try {
                si = Integer.parseInt(rawSourceIndex);
              } catch (NumberFormatException e0) {
                throw new IllegalArgumentException("Invalid source pile index input");
              }
              break;
            case "F":
              sourceType = PileType.FOUNDATION;
              rawSourceIndex = source.substring(1, source.length());
              try {
                si = Integer.parseInt(rawSourceIndex);
              } catch (NumberFormatException e0) {
                throw new IllegalArgumentException("Invalid source pile index input");
              }
              break;
            default:
              throw new IllegalArgumentException("Invalid source pile type");
          }
        }

        if (isToExit) {
          inProgress = false;
          index = 1;
        } else {
          String rawIndex = s.next();

          if (rawIndex.substring(0, 1).equals("Q") || rawIndex.substring(0, 1).equals("q")) {
            isToExit = true;
            index = 1;
          } else {
            try {
              index = Integer.parseInt(rawIndex);
            } catch (NumberFormatException e9) {
              throw new IllegalArgumentException("Invalid card index");
            }
          }
        }

        if (isToExit) {
          inProgress = false;
          destType = PileType.OPEN;
          di = 1;
        } else {

          String dest = s.next();
          String dt = dest.substring(0, 1);
          String rawDestIndex;


          if (dt.equals("Q") || dt.equals("q")) {
            isToExit = true;
            destType = PileType.OPEN;
            di = 1;
          } else {
            switch (dt) {
              case "O":
                destType = PileType.OPEN;
                rawDestIndex = dest.substring(1, dest.length());
                try {
                  di = Integer.parseInt(rawDestIndex);
                } catch (NumberFormatException e0) {
                  throw new IllegalArgumentException("Invalid destination pile index input");
                }
                break;
              case "C":
                destType = PileType.CASCADE;
                rawDestIndex = dest.substring(1, dest.length());
                try {
                  di = Integer.parseInt(rawDestIndex);
                } catch (NumberFormatException e0) {
                  throw new IllegalArgumentException("Invalid destination pile index input");
                }
                break;
              case "F":
                destType = PileType.FOUNDATION;
                rawDestIndex = dest.substring(1, dest.length());
                try {
                  di = Integer.parseInt(rawDestIndex);
                } catch (NumberFormatException e0) {
                  throw new IllegalArgumentException("Invalid destination pile index input");
                }
                break;
              default:
                throw new IllegalArgumentException("Invalid destination pile type");
            }
          }
        }

        if (isToExit) {
          try {
            ap.append("Game quit prematurely.");
            inProgress = false;
          } catch (IOException e4) {
            System.out.print(e4);
          }
        } else {
          try {
            model.move(sourceType, si - 1, index - 1, destType, di - 1);
            ap.append(model.getGameState());
          } catch (IllegalArgumentException ex) {
            ap.append("Invalid move. Try again. Reason: " + ex);
          } catch (IOException ey) {
            System.out.print(ey);
          } catch (IndexOutOfBoundsException ez) {
            ap.append("Invalid move. Try again. Reason: " + ez); //TODO: ask for cardIndex only
          }
        }

      } catch (IOException e3) {
        System.out.print(e3);
      } catch (IllegalArgumentException e10) {
        System.out.print("Invalid move. Try again. Reason: " + e10);
      }
    }

  }

/*
  private void run() {
    Scanner s = new Scanner(rd);
    boolean isToExit = !inProgress;

    if (model.isGameOver()) {
      try {
        inProgress = false;
        ap.append(model.getGameState());
        ap.append("Game over.");
      } catch (IOException e000) {
        System.out.print(e000);
      }
    }

    while (inProgress) {

      try {
        ap.append("\n");
        ap.append("Enter your move: (SourceType + Index, " +
                "CardIndex, DestType + Index)\n");

        String source = s.next();

        int index;
        int si;
        int di;

        PileType sourceType;
        PileType destType;

        String st = source.substring(0, 1);
        String rawSourceIndex;

        if (st.equals("Q") || st.equals("q")) {
          isToExit = true;
          sourceType = PileType.OPEN;
          si = 1;
        } else {
          switch (st) {
            case "O":
              sourceType = PileType.OPEN;
              rawSourceIndex = source.substring(1, source.length());
              try {
                si = Integer.parseInt(rawSourceIndex);
              } catch (NumberFormatException e0) {
                throw new IllegalArgumentException("Invalid source pile index input");
              }
              break;
            case "C":
              sourceType = PileType.CASCADE;
              rawSourceIndex = source.substring(1, source.length());
              try {
                si = Integer.parseInt(rawSourceIndex);
              } catch (NumberFormatException e0) {
                throw new IllegalArgumentException("Invalid source pile index input");
              }
              break;
            case "F":
              sourceType = PileType.FOUNDATION;
              rawSourceIndex = source.substring(1, source.length());
              try {
                si = Integer.parseInt(rawSourceIndex);
              } catch (NumberFormatException e0) {
                throw new IllegalArgumentException("Invalid source pile index input");
              }
              break;
            default:
              throw new IllegalArgumentException("Invalid source pile type");
          }
        }

        if (isToExit) {
          inProgress = false;
          index = 1;
        } else {
          String rawIndex = s.next();

          if (rawIndex.substring(0, 1).equals("Q") || rawIndex.substring(0, 1).equals("q")) {
            isToExit = true;
            index = 1;
          } else {
            try {
              index = Integer.parseInt(rawIndex);
            } catch (NumberFormatException e9) {
              throw new IllegalArgumentException("Invalid card index");
            }
          }
        }

        if (isToExit) {
          inProgress = false;
          destType = PileType.OPEN;
          di = 1;
        } else {

          String dest = s.next();
          String dt = dest.substring(0, 1);
          String rawDestIndex;


          if (dt.equals("Q") || dt.equals("q")) {
            isToExit = true;
            destType = PileType.OPEN;
            di = 1;
          } else {
            switch (dt) {
              case "O":
                destType = PileType.OPEN;
                rawDestIndex = dest.substring(1, dest.length());
                try {
                  di = Integer.parseInt(rawDestIndex);
                } catch (NumberFormatException e0) {
                  throw new IllegalArgumentException("Invalid destination pile index input");
                }
                break;
              case "C":
                destType = PileType.CASCADE;
                rawDestIndex = dest.substring(1, dest.length());
                try {
                  di = Integer.parseInt(rawDestIndex);
                } catch (NumberFormatException e0) {
                  throw new IllegalArgumentException("Invalid destination pile index input");
                }
                break;
              case "F":
                destType = PileType.FOUNDATION;
                rawDestIndex = dest.substring(1, dest.length());
                try {
                  di = Integer.parseInt(rawDestIndex);
                } catch (NumberFormatException e0) {
                  throw new IllegalArgumentException("Invalid destination pile index input");
                }
                break;
              default:
                throw new IllegalArgumentException("Invalid destination pile type");
            }
          }
        }

        if (isToExit) {
          try {
            ap.append("Game quit prematurely.");
            inProgress = false;
          } catch (IOException e4) {
            System.out.print(e4);
          }
        } else {
          try {
            model.move(sourceType, si - 1, index - 1, destType, di - 1);
            ap.append(model.getGameState());
          } catch (IllegalArgumentException ex) {
            ap.append("Invalid move. Try again. Reason: " + ex);
          } catch (IOException ey) {
            System.out.print(ey);
          } catch (IndexOutOfBoundsException ez) {
            ap.append("Invalid move. Try again. Reason: " + ez); //TODO: ask for cardIndex only
          }
        }

      } catch (IOException e3) {
        System.out.print(e3);
      } catch (IllegalArgumentException e10) {
        System.out.print("Invalid move. Try again. Reason: " + e10);
      }
    }

  }

  *///////////////////////////////////////////////////////



/*  *//**
   * Main method of FreeCell Controller.
   *
   * @param args list of arguments
   * @throws IOException IOException
   *//*
  public static void main(String[] args) throws IOException {
    FreeCellController controller =
            new FreeCellController(new InputStreamReader(System.in), System.out);
    FreeCellModel model = new FreeCellModel();
    Scanner s = new Scanner(controller.rd);

    if (controller.rd == null || controller.ap == null) {
      throw new IllegalStateException("Readable or appendable can't be null");
    }

    if (model == null) {
      throw new IllegalArgumentException("Model can't be empty");
    }

    boolean isToExit = false;


    while (controller.inProgress) {
      try {
        controller.ap.append("Enter number of cascade pile, number of open pile " +
                "and shuffle or not");

        String cascade = s.next();
        int numCascade;

        switch (cascade) {
          case "Q":
            isToExit = true;
            numCascade = 4;
            break;
          case "q":
            isToExit = true;
            numCascade = 4;
            break;
          default:
            numCascade = Integer.parseInt(cascade);
        }


        int numOpen;

        if (isToExit) {
          numOpen = 4;
          controller.inProgress = false;
        } else {
          String open = s.next();

          switch (open) {
            case "Q":
              isToExit = true;
              numOpen = 4;
              break;
            case "q":
              isToExit = true;
              numOpen = 4;
              break;
            default:
              numOpen = Integer.parseInt(open);
          }

        }

        boolean shf;
        if (isToExit) {
          shf = false;
          controller.inProgress = false;
        } else {
          String shuffle = s.next();


          switch (shuffle) {
            case "Q":
              shf = false;
              isToExit = true;
              break;
            case "q":
              shf = false;
              isToExit = true;
              break;
            case "true":
              shf = true;
              break;
            case "false":
              shf = false;
              break;
            default:
              throw new IllegalArgumentException("Invalid input");
          }

        }

        if (isToExit) {
          try {
            controller.ap.append("Game quit prematurely.");
            controller.inProgress = false;
          } catch (IOException e5) {
            System.out.print(e5);
          }
        } else {
          try {
            List<Card> deck = model.getDeck();
            controller.playGame(deck, model, numCascade, numOpen, shf);
          } catch (IllegalArgumentException e10) {
            System.out.print(e10);
          } catch (NullPointerException e6) {
            System.out.print(e6);
          }
        }
      } catch (IllegalArgumentException e7) {
        controller.ap.append("Could not start game.");
        controller.inProgress = false;
      } //catch (NullPointerException e6) {
        //controller.ap.append("Could not start game.");
        //controller.inProgress = false;
      //}
      catch (IOException e0) {
        System.out.print(e0);
      }
    }
  }*/
}
