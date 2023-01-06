package view.jplateau;


import components.plateau.Plateau;
import view.jtuile.JTuile;

import javax.swing.*;

public abstract class JPlateau extends JPanel {
    JTuile[][] tuiles;
    Plateau plateau;
    public static final int GAP = 5;


    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
        repaint();
    }

    @Override
    public int getWidth() {
        return (JTuile.TUILE_WIDTH + GAP) * tuiles[0].length;
    }

    @Override
    public int getHeight() {
        return (JTuile.TUILE_HEIGHT + GAP) * tuiles.length;
    }
}
