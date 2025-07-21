package Model;

import View.SorryGameBoard;

public class Card1 extends Card {

    // Method to perform the "pioni" action on a pawn.
    public void pioni(Pawn p, SorryGameBoard b) {
        // Preconditions:
        // - p is not null.
        // - b is not null.
        // - The gridLabels array within b is not null.
        // - p.getPosX() and p.getPosY() are valid coordinates within the bounds of the gridLabels array.
        // - p.getCol() is a valid color within the game.

        Square[][] gridLabels = b.gridLabels();

        if (gridLabels[p.getPosX()][p.getPosY()] instanceof StartSquare) {
            if (p.getCol() == SorryGameBoard.getYellow()) {
                // Postcondition: Move p to position (4, 0) on the game board.
                p.moveTo(4, 0);
            } else if (p.getCol() == SorryGameBoard.getRed()) {
                // Postcondition: Move p to position (11, 15) on the game board.
                p.moveTo(11, 15);
            } else {
                // Postcondition: Move p forward by 1 space on the game board and set timeToChooseAgain to false.
                b.movePawn(p,moveValue);
                b.setTimeToChooseAgain(false);
            }
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

    // Constructor for Card1 class.
    public Card1() {
        // Preconditions: None.

        // Postconditions:
        // - Initializes a Card1 object with a description of "move a pawn 1 space forward."
        // - Sets the move value to 1.
        setDesc("move a pawn 1 space forward.");
        setMoveValue(1);
    }
}