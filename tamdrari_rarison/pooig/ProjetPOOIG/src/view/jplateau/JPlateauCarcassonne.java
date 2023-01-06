package view.jplateau;

import view.jtuile.JTuile;
import view.jtuile.JTTuileCarcassonne;

import java.awt.*;

public class JPlateauCarcassonne extends JPlateau {

    public JPlateauCarcassonne(int nbLins, int nbCols) {
        initTuiles(nbLins, nbCols);
        setLayout(null);
    }

    private void initTuiles(int lins, int cols) {
        tuiles = new JTTuileCarcassonne[lins][cols];
        for(int i = 0; i < lins; i++) {
            for(int j = 0; j < cols; j++) {
                int x = (JTuile.TUILE_WIDTH + GAP) * j;
                int y = (JTuile.TUILE_HEIGHT + GAP) * i;
                tuiles[i][j] = new JTTuileCarcassonne();
                add(tuiles[i][j]);
                tuiles[i][j].setLocation(x, y);
            }
        }
    }

    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        for(int i = 0; i < tuiles.length; i++) {
            for(int j = 0; j < tuiles[0].length; j++) {
                if(plateau != null) {
                    tuiles[i][j].setTuile(plateau.getTuile(i, j));
                }
                tuiles[i][j].paintComponent(g);
            }
        }
    }
}
