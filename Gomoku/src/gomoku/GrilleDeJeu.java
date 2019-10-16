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
		
		/**
		 * Spécialisation de la méthode héritée de GrilleNavale. 
		 * La case correspondant au tir doit être coloriée en Color.RED 
		 * si le tir a touché un navire ou en Color.BLUE s'il est à l'eau.
		 * @param Coordonnee c
		 * @return boolean
		 */
		
//		public boolean recoitTir(Coordonnee c) {
//			if (super.recoitTir(c)) {
//				grille.colorie(c, Color.RED);
//				//System.out.println("est touche");
//				return true;
//			} else if (super.estALEau(c)) {
//				grille.colorie(c, Color.BLUE);
//				//System.out.println("est a l'eau");
//				return true;
//			}
//			return false;
//		}
//		
		
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
