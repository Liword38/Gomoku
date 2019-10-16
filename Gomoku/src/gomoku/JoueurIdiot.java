package gomoku;



public class JoueurIdiot extends JoueurAuto {

	private Marqueur coupChoisi;
	
	public JoueurIdiot(Grille g, String nom, boolean isCroix) {
		super(g,nom,isCroix);

		
		
	}
		
	public JoueurIdiot(Grille g, boolean isCroix) {
		super(g, "Jack Sparrow", isCroix);


		
	}
	
	//Affiche des retours sur d�roulement de la partie dans la console apr�s avoir jou�
	protected void retourAttaque(Marqueur m, int etat) {
		if (etat == GAGNE)
			System.out.println( this.getNom() +" a gagne en jouant en "+m.getCoordonnee()+ " contre "+this.getOpponentName() + " !");
		else  
			System.out.println( this.getNom() + " joue en "+ m.getCoordonnee());

	}
	
	//Affiche des retours sur d�roulement de la partie dans la console apr�s que l'adversaire ait jou�
	protected void retourDefense(Marqueur m, int etat) {
		if (etat == GAGNE)
			System.out.println(this.getNom() +" a perdu contre "+this.getOpponentName()+" !");
		else 
			System.out.println( "a " + this.getNom() + " de jouer");

	}
	

	/**
	 * Cette m�thode est invoqu�e sur le joueur attaquant au d�but d�un tour de jeu. Elle retourne
	   le marqueur que le joueur d�cide de jouer. (Marqueur = Coordonnees + Type) 
	 */
	public Marqueur choisirAttaque() {
		int ligne = (int) (Math.random() * super.getGrille().getTaille());
		int colonne = (int) (Math.random() * super.getGrille().getTaille());
		Coordonnee c = new Coordonnee(ligne,colonne);
		for (int i=0; i<this.getGrille().getnbMarqueurs();i++) {
			if (this.getGrille().getAllMarqueurs()[i] != null) {
				if (this.getGrille().getAllMarqueurs()[i].equals(c))
					this.choisirAttaque();
			}
		}
		Marqueur coupChoisi = this.isCroix() ? new Marqueur(c, true) : new Marqueur (c, false);
		return coupChoisi;
			
	}


	
	public static void main(String[] args) {

		Grille grille1 = new Grille(10);
		System.out.println(grille1);
		
		JoueurIdiot cha = new JoueurIdiot(grille1, "Cha", true);
		JoueurIdiot vic = new JoueurIdiot(grille1, "Vic", false);
		cha.jouerAvec(vic);
		

		
	}

}
