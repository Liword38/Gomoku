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
		frmGomoku.setBounds(100, 100, 560, 400);
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
		
		JRadioButton rdbtnJoueurGraphique1 = new JRadioButton("Joueur Humain");
		buttonGroup1.add(rdbtnJoueurGraphique1);
		rdbtnJoueurGraphique1.setVerticalAlignment(SwingConstants.BOTTOM);
		choix1.add(rdbtnJoueurGraphique1);
		
		JRadioButton rdbtnJoueurMinimax1 = new JRadioButton("Joueur Minimax1");
		buttonGroup1.add(rdbtnJoueurMinimax1);
		choix1.add(rdbtnJoueurMinimax1);
		
		JRadioButton rdbtnJoueurMinimaxAggro2_1 = new JRadioButton("Joueur MinimaxAggro2");
		buttonGroup1.add(rdbtnJoueurMinimaxAggro2_1);
		choix1.add(rdbtnJoueurMinimaxAggro2_1);
		
		JRadioButton rdbtnJoueurMinimaxAggro3_1 = new JRadioButton("Joueur MinimaxAggro3");
		buttonGroup1.add(rdbtnJoueurMinimaxAggro3_1);
		choix1.add(rdbtnJoueurMinimaxAggro3_1);
		
		JRadioButton rdbtnJoueurAlphaBeta_1 = new JRadioButton("Joueur AlphaBeta1");
		buttonGroup1.add(rdbtnJoueurAlphaBeta_1);
		choix1.add(rdbtnJoueurAlphaBeta_1);
		
		JRadioButton rdbtnJoueurAlphaBetaAggro2_1 = new JRadioButton("Joueur AlphaBetaAggro2");
		buttonGroup1.add(rdbtnJoueurAlphaBetaAggro2_1);
		choix1.add(rdbtnJoueurAlphaBetaAggro2_1);
		
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
		
		JRadioButton rdbtnJoueurGraphique2 = new JRadioButton("Joueur Humain");
		buttonGroup2.add(rdbtnJoueurGraphique2);
		choix2.add(rdbtnJoueurGraphique2); 

		JRadioButton rdbtnJoueurMinimax2 = new JRadioButton("Joueur Minimax1");
		buttonGroup2.add(rdbtnJoueurMinimax2);
		choix2.add(rdbtnJoueurMinimax2);
		
		JRadioButton rdbtnJoueurMinimaxAggro2_2 = new JRadioButton("Joueur MinimaxAggro2");
		buttonGroup2.add(rdbtnJoueurMinimaxAggro2_2);
		choix2.add(rdbtnJoueurMinimaxAggro2_2);
		
		JRadioButton rdbtnJoueurMinimaxAggro3_2 = new JRadioButton("Joueur MinimaxAggro3");
		buttonGroup2.add(rdbtnJoueurMinimaxAggro3_2);
		choix2.add(rdbtnJoueurMinimaxAggro3_2);
		
		JRadioButton rdbtnJoueurAlphaBeta_2 = new JRadioButton("Joueur AlphaBeta1");
		buttonGroup2.add(rdbtnJoueurAlphaBeta_2);
		choix2.add(rdbtnJoueurAlphaBeta_2);
		
		JRadioButton rdbtnJoueurAlphaBetaAggro2_2 = new JRadioButton("Joueur AlphaBetaAggro2");
		buttonGroup2.add(rdbtnJoueurAlphaBetaAggro2_2);
		choix2.add(rdbtnJoueurAlphaBetaAggro2_2);

		
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
				}else if(rdbtnJoueurMinimax1.isSelected()){
					J1 = new JoueurMinimax1(fenetre1.getGrilleJeu(),nomJ1,true);
				}else if(rdbtnJoueurMinimaxAggro2_1.isSelected()){
					J1 = new JoueurMinimax1AggroFacteur2(fenetre1.getGrilleJeu(),nomJ1,true);
				}else if(rdbtnJoueurMinimaxAggro3_1.isSelected()){
					J1 = new JoueurMinimax1AggroFacteur3(fenetre1.getGrilleJeu(),nomJ1,true);
				}else if(rdbtnJoueurAlphaBeta_1.isSelected()){
					J1 = new AlphaBetaMinimax1(fenetre1.getGrilleJeu(),nomJ1,true);
				}else if(rdbtnJoueurAlphaBetaAggro2_1.isSelected()){
					J1 = new AlphaBetaMinimaxAggroF2(fenetre1.getGrilleJeu(),nomJ1,true);
				}
				
				if(rdbtnJoueurGraphique2.isSelected()){
					J2 = new JoueurGraphique(fenetre1.getGrilleJeu(),fenetre1.getGrilleJeu().getGrilleGraphique(), nomJ2, false);
				}else if(rdbtnJoueurMinimax2.isSelected()){
					J2 = new JoueurMinimax1(fenetre1.getGrilleJeu(),nomJ2,false);
				}else if(rdbtnJoueurMinimaxAggro2_2.isSelected()){
					J2 = new JoueurMinimax1AggroFacteur2(fenetre1.getGrilleJeu(),nomJ2,false);
				}else if(rdbtnJoueurMinimaxAggro3_2.isSelected()){
					J2 = new JoueurMinimax1AggroFacteur3(fenetre1.getGrilleJeu(),nomJ2,false);
				}else if(rdbtnJoueurAlphaBeta_2.isSelected()){
					J2 = new AlphaBetaMinimax1(fenetre1.getGrilleJeu(),nomJ2,false);
				}else if(rdbtnJoueurAlphaBetaAggro2_2.isSelected()){
					J2 = new AlphaBetaMinimaxAggroF2(fenetre1.getGrilleJeu(),nomJ2,false);
				}
				demarrerPartie();
			}
			
		});
		bas.add(btnCommencerLaPartie);
	}

}
