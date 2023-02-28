package components.plateau;

import components.tuiles.TuileDominos;

public class PlateauDominos extends Plateau {

	public PlateauDominos(int ligne, int col) {
		cases = new TuileDominos[ligne][col];
	}
	
	public void affiche() {
        for(int i = 0; i < cases.length; i++) {
            System.out.print((i+1) + " ".repeat(3 - (int) (Math.log10(i+1) + 1)));
            afficherLigne(i);
        }
        System.out.print("   ");
        for(int i = 0; i < cases[0].length; i++) {
            System.out.print((i+1) + " ".repeat(7 - (int) (Math.log10(i+1) + 1)));
        }
        System.out.println();
    }

    private void afficherLigne(int l) {
        for(int i = 0; i < cases[l].length; i++) {
            System.out.print("_");
            if (cases[l][i] != null) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(((TuileDominos)cases[l][i]).getValue(1, j) + "_");
                }
            } else System.out.print("_".repeat(6));
        }
        System.out.println();

        for(int j = 0; j < 3; j++) {
            System.out.print("   ");
            for (int i = 0; i < cases[l].length; i++) {
                if (cases[l][i] != null) {
                    System.out.print(((TuileDominos)cases[l][i]).getValue(0, j)  + "_____");
                    System.out.print(((TuileDominos)cases[l][i]).getValue(2, j) );
                } else {
                    System.out.print("_".repeat(7));
                }
            }
            System.out.println();
        }

        System.out.print("   ");
        for(int i = 0; i < cases[l].length; i++) {
            System.out.print("_");
            if(cases[l][i] != null) {
                for(int j = 0; j < 3; j++) {
                    System.out.print(((TuileDominos)cases[l][i]).getValue(3, j)  + "_");
                }
            }
            else System.out.print("_".repeat(6));
        }
        System.out.println();
    }

}
