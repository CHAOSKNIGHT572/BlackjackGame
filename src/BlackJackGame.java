import java.util.Scanner;

/**
 * Created by tansen on 1/21/19.
 */
public class BlackJackGame {
    public static void main(String[] args) {
        System.out.println("Welcome to BlackJack Game made by Sen!");
        Deck curDeck = new Deck();
        curDeck.createFullDeck();
        curDeck.shuffle();
        curDeck.shuffle();
        curDeck.shuffle();
        System.out.println(curDeck);
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();
        double playerMoney = 100.00;
        Scanner userInput = new Scanner(System.in);

        //Game Loop
        while (playerMoney > 0) {
            System.out.println("How much money would you like to bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("You cannot bet more than you have.");
                break;
            }
            boolean endRound = false;
            //player get two cards;
            playerDeck.draw(curDeck);
            playerDeck.draw(curDeck);
            //dealer get two cards.
            dealerDeck.draw(curDeck);
            dealerDeck.draw(curDeck);

            while (true) {
                System.out.println("Your hand: ");
                System.out.println(playerDeck.toString());
                System.out.println("Your deck is valued at: " + playerDeck.cardValue());

                //Display Dealer Hand
                System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() +
                " and [Hidden Card]");
                if (playerDeck.cardValue() >= 21) {
                    break;
                }
                //ask player what to do?
                System.out.println("Would you like to (1)Hit or (2)Stand");
                int response = userInput.nextInt();
                if (response == 1) {
                    playerDeck.draw(curDeck);
                    System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());
                    //
                    if (playerDeck.cardValue() > 21) {
                        System.out.println("Bust. Currently valued at " + playerDeck.cardValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if (response == 2) {
                    break;
                }
            }
            if (playerDeck.cardValue() == 21) {
                System.out.println("BLACKJACK!!! You win!!!");
                playerMoney += playerBet;
            }
            else {
                // Reveal Dealer's card
                System.out.println("Dealer Hand: " + dealerDeck.toString());
                if (dealerDeck.cardValue() > playerDeck.cardValue() && !endRound) {
                    System.out.println("Dealer beats you!");
                    playerMoney -= playerBet;
                    endRound = true;
                }
                // Dealer Draws at 16
                while ((dealerDeck.cardValue() < 17) && !endRound) {
                    dealerDeck.draw(curDeck);
                    System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());

                }
                //Display total value
                System.out.println("Dealer's Hand is valued at: " + dealerDeck.cardValue());
                if ((dealerDeck.cardValue() > 21 && !endRound)) {
                    System.out.println("Dealer busts! You win!");
                    playerMoney += playerBet;
                    endRound = true;
                }

                if (playerDeck.cardValue() == dealerDeck.cardValue() && !endRound) {
                    System.out.println("Push");
                    endRound = true;
                }

                if (playerDeck.cardValue() > dealerDeck.cardValue() && !endRound) {
                    System.out.println("You win the hand!");
                    playerMoney += playerBet;
                    //endRound = true;
                }
            }
            playerDeck.moveAllDeck(curDeck);
            dealerDeck.moveAllDeck(curDeck);
            System.out.println("End of Hand");
        }
        System.out.println("Game Over! You are out of Money! :-(");
    }
}
