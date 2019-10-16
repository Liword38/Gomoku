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
	
	public JoueurGraphique(GrilleDeJeu grilleJeu, GrilleGraphique grilleGraph, String nom, boolean isCroix) {
		super(grilleJeu,nom,isCroix);
		this.grilleJeu=grilleGraph;
		
	}
	
	
	
	/**
	 * Permet d'obtenir un joueur graphique de nom nom qui  joue sur grilleJeu 
	 * @param grilleJeu : La grille de jeu
	 */
	
	public JoueurGraphique(Grille grilleJeu, boolean isCroix) {
		super(grilleJeu, isCroix);
	}
	
	
	
	/**
	 * Consiste a  récupérer la coordonnée choisie depuis grilleJeu.
	 */
	
	public Marqueur choisirAttaque() {
		Coordonnee c= grilleJeu.getCoordonneeSelectionnee();
		int ligne = c.getColonne();    //J'inverse volontairement les lignes et colonnes de m sinon c'est dans le mauvais sens
		int col = c.getLigne();
		c = new Coordonnee(ligne,col);
		Marqueur m = this.isCroix() ? new Marqueur(c,true) : new Marqueur(c,false);
		System.out.println(this.getNom()+ " joue en "+m.getCoordonnee());
		return m;
		
	}
	
	
	
	/**
	 * Affichage d'un JOptionPane lorsque le tir a touche ou coule un navire, ou lorsque la partie
	   est perdue.
	 */
	
	protected void retourDefense(Marqueur m,int etat) {
		JOptionPane message = new JOptionPane();
		if (etat == GAGNE)	
			JOptionPane.showMessageDialog(message, "Désolé " + this.getNom() +" tu as perdu contre "+this.getOpponentName() + " !", " Défaite", JOptionPane.WARNING_MESSAGE);
	}
	
	
	/**
	 * Affichage d'un JOptionPane lorsque mon tir a touchÃ© ou coulÃ© un navire adverse, ou lorsque la partie
	   est gagnÃ©e.
	 */
	
	protected void retourAttaque(Marqueur m, int etat) {
	
			if (etat == GAGNE)			
				JOptionPane.showMessageDialog(grilleJeu, "Bravo "+this.getNom() + " tu as gagné contre "+this.getOpponentName()+ " !");	
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
												//Test de Main\\
		GrilleDeJeu HomeSweetHome = new GrilleDeJeu(10); // La oÃƒÂ¹ sont placÃƒÂ©s les bÃƒÂ¢teaux
		

		String nom = "PartieDeLaMort";
		String nom1 = "LaPremiereFois";

		FenetreJoueur hublot = new FenetreJoueur(nom, 10);
		hublot.setVisible(true);	
		
	

	}


}
