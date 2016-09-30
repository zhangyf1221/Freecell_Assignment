package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jowenfan on 9/27/16.
 */
public class PileTest {
  @Test
  public void emptyAddRemoveTest() throws Exception {
    Card spade3 = new Card(Rank.Three, Suit.Spade);
    Pile testPile = new Pile();

    assertEquals(true, testPile.isEmpty());
    testPile.add(spade3);
    assertEquals(false, testPile.isEmpty());
    testPile.remove(spade3);
    assertEquals(true, testPile.isEmpty());
  }

  @Test
  public void getCardTest() throws Exception {
    // Initialization
    Card spade3 = new Card(Rank.Three, Suit.Spade);
    Card diamondK = new Card(Rank.King, Suit.Diamond);
    Card clubA = new Card(Rank.Ace, Suit.Club);
    Card heartJ = new Card(Rank.Jack, Suit.Heart);
    Pile testPile = new Pile();

    testPile.add(spade3);
    testPile.add(diamondK);
    testPile.add(clubA);
    testPile.add(heartJ);

    assertEquals(heartJ, testPile.getCard(3));
  }

}