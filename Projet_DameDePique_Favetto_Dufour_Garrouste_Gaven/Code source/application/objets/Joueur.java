/* 
 * Joueur.java			04/05/2019
 * Aucun droits sur ce programme
 */

package application.objets;

import application.objets.Carte.*;
import java.util.Scanner;
import application.objets.PaquetDeCarte;
import application.objets.Plateau;

/**
 * La classe Joueur représente la structure 
 * de l'objet Joueur. Elle est alors utilisée 
 * pour représenter un Joueur dans l'application
 * qu'il soit un humain ou une machine
 * @author Kevin, Loris, Ambre, Adrien
 * @version 1.0
 */
public class Joueur {

	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 				DECLARATION DES CONSTANTES ET VARIABLES
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */

	/** Analyseur de l'entree texte standard */
	public static Scanner entree = new Scanner(System.in);
	
	/** Nombre de cartes que possèdent les joueurs au début de la partie */
	public static final int MAX_CARTES_MAIN = 13;

	/** Ordre de passage du premier joueur */
	public static final int ORDRE_PASSAGE_MIN = 1;

	/** Ordre de passage du dernier joueur */
	public static final int ORDRE_PASSAGE_MAX = 4;

	/** 
	 * Varibale permettant d'initialiser numeroJoueur
	 * Elle est incrémentée à chaque fois qu'un joueur est créé
	 */
	public static int numero = 1;

	/** Cartes que possède le joueur */
	protected Carte[] cartesEnMain;

	/** Permet de placer les joueurs */
	private int numeroJoueur;

	/** Permet de déterminer quel joueur va commencer à jouer */
	private boolean commence;

	/** Nombre de points encaissés par le joueur */
	private int score;

	/** Les cartes que le joueur à ramassé en perdant un tour */
	private Carte[] cartesRamassees;

	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 			  METHODES DE LA CLASSE Carte ET CONSTRUCTEURS
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */

	/**
	 * Constructeur des objets joueurs
	 * Aucun parametres utilisés car tous les joueurs ont le même état initial
	 * mis à part le numéro qui est calculé automatiquement
	 * @throws IllegalStateException si le nombre de joueurs est dépassé
	 */
	public Joueur() {
		/* 
		 * Les joueurs sont placés autour de la table dans l'ordre où ils
		 * sont créés (il ne peut pas y avoir plus de 4 joueurs)
		 */
		if (numero <= ORDRE_PASSAGE_MAX) {
			this.numeroJoueur = numero;
			numero++;
			if (numero > 4) {
				numero = 1;
			}
		}

		/*
		 * On initialise la taille mais le joueur n'a pas de cartes tant
		 * qu'elles n'ont pas été distribuées
		 */
		this.cartesEnMain = new Carte[MAX_CARTES_MAIN];

		/*
		 * On initialise à false car la personne qui commence
		 * n'a pas encore été déterminée
		 */
		this.commence = false;

		/* Au début de la partie le score de chaque joueur est de 0 */
		this.score = 0;

		/*
		 * On initialise la taille, le joueur peut au maximum ramasser
		 * 52 cartes si il perd tous les tours
		 */
		this.cartesRamassees = new Carte[52];
	}

	/**
	 * Méthode statique qui permet de déterminer l'odre de passage des joueurs
	 * Le premier joueur à jouer est celui qui possède le 2 de trèfle
	 * @param joueur1
	 * @param joueur2
	 * @param joueur3
	 * @param joueur4
	 */
	public static int quiCommence(Joueur joueur1, Joueur joueur2,
					  Joueur joueur3, Joueur joueur4) {
		/* On stocke dans un tableau les cartes de chaque joueur */
		Carte[] cartesJoueur1 = joueur1.getCartesEnMain(), 
		        cartesJoueur2 = joueur2.getCartesEnMain(),
		        cartesJoueur3 = joueur3.getCartesEnMain(), 
		        cartesJoueur4 = joueur4.getCartesEnMain();

		int numJoueurCommence = 0; // Le numéro du joueur qui va commencer à jouer

		/* On parcours les cartes de chaque joueur pour voir qui a le 2 de trefle */
		boolean stop = false; // On s'arrête dès que le trefle est trouvé
		for (int i = 0; i < MAX_CARTES_MAIN && !stop; i++) {
			joueur1.setCommence(cartesJoueur1[i].getType().equals("Trefle")
				                && cartesJoueur1[i].getPuissance() == 2);

			joueur2.setCommence(cartesJoueur2[i].getType().equals("Trefle")
				                && cartesJoueur2[i].getPuissance() == 2);

			joueur3.setCommence(cartesJoueur3[i].getType().equals("Trefle")
				                && cartesJoueur3[i].getPuissance() == 2);

			joueur4.setCommence(cartesJoueur4[i].getType().equals("Trefle")
				                && cartesJoueur4[i].getPuissance() == 2);

			stop =   joueur1.getCommence() || joueur2.getCommence()
			      || joueur3.getCommence() || joueur4.getCommence();
		}

		if (joueur1.getCommence()) numJoueurCommence = 1;
		else if (joueur2.getCommence()) numJoueurCommence = 2;
		else if (joueur3.getCommence()) numJoueurCommence = 3;
		else if (joueur4.getCommence()) numJoueurCommence = 4;
		return numJoueurCommence;
	}

	/**
	 * Méthode statique permettant de distribuer les cartes à tous les joueurs
	 * @param aDistribuer le paquet de cartes à distribuer
	 * @param joueur1
	 * @param joueur2
	 * @param joueur3
	 * @param joueur4
	 */
	public static void distribuerCartes(Joueur joueur1, Joueur joueur2,
					                    Joueur joueur3, Joueur joueur4,
					                    PaquetDeCarte aDistribuer) {
		/* On stocke dans un tableau les cartes de chaque joueur */
		Carte[] cartesJoueur1 = joueur1.getCartesEnMain(), 
		        cartesJoueur2 = joueur2.getCartesEnMain(),
		        cartesJoueur3 = joueur3.getCartesEnMain(), 
		        cartesJoueur4 = joueur4.getCartesEnMain();
		for (int i = 0, j = 0; i < MAX_CARTES_MAIN; i++) {
			cartesJoueur1[i] = aDistribuer.getPaquet()[j];
			j++;
			cartesJoueur2[i] = aDistribuer.getPaquet()[j];
			j++;
			cartesJoueur3[i] = aDistribuer.getPaquet()[j];
			j++;
			cartesJoueur4[i] = aDistribuer.getPaquet()[j];
			j++;
		}
	}

	/**
	 * @return Les cartes dans la main du joueur
	 */
	public Carte[] getCartesEnMain() {
		return cartesEnMain;
	}

	/**
	 * @param nouvellesCartes la nouvelle main du joueur
	 */
	public void setCartesEnMain(Carte[] nouvellesCartes) {
		this.cartesEnMain = nouvellesCartes;
	}

	/**
	 * @return true si le joueur commence sinon false
	 */
	public boolean getCommence() {
		return commence;
	}

	/**
	 * @param aTrefle
	 */
	public void setCommence(boolean aTrefle) {
		this.commence = aTrefle;
	}

	@Override
	public String toString() {
		String retour = "Joueur" + numeroJoueur + "\t-> Score = " + score + "\n";
		for(int i=0; i < MAX_CARTES_MAIN; i++) {
			if (cartesEnMain[i] != null)
				retour += "" + cartesEnMain[i].toString() + " (" + (i+1) + ")\n";
		}

		return retour;
	}

	/**
	 * Cette méthode est appelée dans Humain et dans IA
	 */
	public Carte[] choixCartesADonner() {
		return null;
    }


	/**
	 * Ajoute les cartes qu'un autre joueur à choisi de donner
	 * @param aRecevoir les 3 cartes reçues
	 */
	public void recevoirCartes(Carte[] aRecevoir) {
		trierCartes();
		int indiceMax = cartesEnMain.length-1;
		for (int i = 0; i < aRecevoir.length; i++) {
			cartesEnMain[indiceMax-i] = aRecevoir[i];
		} 
		trierCartes();
	}

	/**
	 * Cette méthodes est appelée dans Humain et dans IA
	 * @param plateauDeJeu le plateau sur lequel on va jouer la carte
	 */
	public Carte choixCarteAJouer(Plateau plateauDeJeu) {
		return null;
	}

	/**
	 * @return Le score du joueur
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Remet à 0 le tableau des cartes ramassées
	 */
	public void resetCartesRamassees() {
		for (int i = 0; i < cartesRamassees.length; i++) {
			cartesRamassees[i] = null;
		}
	}

	/**
	 * @return les cartes qu'a encaissé le joueur
	 */
	public Carte[] getCartesRamassees() {
		return cartesRamassees;
	}

	/**
	 * Actualise le score du joueur
	 * @param aAjouter nombre de points à ajouter
	 */
	public void ajouterScore(int aAjouter) {
		score += aAjouter;
	}

	/**
	 * Actualise les cartes encaissées par le joueur
	 * @param aAjouter les cartes ramassées par le joueur
	 */
	public void aJouterCartesRamassees(Carte[] aAjouter) {
		int i;
		/* On se place à l'endroit ou il n'y a pas déjà une carte */
		for (i = 0; i < cartesRamassees.length && cartesRamassees[i] != null; i++);

		/* Puis on rempli avec les cartes reçues */
		for (int j = 0; j < aAjouter.length; j++, i++) {
			cartesRamassees[i] = aAjouter[j];
		}
	}

	/**
	 * Calcule le score des joueurs à la fin de la manche
	 * et l'ajoute au score qu'ils avaient déjà
	 * @param adversaires Les joueurs qui s'affrontent
	 */
	public static void calculerScore(Joueur[] adversaires) {
		int scoreAjouter;
		for (int i=0; i < adversaires.length; i++) {
			scoreAjouter = 0;
			for (int j=0; j < adversaires[i].getCartesRamassees().length
				          && adversaires[i].getCartesRamassees()[j] != null; j++) {

				scoreAjouter += adversaires[i].getCartesRamassees()[j].getValeur();
			}
			/* Si le joueur a un score de 26 c'est qu'il a fait un grand Chelem */
			if (scoreAjouter == 26) {
				for (int k=0; k < adversaires.length; k++) {
					if (k != i) adversaires[k].ajouterScore(26);
				}
			} else {
				adversaires[i].ajouterScore(scoreAjouter);
			}
		}
	}

	/**
	 * Trie les cartes du joueur
	 */
	public void trierCartes() {
		int i,
		    j;

		Carte aInserer;

		for (i = 1; i < cartesEnMain.length; ++i) {
			if (cartesEnMain[i] != null) {
            	aInserer = cartesEnMain[i];
            	for (j = i; j > 0 && (cartesEnMain[j-1] == null
            	 	|| cartesEnMain[j-1].calculPoids() > aInserer.calculPoids()); j--)
                	cartesEnMain[j] = cartesEnMain[j-1];
         		cartesEnMain[j] = aInserer;
            }
        }
    }

    /**
     * Méthode utilisée dans la classe IA
     */
	public void actualiserMemoire(Plateau plateauDeJeu) {}

	/**
     * Méthode utilisée dans la classe IA
     */
	public void resetMemoire() {}


}

