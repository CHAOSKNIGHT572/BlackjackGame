import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tansen on 1/21/19.
 */
public class Deck {
    private List<Card> deck;
    public Deck() {
        deck = new ArrayList<>();
    }
    public void createFullDeck() {
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                deck.add(new Card(suit, value));
            }
        }
    }
    public String toString() {
        String cardListOutput = "";
        int i = 0;
        for (Card card : deck) {
            cardListOutput += "\n" + i + "-" + card.toString();
            ++i;
        }
        return cardListOutput;
    }
    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < 52; ++i) {
            int swapIndex = i + random.nextInt(52 - i);
            Card curCard = deck.get(i);
            Card swapCard = deck.get(swapIndex);
            deck.set(i, swapCard);
            deck.set(swapIndex, curCard);
        }
    }
    public Card getCard(int i) {
        return deck.get(i);
    }
    public Card removeCard(int i) {
        return deck.remove(i);
    }
    public void addCard(Card card) {
        deck.add(card);
    }
    public void moveAllDeck(Deck moveTo) {
        int thisDeckSize = deck.size();
        //put cards into moveTo deck;
        for (int i = 0; i < thisDeckSize; ++i) {
            moveTo.addCard(getCard(i));
        }
        for (int i = 0; i < thisDeckSize; ++i) {
            this.removeCard(0);
        }
    }
    //Draw from Deck
    public void draw(Deck comingFrom) {
        deck.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }
    public int deckSize() {
        return deck.size();
    }
    //return total value of card in deck
    public int cardValue() {
        int totalValue = 0;
        int aces = 0;
        for (Card card : deck) {
            switch (card.getValue()) {
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 11; break;
                case QUEEN: totalValue += 12; break;
                case KING: totalValue += 13; break;
                case ACE: aces += 1; break;
            }
        }
        for (int i = 0; i < aces; ++i) {
            if (totalValue > 10) {
                totalValue += 1;
            }
            else {
                totalValue += 11;
            }
        }
        return totalValue;
    }
}
