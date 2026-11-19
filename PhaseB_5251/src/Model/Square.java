package Model;


import javax.swing.*;

// Abstract base class for different square types in the Sorry! board game.
public abstract class Square extends JLabel {
    // The color associated with the square (if applicable).
    private int color;

    // A flag to indicate whether this square is taken by a pawn or not.
    private boolean taken;

    // Set the color associated with the square.
    public void setCol(int c) {
        color = c;
    }

    // Get the color associated with the square.
    public int getCol() {
        return color;
    }

    // Set the taken status of the square.
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    // Get the taken status of the square.
    public boolean getTaken() {
        return taken;
    }
}
