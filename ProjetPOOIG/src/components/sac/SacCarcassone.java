package components.sac;

import components.tuiles.Chemin;
import components.tuiles.Pres;
import components.tuiles.TuileCarcassonne;
import components.tuiles.Ville;

public class SacCarcassone extends Sac {

    public SacCarcassone() {
        tuiles = new TuileCarcassonne[72];
        super.capacite = 72;
        super.nbTuiles = 0;
        generate9();
        generate8();
        generate5();
        generate4();
        generate3();
        generate2();
        generate1();
        shuffle();
    }

    private void generate9() {
        for(int i = 0; i < 9; i++) {
            TuileCarcassonne t = new TuileCarcassonne(new Chemin(), new Pres(), new Pres(), new Chemin(), false);
            ajouterTuile(t);
        }
    }

    private void generate8() {
        for(int i = 0; i < 8; i++) {
            TuileCarcassonne t = new TuileCarcassonne(new Pres(), new Chemin(), new Pres(), new Chemin(), false);
            ajouterTuile(t);
        }
    }

    private void generate5() {
        for(int i = 0; i < 5; i++) {
            TuileCarcassonne t = new TuileCarcassonne(new Pres(), new Ville(true), new Pres(), new Pres(), false);
            ajouterTuile(t);
        }
    }

    private void generate4() {
        for(int i = 0; i < 4; i++) {
            TuileCarcassonne t0 = new TuileCarcassonne(new Chemin(), new Pres(), new Chemin(), new Chemin(), false);
            TuileCarcassonne t1 = new TuileCarcassonne(new Pres(), new Pres(), new Pres(), new Pres(), false);
            TuileCarcassonne t2 = new TuileCarcassonne(new Chemin(), new Ville(true), new Chemin(), new Pres(), false);
            ajouterTuile(t0);
            ajouterTuile(t1);
            ajouterTuile(t2);
        }
    }

    private void generate3() {
        for(int i = 0; i < 3; i++) {
            TuileCarcassonne t0 = new TuileCarcassonne(new Pres(), new Ville(true), new Chemin(), new Chemin(), false);
            TuileCarcassonne t1 = new TuileCarcassonne(new Ville(true), new Ville(true), new Chemin(), new Chemin(), false);
            TuileCarcassonne t2 = new TuileCarcassonne(new Chemin(), new Ville(true), new Chemin(), new Chemin(), false);
            TuileCarcassonne t3 = new TuileCarcassonne(new Ville(false), new Ville(true), new Ville(false), new Pres(), false);
            TuileCarcassonne t4 = new TuileCarcassonne(new Ville(true), new Ville(true), new Pres(), new Pres(), false);
            TuileCarcassonne t5 = new TuileCarcassonne(new Chemin(), new Ville(true), new Pres(), new Chemin(), false);
            TuileCarcassonne t6 = new TuileCarcassonne(new Ville(true), new Pres(), new Ville(true), new Pres(), false);
            ajouterTuile(t0);
            ajouterTuile(t1);
            ajouterTuile(t2);
            ajouterTuile(t3);
            ajouterTuile(t4);
            ajouterTuile(t5);
            ajouterTuile(t6);
        }
    }

    private void generate2() {
        for(int i = 0; i < 2; i++) {
            TuileCarcassonne t0 = new TuileCarcassonne(new Ville(true), new Ville(true), new Chemin(), new Chemin(), true);
            TuileCarcassonne t1 = new TuileCarcassonne(new Pres(), new Ville(true), new Ville(true), new Pres(), false);
            TuileCarcassonne t2 = new TuileCarcassonne(new Pres(), new Pres(), new Pres(), new Chemin(), false);
            TuileCarcassonne t3 = new TuileCarcassonne(new Ville(false), new Pres(), new Ville(false), new Pres(), true);
            TuileCarcassonne t4 = new TuileCarcassonne(new Ville(false), new Ville(true), new Ville(false), new Chemin(), true);
            TuileCarcassonne t5 = new TuileCarcassonne(new Ville(true), new Ville(true), new Pres(), new Pres(), true);
            ajouterTuile(t0);
            ajouterTuile(t1);
            ajouterTuile(t2);
            ajouterTuile(t3);
            ajouterTuile(t4);
            ajouterTuile(t5);
        }
    }

    private void generate1() {
        TuileCarcassonne t0 = new TuileCarcassonne(new Ville(false), new Ville(true), new Ville(false), new Chemin(), false);
        TuileCarcassonne t1 = new TuileCarcassonne(new Ville(false), new Ville(true), new Ville(false), new Pres(), true);
        TuileCarcassonne t2 = new TuileCarcassonne(new Ville(false), new Pres(), new Ville(false), new Pres(), false);
        TuileCarcassonne t3 = new TuileCarcassonne(new Ville(false), new Ville(false), new Ville(false), new Ville(false), true);
        TuileCarcassonne t4 = new TuileCarcassonne(new Chemin(), new Chemin(), new Chemin(), new Chemin(), false);
        ajouterTuile(t0);
        ajouterTuile(t1);
        ajouterTuile(t2);
        ajouterTuile(t3);
        ajouterTuile(t4);
    }
}
