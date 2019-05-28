/*
 * PaquetDeCarte.java 		06/05/2019 
 * Aucun droits sur ce programme 
 */
package application.objets;

import application.objets.Carte;

/**
 * La classe PaquetDeCarte représente
 * la structure de l'objet représentant le
 * paquet de carte avant la distribution
 * @author Kevin, Loris, Ambre, Adrien
 * @version 2.0
 */
public class PaquetDeCarte {
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 				DECLARATION DES CONSTANTES ET VARIABLES
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */
	public final int NB_CARTES_MIN = 0;		// Nombre de cartes minimum dans le paquet
	public final int NB_CARTES_MAX = 52; 		// Nombre de cartes maximal dans le paquet

	/* 
	 * Le paquet est repr�sent� par un tableau de type Carte 
	 * de taile 54 (le nombre de cartes d'un jeu)
	 */
	private Carte[] paquet = new Carte[NB_CARTES_MAX];

	private int nbCartes;				// Nombre de cartes dans le paquet	

	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 			  METHODES DE LA CLASSE CARTE ET CONSTRUCTEURS
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */
	/**
	 * Constructeur du paquet de carte
	 * coh�rent avec le jeu dame de pique
	 * Aucun param�tre utilis�
	 */
	public PaquetDeCarte() {
		/* On remplit le paquet de cartes */
		int j = 0;
		for (int i = 0; i < paquet.length/4; i++, j++) {
			this.paquet[j] = new Carte("Coeur", 2+i);
		}
		for (int i = 0; i < paquet.length/4; i++, j++) {
			this.paquet[j] = new Carte("Pique", 2+i);
		}
		for (int i = 0; i < paquet.length/4; i++, j++) {
			this.paquet[j] = new Carte("Carreau", 2+i);
		}
		for (int i = 0; i < paquet.length/4; i++, j++) {
			this.paquet[j] = new Carte("Trefle", 2+i);
		}

		/* Le nombre de cartes est alors maximal */
		this.nbCartes = NB_CARTES_MAX;
	}

	/**
	 * recup�re le nombre de cartes du paquet
	 * @return le nombre de cartes du paquet
	 */
	public int getNbCartes() {
		return this.nbCartes;
	}

	/**
	 * Modifie le nombre de cartes du paquet
	 * @param nbCartes le nouveau nombre de cartes du paquet
	 */
	public void setNbCartes(int nbCartes) {
		if (nbCartes < NB_CARTES_MIN || nbCartes > NB_CARTES_MAX) {
			System.out.println("Le paquet de cartes ne peut pas etre "
					+ "negatif ou superieur � 52 ");
		} else {
			this.nbCartes = nbCartes;
		}
	}

	/**
	 * R�cup�re le tableau contenant les cartes
	 * @return le paquet
	 */
	public Carte[] getPaquet() {
		return paquet;
	}

	/**
	 * Permet de definir un nouvel
	 * ordre des cartes dans le paquet
	 */
	public void melanger() {
		int indiceA, // Les indices qui seront g�n�r�s
		indiceB;     // al�atoirement

		Carte temp;  // Variable temporaire utile � la permutation

		/* On permute 100 fois des cartes al�atoirement */
		for (int i = 0; i < 100; i++) {
			indiceA = (int)(Math.random() * (NB_CARTES_MAX));
			indiceB = (int)(Math.random() * (NB_CARTES_MAX));
			temp = paquet[indiceA];
			paquet[indiceA] = paquet[indiceB];
			paquet[indiceB] = temp;
		}
	}
}
