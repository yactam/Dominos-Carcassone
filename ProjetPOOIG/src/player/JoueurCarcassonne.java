package player;

import components.tuiles.Partisan;
import components.plateau.Plateau;
import components.tuiles.Tuile;
import components.tuiles.TuileCarcassonne;

import java.util.ArrayList;

public class JoueurCarcassonne extends Joueur {

    private ArrayList<Partisan> partisans;
    private boolean peutPoserPartisan;
    private TuileCarcassonne derniereTuile;

    public JoueurCarcassonne(String nom) {
        super(nom);
        initPartisans();
    }

    private void initPartisans() {
        partisans = new ArrayList<>();
        for(int i = 0; i < 10; i++) { // 10 partisans pour chaque joueur
            Partisan p = new Partisan(getCouleur());
            partisans.add(p);
        }
    }

    @Override
    public boolean poser(Plateau p, Tuile t, int i, int j) {
        if(t == null || p == null) return false;
        if(t.corresponds(p, i, j)) {
            p.ajouter(t, i ,j);
            derniereTuile = (TuileCarcassonne) t;
            peutPoserPartisan = true;
            return true;
        } else {
            System.out.println("Impossible de poser la tuile à (" + i + ", " + j + ")");
            return false;
        }
    }

    public boolean poserPartisans(Plateau p, int i, int j, int cote) {
        if(p == null) return false;
        if(derniereTuile == p.getTuile(i, j) && peutPoserPartisan && partisans.size() > 0) {
            Partisan partisan = partisans.remove(0);
            derniereTuile.ajouterPartisan(partisan, cote);
            peutPoserPartisan = false;
            return true;
        } else {
            if(derniereTuile != p.getTuile(i,j)) {
                System.out.println("Tuile differente");
            } else if(!peutPoserPartisan) {
                System.out.println("Le joueur ne peut pas poser un partisan");
            } else if(partisans.size() == 0) {
                System.out.println("Le joueur n'a pas de partisans à poser");
            }
            System.out.println("Il est possible de poser qu'un seul partisan que " +
                    "sur la derniere tuile qu'on vient de poser avec un nombre limité à 10 partisans");
        }
        return false;
    }

    public String getCouleur() {
        String res;
        switch (id) {
            case 0 : res = "Bleu";
                break;
            case 1 : res = "Jaune";
                break;
            case 2 : res = "Vert";
                break;
            case 3 : res = "Rouge";
                break;
            case 4 : res = "Gris";
                break;
            default : res = "Noir";
        };
        return res;
    }
}
