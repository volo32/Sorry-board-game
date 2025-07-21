import View.SorryGameBoard;

import javax.swing.*;

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        SorryGameBoard gameBoard = new SorryGameBoard();
        gameBoard.createAndShowGUI();
    });
}