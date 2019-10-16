package gomoku;


public abstract class JoueurAuto extends JoueurAvecGrille {

	//En fait cette classe sert à rien, a voir plus tard :D

	
	public JoueurAuto(Grille g, boolean isCroix) {
		super(g, "Dumb Guy", isCroix);
	}
	
	public JoueurAuto(Grille g, String nom, boolean isCroix) {
		super(g,nom,isCroix);
				
	}

	protected abstract void retourAttaque(Marqueur m, int etat);

	
	protected abstract void retourDefense(Marqueur m, int etat);


	public abstract Marqueur choisirAttaque();

	
	public static void main(String[] args) {


	}

	
	
}
