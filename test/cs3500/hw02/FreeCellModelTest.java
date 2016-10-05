package cs3500.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by jowenfan on 9/28/16.
 */
public class FreeCellModelTest {

  private FreeCellModel model1;
  private List<Card> deck1 = initDeck1();
  private List<Pile> foundationPileList = new ArrayList<Pile>();
  private List<Pile> openPileList = new ArrayList<Pile>();
  private List<Pile> cascadePileList = new ArrayList<Pile>();

  private FreeCellModel model2;

  private List<Card> initDeck1() {
    return Arrays.asList(
            new Card(Rank.Ace, Suit.Diamond),
            new Card(Rank.Ace, Suit.Club),
            new Card(Rank.Ace, Suit.Heart),
            new Card(Rank.Ace, Suit.Spade),
            new Card(Rank.Two, Suit.Diamond),
            new Card(Rank.Two, Suit.Club),
            new Card(Rank.Two, Suit.Heart),
            new Card(Rank.Two, Suit.Spade),
            new Card(Rank.Three, Suit.Diamond),
            new Card(Rank.Three, Suit.Club),
            new Card(Rank.Three, Suit.Heart),
            new Card(Rank.Three, Suit.Spade),
            new Card(Rank.Four, Suit.Diamond),
            new Card(Rank.Four, Suit.Club),
            new Card(Rank.Four, Suit.Heart),
            new Card(Rank.Four, Suit.Spade),
            new Card(Rank.Five, Suit.Diamond),
            new Card(Rank.Five, Suit.Club),
            new Card(Rank.Five, Suit.Heart),
            new Card(Rank.Five, Suit.Spade),
            new Card(Rank.Six, Suit.Diamond),
            new Card(Rank.Six, Suit.Club),
            new Card(Rank.Six, Suit.Heart),
            new Card(Rank.Six, Suit.Spade),
            new Card(Rank.Seven, Suit.Diamond),
            new Card(Rank.Seven, Suit.Club),
            new Card(Rank.Seven, Suit.Heart),
            new Card(Rank.Seven, Suit.Spade),
            new Card(Rank.Eight, Suit.Diamond),
            new Card(Rank.Eight, Suit.Club),
            new Card(Rank.Eight, Suit.Heart),
            new Card(Rank.Eight, Suit.Spade),
            new Card(Rank.Nine, Suit.Diamond),
            new Card(Rank.Nine, Suit.Club),
            new Card(Rank.Nine, Suit.Heart),
            new Card(Rank.Nine, Suit.Spade),
            new Card(Rank.Ten, Suit.Diamond),
            new Card(Rank.Ten, Suit.Club),
            new Card(Rank.Ten, Suit.Heart),
            new Card(Rank.Ten, Suit.Spade),
            new Card(Rank.Jack, Suit.Diamond),
            new Card(Rank.Jack, Suit.Club),
            new Card(Rank.Jack, Suit.Heart),
            new Card(Rank.Jack, Suit.Spade),
            new Card(Rank.Queen, Suit.Diamond),
            new Card(Rank.Queen, Suit.Club),
            new Card(Rank.Queen, Suit.Heart),
            new Card(Rank.Queen, Suit.Spade),
            new Card(Rank.King, Suit.Diamond),
            new Card(Rank.King, Suit.Club),
            new Card(Rank.King, Suit.Heart),
            new Card(Rank.King, Suit.Spade));
  }

  private void initGame1() {
    model1 = new FreeCellModel();
    model1.startGame(deck1, 8, 4, false);
  }

  private void initGame2() {
    model2 = new FreeCellModel();
    model2.startGame(deck1, 8, 4, true);
  }

  @Test
  public void getDeck() throws Exception {
    initGame1();
    assertEquals(deck1, model1.getDeck());
  }




  @Test
  public void move() throws Exception {
    initGame1();
    model1.move(PileType.CASCADE, 0, 6, PileType.OPEN, 0);
    assertEquals(gameState2, model1.getGameState());
  }


  @Test
  public void getGameState() throws Exception {
    initGame1();
    initGame2();
    assertEquals(gameState1, model1.getGameState());
    assertNotEquals(gameState1, model2.getGameState());
  }

  private String gameState1 = "F1: " +
          "\nF2: " +
          "\nF3: " +
          "\nF4: " +
          "\nO1: " +
          "\nO2: " +
          "\nO3: " +
          "\nO4: " +
          "\nC1: A♦, 3♦, 5♦, 7♦, 9♦, J♦, K♦" +
          "\nC2: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣" +
          "\nC3: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥" +
          "\nC4: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠" +
          "\nC5: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦" +
          "\nC6: 2♣, 4♣, 6♣, 8♣, 10♣, Q♣" +
          "\nC7: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥" +
          "\nC8: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠";

  private String gameState2 = "F1: " +
          "\nF2: " +
          "\nF3: " +
          "\nF4: " +
          "\nO1: K♦" +
          "\nO2: " +
          "\nO3: " +
          "\nO4: " +
          "\nC1: A♦, 3♦, 5♦, 7♦, 9♦, J♦" +
          "\nC2: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣" +
          "\nC3: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥" +
          "\nC4: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠" +
          "\nC5: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦" +
          "\nC6: 2♣, 4♣, 6♣, 8♣, 10♣, Q♣" +
          "\nC7: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥" +
          "\nC8: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠";

}