package gomoku;

import java.awt.Color;

public class GrilleDeJeu extends Grille{
	
	private GrilleGraphique grille;

	
		/**
		 * CONSTRUCTEUR
		 * permet d'obtenir une grille de taille taille.
		 * @param taille
		 */
		
		public GrilleDeJeu(int taille) {
			super(taille);
			this.grille = new GrilleGraphique(taille);
		}
		
		/**
		 * Accesseur en lecture pour grille.
		 * @return GrilleGraphique
		 */
		
		public GrilleGraphique getGrilleGraphique() {
			return grille;
		}
			
		
		/**
		 * Ajoute un marqueur m à this et colorie la grille graphique de la couleur correspondant au type du marqueur
		 */
		public boolean ajouteMarqueur(Marqueur m) {
			if (super.ajouteMarqueur(m)) {
				if (m.isCroix())
					grille.colorie(m.getCoordonnee(), Color.GREEN);
				else
					grille.colorie(m.getCoordonnee(), Color.RED);
				return true;
			}
			return false;
		}
			
		
		public static void main(String[] args) {
			GrilleDeJeu g1 = new GrilleDeJeu(5);
			
			// TEST getGrilleGraphique
			System.out.println(g1.getGrilleGraphique());
			
			// TEST ajouteNavire OK
			Coordonnee c1 = new Coordonnee(3,3);
			Marqueur n1 = new Marqueur(c1,false);
			System.out.println(g1.ajouteMarqueur(n1));
	
			
		}

}
