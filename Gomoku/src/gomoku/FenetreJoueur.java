package gomoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gomoku.GrilleDeJeu;
import gomoku.GrilleGraphique;

import java.awt.GridLayout;

public class FenetreJoueur extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private GrilleDeJeu grilleJeu;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreJoueur frame = new FenetreJoueur();
					frame.setBounds(100, 100, 335, 360);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public FenetreJoueur() {
		this("Nom du joueur", 10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel JPanleGlobal = new JPanel();
		contentPane.add(JPanleGlobal, BorderLayout.CENTER);
		JPanleGlobal.setLayout(new BorderLayout(0, 0));
		
		GrilleGraphique grilleJeu = new GrilleGraphique(0);
		JPanleGlobal.add(grilleJeu);
		grilleJeu.setLayout(new GridLayout(1, 0, 0, 0));
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		grilleJeu.add(table);
		

	}


	/**
	 * permet d'obtenir une fenêtre pour un joueur de nom nom avec des grilles de taille taille.
	 * @param nom : nom de la fenêtre
	 * @param taille : taille des grilles
	 */
	
	public FenetreJoueur(String nom, int taille) {
		this.setTitle(nom);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));		
		grilleJeu = new GrilleDeJeu(taille);
		getContentPane().add(grilleJeu.getGrilleGraphique());

	}
	
	public GrilleDeJeu getGrilleJeu() {
		return grilleJeu;
	}
	
	
}
