package components.sac;

import components.tuiles.Tuile;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

public abstract class Sac implements Serializable {

	protected Tuile[] tuiles;
	protected int nbTuiles;
	protected int capacite;
    
    public boolean estVide() {
        return nbTuiles == 0;
    }
    public boolean estPlein() {
        return nbTuiles == capacite;
    }
    
    public int size() {
    	return nbTuiles;
    }
    
    public void shuffle() {
    	Collections.shuffle(Arrays.asList(tuiles));
    }
    
    public Tuile retirer() {
        if(!estVide()) {
            return tuiles[--nbTuiles];
        }
        System.out.println("Le sac est vide !");
        return null;
    }
    
    public void ajouterTuile(Tuile t) {
    	if(!estPlein()) {
    		tuiles[nbTuiles++] = t;
    	} else {
    		System.out.println("Le sac est plein (" + nbTuiles + " components.tuiles)");
    	}
    }
    
}
