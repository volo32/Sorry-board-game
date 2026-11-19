package View;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Michalis
 */
public class JLayeredPaneExtension extends JLayeredPane {

    Image image;

    public JLayeredPaneExtension(Image img) {
        image=img;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
