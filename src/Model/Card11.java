package Model;

import View.SorryGameBoard;

import javax.swing.*;

public class Card11 extends Card {
    private int tempX;
    private int tempY;
    private Pawn oPawn;

    // Method to perform the "pioni" action on a pawn.
    public void pioni(Pawn p, SorryGameBoard b) {
        // Preconditions:
        // - p is not null.
        // - b is not null.


            // Check if it's the player's turn.
            int userChoice = JOptionPane.showConfirmDialog(
                    b,
                    "Swap one of your pawns with an opposing pawn? If you choose no, you can move a pawn 11 spaces.",
                    "An 11 was drawn...",
                    JOptionPane.YES_NO_OPTION
            );
            if (userChoice == JOptionPane.YES_OPTION) {
                // If the player chooses to swap pawns, set the "timeToChooseAgain" flag to true.
                b.setTimeToChooseAgain(true);

                // Store the current pawn and its position for swapping.
                oPawn = p;
                tempX = p.getPosX();
                tempY = p.getPosY();

            } else {
                // If the player chooses not to swap, advance the pawn by 11 spaces.
                b.movePawn(p,moveValue);
            }

            // If it's not the player's turn, swap the pawns' positions.
            b.setTimeToChooseAgain(false);
            oPawn.moveTo(p.getPosX(), p.getPosY());
            p.moveTo(tempX, tempY);
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

    // Constructor for Card11 class.
    public Card11() {
        // Preconditions: None.

        // Postconditions:
        // - Initializes a Card11 object with a move value of 11.
        // - Sets the description of the card to "Move 11 spaces forward or switch one of your pawns with an opposing player's pawn..."
        setMoveValue(11);
        setDesc("Move 11 spaces forward or switch one of your pawns with an opposing player's pawn...");
    }
}