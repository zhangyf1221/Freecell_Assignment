package cs3500.hw04;

/**
 * Created by GentleFan on 10/10/2016.
 */


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
      case OPEN:
        if (openPileList.size() > sourcePileNumber) {
          Pile<Card> po = openPileList.get(sourcePileNumber);
          Card oc = po.getCard(cardIndex);

          if (po.isLastCard(cardIndex)) {
            switch (destType) {
              case OPEN:
                if (openPileList.size() > destPileNumber) {

                } else {
                  throw new IllegalArgumentException("Destination pile index out of bounds");
                }
            }
          } else {
            throw new IllegalArgumentException("No card found. Card index should only be 1");
          }
        } else {
          throw new IllegalArgumentException("Source pile index out of bounds");
        }
    }

  }

}
