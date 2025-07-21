package Model;

import View.SorryGameBoard;

import javax.swing.*;

public class Card10 extends Card {

    // Method to perform the "pioni" action on a pawn.
    public void pioni(Pawn p, SorryGameBoard b) {
        // Preconditions:
        // - p is not null.
        // - b is not null.

        int userChoice = JOptionPane.showConfirmDialog(
                b,
                "Do you want to move this pawn backwards 1 space?\nChoosing no will move this pawn 10 spaces FORWARD",
                "A 10 was drawn...",
                JOptionPane.YES_NO_OPTION
        );

        if (userChoice == JOptionPane.YES_OPTION) {
            // If the player chooses yes, move the pawn 1 space backwards.
            if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() == 0) && (p.getPosY() > 0) && (p.getPosY() < 15)) {
                p.moveTo(p.getPosX(), p.getPosY() + 1);
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() == 0) && (p.getPosY() == 0)) {
                p.moveTo(p.getPosX(), p.getPosY() + 1);
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() == 15) && (p.getPosY() == 15)) {
                p.moveTo(p.getPosX(), p.getPosY() - 1);
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() == 15) && (p.getPosY() == 0)) {
                p.moveTo(p.getPosX() - 1, p.getPosY());
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() > 0) && (p.getPosX() < 15) && (p.getPosY() == 0)) {
                p.moveTo(p.getPosX() - 1, p.getPosY());
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() == 15) && (p.getPosY() < 15) && (p.getPosY() > 0)) {
                p.moveTo(p.getPosX(), p.getPosY() - 1);
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() > 0) && (p.getPosX() < 15) && (p.getPosY() == 15)) {
                p.moveTo(p.getPosX() + 1, p.getPosY());
            } else if (b.gridLabels()[p.getPosX()][p.getPosY()] instanceof TrackSquare && (p.getPosX() == 0) && (p.getPosY() == 15)) {
                p.moveTo(p.getPosX() + 1, p.getPosY());
            } else {
                // Postcondition: Move the pawn 1 space backwards.
                b.movePawn(p,moveValue);
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

    // Constructor for Card10 class.
    public Card10() {
        // Preconditions: None.

        // Postconditions:
        // - Initializes a Card10 object with a move value of 10.
        // - Sets the description of the card to "Move a pawn 10 spaces forward OR 1 space BACKWARDS..."
        setMoveValue(10);
        setDesc("Move a pawn 10 spaces forward OR 1 space BACKWARDS...");
    }
}