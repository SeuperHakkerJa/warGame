import java.util.*;


public class Game {


  private static List<Card> deck = new ArrayList<>();
  private static LinkedList<Card> player1 = new LinkedList<>();
  private static LinkedList<Card> player2 = new LinkedList<>();


  public static void main(String args[]) {

    // prepare card decks
    initGame();

    // start playing game until one loses all cards
    startGame();

    // game is over, deciding who has won.
    if (player1.size() == 0) {
      System.out.println("Player 2 wins");
    }

    else if (player2.size() == 0 ) {
      System.out.println("Player 1 wins");
    }
  }


  public static void initGame() {


    // add all cards in order to the deck
    for (int rank = 2; rank < 15; rank++) {
      for (int suit = 0; suit < 4; suit++) {
        Card card = new Card(rank, suit);
        deck.add(card);
      }
    }

    // shuffle
    Collections.shuffle(deck, new Random());

    // evenly divide to two players
    player1 = new LinkedList<>();
    player1.addAll(deck.subList(0, 25));

    player2 = new LinkedList<>();
    player2.addAll(deck.subList(26, 51));

  }


  public static void startGame() {


    while (true) {

      // make sure both player still qualify to play games
      if (player1.size() == 0 || player2.size() == 0) {
        System.out.println("Game Over");
        break;
      }

      // reveal top card
      Card card1 = player1.pop();
      Card card2 = player2.pop();

      // print info to terminal
      System.out.println("Player 1 got " + card1);
      System.out.println("Player 2 got " + card2);

      // Compare results
      if (card1.getRank() > card2.getRank()) {
        // p1 win this round so he takes both cards
        player1.addLast(card1);
        player1.addLast(card2);

      } else if (card1.getRank() < card2.getRank()) {
        // p2 win this ronud so ht takes both cards
        player2.addLast(card2);
        player2.addLast(card1);
      } else {

        // WAR
        boolean warDone = false;
        while (!warDone) {


          System.out.println(" ----------WAR---------- ");

          // create container for cards used during war.
          List<Card> war1 = new ArrayList<>();
          List<Card> war2 = new ArrayList<>();

          // put four cards out
          // watch out, not both players are promised to have 4 cards
          int counter1 = 4;
          while (!player1.isEmpty() && counter1 > 0) {
            war1.add(player1.pop());
            counter1--;
          }

          int counter2 = 4;
          while (!player2.isEmpty() && counter2 > 0) {
            war2.add(player2.pop());
            counter2--;
          }

          // if one of the player is not able to get at least one card, game is over.
          if (war1.size() == 0 || war2.size() == 0) {
            // if any war deck is empty, that implies one's full deck is also empty,
            // thus GAME OVER
            System.out.println("Game Over");
            return;
          }


          // compare to last card in war1 and war2
          int lastCardIndex1 = war1.size() - 1;
          int lastCardIndex2 = war2.size() - 1;

          // printInfo
          System.out.println("player 1 got " + war1.get(lastCardIndex1));
          System.out.println("player 2 got " + war2.get(lastCardIndex2));

          // if the player1's revealed card larger than player2's revealed card
          if (war1.get(lastCardIndex1).getRank() > war2.get(lastCardIndex2).getRank()) {
            player1.addAll(war1);
            player1.addAll(war2);
            player1.add(card1);
            player1.add(card2);
            System.out.println("player 1 win the war");
            warDone = true;
            continue;

          } else if (war1.get(lastCardIndex1).getRank() < war2.get(lastCardIndex2).getRank()) {
            player2.addAll(war1);
            player2.addAll(war2);
            player2.add(card1);
            player2.add(card2);
            System.out.println("player 2 win the war");
            warDone = true;
            continue;
          }
        }
      }
    }
  }

}
