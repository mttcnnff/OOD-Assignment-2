package cs3500.hw02.Pile;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Card.Card;

/**
 * Created by Matt on 5/16/17.
 */
public class CascadePile extends Pile {

  public CascadePile(int numOfPiles, List<Card> deck) {

    super(PileType.CASCADE, numOfPiles);

    //Deal cards to cascade pile
    int dealCount = 0;
    for(Card c : deck) {
      this.pileContents.get(dealCount).add(c);
      dealCount = (dealCount + 1) % numOfPiles;
    }
  }

}
