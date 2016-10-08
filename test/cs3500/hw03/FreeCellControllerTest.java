package cs3500.hw03;


import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import java.io.StringReader;

import cs3500.hw02.Card;
import cs3500.hw02.IFreeCellModel;
import cs3500.hw02.FreeCellModel;
import cs3500.hw02.Rank;
import cs3500.hw02.Suit;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jowenfan on 10/6/16.
 */



public class FreeCellControllerTest {

  private List<Card> deck1 = initDeck1();

  @Test
  public void quitTest1() throws Exception {
    Readable in = new StringReader("Q");
    Appendable out = new StringBuffer();

    IFreeCellController controller = new FreeCellController(in, out);

    controller.playGame(deck1, new FreeCellModel(), 8, 4, false);

    assertEquals(gameState1 +
            "\nEnter your move: (SourceType + Index, CardIndex, DestType + Index)" +
            "\nGame quit prematurely.", out.toString());
  }

  @Test
  public void quitTest2() throws Exception {
    Readable in = new StringReader("C1 q");
    Appendable out = new StringBuffer();
    IFreeCellController controller = new FreeCellController(in, out);

    controller.playGame(deck1, new FreeCellModel(), 8, 4, false);

    assertEquals(gameState1 +
            "\nEnter your move: (SourceType + Index, CardIndex, DestType + Index)" +
            "\nGame quit prematurely.", out.toString());
  }

  @Test
  public void quitTest3() throws Exception {
    Readable in = new StringReader("C1 13 q123412412412412441224412124");
    Appendable out = new StringBuffer();
    IFreeCellController controller = new FreeCellController(in, out);

    controller.playGame(deck1, new FreeCellModel(), 8, 4, false);

    assertEquals(gameState1 +
            "\nEnter your move: (SourceType + Index, CardIndex, DestType + Index)" +
            "\nGame quit prematurely.", out.toString());
  }

  @Test
  public void regularMoveTest() throws Exception {
    Readable in = new StringReader("C1 7 O1");
    Appendable out = new StringBuffer();
    IFreeCellController controller = new FreeCellController(in, out);

    controller.playGame(deck1, new FreeCellModel(), 8, 4, false);

    assertEquals(gameState2 +
            "\nEnter your move: (SourceType + Index, CardIndex, DestType + Index)",
            out.toString());
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

}