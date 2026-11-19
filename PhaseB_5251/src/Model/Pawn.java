package Model;

import javax.swing.*;
import java.awt.*;

public class Pawn extends JLabel {
    private int posX;
    private int posY;
    private int startX;
    private int startY;
    private boolean facingForward;
    private int color;

    // Direction enum inside Pawn class
    public enum Direction {
        RIGHT, DOWN, LEFT, UP
    }

    // Attribute to store the current direction of the pawn
    private Direction currentDirection = Direction.RIGHT;

    // Default constructor.
    public Pawn() {

    }

    // Constructor with an ImageIcon for the pawn's image.
    public Pawn(ImageIcon imageIcon) {
        super(resizeIcon(imageIcon, 40, 40));
        setSize(40, 40);
    }

    // Helper method to resize the ImageIcon.
    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage(); // Convert ImageIcon to Image
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Scale it to 40x40
        return new ImageIcon(resizedImage); // Convert back to ImageIcon
    }

    // Method to set direction
    public void setDirection(Direction newDirection) {
        this.currentDirection = newDirection;
    }

    // Method to get direction
    public Direction getDirection() {
        return this.currentDirection;
    }

    public void moveTo(int x, int y) {
        setPosX(x);
        setPosY(y);
        setLocation(x * 40, y * 40);
    }

    // Set the initial position of the pawn.
    private void setInitialPosition() {
        posX = (startX < 0) ? 0 : Math.min(startX, 15);
        posY = (startY < 0) ? 0 : Math.min(startY, 15);
        setLocation(posX * 40, posY * 40);
    }

    // Method to move the pawn to a specific position (x, y).

    public void updateLocation() {
        setLocation(posX * 40, posY * 40);
    }
    // Set the X position of the pawn.
    public void setPosX(int x) {
        posX = (x < 0) ? 0 : Math.min(x, 15);
    }

    // Set the Y position of the pawn.
    public void setPosY(int y) {
        posY = (y < 0) ? 0 : Math.min(y, 15);
    }

    // Get the X position of the pawn.
    public int getPosX() {
        return posX;
    }

    // Get the Y position of the pawn.
    public int getPosY() {
        return posY;
    }

    // Set the color of the pawn.
    public void setCol(int c) {
        color = c;
    }

    // Get the color of the pawn.
    public int getCol() {
        return color;
    }

    // Get the starting X position of the pawn.
    public int getStartX() {
        return startX;
    }

    // Get the starting Y position of the pawn.
    public int getStartY() {
        return startY;
    }

    // Set whether the pawn is facing forward.
    public void setFacingForward(boolean b) {
        facingForward = b;
    }

    // Check if the pawn is facing forward.
    public boolean getFacingForward() {
        return facingForward;
    }

    // Override the toString method to provide a customized string representation of the pawn.
    @Override
    public String toString() {
        return "Pawn owned by Player #" + (color + 1) + " at (" + getPosX() + "," + getPosY() + ")";
    }

    // Set the starting X position of the pawn.
    public void setStartX(int x) {
        startX = x;
    }
    public boolean isAtStartPosition() {
        return this.posX == this.startX && this.posY == this.startY;
    }

    // Set the starting Y position of the pawn and initialize its position.
    public void setStartY(int y) {
        startY = y;
        setInitialPosition();
    }
}