package player;

import components.plateau.Plateau;
import components.tuiles.Tuile;
import components.tuiles.TuileDominos;

import java.io.Serializable;
import java.util.Random;

public class JoueurArtificiel extends Joueur {
    private Instruction choix;

    public JoueurArtificiel() {
        super("Zobo " + new Random().nextInt(1000));
    }

    // une classe interner pour garder la meilleure ligne de placement et la meilleure colonne
    static class Instruction implements Serializable {
        private static final long serialVersionUID = 2660699238932119909L;
		public int i;
        public int j;
        public int tour;
        Instruction(int i , int j,int tour){
            this.i = i; this.j = j; this.tour = tour;
        }
    }

    /**
     *
     * @param p le plateau ou le joueur artificiel va essayer de poser la tuile a
     * @param a la tuile a que le joueur artificiel a tiré
     * @return true si le joueur artificiel peut jouer le tuile sur le plateau et false s'il n'y a aucun coup possible
     */
    public boolean coupPossible(Plateau p,Tuile a) {
        if(a == null || p == null) return false;
        int max = 0;
        boolean res = false;
        for(int i = 0; i < p.nbLin() ; i++) {
            for(int j = 0 ; j < p.nbCol() ; j++) {

                //Tester les 4 choix si on tourne la tuile
                if(a.corresponds(p, i, j)) {
                    //System.out.println(" Possibilité 1 ");
                    p.ajouter(a, i, j);

                    // Verification pour les points
                    boolean c0=false,c1=false,c2=false,c3=false;
                    if(j-1>0) {
                        c0 = p.getTuile(i, j-1) != null;
                    }
                    if(i-1>0) {
                        c1 = p.getTuile(i-1, j) != null;
                    }
                    if(j+1<p.nbCol()) {
                        c2 = p.getTuile(i, j+1) != null;
                    }
                    if(i+1<p.nbLin()) {
                        c3 = p.getTuile(i+1, j) != null;
                    }

                    int nbPoints = ((TuileDominos) a).nbPoints(c0, c1, c2, c3);
                    if(nbPoints > max) {
                        max = nbPoints;
                        choix = new Instruction(i,j,0);
                        res = true;
                    }
                    // Supprimer l'element une fois la supposition fini
                    p.ajouter((Tuile) null, i, j);
                }

                a.tourner();
                if(a.corresponds(p, i, j)) {
                    //System.out.println(" Possibilité 2 ");
                    p.ajouter(a, i, j);
                    // Verification pour les points
                    boolean c0=false,c1=false,c2=false,c3=false;
                    if(j-1>0) {
                        c0 = p.getTuile(i, j-1) != null;
                    }
                    if(i-1>0) {
                        c1 = p.getTuile(i-1, j) != null;
                    }
                    if(j+1<p.nbCol()) {
                        c2 = p.getTuile(i, j+1) != null;
                    }
                    if(i+1<p.nbLin()) {
                        c3 = p.getTuile(i+1, j) != null;
                    }
                    int nbPoints = ((TuileDominos) a).nbPoints(c0, c1, c2, c3);
                    if(nbPoints > max) {
                        max = nbPoints;
                        choix = new Instruction(i,j,1);
                        res = true;
                    }
                    // Supprimer l'element une fois la supposition fini
                    p.ajouter((Tuile) null, i, j);
                }

                a.tourner();
                if(a.corresponds(p, i, j)) {
                    //System.out.println(" Possibilité 3 ");
                    p.ajouter(a, i, j);
                    // Verification pour les points
                    boolean c0=false,c1=false,c2=false,c3=false;
                    if(j-1>0) {
                        c0 = p.getTuile(i, j-1) != null;
                    }
                    if(i-1>0) {
                        c1 = p.getTuile(i-1, j) != null;
                    }
                    if(j+1<p.nbCol()) {
                        c2 = p.getTuile(i, j+1) != null;
                    }
                    if(i+1<p.nbLin()) {
                        c3 = p.getTuile(i+1, j) != null;
                    }int nbPoints = ((TuileDominos) a).nbPoints(c0, c1, c2, c3);
                    if(nbPoints > max) {
                        max = nbPoints;
                        choix = new Instruction(i,j,2);
                        res = true;
                    }
                    // Supprimer l'element une fois la supposition fini
                    p.ajouter((Tuile) null, i, j);
                }

                a.tourner();
                if(a.corresponds(p, i, j)) {
                    //System.out.println(" Posibilité 4 ");
                    p.ajouter(a, i, j);
                    // Verification pour les points
                    boolean c0=false,c1=false,c2=false,c3=false;
                    if(j-1>0) {
                        c0 = p.getTuile(i, j-1) != null;
                    }
                    if(i-1>0) {
                        c1 = p.getTuile(i-1, j) != null;
                    }
                    if(j+1<p.nbCol()) {
                        c2 = p.getTuile(i, j+1) != null;
                    }
                    if(i+1<p.nbLin()) {
                        c3 = p.getTuile(i+1, j) != null;
                    }
                    int nbPoints = ((TuileDominos) a).nbPoints(c0, c1, c2, c3);
                    if(nbPoints > max) {
                        max = nbPoints;
                        choix = new Instruction(i,j,3);
                        res = true;
                    }
                    // Supprimer l'element une fois la supposition fini
                    p.ajouter((Tuile) null, i, j);
                }
                // Retour de la tuile à sa position de depart
                a.tourner();
            }

        }
        if(choix != null) {
            System.out.println("Le meilleur coup est " + choix.tour+" ligne : "+choix.i+ " colonne: "+choix.j );
        }
        return res;
    }

    public int getNbTours() {
        return choix.tour;
    }

    public int getLigne() {
        return choix.i;
    }

    public int getCol() {
        return choix.j;
    }

}
