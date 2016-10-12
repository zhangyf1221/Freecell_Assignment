package cs3500.hw04;

import org.junit.Test;

import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreeCellModel;
import cs3500.hw02.Pile;
import cs3500.hw02.Rank;
import cs3500.hw02.Suit;
import static org.junit.Assert.assertEquals;

/**
 * Created by jowenfan on 10/12/16.
 */
public class FreeCellMultimoveModelTest {
  @Test
  public void moveTest() throws Exception {

  }

  @Test
  public void isValidFormTest() throws Exception {
    initValidFormPile();
    assertEquals(true, multimoveModel.isValidForm(validFormPile.pile, 12));//single card
    //assertEquals(true, multimoveModel.isValidForm(validFormPile.pile, 0));
    initinvalidFormPileOrder();
    assertEquals(false, multimoveModel.isValidForm(invalidFormPile.pile, 0));
  }

  private Pile validFormPile = new Pile();
  private Pile invalidFormPile = new Pile();
  private FreeCellMultimoveModel multimoveModel = new FreeCellMultimoveModel();

  private void initValidFormPile() {
    validFormPile.add(new Card(Rank.King, Suit.Heart));
    validFormPile.add(new Card(Rank.Queen, Suit.Spade));
    validFormPile.add(new Card(Rank.Jack, Suit.Heart));
    validFormPile.add(new Card(Rank.Ten, Suit.Spade));
    validFormPile.add(new Card(Rank.Nine, Suit.Club));
    validFormPile.add(new Card(Rank.Eight, Suit.Heart));
    validFormPile.add(new Card(Rank.Seven, Suit.Club));
    validFormPile.add(new Card(Rank.Six, Suit.Heart));
    validFormPile.add(new Card(Rank.Five, Suit.Spade));
    validFormPile.add(new Card(Rank.Four, Suit.Heart));
    validFormPile.add(new Card(Rank.Three, Suit.Spade));
    validFormPile.add(new Card(Rank.Two, Suit.Heart));
    validFormPile.add(new Card(Rank.Ace, Suit.Spade));
  }

  private void initinvalidFormPileOrder() {
    invalidFormPile.add(new Card(Rank.Three, Suit.Spade));
    invalidFormPile.add(new Card(Rank.Four, Suit.Heart));
  }

  private void initinvalidFormPileColor() {
    invalidFormPile.add(new Card(Rank.Three, Suit.Spade));
    invalidFormPile.add(new Card(Rank.Two, Suit.Spade));
  }
}

