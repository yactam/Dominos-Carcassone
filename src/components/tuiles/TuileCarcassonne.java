package components.tuiles;

public class TuileCarcassonne extends Tuile {
	private Partisan partisan;
    private int cotePartisan;
    private final boolean bouclier;
    private boolean abbaye;
    private boolean carrefour;
    
    public TuileCarcassonne(Paysage c0, Paysage c1, Paysage c2, Paysage c3, boolean bouclier) {
        cotes = new CoteCarcassonne[4];
        cotes[0] = new CoteCarcassonne(c0);
        cotes[1] = new CoteCarcassonne(c1);
        cotes[2] = new CoteCarcassonne(c2);
        cotes[3] = new CoteCarcassonne(c3);
        this.bouclier = bouclier;
        abbayeOuCarrefour(); // Pour initialiser les attributs abbaye et carrefour
    }

    private void abbayeOuCarrefour() {
        int nbPres = 0, nbChemins = 0;
        for(Cote p : cotes) {
            if(((CoteCarcassonne) p).estPres()) nbPres++;
            else if(((CoteCarcassonne) p).estChemin()) nbChemins++;
        }
        if(nbPres > 3) abbaye = true;
        else if(nbChemins > 2) carrefour = true;
    }
    
    public boolean estAbbaye() {
        return abbaye;
    }

    public boolean estCarrefour() {
        return carrefour;
    }
    
    public boolean aUnBouclier() {
		return bouclier;
	}

    /**
     * @param cote     le côté où on va poser le partisan 0 est gauche 1 est haut 2 est droite 3 est bas et 4 est au milieu
     * @param partisan le partisan qu'on veut ajouter à la tuile
     */
    public void ajouterPartisan(Partisan partisan, int cote) {
        if(aucunPartisan()) {
            this.partisan = partisan;
            cotePartisan = cote;
        } else {
            System.out.println("La tuile a deja un partisan");
        }
    }

    /**
     *
     * @return true si la tuile a aucun partisan
     */
    private boolean aucunPartisan() {
        return partisan == null;
    }

    public Partisan getPartisan() {
        if(partisan == null) return null;
        return new Partisan(partisan.couleur());
    }

    public int getCotePartisan() {
        return cotePartisan;
    }

    @Override
    public String toString() {
        return cotes[0].toString() + "_" + cotes[1].toString() + "_" + cotes[2].toString() + "_" + cotes[3].toString() + (bouclier?"_b":"");
    }

}
