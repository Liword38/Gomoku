package gomoku;

public abstract class Joueur {
	public final static int GAGNE = 1;
	public final static int PASGAGNE = 2;
	public final static int REJOUE = 3;
	public final static int EGALITE = 4;
	private Joueur adversaire;
	private int tailleGrille;
	private String nom;
	private boolean isCroix;
	
	//Constructeurs
	public Joueur(int tailleGrille, String nom, boolean isCroix) {
		this(tailleGrille, isCroix);
		this.nom=nom;
	}
	
	public Joueur(int tailleGrille, boolean isCroix) {
		this.tailleGrille=tailleGrille;
		this.nom= "Default name";
		this.isCroix=isCroix;

	}
	
	public void setIsCroix(boolean isCroix) {
		this.isCroix = isCroix;
	}
	
	public String getOpponentName() {
		if (this.adversaire.getNom() != null)
			return this.adversaire.getNom();
		return ("Adversaire inconnu");
	}
	
	//Getters
	public int getTailleGrille() {
		return tailleGrille;
	}
	
	public String getNom() {
		return nom;
	}
	
	//Renvoie vrai si le joueur joue les Croix, faux s'il joue les Ronds
	public boolean isCroix() {
		return isCroix;
	}
	
	/**
	 * Demarre une partie contre j . Avant de lancer le deroulement du jeu, il faut
	 * veiller a etablir le lien entre les 2 joueurs et bien entendu verifier qu'il
	 * puisse etre etabli
	 */

	public String jouerAvec(Joueur j) {
		if (this.adversaire != null || j.adversaire != null) { // verification que les 2 joueurs ne sont pas utilises
			System.out.println("Un des joueurs joue deja avec quelqu'un d'autre !");
			return "Un joueur est occup�";
		} else {
			System.out.println("Debut de la partie, que le meilleur gagne !");
		}
		this.adversaire = j;
		j.adversaire = this;
		String result = deroulementJeu(this, j);
		this.adversaire = null; // si la partie est finie on reinitialise les joueurs a null
		j.adversaire = null;
		return result;
	}
	
	private static String deroulementJeu(Joueur attaquant, Joueur defenseur) {
		int res = 0;
		while (res != GAGNE && res != EGALITE) {
			do {
			Marqueur m = attaquant.choisirAttaque();
			res = defenseur.defendre(m);
			attaquant.retourAttaque(m, res);
			defenseur.retourDefense(m, res);
			}
			while (res == REJOUE);
			Joueur x = attaquant;
			attaquant = defenseur;
			defenseur = x;
			try {
			    Thread.sleep(0);                 //1000 milliseconds is one second.  Permet d'observer le deroulement de la partie. A mettre sur 0 pour des parties instantanees.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		if (res == GAGNE)		//On a inverse defenseur et attaquant apres la victoire, c'est donc defenseur qui a gagne
			return defenseur.nom;
		return "tie";
	}
	

	
	
	protected abstract void retourAttaque(Marqueur m, int etat);

	/**
	 * Cette méthode est invoquée sur le joueur défenseur à la fin d’un tour de jeu
	 * . c est la coordonnée à laquelle le tir a eu lieu et etat le résultat de ce
	 * tir. etat ne peut être que TOUCHE , COULE , A_L_EAU , ou GAMEOVER .
	 * 
	 * @param c
	 * @param etat
	 */

	protected abstract void retourDefense(Marqueur m,int etat);

	/**
	 * Cette méthode est invoquée sur le joueur attaquant au début d’un tour de jeu.
	 * Elle retourne la coordonnée cible du tir effectué par l’attaquant .
	 * 
	 */

	public abstract Marqueur choisirAttaque();

	/**
	 * Cette méthode est invoquée sur le joueur défenseu r après le choix de
	 * l’attaquant, c est la coordonnée à laquelle l’attaquant a choisi d’effectuer
	 * un tir . Elle retourne le résultat du tir qui ne peut être que TOUCHE , COULE
	 * , A_L_EAU , ou GAMEOVER .
	 * 
	 * @param c
	 * @return
	 */

	public abstract int defendre(Marqueur m);
	


}
