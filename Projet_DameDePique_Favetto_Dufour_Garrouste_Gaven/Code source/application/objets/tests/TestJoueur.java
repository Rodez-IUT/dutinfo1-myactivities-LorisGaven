/*
 * TestJoueur.java					15/05/2019
 * Aucun de droits d'auteur
 */
package application.objets.tests;

import application.objets.Carte;
import java.util.Scanner;
import application.objets.Joueur;
import application.objets.Joueur.*;
import application.objets.PaquetDeCarte;
import application.objets.Humain;
import application.objets.IA;

/**
 * Tests de la classe Joueur
 * @author Kevin, Loris, Ambre, Adrien
 * @version 2.0
 */
public class TestJoueur {
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 				DECLARATION DES CONSTANTES ET VARIABLES
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */

	/** Le paquet a distribuer */
	public static PaquetDeCarte paquet = new PaquetDeCarte();

	/** Utiliser pour la méthode continuer */
	public static Scanner entree = new Scanner(System.in);

	/** Quelques joueurs pour les tests */
	public static  Joueur joueur1,
	              		  joueur2, 
	              		  joueur3, 
	              		  joueur4; 
	
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 		DECLARATION DES METHDOES DE ESSENTIEL A LA CLASSE TEST
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */

	/** Sert à faire une rupture (pause) entre différents tests */
	public static void continuer() {
		
		System.out.println("\n\nAppuyer sur une touche pour continuer et confirmer\n\n");
		entree.nextLine();
	}

	/**
	 * test du constructeur de joueur (visuel)
	 */
	public static void testConstructeurJoueur() {
		System.out.println("---- Test des constructeur ----\n\n");
		joueur1 = new Humain();
	    joueur2 = new IA();
	    joueur3 = new Humain();
	    joueur4 = new IA();

	    System.out.println(joueur1.toString() + joueur2.toString()
	    		           + joueur3.toString() + joueur4.toString());

	    continuer();
	}

	/**
	 * test de la méthode de distribution des cartes (visuel)
	 */
	public static void testDistribuerCartes() {
		System.out.println("---- Test de distribuerCartes ----\n\n");
		paquet.melanger();
		Joueur.distribuerCartes(joueur1, joueur2, joueur3, joueur4, paquet);
		System.out.println(joueur1.toString() + "\n"+ joueur2.toString()
	    		           + "\n" + joueur3.toString() + "\n" + joueur4.toString());

		continuer();
	}

	/**
	 * test de la méthode qui détermine le joueur qui commence (visuel)
	 */
	public static void testQuiCommence() {
		System.out.println("---- Test de quiCommence ----\n\n");
		Joueur.quiCommence(joueur1, joueur2, joueur3, joueur4);
		if (joueur1.getCommence()) {
			System.out.println("Le joueur1 commence");
		} else if (joueur2.getCommence()) {
			System.out.println("Le joueur2 commence");
		} else if (joueur3.getCommence()) {
			System.out.println("Le joueur3 commence");
		} else if (joueur4.getCommence()) {
			System.out.println("Le joueur4 commence");
		} else {
			System.out.println("ERREUR");
		}

		continuer();

	}

	/**
	 * test de la méthode qui demande au joueur de choisir les cartes à donner
	 */
	public static void testChoixCartesADonner() {
		Carte[] cartesDonnees;

		System.out.println("---- Test de choixCartesADonner ----\n\n");
		System.out.println("** Joueur1 **\n\n");
		cartesDonnees = joueur1.choixCartesADonner();
		System.out.println("Cartes donnees : ");
		for (int i = 0; i < cartesDonnees.length; i++) {
			System.out.println(cartesDonnees[i]);
		}
		System.out.println("** Joueur2 **\n\n");
		cartesDonnees = joueur2.choixCartesADonner();
		for (int i = 0; i < cartesDonnees.length; i++) {
			System.out.println(cartesDonnees[i]);
		}
		System.out.println("** Joueur3 **\n\n");
		cartesDonnees = joueur3.choixCartesADonner();
		for (int i = 0; i < cartesDonnees.length; i++) {
			System.out.println(cartesDonnees[i]);
		}
		System.out.println("** Joueur4 **\n\n");
		cartesDonnees = joueur4.choixCartesADonner();
		for (int i = 0; i < cartesDonnees.length; i++) {
			System.out.println(cartesDonnees[i]);
		}

		continuer();
	}

	/**
	 * test de la méthode trier cartes (visuel)
	 */
	public static void testTrierCartes() {
		System.out.println("---- Test de trierCartes ----\n\n");
		joueur1.trierCartes();
		joueur2.trierCartes();
		joueur3.trierCartes();
		joueur4.trierCartes();
		System.out.println(joueur1.toString() + "\n"+ joueur2.toString()
	    		           + "\n" + joueur3.toString() + "\n" + joueur4.toString());

		continuer();
	}

	/**
	 * Execute les tests
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		testConstructeurJoueur();
		testDistribuerCartes();
		testQuiCommence();
		testChoixCartesADonner();
		testTrierCartes();
	}
}