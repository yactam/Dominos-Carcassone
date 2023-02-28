package components.tuiles;

import java.util.Random;

public class TuileDominos extends Tuile {
	private static final Random rand = new Random();
	private static final int MAX_VALUE = 2;
	
	public TuileDominos() {
		this(genereTab(), genereTab(), genereTab(), genereTab());
	}
	
	private static int[] genereTab() {
        int[] res = new int[3];
        for(int i = 0; i < 3; i++) {
            res[i] = rand.nextInt(MAX_VALUE) + 1;
        }
        return res;
    }

    private TuileDominos(int[] c0, int[] c1, int[] c2, int[] c3) {
    	cotes = new CoteDominos[4];
    	for(int i = 0; i < 4; i++) {
    		cotes[i] = new CoteDominos();
    	}
        remplirCote(0, c0);
        remplirCote(1, c1);
        remplirCote(2, c2);
        remplirCote(3, c3);
    }

    private void remplirCote(int cote, int[] valeurs) {
        ((CoteDominos)cotes[cote]).remplir(valeurs);
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(" ");
        for(int i = 0; i < 3; i++) s.append(((CoteDominos)cotes[1]).getVal(i)).append("  ");
        s.append("\n");
        for(int i = 0; i < 3; i++) s.append(((CoteDominos)cotes[0]).getVal(i)).append("       ").append(((CoteDominos)cotes[2]).getVal(i)).append("\n");
        s.append(" ");
        for(int i = 0; i < 3; i++) s.append(((CoteDominos)cotes[3]).getVal(i)).append("  ");
        return s.toString();
    }

    public int nbPoints(boolean c0, boolean c1, boolean c2, boolean c3) {
        int res = 0;
        if(c0) {
            for(int i : (CoteDominos)cotes[0]) res += i;
        }
        if(c1) {
            for(int i : (CoteDominos)cotes[1]) res += i;
        }
        if(c2) {
            for(int i : (CoteDominos)cotes[2]) res += i;
        }
        if(c3) {
            for(int i : (CoteDominos)cotes[3]) res += i;
        }
        return res;
    }

    public int getValue(int i, int j) {
        return ((CoteDominos)cotes[i]).getVal(j);
    }

}
