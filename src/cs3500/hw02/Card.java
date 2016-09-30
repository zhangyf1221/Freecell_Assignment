package cs3500.hw02;

/**
 * Created by jowenfan on 9/22/16.
 */

/**
 * This is the class of Card. A card has one of 4 different kinds of suit
 * and an index from 1 to 13.
 */
public class Card {
  private final Rank rank;
  private final Suit suit;


  /**
   * The constructor of a card which has a suit and an index.
   * @param rank rank of card
   * @param suit suit of card
   */
  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  /**
   * Get the suit of this card.
   * @return the suit of the card
   */
  public Suit getSuit() {
    return this.suit;
  }

  /**
   * Get the index of this card.
   * @return the card index of the card
   */
  public Rank getRank() {
    return this.rank;
  }

  /**
   * print the card in String.
   * @return the card's index and suit in String
   */
  public String printCard() {
    return rank.toString() + suit.toString();
  }

  /**
   * Assign value for each rank.
   * @return integer from 1 to 13
   */
  public int cardValue() {
    Rank r = this.getRank();
    int value = 0;
    switch (r) {
      case Ace:
        value = 1;
        break;
      case Two:
        value = 2;
        break;
      case Three:
        value = 3;
        break;
      case Four:
        value = 4;
        break;
      case Five:
        value = 5;
        break;
      case Six:
        value = 6;
        break;
      case Seven:
        value = 7;
        break;
      case Eight:
        value = 8;
        break;
      case Nine:
        value = 9;
        break;
      case Ten:
        value = 10;
        break;
      case Jack:
        value = 11;
        break;
      case Queen:
        value = 12;
        break;
      case King:
        value = 13;
        break;
      default:
        throw new IllegalArgumentException("Invalid rank");
    }
    return value;
  }




  /**
   * Check two cards' suit are same and card 2's value is exactly 1 greater than card 1.
   * @param that another card use to compare
   * @return true if two cards have same suit and c2 is exactly 1 greater than c1
   */
  public boolean nextCard(Card that) {
    return (this.suit == that.suit) && (this.cardValue() == that.cardValue() - 1);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    else if (!(obj instanceof Card)) {
      return false;
    }
    else {
      Card that = (Card)obj;
      return that.getRank().equals(this.getRank()) &&
              that.getSuit().equals(this.getSuit());
    }
  }

  @Override
  public int hashCode() {
    return this.rank.hashCode() * this.suit.hashCode();
  }



}
