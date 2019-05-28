/* TestPlateau.java					13/05/2019
 * Aucun droit sur ce programme
 */
package application.objets.tests;

import application.objets.Carte;
import application.objets.Joueur;
import application.objets.Plateau;

import java.util.Scanner;

/**
 * Cette classe permet les tester toutes 
 * les m�thodes de classe structurant l'objet Plateau. 
 * (test unitaire). 
 * @author Kevin, Loris, Ambre, Adrien
 * @version 1.0
 */
public class TestPlateau {
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 				DECLARATION DES CONSTANTES ET VARIABLES
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */

	/* Valeurs des instances d'objets Carte*/
	public static final String[] TYPES_TEST = 
		{
				"Pique", "Carreau", "Coeur", "Trefle",
				"Pique", "Carreau", "Coeur", "Trefle"
		};

	public static final int[] PUISSANCE_TEST= 
		{
				2, 2, 2, 2,
				2, 2, 2, 2
		};

	/* Contient les cartes n�c�ssaire aux tests */
	public static Carte[] cartes = new Carte[8];

	/* Objet aTester */
	public static Plateau aTester;

	/* Utiliser pour la m�thode continuer */
	public static Scanner entree = new Scanner(System.in);
	
	public static Joueur testJoueur = new Joueur();

	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 		DECLARATION DES METHDOES DE ESSENTIEL A LA CLASSE TEST
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */

	/** Sert � faire une rupture (pause) entre diff�rents tests */
	public static void continuer() {

		System.out.println("\n\nAppuyer sur une touche pour continuer et confirmer");
		entree.nextLine();
	}

	/** initialise ce qui est n�c�ssaire pour les tests de Plateau */
	public static void initialisation() {
		/* Initialisation de aTester */
		aTester = new Plateau();

		/* Ajout par balayage des cartes dans le tableau cartes */
		for (int i = 0; i < TYPES_TEST.length; i++ ) {
			try {
				cartes[i] = new Carte(TYPES_TEST[i],
						PUISSANCE_TEST[i]);
			} catch (IllegalArgumentException e) { }
		}
	}

	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 					METHODES DE TEST DE LA CLASSE PLATEAU
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */

	/** Test de la m�thode incNbTours */
	public static void testIncNbTours() {
		/* Test Visuel */ 

		System.out.println("===============================================\n"
				+ "Test de la m�thode incNbTours() (Visuel)"
				+ "\n===============================================");

		for (int i = 0; i < 5; i++ ) {
			System.out.println("Appel num�ro " + (i+1) + " de la methode incNbTours()");

			/* Incr�mentation du nombre de tours */
			//aTester.incNbTours();

			System.out.println("Tours num�ro : " + aTester.getNumManche() + "\n");
		}

		System.out.println("Fin des tests ");
		continuer();
	}

	/** Test de la m�thode getNbTours */
	public static void testGetNumManche() {
		int nbReussi;							// Nombre de test r�ussi
		int nbEchoue;							// Nombre de test echou� 

		/* Ce que la m�thode � tester devrait retourner */
		int[] retourCorrect = {5, 6, 7, 8};

		System.out.println("===============================================\n"
				+ "Test de la m�thode getNbTours()"
				+ "\n===============================================");

		/* Initialisation des variables */
		nbReussi = nbEchoue = 0;
		for (int i = 0; i < retourCorrect.length; i++ ) {
			if (retourCorrect[i] == aTester.getNumManche()) {
				nbReussi++;
			} else {
				System.out.println("Echec pour l'objet Carte n�" + i );
				nbEchoue++;
			}

			/* Incr�mentation du nombre de tours */
			//aTester.incNbTours();
		}

		System.out.println("Nombre de test r�ussi : " + nbReussi 
				+ "\n Nombre de test Echou� : " + nbEchoue);
		continuer();
	}

	/** Test de la m�thode ajouterCarteEnjeux */
	public static void testAjouterCarteEnjeux() {

		System.out.println("===============================================\n"
				+ "Test de la m�thode ajouterCarteEnjeux() (Visuel)"
				+ "\n===============================================");

		for (int i = 0; i < 4; i++ ) {
			aTester.ajouterCarteEnJeu(cartes[i]);

			System.out.println("\nVoici l'�tat du tableau carteEnJeux : \n");
			aTester.afficherCartesEnJeux();
		}
		continuer();
	}

	/** Test de la m�thode afficherCarteEnJeux */
	public static void testAfficherCarteEnJeux() {

		System.out.println("===============================================\n"
				+ "Test de la m�thode afficherCarteEnJeux() (Visuel)"
				+ "\n===============================================");

		aTester.afficherCartesEnJeux();
		continuer();
	}
	
	/** Test de la m�thode toString */
	public static void testToString() {

		System.out.println("===============================================\n"
				+ "Test de la m�thode toString() (Visuel)"
				+ "\n===============================================");

		System.out.println(aTester.toString());
		continuer();
	}

	/** Test de la m�thode resetCarteEnJeux */
	public static void testResetCarteEnJeux() {
		System.out.println("===============================================\n"
				+ "Test de la m�thode resetCarteEnJeux() (Visuel)"
				+ "\n===============================================");
		/* On execute la m�thode */
		aTester.resetCarteEnJeux();

		/* On observe si le resultat est correcte */
		aTester.afficherCartesEnJeux();
		continuer();
	}
	
//	/** Test de la m�thode ajouterDansPile */
//	public static void testAjouterDansPile() {
//		System.out.println("===============================================\n"
//				+ "Test de la m�thode ajouterDansPile() (Visuel)"
//				+ "\n===============================================");
//		System.out.println("Essai num�ro 1 : ");
//		/* On execute la m�thode */
//		aTester.ajouterDansPile(cartes);
//
//		/* On observe si le resultat est correcte */
//		aTester.getPile();
//
//		System.out.println("Essai num�ro 2 : ");
//		aTester.ajouterDansPile(cartes);
//
//		/* On observe si le resultat est correcte */
//		aTester.getPile();
//		continuer();
//	}

//	/** Test de la m�thode getPile */
//	public static void testGetPile() {
//		System.out.println("===============================================\n"
//				+ "Test de la m�thode getPile() (Visuel)"
//				+ "\n===============================================");
//		aTester.getPile();
//		continuer();
//	}

	/**
	 * Execute touts les tests
	 * concernant les m�thodes
	 * la classe Plateau
	 * @param args non utilis� 
	 */
	public static void main (String args[]) {
		initialisation(); 	// Indispensable pour le bon fonctionnement
//		testIncNbTours();
		
		testGetNumManche();
		testAjouterCarteEnjeux();
		testAfficherCarteEnJeux();
		testResetCarteEnJeux();
		testToString();
//		testCalculerScore();
		
//		testAjouterDansPile();
//		testGetPile();
	}
}

/*
 *  TODO : LORSQUE  QUE TU AURA LA BONNE CLASSE CARTE NE PAS OUBLIER DE CHANGER LES VALEURS DES VARIABLES, 
 *  DE LINITIALISATEUR
 *  DE PLUS FAIRE LA METHODE DE TEST POUR afficherScore() QUAND JOUEUR SERA DISPO
 */
