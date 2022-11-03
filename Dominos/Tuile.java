import java.util.Arrays;

public class Tuile {

    private int[][] cotes;

    public Tuile() {
        cotes = new int[4][3];
    }

    public Tuile(int[] c0, int[] c1, int[] c2, int[] c3) {
        cotes = new int[4][3];
        remplirCote(this, 0, c0);
        remplirCote(this, 1, c1);
        remplirCote(this, 2, c2);
        remplirCote(this, 3, c3);
    }

    private void remplirCote(Tuile t, int cote, int[] valeurs) {
        for(int i = 0; i < valeurs.length; i++) {
            t.cotes[cote][i] = valeurs[i];
        }
    }

    public void touner() { // tourner de 90° dans le sens des aiguilles d'une montre
        int[] tmp = this.cotes[0];
        this.cotes[0] = this.cotes[3];
        this.cotes[3] = this.cotes[2];
        this.cotes[2] = this.cotes[1];
        this.cotes[1] = tmp;
    }

    public String toString() {
        String s = "";
        s += " ";
        for(int i = 0; i < 3; i++) s += cotes[1][i] + "  ";
        s += "\n";
        for(int i = 0; i < 3; i++) s += cotes[0][i] + "       " + cotes[2][i] + "\n";
        s += " ";
        for(int i = 0; i < 3; i++) s += cotes[3][i] + "  ";
        return s;
    }

    public int nbPoints(boolean c0, boolean c1, boolean c2, boolean c3) {
        int res = 0;
        if(c0) {
            for(int i : this.cotes[0]) res += i;
        }
        if(c1) {
            for(int i : this.cotes[1]) res += i;
        }
        if(c2) {
            for(int i : this.cotes[2]) res += i;
        }
        if(c3) {
            for(int i : this.cotes[4]) res += i;
        }
        return res;
    }

    /*public boolean corresponds(Plateau p, int i, int j) {
        if(p.getTuile(i, j) != null) return false; // la case n'est pas vide
        if(i-1 > 0 && p.getTuile(i-1, j) != null
                && !p.getTuile(i-1, j).cotes[3].equals(this.cotes[1])) return false;
        if(i+1 < p.nbLin() && p.getTuile(i+1, j) != null
                && !p.getTuile(i+1, j).cotes[1].equals(this.cotes[3])) return false;
        if(j-1 > 0 && p.getTuile(i, j-1) != null
                && !p.getTuile(i, j-1).cotes[2].equals(this.cotes[0])) return false;
        if(j+1 < p.nbCol() && p.getTuile(i, j+1) != null
                && !p.getTuile(i, j+1).cotes[0].equals(this.cotes[2])) return false;
        return true;
    }*/

    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Tuile)) return false;
        Tuile t = (Tuile) o;
        if(t.identique(this)) return true;
        t.touner();
        if(t.identique(this)) return true;
        t.touner();
        if(t.identique(this)) return true;
        t.touner();
        if(t.identique(this)) return true;
        t.touner(); // Pour restaurer la tuile a son orientation avant le test
        return false;
    }

    private boolean identique(Tuile t) {
        return (Arrays.equals(this.cotes[0], t.cotes[0]) && Arrays.equals(this.cotes[1], t.cotes[1])
                && Arrays.equals(this.cotes[2], t.cotes[2]) && Arrays.equals(this.cotes[3], t.cotes[3]));
    }

}