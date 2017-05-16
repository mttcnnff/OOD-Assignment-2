package cs3500.hw02.Card;

/**
 * Type for the four types of suits in a deck of Cards. <br>
 *
 * Hearts: ♥ <br>
 * Diamonds: ♦ <br>
 * Spades: ♠ <br>
 * Clubs: ♣ <br>
 *
 */
public enum CardSuit {
  hearts,
  diamonds,
  spades,
  clubs;

  @Override
  public String toString() {
      switch(this) {
        case hearts: return "♥";
        case diamonds: return "♦";
        case spades: return "♠";
        case clubs: return "♣";
        default: throw new IllegalArgumentException("Card suit does not exist.");
      }
  }
}
