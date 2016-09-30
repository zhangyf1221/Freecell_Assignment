package cs3500.hw02;

/**
 * Created by jowenfan on 9/24/16.
 */
/**
 * Represents the index of a card. Starts from Ace to King.
 */
public enum Rank {
  Ace("A"), Two("2"), Three("3"), Four("4"), Five("5"),
  Six("6"), Seven("7"), Eight("8"), Nine("9"), Ten("10"), Jack("J"),
  Queen("Q"), King("K");

  // Define the field:
  private final String rank;

  // Define the constructor
  Rank(String rank) {
    this.rank = rank;
  }

  @Override
  public String toString() {
    return this.rank;
  }

}
