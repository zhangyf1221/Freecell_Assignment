package cs3500.hw02;

/**
 * Created by jowenfan on 9/22/16.
 */

/**
 * Represents the suit of a card. Has 4 different types.
 */
public enum Suit {
  Diamond("♦"), Club("♣"),Heart("♥"), Spade("♠");

  // Define the field
  private final String suit;

  // Define the constructor
  Suit(String suit) {
    this.suit = suit;
  }

  @Override
  public String toString() {
    return this.suit;
  }
}
