package controller.game;

import components.plateau.PlateauDominos;
import components.sac.Sac;
import components.sac.SacDominos;
import components.tuiles.Tuile;
import components.tuiles.TuileDominos;
import player.Joueur;
import player.JoueurArtificiel;

import java.util.ArrayList;
import java.util.Scanner;

public class TerminalController {
	 private final Sac sac;
	    private final ArrayList<Joueur> joueurs;
	    private final PlateauDominos plateau;
	    private static final Scanner scan = new Scanner(System.in);

	    public TerminalController() {
	        sac = new SacDominos(20);
	        plateau = new PlateauDominos(10, 10);
	        joueurs = new ArrayList<Joueur>();
	    }

	    public void lancer() {
	        System.out.println("Bienvenue dans une partie de Dominos Carrés");
	        int nbJoueurs  = 0;
	        int nbJoueursA = 0;
	        do {
	            System.out.print("Donner le nombre de joueurs (1 ou plus): ");
	            nbJoueurs = scan.nextInt();
	            if(nbJoueurs == 1) {
	                System.out.println("Donner le nombre de joueurs artificiels (1 ou plus): ");
	            } else {
	                System.out.println("Donner le nombre de joueurs artificiels (0 ou plus): ");
	            }
	            nbJoueursA = scan.nextInt();
	        } while (nbJoueurs + nbJoueursA < 2);
	        scan.nextLine();
	        while(nbJoueurs > 0) {
	            System.out.print("Donner le nom de joueur " + nbJoueurs + ": ");
	            String nom = scan.nextLine();
	            Joueur j = new Joueur(nom);
	            joueurs.add(j);
	            nbJoueurs--;
	        }
	        while(nbJoueursA > 0) {
	            Joueur j = new JoueurArtificiel();
	            joueurs.add(j);
	            nbJoueursA--;
	        }
	        Tuile initiale = sac.retirer();
	        plateau.ajouter(initiale, plateau.nbLin() / 2, plateau.nbCol() / 2);
	        while(joueurs.size() > 1 && !sac.estVide()) {
	            for(Joueur j : joueurs) {
	                if(!(j instanceof JoueurArtificiel)) {
	                    System.out.println("C'est à vous " + j.nom + " vous avez " + j.getNbPoints() + " points");
	                    System.out.println("Voici le plateau: ");
	                    plateau.affiche();
	                    TuileDominos t = (TuileDominos) j.piocher(sac);
	                    System.out.println("Voici votre tuile");
	                    System.out.println(t);
	                    System.out.print("Voulez-vous abandonner?(o/n)");
	                    String rep = scan.nextLine();
	                    if(rep.equalsIgnoreCase("o") || rep.equalsIgnoreCase("oui")) {
	                        j.abandonner();
	                        joueurs.remove(j);
	                        break;
	                    }
	                    System.out.print("Voulez-vous passer votre tour?(o/n)");
	                    rep = scan.nextLine();
	                    if(rep.equalsIgnoreCase("o") || rep.equalsIgnoreCase("oui")) {
	                        break;
	                    }
	                    do {
	                        System.out.print("Voulez-vous tourner votre tuile?(o/n)");
	                        rep = scan.nextLine();
	                        if(rep.equalsIgnoreCase("o") || rep.equalsIgnoreCase("oui")) {
	                            System.out.print("Donner le nombre de tours de 90° dans le sens horaire: ");
	                            int nbTours = scan.nextInt();
	                            j.tourner(t, nbTours);
	                            System.out.println(t);
	                        }
	                        System.out.print("Donner le numero de la ligne ou vous voulez poser votre tuile? ");
	                        int lin = scan.nextInt();
	                        lin -= 1;
	                        System.out.print("Donner le numero de la case ou vous voulez poser votre tuile sur la ligne " + (lin+1) + "? ");
	                        int col = scan.nextInt();
	                        col -= 1;
	                        scan.nextLine();
	                        if(j.poser(plateau, t, lin, col)) {
	                            boolean c0 = plateau.getTuile(lin, col-1) != null, c1 = plateau.getTuile(lin-1, col) != null,
	                                    c2 = plateau.getTuile(lin, col+1) != null, c3 = plateau.getTuile(lin+1, col) != null;
	                            int nbPoints = t.nbPoints(c0, c1, c2, c3);
	                            j.gangerPoints(nbPoints);
	                            System.out.println("Felicitation vous avez gangé " + nbPoints + " milliards d'euros");
	                            break;
	                        } else {
	                            System.out.println("La position n'est pas valide");
	                        }
	                    } while(!rep.equalsIgnoreCase("o") && !rep.equalsIgnoreCase("oui"));
	                } else {
	                    TuileDominos t = (TuileDominos) j.piocher(sac);
						JoueurArtificiel joueurArtificiel = (JoueurArtificiel) j;
						if(joueurArtificiel.coupPossible(plateau, t)) {
	                        j.tourner(t, joueurArtificiel.getNbTours());
	                        int lin = joueurArtificiel.getLigne(), col = joueurArtificiel.getCol();
	                        j.poser(plateau, t, lin, col);
	                        boolean c0 = plateau.getTuile(lin, col-1) != null, c1 = plateau.getTuile(lin-1, col) != null,
	                                c2 = plateau.getTuile(lin, col+1) != null, c3 = plateau.getTuile(lin+1, col) != null;
	                        int nbPoints = t.nbPoints(c0, c1, c2, c3);
	                        j.gangerPoints(nbPoints);
	                    } else {
	                        break; // passer son tour
	                    }
	                }
	            }
	        }
	        if(joueurs.size() <= 1) {
	            if(joueurs.size() == 0) System.out.println("Tous les joueurs ont abandonnés");
	            else System.out.println("Le gangant est " + joueurs.get(0));
	        }
	        else {
	            System.out.println("Le sac est vide");
	            Joueur gagnant = joueurs.get(0);
	            for(Joueur j : joueurs) {
	                if(j.getNbPoints() > gagnant.getNbPoints()) gagnant = j;
	            }
	            System.out.println("Le gangant est le joueur " + gagnant);
	        }
	    }
}
