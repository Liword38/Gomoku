package gomoku;

public class AlphaBetaMinimax1 extends JoueurMinimax1 {

	public AlphaBetaMinimax1(Grille g, String nom, boolean isCroix) {
		super(g, nom, isCroix);
		// TODO Auto-generated constructor stub
	}

	public AlphaBetaMinimax1(Grille g, boolean isCroix) {
		super(g, isCroix);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Cette methode est invoquee sur le joueur attaquant au debut d'un tour de jeu.
	 * Elle retourne le marqueur que le joueur decide de jouer. (Marqueur =
	 * Coordonnees + Type)
	 */
	 public Marqueur choisirAttaque() {
		 long debut = System.currentTimeMillis();
		 //Si c'est le 1er tour on joue au hasard
		 if (this.getGrille().getnbMarqueurs()==0) {	 
			 int ligne = (int) (Math.random() * super.getGrille().getTaille());
			 int colonne = (int) (Math.random() * super.getGrille().getTaille());
			 Coordonnee c = new Coordonnee(ligne,colonne);
			 Marqueur m = new Marqueur(c,this.isCroix());
			 return m;
		 }
		 
		 //Sinon on appelle minimax sur tous les coups possibles
		 int bestValue = this.isCroix() ? -20000 : 20000;
		 char[][] etat = this.getGrille().getGrilleLogique();
		 Marqueur bestMove = new Marqueur (new Coordonnee(0,0), isCroix());

		 for (int i=0 ; i<this.getTailleGrille() ; i++) {	//On traverse toutes les cases de la grille
			 for (int j=0; j<this.getTailleGrille() ; j++) {
				 
				 if (etat[i][j] == '_') {	//Si une case est vide on joue le coup
					 
					 etat[i][j] = this.isCroix() ? 'X' : 'O';
					 int scoreCoup = alphaBeta(etat, 4, 0,-30000, 30000, !this.isCroix()); //On evalue le score du coup en question /!\ c'est ici qu'on determine la profondeur de l'arbre minimax
					 etat[i][j] = '_'; //On defait le coup joue
					 
					 if (this.isCroix()) {
						 if (scoreCoup > bestValue || bestValue==-20000) {
							 bestMove.setCoordonnee(new Coordonnee(i,j));;
							 bestValue=scoreCoup;
						 }
					 }
					 else {
						 if (scoreCoup < bestValue || bestValue==20000) {
							 bestMove.setCoordonnee(new Coordonnee(i,j));;
							 bestValue=scoreCoup;
						 }
					 }
				 }
			 }
		 }
		 System.out.println("Le score du meilleur coup est: " + bestValue + " isCroix="+ isCroix()+" en " + bestMove.getCoordonnee());
		 System.out.println(System.currentTimeMillis()-debut);
		 return bestMove;
	 }
	
	/**
	 * 
	 * @param etat  representtion de la grille de jeu
	 * @param depth profondeur max de l'arbre, permet d'eviter de faire bruler son
	 *              pc
	 * @param isMax vaut vraie si le joueur appelant est MaxPlayer
	 * @return
	 */
	public int alphaBeta(char[][] etat, int depth, int depth2,int alpha, int beta, boolean isMax) {
		int score = evaluate1(etat);
		score = isMax ? score-depth2*10 : score+depth2*10; 

		if (score > 9000 || score < -9000 || depth == 0 || !isMovesLeft(etat)) // Si quelqu'un a gagne la partie, si on arrive au plus bas niveau souhaite de l'arbre, ou s'il y a match nul: on renvoie le score de l'etat courant
			return score;

		//Sinon on cherche chez les enfants du noeud courant
		
		if (isMax) { // Si c'est au tour de PlayerMax
			int best = -20000;

			for (int i = 0; i < etat.length; i++) {
				for (int j = 0; j < etat.length; j++) { // Si une case est vide on joue le coup
					if (etat[i][j] == '_') {
						etat[i][j] = 'X';
						best = Math.max(best, alphaBeta(etat, depth-1, depth2+1, alpha, beta, false)); // Appel recursif de minimax pour
																					// trouver la plus grande valeur
						etat[i][j] = '_'; // On defait le coup pour revenir a l'etat initial
						if(best >= beta)//Coupure beta
							return best;
						alpha=Math.max(alpha, best);
					}
				}
			}
			return best;
		}

		else { // Si c'est a MinPlayer de jouer
			int best = 20000;
			

			for (int i = 0; i < etat.length; i++) {
				for (int j = 0; j < etat.length; j++) { // Si une case est vide on joue le coup
					if (etat[i][j] == '_') {
						etat[i][j] = 'O';
						best = Math.min(best, alphaBeta(etat, depth-1, depth2+1, alpha, beta, true)); // Appel recursif de minimax pour
																					// trouver la plus petite valeur						
						etat[i][j] = '_'; // On defait le coup pour revenir a l'etat initial
						if(alpha >= best) //Coupure alpha
							return best;
						beta=Math.min(beta, best);
					}
				}
			}
			return best;
		}
	}
	
	public static void main(String[] args) {
		Grille maGrille = new Grille(8);
		//char[][] etat2 = maGrille.getGrilleLogique();
		JoueurMinimax1 minimax2 = new AlphaBetaMinimax1(maGrille, "alphabeta" , true);
		JoueurMinimax1 minimax3 = new AlphaBetaMinimax1(maGrille, "alphabeta" , false);

		minimax2.jouerAvec(minimax3);
	}
}
