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
  public void isGameOverTest() throws Exception {
    List<Card> deck = multimoveModel.getDeck();
    multimoveModel.startGame(deck, 4, 4, false);
    //C1 to F1
    multimoveModel.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 0);
    multimoveModel.move(PileType.CASCADE, 0, 11, PileType.FOUNDATION, 0);
    multimoveModel.move(PileType.CASCADE, 0, 10, PileType.FOUNDATION, 0);
    multimoveModel.move(PileType.CASCADE, 0, 9, PileType.FOUNDATION, 0);
    multimoveModel.move(PileType.CASCADE, 0, 8, PileType.FOUNDATION, 0);
    multimoveModel.move(PileType.CASCADE, 0, 7, PileType.FOUNDATION, 0);
    multimoveModel.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 0);
    multimoveModel.move(PileType.CASCADE, 0, 5, PileType.FOUNDATION, 0);
    multimoveModel.move(PileType.CASCADE, 0, 4, PileType.FOUNDATION, 0);
    multimoveModel.move(PileType.CASCADE, 0, 3, PileType.FOUNDATION, 0);
    multimoveModel.move(PileType.CASCADE, 0, 2, PileType.FOUNDATION, 0);
    multimoveModel.move(PileType.CASCADE, 0, 1, PileType.FOUNDATION, 0);
    multimoveModel.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 0);
    assertEquals("F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n" +
            "C3: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n" +
            "C4: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦",
            multimoveModel.getGameState());
    assertEquals(false, multimoveModel.isGameOver());
    //C2 to F2
    multimoveModel.move(PileType.CASCADE, 1, 12, PileType.FOUNDATION, 1);
    multimoveModel.move(PileType.CASCADE, 1, 11, PileType.FOUNDATION, 1);
    multimoveModel.move(PileType.CASCADE, 1, 10, PileType.FOUNDATION, 1);
    multimoveModel.move(PileType.CASCADE, 1, 9, PileType.FOUNDATION, 1);
    multimoveModel.move(PileType.CASCADE, 1, 8, PileType.FOUNDATION, 1);
    multimoveModel.move(PileType.CASCADE, 1, 7, PileType.FOUNDATION, 1);
    multimoveModel.move(PileType.CASCADE, 1, 6, PileType.FOUNDATION, 1);
    multimoveModel.move(PileType.CASCADE, 1, 5, PileType.FOUNDATION, 1);
    multimoveModel.move(PileType.CASCADE, 1, 4, PileType.FOUNDATION, 1);
    multimoveModel.move(PileType.CASCADE, 1, 3, PileType.FOUNDATION, 1);
    multimoveModel.move(PileType.CASCADE, 1, 2, PileType.FOUNDATION, 1);
    multimoveModel.move(PileType.CASCADE, 1, 1, PileType.FOUNDATION, 1);
    multimoveModel.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 1);
    assertEquals(false, multimoveModel.isGameOver());
    //C3 to F3
    multimoveModel.move(PileType.CASCADE, 2, 12, PileType.FOUNDATION, 2);
    multimoveModel.move(PileType.CASCADE, 2, 11, PileType.FOUNDATION, 2);
    multimoveModel.move(PileType.CASCADE, 2, 10, PileType.FOUNDATION, 2);
    multimoveModel.move(PileType.CASCADE, 2, 9, PileType.FOUNDATION, 2);
    multimoveModel.move(PileType.CASCADE, 2, 8, PileType.FOUNDATION, 2);
    multimoveModel.move(PileType.CASCADE, 2, 7, PileType.FOUNDATION, 2);
    multimoveModel.move(PileType.CASCADE, 2, 6, PileType.FOUNDATION, 2);
    multimoveModel.move(PileType.CASCADE, 2, 5, PileType.FOUNDATION, 2);
    multimoveModel.move(PileType.CASCADE, 2, 4, PileType.FOUNDATION, 2);
    multimoveModel.move(PileType.CASCADE, 2, 3, PileType.FOUNDATION, 2);
    multimoveModel.move(PileType.CASCADE, 2, 2, PileType.FOUNDATION, 2);
    multimoveModel.move(PileType.CASCADE, 2, 1, PileType.FOUNDATION, 2);
    multimoveModel.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 2);
    assertEquals(false, multimoveModel.isGameOver());
    //C4 to F4
    multimoveModel.move(PileType.CASCADE, 3, 12, PileType.FOUNDATION, 3);
    multimoveModel.move(PileType.CASCADE, 3, 11, PileType.FOUNDATION, 3);
    multimoveModel.move(PileType.CASCADE, 3, 10, PileType.FOUNDATION, 3);
    multimoveModel.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 3);
    multimoveModel.move(PileType.CASCADE, 3, 8, PileType.FOUNDATION, 3);
    multimoveModel.move(PileType.CASCADE, 3, 7, PileType.FOUNDATION, 3);
    multimoveModel.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 3);
    multimoveModel.move(PileType.CASCADE, 3, 5, PileType.FOUNDATION, 3);
    multimoveModel.move(PileType.CASCADE, 3, 4, PileType.FOUNDATION, 3);
    multimoveModel.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION, 3);
    multimoveModel.move(PileType.CASCADE, 3, 2, PileType.FOUNDATION, 3);
    multimoveModel.move(PileType.CASCADE, 3, 1, PileType.FOUNDATION, 3);
    multimoveModel.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 3);
    assertEquals("F1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "F2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "F3: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n", multimoveModel.getGameState());
    assertEquals(true, multimoveModel.isGameOver());


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

