package Model;

import View.SorryGameBoard;

import javax.swing.*;

public class Card8 extends Card {

    // Method to perform the "pioni" action on a pawn.
    public void pioni(Pawn p, SorryGameBoard b) {
        // Preconditions:
        // - p is not null.
        // - b is not null.

        // Show a confirmation dialog to the player.
        int userChoice = JOptionPane.showConfirmDialog(
                b,
                "Do you want to move this pawn 8 spaces ahead? If you choose no, you draw another card.",
                "An 8 was drawn...",
                JOptionPane.YES_NO_OPTION
        );

        if (userChoice == JOptionPane.YES_OPTION) {
            // If the player chooses yes, advance the pawn by 8 spaces.
            b.movePawn(p,moveValue);
            // Set the "timeToChooseAgain" flag to false to indicate the end of pawn selection.
            b.setTimeToChooseAgain(false);
        } else {
            // If the player chooses no, set the "timeToChooseAgain" flag to true to draw another card.
            b.setTimeToChooseAgain(true);
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

    // Constructor for Card8 class.
    public Card8() {
        // Preconditions: None.

        // Postconditions:
        // - Initializes a Card8 object with a move value of 8.
        // - Sets the description of the card to "Move a pawn 8 spaces ahead or draw again..."
        setMoveValue(8);
        setDesc("Move a pawn 8 spaces ahead or draw again...");
    }
}
