package gomoku;

import java.util.ArrayList;
import java.util.List;


public class JoueurMinimax1 extends JoueurAuto {

	public JoueurMinimax1(Grille g, String nom, boolean isCroix) {
		super(g, nom, isCroix);
	}

	public JoueurMinimax1(Grille g, boolean isCroix) {
		super(g, "Minimax", isCroix);
	}

	// Affiche des retours sur deroulement de la partie dans la console apres avoir
	// joue
	protected void retourAttaque(Marqueur m, int etat) {
		if (etat == GAGNE)
			System.out.println(this.getNom() + " a gagné en jouant en " + m.getCoordonnee() + " contre "
					+ this.getOpponentName() + " !");
		else if (etat == PASGAGNE)
			System.out.println(this.getNom() + " joue en " + m.getCoordonnee());
		else if (etat == EGALITE)
			System.out.println("Egalite entre " + this.getNom() + " et " + this.getOpponentName());
		else
			System.out.println(this.getNom() + " rejoue");

	}

	// Affiche des retours sur deroulement de la partie dans la console apres que
	// l'adversaire ait joue
	protected void retourDefense(Marqueur m, int etat) {
		if (etat == GAGNE)
			System.out.println(this.getNom() + " a perdu contre " + this.getOpponentName() + " !");
		else if (etat == PASGAGNE)
			System.out.println("a " + this.getNom() + " de jouer");
		else if (etat == REJOUE)
			System.out.println(this.getOpponentName() + " rejoue");

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
					 int scoreCoup = minimax(etat, 2, 0, !this.isCroix()); //On evalue le score du coup en question /!\ c'est ici qu'on determine la profondeur de l'arbre minimax
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
		System.out.print("Le temps de calcul est de: ");
		System.out.println(System.currentTimeMillis()-debut);
		 return bestMove;
	 }


	/**
	 * Renvois une liste de int[2] de {ligne,colonne} de tous les coups possible
	 * dans un etat donne ou vide si aucun coup possible
	 * 
	 * @param etat
	 * @return
	 */
	private List<int[]> getCoupsPossibles(char[][] etat) {
		List<int[]> nextMoves = new ArrayList<int[]>();

		for (int i = 0; i < etat.length; i++) {
			for (int j = 0; j < etat.length; j++) {
				if (etat[i][j] == '_') {
					nextMoves.add(new int[] { i, j });
				}
			}
		}
		return nextMoves;
	}

	public boolean isMovesLeft(char[][] etat) {
		return !getCoupsPossibles(etat).isEmpty();
	}

	/**
	 * renvoie une liste des characteres de tous les groupes de 5 cases alignes sur
	 * la grille
	 * 
	 * @param etat
	 * @return
	 */
	public static List<List<Character>> getListOfLines(char[][] etat) {
		List<List<Character>> lesLignes = new ArrayList<List<Character>>();

		for (int i = 0; i < etat.length; i++) { // Pour chaque colonne de la grille
			for (int j = 0; j < etat.length - 4; j++) { // Pour chaque cases de la colonne sauf les 4 dernieres, on
														// enregistre la case + les 4 suivantes
				ArrayList<Character> uneLigne = new ArrayList<Character>();
				uneLigne.add(etat[j][i]);
				uneLigne.add(etat[j + 1][i]);
				uneLigne.add(etat[j + 2][i]);
				uneLigne.add(etat[j + 3][i]);
				uneLigne.add(etat[j + 4][i]);
				lesLignes.add(uneLigne);
			}
		}

		for (int i = 0; i < etat.length; i++) { // Pour chaque ligne de la grille
			for (int j = 0; j < etat.length - 4; j++) { // Pour chaque case de la ligne sauf les 4 dernieres, on
														// enregistre la case + les 4 suivantes
				ArrayList<Character> uneLigne = new ArrayList<Character>();
				uneLigne.add(etat[i][j]);
				uneLigne.add(etat[i][j + 1]);
				uneLigne.add(etat[i][j + 2]);
				uneLigne.add(etat[i][j + 3]);
				uneLigne.add(etat[i][j + 4]);
				lesLignes.add(uneLigne);
			}
		}

		for (int i = 0; i < etat.length - 4; i++) { // Pour chaque diagonale (du haut a gauche jusqu'en bas a droite)
			for (int j = 0; j < etat.length - 4; j++) { // pour chaque ligne sauf les 4 dernieres et chaque colonne sauf
														// les 4 dernieres
				ArrayList<Character> uneLigne = new ArrayList<Character>();
				uneLigne.add(etat[i][j]);
				uneLigne.add(etat[i + 1][j + 1]);
				uneLigne.add(etat[i + 2][j + 2]);
				uneLigne.add(etat[i + 3][j + 3]);
				uneLigne.add(etat[i + 4][j + 4]);
				lesLignes.add(uneLigne);
			}
		}

		for (int i = 0; i < etat.length - 4; i++) { // Pour chaque diagonale (du haut a droite jusqu'en bas a gauche)
			for (int j = etat.length - 1; j >= 4; j--) { // pour chaque ligne sauf les 4 premieres et chaque colonne
															// sauf les 4 dernieres
				ArrayList<Character> uneLigne = new ArrayList<Character>();
				uneLigne.add(etat[i][j]);
				uneLigne.add(etat[i + 1][j - 1]);
				uneLigne.add(etat[i + 2][j - 2]);
				uneLigne.add(etat[i + 3][j - 3]);
				uneLigne.add(etat[i + 4][j - 4]);
				lesLignes.add(uneLigne);
			}
		}
		return lesLignes;
	}

	/**
	 * Renvoie le score correspondant a l'etat du jeu (les 'X' sont MaxPLayer et 'O'
	 * MinPlayer) Pour chaque groupe de 5 cases potentiellement gagnants, si:
	 * 5 croix = 30000 		5 ronds = -30000 
	 * 4croix = +1000 			4 ronds = -1000 
	 * 3croix = +100 			3 ronds = -100 	
	 * 2croix = +10				2 ronds = -10
	 * 
	 * 
	 * 
	 * @param etat
	 * @return le score correspondant à l'etat donné, MaxPlayer=Croix,
	 *         MinPlayer=Ronds
	 */
	public static int evaluate1(char[][] etat) {
		List<List<Character>> lesLignes = new ArrayList<List<Character>>();
		lesLignes = getListOfLines(etat);
		int score = 0;
		boolean gameOver = true;
		for (List<Character> ligne : lesLignes) { // On recupere le nombre de croix et ronds  dans chaque
												// groupe de 5 cases
			int nbCroix = 0;
			int nbRonds = 0;
			for (char uneCase : ligne) {
				switch (uneCase) {
				case '_':
					gameOver = false;
					break;
				case 'X':
					nbCroix++;
					break;
				case 'O':
					nbRonds++;
					break;
				}
			}
			if (nbCroix == 5) // Le cas ou PlayerMax a gagne (les croix)
				return 30000;
			if (nbRonds == 5) // Le cas ou PlayerMin a gagne (les ronds)
				return -30000;
			
			// Si aucun de ces 2 cas, on calcule un score personnalise (ne sont
			// comptabilisees que les groupes de 5 potentiellement gagnants, donc avec un
			// seul type de marqueurs)

			if (nbRonds == 0) {
				switch (nbCroix) {
				case 4:
				//	System.out.println(ligne +"="+ "1000");
					score += 1000;
					break;
				case 3:
				//	System.out.println(ligne +"="+ "100");

					score += 100;
					break;
				case 2:
				//	System.out.println(ligne +"="+ "10");

					score += 10;
					break;
				case 1:
				//	System.out.println(ligne +"="+ "5");

					score += 5;
					break;
				}
			}

			if (nbCroix == 0) {
				switch (nbRonds) {
				case 4:
				//	System.out.println(ligne +"="+ "-1000");

					score -= 1000;
					break;
				case 3:
				//	System.out.println(ligne +"="+ "-100");

					score -= 100;
					break;
				case 2:
				//	System.out.println(ligne +"="+ "-10");

					score -= 10;
					break;
				case 1:
				//	System.out.println(ligne +"="+ "-5");

					score -= 5;
					break;
				}
			}

		}
		if (gameOver) { // Le cas ou la grille est remplie et personne n'a gagne
			System.out.println("grille complete");
			return 0; }

		return score;

	}

	/**
	 * 
	 * @param etat  representtion de la grille de jeu
	 * @param depth profondeur max de l'arbre, permet d'eviter de faire bruler son
	 *              pc
	 * @param isMax vaut vraie si le joueur appelant est MaxPlayer
	 * @return
	 */
	public int minimax(char[][] etat, int depth, int depth2, boolean isMax) {
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
						best = Math.max(best, minimax(etat, depth-1, depth2+1, false)); // Appel recursif de minimax pour
																					// trouver la plus grande valeur
						etat[i][j] = '_'; // On defait le coup pour revenir a l'etat initial
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
						best = Math.min(best, minimax(etat, depth-1, depth2+1, true)); // Appel recursif de minimax pour
																					// trouver la plus petite valeur
						etat[i][j] = '_'; // On defait le coup pour revenir a l'etat initial
					}
				}
			}
			return best;
		}
	}

	

	public static void main(String[] args) {
		char[][] etat = new char[8][8];
//		char[] row1 = { 'a', 'b', 'c', 'd', 'e', 'z', 'g' , 'h'};
//		char[] row2 = { 'a', 'b', 'c', 'd', 'e', 'y', 'g' , 'h'};
//		char[] row3 = { 'a', 'b', 'c', 'd', 'e', 'x', 'g' , 'h'};
//		char[] row4 = { 'a', 'b', 'c', 'q', 'r', 'w', 'g' , 'h'};
//		char[] row5 = { 'd', 'e', 'l', 'm', 's', 'v', 'g' , 'h'};
//		char[] row6 = { 'c', 'f', 'k', 'n', 't', 'u', 'g' , 'h'};
//		char[] row7 = { 'b', 'g', 'j', 'o', 'e', 'f', 'g' , 'h'};
//		char[] row8 = { 'a', 'h', 'i', 'd', 'e', 'f', 'g' , 'h'};
		
		char[] row1 = { 'X', 'O', 'X', 'O', 'O', 'X', 'O','X'};
		char[] row2 = { 'O', 'X', 'O', 'O', 'O', 'X', 'X','O'};
		char[] row3 = { 'X', 'X', 'X', 'X', 'O', 'O', 'X','O'};
		char[] row4 = { 'X', 'O', 'O', 'X', 'X', 'X', 'O','X'};
		char[] row5 = { 'O', 'O', 'O', 'X', 'O', 'X', 'X','X'};
		char[] row6 = { '_', '_', 'X', 'O', '_', 'X', 'O','O'};
		char[] row7 = { '_', 'X', '_', 'O', 'X', '_', 'X','_'};
		char[] row8 = { 'O', '_', '_', '_', '_', 'O', 'O','_'};

		etat[0] = row1;
		etat[1] = row2;
		etat[2] = row3;
		etat[3] = row4;
		etat[4] = row5;
		etat[5] = row6;
		etat[6] = row7;
		etat[7] = row8;

		Grille maGrille = new Grille(8);
		char[][] etat2 = maGrille.getGrilleLogique();
	//	System.out.println(getListOfLines(etat));
	//	System.out.println(evaluate1(etat));
		JoueurMinimax1 j1 = new JoueurMinimax1(maGrille, "j1" , true);
		JoueurMinimax1 j2 = new JoueurMinimax1(maGrille, "j2" , false);
//		System.out.println(evaluate1(etat));
		j1.jouerAvec(j2);
		

	}

}
