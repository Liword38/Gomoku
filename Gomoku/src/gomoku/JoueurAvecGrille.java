package gomoku;

public abstract class JoueurAvecGrille extends Joueur{
	
	private Grille grille;
	
	public JoueurAvecGrille(Grille g, String nom, boolean isCroix) {
		super(g.getTaille(), nom, isCroix);
		this.grille=g;
	}

	public JoueurAvecGrille(Grille g, boolean isCroix) {
		super(g.getTaille(),isCroix);
		this.grille=g;
	}
	
	public Grille getGrille() {
		return grille;
	}

	/**
	 * ajoute le marqueur adverse sur la grille et vérifie si l'adversaire a gagné avec ce coup ou pas
	 * 
	 */
	public int defendre(Marqueur m) {
		
	if(grille.ajouteMarqueur(m) && grille.fiveInARow(m))
		return GAGNE;
	else 
		return PASGAGNE;
			
	}	

	public static void main(String[] args) {

	}
}
