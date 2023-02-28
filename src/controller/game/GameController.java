package controller.game;


import components.plateau.Plateau;
import components.sac.Sac;
import components.tuiles.Tuile;
import components.tuiles.TuileDominos;
import player.Joueur;
import player.JoueurArtificiel;
import view.gameview.GameView;
import view.jplateau.JPlateau;
import view.jtuile.JTuile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public abstract class GameController extends JFrame implements Serializable {

    GameView gameView;
    Plateau plateau;
    Sac sac;
    ArrayList<Joueur> joueurs;
    Joueur courant;
    boolean tuileMovable;

    public void start() {
        addMouseListener(mouseController());
        if(courant instanceof JoueurArtificiel) {
            aiPlayer();
        }
        gameView.piocher_addActionListener(e -> {
            tuileMovable = true;
            gameView.enable_piocher(false);
            gameView.setTuile(courant.piocher(sac));
            enableControl();
            if(sac.estVide()) {
                gameView.sacVide();
            }
            repaint();
        });
        gameView.turnLeft_addListener(e -> {
            Tuile t = gameView.getJTuile().getTuile();
            courant.tourner(t, 3);
            gameView.setTuile(t);
            repaint();
        });
        gameView.turnRight_addListener(e -> {
            Tuile t = gameView.getJTuile().getTuile();
            courant.tourner(t, 1);
            gameView.setTuile(t);
            repaint();
        });
        gameView.abandonner_addActionListener(e -> {
            courant.abandonner();
            int current_index = joueurs.indexOf(courant);
            joueurs.remove(courant);
            nextPlayer(current_index-1);
            clearTuile();
            resetTuilePosition();
            repaint();
            if(joueurs.size() == 1) {
                winner();
            }
        });
        gameView.passerTour_addActionListener(e -> {
            nextPlayer(joueurs.indexOf(courant));
            clearTuile();
            resetTuilePosition();
            repaint();
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                save();
            }
        });
    }

    public boolean isInTuile(MouseEvent mouseEvent) {
        JTuile jTuile = gameView.getJTuile();
        boolean isInWidthBounds = (mouseEvent.getX() - getInsets().left >= jTuile.getX())
                && (mouseEvent.getX() - getInsets().left <= jTuile.getX() + jTuile.getWidth());
        boolean isInHeightBounds= (mouseEvent.getY() - getInsets().top >= jTuile.getY())
                && (mouseEvent.getY() - getInsets().top <= jTuile.getY() + jTuile.getHeight());

        return isInHeightBounds && isInWidthBounds;
    }

    boolean isInPlateau(MouseEvent mouseEvent) {
        JPlateau jPlateau = gameView.getjPlateau();

        boolean isInWidthBounds = (mouseEvent.getX() - getInsets().left >= jPlateau.getX())
                && (mouseEvent.getX() - getInsets().left <= jPlateau.getX() + jPlateau.getWidth());
        boolean isInHeightBounds= (mouseEvent.getY() - getInsets().top >= jPlateau.getY())
                && (mouseEvent.getY() - getInsets().top <= jPlateau.getY() + jPlateau.getHeight());

        return isInWidthBounds && isInHeightBounds;
    }

    /*@Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if(tuileMovable) {
            Point current_point = mouseEvent.getPoint();
            int newX = (int) (current_point.getX() - getInsets().left);
            int newY = (int) (current_point.getY() - getInsets().top);
            gameView.getJTuile().setLocation(newX, newY);
            repaint();
        }
    }*/


    Point getCoordinate(MouseEvent event) {
        int plateauX = gameView.getjPlateau().getX(), plateauY = gameView.getjPlateau().getY();
        int tuileWidth = gameView.getJTuile().getWidth() + 5, tuileHeight = gameView.getJTuile().getHeight() + 5; // 5, c'est l'espace entre les components ;tuiles
        int y = (event.getX() - plateauX - getInsets().left) / tuileWidth;
        int x = (event.getY() - plateauY - getInsets().top) / tuileHeight;
        return new Point(x, y);
    }

    void aiPlayer() {
        Tuile t = courant.piocher(sac);
        gameView.setTuile(t);
        JoueurArtificiel j = (JoueurArtificiel) courant;
        if(j.coupPossible(plateau, t)) {
            courant.tourner(t, j.getNbTours());
            gameView.setTuile(t);
            int lin = j.getLigne(), col = j.getCol();
            courant.poser(plateau, t, lin, col);
            boolean c0 = plateau.getTuile(lin, col-1) != null, c1 = plateau.getTuile(lin-1, col) != null,
                    c2 = plateau.getTuile(lin, col+1) != null, c3 = plateau.getTuile(lin+1, col) != null;
            int nbPoints = ((TuileDominos) t).nbPoints(c0, c1, c2, c3);
            courant.gangerPoints(nbPoints);
            gameView.setCurrentPlayer(courant.nom, courant.getNbPoints());
            gameView.updatePlayerInfo(courant.id, courant.getNbPoints());
            gameView.setPlateau(plateau);
        } else {
            System.out.println("impossible");
        }
        repaint();
        if(sac.estVide()) {
            winner();
            return;
        }
        nextPlayer(joueurs.indexOf(courant));
    }

    public void nextPlayer(int indexCourant) {
        courant = joueurs.get((indexCourant+1) % joueurs.size());
        clearTuile();
        resetTuilePosition();
        gameView.setCurrentPlayer(courant.nom, courant.getNbPoints());
        tuileMovable = false;
        if(courant instanceof JoueurArtificiel) aiPlayer();
    }

    protected MouseListener mouseController() {
        return new MouseAdapter() {
        };
    }

    void clearTuile() {
        gameView.setTuile(null);
        disableControl();
        gameView.enable_piocher(true);
    }

    void resetTuilePosition() {
        gameView.getJTuile().setLocation((int) (0.5 * getWidth() + getInsets().left), (int) (0.8 * getHeight() + getInsets().top));
    }

    private void enableControl() {
        gameView.enableControl(true);
    }
    void disableControl() {
        gameView.enableControl(false);
    }

    void winner() {
        JPanel winnerPanel = new JPanel();
        winnerPanel.setBackground(Color.gray);
        winnerPanel.setLayout(new FlowLayout());
        Joueur winner = getWinner();
        JLabel winnerText = new JLabel(winner + " a gagné");
        winnerText.setFont(new Font("Serif", Font.BOLD, 75));

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/Resources/images/menu/quitter.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JButton quitter = new JButton(new ImageIcon(image));
        quitter.setBackground(new Color(0, 0, 0));
        quitter.setOpaque(false);
        quitter.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0)));
        quitter.addActionListener(e -> System.exit(0));
        winnerPanel.add(winnerText);
        winnerPanel.add(quitter);
        this.remove(gameView);
        this.add(winnerPanel);

        this.setVisible(true);

    }

    private Joueur getWinner() {
        Joueur res = joueurs.get(0);
        for(Joueur joueur : joueurs) {
            if(joueur.getNbPoints() > res.getNbPoints()) res = joueur;
        }
        return res;
    }

    public void save() {
        try {
            FileOutputStream fileOut;
            if(this instanceof GameControllerCarcassonne) {
                fileOut = new FileOutputStream("saveCarcassonne.ser");
            } else {
                fileOut = new FileOutputStream("saveDominos.ser");
            }
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

