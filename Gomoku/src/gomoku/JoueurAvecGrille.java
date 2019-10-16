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

	
	public int defendre(Marqueur m) {
		
	if(grille.ajouteMarqueur(m) && grille.fiveInARow(m))
		return GAGNE;
	else 
		return PASGAGNE;
			
	}	
	/*	
	if(grille.recoitTir(c)) {
		if (grille.estCoule(c)) 
			if (grille.perdu()) {
				return super.GAMEOVER;
			}else 
				return super.COULE;
		else  
			return super.TOUCHE;
			}
	else 
		return super.A_L_EAU;
*/
	//}
	
	
	public static void main(String[] args) {

	}
}
