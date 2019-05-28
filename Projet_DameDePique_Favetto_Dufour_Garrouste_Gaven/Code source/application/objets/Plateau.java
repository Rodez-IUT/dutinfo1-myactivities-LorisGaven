/* Plateau.java				12/05/2019
 * aucun droit sur ce programme
 */
package application.objets;

/** La classe Plateau est une classe
 *  représentant le tapis du jeu la 
 *  dame de pique. C'est dans ce plateau que nous 
 *  verrons toutes les informations nécessaires
 *  sur l'état de la partie
 *  @author Kevin, Loris, Ambre, Adrien
 *  @version 1.0
 */
public class Plateau {
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 				DECLARATION DES CONSTANTES ET VARIABLES
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */
	public int NB_JOUEUR_AUTORISE = 4;		// Le nombre de joueurs autorisés dans une partie
	public int NB_CARTE_EN_JEUX_MAX = 4;	// Le nombre de cartes en jeu au maximum
	
	private int numManche; 			// Le nombre de manches jouées depuis le début de la partie
	private int nbCarteEnJeux; 		// Le nombre de cartes en jeu lors du tour
	private Carte[] pile;           // Les cartes qui ont déjà été jouées
	
	/* 
	 * Contient les joeurs de 
	 * la partie avec leurs informations
	 */
	private Joueur[] adversaires; 
	
	/* 
	 * Contient les cartes que les 
	 * joueurs ont joué pendant la manche
	 */ 
	private Carte[] carteEnJeux;
	
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 			  METHODES DE LA CLASSE CARTE ET CONSTRUCTEURS
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */
	
	/** 
	 * Constructeur de Plateau.
	 * Cohérent avec le jeu Dame de Piques
	 */
	public Plateau() {
		this.numManche = 1;
		this.nbCarteEnJeux = 0;
		this.carteEnJeux = new Carte[4];
		this.pile = new Carte[52];
	}

	/** 
	 * @return le nombre de manches
	 */
	public int getNumManche() {
		return numManche;
	}
	
	/**
	 * Ajoute une carte dans le jeux
	 * @param aAjouter La carte à ajouter
	 */
	public void ajouterCarteEnJeu(Carte aAjouter) {
		/* Erreur si les cartes en jeu sont déjà de 4 */
		if (nbCarteEnJeux >= NB_CARTE_EN_JEUX_MAX) {
			System.out.println("Impossible d'ajouter une carte "
					+ "dans le jeu (4 cartes ont deja ete jouees");
		} else { 
			/* ne pas oublier que les tableaux commencent à index 0 */
			carteEnJeux[nbCarteEnJeux] = aAjouter;
			nbCarteEnJeux++;
		}
	}
	
	/** 
	 * Affiche les cartes en jeu lors du tour
	 */
	public void afficherCartesEnJeux() {
		System.out.println("Cartes en jeu : ");
		
		/* Montre les carte qui constitue carteEnJeux */
		for (int i = 0; i < carteEnJeux.length && carteEnJeux[i] != null; i++) {
			System.out.print("    " + carteEnJeux[i].toString());
		}
		System.out.println("\n");
	}
	
	/**
	 * Remet à 0 CartesEnJeux
	 */
	public void resetCarteEnJeux() {
		/* Erreur si le nombre de cartes en jeu est pas de 4 */
		if (nbCarteEnJeux < NB_CARTE_EN_JEUX_MAX) {
			System.out.println("Impossible de reset si le "
					+ "nombre de cartes en jeu est inferieur a 4 ");
		} else {
			/* Reset du tableau */
			for (int i = 0; i < carteEnJeux.length; i++) {
				carteEnJeux[i] = null;
			}
		}
		nbCarteEnJeux = 0;
	}
	
	/**
	 * Affiche le score des joueurs
	 * @param joueur Le joueur dont on souhaite afficher le score
	 */
	public void afficherScore(Joueur joueur) {
		System.out.println("Score : " + joueur.getScore());
	}

	/**
	 * Calcule le score et l'ajoute au joueur qui a perdu
	 * @param leJoueur qui a ramasse les points
	 */
	public void calculerScore(Joueur leJoueur) {
		int aAjouter;
		aAjouter = 0;
		for (int i = 0; i < carteEnJeux.length; i++) {
			aAjouter += carteEnJeux[i].getValeur();
		}
		leJoueur.ajouterScore(aAjouter);
	}

	@Override
	public String toString() {
		String retour;
		retour = "\n\n\t\t\t\tJoueur3\n\n\n\n\tJoueur2\t\t\t\t\t\tJoueur4\n\n\n\n\t\t\t\tJoueur1\n\n";
		return retour;
	}

	/**
	 * @return Les cartes qui ont été jouées durant le tour
	 */
	public Carte[] getCartesEnJeux() {
		return carteEnJeux;
	}

	/**
	 * @return La pile
	 */
	public Carte[] getPile() {
		return pile;
	}

	/**
	 * Met à jour la pile
	 */
	public void actualiserPile() {
		/* indice de parcour du tableau */
		int i=0;
		for (; i < pile.length && pile[i] != null; i++);

		for (int j = 0; j < carteEnJeux.length; i++, j++) {
			pile[i] = carteEnJeux[j];
		}
	}

	/**
	 * Remet la pile à 0
	 */
	public void resetPile() {
		for (int i=0; i < pile.length; i++) {
			pile[i] = null;
		}
	}

	/**
	 * Détermine à quel tour de la manche en ai la pertie
	 * @return le numéro du tour
	 */
	public int calculerTour() {
		int nbPile,
			noTour;
		/* On compte combien il y a de carte dans la pile */
		for (nbPile=0; nbPile < pile.length && pile[nbPile] != null; nbPile++);
		/* On détermine le tour */
		noTour = nbPile/4+1;
		return noTour;
	}

	/**
	 * @return true si une carte de type coeur est présente dans la pile
	 */
	public boolean coeurDansPile() {
		boolean coeurPresent=false;
		for (int i=0; i < pile.length && pile[i] != null && !coeurPresent; i++) {
			if (pile[i].getType().equals("Coeur"))
				coeurPresent=true;
		}
		return coeurPresent;
	}
}

