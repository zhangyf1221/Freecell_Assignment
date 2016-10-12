package cs3500.hw04;

/**
 * Created by GentleFan on 10/10/2016.
 */


import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreeCellModel;
import cs3500.hw02.Pile;
import cs3500.hw02.PileType;

/**
 * A FreeCell game model with multimove option
 */
public class FreeCellMultimoveModel extends FreeCellModel {

  public FreeCellMultimoveModel() {
    super();
  }

  @Override
  public void move(PileType sourceType, int sourcePileNumber, int cardIndex, PileType destType,
                   int destPileNumber) {
    //TODO: move method for multimove model

    switch (sourceType) {

      case OPEN://source type
        Pile po = openPileList.get(sourcePileNumber);
        Card oc;

        if (!po.isEmpty()) {
          oc = po.getCard(cardIndex);
        } else {
          throw new IllegalArgumentException("Source pile is empty");
        }

        if (openPileList.size() > sourcePileNumber) {
          if (po.isLastCard(cardIndex)) {
            switch (destType) {

              case OPEN://dest type
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

              case CASCADE://dest type
                Pile pc = cascadePileList.get(destPileNumber);

                if (cascadePileList.size() > destPileNumber) {
                  if (pc.isEmpty()) {
                    pc.add(oc);
                    po.remove(oc);
                    break;
                  } else {
                    Card cc = pc.lastCard();
                    if (oc.cardValue() == cc.cardValue() - 1) {//descend order?? 1 value small or just smaller
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

              case FOUNDATION://dest type
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
            }//close bracket for switch dest type
          } else {
            throw new IllegalArgumentException("No card found. Card index should only be 1");
          }
        } else {
          throw new IllegalArgumentException("Source pile index out of bounds");
        }

      case CASCADE:
        Pile pc = cascadePileList.get(sourcePileNumber);//nullPointerException or indexOutOfBounds

        if (pc.isEmpty()) throw new IllegalArgumentException("Source pile is empty");

        if (cascadePileList.size() > sourcePileNumber) {
          switch (destType) {
            case OPEN:
              if (openPileList.size() > destPileNumber) {
                if (pc.isLastCard(cardIndex)) {
                  if (openPileList.get(destPileNumber).isEmpty()) {
                    openPileList.get(destPileNumber).add(pc.getCard(cardIndex));
                    pc.remove(pc.getCard(cardIndex));
                    break;
                  } else {
                    throw new IllegalArgumentException("Open pile can only keeps 1 card");
                  }
                } else {
                  throw new IllegalArgumentException("Open pile can only keeps 1 card");
                }
              } else {
                throw new IllegalArgumentException("Destination pile number out of bounds");
              }

            case CASCADE:
              //TODO: multimove check number of empty free open piles
              if (isValidForm(pc.pile, cardIndex)
                      && cascadePileList.get(destPileNumber).lastCard().cardValue() ==
                      pc.get(cardIndex).cardValue() - 1) {
                for (int i = cardIndex; i < pc.size(); i++) {
                  cascadePileList.get(destPileNumber).add(pc.get(i));
                }
                int num = pc.size() - cardIndex + 1;
                for (int j = 0; j < num; j++) {
                  pc.removeLast();
                }
                break;
              } else {
                throw new IllegalArgumentException("Invalid move. Not a valid form.");
              }

            case FOUNDATION:
              if (foundationPileList.size() > destPileNumber) {
                Pile fp = foundationPileList.get(destPileNumber);

                if (fp.isEmpty()) {
                  if (pc.get(cardIndex).cardValue() == 1) {
                    fp.add(pc.get(cardIndex));
                    pc.remove(pc.get(cardIndex));
                    break;
                  } else {
                    throw new IllegalArgumentException("Only Ace can be placed " +
                            "into a empty foundation pile");
                  }
                } else {
                  if (fp.lastCard().nextCard(pc.get(cardIndex))) {
                    fp.add(pc.get(cardIndex));
                    pc.remove(pc.get(cardIndex));
                    break;
                  } else {
                    throw new IllegalArgumentException("Cards should be consecutive");
                  }
                }
              } else {
                throw new IllegalArgumentException("Destination pile number out of bounds");
              }

            default:
              throw new IllegalArgumentException("Invalid move");
          }//close bracket for switch dest type
        } else {
          throw new IllegalArgumentException("Source pile index out of bounds");
        }

      case FOUNDATION:
        throw new IllegalArgumentException("Cannot move a card out from foundation pile");

      default:
        throw new IllegalArgumentException("Invalid move");

    }//close bracket for switch source type
  }

  public boolean isValidForm(List<Card> pile, int index) {
    if (pile.size() <= index) throw new IllegalArgumentException("Invalid card index");
    if (index == pile.size() - 1) return true;
    for (int i = index + 1; i < pile.size(); i++) {
      if (!(pile.get(i - 1).cardValue() == pile.get(i).cardValue() - 1)
              && !(pile.get(i - 1).cardColor() == pile.get(i).cardColor())) {
        return false;
      }
    }
    return true;
  }
}
