package gomoku;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

class JButtonCoordonnee extends JButton {

	private static final long serialVersionUID = 1L;
	private Coordonnee c;

	public JButtonCoordonnee(Coordonnee c) {

		this.c = c;
	}

	public Coordonnee getCoordonnee() {
		return c;
	}
}

/**
 * Classe representant un composant graphique "Grille". Une grille est composee
 * de JButton
 * 
 * @author jerome.david@univ-grenoble-alpes.fr
 * 
 */
public class GrilleGraphique extends JPanel implements ActionListener {

	private static final long serialVersionUID = 8857166149660579225L;

	/**
	 * La matrice des boutons (cases de la grille)
	 */
	private JButton[][] cases;

	/**
	 * La coordonnee actuellement selectionnÃ©e. Null si aucune selection en cours
	 */
	private Coordonnee coordonneeSelectionnee;

	/**
	 * Initialise une grille carree de taille donnee
	 * 
	 * @param taille la taille de la grille
	 */
	public GrilleGraphique(int taille) {
		try {
			// Certains LookAndFeels ne colorient pas les boutons.
			// on choisi celui le plus simple (et le moins joli)
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		this.setLayout(new GridLayout(taille + 1, taille + 1));

		this.add(new JLabel());
		for (int i = 0; i < taille; i++) {
			JLabel lbl = new JLabel(String.valueOf((char) ('A' + i)));
			lbl.setHorizontalAlignment(JLabel.CENTER);
			this.add(lbl);
		}
		cases = new JButton[taille][taille];
		for (int i = 0; i < taille; i++) {
			JLabel lbl = new JLabel(String.valueOf(i + 1));
			lbl.setHorizontalAlignment(JLabel.CENTER);
			this.add(lbl);
			for (int j = 0; j < taille; j++) {
				cases[i][j] = new JButtonCoordonnee(new Coordonnee(j, i));
				this.add(cases[i][j]);
				cases[i][j].addActionListener(this);
			}
		}
		coordonneeSelectionnee = null;
	}

	/**
	 * Colorie la case indiquee par la coordonnee
	 * 
	 * @param coord la coordonnee de la case a colorier
	 * @param color la couleur de la case
	 */
	public void colorie(Coordonnee cord, Color color) {
		// cases[cord.getLigne()][cord.getColonne()].setBackground(color);

		if (color.equals(Color.GREEN)) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("CrossIcon.png"));	
				//cases[cord.getLigne()][cord.getColonne()].setIcon(new ImageIcon("CrossIcon.png"));
			} catch (Exception ex) {
				System.out.println(ex);
			}
			Image dimg = img.getScaledInstance(cases[cord.getLigne()][cord.getColonne()].getWidth(),cases[cord.getLigne()][cord.getColonne()].getHeight() ,Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			cases[cord.getLigne()][cord.getColonne()].setIcon(imageIcon);
		}
		else{
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("CircleIcon.png"));	
				//cases[cord.getLigne()][cord.getColonne()].setIcon(new ImageIcon("CrossIcon.png"));
			} catch (Exception ex) {
				System.out.println(ex);
			}
			Image dimg = img.getScaledInstance(cases[cord.getLigne()][cord.getColonne()].getWidth(),cases[cord.getLigne()][cord.getColonne()].getHeight() ,Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			cases[cord.getLigne()][cord.getColonne()].setIcon(imageIcon);
		}
	}

	/**
	 * Colorie le rectangle compris entre les deux coordonnees
	 * 
	 * @param debut Coordonnee du debut de la zone a colorier (haut gauche)
	 * @param fin   Coordonnee de la fin de la zone a colorier (bas droit)
	 * @param color la couleur de la case
	 */
//	public void colorie(Coordonnee debut, Coordonnee fin, Color color) {
//		for (int i = debut.getLigne(); i <= fin.getLigne(); i++) {
//			for (int j = debut.getColonne(); j <= fin.getColonne(); j++) {
//				cases[i][j].setBackground(color);
//			}
//		}
//
//	}

	@Override
	public Dimension getPreferredSize() {
		Dimension d = super.getPreferredSize();
		d.setSize(d.width, d.width);
		return d;
	}

	// ??????????????????
//	public void setClicActive(boolean active) {
//		SwingUtilities.invokeLater(() -> {
//			this.setEnabled(false);
//			for (JButton[] ligne : cases) {
//				for (JButton bt : ligne) {
//					bt.setEnabled(active);
//				}
//			}
//			this.setEnabled(true);
//		});
//	}

	/**
	 * Methode appelee lorsque l'on clique sur une case de la grille. Elle
	 * "reveille" la methode getCoordonneeSelectionnee
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//this.setClicActive(false);
		coordonneeSelectionnee = ((JButtonCoordonnee) e.getSource()).getCoordonnee();
		synchronized (this) {
			this.notifyAll();
		}
	}

	/**
	 * Attend que l'utilisateur selectionne (clic) sur une case de la grille et
	 * retourne la coordonnee qui a ete selectionnee
	 * 
	 * @return la coordonnee selectionnee
	 */
	public synchronized Coordonnee getCoordonneeSelectionnee() {
	//	this.setClicActive(true);
		try {
			this.wait();
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
		return coordonneeSelectionnee;
	}

}
