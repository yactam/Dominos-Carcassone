package components.plateau;

import components.tuiles.TuileCarcassonne;

public class PlateauCarcassone extends Plateau {

    public PlateauCarcassone(int ligne, int col) {
        cases = new TuileCarcassonne[ligne][col];
    }
}
