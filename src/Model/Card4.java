package Model;

import View.SorryGameBoard;

public class Card4 extends Card {

    // Method to perform the "pioni" action on a pawn.
    public void pioni(Pawn p, SorryGameBoard b) {
        // Preconditions:
        // - p is not null.
        // - b is not null.

        for (int move = 0; move < getMoveValue(); move++) {
            if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() == 0) && (p.getPosY() > 0) && (p.getPosY() < 15)) {
                // Postcondition: Move the pawn one space to the right if it's on a TrackSquare at the top row.
                p.moveTo(p.getPosX(), p.getPosY() + 1);
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() == 0) && (p.getPosY() == 0)) {
                // Postcondition: Move the pawn one space to the right if it's on a TrackSquare at the top-left corner.
                p.moveTo(p.getPosX(), p.getPosY() + 1);
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() == 15) && (p.getPosY() == 15)) {
                // Postcondition: Move the pawn one space to the left if it's on a TrackSquare at the bottom-right corner.
                p.moveTo(p.getPosX(), p.getPosY() - 1);
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() == 15) && (p.getPosY() == 0)) {
                // Postcondition: Move the pawn one space up if it's on a TrackSquare at the bottom-left corner.
                p.moveTo(p.getPosX() - 1, p.getPosY());
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() > 0) && (p.getPosX() < 15) && (p.getPosY() == 0)) {
                // Postcondition: Move the pawn one space up if it's on a TrackSquare at the leftmost column.
                p.moveTo(p.getPosX() - 1, p.getPosY());
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() == 15) && (p.getPosY() < 15) && (p.getPosY() > 0)) {
                // Postcondition: Move the pawn one space to the left if it's on a TrackSquare at the bottom row.
                p.moveTo(p.getPosX(), p.getPosY() - 1);
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() > 0) && (p.getPosX() < 15) && (p.getPosY() == 15)) {
                // Postcondition: Move the pawn one space down if it's on a TrackSquare at the rightmost column.
                p.moveTo(p.getPosX() + 1, p.getPosY());
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() == 0) && (p.getPosY() == 15)) {
                // Postcondition: Move the pawn one space down if it's on a TrackSquare at the top-right corner.
                p.moveTo(p.getPosX() + 1, p.getPosY());
            }
        }

        // Postcondition: Set the "timeToChooseAgain" flag in the game board to false.
        b.setTimeToChooseAgain(false);
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

    // Constructor for Card4 class.
    public Card4() {
        // Preconditions: None.

        // Postconditions:
        // - Initializes a Card4 object with a move value of 4.
        // - Sets the description of the card to "Move one of your pawns 4 spaces BACKWARDS..."
        setMoveValue(4);
        setDesc("Move one of your pawns 4 spaces BACKWARDS...");
    }
}
