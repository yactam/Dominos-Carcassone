package view.jtuile;

import components.tuiles.Tuile;

import javax.swing.*;
import java.awt.*;

public abstract class JTuile extends JPanel {

    Tuile tuile;
    public static final int TUILE_HEIGHT = 75;
    public static final int TUILE_WIDTH = 75;

    public JTuile() {
        initTuile();
        repaint();
    }

    private void initTuile() {
        setSize(TUILE_WIDTH, TUILE_HEIGHT);
        tuile = null;
    }

    public Tuile getTuile() {
        return tuile;
    }

    public void setTuile(Tuile t) {
        tuile = t;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    @Override
    public int getWidth() {
        return TUILE_WIDTH;
    }

    @Override
    public int getHeight() {
        return TUILE_HEIGHT;
    }
}
