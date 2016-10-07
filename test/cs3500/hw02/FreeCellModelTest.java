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

  @Test
  public void gameOverTest() throws Exception {
    initGame2();
    initGame3();
    assertEquals(gameState3, model3.getGameState());
    assertEquals(false, model2.isGameOver());
    assertEquals(true, model3.isGameOver());
  }

  private String gameState1 = "F1:" +
          "\nF2:" +
          "\nF3:" +
          "\nF4:" +
          "\nO1:" +
          "\nO2:" +
          "\nO3:" +
          "\nO4:" +
          "\nC1: A♦, 3♦, 5♦, 7♦, 9♦, J♦, K♦" +
          "\nC2: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣" +
          "\nC3: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥" +
          "\nC4: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠" +
          "\nC5: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦" +
          "\nC6: 2♣, 4♣, 6♣, 8♣, 10♣, Q♣" +
          "\nC7: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥" +
          "\nC8: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠";

  private String gameState2 = "F1:" +
          "\nF2:" +
          "\nF3:" +
          "\nF4:" +
          "\nO1: K♦" +
          "\nO2:" +
          "\nO3:" +
          "\nO4:" +
          "\nC1: A♦, 3♦, 5♦, 7♦, 9♦, J♦" +
          "\nC2: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣" +
          "\nC3: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥" +
          "\nC4: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠" +
          "\nC5: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦" +
          "\nC6: 2♣, 4♣, 6♣, 8♣, 10♣, Q♣" +
          "\nC7: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥" +
          "\nC8: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠";

  private String gameState3 = "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦" +
          "\nF2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣" +
          "\nF3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥" +
          "\nF4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠" +
          "\nO1:" +
          "\nC1:";

  private FreeCellModel model3 = new FreeCellModel();
  private Pile<Card> f1 = new Pile<>();
  private Pile<Card> f2 = new Pile<>();
  private Pile<Card> f3 = new Pile<>();
  private Pile<Card> f4 = new Pile<>();
  private Pile<Card> o1 = new Pile<>();
  private Pile<Card> c1 = new Pile<>();

  private void initF1() {
    f1.add(new Card(Rank.Ace, Suit.Diamond));
    f1.add(new Card(Rank.Two, Suit.Diamond));
    f1.add(new Card(Rank.Three, Suit.Diamond));
    f1.add(new Card(Rank.Four, Suit.Diamond));
    f1.add(new Card(Rank.Five, Suit.Diamond));
    f1.add(new Card(Rank.Six, Suit.Diamond));
    f1.add(new Card(Rank.Seven, Suit.Diamond));
    f1.add(new Card(Rank.Eight, Suit.Diamond));
    f1.add(new Card(Rank.Nine, Suit.Diamond));
    f1.add(new Card(Rank.Ten, Suit.Diamond));
    f1.add(new Card(Rank.Jack, Suit.Diamond));
    f1.add(new Card(Rank.Queen, Suit.Diamond));
    f1.add(new Card(Rank.King, Suit.Diamond));
  }

  private void initF2() {
    f2.add(new Card(Rank.Ace, Suit.Club));
    f2.add(new Card(Rank.Two, Suit.Club));
    f2.add(new Card(Rank.Three, Suit.Club));
    f2.add(new Card(Rank.Four, Suit.Club));
    f2.add(new Card(Rank.Five, Suit.Club));
    f2.add(new Card(Rank.Six, Suit.Club));
    f2.add(new Card(Rank.Seven, Suit.Club));
    f2.add(new Card(Rank.Eight, Suit.Club));
    f2.add(new Card(Rank.Nine, Suit.Club));
    f2.add(new Card(Rank.Ten, Suit.Club));
    f2.add(new Card(Rank.Jack, Suit.Club));
    f2.add(new Card(Rank.Queen, Suit.Club));
    f2.add(new Card(Rank.King, Suit.Club));
  }

  private void initF3() {
    f3.add(new Card(Rank.Ace, Suit.Heart));
    f3.add(new Card(Rank.Two, Suit.Heart));
    f3.add(new Card(Rank.Three, Suit.Heart));
    f3.add(new Card(Rank.Four, Suit.Heart));
    f3.add(new Card(Rank.Five, Suit.Heart));
    f3.add(new Card(Rank.Six, Suit.Heart));
    f3.add(new Card(Rank.Seven, Suit.Heart));
    f3.add(new Card(Rank.Eight, Suit.Heart));
    f3.add(new Card(Rank.Nine, Suit.Heart));
    f3.add(new Card(Rank.Ten, Suit.Heart));
    f3.add(new Card(Rank.Jack, Suit.Heart));
    f3.add(new Card(Rank.Queen, Suit.Heart));
    f3.add(new Card(Rank.King, Suit.Heart));
  }

  private void initF4() {
    f4.add(new Card(Rank.Ace, Suit.Spade));
    f4.add(new Card(Rank.Two, Suit.Spade));
    f4.add(new Card(Rank.Three, Suit.Spade));
    f4.add(new Card(Rank.Four, Suit.Spade));
    f4.add(new Card(Rank.Five, Suit.Spade));
    f4.add(new Card(Rank.Six, Suit.Spade));
    f4.add(new Card(Rank.Seven, Suit.Spade));
    f4.add(new Card(Rank.Eight, Suit.Spade));
    f4.add(new Card(Rank.Nine, Suit.Spade));
    f4.add(new Card(Rank.Ten, Suit.Spade));
    f4.add(new Card(Rank.Jack, Suit.Spade));
    f4.add(new Card(Rank.Queen, Suit.Spade));
    f4.add(new Card(Rank.King, Suit.Spade));
  }

  private List<Pile> fPL = new ArrayList<>();
  private List<Pile> oPL = new ArrayList<>();
  private List<Pile> cPL = new ArrayList<>();

  private void initFP() {
    initF1();
    initF2();
    initF3();
    initF4();
    fPL.add(f1);
    fPL.add(f2);
    fPL.add(f3);
    fPL.add(f4);
  }

  private void initOP() {
    oPL.add(o1);
  }

  private void initCP() {
    cPL.add(c1);
  }

  private void initGame3() {
    initFP();
    initOP();
    initCP();
    model3.foundationPileList = fPL;
    model3.openPileList = oPL;
    model3.cascadePileList = cPL;
  }


}