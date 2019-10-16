package gomoku;



public class Coordonnee {

	@Override
	public String toString() {
		return "Coordonnee [ligne=" + ligne + ", colonne=" + colonne + "]";
	}

	private int ligne;
	private int colonne;

	public Coordonnee(int ligne, int colonne) {  // J'autorise les coord en 0,0 même si c'est pas censé exister pour éviter des erreurs ailleurs reloues à gérer
		if ((ligne < 0) || (ligne > 22))
			throw new IllegalArgumentException("Numéro de ligne <0 ou >22 illegal");
		if ((colonne < 0) || (colonne > 22))
			throw new IllegalArgumentException("Numéro de colonne <0 ou >22 illegal");
		this.ligne = ligne;
		this.colonne = colonne;
	}

	/**
	 * Initialise les attributs d'instance Ã  partir d'une String s de la forme "X00"
	 * @param s
	 */
	
	public Coordonnee(String s) {
		if (s.equals("")) {
			throw new IllegalArgumentException("Erreur chaÃ®ne vide");
		}
		
		int l = Integer.parseInt(s.substring(1, s.length())) - 1;
		
		
		if(s.length() != 2 && s.length() != 3) {
			throw new IllegalArgumentException("Longueur incorrecte");
		}
		
		if ( l < 0 || l > 25) {
			throw new IllegalArgumentException("La ligne doit Ãªtre comprise entre 0 et 25");
		}
			
		this.colonne = s.charAt(0) - 'A';
		this.ligne = Integer.parseInt(s.substring(1, s.length())) - 1;
	}
	
	public int getLigne() {
		return this.ligne;
	}

	public int getColonne() {
		return this.colonne;
	}

	/**
	 * Retourne vrai si this et obj sont équivalent
	 */

	public boolean equals(Object obj) {
		return (obj instanceof Coordonnee)
				&& (this.ligne == ((Coordonnee) obj).ligne && this.colonne == ((Coordonnee) obj).colonne);
	}

	public boolean voisine(Coordonnee c) {
		int diffLigne = c.ligne - this.ligne ;
		int diffCol = c.colonne - this.colonne;
		
		return (diffLigne <= 1 && diffLigne >= -1 && diffCol <= 1 && diffCol >= -1 && !(diffLigne == 0 && diffCol == 0));
	}
}
