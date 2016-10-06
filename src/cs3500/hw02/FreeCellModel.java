package cs3500.hw02;

/**
 * Created by jowenfan on 9/24/16.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;


/**
 * This is the class of the Freecell model. It is parameterized over the
 * card type.
 */
public class FreeCellModel implements IFreeCellModel<Card> {

  // Define fields
  protected List<Card> deck;
  protected List<Pile> cascadePileList;
  protected List<Pile> foundationPileList;
  protected List<Pile> openPileList;

  /**
   * Constructor.
   */
  public FreeCellModel() {
    foundationPileList = new ArrayList<Pile>();
    for (int i = 0; i < 4; i++) {
      foundationPileList.add(new Pile());
    }
  }

  /**
   * Return a valid and complete deck of cards for a game of Freecell. There is
   * no restriction imposed on the ordering of these cards in the deck.
   *
   * @return the deck of cards as a list
   */

  @Override
  public List<Card> getDeck() {
    List<Card> result = new ArrayList<>();
    Rank ranks[] = Rank.values();
    Suit suits[] = Suit.values();
    for (int i = 0; i < 13; i++) {
      for (int j = 0; j < 4; j++) {
        result.add(new Card(ranks[i], suits[j]));
      }
    }
    return result;
  }


  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {

    if (shuffle) {
      Collections.shuffle(deck);
    }

    if (this.validDeck(deck) && numCascadePiles >= 4 && numOpenPiles >= 1) {
      // Set up cascade pile list
      cascadePileList = new ArrayList<Pile>();
      for (int i = 0; i < numCascadePiles; i++) {
        cascadePileList.add(new Pile());
      }
      // Set up open pile list
      openPileList = new ArrayList<Pile>();
      for (int j = 0; j < numOpenPiles; j++) {
        openPileList.add(new Pile());
      }

      // Place card in pile
      for (int i = 0; i < deck.size(); i++) {
        int whichPile = i % numCascadePiles;
        cascadePileList.get(whichPile).add(deck.get(i));
      }
    } else {
      throw new IllegalArgumentException("Not a valid game");
    }
  }

  /**
   * Check the given deck is valid or not.
   *
   * @param deck list of cards
   * @return true is the deck is valid, false if not
   */
  public boolean validDeck(List<Card> deck) {
    if (deck.size() != 52) {
      return false;
    } else {
      Set<Card> deckSet = new HashSet<>(deck);

      return deckSet.size() == 52;
    }
  }

  /**
   * Move a card from the given source pile to the given destination pile, if
   * the move is valid.
   *
   * @param sourceType       the type of the source pile (see {@link PileType})
   * @param sourcePileNumber the pile number of the given type, starting at 0
   * @param cardIndex        the index of the card to be moved from the source pile, starting at 0
   * @param destType         the type of the destination pile (see {@link PileType})
   * @param destPileNumber   the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the move is not possible
   */
  public void move(PileType sourceType, int sourcePileNumber, int cardIndex, PileType destType,
                   int destPileNumber) {
    switch (sourceType) {
      case OPEN:
        Pile<Card> po = openPileList.get(sourcePileNumber);
        Card oc = po.getCard(cardIndex);

        if (po.isLastCard(cardIndex)) {
          switch (destType) {
            case OPEN:
              if (po.isEmpty()) {
                openPileList.get(destPileNumber).add(oc);
                po.remove(oc);
                break;
              } else {
                throw new IllegalArgumentException("Cannot move a card to a not empty open pile");
              }


            case FOUNDATION:
              Pile<Card> p2 = foundationPileList.get(destPileNumber);
              if (p2.isEmpty()) {
                if (oc.cardValue() == 1) {
                  p2.add(oc);
                  po.remove(oc);
                  break;
                } else {
                  throw new IllegalArgumentException("Only Ace can be placed " +
                          "into a empty foundation pile");
                }
              } else {
                if (p2.lastCard().nextCard(oc)) {
                  p2.add(oc);
                  po.remove(oc);
                  break;
                } else {
                  throw new IllegalArgumentException("Invalid move");
                }
              }

            case CASCADE:
              Pile<Card> pc = cascadePileList.get(destPileNumber);
              Card cc = pc.lastCard();
              Pile<Card> p3 = openPileList.get(sourcePileNumber);
              Card c3 = p3.getCard(cardIndex);

              if (c3.cardValue() < cc.cardValue()) {
                pc.add(c3);
                p3.remove(c3);
                break;
              } else {
                throw new IllegalArgumentException("Invalid move");
              }

            default:
              throw new IllegalArgumentException("Invalid move");
          }
        } else {
          throw new IllegalArgumentException("Invalid move");
        }
        break;

      case CASCADE:
        Pile<Card> casp = cascadePileList.get(sourcePileNumber);
        Card casc = casp.getCard(cardIndex);

        if (casp.isLastCard(cardIndex)) {
          switch (destType) {
            case OPEN:
              if (openPileList.get(destPileNumber).isEmpty()) {
                openPileList.get(destPileNumber).add(casc);
                casp.remove(casc);
                break;
              } else {
                throw new IllegalArgumentException("Open pile can only keep 1 card");
              }


            case FOUNDATION:
              Pile<Card> fp = foundationPileList.get(destPileNumber);

              if (fp.isEmpty()) {
                if (casc.cardValue() == 1) {
                  fp.add(casc);
                  casp.remove(casc);
                  break;
                } else {
                  throw new IllegalArgumentException("Only Ace can be placed " +
                          "into a empty foundation pile");
                }
              } else {
                if (fp.lastCard().nextCard(casc)) {
                  fp.add(casc);
                  casp.remove(casc);
                  break;
                } else {
                  throw new IllegalArgumentException("Invalid move");
                }
              }

            case CASCADE:
              Pile<Card> cp = cascadePileList.get(destPileNumber);
              Card cc = cp.lastCard();
              if (casc.cardValue() < cc.cardValue()) {
                cp.add(casc);
                casp.remove(casc);
                break;
              } else {
                throw new IllegalArgumentException("Invalid move");
              }

            default:
              throw new IllegalArgumentException("Invalid move");
          }
        } else {
          throw new IllegalArgumentException("Invalid move");
        }
        break;

      case FOUNDATION:
        throw new IllegalArgumentException("Cannot move a card out from foundation pile");

      default:
        throw new IllegalArgumentException("Invalid move");
    }
  }

  /**
   * Signal if the game is over or not.
   *
   * @return true if game is over, false otherwise
   */
  public boolean isGameOver() {
    return (foundationPileList.get(0).size() == 13
            && foundationPileList.get(1).size() == 13
            && foundationPileList.get(2).size() == 13
            && foundationPileList.get(3).size() == 13);
  }

  /**
   * Return the present state of the game as a string. The string is formatted
   * as follows:
   *
   * <pre>
   * F1:[b]f11,[b]f12,[b],...,[b]f1n1[n] (Cards in foundation pile 1 in order)
   * F2:[b]f21,[b]f22,[b],...,[b]f2n2[n] (Cards in foundation pile 2 in order)
   * ...
   * Fm:[b]fm1,[b]fm2,[b],...,[b]fmnm[n] (Cards in foundation pile m in order)
   * O1:[b]o11[n]                        (Cards in open pile 1)
   * O2:[b]o21[n]                        (Cards in open pile 2)
   * ...
   * Ok:[b]ok1[n]                        (Cards in open pile k)
   * C1:[b]c11,[b]c12,[b]...,[b]c1p1[n]  (Cards in cascade pile 1 in order)
   * C2:[b]c21,[b]c22,[b]...,[b]c2p2[n]  (Cards in cascade pile 2 in order)
   * ...
   * Cs:[b]cs1,[b]cs2,[b]...,[b]csps     (Cards in cascade pile s in order)
   * </pre>
   * where [b] is a single space, [n] is newline. Note that there is no
   * newline on the last line.  If a pile has no cards in it, there should be no
   * space character after the colon and before the newline.
   */
  public String getGameState() {
    String result = "";
    for (int i = 1; i < foundationPileList.size() + 1; i++) {
      result += String.format("F%d: ", i);
      if (foundationPileList.get(i - 1).isEmpty()) {
        result += "\n";
      } else {
        for (int j = 0; j < foundationPileList.get(i - 1).size(); j++) {
          Pile<Card> p = foundationPileList.get(i - 1);
          Card c = p.get(j);
          if (j == p.size() - 1) {
            result += c.printCard() + "\n";
          } else {
            result += c.printCard() + ", ";
          }
        }
      }
    }

    for (int i = 1; i < openPileList.size() + 1; i++) {
      result += String.format("O%d: ", i);
      if (openPileList.get(i - 1).isEmpty()) {
        result += "\n";
      } else {
        for (int j = 0; j < openPileList.get(i - 1).size(); j++) {
          Pile<Card> p = openPileList.get(i - 1);
          Card c = p.get(j);
          if (j == p.size() - 1) {
            result += c.printCard() + "\n";
          } else {
            result += c.printCard() + ", ";
          }
        }
      }
    }

    for (int i = 1; i < cascadePileList.size() + 1; i++) {
      result += String.format("C%d: ", i);

      for (int j = 0; j < cascadePileList.get(i - 1).size(); j++) {
        Pile<Card> p = cascadePileList.get(i - 1);
        Card c = p.get(j);

        if (i == cascadePileList.size()) {
          if (j == p.size() - 1) {
            result += c.printCard();
          } else {
            result += c.printCard() + ", ";
          }
        } else {
          if (j == p.size() - 1) {
            result += c.printCard() + "\n";
          } else {
            result += c.printCard() + ", ";
          }
        }
      }
    }

    return result;
  }
}
