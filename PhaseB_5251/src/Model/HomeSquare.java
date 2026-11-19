package Model;

public class HomeSquare extends Square {
    // A flag to indicate whether this home square is taken by a pawn or not.
    private static boolean isTaken = false;

    // Method to set the taken status of the home square.
    public void setTaken(boolean value) {
        isTaken = value;
    }
}