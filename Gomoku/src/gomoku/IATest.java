package gomoku;

public class IATest {

	public IATest() {
		// TODO Auto-generated constructor stub
	}

	public static void runMultipleGames(JoueurAvecGrille j1, JoueurAvecGrille j2, int nbGames) {
		int nbJ1Win=0;
		int nbJ2Win=0;
		int nbTie=0;
		String j1Name=j1.getNom();
		String j2Name=j2.getNom();
		String res;
		long AvgDuration=0;
		
		for (int i=0; i<nbGames; i++) {
			long startTime = System.currentTimeMillis();
			if (i<Math.ceil(nbGames/2)) 
				res = j1.jouerAvec(j2);		
			else
				res = j2.jouerAvec(j1);
			Grille newGrille = new Grille(j1.getTailleGrille());
			j1.setGrille(newGrille);
			j2.setGrille(newGrille);
			long duration = (System.currentTimeMillis()-startTime)/1000;

			if (res.equals("tie"))
				nbTie++;
			else if (res.equals(j1Name))
				nbJ1Win++;
			else
				nbJ2Win++;
		}
		System.out.println("Victoires de " + j1Name + " = " + nbJ1Win);
		System.out.println("Victoires de " + j2Name + " = " + nbJ2Win);
		System.out.println("Egalites: " + nbTie);
	}
	
	
	public static void main(String[] args) {
		Grille laGrille = new Grille(8);
			
		JoueurMinimax1 M1Croix = new JoueurMinimax1(laGrille,"M1 croix", true);
		JoueurMinimax1 M1Ronds = new JoueurMinimax1(laGrille,"M1 ronds", false);
		
		JoueurMinimax1 MAF2Croix = new JoueurMinimax1AggroFacteur2(laGrille,"MAF2 croix", true);
		JoueurMinimax1 MAF2Ronds = new JoueurMinimax1AggroFacteur2(laGrille,"MAF2 ronds", false);
		
		JoueurMinimax1 MAF3Croix = new JoueurMinimax1AggroFacteur3(laGrille,"MAF3 croix", true);
		JoueurMinimax1 MAF3Ronds = new JoueurMinimax1AggroFacteur3(laGrille,"MAF3 ronds", false);
		
		JoueurMinimax1 MAF4Croix = new JoueurMinimax1AggroFacteur4(laGrille,"MAF4 croix", true);
		JoueurMinimax1 MAF4Ronds = new JoueurMinimax1AggroFacteur4(laGrille,"MAF4 ronds", false);
		
		JoueurMinimax1 ABM1Croix = new AlphaBetaMinimax1(laGrille,"ABM1 croix", true);
		JoueurMinimax1 ABM1Ronds = new AlphaBetaMinimax1(laGrille,"ABM1 ronds", false);
		
		JoueurMinimax1 ABMAF2Croix = new AlphaBetaMinimaxAggroF2(laGrille,"ABMAF2 croix", true);
		JoueurMinimax1 ABMAF2Ronds = new AlphaBetaMinimaxAggroF2(laGrille,"ABMAF2 ronds", false);
		
		// Not implemented yet

//		JoueurMinimax1 AlphaBetaMinimaAggroFact3Croix = new AlphaBetaMinimaxAggro3(laGrille,"AlphaBetaMinimaxAggro3 croix", true);
//		JoueurMinimax1 AlphaBetaMinimaAggroFact3Ronds = new AlphaBetaMinimaxAggro3(laGrille,"AlphaBetaMinimaxAggro3 ronds", false);


		runMultipleGames(MAF4Croix, M1Ronds, 5);
	}
}
