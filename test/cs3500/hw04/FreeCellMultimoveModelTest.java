package cs3500.hw04;

import org.junit.Test;

import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreeCellModel;
import cs3500.hw02.Pile;
import cs3500.hw02.PileType;
import cs3500.hw02.Rank;
import cs3500.hw02.Suit;

import static org.junit.Assert.assertEquals;

/**
 * Created by jowenfan on 10/12/16.
 */
public class FreeCellMultimoveModelTest {
  private Pile validFormPile = new Pile();
  private Pile invalidOrderFormPile = new Pile();
  private Pile invalidColorFormPile = new Pile();
  private FreeCellModel multimoveModel =
          FreeCellModelCreator.create(FreeCellModelCreator.GameType.MULTIMOVE);

  @Test
  public void moveTest() throws Exception {
    List<Card> deck = multimoveModel.getDeck();
    multimoveModel.startGame(deck, 4, 4, false);

    assertEquals("F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n" +
                    "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦",
            multimoveModel.getGameState());
    assertEquals(true, multimoveModel.foundationPileList.get(0).isEmpty());
    multimoveModel.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    assertEquals(false, multimoveModel.foundationPileList.get(0).isEmpty());
    multimoveModel.move(PileType.CASCADE, 1, 12, PileType.OPEN, 0);
    assertEquals("F1: A♠\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1: A♥\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n" +
                    "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦",
            multimoveModel.getGameState());
    multimoveModel.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
    assertEquals("F1: A♠\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2: A♥\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n" +
                    "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n" +
                    "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
                    "C4: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦",
            multimoveModel.getGameState());
    //multimoveModel.move(PileType.CASCADE, 0, 11, PileType.OPEN, 1);

  }

  @Test
  public void startGameTest() throws Exception {
    List<Card> deck = multimoveModel.getDeck();
    multimoveModel.startGame(deck, 4, 4, false);
    multimoveModel.startGame(deck, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n" +
            "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
            "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
            "C4: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦",
            multimoveModel.getGameState());
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

