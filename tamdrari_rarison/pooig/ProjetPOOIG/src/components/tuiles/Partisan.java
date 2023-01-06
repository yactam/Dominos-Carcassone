package components.tuiles;

import java.io.Serializable;
import java.util.Objects;

public final class Partisan implements Serializable {
    private final String couleur;

    public Partisan(String couleur) {
        this.couleur = couleur;
    }

    public String couleur() {
        return couleur;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Partisan) obj;
        return Objects.equals(this.couleur, that.couleur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couleur);
    }

    @Override
    public String toString() {
        return "Partisan[" +
                "couleur=" + couleur + ']';
    }


}
