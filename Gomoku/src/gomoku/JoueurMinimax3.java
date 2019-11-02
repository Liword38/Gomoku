package gomoku;

import java.util.ArrayList;
import java.util.List;

public class JoueurMinimax3 extends JoueurMinimax1 {

	public JoueurMinimax3(Grille g, String nom, boolean isCroix) {
		super(g, nom, isCroix);
		// TODO Auto-generated constructor stub
	}

	public JoueurMinimax3(Grille g, boolean isCroix) {
		super(g, "minimax3", isCroix);
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
		int score = evaluate3(etat);
		//score = isMax ? score-depth2*10 : score+depth2*10; 

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
	 * Renvoie le nombre de lignes gagnantes de PlayerMax moins le nombre de lignes gagnantes de J2
	 * 
	 * 
	 * @param etat
	 * @return le nombre de lignes gagnantes de PlayerMax moins le nombre de lignes gagnantes de J2. MaxPlayer=Croix,
	 *         MinPlayer=Ronds
	 */
	public static int evaluate3(char[][] etat) {
		List<List<Character>> lesLignes = new ArrayList<List<Character>>();
		lesLignes = getListOfLines(etat);
		int score = 0;
		int scoreMaxPlayer = 0;
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
			
			int currentScoreMax=0;
			int currentScoreMin=0;
			if (nbRonds == 0) {
				currentScoreMax = nbCroix;
			}

			if (nbCroix == 0) {
				currentScoreMin = nbRonds;
			}
			scoreMaxPlayer = currentScoreMax > scoreMaxPlayer ? currentScoreMax : scoreMaxPlayer;
			scoreMinPlayer = currentScoreMin > scoreMinPlayer ? currentScoreMin : scoreMinPlayer;

		}
		if (gameOver) { // Le cas ou la grille est remplie et personne n'a gagne
			System.out.println("grille complete");
			return 0; }
		score = scoreMaxPlayer-scoreMinPlayer;
		return score;

	}

	public static void main(String[] args) {
		Grille maGrille = new Grille(8);
		//char[][] etat2 = maGrille.getGrilleLogique();
		JoueurMinimax1 minimax2 = new JoueurMinimax3(maGrille, "minimax2" , true);
		JoueurMinimax1 minimax3 = new JoueurMinimax3(maGrille, "minimax3" , false);

		minimax2.jouerAvec(minimax3);
	}
}