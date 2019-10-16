package gomoku;


public abstract class JoueurAuto extends JoueurAvecGrille {

	private Marqueur coupChoisi;
	
	public JoueurAuto(Grille g, boolean isCroix) {
		super(g, "Dumb Guy", isCroix);
	}
	
	public JoueurAuto(Grille g, String nom, boolean isCroix) {
		super(g,nom,isCroix);
				
	}

	protected abstract void retourAttaque(Marqueur m, int etat);// {
//		if (etat == GAGNE)
//			System.out.println( this.getNom() +" a gagn�!");
//		else  
//			System.out.println( "Joueur suivant");
//		System.out.println(this.getGrille());


	//}
	
	protected abstract void retourDefense(Marqueur m, int etat);// {
//		if (etat == GAGNE)
//			System.out.println(this.getNom() +" a perdu!");
//		else 
//			System.out.println( "Joueur suivant");

//}
	
	/**
	 * Cette m�thode est invoqu�e sur le joueur attaquant au d�but d�un tour de jeu. Elle retourne
	   la coordonn�e cible du tir effectu� par l�attaquant. 
	 */
	public abstract Marqueur choisirAttaque(); //{
//		int ligne = (int) (Math.random() * super.getGrille().getTaille());
//		int colonne = (int) (Math.random() * super.getGrille().getTaille());
//		Coordonnee c = new Coordonnee(ligne,colonne);
//		Marqueur coupChoisi = super.isCroix() ? new Marqueur(c, true) : new Marqueur (c, false);
//		
//		return coupChoisi;
			
	//}
	

	public static void main(String[] args) {


	}

	
	
}
