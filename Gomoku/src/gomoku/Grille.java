package gomoku;

import java.awt.LinearGradientPaint;
import java.lang.reflect.Array;
import java.security.AllPermission;
import java.util.Arrays;

public class Grille {

	private Marqueur[] ronds; // Enregistrement des marqueurs de type ronds plac�s sur la grille
	private Marqueur[] croix; // Marqueurs de type croix sur la grille
	private Coordonnee[] allMarqueurs; // Toutes les coordonn�es de marqueurs plac�s sur la grille(croix ET rond)
	private int taille;
	private int nbMarqueurs;
	private int nbCroix;
	private int nbRonds;

	public Grille(int taille) {
		this.taille = taille;
		this.allMarqueurs = new Coordonnee[taille * taille];
		this.ronds = new Marqueur[(taille * taille) / 2+1];

		this.croix = new Marqueur[((taille * taille) / 2)+1];

		this.nbCroix = 0;
		this.nbMarqueurs = 0;
		this.nbRonds = 0;
	}

	public Coordonnee[] getAllMarqueurs() {
		return allMarqueurs;
	}

	public int getnbMarqueurs() {
		return nbMarqueurs;
	}

	public int getTaille() {
		return this.taille;
	}

	/**
	 * Ajoute un nouveau Marqueur dans allMarqueurs et dans le tableau correspondant
	 * � son type si il n'a pas d�j� �t� plac�
	 * 
	 * @param Marqueur m � ajouter
	 * @return vrai si les tableaux ont �t� modifi�s, faux sinon
	 */
	public boolean ajouteMarqueur(Marqueur m) {
		if (!estDansGrille(m))
			return false;
		for (int i = 0; i < nbMarqueurs; i++) {
			if (allMarqueurs[i].equals(m.getCoordonnee()))
				return false;
		}

		if (m.isCroix()) {
			croix[nbCroix] = m;
			nbCroix++;

		} else {
			ronds[nbRonds] = m;
			nbRonds++;

		}
		allMarqueurs[nbMarqueurs] = m.getCoordonnee();
		nbMarqueurs++;
		System.out.println(this.toString());
		return true;
	}

	/**
	 * Retourne true si et seulement si la Coordonne correspondant � [ligne,col] est
	 * valide dans la grille this.
	 * 
	 * @param ligne, col : int
	 * @return : true si la coord est valide dans la grille, et false si elle ne
	 *         'lest pas
	 */

	private boolean estDansGrille(int ligne, int col) {
		if (col >= 0 && col < this.taille && ligne >= 0 && ligne < this.taille)
			return true;
		else
			return false;
	}

	private boolean estDansGrille(Marqueur m) {
		if (m.getCoordonnee().getColonne() >= 0 && m.getCoordonnee().getColonne() < this.taille
				&& m.getCoordonnee().getLigne() >= 0 && m.getCoordonnee().getLigne() < this.taille)
			return true;
		else
			return false;
	}

	/**
	 * Retourne true si et seulement si c correspond à un coup joue dans this.
	 * 
	 * @param c : Marqueur
	 * @return : true si c est dans allMarqueurs, et false si c n'est pas dans
	 *         allMarqueurs.
	 */

	private boolean hasMarqueur(Coordonnee c) {
		for (int i = 0; i < nbMarqueurs; i++)
			if (allMarqueurs[i] != null && allMarqueurs[i] == c)
				return true;
		return false;
	}

	private boolean hasCroix(Coordonnee c) {
		for (int i = 0; i < nbCroix; i++)
			if (croix[i] != null && croix[i].getCoordonnee() == c)
				return true;
		return false;
	}

	private boolean hasRond(Coordonnee c) {
		for (int i = 0; i < nbRonds; i++)
			if (ronds[i] != null && ronds[i].getCoordonnee() == c)
				return true;
		return false;
	}
	
	public boolean isFull() {
		return this.nbMarqueurs==this.taille*this.taille;
	}

	/**
	 * Cherche les voisins (diagonales comprises) du m�me type que m (croix/rond)
	 * 
	 * @param m
	 * @return tableau des Marqueur du m�me type que m et en contact avec m /!\ Le
	 *         tableau retourn� contient des valeurs null /!\
	 */
	private Marqueur[] voisins(Marqueur m) {
		Marqueur[] res = new Marqueur[8];
		int resIndex = 0;

		if (m.isCroix()) {
			for (int i = 0; i < nbCroix; i++) {
				if (croix[i] != m && croix[i].touche(m.getCoordonnee())) {
					res[resIndex] = croix[i];
					resIndex++;
				}
			}
		} else {
			for (int i = 0; i < nbRonds; i++) {
				if (ronds[i] != m && ronds[i].touche(m.getCoordonnee())) {
					res[resIndex] = ronds[i];
					resIndex++;
				}
			}
		}

		return res;
	}

	/**
	 * V�rifie si m appartient � une ligne de 5 ou plus marqueurs de son type
	 * (auquel cas la partie est gagn�e)
	 * 
	 * @param m
	 * @return true si m constitue une ligne de 5 ou plus marqueurs de son type
	 */

	public boolean fiveInARow(Marqueur m) {
		Marqueur[][] lesLignes = this.lignesDeMarqueur(m);
		for (int i = 0; i < lesLignes.length; i++) {
			int total = 0;
			if (lesLignes[i] != null) {
				for (int j = 0; j < lesLignes[i].length; j++) {
					if (lesLignes[i][j] != null)
						total += 1;
				}
				if (total >= 5)
					return true;
			}
		}
		return false;
	}

	/**
	 * Pour un marqueur m donn�, renvoie un tableau des diff�rentes lignes dont il
	 * fait partie sous forme des tableaux de marqueurs. Attention m�thode de 500
	 * lignes, il est conseill� de la r�duire :)
	 * 
	 * @param m: Marqueur � scanner
	 * @return Un tableau contenant les lignes du marqueur m. Une ligne est un
	 *         tableau de marqueurs
	 */
	private Marqueur[][] lignesDeMarqueur(Marqueur m) {
		Marqueur[][] res = new Marqueur[8][9]; // 8 lignes maximum par points et 9 marqueurs maximum par ligne (si 4 de
												// chaque c�t� et on pose un marqueur au milieu)
		int resIndex = 0;
		Marqueur[] voisins = voisins(m);
		int mligne = m.getCoordonnee().getLigne();
		int mcol = m.getCoordonnee().getColonne();
		Marqueur[] sameTypeMarq = m.isCroix() ? croix : ronds;
		int nbSameTypeM = m.isCroix() ? nbCroix : nbRonds;

		for (int i = 0; i < voisins.length; i++) { // Pour chaque voisin(du m�me type) que m
			if (voisins[i] != null) {
				Marqueur[] ligne = new Marqueur[9];
				ligne[0] = m;
				int ligneIndex = 1;
				Coordonnee target;
				boolean found = false;
				int diffLigne = mligne - voisins[i].getCoordonnee().getLigne(); // On situe la direction entre m et son
																				// voisin
				int diffCol = mcol - voisins[i].getCoordonnee().getColonne();

				if ((diffLigne == -1 && diffCol == -1) || (diffLigne == 1 && diffCol == 1)) {
					// Si le voisin est en dessous � droite ou au dessus � gauche -- On parcours en
					// bas � droite ET en haut � gauche

					if (this.estDansGrille(mligne + 1, mcol + 1)) {
						target = new Coordonnee(mligne + 1, mcol + 1); // 1cases en bas et 1 � droite

						for (int j = 0; j < nbSameTypeM; j++) {
							if (sameTypeMarq[j].getCoordonnee().equals(target)) {
								ligne[ligneIndex] = sameTypeMarq[j];
								ligneIndex++;
								found = true;
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne + 2, mcol + 2)) {
							target = new Coordonnee(mligne + 2, mcol + 2); // 2cases en bas et 2 � droite

							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}
					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne + 3, mcol + 3)) {
							target = new Coordonnee(mligne + 3, mcol + 3); // 3cases en bas et 3 � droite

							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne + 4, mcol + 4)) {
							target = new Coordonnee(mligne + 4, mcol + 4); // 4cases en bas et 4 � droite

							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
								}
							}
						}
					}

					found = false;

					if (this.estDansGrille(mligne - 1, mcol - 1)) {
						target = new Coordonnee(mligne - 1, mcol - 1); // 1 case en haut et 1 � gauche

						for (int j = 0; j < nbSameTypeM; j++) {
							if (sameTypeMarq[j].getCoordonnee().equals(target)) {
								ligne[ligneIndex] = sameTypeMarq[j];
								ligneIndex++;
								found = true;
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne - 2, mcol - 2)) {
							target = new Coordonnee(mligne - 2, mcol - 2); // 2 case en haut et 2 � gauche
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne - 3, mcol - 3)) {
							target = new Coordonnee(mligne - 3, mcol - 3); // 3 case en haut et 3 � gauche
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}
					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne - 4, mcol - 4)) {
							target = new Coordonnee(mligne - 4, mcol - 4); // 4 case en haut et 4 � gauche
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
								}
							}
						}
					}

				}

				if ((diffLigne == -1 && diffCol == 0) || (diffLigne == 1 && diffCol == 0)) {
					// Si le voisin est sur la m�me colonne -- On parcours en bas ET en haut
					found = false;

					if (this.estDansGrille(mligne + 1, mcol)) {
						target = new Coordonnee(mligne + 1, mcol); // 1 case en bas

						for (int j = 0; j < nbSameTypeM; j++) {
							if (sameTypeMarq[j].getCoordonnee().equals(target)) {
								ligne[ligneIndex] = sameTypeMarq[j];
								ligneIndex++;
								found = true;
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne + 2, mcol)) {
							target = new Coordonnee(mligne + 2, mcol); // 2 cases en bas
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne + 3, mcol)) {
							target = new Coordonnee(mligne + 3, mcol); // 3 cases en bas
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne + 4, mcol)) {
							target = new Coordonnee(mligne + 4, mcol); // 4 cases en bas
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
								}
							}
						}
					}

					found = false;

					if (this.estDansGrille(mligne - 1, mcol)) {
						target = new Coordonnee(mligne - 1, mcol); // 1 case en haut

						for (int j = 0; j < nbSameTypeM; j++) {
							if (sameTypeMarq[j].getCoordonnee().equals(target)) {
								ligne[ligneIndex] = sameTypeMarq[j];
								ligneIndex++;
								found = true;
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne - 2, mcol)) {
							target = new Coordonnee(mligne - 2, mcol); // 2 cases en haut
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne - 3, mcol)) {
							target = new Coordonnee(mligne - 3, mcol); // 3 cases en haut
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne - 4, mcol)) {
							target = new Coordonnee(mligne - 4, mcol); // 4 cases en haut
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
								}
							}
						}
					}

				}

				if ((diffLigne == -1 && diffCol == 1) || (diffLigne == 1 && diffCol == -1)) {

					// case 1: // Si le voisin est en bas � gauche ou en haut � droite -- On
					// parcours en bas � gauche ET en haut � droite

					found = false;

					if (this.estDansGrille(mligne + 1, mcol - 1)) {
						target = new Coordonnee(mligne + 1, mcol - 1); // 1 case en bas et 1 � gauche

						for (int j = 0; j < nbSameTypeM; j++) {
							if (sameTypeMarq[j].getCoordonnee().equals(target)) {
								ligne[ligneIndex] = sameTypeMarq[j];
								ligneIndex++;
								found = true;
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne + 2, mcol - 2)) {
							target = new Coordonnee(mligne + 2, mcol - 2); // 2 case en bas et 2 � gauche
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne + 3, mcol - 3)) {
							target = new Coordonnee(mligne + 3, mcol - 3); // 3 case en bas et 3 � gauche
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}
					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne + 4, mcol - 4)) {
							target = new Coordonnee(mligne + 4, mcol - 4); // 4 case en bas et 4 � gauche
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
								}
							}
						}
					}

					found = false;

					if (this.estDansGrille(mligne - 1, mcol + 1)) {
						target = new Coordonnee(mligne - 1, mcol + 1); // 1 case en haut et 1 � droite

						for (int j = 0; j < nbSameTypeM; j++) {
							if (sameTypeMarq[j].getCoordonnee().equals(target)) {
								ligne[ligneIndex] = sameTypeMarq[j];
								ligneIndex++;
								found = true;
							}
						}
					}

					if (found == true) {
						found = false;
						if (this.estDansGrille(mligne - 2, mcol + 2)) {
							target = new Coordonnee(mligne - 2, mcol + 2); // 2 case en haut et 2 � droite
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;
						if (this.estDansGrille(mligne - 3, mcol + 3)) {
							target = new Coordonnee(mligne - 3, mcol + 3); // 3 case en haut et 3 � droite
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;
						if (this.estDansGrille(mligne - 4, mcol + 4)) {
							target = new Coordonnee(mligne - 4, mcol + 4); // 4 case en haut et 4 � droite
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
								}
							}
						}
					}

				}

				if ((diffLigne == 0 && diffCol == 1) || (diffLigne == 0 && diffCol == -1)) {

					// Si le voisin est sur la m�me ligne, on test uniquement l'axe horizontal

					found = false;

					if (this.estDansGrille(mligne, mcol + 1)) {
						target = new Coordonnee(mligne, mcol + 1); // 1 case � droite

						for (int j = 0; j < nbSameTypeM; j++) {
							if (sameTypeMarq[j].getCoordonnee().equals(target)) {
								ligne[ligneIndex] = sameTypeMarq[j];
								ligneIndex++;
								found = true;
							}
						}
					}
					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne, mcol + 2)) {
							target = new Coordonnee(mligne, mcol + 2); // 2 case � droite
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne, mcol + 3)) {
							target = new Coordonnee(mligne, mcol + 3); // 3 case � droite
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne, mcol + 4)) {
							target = new Coordonnee(mligne, mcol + 4); // 4 case � droite
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
								}
							}
						}
					}

					found = false;

					if (this.estDansGrille(mligne, mcol - 1)) {
						target = new Coordonnee(mligne, mcol - 1); // 1 case � gauche

						for (int j = 0; j < nbSameTypeM; j++) {
							if (sameTypeMarq[j].getCoordonnee().equals(target)) {
								ligne[ligneIndex] = sameTypeMarq[j];
								ligneIndex++;
								found = true;
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne, mcol - 2)) {
							target = new Coordonnee(mligne, mcol - 2); // 2 cases � gauche
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne, mcol - 3)) {
							target = new Coordonnee(mligne, mcol - 3); // 3 cases � gauche
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
									found = true;
								}
							}
						}
					}

					if (found == true) {
						found = false;

						if (this.estDansGrille(mligne, mcol - 4)) {
							target = new Coordonnee(mligne, mcol - 4); // 4 cases � gauche
							for (int j = 0; j < nbSameTypeM; j++) {
								if (sameTypeMarq[j].getCoordonnee().equals(target)) {
									ligne[ligneIndex] = sameTypeMarq[j];
									ligneIndex++;
								}
							}
						}
					}

				}
				res[resIndex] = ligne;
				resIndex++;
				ligne = new Marqueur[5];
				ligneIndex = 0;
			}

		}
		return res;

	}

	/**
	 * Retourne une String representant this. On souhaite obtenir une
	 * repr�sentation s'affichant sur la console.
	 */

	public String toString() {

		StringBuffer grilleNavale = new StringBuffer("  ");
		char c = 'A';
		int l = 1;
		for (int i = 0; i < this.taille; i++) { // On cree la 1ere ligne
			grilleNavale = grilleNavale.append(' ');
			grilleNavale = grilleNavale.append(c);

			c++;
		}

		for (int j = 0; j < this.taille; j++) { // on ajoute les autres lignes

			grilleNavale = grilleNavale.append("\n");
			if (l < 10)
				grilleNavale = grilleNavale.append(' ');
			grilleNavale = grilleNavale.append(l);
			l++;
			for (int k = 0; k < this.taille; k++) {
				grilleNavale = grilleNavale.append(" .");
			}
		}

		for (int i = 0; i < croix.length; i++) { // On ajoute les croix
			if (croix[i] != null) {
				grilleNavale.replace(
						((2 + 2 * taille + 4) + (2 * taille + 3) * croix[i].getCoordonnee().getLigne())
								+ croix[i].getCoordonnee().getColonne() * 2,
						((2 + 2 * taille + 5) + (2 * taille + 3) * croix[i].getCoordonnee().getLigne())
								+ croix[i].getCoordonnee().getColonne() * 2,
						"X");
			}
		}

		for (int j = 0; j < ronds.length; j++) { // On ajoute les ronds
			if (ronds[j] != null) {
				grilleNavale.replace(
						((2 + 2 * taille + 4) + (2 * taille + 3) * ronds[j].getCoordonnee().getLigne())
								+ ronds[j].getCoordonnee().getColonne() * 2,
						((2 + 2 * taille + 5) + (2 * taille + 3) * ronds[j].getCoordonnee().getLigne())
								+ ronds[j].getCoordonnee().getColonne() * 2,
						"O");
			}
		}

		return grilleNavale.toString();
	}

	public static void main(String[] args) {
		Marqueur monMarq1 = new Marqueur(new Coordonnee(2, 4), true);
		Marqueur monMarq2 = new Marqueur(new Coordonnee(2, 4), false);
//		Marqueur monMarq3 = new Marqueur(new Coordonnee(4, 2), false);
//		Marqueur monMarq8 = new Marqueur(new Coordonnee(2, 3), false);
//		Marqueur monMarq9 = new Marqueur(new Coordonnee(2, 2), false);
//		Marqueur monMarq10 = new Marqueur(new Coordonnee(1, 5), false);
//		Marqueur monMarq11 = new Marqueur(new Coordonnee(1, 1), false);
//		Marqueur monMarq12 = new Marqueur(new Coordonnee(1, 3), false);
//		Marqueur monMarq13 = new Marqueur(new Coordonnee(3, 5), false);
//		Marqueur monMarq14 = new Marqueur(new Coordonnee(3, 2), false);
//		Marqueur monMarq15 = new Marqueur(new Coordonnee(3, 1), false);
//		Marqueur monMarq16 = new Marqueur(new Coordonnee(5, 5), false);
//		Marqueur monMarq17 = new Marqueur(new Coordonnee(11, 11), false);
//
//		Marqueur monMarq4 = new Marqueur(new Coordonnee(4, 3), false);
//		Marqueur monMarq5 = new Marqueur(new Coordonnee(4, 4), true);
//		Marqueur monMarq6 = new Marqueur(new Coordonnee(3, 4), false);
//		Marqueur monMarq7 = new Marqueur(new Coordonnee(6, 1), false);
//		Marqueur monMarq18 = new Marqueur(new Coordonnee(6, 6), false);
//		Marqueur monMarq19 = new Marqueur(new Coordonnee(7, 7), false);
//
//		Marqueur monMarq20 = new Marqueur(new Coordonnee(13, 13), false);
//		Marqueur monMarq21 = new Marqueur(new Coordonnee(14, 14), false);
//		Marqueur monMarq22 = new Marqueur(new Coordonnee(15, 15), false);
//		Marqueur monMarq23 = new Marqueur(new Coordonnee(16, 16), false);
//		Marqueur monMarq24 = new Marqueur(new Coordonnee(17, 17), false);

		Grille maGrille = new Grille(22);
		System.out.println(maGrille.ajouteMarqueur(monMarq1));
		System.out.println(maGrille.ajouteMarqueur(monMarq2));
//		maGrille.ajouteMarqueur(monMarq3);
//		maGrille.ajouteMarqueur(monMarq4);
//		maGrille.ajouteMarqueur(monMarq5);
//		maGrille.ajouteMarqueur(monMarq6);
//		maGrille.ajouteMarqueur(monMarq7);
//		maGrille.ajouteMarqueur(monMarq8);
//		maGrille.ajouteMarqueur(monMarq9);
//		maGrille.ajouteMarqueur(monMarq10);
//		maGrille.ajouteMarqueur(monMarq11);
//		maGrille.ajouteMarqueur(monMarq12);
//		maGrille.ajouteMarqueur(monMarq13);
//		maGrille.ajouteMarqueur(monMarq14);
//		maGrille.ajouteMarqueur(monMarq15);
//		maGrille.ajouteMarqueur(monMarq16);
//		maGrille.ajouteMarqueur(monMarq17);
//		maGrille.ajouteMarqueur(monMarq18);
//		maGrille.ajouteMarqueur(monMarq19);
//
//		maGrille.ajouteMarqueur(monMarq20);
//		maGrille.ajouteMarqueur(monMarq21);
//		maGrille.ajouteMarqueur(monMarq22);
//		maGrille.ajouteMarqueur(monMarq23);
//		maGrille.ajouteMarqueur(monMarq24);

		System.out.println(maGrille);
		for (int i = 0 ; i< maGrille.ronds.length;i++) {
			if (maGrille.ronds[i] != null) System.out.println("Ronds : " + maGrille.ronds[i].toString());
		}

		for (int i = 0 ; i< maGrille.croix.length;i++) {
			if (maGrille.croix[i] != null) System.out.println("Croix : " + maGrille.croix[i].toString());
		}


	}

}
