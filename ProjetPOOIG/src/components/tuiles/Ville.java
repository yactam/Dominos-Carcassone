package components.tuiles;

public class Ville extends Paysage {

    private final boolean mur;
    public Ville(boolean mur) {
        this.mur = mur;
    }

    public boolean estMur() {
        return mur;
    }

    @Override
    public String toString() {
        if(mur) return "mur";
        return "ville";
    }
}
