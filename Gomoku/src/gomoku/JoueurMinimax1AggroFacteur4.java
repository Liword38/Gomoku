package gomoku;

import java.util.ArrayList;
import java.util.List;

public class JoueurMinimax1AggroFacteur4 extends JoueurMinimax1 {

	public JoueurMinimax1AggroFacteur4(Grille g, String nom, boolean isCroix) {
		super(g, nom, isCroix);
		// TODO Auto-generated constructor stub
	}

	public JoueurMinimax1AggroFacteur4(Grille g, boolean isCroix) {
		super(g, isCroix);
		// TODO Auto-generated constructor stub
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
		int score = evaluateAggroFacteur4(etat);
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
	
	/**
	 * Renvoie le score correspondant a l'etat du jeu (les 'X' sont MaxPLayer et 'O'
	 * MinPlayer) Pour chaque groupe de 5 cases potentiellement gagnants, si:
	 * 5 croix = 30000 		5 ronds = -30000 
	 * 4croix = +1000 			4 ronds = -1000 
	 * 3croix = +100 			3 ronds = -100 	
	 * 2croix = +10				2 ronds = -10
	 * 
	 * Le score de l'adversaire est multiplié par 3.
	 * 
	 * @param etat
	 * @return le score correspondant à l'etat donné, MaxPlayer=Croix,
	 *         MinPlayer=Ronds
	 */
	public int evaluateAggroFacteur4(char[][] etat) {
		List<List<Character>> lesLignes = new ArrayList<List<Character>>();
		lesLignes = getListOfLines(etat);
		int score = 0;
		int scoreMaxPlayer=0;
		int scoreMinPlayer=0;
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
			// Si aucun de ces 3 cas, on calcule un score personnalise (ne sont
			// comptabilisees que les groupes de 5 potentiellement gagnants, donc avec un
			// seul type de marqueurs)

			if (nbRonds == 0) {
				switch (nbCroix) {
//				case 5:
//					score += 10000;
//					break;
				case 4:
				//	System.out.println(ligne +"="+ "1000");
					scoreMaxPlayer += 1000;
					break;
				case 3:
				//	System.out.println(ligne +"="+ "100");

					scoreMaxPlayer += 100;
					break;
				case 2:
				//	System.out.println(ligne +"="+ "10");

					scoreMaxPlayer += 10;
					break;
				case 1:
				//	System.out.println(ligne +"="+ "5");

					scoreMaxPlayer += 5;
					break;
				}
			}

			if (nbCroix == 0) {
				switch (nbRonds) {
//				case 5:
//					score -= 10000;
//					break;
				case 4:
				//	System.out.println(ligne +"="+ "-1000");

					scoreMinPlayer += 1000;
					break;
				case 3:
				//	System.out.println(ligne +"="+ "-100");

					scoreMinPlayer += 100;
					break;
				case 2:
				//	System.out.println(ligne +"="+ "-10");

					scoreMinPlayer += 10;
					break;
				case 1:
				//	System.out.println(ligne +"="+ "-5");

					scoreMinPlayer += 5;
					break;
				}
			}

		}
		if (gameOver) { // Le cas ou la grille est remplie et personne n'a gagne
			System.out.println("grille complete");
			return 0; }
		if (this.isCroix())
			scoreMinPlayer *=4;
		else 
			scoreMaxPlayer *=4;
		score = scoreMaxPlayer - scoreMinPlayer;
		return score;

	}

	public static void main(String[] args) {
		Grille maGrille = new Grille(8);

		JoueurMinimax1 j1 = new JoueurMinimax1AggroFacteur2(maGrille, "minimaxFacteur2" , true);
		JoueurMinimax1 j2 = new JoueurMinimax1AggroFacteur3(maGrille, "minimaxFacteur3" , false);

		j1.jouerAvec(j2);
		
	}
}
