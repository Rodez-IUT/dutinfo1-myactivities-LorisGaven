/* 
 * IA.java			23/05/2019
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
 * de l'objet IA. Elle est alors utilisée 
 * pour représenter un joueur qui a une intelligence artificielle 
 * dans l'application
 * @author Kevin, Loris, Ambre, Adrien
 * @version 1.0
 */
public class IA extends Joueur {

	/** L'IA se souvient des cartes qui ont été jouées */
	private Carte[] memoire;

	private static boolean carreau,
	                       coeur,
	                       pique,
	                       trefle;

	/**
	 * L'IA possède les caractéristiques d'un joueur
	 * avec en plus une mémoire
	 */
	public IA() {
		super();
		/* 
		 * On initialise la taille à 52 car c'est le max de cartes jouées
		 * lors d'une manche.
		 */ 
		this.memoire = new Carte[52];
	}

	/**
	 * L'IA va choisir de donner les 3 cartes de son jeu qui on la
	 * puissance la plus élévée
	 * @return Les cartes à donner
	 */
	public Carte[] choixCartesADonner() {
		/* Les indices des cartes ajoutées */
		int[] indices = {0, 1, 2};
		
	    /* On initialise les cartes à donner avec les 3 premières cartes */
		Carte[] aDonner = {cartesEnMain[0], cartesEnMain[1], cartesEnMain[2]};

		/* On cherche les 3 cartes les plus fortes */
		for (int i=3; i < cartesEnMain.length; i++) {
			if (cartesEnMain[i].getPuissance() > aDonner[0].getPuissance()) {
				aDonner[0] = cartesEnMain[i];
				indices[0] = i;
			} else if (cartesEnMain[i].getPuissance() > aDonner[1].getPuissance()) {
				aDonner[1] = cartesEnMain[i];
				indices[1] = i;
		    } else if (cartesEnMain[i].getPuissance() > aDonner[2].getPuissance()) {
				aDonner[2] = cartesEnMain[i];
				indices[2] = i;
			}
		}

		/* On supprime du jeu les cartes à donner */
		for (int i=0; i < indices.length; i++) {
			cartesEnMain[indices[i]] = null;
		}

		return aDonner;
	}

	/**
	 * L'IA va déterminer quelle est la meilleure carte à jouer
	 * @param plateauDeJeu le plateau de la partie
	 * @return la carte à jouer
	 */
	public Carte choixCarteAJouer(Plateau plateauDeJeu) {
		/* La carte à jouer */
		Carte aJouer=null;

		/* True si le joueur a une carte de ce type dans les mains */
		carreau=false;
	    coeur=false;
		pique=false;
		trefle=false;

		String typeDemande = ""; // Le type qu'on est obligé de jouer
		int puissanceSup = 0;    // La puissance la plus haute présente

		this.trierCartes();       // On trie le jeu avant de commencer
		this.actualiserMemoire(plateauDeJeu); // On met à jour la mémoire

		/* On détermine quels type de carte sont présents dans la main */
		for (int i=0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
			if (cartesEnMain[i].getType().equals("Carreau"))
				carreau = true;
			if (cartesEnMain[i].getType().equals("Coeur"))
				coeur = true;
			if (cartesEnMain[i].getType().equals("Pique"))
				pique = true;
			if (cartesEnMain[i].getType().equals("Trefle"))
				trefle = true;
		}

		/* L'IA joue en première */
		if (plateauDeJeu.getCartesEnJeux()[0] == null) {
			/* L'IA a le 2 de trefle */
			if (rechercheTrefle() != -1) {
				aJouer = cartesEnMain[rechercheTrefle()];

			/* L'IA n'a pas le 2 de trefle */
			} else {
				/* 
				 * Si la probabilité que les adversaires aient tous du type
				 * est supérieure à 50% on joue carte haute
				 */
				if (nbType("Carreau") > 6 && carreau) {
					aJouer = plusForte("Carreau", 15, 0);
				} else if (nbType("Trefle") > 6 && trefle) {
					aJouer = plusForte("Trefle", 15, 0);
				} else if (nbType("Pique") > 6 && pique) {
					/* 
					 * On fait attention de ne pas jouer plus haut que la dame
					 * si elle n'a pas été jouée ou quelle n'est pas dans
					 * les mains de l'IA
					 */
					if (dameAdversaires()) {
						aJouer = plusForte("Pique", 12, 0);
					} else {
						aJouer = plusForte("Pique", 15, 12);
					}

				} else if (plateauDeJeu.calculerTour() == 0 
					       || !plateauDeJeu.coeurDansPile()) {
					aJouer = plusFaible("Coeur");
				} else {
					aJouer = plusFaible("");
				}
			}

		/* L'IA ne joue pas en première */	
		} else {
			/* On cherche d'abord quel est le type demandé */
			typeDemande = plateauDeJeu.getCartesEnJeux()[0].getType();

			/* 
			 * Si on a du type demandé on joue la carte la plus haute
			 * possible tout en restant en dessous de la plus haute posée
			 * sur le plateau
			 */
			if (oblige(typeDemande)) {
				if (superieurDame(plateauDeJeu) && dameEnMain() != -1) {
					aJouer = cartesEnMain[dameEnMain()];
				} else if (nbType(typeDemande) > 6 && !defausse(plateauDeJeu) 
					       && dameAdversaires() && typeDemande.equals("Pique")) {
					aJouer = plusForte(typeDemande, 12, 0);
				} else if (nbType(typeDemande) > 6 && !defausse(plateauDeJeu)
					&& !typeDemande.equals("Coeur")) {
					aJouer = plusForte(typeDemande, 15, 0);
				} else {
					/* 
					 * On cherche la carte de puissance la plus haute et de
					 * type demandé sur le plateau
					 */
					for (int i=0; i < plateauDeJeu.getCartesEnJeux().length 
					      	    && plateauDeJeu.getCartesEnJeux()[i] != null; i++) {
						if (plateauDeJeu.getCartesEnJeux()[i].getType().equals(typeDemande)) {
							puissanceSup = 
						    	plateauDeJeu.getCartesEnJeux()[i].getPuissance() > puissanceSup
							    ? plateauDeJeu.getCartesEnJeux()[i].getPuissance() : puissanceSup;
						}
					}
					aJouer = plusForte(typeDemande, puissanceSup, 0);
			    }
			/*
			 * Si l'IA n'a pas le type demandé elle peut jouer ce quelle veut
			 */
			} else {
				/* On se débarasse en priorité de la dame de pique */
				if (dameEnMain() != -1 && plateauDeJeu.calculerTour() != 1) {
					aJouer = cartesEnMain[dameEnMain()];
				/* Puis des cartes coeur les plus hautes */
				} else if (coeur && plateauDeJeu.calculerTour() != 1) {
					aJouer = plusForte("Coeur", 15, 0);
				/* Puis des autres cartes */
				} else if (pique) {
					aJouer = plusForte("Pique", 15, 0);
				} else if (trefle) {
					aJouer = plusForte("Trefle", 15, 0);
				} else if (carreau) {
					aJouer = plusForte("Carreau", 15, 0);
				}
			}
		}

		/* On supprime la carte que l'on va jouer */
		for (int i=0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
			if (cartesEnMain[i].getType().equals(aJouer.getType())
				&& cartesEnMain[i].getPuissance() == aJouer.getPuissance()) {
				cartesEnMain[i] = null;
			}
		}

		return aJouer;
	}

	/**
	 * Met à jour la mémoire de l'IA
	 * @param plateauDeJeu le plateau de la partie
	 */
	public void actualiserMemoire(Plateau plateauDeJeu) {
		memoire = plateauDeJeu.getPile();
	}

	/**
	 * Cherche si il y le 2 de trefle dans les mains de l'IA
	 * @return l'indice du 2 de trefle si il est présent
	 *		   sinon -1
	 */
	public int rechercheTrefle() {
		int indiceTrefle;
		indiceTrefle = -1;
		for (int i=0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
			if (cartesEnMain[i].getType().equals("Trefle") 
				&& cartesEnMain[i].getPuissance() == 2) {
				indiceTrefle = i;
			}
		}
		return indiceTrefle;
	}

	/**
	 * Compte combien de carte du type demandé sont dans les mains des adversaires
	 * @param type le type à chercher
	 * @return le nombre de carte du type demandé
	 */
	public int nbType(String type) {
		int nbType=0;
		for (int i=0; i < memoire.length && memoire[i] != null; i++) {
			if (memoire[i].getType().equals(type)) {
				nbType++;
			}
		}
		for (int i=0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
		if (cartesEnMain[i].getType().equals(type)) {
				nbType++;
			}
		}

		return (13 - nbType);
	}

	/**
	 * Cherche la carte la plus puissante du type demandé
	 * @param type le type demandé
	 * @param borneSup ne prend pas les carte au dessus
	 * @param ignorer puissance à ignorer
	 * @return la carte la plus forte
	 */
	public Carte plusForte(String type, int borneSup, int ignorer) {
		Carte carteLaPlusForte;
		boolean continuer;

		/* On initialise avec la carte la plus faible du type demandé */
		carteLaPlusForte = null;
		for (int i=0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
			if (cartesEnMain[i].getType().equals(type))
				carteLaPlusForte = cartesEnMain[i];
		}
		continuer = true;
		for (int i=0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
			if (cartesEnMain[i].getType().equals(type)) {
				carteLaPlusForte =
			    	carteLaPlusForte.getPuissance() > cartesEnMain[i].getPuissance()
			    	? cartesEnMain[i] : carteLaPlusForte;
			}
		}

		/* On détermine quelle carte est la plus forte */
		for (int i=0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
			if (cartesEnMain[i].getType().equals(type) 
				&& cartesEnMain[i].getPuissance() < borneSup
				&& cartesEnMain[i].getPuissance() != ignorer) {
				carteLaPlusForte = 
				    carteLaPlusForte.getPuissance() < cartesEnMain[i].getPuissance()
				    ? cartesEnMain[i] : carteLaPlusForte;
			}
		}
		return carteLaPlusForte;
	}

	/**
	 * Cherche où se trouve la dame de pique
	 * @return true si la dame est dans les mains de l'adversaire
	 *		   false si elle est dans nos mains ou déjà jouée
	 */
	public boolean dameAdversaires() {
		/* True si la dame est dans les mains ou dans la mémoire */
		boolean present=false;

		/* On cherche dans la memoire */
		for (int i=0; i < memoire.length && memoire[i] != null; i++) {
			if (memoire[i].getType().equals("Pique") 
				&& memoire[i].getPuissance() == 12) {
				present = true;
			}
		}

		/* On cherche dans les mains */
		for (int i=0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
			if (cartesEnMain[i].getType().equals("Pique") 
				&& cartesEnMain[i].getPuissance() == 12) {
				present = true;
			}
		}

		/* Si il est présent alors les adversaires ne l'on pas */
		return !present;
	}

	/**
	 * Cherche la carte la plus faible dans les mains du joueur
	 * @param ignorer le type à ignorer
	 */ 
	public Carte plusFaible(String ignorer) {
		Carte carteLaPlusFaible=null;

		/* On initialise avec la carte la plus forte d'un des types */
		if (carreau) {
			carteLaPlusFaible = plusForte("Carreau", 15, 0);
		} else if (trefle) {
			carteLaPlusFaible = plusForte("Trefle", 15, 0);
		} else if (pique) {
			carteLaPlusFaible = plusForte("Pique", 15, 0);
		} else if (coeur) {
			carteLaPlusFaible = plusForte("Coeur", 15, 0);
		}

		for (int i=1; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
			if (!cartesEnMain[i].getType().equals(ignorer)) {
				carteLaPlusFaible =
			    	carteLaPlusFaible.getPuissance() > cartesEnMain[i].getPuissance()
			    	? cartesEnMain[i] : carteLaPlusFaible;
			}
		}
		return carteLaPlusFaible;
	}

	/**
	 * Détermine si on a une carte du type demandé
	 * @param typeDemande
	 * @return true si on a une carte du type demandé
	 *         sinon false
	 */
	public boolean oblige(String typeDemande) {
		boolean aRetourner=false;
		for (int i=0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
			if (cartesEnMain[i].getType().equals(typeDemande)) {
				aRetourner = true;
			}
		} 
		return aRetourner;
	}

	/**
	 * Recherche si l'IA a la dame de pique en main
	 * @return l'indice de la dame de pique si elle est reouvée
	 * 		   sinon -1
	 */
	public int dameEnMain() {
		int indiceDame = -1;

		for (int i=0; i < cartesEnMain.length && cartesEnMain[i] != null; i++) {
			if (cartesEnMain[i].getType().equals("Pique")
				&& cartesEnMain[i].getPuissance() == 12) {
				indiceDame = i;
			}
		}
		return indiceDame;
	}

	/**
	 * Détermine si quelqu'un a joué la défausse
	 * @param plateauDeJeu le plateau de la partie
	 * @return vrai si on est en situation de defausse
	 *         sinon faux
	 */
	public boolean defausse(Plateau plateauDeJeu) {
		boolean defausse=false;

		for (int i=0; i < plateauDeJeu.getCartesEnJeux().length
			          && plateauDeJeu.getCartesEnJeux()[i] != null ; i++) {
			if (!plateauDeJeu.getCartesEnJeux()[i].getType()
				.equals(plateauDeJeu.getCartesEnJeux()[0].getType())) {
				defausse=true;
			}
		}
		return defausse;
	}

	/**
	 * @param plateauDeJeu
	 * @return vrai si il y a une carte de type pique supérieure à la dame
	 *         sur le plateau
	 */
	public boolean superieurDame(Plateau plateauDeJeu) {
		boolean aRetourner=false;
		for(int i=0; i < plateauDeJeu.getCartesEnJeux().length
			         && plateauDeJeu.getCartesEnJeux()[i] != null; i++) {
			if (plateauDeJeu.getCartesEnJeux()[i].getType().equals("Pique")
				&& plateauDeJeu.getCartesEnJeux()[i].getPuissance() > 12) {
				aRetourner = true;
			}
		}
		return aRetourner;
	}
}	


