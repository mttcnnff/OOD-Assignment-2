package cs3500.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Matt on 5/14/17.
 */
public class FreecellModel implements FreecellOperations<Card>{

  private List<Card> deck;
  private ArrayList<ArrayList<Card>> foundation;
  private ArrayList<ArrayList<Card>> cascade;
  private ArrayList<ArrayList<Card>> open;

  @Override
  public List<Card> getDeck() {
    ArrayList<Card> cards = new ArrayList<>();

    for (int i = 0; i < 4; i++) {
      for (int j = 1; j < 14; j++) {
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
    this.foundation = this.initPile(4);

    //Check if cascade pile input is invalid
    if (this.cascadePileIsInvalid(numCascadePiles)) {
      throw new IllegalArgumentException("Number of Cascade Piles Invalid.");
    } else {
      this.cascade = this.initPile(numCascadePiles);
    }

    //Check if open pile input is invalid
    if (this.openPileIsInvalid(numOpenPiles)) {
      throw new IllegalArgumentException("Number of Open Piles Invalid.");
    } else {
      this.open = this.initPile(numOpenPiles);
    }

    //Deal cards to cascade piles
    int dealCount = 0;
    for(Card c : this.deck) {
      this.cascade.get(dealCount).add(c);
      dealCount = (dealCount + 1) % numCascadePiles;
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
    return null;
  }

  private ArrayList<ArrayList<Card>> initPile(int numPiles) {
    ArrayList<ArrayList<Card>> pile = new ArrayList<>();
    for(int i = 0; i < numPiles; i++) {
      pile.add(new ArrayList<Card>());
    }

    return pile;
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
    for(int i = 0; i < 14; i++) {
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
