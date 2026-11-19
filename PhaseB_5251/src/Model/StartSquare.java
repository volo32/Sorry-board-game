package Model;

public class StartSquare extends Square {
    // A flag to indicate whether this start square is taken by a pawn or not.
    private static boolean isTaken = false;

    // Method to set the taken status of the start square.
    public  void setTaken(boolean value) {
        isTaken = value;
    }
}