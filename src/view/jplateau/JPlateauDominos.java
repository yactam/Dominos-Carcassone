package view.jplateau;

import view.jtuile.JTuile;
import view.jtuile.JTuileDominos;

import java.awt.*;

public class JPlateauDominos extends JPlateau {

    public JPlateauDominos(int nbLins, int nbCols) {
        initTuiles(nbLins, nbCols);
        setLayout(null);
    }

    private void initTuiles(int lins, int cols) {
        tuiles = new JTuileDominos[lins][cols];
        for(int i = 0; i < lins; i++) {
            for(int j = 0; j < cols; j++) {
                int x = (JTuile.TUILE_WIDTH + GAP) * j;
                // indices de la tuile suivante sur le plateau la toute premiéres étant 0 0
                int y = (JTuile.TUILE_HEIGHT + GAP) * i;
                tuiles[i][j] = new JTuileDominos();
                add(tuiles[i][j]);
                tuiles[i][j].setLocation(x, y);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
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
