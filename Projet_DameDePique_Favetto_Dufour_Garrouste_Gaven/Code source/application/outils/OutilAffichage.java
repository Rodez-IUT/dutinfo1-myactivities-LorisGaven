/* 
 * Classe contenant les methodes outils pour gerer les interfaces
 * de l'application 
 */
package application.outils;

import java.util.Scanner;
import application.objets.Joueur;


/** 
 * Cette classe contient differentes methodes outils correspondant a l'interface 
 * utilisateur de l'application "Dame de pique"
 * - reponseValide
 * - afficherMenu
 * - afficherjeu
 * - afficherRegles
 * - selectionnerOption
 * - afficherFinDuJeu
 * - afficherScoreFinal
 *
 * @author Adrien Garrouste, Kevin Dufour, Ambre Favetto
 * @version 1.0
 *
 */

public class OutilAffichage {

    /*
     * Objet scanner pour les saisies au clavier 
     */
    private static Scanner entree = new Scanner(System.in);

    /**
     * Texte de titre pour le menu principal
     */
    private static final String TITRE_MENU_PRINCIPAL = 
            "  ------------------------------------------------------------------\n"
            + "  |                      JEU DAME DE PIQUE                         |\n"
            + "  ------------------------------------------------------------------\n";
    
    /**
     * Texte de titre pour les règles du jeu
     */
    private static final String TITRE_MENU_REGLES = 
            "  ------------------------------------------------------------------\n"
            + "  |                      REGLES DU JEU                             |\n"
            + "  ------------------------------------------------------------------\n";

    /**
     * Première partie du texte pour les regles du jeu
     */
    public static final String TEXTE_MENU_REGLES_DU_JEU_1 = 
            
            "  ____________________________________________________\n\n"
            + "   1. Objectif de ce jeu : \n"
            + "  ____________________________________________________\n"
            + "\n  Accumuler le moins de points possible.\n"
            + "  Ce jeu de defausse met en concurrence 4 joueurs avec un jeu de 52 cartes.\n"
            + "  La partie se deroule sur plusieurs manches.\n"
            + "  A chaque manche, il faut se defausser du plus grand nombre de cartes valant des points ou,\n" 
            + "  au contraire, les accumuler toutes afin de realiser un « Grand Chelem ».\n\n" 
            + "  Les points des cartes sont : \n"
            + "    Dame de pique : 13 pts,\n"
            + "    Chaque coeur : 1 pts, \n"
            + "    Et les autres : 0 pts.\n"
            + "  Un « Grand Chelem » fait marquer 26 points d'un coup a chacun de ses adversaires.\n"
            + "\n";
    
    /**
     * Deuxième partie du texte pour les regles du jeu
     */
    public static final String TEXTE_MENU_REGLES_DU_JEU_2 = 
            "  ___________________________________________________\n\n"
            + "   2. Deroulement d'une partie :\n" 
            + "  ____________________________________________________\n" 
            + "\n  Une partie se deroule sur plusieurs manches, jusqu'a ce qu'un joueur ait atteint ou depasse 100 points.\n"
            + "  Le gagnant est le joueur ayant accumule le moins de points.\n"
            + "\n";

    /**
     * Troisième partie du texte pour les regles du jeu
     */
    public static final String TEXTE_MENU_REGLES_DU_JEU_3 =         
            "  ____________________________________________________\n\n"
            + "   3. Deroulement d'une manche :\n"
            +"  ____________________________________________________\n"
            + "\n  Etape 1 :\n\n"
            + "  Distribution des cartes :\n" 
            + "    Les joueurs distribuent les cartes a tour de role.\n"
            + "\n"
            + "  Etape 2 :\n\n"
            + "  Echange de 3 cartes :\n" 
            + "    Chacun des joueurs choisit 3 cartes de son jeu, les enleve de son jeu et les donne a un autre joueur faces cachees.\n"
            + "    Celui-ci ne peut les regarder tant qu'il n'a pas lui meme donne 3 cartes.\n"
            + "    Les echanges ne se font pas toujours avec le meme joueur.\n"
            + "    Effectivement, lors de La 1ere manche, les cartes sont donnees au voisin de gauche, puis a la manche suivante \n" 
            + "    au voisin de droite, puis au joueur en face.\n"
            + "    Enfin, a la quatrieme manche, il n'y a pas d'echange.\n" 
            + "    A partir de la cinquieme manche, on reprend les echanges selon le meme ordre (gauche, droite, en face, puis pas d'echange). \n"
            + "    Et ainsi de suite...\n"
            + "\n"
            + "  Etape 3 :\n\n"
            + "  1er tour de jeu :\n"
            + "    Lorsque les echanges sont acheves, le joueur qui detient le 2 de trefle commence.\n"
            + "    Le jeu se deroule dans le sens des aiguilles d'une montre. Chaque joueur doit fournir du trefle.\n" 
            + "    S'il n'en possede pas, il peut se defausser de carreau ou de pique (sauf la Dame de Pique).\n" 
            + "    Par contre, ici, il est interdit de se defausser de coeur. \n"
            + "    Celui qui a joue le trefle le plus fort remporte le pli et entame le tour de jeu suivant.\n"
            + "\n"
            + "  Etape 4 :\n\n" 
            + "    Les 12 tours de jeu suivants :\n"
            + "    Le joueur ayant remporte le pli (le tour de jeu) precedent commence. Pour l'entame, une seule restriction :\n" 
            + "    On ne peut pas entamer par un coeur si aucun coeur n'a deja ete defausse, \n"
            + "    sauf si le joueur ne possede plus que cette couleur dans sa main. \n"
            + "    Chaque joueur doit ensuite jouer la meme couleur que celle jouee par le joueur qui entame.\n" 
            + "    S'il ne possede pas de carte dans la couleur demandee, \n"
            + "    il peut se defausser d'une carte au choix y compris de la dame de pique ou d'un coeur. \n"
            + "    Celui qui a joue la carte la plus forte dans la couleur demandee remporte le pli et entame le suivant et ainsi de suite...\n";

    /** 
     * Texte affiche pour l'aide en ligne 
     */
    public static final String TEXTE_AIDE_MENU_PRINCIPAL = 
              "  ------------------------------------------------------------------\n"
            + "  |                             AIDE EN LIGNE                      |\n"
            + "  ------------------------------------------------------------------\n\n"
            + "  4 options disponibles sur cette application :\n"
            + "    - Une option pour jouer - (j)\n"
            + "    - Une option pour consulter les regles du jeu - (r)\n"
            + "    - Une option pour obtenir de l'aide sur le fonctionnement de l'application - (?)\n"
            + "    - Une option pour quitter l'application - (q)\n"
            + "\n\n  Option jouer : \n"
            +"    Pour jouer une carte il faut taper le chiffre place entre parentheses. \n"
            +"    Quand on donne 3 cartes à un autre joueurs en début de manche il faut taper 3 nombres différents.\n"
            +"    (Les cartes ne disparaissent pas, elles sont selectionnees et envoyees lorsque la derniere est entree).";
    
    /**
     * La structure du titre lorsque que le jeu
     * est en cours
     */
    private static String TITRE_JEU = 
              "  ------------------------------------------------------------------\n"
            + "  |                 JEU DAME DE PIQUE : JEU EN COURS               |\n"
            + "  ------------------------------------------------------------------\n";;

    /**
     * Liste des options possibles pour le menu
     */
    private static final char[] OPTIONS_MENU_PRINCIPAL = { 'j', 'r', '?', 'q'};  

    /**
     * Liste des libelles associes a chacune des options du menu principal
     */ 
    private static final String[] LIBELLES_MENU_PRINCIPAL = {
                "jouer au jeu",
                "consulter les regles du jeu",
                "obtenir de l'aide sur l'application",
                "quitter l'application"};

    /**
     *  Liste des options possibles pour le menu des règles
     */
    private static final char[] OPTIONS_MENU_REGLES = {'q'};
    
    /**
     * Liste des libelles associes a chacune des options du menu principal
     */ 
    private static final String[] LIBELLES_MENU_REGLES = {"Retour au menu principal"};
    
    /**
     * Liste des options possibles pour l'aide en ligne
     */
    private static final char[] OPTIONS_MENU_AIDE_EN_LIGNE = {'q'};
    
    /**
     * Liste des libelles associes a chacune des options du menu principal
     */ 
    private static final String[] LIBELLES_MENU_AIDE_EN_LIGNE = {"Retour au menu Principal"};
                                        
    
    /* **************************************************************************** */
    /* ************           gestion du menu principal             *************** */
    /* ***************************************************************************  */
    
    /**
     * saisi le choix de l'utilisateur.
     * L'action est repetee jusqu'a obtenir un choix valide
     * @return un caractere contenant le choix de l'utilisateur
     */
    public static char selectionnerOptions() {

        String reponse;       // utilise pour la lecture de la reponse de l'utilisateur
        
        // afiche le choix, repete jusqu'a obtenir un choix valide
        do {            
            reponse = entree.nextLine();
            if (!reponseValide(reponse, OPTIONS_MENU_PRINCIPAL)) {
                System.out.println("\n       ==> Ce choix est inorrect."
                                   + " Recommencez.\n\n");
            }
        } while (!reponseValide(reponse, OPTIONS_MENU_PRINCIPAL));     
        System.out.println();
        return reponse.charAt(0);
    }
    
    /**
     * Determine si la chaine argument est valide, c'est-a-dire contient un unique
     * caractere egal a l'un des caracteres du tableau choixCorrect
     * @param aTester       chaine de caracteres a tester
     * @param choixCorrect  tableau contenant des caracteres valides
     * @return un booleen egal a vrai si la chaine arguement est valide
     */
    public static boolean reponseValide(String aTester, char[] choixCorrect) {

        boolean resultat;           // resultat renvoye par la methode
                                    // vrai si la reponse argument est valide          
        if (aTester == null || aTester.length() != 1) {
           //  la reponse ne contient pas un caractere unique
           resultat = false;     
        } else {
            // on verifie si la reponse est presente dans le tableau 
            int i;  // indice de parcours
            for (i = 0; i < choixCorrect.length && aTester.charAt(0) != choixCorrect[i]; i++);
            resultat = i < choixCorrect.length;
            
        }
        return resultat;
    }

    /**
     * Affiche le menu principal de l'application
     */
    public static void afficherMenu() {

        System.out.println(TITRE_MENU_PRINCIPAL);
        
        // on affiche toutes les options et les libelles
        for(int i = 0; i < OPTIONS_MENU_PRINCIPAL.length; i++) {
            System.out.print("   => " + OPTIONS_MENU_PRINCIPAL[i] 
                             + " - " + LIBELLES_MENU_PRINCIPAL[i] + "\n");
        }
        System.out.print("\n       ====> ");
    }

    /**
     * Affiche les regles du jeu
     */
    public static void afficherRegles() {
        
        System.out.println(TITRE_MENU_REGLES);
        System.out.println(TEXTE_MENU_REGLES_DU_JEU_1);
        System.out.println("Appuyez sur la touche entree pour continuer de lire les regles...");
        entree.nextLine();
        System.out.println(TEXTE_MENU_REGLES_DU_JEU_2);
        System.out.println("Appuyez sur la touche entree pour continuer de lire les regles...");
        entree.nextLine();
        System.out.println(TEXTE_MENU_REGLES_DU_JEU_3);
        System.out.println("Appuyez sur la touche entree pour terminer de lire les regles...");
        entree.nextLine();
     // on affiche toutes les options et les libelles
        for(int i = 0; i < OPTIONS_MENU_REGLES.length; i++) {
            System.out.print("\n   => " + OPTIONS_MENU_REGLES[i] 
                             + " - " + LIBELLES_MENU_REGLES[i] + "\n");
        }
        System.out.print("\n       ====> ");
    }
    
    /**
     * Affiche l'aide en ligne 
     * @param texteAide  chaine contenant le texte de l'aide en ligne
     */
    public static void afficherAide() {

        System.out.println(TEXTE_AIDE_MENU_PRINCIPAL);
        
     // on affiche toutes les options et les libelles
        for(int i = 0; i < OPTIONS_MENU_AIDE_EN_LIGNE.length; i++) {
            System.out.print("\n   => " + OPTIONS_MENU_AIDE_EN_LIGNE[i] 
                             + " - " + LIBELLES_MENU_AIDE_EN_LIGNE[i] + "\n");
        }
        System.out.print("\n       ====> ");
    }

    /** 
     * Affiche la fin du jeu
     */
    public static void afficherFinDuJeu(){
        System.out.println(TITRE_JEU);
        System.out.println("FIN DE LA PARTIE\n SCORE FINAL : ");
    }
    
    /**
     * Affiche le titre lorsque le jeu
     * est en cours
     */
    public static void afficherTitreJeu() {
        System.out.println(TITRE_JEU);
    } 

    public static void afficherTableauScore(Joueur[] adversaires) {
        int score1 = adversaires[0].getScore();
        int score2 = adversaires[1].getScore();
        int score3 = adversaires[2].getScore();
        int score4 = adversaires[3].getScore();

        String tableauScore =  " ____________________________________________________ \n"
                             + "                 Tableau des scores                 \n"
                             + "                                                    \n"
                             + "   Joueur1 :\t" + score1 + "\n"
                             + "   Joueur2 :\t" + score2 + "\n"
                             + "   Joueur3 :\t" + score3 + "\n"
                             + "   Joueur4 :\t" + score4 + "\n"
                             + "\n ____________________________________________________\n";
        System.out.println(tableauScore);
    }
}