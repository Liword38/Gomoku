package gomoku;

public class Marqueur {
	private Coordonnee coordonnee;
	private boolean croix;

	public Marqueur(Coordonnee c, boolean croix) {
		this.coordonnee = c;
		this.croix=croix;
		
	}

	public boolean touche(Coordonnee m) {
		return this.coordonnee.voisine(m);
	}
	
	
	


	


	@Override
	public String toString() {
		return "Marqueur [" + coordonnee + ", croix=" + croix + "]";
	}

	public boolean isCroix() {
		return croix;
	}
	
	public boolean isRond() {
		return !croix;
	}
	
	public boolean isSameType(Marqueur m) {
		return this.croix == m.croix;
	}

	public void setCroix(boolean croix) {
		this.croix = croix;
	}

	public Coordonnee getCoordonnee() {
		return coordonnee;
	}

	public void setCoordonnee(Coordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}

	public static void main(String[] args) {
	Coordonnee c1 = new Coordonnee(3,3);
	Marqueur monMarq1 = new Marqueur(c1,true);
	System.out.println(monMarq1);
	}
	
}
