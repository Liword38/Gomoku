package gomoku;



public class JoueurIdiot extends JoueurAuto {

	private Marqueur coupChoisi;
	
	public JoueurIdiot(Grille g, String nom, boolean isCroix) {
		super(g,nom,isCroix);

		
		
	}
		
	public JoueurIdiot(Grille g, boolean isCroix) {
		super(g, "Jack Sparrow", isCroix);


		
	}
	
	//Affiche des retours sur dï¿½roulement de la partie dans la console aprï¿½s avoir jouï¿½
	protected void retourAttaque(Marqueur m, int etat) {
		if (etat == GAGNE)
			System.out.println( this.getNom() +" a gagné en jouant en "+m.getCoordonnee()+ " contre "+this.getOpponentName() + " !");
		else  if (etat == PASGAGNE)
			System.out.println( this.getNom() + " joue en "+ m.getCoordonnee());
		else if (etat == EGALITE)
			System.out.println("Egalite entre " + this.getNom() + " et " + this.getOpponentName());
		else 
			System.out.println(this.getNom()+" rejoue");

	}
	
	//Affiche des retours sur dï¿½roulement de la partie dans la console aprï¿½s que l'adversaire ait jouï¿½
	protected void retourDefense(Marqueur m, int etat) {
		if (etat == GAGNE)
			System.out.println(this.getNom() +" a perdu contre "+this.getOpponentName()+" !");
		else if (etat == PASGAGNE)
			System.out.println( "a " + this.getNom() + " de jouer");
		else if (etat == REJOUE)
			System.out.println(this.getOpponentName()+ " rejoue");

	}
	

	/**
	 * Cette mï¿½thode est invoquï¿½e sur le joueur attaquant au dï¿½but dï¿½un tour de jeu. Elle retourne
	   le marqueur que le joueur dï¿½cide de jouer. (Marqueur = Coordonnees + Type) 
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
