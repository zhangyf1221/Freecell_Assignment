package cs3500.hw03;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.StringReader;
import cs3500.hw02.Card;
import cs3500.hw02.FreeCellModel;
import cs3500.hw02.Pile;
import cs3500.hw02.Rank;
import cs3500.hw02.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    assertEquals(gameState1 +
                    "\nEnter your move: (SourceType + Index, CardIndex, DestType + Index)\n" +
                    gameState2,
            out.toString());
  }

  @Test
  public void invalidSourceInputTest() throws Exception {
    Readable in = new StringReader("C15 7 O1");
    Appendable out = new StringBuffer();
    IFreeCellController controller = new FreeCellController(in, out);

    controller.playGame(deck1, new FreeCellModel(), 8, 4, false);

    assertEquals(gameState1 +
                    "\nEnter your move: (SourceType + Index, CardIndex, DestType + Index)\n" +
                    "Invalid move. Try again. " +
                    "Reason: java.lang.IllegalArgumentException: Source pile number out of bound",
            out.toString());
  }

  @Test
  public void invalidCardIndexInputTest() throws Exception {
    Readable in = new StringReader("C1 17 O1");
    Appendable out = new StringBuffer();
    IFreeCellController controller = new FreeCellController(in, out);

    controller.playGame(deck1, new FreeCellModel(), 8, 4, false);

    assertEquals(gameState1 +
                    "\nEnter your move: (SourceType + Index, CardIndex, DestType + Index)\n" +
                    "Invalid move. Try again. " +
                    "Reason: java.lang.IndexOutOfBoundsException: Index: 16, Size: 7",
            out.toString());
  }

  @Test
  public void invalidDestInputTest() throws Exception {
    Readable in = new StringReader("C1 7 O1234");
    Appendable out = new StringBuffer();
    IFreeCellController controller = new FreeCellController(in, out);

    controller.playGame(deck1, new FreeCellModel(), 8, 4, false);

    assertEquals(gameState1 +
                    "\nEnter your move: (SourceType + Index, CardIndex, DestType + Index)\n" +
                    "Invalid move. Try again. " +
                    "Reason: java.lang.IllegalArgumentException: " +
                    "Destination pile number out of bounds",
            out.toString());
  }

  @Test
  public void invalidMove1() throws Exception {
    Readable in = new StringReader("C1 7 C2");
    Appendable out = new StringBuffer();
    IFreeCellController controller = new FreeCellController(in, out);

    controller.playGame(deck1, new FreeCellModel(), 8, 4, false);

    assertEquals(gameState1 +
                    "\nEnter your move: (SourceType + Index, CardIndex, DestType + Index)\n" +
                    "Invalid move. Try again. Reason: java.lang.IllegalArgumentException: " +
                    "The card you move to the pile is not in correct order",
            out.toString());
  }

  @Test
  public void invalidMove2() throws Exception {
    Readable in = new StringReader("C1 7 O1 C1 6 O1");
    Appendable out = new StringBuffer();
    IFreeCellController controller = new FreeCellController(in, out);

    controller.playGame(deck1, new FreeCellModel(), 8, 4, false);

    assertEquals(gameState1 +
                    "\nEnter your move: (SourceType + Index, CardIndex, DestType + Index)\n" +
                    "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1: K♦\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: A♦, 3♦, 5♦, 7♦, 9♦, J♦\n" +
                    "C2: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n" +
                    "C3: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥\n" +
                    "C4: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n" +
                    "C5: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦\n" +
                    "C6: 2♣, 4♣, 6♣, 8♣, 10♣, Q♣\n" +
                    "C7: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
                    "C8: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠" +
                    "\nEnter your move: (SourceType + Index, CardIndex, DestType + Index)\n" +
                    "Invalid move. Try again. " +
                    "Reason: java.lang.IllegalArgumentException: Open pile can only keep 1 card",
            out.toString());
  }

  @Test
  public void invalidMove3() throws Exception {
    Readable in = new StringReader("C1 7 F2");
    Appendable out = new StringBuffer();
    IFreeCellController controller = new FreeCellController(in, out);

    controller.playGame(deck1, new FreeCellModel(), 8, 4, false);

    assertEquals(gameState1 +
                    "\nEnter your move: (SourceType + Index, CardIndex, DestType + Index)\n" +
                    "Invalid move. Try again. " +
                    "Reason: java.lang.IllegalArgumentException: " +
                    "Only Ace can be placed into a empty foundation pile",
            out.toString());
  }


  /*
    @Test
    public void gameOverTest() throws Exception {
      initGame3();
      Readable in = new StringReader("");
      Appendable out = new StringBuffer();
      IFreeCellController controller = new FreeCellController(in, out);

      controller.playGame(deck1, model3, 4, 1, false);

      assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
              "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
              "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
              "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
              "O1:\n" +
              "C1:\n" +
              "C2:\n" +
              "C3:\n" +
              "C4:\n" +
              "Game over.", out.toString());
    }
  */
  @Test
  public void invalidModel1() throws Exception {
    Readable in = new StringReader("");
    Appendable out = new StringBuffer();
    IFreeCellController controller = new FreeCellController(in, out);

    controller.playGame(deck1, model3, 3, 1, false);

    assertEquals("Could not start game.", out.toString());
  }

  @Test
  public void invalidModel2() throws Exception {
    Readable in = new StringReader("");
    Appendable out = new StringBuffer();
    IFreeCellController controller = new FreeCellController(in, out);

    controller.playGame(deck1, model3, 8, 0, false);

    assertEquals("Could not start game.", out.toString());
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

  private FreeCellModel model3 = new FreeCellModel();
  private Pile f1 = new Pile();
  private Pile f2 = new Pile();
  private Pile f3 = new Pile();
  private Pile f4 = new Pile();
  private Pile o1 = new Pile();
  private Pile c1 = new Pile();
  private Pile c2 = new Pile();
  private Pile c3 = new Pile();
  private Pile c4 = new Pile();


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
    cPL.add(c2);
    cPL.add(c3);
    cPL.add(c4);
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