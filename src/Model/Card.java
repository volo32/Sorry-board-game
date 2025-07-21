package Model;

import View.SorryGameBoard;

public class Card {
    protected int moveValue;
    private String description;

    // Method to set the description of the card.
    public void setDesc(String s) {
        // Preconditions: None.

        // Postconditions:
        // - Sets the description of the card to the provided string 's'.
        description = s;
    }

    // Method to get the description of the card.
    public String getDescription() {
        // Preconditions: None.

        // Postconditions:
        // - Returns the description of the card.
        return description;
    }

    // Method to set the move value of the card.
    public void setMoveValue(int i) {
        // Preconditions: None.

        // Postconditions:
        // - Sets the move value of the card to the provided integer 'i'.
        moveValue = i;
    }

    // Method to get the move value of the card.
    public int getMoveValue() {
        // Preconditions: None.

        // Postconditions:
        // - Returns the move value of the card.
        return moveValue;
    }

    // Method to perform the "kinisi" action on a pawn and game board.
    public void kinisi(Pawn p, SorryGameBoard b) {
        // Preconditions:
        // - p is not null.
        // - b is not null.

        // Postconditions:
        // - Calls the "advance" method on the game board 'b' with the card's move value and pawn 'p' as parameters.
        // - Sets the "timeToChooseAgain" flag in the game board to false.
        b.movePawn(p,moveValue);
        b.setTimeToChooseAgain(false);
    }

    // Method to generate a string representation of the card.
    public String toString() {
        // Preconditions: None.

        // Postconditions:
        // - Returns a string representing the class name of the card along with its move value.
        return (this.getClass() + " with moveValue = " + moveValue);
    }
}
