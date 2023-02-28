package controller.game;

import components.tuiles.Partisan;
import components.plateau.PlateauCarcassone;
import components.sac.SacCarcassone;
import components.tuiles.Tuile;
import player.JoueurArtificielCarcassonne;
import player.JoueurCarcassonne;
import view.gameview.GameView;
import view.gameview.GameViewCarcassonne;
import view.jtuile.JPartisan;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class GameControllerCarcassonne extends GameController {

    private boolean partisanMovable;
    public GameControllerCarcassonne(int nbJoueurs, int nbJoueursA) {
        gameView = new GameViewCarcassonne(nbJoueurs, nbJoueursA);
        initGame();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(gameView);
        setSize(GameView.WIDTH, GameView.HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Carcassonne");
        setVisible(true);
    }

    @Override
    protected MouseListener mouseController() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if(isInPlateau(mouseEvent)) {
                    if(tuileMovable) {
                        Point coordinates = getCoordinate(mouseEvent);
                        Tuile t = gameView.getJTuile().getTuile();
                        int lin = (int) coordinates.getX(), col = (int) coordinates.getY();
                        if(t.corresponds(plateau, lin, col)) {
                            courant.poser(plateau, t, lin, col);
                            gameView.setPlateau(plateau);
                            int nbPoints = 10;
                            courant.gangerPoints(nbPoints);
                            gameView.setCurrentPlayer(courant.nom, courant.getNbPoints());
                            gameView.updatePlayerInfo(courant.id, courant.getNbPoints());
                            partisanMovable = true;
                            tuileMovable = false;
                            clearTuile();
                            disableControl();
                            gameView.enable_piocher(false);
                            if(sac.estVide()) {
                                winner();
                            }
                        }
                    } else if(partisanMovable) {
                        Point coordinates = getCoordinate(mouseEvent);
                        int lin = (int) coordinates.getX(), col = (int) coordinates.getY();
                        if(((JoueurCarcassonne)courant).poserPartisans(plateau, lin, col, getCote(mouseEvent))) {
                            gameView.setPlateau(plateau);
                            nextPlayer(joueurs.indexOf(courant));
                        } else {
                            resetPartisanPosition();
                        }
                    }

                } else {
                    resetTuilePosition();
                    resetPartisanPosition();
                }
            }
        };
    }

    private void initGame() {
        sac = new SacCarcassone();
        plateau = new PlateauCarcassone(GameView.NB_LIGNES, GameView.NB_COLS);
        joueurs = new ArrayList<>(List.of(gameView.getJoueurs()));
        courant = joueurs.get(0);
        Tuile initiale = sac.retirer();
        plateau.ajouter(initiale, plateau.nbLin() / 2 - 1, plateau.nbCol() / 2 - 1);
        gameView.setPlateau(plateau);
        gameView.setCurrentPlayer(courant.nom, courant.getNbPoints());
        ((GameViewCarcassonne)gameView).setPartisan(new Partisan(((JoueurCarcassonne)courant).getCouleur()));
        gameView.enable_piocher(true);
    }

    private int getCote(MouseEvent event) {
        GameViewCarcassonne gameViewCarcassone = (GameViewCarcassonne) gameView;
        int plateauX = gameViewCarcassone.getjPlateau().getX(), plateauY = gameViewCarcassone.getjPlateau().getY();
        int tuileWidth = gameView.getJTuile().getWidth() + 5, tuileHeight = gameView.getJTuile().getHeight() + 5; // 5, c'est l'espace entre les tuiles
        int y = (event.getX() - plateauX - getInsets().left) % tuileWidth;
        int x = (event.getY() - plateauY - getInsets().top) % tuileHeight;
        if(y > 50) return 2;
        if(x > 50) return 3;
        if(x < 25) return 1;
        if(y < 25) return 0;
        return 4; // Poser le partisan au milieu
    }



    @Override
    public void nextPlayer(int indexCourant) {
        courant = joueurs.get((indexCourant+1) % joueurs.size());
        clearTuile();
        resetTuilePosition();
        partisanMovable = false;
        gameView.setCurrentPlayer(courant.nom, courant.getNbPoints());
        ((GameViewCarcassonne)gameView).setPartisan(new Partisan(((JoueurCarcassonne)courant).getCouleur()));
        if(courant instanceof JoueurArtificielCarcassonne) aiPlayer();
    }

    private boolean isInPartisan(MouseEvent mouseEvent) {
        JPartisan jPartisan = ((GameViewCarcassonne)gameView).getjPartisan();
        boolean isInWidthBounds = (mouseEvent.getX() - getInsets().left >= jPartisan.getX())
                && (mouseEvent.getX() - getInsets().left <= jPartisan.getX() + jPartisan.getWidth());
        boolean isInHeightBounds= (mouseEvent.getY() - getInsets().top >= jPartisan.getY())
                && (mouseEvent.getY() - getInsets().top <= jPartisan.getY() + jPartisan.getHeight());

        return isInHeightBounds && isInWidthBounds;
    }
    @Override
    void aiPlayer() {
        Tuile t = courant.piocher(sac);
        gameView.setTuile(t);
        JoueurArtificielCarcassonne j = (JoueurArtificielCarcassonne) courant;
        if(j.coupPossible(plateau, t)) {
            courant.tourner(t, j.getNbTours());
            gameView.setTuile(t);
            int lin = j.getLigne(), col = j.getCol();
            courant.poser(plateau, t, lin, col);
            double r = Math.random();
            if(r < 0.4) {
                j.poserPartisans(plateau, lin, col, 4);
            }
            int nbPoints = 10;
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

    private void resetPartisanPosition() {
        ((GameViewCarcassonne)gameView).getjPartisan().setLocation((int) (0.7 * getWidth() + getInsets().left), (int) (0.8 * getHeight() + getInsets().top));
    }

    public static GameControllerCarcassonne load() throws IOException, ClassNotFoundException {
        GameControllerCarcassonne res = null;
        FileInputStream fileIn = new FileInputStream("saveCarcassonne.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        res = (GameControllerCarcassonne) in.readObject();
        in.close();
        fileIn.close();
        return res;
    }
}
