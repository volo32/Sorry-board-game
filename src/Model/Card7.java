package Model;

import View.SorryGameBoard;

public class Card7 extends Card {

    private Pawn p1;
    private Pawn p2;
    private int p1move;
    private int p2move;

    // Method to perform the "pioni" action on a pawn.
    public void pioni(Pawn p, SorryGameBoard b) {
        // Preconditions:
        // - p is not null.
        // - b is not null.
//la8os
    }
    // Method to perform the "kinisi" action on a pawn (calls the "pioni" method).
    public void kinisi(Pawn p, SorryGameBoard b) {
        // Preconditions:
        // - p is not null.
        // - b is not null.

        // Call the "pioni" method.
        pioni(p, b);
        // Postconditions: Same as those specified for the "pioni" method.
    }

    // Constructor for Card7 class.
    public Card7() {
        // Preconditions: None.

        // Postconditions:
        // - Initializes a Card7 object with a move value of 7.
        // - Sets the description of the card to "Move a pawn 7 spaces or split 7 between two pawns..."
        setMoveValue(7);
        setDesc("Move a pawn 7 spaces or split 7 between two pawns...");
    }
}
