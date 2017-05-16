package cs3500.hw02.Pile;

import java.util.ArrayList;

import cs3500.hw02.Card.Card;

/**
 * Created by Matt on 5/16/17.
 */
public abstract class Pile {
  private PileType pileType;
  protected ArrayList<ArrayList<Card>> pileContents;

  public Pile(PileType pileType, int numOfPiles) {
    this.pileType = pileType;
    this.pileContents = new ArrayList<>();

    for(int i = 0; i < numOfPiles; i++) {
      this.pileContents.add(new ArrayList<Card>());
    }
  }

  public String getPileState() {
    String state = "";
    int index = 1;
    for (ArrayList<Card> pile : pileContents) {
      state = state + this.pileType.toString() + String.valueOf(index) + ":";
      for(Card card : pile) {
        state = state + " "+ card.toString();
        if (card != pile.get(pile.size()-1)) {
           state = state + ",";
        }
      }
      if (pile != pileContents.get(pileContents.size()-1)) {
        state = state + "\n";
      }
      index++;
    }
    return state;
  }
}
