package player;

import components.plateau.Plateau;
import components.tuiles.Tuile;

import java.util.ArrayList;
import java.util.Random;
import static player.JoueurArtificiel.Instruction;

public class JoueurArtificielCarcassonne extends JoueurCarcassonne {

    private Instruction choix;
    public JoueurArtificielCarcassonne() {
        super("Flatchy" + + new Random().nextInt(1000));
    }

    public boolean coupPossible(Plateau p, Tuile a) {
        if(a == null || p == null) return false;
        int nbChoix = 0;
        boolean res = false;
        ArrayList<Instruction> instructions = new ArrayList<>();

        for(int i = 0; i < p.nbLin() ; i++) {
            for(int j = 0 ; j < p.nbCol() ; j++) {
                //Tester les 4 choix si on tourne la tuile
                if(a.corresponds(p, i, j)) {
                    choix = new Instruction(i,j,0);
                    instructions.add(choix);
                    res = true;
                    nbChoix++;
                }

                a.tourner();
                if(a.corresponds(p, i, j)) {
                    choix = new Instruction(i,j,1);
                    instructions.add(choix);
                    res = true;
                    nbChoix++;
                }

                a.tourner();
                if(a.corresponds(p, i, j)) {
                    choix = new Instruction(i,j,2);
                    instructions.add(choix);
                    res = true;
                    nbChoix++;
                }

                a.tourner();
                if(a.corresponds(p, i, j)) {
                    choix = new Instruction(i,j,3);
                    instructions.add(choix);
                    res = true;
                    nbChoix++;
                }
                // Retour de la tuile à sa position de depart
                a.tourner();
            }

        }
        if(instructions.size() != 0) {
            int index = new Random().nextInt(nbChoix);
            choix = instructions.get(index);
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
