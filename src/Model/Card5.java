package Model;

import View.SorryGameBoard;

public class Card5 extends Card {

    public void pioni(Pawn p, SorryGameBoard b) {
        // Preconditions:
        // - p is not null.
        // - b is not null.
        // - The gridLabels array within b is not null.
        // - p.getPosX() and p.getPosY() are valid coordinates within the bounds of the gridLabels array.
        // - p.getCol() is a valid color within the game.

        // Move the pawn forward by 5 spaces.
        b.movePawn(p,moveValue);
        b.setTimeToChooseAgain(false);

        // Postconditions:
        // - The pawn is moved forward by 5 spaces on the game board.
        // - timeToChooseAgain is set to false.
    }

    public void kinisi(Pawn p, SorryGameBoard b) {
        pioni(p, b);
    }

    public Card5() {
        setMoveValue(5);
        setDesc("Move a pawn 5 spaces forward.");
    }
}