package Model;

public class SlideEnd extends Square {
    // A flag to indicate whether this slide end square is taken by a pawn or not.
    private static boolean isTaken = false;

    // Method to set the taken status of the slide end square.
    public  void setTaken(boolean value) {
        isTaken = value;
    }
}

