package gomoku;

import java.util.Scanner;

public class JoueurTexte extends JoueurAvecGrille{
	
	private Scanner sc;

	public JoueurTexte(Grille g, String nom, boolean isCroix) {
		super(g, nom, isCroix);
	}
	
	public JoueurTexte(Grille g, boolean isCroix) {
		super(g, isCroix);
	}
	
	protected void retourAttaque(Marqueur m, int etat) {
		if (etat == super.GAGNE)
			System.out.println(this.getNom()+ " a gagné!");
		else 
			System.out.println( this.getNom() + " joue en "+ m.getCoordonnee());
	}
			

	protected void retourDefense(Marqueur m, int etat) {
		if (etat == super.GAGNE)
			System.out.println(this.getNom()+" a perdu!");
		else 
			System.out.println( "a " +this.getNom() + " de jouer");
	}
	
	public Marqueur choisirAttaque() {
		Scanner sc = new Scanner(System.in);
		String attaque;
		System.out.println(this.getNom() + " choisis ton attaque :");
		attaque = sc.nextLine();
		if (attaque.length()!=2 && attaque.length()!=3) {
			while (attaque.length()!=2 && attaque.length()!=3) {
				System.out.println("Veuillez entrez une case au format 'A4' svp");
			attaque = sc.nextLine();
		}}
		Coordonnee c = new Coordonnee(attaque);
		if (c.getColonne()>=this.getTailleGrille() || c.getLigne()>=this.getTailleGrille()) {
			System.out.println("veuillez entrez une case de la grille");
			this.choisirAttaque();}
		Marqueur m = this.isCroix() ? new Marqueur(c, true) : new Marqueur(c,false);		
		return m;
	}
	
	public static void main(String[] args) {

		Grille grille1 = new Grille(10);
		System.out.println(grille1);
		
		JoueurTexte cha = new JoueurTexte(grille1, "Cha", true);
		JoueurTexte vic = new JoueurTexte(grille1, "Vic", false);
		cha.jouerAvec(vic);
		

		
	}
	
}