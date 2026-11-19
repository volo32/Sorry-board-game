package Model;

public class TrackSquare extends Square {
    // A flag to indicate whether this track square is taken by a pawn or not.
    private static boolean isTaken = false;

    // Method to set the taken status of the track square.
    public  void setTaken(boolean value) {
        isTaken = value;
    }
}
