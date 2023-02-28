package components.sac;

import components.tuiles.TuileDominos;

public class SacDominos extends Sac {

	public SacDominos(int capacite) {
		tuiles = new TuileDominos[capacite];
		super.capacite = capacite;
		super.nbTuiles = 0;
		remplir();
		shuffle();
	}
	
	private void remplir() {
		while(!estPlein()) {
            TuileDominos t = new TuileDominos();
            ajouterTuile(t);
        }
	}
	
	// Surcharge
	public void ajouterTuile(TuileDominos t) {
		if(t == null) return;
		if(size() < capacite) {
            for(int i = 0; i < nbTuiles; i++) {
                if(t.equals(tuiles[i])) {
                    System.out.println("La tuile est déja présente dans le sac");
                    return;
                }
            }
            super.ajouterTuile(t);
        } else {
            System.out.println("Le sac est plein (" + this.size() + " components.tuiles)");
        }
	}

}
