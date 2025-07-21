package Model;

import View.SorryGameBoard;

import static View.SorryGameBoard.currentPlayer;

public class CardSorry extends Card {
    private Pawn oPawn;

    // Method to perform the "pioni" action on a pawn.
    public void pioni(Pawn p, SorryGameBoard b) {
        // Preconditions:
        // - p is not null.
        // - b is not null.

        if (currentPlayer == 1||currentPlayer==3) {
            // Check if it's the player's turn.
            b.setTimeToChooseAgain(true);

            // Store the current pawn for swapping.
            oPawn = p;

        } else {
            // If it's not the player's turn, swap the pawns' positions.
            b.setTimeToChooseAgain(false);
            oPawn.moveTo(p.getPosX(), p.getPosY());
            p.moveTo(p.getStartX(), p.getStartY());
        }
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

    // Constructor for CardSorry class.
    public CardSorry() {
        // Preconditions: None.

        // Postconditions:
        // - Initializes a CardSorry object with a move value of 0.
        setMoveValue(0);
    }
}