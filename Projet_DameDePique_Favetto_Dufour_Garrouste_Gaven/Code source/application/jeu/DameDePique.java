/* DameDePique.java		14/05/2019
 * Aucun droits sur ce proramme
 */
package application.jeu;

import application.objets.Carte;
import application.objets.Joueur;
import application.objets.PaquetDeCarte;
import application.objets.Plateau;
import application.outils.OutilAffichage;
import java.util.Scanner;
import application.objets.Humain;
import application.objets.IA;

/**
 * Le jeu de la DameDePique 
 * suivant est un jeu de carte qui 
 * se joue a 4. Nous avons un joueur
 * humain confrontant 3 adversaires 
 * qui ont des ordinateurs dotés d'une
 * inteligence artificiel. L'objectif 
 * du jeu est d'avoir le moins de pénalitées
 * possible.
 * @author Kevin, Loris, Ambre, Adrien
 * @version 2.0
 */
public class DameDePique {
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 				DECLARATION DES CONSTANTES ET VARIABLES
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */

	public static Scanner entree = new Scanner(System.in);
	
	public static PaquetDeCarte jeuOriginal;	// Contient le jeu de carte a distribuer
	public static Plateau plateauDeJeu;			// Contient le plateau de jeu
	public static Joueur joueur1,
	                     joueur2,
	                     joueur3,
	                     joueur4;

	public static Joueur[] adversaires = {joueur1, joueur2, joueur3, joueur4};	// Contient les joueurs qui s'affronterons

	/** les cartes que les joueurs choisissent de donner */
	public static Carte[] aDonnerJoueur1 = new Carte[3],
						  aDonnerJoueur2 = new Carte[3],
						  aDonnerJoueur3 = new Carte[3],
						  aDonnerJoueur4 = new Carte[3];

	/* Les cartes jouées par les joueurs lors d'un tour */
	public static Carte[] aJouer = new Carte[4];
	
	
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 			METHODES DE LA CLASSE DAMEDEPIQUE
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */
	
	/**
	 * initalise l'état des variables nécéssaires aux jeux 
	 */
	public static void initialisation() {
		/* On crée les joueurs */
		adversaires[0] = new Humain();
		adversaires[1] = new IA();
		adversaires[2] = new IA();
		adversaires[3] = new IA();
		
		/* On crée le paquet de cartes original */
		jeuOriginal = new PaquetDeCarte();
		
		/* On crée le plateau de jeu */
		plateauDeJeu = new Plateau();
	}

	/**
	 * Les joueus choisissent les cartes a donner puis elles sont attribuées
	 * @param sens où donner les cartes
	 */
	public static void echanger(String sens) {

		/* Joueur1 */
		System.out.println("\n--- Le joueur1 choisi les cartes a donner " + sens + " ---\n");
		System.out.println(adversaires[0].toString() + "\n\n");
		aDonnerJoueur1 = adversaires[0].choixCartesADonner();
		System.out.println("\nLes cartes choisies sont : " + aDonnerJoueur1[0].toString() + "  "
			                                             + aDonnerJoueur1[1].toString() + "  "
			                                             + aDonnerJoueur1[2].toString() + "  \n");
		pause();
		/* Joueur2 */
		System.out.println("\n--- Le joueur2 choisi les cartes a donner " + sens + " ---\n");
		aDonnerJoueur2 = adversaires[1].choixCartesADonner();
		System.out.println("\nLes cartes choisies sont : " + aDonnerJoueur2[0].toString() + "  "
			                                             + aDonnerJoueur2[1].toString() + "  "
			                                             + aDonnerJoueur2[2].toString() + "  \n");
		pause();
		/* Joueur3 */
		System.out.println("\n--- Le joueur3 choisi les cartes a donner " + sens + " ---\n");
		aDonnerJoueur3 = adversaires[2].choixCartesADonner();
		System.out.println("\nLes cartes choisies sont : " + aDonnerJoueur3[0].toString() + "  "
			                                             + aDonnerJoueur3[1].toString() + "  "
			                                             + aDonnerJoueur3[2].toString() + "  \n");
		pause();
		/* Joueur4 */
		System.out.println("\n--- Le joueur4 choisi les cartes a donner " + sens +  " ---\n");
		aDonnerJoueur4 = adversaires[3].choixCartesADonner();
		System.out.println("Les cartes choisies sont : " + aDonnerJoueur4[0].toString() + "  "
			                                             + aDonnerJoueur4[1].toString() + "  "
			                                             + aDonnerJoueur4[2].toString() + "  \n");
		pause();
		/* On ajoute les cartes reçues */
		if (sens.equals("a gauche")) {
			adversaires[0].recevoirCartes(aDonnerJoueur4);
			adversaires[1].recevoirCartes(aDonnerJoueur1);
			adversaires[2].recevoirCartes(aDonnerJoueur2);
			adversaires[3].recevoirCartes(aDonnerJoueur3);
		} else if (sens.equals("a droite")) {
			adversaires[0].recevoirCartes(aDonnerJoueur2);
			adversaires[1].recevoirCartes(aDonnerJoueur3);
			adversaires[2].recevoirCartes(aDonnerJoueur4);
			adversaires[3].recevoirCartes(aDonnerJoueur1);
		} else if (sens.equals("en face")) {
			adversaires[0].recevoirCartes(aDonnerJoueur3);
			adversaires[1].recevoirCartes(aDonnerJoueur4);
			adversaires[2].recevoirCartes(aDonnerJoueur1);
			adversaires[3].recevoirCartes(aDonnerJoueur2);
		}
	}
	
	/**
	 * Fin de la partie
	 */
	public static void fin() {
		System.out.println("Appuyez sur une touche pour revenir au menu principal ... ");
		entree.nextLine();	
	}

	/**
	 * Met en pause le jeu 
	 */
	public static void pause() {
		System.out.println("Appuyez sur entree pour continuer ... ");
		entree.nextLine();	
	}

	/**
	 * Mélange et distribu les cartes
	 */
	public static void distribution() {
		System.out.println("------ Distribution des cartes ------");
		jeuOriginal.melanger(); // On mélange avant de distribuer
		Joueur.distribuerCartes(adversaires[0], adversaires[1],
		                        adversaires[2], adversaires[3], jeuOriginal);
		toutTrier();
	}

	/**
	 * trie les cartes de tous les joueurs
	 */
	public static void toutTrier() {
		adversaires[0].trierCartes();
		adversaires[1].trierCartes();
		adversaires[2].trierCartes();
		adversaires[3].trierCartes();
	}

	/**
	 * Détermine qui a gagné la partie
	 * @return numero du gagnant
	 */
	public static int calculGagnant() {
		int numGagnant;
		numGagnant = 0;
		for (int i = 1; i < adversaires.length; i++) {
			numGagnant = adversaires[i].getScore() < adversaires[numGagnant].getScore() ? i : numGagnant;
		}  
		return numGagnant+1;
	}

	/**
	 * Determine qui ramasse les cartes
	 * et les ajoutes au joueur
	 * @return le numéro du joueur qui ramasse
	 */
	public static int quiRammasse() {
		int numPerdant;      // Le numero du joueur qui doit ramasser
		String typeDemande;  // Le type de la première carte jouée
		Carte carteForte;    // La carte qui ramasse

		/* On vérifie d'abord quel était le type demandé */
		typeDemande = plateauDeJeu.getCartesEnJeux()[0].getType();

		numPerdant = 0;         // On initialise
		carteForte = aJouer[0];
		for (int i = 1; i < aJouer.length; i++) {
			if (aJouer[i].getType().equals(typeDemande)
			        && !carteForte.getType().equals(typeDemande)
			        || aJouer[i].getType().equals(typeDemande)
			        && carteForte.getType().equals(typeDemande)
	                && aJouer[i].getPuissance() > carteForte.getPuissance()) {
				numPerdant = i;
				carteForte = aJouer[i];
			}
		}
		adversaires[numPerdant].aJouterCartesRamassees(plateauDeJeu.getCartesEnJeux());
		return numPerdant+1;
	}

	/**
	 * Déroulement d'une manche
	 */
	public static void jouerManche() {
		int numJoueur = Joueur.quiCommence(adversaires[0], adversaires[1],
										   adversaires[2], adversaires[3]);

		/* Une manche dure 13 tours */
		for (int i = 0; i < 13; i++) {
			System.out.println("*** Debut du tour " + (i+1) + " ***\n");
			/* Un tour dure 4 mains */
			for (int j = 0; j < 4; j++) {
				System.out.println("\n\n--- Le joueur" + numJoueur + " joue ---\n\n");
				plateauDeJeu.afficherCartesEnJeux();
				if (numJoueur == 1) {
					System.out.println(adversaires[numJoueur-1].toString());
				}
				
				aJouer[numJoueur-1] = adversaires[numJoueur-1].choixCarteAJouer(plateauDeJeu);

				plateauDeJeu.ajouterCarteEnJeu(aJouer[numJoueur-1]);
				System.out.println("\nLe joueur" + numJoueur + " a joué : " 
					               + aJouer[numJoueur-1].toString() + "\n");
				adversaires[numJoueur-1].trierCartes();
				numJoueur++;
				numJoueur = numJoueur == 5 ? 1 : numJoueur;
				pause();
			}
			plateauDeJeu.actualiserPile();
			/* Le joueur qui a mis la carte la plus forte ramasse */
			numJoueur = quiRammasse();
			System.out.println("\n\nLe joueur" + numJoueur + " ramasse\n");
			plateauDeJeu.resetCarteEnJeux();
		}
		Joueur.calculerScore(adversaires);
		OutilAffichage.afficherTableauScore(adversaires);
		adversaires[0].resetCartesRamassees();
		adversaires[1].resetCartesRamassees();
		adversaires[2].resetCartesRamassees();
		adversaires[3].resetCartesRamassees();
		plateauDeJeu.resetPile();
	}
	
	/**
	 * affiche l'état du jeu en cours
	 */
	public static void afficherPlateau() {
		OutilAffichage.afficherTitreJeu();
		System.out.println(plateauDeJeu.toString());
	}

	/**
	 * C'est dans cette méthode que va se dérouler la partie
	 */
	public static void lancerPartie() {
		/* Initialisation de la partie */
		initialisation();  // Les joueurs sont crées ainsi que le plateau
		afficherPlateau(); // On affiche le plateau de jeu

		while (adversaires[0].getScore() < 100 && adversaires[1].getScore() < 100 
			   && adversaires[2].getScore() < 100 && adversaires[3].getScore() < 100) {
			/* Distribution des cartes */
			distribution();
			/* Echange des cartes avec le joueur de gauche */
			echanger("a gauche");
			toutTrier();
			/* 
		 	* Les joueurs jouent les uns après les autres
		 	* Le joueur qui a le 2 de trefle commence a jouer 
		 	*/
			jouerManche();

			if (adversaires[0].getScore() < 100 && adversaires[1].getScore() < 100 
			   && adversaires[2].getScore() < 100 && adversaires[3].getScore() < 100) {
				distribution();
				/* Echange des cartes avec le joueur de droite */
				echanger("a droite");
				toutTrier();
				jouerManche();
			}
			if (adversaires[0].getScore() < 100 && adversaires[1].getScore() < 100 
			   && adversaires[2].getScore() < 100 && adversaires[3].getScore() < 100) {
				distribution();
				/* Echange des cartes avec le joueur de droite */
				echanger("en face");
				toutTrier();
				jouerManche();
			}
			if (adversaires[0].getScore() < 100 && adversaires[1].getScore() < 100 
			   && adversaires[2].getScore() < 100 && adversaires[3].getScore() < 100) {
				distribution();
				/* Pas d'échange a la 4eme manche */
				toutTrier();
				jouerManche();
			}
		}

		System.out.println("\n\nLE JOUEUR" + calculGagnant() + " GAGNE LA PARTIE\n");

		fin();
	}

	/**
	 * Programme principal lancant le jeu 
	 * de la DameDePique
	 * @param args Non utilisé
	 */
	public static void main(String[] args) {

		char saisiePrincipale;		// Contient le charactere saisie dans le menu principal
		char saisieRegle;			// Contient le charactere saisie dans le menu des règles
		char saisieAideEnLigne;		// Contient le charactere saisie dans le menu de l'aide en ligne

		do {

			/* Affiche le menu principal ainsi que ces options */
			OutilAffichage.afficherMenu();	
			saisiePrincipale = OutilAffichage.selectionnerOptions();

			if (saisiePrincipale == 'j') {
				/* Affiche l'état du jeu en cours */
				lancerPartie();
			} else if (saisiePrincipale == 'r'){
				do {
					/* Affiche le menu principal ainsi que ces options */
					OutilAffichage.afficherRegles();
					saisieRegle = OutilAffichage.selectionnerOptions();
				} while(saisieRegle != 'q');

			} else if (saisiePrincipale == '?') {
				do {
					/* Affiche le menu principal ainsi que ces options */
					OutilAffichage.afficherAide();
					saisieAideEnLigne = OutilAffichage.selectionnerOptions();
				} while(saisieAideEnLigne != 'q');
			}
		} while(saisiePrincipale != 'q');		// Tant qu'on ne quitte pas l'application
	}
}
