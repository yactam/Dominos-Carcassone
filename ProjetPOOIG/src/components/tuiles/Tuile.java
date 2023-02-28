package components.tuiles;

import components.plateau.Plateau;

import java.io.Serializable;

public abstract class Tuile implements Serializable {
	
	Cote[] cotes;
	
	public void tourner() {
		Cote tmp = cotes[0];
		this.cotes[0] = this.cotes[3];
	    this.cotes[3] = this.cotes[2];
        this.cotes[2] = this.cotes[1];
        this.cotes[1] = tmp;
	}
	
	public String toString() {
		return cotes[0].toString() + " " + cotes[1].toString() + " " + cotes[2].toString() + " " + cotes[3].toString();
	}
	
	public boolean corresponds(Plateau p, int i, int j) {
		if(p.getTuile(i, j) != null) return false; // la case n'est pas vide
        if((i-1 < 0 || p.getTuile(i-1, j) == null) && (i+1 >= p.nbLin() || p.getTuile(i+1, j) == null)
                && (j-1 < 0 || p.getTuile(i, j-1) == null) && (j+1 >= p.nbCol() || p.getTuile(i, j+1) == null)) return false; // il n y a aucune tuile autour
        // Il n'y a pas de correspondance avec les components.tuiles adjacentes
        if(i-1 > 0 && p.getTuile(i-1, j) != null
                && !p.getTuile(i-1, j).cotes[3].corresponds(this.cotes[1])) {
            return false;
        }
        if(i+1 < p.nbLin() && p.getTuile(i+1, j) != null
                && !p.getTuile(i+1, j).cotes[1].corresponds(this.cotes[3])) {
            return false;
        }
        if(j-1 > 0 && p.getTuile(i, j-1) != null
                && !p.getTuile(i, j-1).cotes[2].corresponds(this.cotes[0])) {
            return false;
        }
        if(j+1 < p.nbCol() && p.getTuile(i, j+1) != null
                && !p.getTuile(i, j+1).cotes[0].corresponds(this.cotes[2])) {
            return false;
        }
        // Tout est bon on peut poser la tuile
        return true;
	}
	
	public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Tuile)) return false;
        Tuile t = (Tuile) o;
        if(t.identique(this)) return true;
        t.tourner();
        if(t.identique(this)) {
            t.tourner(); // Pour restaurer la tuile a son orientation avant le test
            t.tourner();
            t.tourner();
            return true;
        }
        t.tourner();
        if(t.identique(this)) {
            t.tourner(); // Pour restaurer la tuile a son orientation avant le test
            t.tourner();
            return true;
        }
        t.tourner();
        if(t.identique(this)) return true;
        t.tourner(); // Pour restaurer la tuile a son orientation avant le test
        return false;
    }
	
	public boolean identique(Tuile t) {
		 return (this.cotes[0].corresponds(t.cotes[0]) && this.cotes[1].corresponds(t.cotes[1])
	                && this.cotes[2].corresponds(t.cotes[2]) && this.cotes[3].corresponds(t.cotes[3]));
	}

}
