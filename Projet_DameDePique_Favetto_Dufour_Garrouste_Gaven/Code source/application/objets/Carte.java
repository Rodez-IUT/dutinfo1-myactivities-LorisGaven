/* Carte.java			23/04/2019
 * Aucun droits sur ce programme
 */

package application.objets;

/**
 * La classe Carte représente la structure 
 * de l'objet Carte. Elle est alors utilisée 
 * pour représenter une Carte dans l'application
 * qu'elle soit dans la main d'un joueur dans
 * le pile de jeu ou encore dans la dans le 
 * paquet de distribution des mains (pour les joueurs).
 * En plus de cela les objet Carte peuvent composer un
 * pâquet de Carte représenter par l'objet PaquetDeCarte
 * @author Kevin, Loris, Ambre, Adrien
 * @version 2.1
 */
public class Carte {
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 				DECLARATION DES CONSTANTES ET VARIABLES
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */
	
	/* Les différentes constantes contiennent 
	 * les contraintes de l'application
	 * en ce qui concerne l'objet Carte
	 */
	public final int VALEUR_COEUR = 1;			// Valeur de toutes les Cartes de type coeur
	public final int VALEUR_DAME_PIQUE = 13;	// Valeur de la dame de pique
	public final int VALEUR_AUTRE = 0;			// Valeur des autres Cartes
	public final int PUISSANCE_MIN = 2;
	public final int PUISSANCE_MAX = 14;
	
	private String type;		// type de la Carte (Pique, Trefle, Carreau, Coeur)
	private int valeur;			// Nombre de points que rapporte la Carte
	private int puissance;		// Ordre de puissance de la Carte (Sert à comparer les Cartes)
	private String couleur;		// Couleur de la Carte (Noir ou Rouge)
	
	/*+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	 * 			  METHODES DE LA CLASSE Carte ET CONSTRUCTEURS
	 *+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+ 
	 */
	/**
	 * Constructeur des objets Carte
	 * cohérent avec le jeu DameDePique
	 * @param type le type de la Carte
	 * @param puissance la puissance de la Carte 
	 * @throws IllegalArgumentException <p><ul>
	 * 		<li>Si le type est pas Pique, Trefle, Carreau, Coeur</li>
	 * 		<li>Si la puissance de la Carte est < que PUISSANCE_MIN ou > que PUISSANCE_MAX</li>
	 * </ul></p>
	 */
	public Carte(String type, int puissance) 
		throws IllegalArgumentException {
		
		/* Exception levée en fonction de la contrainte non respectée */
		if (!type.equals("Pique") && !type.equals("Trefle")
			&& !type.equals("Carreau") && !type.equals("Coeur")) {
			throw new IllegalArgumentException();
			
		} else if (puissance < PUISSANCE_MIN || puissance > PUISSANCE_MAX){
			throw new IllegalArgumentException();

		} else {
			/* Si aucune exception levée on initialise l'objet*/
			/* On détermine la valeur de la Carte */
			if (!(type.equals("Pique") && puissance == 12) && !type.equals("Coeur")) {
				/* Si la Carte n'est pas la dame de pique ou un coeur alors la valeur est 0 */
				this.valeur = VALEUR_AUTRE;
			} else if (type.equals("Coeur")) {
				/* Si la Carte est de type coeur sa valeur est 1 */
				this.valeur = VALEUR_COEUR;
			} else {
				/* Si la Carte est la dame de pique sa valeur est 13 */
				this.valeur = VALEUR_DAME_PIQUE;
			}

			/* On détermine la couleur */
			if (type.equals("Coeur") || type.equals("Carreau")) {
				/* Si le type est coeur ou carreau la Carte est rouge */
				this.couleur = "Rouge";
			} else {
				/* Si le type est pique ou trefle la Carte est noire */
				this.couleur = "Noire";
			}

			this.puissance = puissance;
			this.type = type;
		}
	}

	/** 
	 * @return le type de Carte
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return la valeur valeur de la Carte
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * @return la puissance de la Carte
	 */
	public int getPuissance() {
		return puissance;
	}

	/**
	 * @return la couleur de la Carte
	 */
	public String getCouleur() {
		return couleur;
	}


	@Override
	public String toString() {
		String numero; // ex : 2, 3, J, Q, 1, ...
		if (puissance == 11) {
			numero = "J";
		} else if (puissance == 12) {
			numero = "Q";
		} else if (puissance == 13) {
			numero = "K";
		} else if (puissance == 14) {
			numero = "1";
		} else {
			numero = "" + puissance;
		}
		
		return "" + numero + ' ' + type;
	}


	/**
	 * Calcule le poids des cartes pour pouvoir les trier
	 * cette méthode est utilisée dans Joueur
	 */
	public int calculPoids() {
		int poids;
		poids = 0;
		if (type.equals("Carreau")) {
			poids = 10;
		} else if (type.equals("Coeur")) {
			poids = 30;
		} else if (type.equals("Pique")) {
			poids = 50;
		} else if (type.equals("Trefle")) {
			poids = 70;
		}

		return poids + puissance;
	}
}
