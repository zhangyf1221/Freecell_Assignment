package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jowenfan on 9/27/16.
 */
public class CardTest {

  Card spade3 = new Card(Rank.Three, Suit.Spade);
  Card diamondK = new Card(Rank.King, Suit.Diamond);
  Card clubA = new Card(Rank.Ace, Suit.Club);
  Card heartJ = new Card(Rank.Jack, Suit.Heart);



  @Test
  public void getSuit() throws Exception {
    assertEquals(Suit.Spade, spade3.getSuit());
    assertEquals(Suit.Club, clubA.getSuit());
  }

  @Test
  public void getRank() throws Exception {
    assertEquals(Rank.King, diamondK.getRank());
    assertEquals(Rank.Jack, heartJ.getRank());
  }

  @Test
  public void printCard() throws Exception {
    assertEquals("A♣", clubA.printCard());
    assertEquals("K♦", diamondK.printCard());
  }

  @Test
  public void cardValue() throws Exception {
    assertEquals(3, spade3.cardValue());
    assertEquals(1, clubA.cardValue());
    assertEquals(13, diamondK.cardValue());
  }

  @Test
  public void nextCard() throws Exception {
    Card spade4 = new Card(Rank.Four, Suit.Spade);
    Card spade5 = new Card(Rank.Five, Suit.Spade);
    Card club3 = new Card(Rank.Three, Suit.Club);

    assertEquals(true, spade3.nextCard(spade4));
    assertEquals(false, spade3.nextCard(spade5));
    assertEquals(false, spade3.nextCard(club3));
  }

}