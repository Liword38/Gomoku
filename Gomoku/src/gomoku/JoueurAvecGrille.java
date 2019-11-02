package gomoku;

public abstract class JoueurAvecGrille extends Joueur {

	private Grille grille;

	public JoueurAvecGrille(Grille g, String nom, boolean isCroix) {
		super(g.getTaille(), nom, isCroix);
		this.grille = g;
	}

	public JoueurAvecGrille(Grille g, boolean isCroix) {
		super(g.getTaille(), isCroix);
		this.grille = g;
	}

	public Grille getGrille() {
		return grille;
	}

	/**
	 * ajoute le marqueur adverse sur la grille et v�rifie si l'adversaire a gagn�
	 * avec ce coup ou pas
	 * 
	 */
	public int defendre(Marqueur m) {

		if (grille.ajouteMarqueur(m)) {
			if (grille.plusLongueLigne(m) >= 5) {
				
				return GAGNE;
				}
			else {
				if (grille.isFull()) 
					return EGALITE;
				else
					return PASGAGNE;
			}
		} else
			return REJOUE;
	}

	public static void main(String[] args) {

	}
}
