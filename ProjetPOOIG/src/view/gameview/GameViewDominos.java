package view.gameview;

import view.jgameinfo.JGameInfo;
import view.jplateau.JPlateauDominos;
import view.jtuile.JTuileDominos;

import javax.swing.*;
import java.awt.*;

public class GameViewDominos extends GameView {

    public GameViewDominos(int nbJoueurs, int nbJoueursA) {
        super(nbJoueurs, nbJoueursA, JGameInfo.Jeu.DOMINOS);
        jPlateau = new JPlateauDominos(NB_LIGNES, NB_COLS);
        jTuile = new JTuileDominos();
        jPlateau.setLocation((int) (0.2 * getWidth()), 0);
        jPlateau.setSize((int) (0.8 * getWidth()), (int) (0.8 * getHeight()));
        jTuile.setLocation((int) (0.5 * getWidth()), (int) (0.8 * getHeight()) + 10);
        add(jPlateau);
        add(jTuile);
        style();

    }

    private void style() {
        piocher.setIcon(new ImageIcon("src/Resources/images/Dominos/dominos.jpg"));
        turnLeft.setIcon(new ImageIcon("src/Resources/images/boutonsDominos/g.png"));
        turnRight.setIcon(new ImageIcon("src/Resources/images/boutonsDominos/d.png"));
        abandonner.setIcon(new ImageIcon("src/Resources/images/boutonsDominos/abandonner.png"));
        passerTour.setIcon(new ImageIcon("src/Resources/images/boutonsDominos/passer.png"));
        setBackground(Color.DARK_GRAY);
        setGameInfoBackground(Color.DARK_GRAY);
    }
}
