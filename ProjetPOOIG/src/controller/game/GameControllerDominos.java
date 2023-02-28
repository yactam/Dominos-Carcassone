package controller.game;

import components.plateau.PlateauDominos;
import components.sac.SacDominos;
import components.tuiles.Tuile;
import components.tuiles.TuileDominos;
import view.gameview.GameView;
import view.gameview.GameViewDominos;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class GameControllerDominos extends GameController {

    public GameControllerDominos(int nbJoueurs, int nbJoueursA) {
        gameView = new GameViewDominos(nbJoueurs, nbJoueursA);
        initGame();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(gameView);
        setSize(GameView.WIDTH, GameView.HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Nominos");
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
                        if(t != null && t.corresponds(plateau, lin, col)) {
                            plateau.ajouter(t, lin, col);
                            gameView.setPlateau(plateau);
                            tuileMovable = false;
                            boolean c0 = plateau.getTuile(lin, col-1) != null, c1 = plateau.getTuile(lin-1, col) != null,
                                    c2 = plateau.getTuile(lin, col+1) != null, c3 = plateau.getTuile(lin+1, col) != null;
                            int nbPoints = ((TuileDominos) t).nbPoints(c0, c1, c2, c3);
                            courant.gangerPoints(nbPoints);
                            gameView.setCurrentPlayer(courant.nom, courant.getNbPoints());
                            gameView.updatePlayerInfo(courant.id, courant.getNbPoints());
                            nextPlayer(joueurs.indexOf(courant));
                            if(sac.estVide()) {
                                winner();
                            }
                        }
                    }
                } else {
                    resetTuilePosition();
                }
                repaint();
            }
        };
    }

    private void initGame() {
        sac = new SacDominos(60);
        plateau = new PlateauDominos(GameView.NB_LIGNES, GameView.NB_COLS);
        joueurs = new ArrayList<>(List.of(gameView.getJoueurs()));
        courant = joueurs.get(0);
        Tuile initiale = sac.retirer();
        plateau.ajouter(initiale, plateau.nbLin() / 2 - 1, plateau.nbCol() / 2 - 1);
        gameView.setPlateau(plateau);
        gameView.setCurrentPlayer(courant.nom, courant.getNbPoints());
        gameView.enable_piocher(true);
    }

    public static GameControllerDominos load() throws IOException, ClassNotFoundException {
        GameControllerDominos res = null;
        FileInputStream fileIn = new FileInputStream("saveDominos.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        res = (GameControllerDominos) in.readObject();
        in.close();
        fileIn.close();
        return res;
    }
}
