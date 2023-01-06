package components.tuiles;

public class CoteCarcassonne extends Cote {
	
	private final Paysage paysage;
	
	public CoteCarcassonne(Paysage paysage) {
		this.paysage = paysage;
	}

	@Override
	public boolean corresponds(Cote c) {
		if(!(c instanceof CoteCarcassonne)) return false;
		return this.paysage.getClass().equals(((CoteCarcassonne)c).paysage.getClass());
	}

	public boolean estPres() {
		return paysage.getClass().equals(Pres.class);
	}

	public boolean estChemin() {
		return paysage.getClass().equals(Chemin.class);
	}
	
	@Override
	public String toString() {
		return paysage.toString();
	}

}
