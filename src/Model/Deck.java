package Model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    // Store the cards in an ArrayList.
    private List<Card> cards = new ArrayList<Card>();
    private int totalCards = 0; // Counter for the total number of cards in the deck.

    // Constructor to initialize the deck with cards.
    public Deck() {
        recreateDeck(); // Create the initial deck
    }

    // Create a new deck with 4 sets of cards.
    private void recreateDeck() {
        cards.clear(); // Clear the existing cards
        totalCards = 0; // Reset the card counter

        // Add 4 sets of cards (each set contains various card types).
        for (int index = 0; index < 4; index++) {
            cards.add(new Card1());
            cards.add(new Card2());
            cards.add(new Card3());
            cards.add(new Card4());
            cards.add(new Card5());
            cards.add(new Card7());
            cards.add(new Card8());
            cards.add(new Card10());
            cards.add(new Card11());
            cards.add(new Card12());
            cards.add(new CardSorry());
            totalCards += 11; // Increment the card counter for each set (11 cards per set).
        }

        // Print a message to indicate the deck has been recreated.
        System.out.println("Deck recreated. Total cards in deck: " + totalCards);
    }

    // Get a card from the deck at a specific index.
    public Card get(int index) {
        return cards.get(index);
    }

    // Get the total number of cards in the deck.
    public int getSize() {
        return totalCards;
    }

    // Remove a card from the deck at a specific index.
    public void remove(int i) {
        cards.remove(i);
        totalCards--;
        System.out.println("Card removed from the deck. Total cards left in deck: " + totalCards);
        if (totalCards == 0) {
            recreateDeck(); // Recreate the deck when the card count reaches 0.
        }
    }
}
