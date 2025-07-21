package Model;

import View.SorryGameBoard;

public class Card2 extends Card
{

    public void pioni( Pawn p, SorryGameBoard b )
    {
        if ( b.gridLabels()[p.getPosX()][p.getPosY()] instanceof StartSquare )
        {
            if ( p.getCol() == SorryGameBoard.getYellow() )
            {
                p.moveTo(4,0);
            }
            if ( p.getCol() == SorryGameBoard.getRed() )
            {
                p.moveTo(11,15);
            }
        }
        else
        {
            b.movePawn(p,moveValue);
            b.setTimeToChooseAgain( false );
        }
    }

    public void kinisi( Pawn p, SorryGameBoard b)
    {
        pioni( p, b );
    }

    public Card2() {
        setMoveValue(2);
        setDesc("Move a pawn from Start or move a\npawn 2 spaces forward.  Draw again after moving...");
    }

}
