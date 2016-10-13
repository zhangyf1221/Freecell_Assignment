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
    assertEquals(true, multimoveModel.isValidForm(validFormPile.pile, 0));
    initinvalidFormPileOrder();
    assertEquals(true, multimoveModel.isValidForm(invalidOrderFormPile.pile, 1));//single card
    assertEquals(false, multimoveModel.isValidForm(invalidOrderFormPile.pile, 0));
    initinvalidFormPileColor();
    assertEquals(false, multimoveModel.isValidForm(invalidColorFormPile.pile, 0));
  }

  private Pile validFormPile = new Pile();
  private Pile invalidOrderFormPile = new Pile();
  private Pile invalidColorFormPile = new Pile();
  private FreeCellMultimoveModel multimoveModel = new FreeCellMultimoveModel();

  private void initValidFormPile() {
    validFormPile.add(new Card(Rank.King, Suit.Heart));
    validFormPile.add(new Card(Rank.Queen, Suit.Spade));
    validFormPile.add(new Card(Rank.Jack, Suit.Heart));
    validFormPile.add(new Card(Rank.Ten, Suit.Spade));
    validFormPile.add(new Card(Rank.Nine, Suit.Heart));
    validFormPile.add(new Card(Rank.Eight, Suit.Spade));
    validFormPile.add(new Card(Rank.Seven, Suit.Heart));
    validFormPile.add(new Card(Rank.Six, Suit.Spade));
    validFormPile.add(new Card(Rank.Five, Suit.Heart));
    validFormPile.add(new Card(Rank.Four, Suit.Spade));
    validFormPile.add(new Card(Rank.Three, Suit.Heart));
    validFormPile.add(new Card(Rank.Two, Suit.Spade));
    validFormPile.add(new Card(Rank.Ace, Suit.Heart));
  }

  private void initinvalidFormPileOrder() {
    invalidOrderFormPile.add(new Card(Rank.Three, Suit.Spade));
    invalidOrderFormPile.add(new Card(Rank.Six, Suit.Heart));
  }

  private void initinvalidFormPileColor() {
    invalidColorFormPile.add(new Card(Rank.Three, Suit.Spade));
    invalidColorFormPile.add(new Card(Rank.Two, Suit.Spade));
  }
}

