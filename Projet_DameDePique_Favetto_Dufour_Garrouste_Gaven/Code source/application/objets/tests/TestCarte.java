/* TestCarte.java				23/04/2019 
 * Aucun droits sur ce programme
 */
package application.objets.tests;

import java.util.Scanner;

import application.objets.Carte;

/**
 * Cette classe permet les tester toutes 
 * les méthodes de classe structurant l'objet Carte. 
 * (test unitaire). 
 * @author Kevin, Loris, Ambre, Adrien
 * @version 2.1
 */
public class TestCarte {
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 				DECLARATION DES CONSTANTES ET VARIABLES
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */
	
	/* Types de carte utilisées pour l'application */
	public static final String[] TYPES = {"Pique", "Trefle", "Carreau", "Coeur"}; 
	
	/* Puissances utilisées pour l'application */
	public static final int[] PUISSANCES = {2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 12, 13, 12}; 
	
	/* Objets permettant les tests des methodes
	 * getType, getValeur, getPuissance, getCouleur
	 */

	/* Valeurs des instances d'objets Carte*/
	public static final String[] TYPES_TEST = {"Pique", "Carreau", "Coeur", "Trefle"};
	public static final int[] PUISSANCE_TEST= {12, 12, 8, 9};
	
	/* Contient les cartes nécéssaire au test */
	public static Carte[] cartes = new Carte[4];
	
	/* Utiliser pour la méthode continuer */
	public static Scanner entree = new Scanner(System.in);
	
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 		DECLARATION DES METHDOES DE ESSENTIEL A LA CLASSE TEST
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */
	
	/** Ajoute les cartes dans le tableau de carte */
	public static void initialisationCartes() {		
		/* Ajout par balayage des cartes dans le tableau cartes */
		for (int i = 0; i < TYPES_TEST.length; i++ ) {
			try {
				cartes[i] = new Carte(TYPES_TEST[i], PUISSANCE_TEST[i]);
			} catch (IllegalArgumentException e) { }
		}
	}
	
	/** Sert à faire une rupture (pause) entre différents tests */
	public static void continuer() {
		
		System.out.println("\n\nAppuyer sur une touche pour continuer et confirmer");
		entree.nextLine();
	}
	
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 					METHODES DE TEST DE LA CLASSE CARTE
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */
	/** Test du constructeur d'un objet Carte */
	public static void testConstructeur() {
		int nbReussi;							// Nombre de test réussi
		int nbEchoue;							// Nombre de test echoué 
		
		Carte[] cartesTest = new Carte[13];
		/* Valeurs des différents pour les objets créer */
		String[] typesTest = 
			{
				"Pique", "Carreau", "Coeur", "Trefle", 
				"Pique", "Carreau", "Coeur", "Trefle",
				"Fleur", "Coeur", "Carreau", "COeur",
				"Triangle"
			};
		int[] puissancesTest = {5, 12, 8, 9, 13, 2, -9, 16, 2, 7, 12, 12, 12};

		boolean[] echecsAttendus = {false, false, false, false, false, false,
		                            true, true, true, false, false, true, true}; 
		
		System.out.println("==============================================\n"
						 + "Test du constructeur Carte()"
						 + "\n===============================================");
		/* Ajout par balayage des cartes dans le tableau cartes */
		for (int i = 0; i < puissancesTest.length; i++ ) {
			try {
				cartesTest[i] = new Carte(typesTest[i], puissancesTest[i]);
			} catch (IllegalArgumentException e) { 
				/* Message affiché lors d'une initialisation echoué */
				System.out.println("L'initialisation de l'objet " + i
								 + " N'a pas pu être effectué");
				cartesTest[i] = null;
			}
		}
		
		/* Initialisation des variables */
		nbReussi = nbEchoue = 0;
		
		/* Verifie si ce sont les bonnes exceptions et les bons objets
		 * qui ont été initialisé 
		 */
		for (int i = 0; i < cartesTest.length; i++) {
			if ((cartesTest[i] != null && !echecsAttendus[i]) || (cartesTest[i] == null && echecsAttendus[i] )) {
				nbReussi++;
			} else {
				nbEchoue++;
			}
		}
		
		System.out.println("Nombre de test réussi : " + nbReussi 
				 + "\n Nombre de test Echoué : " + nbEchoue);
		
		continuer();
	}
	
	/** Test de la méthode getType */
	public static void testGetType() {
		int nbReussi;							// Nombre de test réussi
		int nbEchoue;							// Nombre de test echoué 
		
		/* Ce que la méthode à tester devrait retourner */
		String[] retourCorrect = {"Pique", "Carreau", "Coeur", "Trefle"};
		
		System.out.println("===============================================\n"
				 + "Test de la méthode getType()"
				 + "\n===============================================");
		
		/* Initialisation des variables */
		nbReussi = nbEchoue = 0;
		for (int i = 0; i < retourCorrect.length; i++ ) {
			if (retourCorrect[i].equals(cartes[i].getType())) {
				nbReussi++;
			} else {
				System.out.println("Echec pour l'objet Carte n°" + i );
				nbEchoue++;
			}
		}
		
		System.out.println("Nombre de test réussi : " + nbReussi 
						 + "\n Nombre de test Echoué : " + nbEchoue);
		continuer();
	}
	
	/** Test de la méthode getValeur */
	public static void testGetValeur() {
		int nbReussi;							// Nombre de test réussi
		int nbEchoue;							// Nombre de test echoué 
		
		/* Ce que la méthode à tester devrait retourner */
		int[] retourCorrect = {13, 0, 1, 0};
		
		System.out.println("===============================================\n"
				 + "Test de la méthode getValeur()"
				 + "\n===============================================");
		
		/* Initialisation des variables */
		nbReussi = nbEchoue = 0;
		for (int i = 0; i < retourCorrect.length; i++ ) {
			if (retourCorrect[i] == cartes[i].getValeur()) {
				nbReussi++;
			} else {
				System.out.println("Echec pour l'objet Carte n°" + i );
				nbEchoue++;
			}
		}
		
		System.out.println("Nombre de test réussi : " + nbReussi 
						 + "\n Nombre de test Echoué : " + nbEchoue);
		continuer();
	}
	
	/** Test de la méthode getPuissance */
	public static void testGetPuissance() {
		int nbReussi;							// Nombre de test réussi
		int nbEchoue;							// Nombre de test echoué 
		
		/* Ce que la méthode à tester devrait retourner */
		int[] retourCorrect = {12, 12, 8, 9};
		
		System.out.println("===============================================\n"
				 + "Test de la méthode getPuissance()"
				 + "\n===============================================");
		
		/* Initialisation des variables */
		nbReussi = nbEchoue = 0;
		for (int i = 0; i < retourCorrect.length; i++ ) {
			if (retourCorrect[i] == cartes[i].getPuissance()) {
				nbReussi++;
			} else {
				System.out.println("Echec pour l'objet Carte n°" + i );
				nbEchoue++;
			}
		}
		
		System.out.println("Nombre de test réussi : " + nbReussi 
						 + "\n Nombre de test Echoué : " + nbEchoue);
		continuer();
	}
	
	/** Test de la méthode getCouleur */
	public static void testGetCouleur() {
		int nbReussi;							// Nombre de test réussi
		int nbEchoue;							// Nombre de test echoué 
		
		/* Ce que la méthode à tester devrait retourner */
		String[] retourCorrect = {"Noire", "Rouge", "Rouge", "Noire"};
		
		System.out.println("===============================================\n"
				 + "Test de la méthode getCouleur()"
				 + "\n===============================================");
		
		/* Initialisation des variables */
		nbReussi = nbEchoue = 0;
		for (int i = 0; i < retourCorrect.length; i++ ) {
			if (retourCorrect[i].equals(cartes[i].getCouleur())) {
				nbReussi++;
			} else {
				System.out.println("Echec pour l'objet Carte n°" + i );
				nbEchoue++;
			}
		}
		
		System.out.println("Nombre de test réussi : " + nbReussi 
						 + "\n Nombre de test Echoué : " + nbEchoue);
		continuer();
	}
	
	/** Test de la m�thode toString */
	public static void testToString() {
		System.out.println("===============================================\n"
				 + "Test de la m�thode toString() (Visuel)"
				 + "\n===============================================");
		/* On balaye toutes les cartes afin d'afficher leurs contenu*/
		for(int i = 0; i < cartes.length ; i++) {
			System.out.println(cartes[i].toString());
		}
		
		continuer();
	}
	
	/** Test de la m�thode calculsPoids */
	public static void testCalculPoids() {
		int nbReussi;							// Nombre de test réussi
		int nbEchoue;							// Nombre de test echoué 
		
		/* Ce que la méthode à tester devrait retourner */
		int[] retourCorrect = {62, 22, 38, 79};
		
		System.out.println("===============================================\n"
				 + "Test de la méthode calculPoids()"
				 + "\n===============================================");
		
		/* Initialisation des variables */
		nbReussi = nbEchoue = 0;
		for (int i = 0; i < retourCorrect.length; i++ ) {
			if (retourCorrect[i] == cartes[i].calculPoids()) {
				nbReussi++;
			} else {
				System.out.println("Echec pour l'objet Carte n°" + i );
				nbEchoue++;
			}
		}
		
		System.out.println("Nombre de test réussi : " + nbReussi 
						 + "\n Nombre de test Echoué : " + nbEchoue);
		continuer();
	}
	
	/**
	 * Execute touts les tests
	 * concernant les méthodes
	 * la classe Carte
	 * @param args non utilisé 
	 */
	public static void main (String args[]) {
		initialisationCartes(); 	// indispensable pour le bon déroulement des tests
		testConstructeur();
		testGetType();
		testGetValeur();
		testGetPuissance();
		testGetCouleur();
		testToString();
		testCalculPoids();
	}
}
