/* TestPaquetDeCarte.java			25/04/2019
 * aucun droits sur ce programme
 */
package application.objets.tests;

import java.util.Scanner;
import application.objets.PaquetDeCarte;

/**
 * Cette classe permet les tester toutes 
 * les m�thodes de classe structurant l'objet PaquetDeCarte. 
 * (test unitaire). 
 * @author Kevin, Loris, Ambre, Adrien
 * @version 3.0
 */
public class TestPaquetDeCarte {
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 				DECLARATION DES CONSTANTES ET VARIABLES
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */

	public final static int NB_CARTES_DANS_PAQUET = 52;
	public static PaquetDeCarte essai = new PaquetDeCarte();

	/* Utiliser pour la m�thode continuer */
	public static Scanner entree = new Scanner(System.in);

	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 		DECLARATION DES METHDOES DE ESSENTIEL A LA CLASSE TEST
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */
	/** Sert � faire une rupture (pause) entre diff�rents tests */
	public static void continuer() {

		System.out.println("\n\nAppuyer sur une touche pour continuer et confirmer");
		entree.nextLine();
	}

	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 			METHODES DE TEST DE LA CLASSE PAQUETDECARTE
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */
	/** Test du constructeur d'un objet PaquetDeCarte */
	public static void testConstructeur() {

		System.out.println("===============================================\n"
				+ "Test du constructeur PaquetDeCarte() (Visuel)"
				+ "\n===============================================");

		for (int i = 0; i < 52; i++) {
			System.out.println(essai.getPaquet()[i].toString());
		}

		continuer();
	}

	/** Test de la m�thode melanger */
	public static void testMelanger() {

		System.out.println("===============================================\n"
				+ "Test de la m�thode melanger() (Visuel)"
				+ "\n===============================================");

		essai.melanger();
		for (int i = 0; i < 52; i++) {
			System.out.println(essai.getPaquet()[i].toString());
		}

		continuer();
	}

	/** Test de la méthode getNbCartes */
	public static void testGetNbCartes() {
		int nbReussi;							// Nombre de test réussi
		int nbEchoue;							// Nombre de test echoué 

		/* Ce que la méthode à tester devrait retourner */
		int[] retourCorrect = {NB_CARTES_DANS_PAQUET};

		System.out.println("===============================================\n"
				+ "Test de la m�thode getNbCartes()"
				+ "\n===============================================");

		/* Initialisation des variables */
		nbReussi = nbEchoue = 0;
		for (int i = 0; i < retourCorrect.length; i++ ) {
			if (essai.getNbCartes() == NB_CARTES_DANS_PAQUET) {
				nbReussi++;
			} else {
				System.out.println("Echec pour l'objet PaquetDeCarte essai");
				nbEchoue++;
			}
		}

		System.out.println("Nombre de test réussi : " + nbReussi 
				+ "\n Nombre de test Echoué : " + nbEchoue);
		continuer();
	}

	/** Test de la méthode setNbCartes */
	public static void testSetNbCartes() {
		int nbReussi;							// Nombre de test réussi
		int nbEchoue;							// Nombre de test echoué 

		/* Ce que la méthode à tester devrait retourner */
		int[] aTester = 	  {10,20,30,55,52,-1,53,0};
		int[] retourCorrect = {10,20,30,30,52,52,52,0};

		System.out.println("===============================================\n"
				+ "Test de la m�thode SetNbCartes()"
				+ "\n===============================================");

		/* Initialisation des variables */
		nbReussi = nbEchoue = 0;
		for (int i = 0; i < retourCorrect.length; i++ ) {
			essai.setNbCartes(aTester[i]);
			if (essai.getNbCartes() == retourCorrect[i]) {
				nbReussi++;
			} else {
				System.out.println("Echec pour l'objet � l'index " + i);
				nbEchoue++;
			}
		}

		System.out.println("Nombre de test réussi : " + nbReussi 
				+ "\n Nombre de test Echoué : " + nbEchoue);
		continuer();
	}
	/**
	 * Execute touts les tests
	 * concernant les m�thodes
	 * la classe PaquetDeCarte
	 * @param args non utilis� 
	 */
	public static void main(String[] args) {
		testConstructeur();
		testGetNbCartes();
		testMelanger();
		testSetNbCartes();
	}

}
