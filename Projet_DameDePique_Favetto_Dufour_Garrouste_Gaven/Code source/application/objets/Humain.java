/* 
 * Humain.java			20/05/2019
 * Aucun droits sur ce programme
 */

package application.objets;

import application.objets.Carte.*;
import java.util.Scanner;
import application.objets.PaquetDeCarte;
import application.objets.Plateau;
import application.objets.Joueur;

/**
 * La classe Joueur représente la structure 
 * de l'objet Humain. Elle est alors utilisée 
 * pour représenter un joueur humain dans l'application
 * @author Kevin, Loris, Ambre, Adrien
 * @version 1.0
 */
public class Humain extends Joueur {

	/**
	 * Constructeur de l'objet humain, il n'a pas plus de caractéristiques
	 * que la classe Joueur
	 */
	public Humain() {
		super();
	}

	/**
	 * Le joueur choisi les cartes à donner, ces cartes sont alors supprimées
	 * de son jeu
	 * @return Les cartes à donner
	 */
	public Carte[] choixCartesADonner() {
		Carte[] aDonner = new Carte[3];
		boolean continuer;
		int numCarte; // Numéro de la carte à jouer

		for (int i = 0; i < aDonner.length; i++) {
			System.out.println("Choix de la carte numero " + (i+1) + " a donner");
			do {
				System.out.print("\tNumero de la carte a donner : ");
				numCarte = entree.hasNextInt() ? entree.nextInt()-1 : -1;
				entree.nextLine();
				continuer = numCarte < 0 || numCarte > 12 
				            || cartesEnMain[numCarte] == null;
				if (continuer)
					System.out.println("Aucune carte ne correspond au numero que"
						               + " vous avez choisi");
			} while (continuer);
			aDonner[i] = cartesEnMain[numCarte];
			cartesEnMain[numCarte] = null;
		}
		return aDonner;
    }

    /**
     * Le joueur choisi la carte à poser sur le plateau
     * @param plateauDeJeu le plateau sur lequel il joue
     * @return La carte à poser
     */
    public Carte choixCarteAJouer(Plateau plateauDeJeu) {
		Carte aJouer;
		boolean continuer;
		int numCarte; // Numéro de la carte à jouer
		String typeDemande; 
		boolean oblige;  // Le joueur est obligé de jouer du type de la 1ere carte jouée
		boolean trefle;  // Si le joueur a le 2 de trefle il est obligé de le jouer

        /* On regarde si le joueur a une carte du type demandé */
        typeDemande = "";
        oblige = false; // On initialise à false
        trefle = false;
		System.out.println("Choix de la carte a jouer");
		do {
			System.out.print("\tNumero de la carte a jouer : ");
			numCarte = entree.hasNextInt() ? entree.nextInt()-1 : -1;
			entree.nextLine();
			continuer = numCarte < 0 || numCarte > 12 
			            || cartesEnMain[numCarte] == null;

			 /* On regarde si le joueur a le 2 de trefle */
			for (int i = 0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
				if (cartesEnMain[i].getType().equals("Trefle")
					&& cartesEnMain[i].getPuissance() == 2)
					trefle = true;
			}

			/* On détermine le type qui est demandé */
			if (plateauDeJeu.getCartesEnJeux()[0] != null) {
				typeDemande = plateauDeJeu.getCartesEnJeux()[0].getType();
				for (int i = 0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
					if (cartesEnMain[i].getType().equals(typeDemande)) 
						oblige = true;
				}
			} else {
				typeDemande = plateauDeJeu.getCartesEnJeux()[0] == null && !continuer
							  ? cartesEnMain[numCarte].getType() : typeDemande;
			}


			if (continuer) {
				System.out.println("Aucune carte ne correspond au numero que"
					               + " vous avez choisi");
			} else if (oblige && !cartesEnMain[numCarte].getType().equals(typeDemande)) {
				System.out.println("Attention : vous devez jouer une carte du type demande");
				continuer = true;
			} else if (plateauDeJeu.calculerTour() == 1 
				       && (cartesEnMain[numCarte].getType().equals("Coeur")
				       || (cartesEnMain[numCarte].getType().equals("Pique")
				       && cartesEnMain[numCarte].getPuissance() == 12))) {
				System.out.println("Attention : vous ne pouvez pas jouer "
					            + "une carte valant des points au 1er tour");
				continuer = true;

			} else if (trefle && (!cartesEnMain[numCarte].getType().equals("Trefle")
			   	       || cartesEnMain[numCarte].getPuissance() != 2)) {
				System.out.println("Attention : vous devez jouer le 2 de Trefle");
				continuer = true;
			} else if (plateauDeJeu.getCartesEnJeux()[0] == null 
				       && !plateauDeJeu.coeurDansPile()
				       && cartesEnMain[numCarte].getType().equals("Coeur")
				       && !queDuCoeur()) {
				System.out.println("Attention : vous ne pouvez pas jouer "
					             + "de coeur tant que personne n'en a joue");
				continuer = true;
			}

		} while (continuer);
		aJouer = cartesEnMain[numCarte];
		cartesEnMain[numCarte] = null;
		this.trierCartes();
		
		return aJouer;
	}

	/**
	 * Parcours les cartes en main du joueur
	 * @return true si il n'a que du coeur
	 *         false si il a autre chose     
	 */
	public boolean queDuCoeur() {
		boolean queDuCoeur=true;
		for (int i=0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
			if (!cartesEnMain[i].getType().equals("Coeur")) {
				queDuCoeur = false;
			}
		}
		return queDuCoeur;
	}

}
