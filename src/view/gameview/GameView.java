package view.gameview;

import components.plateau.Plateau;
import components.tuiles.Tuile;
import player.Joueur;
import view.jgameinfo.JGameInfo;
import view.jplateau.JPlateau;
import view.jtuile.JTuile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class GameView extends JPanel {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH  = (int) screenSize.getWidth();
    public static final int HEIGHT = (int) screenSize.getHeight();
    public static final int NB_LIGNES = (int) ((0.8 * HEIGHT) / (JTuile.TUILE_WIDTH + JPlateau.GAP));

    public static final int NB_COLS = (int) ((0.8 * WIDTH) / (JTuile.TUILE_HEIGHT + JPlateau.GAP));
    JPlateau jPlateau;
    private JGameInfo gameInfo;
    JTuile jTuile;
    JButton abandonner;
    JButton passerTour;
    JButton turnLeft;
    JButton turnRight;
    JButton piocher;


    public GameView(int nbJoueurs, int nbJoueursA, JGameInfo.Jeu jeu) {
        initComponents(nbJoueurs, nbJoueursA, jeu);

        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        add(gameInfo);
        add(abandonner);
        add(passerTour);
        add(turnLeft);
        add(turnRight);
        add(piocher);
        reposition();
    }

    void initComponents(int nbJoueurs, int nbJoueursA, JGameInfo.Jeu jeu) {
        gameInfo = new JGameInfo(nbJoueurs, nbJoueursA, jeu);
        piocher = new JButton();
        abandonner = new JButton();
        passerTour = new JButton();
        turnLeft = new JButton();
        turnRight = new JButton();
        personnaliseButton();
        enableControl(false);
    }

    private void personnaliseButton() {
        abandonner.setBackground(new Color(255, 255, 255)); abandonner.setOpaque(false); abandonner.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        passerTour.setBackground(new Color(255, 255, 255)); passerTour.setOpaque(false); passerTour.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        turnLeft.setBackground(new Color(255, 255, 255)); turnLeft.setOpaque(false); turnLeft.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        turnRight.setBackground(new Color(255, 255, 255)); turnRight.setOpaque(false); turnRight.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
    }

    private void reposition() {
        gameInfo.setLocation(0,0);
        gameInfo.setSize(new Dimension((int) (0.2 * getWidth()), (int) (0.8 * getHeight())));
        piocher.setSize(JTuile.TUILE_WIDTH, JTuile.TUILE_HEIGHT);
        piocher.setLocation(getWidth() - JTuile.TUILE_WIDTH - 10, (int) (0.8 * getHeight()));
        turnLeft.setSize(getWidth() / 12, getHeight()/24);
        turnLeft.setLocation((getWidth() / 2) - JTuile.TUILE_WIDTH - (getWidth() / 10), (int) (0.8 * getHeight()) + 20);
        turnRight.setSize(getWidth() / 12, getHeight()/24);
        turnRight.setLocation(getWidth() / 2 + 2 * JTuile.TUILE_WIDTH, (int) (0.8 * getHeight()) + 20);
        abandonner.setSize(getWidth() / 12, getHeight() / 24);
        abandonner.setLocation(95, (int) (0.8 * getHeight()) + 10);
        passerTour.setSize(getWidth() / 12, getHeight() / 24);
        passerTour.setLocation(95 , (int) (0.8 * getHeight()) + getHeight() / 24 + 25);
    }

    public Joueur[] getJoueurs() {
        return gameInfo.getJoueurs();
    }

    public JPlateau getjPlateau() {
        return jPlateau;
    }

    public void setPlateau(Plateau jPlateau) {
        this.jPlateau.setPlateau(jPlateau);
    }

    public void setCurrentPlayer(String name, int score) {
        gameInfo.setPlayerInfo(name, score);
    }

    public void updatePlayerInfo(int i, int score) {
        gameInfo.setGameInfo(i, score);
    }

    public void setTuile(Tuile t) {
        jTuile.setTuile(t);
    }
    public void turnLeft_addListener(ActionListener actionListener) {
        turnLeft.addActionListener(actionListener);
    }

    public void enableControl(boolean enabled) {
        turnRight.setEnabled(enabled);
        turnLeft.setEnabled(enabled);
        abandonner.setEnabled(enabled);
    }

    public void enable_piocher(boolean enabled) {
        piocher.setEnabled(enabled);
    }

    public void turnRight_addListener(ActionListener actionListener) {
        turnRight.addActionListener(actionListener);
    }

    public void abandonner_addActionListener(ActionListener actionListener) {
        abandonner.addActionListener(actionListener);
    }

    public void passerTour_addActionListener(ActionListener actionListener) {
        passerTour.addActionListener(actionListener);
    }

    public void piocher_addActionListener(ActionListener actionListener) {
        piocher.addActionListener(actionListener);
    }

    public JTuile getJTuile() {
        return jTuile;
    }

    void setGameInfoBackground(Color color) {
        gameInfo.setBackgroundColor(color);
    }

    public void sacVide() {
        piocher.setIcon(new ImageIcon("src/Resources/images/Dominos/empty.png"));
    }
}
