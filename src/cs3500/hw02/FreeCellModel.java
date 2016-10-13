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
  public List<Card> deck;
  public List<Pile> cascadePileList;
  public List<Pile> foundationPileList;
  public List<Pile> openPileList;
  public boolean inProgress;

  /**
   * Constructor.
   */
  public FreeCellModel() {
    foundationPileList = new ArrayList<Pile>();
    openPileList = new ArrayList<Pile>();
    cascadePileList = new ArrayList<Pile>();
    inProgress = true;
    deck = getDeck();
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
    Rank[] ranks = Rank.values();
    Suit[] suits = Suit.values();
    for (int i = 0; i < 13; i++) {
      for (int j = 0; j < 4; j++) {
        result.add(new Card(ranks[i], suits[j]));
      }
    }
    return result;
  }


  /*
  Made change here. Initialize foundation pile at start game. Clear foundation pile, open pile and
  cascade pile before start a new game.
   */
  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {

    foundationPileList.clear();
    openPileList.clear();
    cascadePileList.clear();

    if (shuffle) {
      Collections.shuffle(deck);
    }

    if (numCascadePiles < 4 || numOpenPiles < 1) {
      throw new IllegalStateException("Not a valid game");
    }

    if (this.validDeck(deck)) {
      if (deck.isEmpty()) {
        throw new IllegalArgumentException("Deck is empty");
      }

      // Set up foundation pile list
      for (int i = 0; i < 4; i++) {
        foundationPileList.add(new Pile());
      }


      // Set up cascade pile list
      for (int i = 0; i < numCascadePiles; i++) {
        cascadePileList.add(new Pile());
      }

      // Set up open pile list
      for (int j = 0; j < numOpenPiles; j++) {
        openPileList.add(new Pile());
      }

      // Place card in pile
      for (int i = 0; i < deck.size(); i++) {
        int whichPile = i % numCascadePiles;
        cascadePileList.get(whichPile).add(deck.get(i));
      }
    } else {
      throw new IllegalArgumentException("Not a valid deck");
    }
  }

  /**
   * Check the given deck is valid or not.
   * EDIT: Change method from public to private
   *
   * @param deck list of cards
   * @return true is the deck is valid, false if not
   */
  private boolean validDeck(List<Card> deck) {
    Set<Card> deckSet = new HashSet<Card>(deck);
    return (deckSet.size() == deck.size() && deck.size() == 52);

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
        Pile po = openPileList.get(sourcePileNumber);
        Card oc;

        if (!po.isEmpty()) {
          oc = po.getCard(cardIndex);
        } else {
          throw new IllegalArgumentException("Invalid card index");
        }

        if (openPileList.size() > sourcePileNumber) {
          if (po.isLastCard(cardIndex)) {
            switch (destType) {

              case OPEN:
                if (openPileList.size() > destPileNumber) {
                  if (openPileList.get(destPileNumber).isEmpty()) {
                    openPileList.get(destPileNumber).add(oc);
                    po.remove(oc);
                    break;
                  } else {
                    throw new IllegalArgumentException("Cannot move a card to a not " +
                            "empty open pile");
                  }
                } else {
                  throw new IllegalArgumentException("Destination pile index out of bounds");
                }

              case CASCADE:
                Pile pc = cascadePileList.get(destPileNumber);

                if (cascadePileList.size() > destPileNumber) {
                  if (pc.isEmpty()) {
                    pc.add(oc);
                    po.remove(oc);
                    break;
                  } else {
                    Card cc = pc.lastCard();
                    if (oc.cardValue() == cc.cardValue() - 1) {
                      pc.add(oc);
                      po.remove(oc);
                      break;
                    } else {
                      throw new IllegalArgumentException("Has to be descend order");
                    }
                  }
                } else {
                  throw new IllegalArgumentException("Destination pile index out of bounds");
                }

              case FOUNDATION:
                Pile pf = foundationPileList.get(destPileNumber);
                if (foundationPileList.size() > destPileNumber) {
                  if (pf.isEmpty()) {
                    if (oc.cardValue() == 1) {
                      pf.add(oc);
                      po.remove(oc);
                      break;
                    } else {
                      throw new IllegalArgumentException("Only Ace can be placed " +
                              "into a empty foundation pile");
                    }
                  } else {
                    Card fc = pf.lastCard();
                    if (fc.nextCard(oc)) {
                      pf.add(oc);
                      po.remove(oc);
                      break;
                    } else {
                      throw new IllegalArgumentException("The card you move to foundation pile " +
                              "should in descend order.");
                    }
                  }
                } else {
                  throw new IllegalArgumentException("Destination pile index out of bounds");
                }

              default:
                throw new IllegalArgumentException("Invalid move");
            }
          } else {
            throw new IllegalArgumentException("No card found. Card index should only be 1");
          }
        } else {
          throw new IllegalArgumentException("Source pile index out of bounds");
        }
        break;


      case CASCADE:
        if (cascadePileList.size() > sourcePileNumber) {
          Pile casp = cascadePileList.get(sourcePileNumber);
          Card casc = casp.getCard(cardIndex);
          if (casp.isLastCard(cardIndex)) {
            switch (destType) {
              case OPEN:
                if (openPileList.size() > destPileNumber) {
                  if (openPileList.get(destPileNumber).isEmpty()) {
                    openPileList.get(destPileNumber).add(casc);
                    casp.remove(casc);
                    break;
                  } else {
                    throw new IllegalArgumentException("Open pile can only keeps 1 card");
                  }
                } else {
                  throw new IllegalArgumentException("Destination pile number out of bounds");
                }


              case FOUNDATION:
                if (foundationPileList.size() > destPileNumber) {
                  Pile fp = foundationPileList.get(destPileNumber);

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
                      throw new IllegalArgumentException("Cards should be consecutive");
                    }
                  }
                } else {
                  throw new IllegalArgumentException("Destination pile number out of bounds");
                }

              case CASCADE:
                if (cascadePileList.size() > destPileNumber) {
                  Pile cp = cascadePileList.get(destPileNumber);
                  Card cc = cp.lastCard();
                  if (casc.cardValue() == cc.cardValue() - 1) {
                    cp.add(casc);
                    casp.remove(casc);
                    break;
                  } else {
                    throw new IllegalArgumentException("The card you move to the " +
                            "pile is not in correct order");
                  }
                } else {
                  throw new IllegalArgumentException("Destination pile number out of bounds");
                }

              default:
                throw new IllegalArgumentException("Invalid move7");
            }
          } else {
            throw new IllegalArgumentException("The card you chose is not the last card");
          }
          break;
        } else {
          throw new IllegalArgumentException("Source pile number out of bound");
        }

      case FOUNDATION:
        throw new IllegalArgumentException("Cannot move a card out from foundation pile");

      default:
        throw new IllegalArgumentException("Invalid move9");
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

    if (cascadePileList.size() < 4 || openPileList.size() < 1 || !validDeck(deck)) {
      return "";
    }

    for (int i = 1; i < foundationPileList.size() + 1; i++) {
      result += String.format("F%d:", i);
      if (foundationPileList.get(i - 1).isEmpty()) {
        result += "\n";
      } else {
        for (int j = 0; j < foundationPileList.get(i - 1).size(); j++) {
          Pile p = foundationPileList.get(i - 1);
          Card c = p.get(j);
          if (j == p.size() - 1) {
            result += " " + c.toString() + "\n";
          } else {
            result += " " + c.toString() + ",";
          }
        }
      }
    }

    for (int i = 1; i < openPileList.size() + 1; i++) {
      result += String.format("O%d:", i);
      if (openPileList.get(i - 1).isEmpty()) {
        result += "\n";
      } else {
        for (int j = 0; j < openPileList.get(i - 1).size(); j++) {
          Pile p = openPileList.get(i - 1);
          Card c = p.get(j);
          if (j == p.size() - 1) {
            result += " " + c.toString() + "\n";
          } else {
            result += " " + c.toString() + ",";
          }
        }
      }
    }

    for (int i = 1; i < cascadePileList.size() + 1; i++) {
      result += String.format("C%d:", i);

      for (int j = 0; j < cascadePileList.get(i - 1).size(); j++) {
        Pile p = cascadePileList.get(i - 1);
        Card c = p.get(j);

        if (i == cascadePileList.size()) {
          if (j == p.size() - 1) {
            result += " " + c.toString();
          } else {
            result += " " + c.toString() + ",";
          }
        } else {
          if (j == p.size() - 1) {
            result += " " + c.toString() + "\n";
          } else {
            result += " " + c.toString() + ",";
          }
        }
      }
    }

    return result;
  }

  /**
   * EDIT: New method added to FreeCellModel class.
   * Check the pile is valid form or not.
   * @param pile a pile which is formed by a list of card
   * @param index index of card player wants to move
   * @return true if the pile is valid form, false if not
   */
  public boolean isValidForm(List<Card> pile, int index) {
    return false;
  }

  /**
   * EDIT: New method added.
   * Get the number of empty open pile.
   * @return integer number of empty pile
   */
  public int numOfEmptyOpen() {
    int result = 0;
    for (int i = 0; i  < openPileList.size(); i++) {
      if (openPileList.get(i).isEmpty()) {
        result = result + 1;
      }
    }
    return result;
  }

}
