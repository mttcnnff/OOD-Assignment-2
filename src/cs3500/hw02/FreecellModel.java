package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs3500.hw02.Card.Card;
import cs3500.hw02.Card.CardSuit;
import cs3500.hw02.Card.CardValue;
import cs3500.hw02.Pile.CascadePile;
import cs3500.hw02.Pile.FoundationPile;
import cs3500.hw02.Pile.OpenPile;
import cs3500.hw02.Pile.Pile;
import cs3500.hw02.Pile.PileType;

/**
 * Created by Matt on 5/14/17.
 */
public class FreecellModel implements FreecellOperations<Card>{

  private List<Card> deck;
  private Pile foundation;
  private Pile cascade;
  private Pile open;

  @Override
  public List<Card> getDeck() {
    ArrayList<Card> cards = new ArrayList<>();

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 13; j++) {
        cards.add(new Card(CardValue.values()[j], CardSuit.values()[i]));
      }
    }

    return cards;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) throws IllegalArgumentException {

    //Check if deck is valid
    if (this.deckIsInvalid(deck)) {
      throw new IllegalArgumentException("Deck is invalid.");
    }else {
      this.deck = deck;
    }

    //Check if deck should be shuffled
    if (shuffle) {
      Collections.shuffle(this.deck);
    }

    //Build foundation piles
    this.foundation = new FoundationPile();

    //Check if open pile input is invalid
    if (this.openPileIsInvalid(numOpenPiles)) {
      throw new IllegalArgumentException("Number of Open Piles Invalid.");
    } else {
      this.open = new OpenPile(numOpenPiles);
    }

    //Check if cascade pile input is invalid
    if (this.cascadePileIsInvalid(numCascadePiles)) {
      throw new IllegalArgumentException("Number of Cascade Piles Invalid.");
    } else {
      this.cascade = new CascadePile(numCascadePiles, this.deck);
    }

  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination, int destPileNumber) throws IllegalArgumentException {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String getGameState() {
    return this.foundation.getPileState()
            + "\n"
            + this.open.getPileState()
            + "\n"
            + this.cascade.getPileState();
  }

  private boolean deckIsInvalid(List<Card> deck) {
    //Check deck size
    if(deck.size() != 52) {
      return true;
    }

    //Check duplicates by suit
    for(int i = 0; i < 4; i++) {
      if (this.suitCount(CardSuit.values()[i], deck) != 13) {
        return true;
      }
    }

    //Check duplicated by number
    for(int i = 0; i < 13; i++) {
      if (this.valueCount(CardValue.values()[i], deck) != 4) {
        return true;
      }
    }
    return false;
  }

  private boolean cascadePileIsInvalid(int numCascadePiles) {
    return numCascadePiles < 4 || numCascadePiles > 8;
  }

  private boolean openPileIsInvalid(int numOpenPiles) {
    return numOpenPiles < 1 || numOpenPiles > 4;
  }

  private int suitCount(CardSuit suit, List<Card> deckToCheck) {
    int count = 0;
    for (Card c : deckToCheck) {
      if (c.isOfSuit(suit)) {
        count++;
      }
    }
    return count;
  }

  private int valueCount(CardValue value, List<Card> deckToCheck) {
    int count = 0;
    for (Card c : deckToCheck) {
      if (c.isOfValue(value)) {
        count++;
      }
    }
    return count;
  }
}
