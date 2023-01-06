package controller.menu;


import controller.game.GameController;
import controller.game.GameControllerCarcassonne;
import controller.game.GameControllerDominos;
import view.menu.HomeView;

import javax.swing.*;

public class HomeController extends JFrame {

    private final HomeView homeView;

    public HomeController() {
        homeView = new HomeView();
        
        homeView.setDominosBtnActionListener(e -> {
            GameController gameController = new GameControllerDominos(homeView.getNbJoueursDominos(),homeView.getNbJoueursADominos());
            this.dispose();
            gameController.start();
        });
        homeView.setCarcassonneBtnActionListener(e -> {
            GameController gameController = new GameControllerCarcassonne(homeView.getNbJoueursCarcassonne(), homeView.getNbJoueursACCarcassonne());
            this.dispose();
            gameController.start();
        });
        homeView.setLoadDominosBtnActionListener(e -> {
            try {
                GameControllerDominos gameController = GameControllerDominos.load();
                this.dispose();
                gameController.setVisible(true);
                gameController.start();
            } catch (Exception exception) {
                homeView.enableLoadDominos(false);
            }

        });
        homeView.setLoadCarcassonneBtnActionListener(e -> {
            try {
                GameControllerCarcassonne gameController = GameControllerCarcassonne.load();
                this.dispose();
                gameController.setVisible(true);
                gameController.start();
            } catch (Exception exception) {
                homeView.enableLoadCarcassonne(false);
            }

        });

        add(homeView);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(HomeView.WIDTH, HomeView.HEIGHT);
        setVisible(true);
    }
}
