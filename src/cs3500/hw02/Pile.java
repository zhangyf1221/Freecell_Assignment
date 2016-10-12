package cs3500.hw02;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by jowenfan on 9/27/16.
 */

/**
 * Represents a pile, can be a open pile, foundation pile or cascade pile.
 */
public class Pile {
  // Define field:
  public List<Card> pile;

  public Pile() {
    this.pile = new ArrayList<>();
  }

  /**
   * Get the card at last position of the pile.
   * @param t index
   * @return card at last position of a pile
   * @throws IllegalArgumentException if the given index is not the last position of the pile
   */
  public Card getCard(int t) {
    return pile.get(t);
  }

  /**
   * Check the pile is empty or not.
   * @return true if the pile is empty, false if not
   */
  public boolean isEmpty() {
    return pile.isEmpty();
  }

  /**
   * Add the card to the last position of pile.
   * @param card card to be added
   */
  public void add(Card card) {
    pile.add(card);
  }

  /**
   * Remove the card to the last position of pile.
   * @param card card to be removed
   */
  public void remove(Card card) {
    pile.remove(card);
  }

  /**
   * Get the card from the last position of a pile.
   * @return card at the last position of a pile
   */
  public Card lastCard() {
    return pile.get(pile.size() - 1);
  }

  /**
   * Get the size of pile.
   * @return the size of pile
   */
  public int size() {
    return pile.size();
  }

  public Card get(int i) {
    return pile.get(i);
  }

  /**
   * check the card at given index is the last card in the pile or not.
   * @param i index of card
   * @return true if the card is the last card in pile, false if not
   */
  public boolean isLastCard(int i) {
    return (pile.size() == (i + 1));
  }

  /**
   * Remove the last card of a pile.
   */
  public void removeLast() {
    pile.remove(size() - 1);
  }

}
