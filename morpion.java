public class morpion {
    /**
     * Initialise l'application
     * @param args
     */
    public static void main(String[] args) {
        
         
        int [][] plateauDeJeu;
        int taillePlateauDeJeu;
        int nbrJetonsALigner;
        int joueurCourant = 0;
        int coordonneeX, coordonneeY;
        int nbrCasesLibres;
        
     // Boucle infinie pour un nombre de parties de jeu infini.
        while (true) {
            Terminal.ecrireStringln("Quelle est la taille du plateauDeJeu ?" );
            taillePlateauDeJeu= Terminal.lireInt();
            plateauDeJeu = nouveaujeu(taillePlateauDeJeu, taillePlateauDeJeu);
            Terminal.ecrireStringln("Combien de jetons a aligner pour gagner ?");
            nbrJetonsALigner= Terminal.lireInt();
            nbrCasesLibres = taillePlateauDeJeu * taillePlateauDeJeu ;
            affiche(plateauDeJeu);
            
         // Boucle tant que la partie n'est pas terminÃ©e.
             do {
                if (joueurCourant < 2) {
                    joueurCourant++;
                } else {
                    joueurCourant = 1;
                }
                
             // Boucle tant que le joueur courant n'a pas saisi de
                // coordonnÃ©es valides.

                do {
                    Terminal.ecrireStringln("Joueur " + joueurCourant + " :");
                    coordonneeX = Terminal.lireInt();
                    coordonneeY = Terminal.lireInt();

                } while (!metAjour(plateauDeJeu, joueurCourant, coordonneeY, coordonneeX));
                 nbrCasesLibres--; 
                 affiche(plateauDeJeu);

            } while (!partieFinie(plateauDeJeu, nbrJetonsALigner)&& nbrCasesLibres > 0);
             
             if (nbrCasesLibres == 0){
                 Terminal.ecrireStringln(" Match nul, aucun joueur ne gagne la partie ");
             } else {
                 Terminal.ecrireStringln("Le joueur " + joueurCourant + " gagne la partie !");
             }
             
            Terminal.ecrireStringln("--------------------------------\n");
            Terminal.ecrireStringln("NOUVELLE PARTIE :\n");
        }
    }
    
    
               
   /**
    * Initialise le plateau de jeu               		        
    * @param nbreLignes
    * @param nbreColonnes
    * @return Le plateau de jeu
    */
    private static int[][] nouveaujeu(int nbreLignes,int nbreColonnes){
        return new int [nbreLignes][nbreColonnes];
    }
    
    
    /**
     * Affiche le plateau de jeu (=une matrice Ã  deux dimensions), selon une
     * reprÃ©sentation texte multiLigneIndexs.
     * 
     * @param plateauDeJeu
     *            Le plateau de jeu Ã  afficher.
     * @return La reprÃ©sentation texte multiLignes du plateau de jeu.
     */
    public static String affiche(int[][] plateauDeJeu) {
      
        String plateauDeJeuEnTexte = "n/";
        int iColonneIndex, iLigneIndex;
        for (iLigneIndex = 0; iLigneIndex < plateauDeJeu.length; iLigneIndex++) {
            for (iColonneIndex = 0; iColonneIndex < plateauDeJeu[iLigneIndex].length; iColonneIndex++) {

                plateauDeJeuEnTexte += plateauDeJeu[iLigneIndex][iColonneIndex];
            }
            plateauDeJeuEnTexte += ".";
        }
       
        System.out.println(plateauDeJeuEnTexte);
        return plateauDeJeuEnTexte;
    }
    
    
    /**
     * Met Ã  jour le plateau de jeu (une matrice Ã  deux dimensions) en plaÃ§ant
     * un jeton (= une valeur entiÃ¨re > 0) dans une de ses cases (Ã©lÃ©ments)
     * selon des coordonnÃ©es comprises entre 1 et la taille de chaque dimension.
     * Un contrÃ´le prÃ©alable est effectuÃ© sur les coordonnÃ©es fournie et sur la
     * valeur du jeton Ã  placer.
     * 
     * @param plateauDeJeu
     *            Le plateau de jeu Ã  mettre Ã  jour.
     * @param numJoueur
     *            Le numÃ©ro du joueur Ã  affecter comme jeton dans la case du
     *            plateau de jeu.
     * @param numLigne
     *            La ligne sur le plateau de jeu.
     * @param numColonne
     *            La colonne sur le plateau de jeu.
     * @return Un boolÃ©en Ã  vrai si la mise-Ã -jour est effectuÃ©e, ou Ã  faux si
     *         un contrÃ´le prÃ©alables a rÃ©vÃ©lÃ© une erreurs dans l'appel de la
     *         mÃ©thode.
     */

    public static boolean metAjour(int[][] plateauDeJeu, int numJoueur, int numLigne, int numColonne) {
       
        if (numLigne < 1 || numLigne > plateauDeJeu.length || numColonne < 1 || numColonne > plateauDeJeu[0].length) {
            System.out.println("La saisie de la position de la case aux coordonnÃ©es x=" + numLigne + " et y= " + numColonne
                    + " est incorrecte sur le plateau de jeu de taille [" + plateauDeJeu.length + ","
                    + plateauDeJeu[plateauDeJeu.length - 1].length + "]");
            return false;
        }

       
        if (plateauDeJeu[numLigne - 1][numColonne - 1] != 0) {
            Terminal.ecrireStringln("La position de la case aux coordonnÃ©es x=" + numLigne + "et y= " + numColonne
                    + " est dÃ©jÃ  affectÃ©e au joueur " + plateauDeJeu[numLigne - 1][numColonne - 1] + " ! \n");
            return false;
        }

        // Joue la case (= affecte sa valeur au numÃ©ro du joueur).
        plateauDeJeu[numLigne - 1][numColonne - 1] = numJoueur;
        return true;
    }
    
    
    
    
    /**
     * ContrÃ´le si un joueur a gagnÃ© la partie, cÂ’est-Ã -dire si le plateau de
     * jeu contient un groupe de jetons identiques et tous contigÃ¼s Ã  un autre
     * du groupe.
     * 
     * @param plateauDeJeu
     *            Le plateau de jeu dont l'Ã©tat est Ã  vÃ©rifier.
     * @param nbrJetonsALigner
     *            Le nombre de jetons Ã  aligner.
     * @return
     */
    public static boolean partieFinie(int[][] plateauDeJeu, int nbrJetonsALigner) {
        if (chercheGroupesJetonsHorizontaux(plateauDeJeu, nbrJetonsALigner)
                || chercheGroupesJetonsVerticaux(plateauDeJeu, nbrJetonsALigner)
                || chercheGroupesJetonsDiagonaux(plateauDeJeu, nbrJetonsALigner)
                || chercheGroupesJetonsDiagonauxSpeciaux(plateauDeJeu, nbrJetonsALigner)){ 
            return true; 
        }
        return false;
    }
    
    
    /**
     * Cherche les groupes horizontaux de jetons identiques et tous contigÃ¼s Ã 
     * un autre du groupe sur la mÃªme ligne (=ayant un Ã©cart de colonne
     * strictement Ã©gal Ã  1 par rapport Ã  un des autres jetons identiques et
     * contigÃ¼s sur cette ligne). S'arrÃªte et retourne vrai lorsque le nombre de
     * jetons trouvÃ©s dans un mÃªme groupe est Ã©gal Ã  celui constituant une
     * partie finie, sinon retourne faux.
     * 
     * @param plateauDeJeu
     *            Le plateau de jeu (= la matrice de 2 dimensions)
     * @param nbrDeJetonsPourGagner
     *            Indique le nombre de jetons identiques et contigÃ¼es Ã  trouver
     *            dans un mÃªme groupe pour finir la partie.
     * @return BoolÃ©en Ã  vrai si une partie est finie, sinon faux.
     */
    public static boolean chercheGroupesJetonsHorizontaux(char[][] plateauDeJeu, int nbrDeJetonsPourGagner) {   
        int valeurJetonPrecedent;
        int nbrJetonsTrouvesDansGroupeActuel;
        int iIndexColonne, iIndexLigne;
        for (iIndexLigne = 0; iIndexLigne < plateauDeJeu.length; iIndexLigne++) {
            
            nbrJetonsTrouvesDansGroupeActuel = 1;
            
            valeurJetonPrecedent = plateauDeJeu[iIndexLigne][0];
            
            for (iIndexColonne = 1; iIndexColonne < plateauDeJeu[0].length; iIndexColonne++) {
               
                if (plateauDeJeu[iIndexLigne][iIndexColonne] == valeurJetonPrecedent
                        && plateauDeJeu[iIndexLigne][iIndexColonne] != 0) {
                    
                    nbrJetonsTrouvesDansGroupeActuel++;
                    
                    if (nbrDeJetonsPourGagner == nbrJetonsTrouvesDansGroupeActuel) {
                        return true; 
                    }
                }
                
                valeurJetonPrecedent = plateauDeJeu[iIndexLigne][iIndexColonne];
            }
        }
        return false;
    }

    
    /**
     * Cherche les groupes verticaux de jetons identiques et tous contigÃ¼s Ã  un
     * autre du groupe sur la mÃªme colonne (=ayant un Ã©cart de ligne strictement
     * Ã©gal Ã  1 par rapport Ã  un des autres jetons identiques et contigÃ¼s sur
     * cette colonne). S'arrÃªte et retourne vrai lorsque le nombre de jetons
     * trouvÃ©s dans un mÃªme groupe est Ã©gal Ã  celui constituant une partie
     * finie, sinon retourne faux.
     * 
     * @param plateauDeJeu
     *            Le plateau de jeu (=la matrice de 2 dimensions)
     * @param nbrDeJetonsPourGagner
     *            Indique le nombre de jetons identiques et contigÃ¼es Ã  trouver
     *            dans un mÃªme groupe pour finir la partie.
     * @return BoolÃ©en Ã  vrai si une partie est finie, sinon faux.
     */
    public static boolean chercheGroupesJetonsVerticaux(char[][] plateauDeJeu, int nbrDeJetonsPourGagner) {
    
        int valeurJetonPrecedent;

        int nbrJetonsTrouvesDansGroupeActuel;

       
        int iIndexColonne, iIndexLigne;

        
        for (iIndexColonne = 0; iIndexColonne < plateauDeJeu[0].length; iIndexColonne++) {
            
            nbrJetonsTrouvesDansGroupeActuel = 1;
            
            valeurJetonPrecedent = plateauDeJeu[0][iIndexColonne];
           
            for (iIndexLigne = 1; iIndexLigne < plateauDeJeu.length; iIndexLigne++) {
               
                if (plateauDeJeu[iIndexLigne][iIndexColonne] == valeurJetonPrecedent
                        && plateauDeJeu[iIndexLigne][iIndexColonne] != 0) {
                   
                    nbrJetonsTrouvesDansGroupeActuel++;
                   
                    if (nbrDeJetonsPourGagner == nbrJetonsTrouvesDansGroupeActuel) {
                        return true; 
                    }
                }
                valeurJetonPrecedent = plateauDeJeu[iIndexLigne][iIndexColonne];
            }
        }
        return false;
    }

   
    /**
     * Cherche les groupes diagonaux de jetons identiques et tous contigÃ¼s Ã  un
     * autre du groupe sur la mÃªme diagonale (= ayant un Ã©cart de colonne et de
     * ligne simultanÃ©ment et strictement Ã©gal Ã  1 par rapport au jeton
     * prÃ©cÃ©dent et/ou suivant. S'arrÃªte et retourne vrai lorsque le nombre de
     * jetons trouvÃ©s dans un mÃªme groupe est Ã©gal Ã  celui constituant une
     * partie finie, sinon retourne faux.
     * 
     * @param plateauDeJeu
     *            Le plateau de jeu (= la matrice de 2 dimensions)
     * @param nbrDeJetonsPourGagner
     *            Indique le nombre de jetons identiques et contigÃ¼es Ã  trouver
     *            dans un mÃªme groupe pour finir la partie.
     * @return BoolÃ©en Ã  vrai si une partie est finie, sinon faux.
     */
    public static boolean chercheGroupesJetonsDiagonaux(char[][] plateauDeJeu, int nbrDeJetonsPourGagner) {
       
        int valeurJetonPrecedent;
        int nbrJetonsTrouvesDansGroupeActuel;  
        int iIndexColonne, iIndexLigne;
        int iIndexColonneCourante;
          for (iIndexColonne = 0; iIndexColonne < plateauDeJeu[0].length; iIndexColonne++) {
           
            nbrJetonsTrouvesDansGroupeActuel = 1;
            valeurJetonPrecedent = plateauDeJeu[0][iIndexColonne];
            iIndexColonneCourante = iIndexColonne + 1;
            for (iIndexLigne = 1; (iIndexLigne < plateauDeJeu.length
                    && iIndexColonneCourante < plateauDeJeu[0].length); iIndexLigne++) {
               
                if (plateauDeJeu[iIndexLigne][iIndexColonneCourante] == valeurJetonPrecedent
                        && plateauDeJeu[iIndexLigne][iIndexColonneCourante] != 0) {
                    
                    nbrJetonsTrouvesDansGroupeActuel++;
                    
                    if (nbrDeJetonsPourGagner == nbrJetonsTrouvesDansGroupeActuel) {
                        return true; 
                    }
                }
                
                valeurJetonPrecedent = plateauDeJeu[iIndexLigne][iIndexColonneCourante];
              
                iIndexColonneCourante++;
            }
            
            nbrJetonsTrouvesDansGroupeActuel = 1;
            valeurJetonPrecedent = plateauDeJeu[0][iIndexColonne];

           
            iIndexColonneCourante = iIndexColonne -1 ;
            for (iIndexLigne = 1; (iIndexLigne < plateauDeJeu.length && iIndexColonneCourante >= 0); iIndexLigne++) {
                
                if (plateauDeJeu[iIndexLigne][iIndexColonneCourante] == valeurJetonPrecedent
                        && plateauDeJeu[iIndexLigne][iIndexColonneCourante] != 0) {
                         nbrJetonsTrouvesDansGroupeActuel += 1;
      
                    if (nbrDeJetonsPourGagner == nbrJetonsTrouvesDansGroupeActuel) {
                        return true; 
                    }
                } 
                valeurJetonPrecedent = plateauDeJeu[iIndexLigne][iIndexColonneCourante];
                iIndexColonneCourante--;
            }
        }
        return false;
       
        }
  
  
    /**
     * Cherche les groupes diagonaux de jetons identiques et tous contigÃ¼s Ã  un
     * autre du groupe sur la mÃªme diagonale (= ayant un Ã©cart de colonne et de
     * ligne simultanÃ©ment et strictement Ã©gal Ã  1 par rapport au jeton
     * prÃ©cÃ©dent et/ou suivant. Ici il s'agit d'itÃ©rer sur les diagonales
     * descendantes situÃ©es au dÃ©part de la 1Ã¨re colonne uniquement et 2Ã¨me
     * ligne jusqu'Ã  l'avant-derniÃ¨re ligne, puis celles situÃ©es au dÃ©part de la
     * derniÃ¨re colonne uniquement et 2Ã¨me ligne jusqu'Ã  l'avant-derniÃ¨re ligne
     * ; cette mÃ©thode permet de complÃ©ter l'autre mÃ©thode cherchant les
     * diagonales au dÃ©part de la 1Ã¨re ligne.
     * 
     * S'arrÃªte et retourne vrai lorsque le nombre de jetons trouvÃ©s dans un
     * mÃªme groupe est Ã©gal Ã  celui constituant une partie finie, sinon retourne
     * faux.
     * 
     * @param plateauDeJeu
     *            Le plateau de jeu (= la matrice de 2 dimensions)
     * @param nbrDeJetonsPourGagner
     *            Indique le nombre de jetons identiques et contigÃ¼es Ã  trouver
     *            dans un mÃªme groupe pour finir la partie.
     * @return BoolÃ©en Ã  vrai si une partie est finie, sinon faux.
     */  
  public static boolean chercheGroupesJetonsDiagonauxSpeciaux(char[][] plateauDeJeu, int nbrDeJetonsPourGagner) {
   
         
        int valeurJetonPrecedent;
        int nbrJetonsTrouvesDansGroupeActuel;
        int iIndexLigne, iIndexColonne;
        int iIndexLigneCourante;
          for (iIndexLigne = 1; iIndexLigne < plateauDeJeu.length - 1; iIndexLigne++) {
            
            nbrJetonsTrouvesDansGroupeActuel = 1;
            valeurJetonPrecedent = plateauDeJeu[iIndexLigne][0];
            iIndexLigneCourante = iIndexLigne + 1;

            
            for (iIndexColonne = 1; (iIndexColonne < plateauDeJeu[iIndexLigne].length
                    && iIndexLigneCourante < plateauDeJeu.length); iIndexColonne++) {
                
                if (plateauDeJeu[iIndexLigneCourante][iIndexColonne] == valeurJetonPrecedent
                        && plateauDeJeu[iIndexLigneCourante][iIndexColonne] != 0) {
                    
                    nbrJetonsTrouvesDansGroupeActuel += 1;

                    
                    if (nbrDeJetonsPourGagner == nbrJetonsTrouvesDansGroupeActuel) {
                        return true; 
                    }
                }

               
                valeurJetonPrecedent = plateauDeJeu[iIndexLigneCourante][iIndexColonne];

              
                iIndexLigneCourante += 1;
            }

        
            nbrJetonsTrouvesDansGroupeActuel = 1;

           
            valeurJetonPrecedent = plateauDeJeu[iIndexLigne][plateauDeJeu[iIndexLigne].length - 1];

          
            iIndexLigneCourante = iIndexLigne + 1;

        
            for (iIndexColonne = plateauDeJeu[iIndexLigne].length - 2; (iIndexColonne >= 0
                    && iIndexLigneCourante < plateauDeJeu.length); iIndexColonne--) {
                
                if (plateauDeJeu[iIndexLigneCourante][iIndexColonne] == valeurJetonPrecedent
                        && plateauDeJeu[iIndexLigneCourante][iIndexColonne] != 0) {
                   
                    nbrJetonsTrouvesDansGroupeActuel += 1;

                    
                    if (nbrDeJetonsPourGagner == nbrJetonsTrouvesDansGroupeActuel) {
                        return true; 
                    }
                }
                valeurJetonPrecedent = plateauDeJeu[iIndexLigneCourante][iIndexColonne];
                iIndexLigneCourante += 1;
            }
        }
        return false;
    }
}
