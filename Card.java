public class Card {

  // attribute of a card
  private int rank;
  private int suit;

  // constructor
  public Card(int rank, int suit) {
    this.rank = rank;
    this.suit = suit;

  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public int getRank() {
    return this.rank;
  }


  @Override
  public String toString() {

    String info = "";

    // special cases in converting rank to string
    switch (rank) {
      case 11:
        info += "J ";
        break;
      case 12:
        info += "Q";
        break;

      case 13:
        info += "K";
        break;
      case 14:
        info += "A";
        break;
      default:
        info += rank;
        break;
    }

    info += " (";


    // special cases in converting suit to string
    switch (suit) {
      case 0:
        info += "Spades";
        break;
      case 1:
        info += "Hearts";
        break;
      case 2:
        info += "Clubs";
        break;
      case 3:
        info += "Diamonds";
        break;
      default:
        break;
    }

    info += ")";

    return info; // sample A (Spaced);
  }
}
