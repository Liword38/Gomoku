package gomoku;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Gomoku {

	private JFrame frmGomoku;
	private JTextField tailleGrille;
	private JTextField nomJoueur1;
	private JTextField nomJoueur2;
	private final ButtonGroup buttonGroup1 = new ButtonGroup();
	private final ButtonGroup buttonGroup2 = new ButtonGroup();
	Joueur J1;
	Joueur J2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gomoku window = new Gomoku();
					window.frmGomoku.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void demarrerPartie() {
		new Thread() {
			public void run() {
				J1.jouerAvec(J2);
			}
		}.start();
	}

	
	/**
	 * Create the application
	 */
	public Gomoku() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGomoku = new JFrame();
		frmGomoku.setTitle("Gomoku");
		frmGomoku.setBounds(100, 100, 425, 450);
		frmGomoku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmGomoku.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel haut = new JPanel();
		panel.add(haut, BorderLayout.NORTH);
		
		JLabel lblTailleGrille = new JLabel("Taille de grille");
		haut.add(lblTailleGrille);
		
		tailleGrille = new JTextField();
		haut.add(tailleGrille);
		tailleGrille.setColumns(10);
		
		JPanel milieu = new JPanel();
		panel.add(milieu, BorderLayout.CENTER);
		milieu.setLayout(new BorderLayout(0, 0));
		
		JPanel joueur_1 = new JPanel();
		joueur_1.setBorder(new TitledBorder(null, "Joueur 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		milieu.add(joueur_1, BorderLayout.NORTH);
		joueur_1.setLayout(new BorderLayout(0, 0));
		
		JPanel nom1 = new JPanel();
		joueur_1.add(nom1, BorderLayout.NORTH);
		
		JLabel lblNom = new JLabel("Nom : ");
		nom1.add(lblNom);
		
		nomJoueur1 = new JTextField();
		nom1.add(nomJoueur1);
		nomJoueur1.setColumns(10);
		
		JPanel choix1 = new JPanel();
		joueur_1.add(choix1, BorderLayout.SOUTH);
		choix1.setLayout(new GridLayout(3, 2));
		
		JRadioButton rdbtnJoueurGraphique1 = new JRadioButton("Joueur Graphique");
		buttonGroup1.add(rdbtnJoueurGraphique1);
		rdbtnJoueurGraphique1.setVerticalAlignment(SwingConstants.BOTTOM);
		choix1.add(rdbtnJoueurGraphique1);
		
		JRadioButton rdbtnJoueurTexte1 = new JRadioButton("Joueur Texte");
		buttonGroup1.add(rdbtnJoueurTexte1);
		choix1.add(rdbtnJoueurTexte1); 
		
		JRadioButton rdbtnJoueurAuto1 = new JRadioButton("Joueur Idiot");
		buttonGroup1.add(rdbtnJoueurAuto1);
		choix1.add(rdbtnJoueurAuto1); 
		
		JRadioButton rdbtnJoueurMinimax1 = new JRadioButton("Joueur Minimax ");
		buttonGroup1.add(rdbtnJoueurMinimax1);
		choix1.add(rdbtnJoueurMinimax1);
		
		JRadioButton rdbtnJoueurAutoTrois1 = new JRadioButton("Joueur Auto 2");
		buttonGroup1.add(rdbtnJoueurAutoTrois1);
		choix1.add(rdbtnJoueurAutoTrois1);
		
		JRadioButton rdbtnJoueurAutoQuatre1 = new JRadioButton("Joueur Auto 3");
		buttonGroup1.add(rdbtnJoueurAutoQuatre1);
		choix1.add(rdbtnJoueurAutoQuatre1);
		
		JRadioButton rdbtnJoueurAutoCinq1 = new JRadioButton("Joueur Auto 4");
		buttonGroup1.add(rdbtnJoueurAutoCinq1);
		choix1.add(rdbtnJoueurAutoCinq1);
		
		JRadioButton rdbtnJoueurAutoSix1 = new JRadioButton("Joueur Auto 5");
		buttonGroup1.add(rdbtnJoueurAutoSix1);
		choix1.add(rdbtnJoueurAutoSix1);
		
		
		
		
		JPanel joueur_2 = new JPanel();
		joueur_2.setBorder(new TitledBorder(null, "Joueur 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		milieu.add(joueur_2, BorderLayout.SOUTH);
		joueur_2.setLayout(new BorderLayout(0, 0));
		
		JPanel nom2 = new JPanel();
		joueur_2.add(nom2, BorderLayout.NORTH);
		
		JLabel lblNom_1 = new JLabel("Nom : ");
		nom2.add(lblNom_1);
		
		nomJoueur2 = new JTextField();
		nom2.add(nomJoueur2);
		nomJoueur2.setColumns(10);
		
		JPanel choix2 = new JPanel();
		joueur_2.add(choix2, BorderLayout.SOUTH);
		choix2.setLayout(new GridLayout(3, 2));
		
		JRadioButton rdbtnJoueurGraphique2 = new JRadioButton("Joueur Graphique");
		buttonGroup2.add(rdbtnJoueurGraphique2);
		choix2.add(rdbtnJoueurGraphique2); 
		
		JRadioButton rdbtnJoueurTexte2 = new JRadioButton("Joueur Texte");
		buttonGroup2.add(rdbtnJoueurTexte2);
		choix2.add(rdbtnJoueurTexte2); 
		
		JRadioButton rdbtnJoueurAuto2 = new JRadioButton("Joueur Idiot");
		buttonGroup2.add(rdbtnJoueurAuto2);
		choix2.add(rdbtnJoueurAuto2); 
		
		JRadioButton rdbtnJoueurMinimax2 = new JRadioButton("Joueur Minimax");
		buttonGroup2.add(rdbtnJoueurMinimax2);
		choix2.add(rdbtnJoueurMinimax2);
		
		JRadioButton rdbtnJoueurAutoTrois2 = new JRadioButton("Joueur Auto 2");
		buttonGroup2.add(rdbtnJoueurAutoTrois2);
		choix2.add(rdbtnJoueurAutoTrois2);
		
		JRadioButton rdbtnJoueurAutoQuatre2 = new JRadioButton("Joueur Auto 3");
		buttonGroup2.add(rdbtnJoueurAutoQuatre2);
		choix2.add(rdbtnJoueurAutoQuatre2);
		
	
		
		JPanel bas = new JPanel();
		panel.add(bas, BorderLayout.SOUTH);
		
		JButton btnCommencerLaPartie = new JButton("Commencer la partie");
		btnCommencerLaPartie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int taille = Integer.parseInt(tailleGrille.getText());
				String nomJ1 = nomJoueur1.getText();
				String nomJ2 = nomJoueur2.getText();
				FenetreJoueur fenetre1 = new FenetreJoueur("Gomoku",taille);
				fenetre1.setVisible(true);
				fenetre1.setSize(new Dimension(800,800));  //Modifie la taille de la grille de jeu

				
				if(rdbtnJoueurGraphique1.isSelected()) {
					J1 = new JoueurGraphique(fenetre1.getGrilleJeu(),fenetre1.getGrilleJeu().getGrilleGraphique(), nomJ1, true);
				}else if(rdbtnJoueurTexte1.isSelected()){
					J1 = new JoueurTexte(fenetre1.getGrilleJeu(),nomJ1, true);
				}else if(rdbtnJoueurAuto1.isSelected()){
					J1 = new JoueurIdiot(fenetre1.getGrilleJeu(),nomJ1,true);
				}else if(rdbtnJoueurMinimax1.isSelected()){
					J1 = new JoueurMinimax1(fenetre1.getGrilleJeu(),nomJ1,true);
				}
				
				if(rdbtnJoueurGraphique2.isSelected()){
					J2 = new JoueurGraphique(fenetre1.getGrilleJeu(),fenetre1.getGrilleJeu().getGrilleGraphique(), nomJ2, false);
				}else if(rdbtnJoueurTexte2.isSelected()){
					J2 = new JoueurTexte(fenetre1.getGrilleJeu(),nomJ2, false);
				}else if(rdbtnJoueurAuto2.isSelected()){
					J2 = new JoueurIdiot(fenetre1.getGrilleJeu(),nomJ2,false);
				}else if(rdbtnJoueurMinimax2.isSelected()){
					J2 = new JoueurMinimax1(fenetre1.getGrilleJeu(),nomJ2,false);
				}
				demarrerPartie();
			}
			
		});
		bas.add(btnCommencerLaPartie);
	}

}
