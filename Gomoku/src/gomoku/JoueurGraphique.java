package gomoku;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class JoueurGraphique extends JoueurAvecGrille {
	
	private GrilleGraphique grilleJeu;

	
	
	/**
	 * Permet d'obtenir un joueur graphique de nom nom qui  joue sur grilleJeu 
	 * @param grilleJeu : La grille de jeu
	 * @param nom
	 */
	
	public JoueurGraphique(GrilleDeJeu grilleJeu, String nom, boolean isCroix) {
		super(grilleJeu,nom,isCroix);
		
	}
	
	
	
	/**
	 * Permet d'obtenir un joueur graphique de nom nom qui  joue sur grilleJeu 
	 * @param grilleJeu : La grille de jeu
	 */
	
	public JoueurGraphique(GrilleDeJeu grilleJeu, boolean isCroix) {
		super(grilleJeu, isCroix);
	}
	
	
	
	/**
	 * Consiste à récupérer la coordonnée choisie depuis grilleJeu.
	 */
	
	public Marqueur choisirAttaque() {
		Coordonnee c= grilleJeu.getCoordonneeSelectionnee();
		Marqueur m = this.isCroix() ? new Marqueur(c,true) : new Marqueur(c,false);
		return m;
		
	}
	
	
	
	/**
	 * Affichage d'un JOptionPane lorsque le tir a touché ou coulé un navire, ou lorsque la partie
	   est perdue.
	 */
	
	protected void retourDefense(Marqueur m,int etat) {
		JOptionPane message = new JOptionPane();
		Color couleur = m.isCroix() ? Color.BLUE : Color.RED;
		grilleJeu.colorie(m.getCoordonnee(), couleur);
		if (etat == GAGNE)	
			JOptionPane.showMessageDialog(message, "Tu as perdu !", " D�faite", JOptionPane.WARNING_MESSAGE);
	}
	
	
	/**
	 * Affichage d'un JOptionPane lorsque mon tir a touché ou coulé un navire adverse, ou lorsque la partie
	   est gagnée.
	 */
	
	protected void retourAttaque(Marqueur m, int etat) {
		
		Color couleur = m.isCroix() ? Color.BLUE : Color.RED;
		grilleJeu.colorie(m.getCoordonnee(), couleur);
			if (etat == GAGNE)			
				JOptionPane.showMessageDialog(grilleJeu, "Tu as gagn� ! ");	
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
												//Test de Main\\
		GrilleDeJeu HomeSweetHome = new GrilleDeJeu(10); // La oÃ¹ sont placÃ©s les bÃ¢teaux
		

		String nom = "PartieDeLaMort";
		String nom1 = "LaPremiereFois";

		FenetreJoueur hublot = new FenetreJoueur(nom, 10);
		hublot.setVisible(true);	
		
	

	}


}
