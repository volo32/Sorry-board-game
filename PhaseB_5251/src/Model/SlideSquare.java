package Model;

public class SlideSquare extends Square {
    // A flag to indicate whether this slide square is taken by a pawn or not.
    private boolean taken;

    // Default constructor.
    public SlideSquare() {
    }

    // Set the taken status of the slide square.
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    // Get the taken status of the slide square.
    public boolean getTaken() {
        return taken;
    }
}