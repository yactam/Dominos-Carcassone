package view.gameview;

import components.tuiles.Partisan;
import view.jgameinfo.JGameInfo;
import view.jplateau.JPlateauCarcassonne;
import view.jtuile.JPartisan;
import view.jtuile.JTTuileCarcassonne;

import javax.swing.*;
import java.awt.*;

public class GameViewCarcassonne extends GameView {

    private final JPartisan jPartisan;
    public GameViewCarcassonne(int nbJoueurs, int nbJoueursA) {
        super(nbJoueurs, nbJoueursA, JGameInfo.Jeu.CARCASSONE);
        jPlateau = new JPlateauCarcassonne(NB_LIGNES, NB_COLS);
        jTuile = new JTTuileCarcassonne();
        jPartisan = new JPartisan();
        reposition();
        add(jPartisan);
        add(jPlateau);
        add(jTuile);
        style();

    }

    private void reposition() {
        jPartisan.setLocation((int) (0.7 * getWidth() + getInsets().left), (int) (0.8 * getHeight() + 10));
        jPlateau.setLocation((int) (0.2 * getWidth()), 0);
        jPlateau.setSize((int) (0.8 * getWidth()), (int) (0.8 * getHeight()));
        jTuile.setLocation((int) (0.5 * getWidth() + getInsets().left), (int) (0.8 * getHeight() + 10));
    }

    private void style() {
        piocher.setIcon(new ImageIcon("src/Resources/images/Carcassonne/back.jpg"));
        turnLeft.setIcon(new ImageIcon("src/Resources/images/boutonsCarcassonne/g.png"));
        turnRight.setIcon(new ImageIcon("src/Resources/images/boutonsCarcassonne/d.png"));
        abandonner.setIcon(new ImageIcon("src/Resources/images/boutonsCarcassonne/abandonner.png"));
        passerTour.setIcon(new ImageIcon("src/Resources/images/boutonsCarcassonne/passer.png"));
        setBackground(new Color(67, 154, 151));
        setGameInfoBackground(new Color(67, 154, 151));
    }

    public JPartisan getjPartisan() {
        return jPartisan;
    }

    public void setPartisan(Partisan p) {
        jPartisan.setPartisan(p);
    }


}
