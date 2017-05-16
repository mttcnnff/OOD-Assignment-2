package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Matt on 5/14/17.
 */
public class Deck {

  private ArrayList<Card> cards;

  public Deck() {

    for (int i = 0; i < 4; i++) {

      for (int j = 1; j < 14; j++) {
        cards.add(new Card(CardValue.values()[j], CardSuit.values()[i]));
      }

    }
  }

  public void shuffle() {
    Collections.shuffle(this.cards);
  }

  public ArrayList<Card> getCards() {
    return this.cards;
  }
}
