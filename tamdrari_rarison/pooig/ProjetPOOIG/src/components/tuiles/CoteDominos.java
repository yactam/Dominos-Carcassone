package components.tuiles;

import java.util.Arrays;
import java.util.Iterator;

public class CoteDominos extends Cote implements Iterable<Integer> {

	private int[] cote;
	
	public CoteDominos() {
		this.cote = new int[3];
	}
	
	void remplir(int[] t) {
		cote = t.clone();
	}
	
	public int getVal(int i) {
		return cote[i];
	}
	@Override
	public boolean corresponds(Cote c) {
		return Arrays.equals(this.cote, ((CoteDominos)c).cote);
	}
	
	@Override
	public String toString() {
		String res = "";
		for(int i = 0; i < 3; i++) {
			res += cote[i] + "  ";
		}
		return res;
	}
	@Override
	public Iterator<Integer> iterator() {
		return Arrays.stream(cote).iterator();
	}

}
