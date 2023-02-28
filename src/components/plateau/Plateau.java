package components.plateau;

import components.tuiles.Tuile;

import java.io.Serializable;

public abstract class Plateau implements Serializable {
	
	Tuile[][] cases; // visibilité package

    public int nbLin() {
        return cases.length;
    }

    public int nbCol() {
        return cases[0].length;
    }

    public boolean estDansLimites(int i, int j) {
        return (((i >= 0) && i < cases.length) && ((j >= 0) && j < cases[0].length));
    }
    public Tuile getTuile(int i, int j) {
        if(estDansLimites(i, j)) return cases[i][j];
        return null;
    }

    public void ajouter(Tuile t, int i, int j) {
        if(estDansLimites(i, j)) cases[i][j] = t;
    }

}
