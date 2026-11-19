package Model;

public class SlideStart extends Square {
    // A flag to indicate whether this slide start square is taken by a pawn or not.
    private static boolean isTaken = false;

    // Method to set the taken status of the slide start square.
    public  void setTaken(boolean value) {
        isTaken = value;
    }
}