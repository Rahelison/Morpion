
		
		 
		public class morpion {
			/**
			 * Initialise l'application
			 * @param args
			 */
		    public static void main(String[] args) {
		    	
		    	 
		        int[][] plateauDeJeu;
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
		            
		         // Boucle tant que la partie n'est pas terminée.
		             do {
		                if (joueurCourant < 2) {
		                    joueurCourant++;
		                } else {
		                    joueurCourant = 1;
		                }
		                
		             // Boucle tant que le joueur courant n'a pas saisi de
		                // coordonnées valides.

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
		     * Affiche le plateau de jeu (=une matrice à deux dimensions), selon une
		     * représentation texte multiLigneIndexs.
		     * 
		     * @param plateauDeJeu
		     *            Le plateau de jeu à afficher.
		     * @return La représentation texte multiLignes du plateau de jeu.
		     */
		    public static String affiche(int[][] plateauDeJeu) {
		      
		        String plateauDeJeuEnTexte = "\n";
		        int iColonneIndex, iLigneIndex;
		        for (iLigneIndex = 0; iLigneIndex < plateauDeJeu.length; iLigneIndex++) {
		            for (iColonneIndex = 0; iColonneIndex < plateauDeJeu[iLigneIndex].length; iColonneIndex++) {

		                plateauDeJeuEnTexte += plateauDeJeu[iLigneIndex][iColonneIndex];
		            }
		            plateauDeJeuEnTexte += "\n";
		        }
		       
		        System.out.println(plateauDeJeuEnTexte);
		        return plateauDeJeuEnTexte;
		    }
		    
		    
		    /**
		     * Met à jour le plateau de jeu (une matrice à deux dimensions) en plaçant
		     * un jeton (= une valeur entière > 0) dans une de ses cases (éléments)
		     * selon des coordonnées comprises entre 1 et la taille de chaque dimension.
		     * Un contrôle préalable est effectué sur les coordonnées fournie et sur la
		     * valeur du jeton à placer.
		     * 
		     * @param plateauDeJeu
		     *            Le plateau de jeu à mettre à jour.
		     * @param numJoueur
		     *            Le numéro du joueur à affecter comme jeton dans la case du
		     *            plateau de jeu.
		     * @param numLigne
		     *            La ligne sur le plateau de jeu.
		     * @param numColonne
		     *            La colonne sur le plateau de jeu.
		     * @return Un booléen à vrai si la mise-à-jour est effectuée, ou à faux si
		     *         un contrôle préalables a révélé une erreurs dans l'appel de la
		     *         méthode.
		     */

		    public static boolean metAjour(int[][] plateauDeJeu, int numJoueur, int numLigne, int numColonne) {
		       
		        if (numLigne < 1 || numLigne > plateauDeJeu.length || numColonne < 1 || numColonne > plateauDeJeu[0].length) {
		            System.out.println("La saisie de la position de la case aux coordonnées x=" + numLigne + " et y= " + numColonne
		                    + " est incorrecte sur le plateau de jeu de taille [" + plateauDeJeu.length + ","
		                    + plateauDeJeu[plateauDeJeu.length - 1].length + "]");
		            return false;
		        }

		       
		        if (plateauDeJeu[numLigne - 1][numColonne - 1] != 0) {
		            Terminal.ecrireStringln("La position de la case aux coordonnées x=" + numLigne + "et y= " + numColonne
		                    + " est déjà affectée au joueur " + plateauDeJeu[numLigne - 1][numColonne - 1] + " ! \n");
		            return false;
		        }

		        // Joue la case (= affecte sa valeur au numéro du joueur).
		        plateauDeJeu[numLigne - 1][numColonne - 1] = numJoueur;
		        return true;
		    }
		    
		    
		    
		    
		    /**
		     * Contrôle si un joueur a gagné la partie, c’est-à-dire si le plateau de
		     * jeu contient un groupe de jetons identiques et tous contigüs à un autre
		     * du groupe.
		     * 
		     * @param plateauDeJeu
		     *            Le plateau de jeu dont l'état est à vérifier.
		     * @param nbrJetonsALigner
		     *            Le nombre de jetons à aligner.
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
		     * Cherche les groupes horizontaux de jetons identiques et tous contigüs à
		     * un autre du groupe sur la même ligne (=ayant un écart de colonne
		     * strictement égal à 1 par rapport à un des autres jetons identiques et
		     * contigüs sur cette ligne). S'arrête et retourne vrai lorsque le nombre de
		     * jetons trouvés dans un même groupe est égal à celui constituant une
		     * partie finie, sinon retourne faux.
		     * 
		     * @param plateauDeJeu
		     *            Le plateau de jeu (= la matrice de 2 dimensions)
		     * @param nbrDeJetonsPourGagner
		     *            Indique le nombre de jetons identiques et contigües à trouver
		     *            dans un même groupe pour finir la partie.
		     * @return Booléen à vrai si une partie est finie, sinon faux.
		     */
		    public static boolean chercheGroupesJetonsHorizontaux(int[][] plateauDeJeu, int nbrDeJetonsPourGagner) {   
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
		     * Cherche les groupes verticaux de jetons identiques et tous contigüs à un
		     * autre du groupe sur la même colonne (=ayant un écart de ligne strictement
		     * égal à 1 par rapport à un des autres jetons identiques et contigüs sur
		     * cette colonne). S'arrête et retourne vrai lorsque le nombre de jetons
		     * trouvés dans un même groupe est égal à celui constituant une partie
		     * finie, sinon retourne faux.
		     * 
		     * @param plateauDeJeu
		     *            Le plateau de jeu (=la matrice de 2 dimensions)
		     * @param nbrDeJetonsPourGagner
		     *            Indique le nombre de jetons identiques et contigües à trouver
		     *            dans un même groupe pour finir la partie.
		     * @return Booléen à vrai si une partie est finie, sinon faux.
		     */
		    public static boolean chercheGroupesJetonsVerticaux(int[][] plateauDeJeu, int nbrDeJetonsPourGagner) {
		    
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
		     * Cherche les groupes diagonaux de jetons identiques et tous contigüs à un
		     * autre du groupe sur la même diagonale (= ayant un écart de colonne et de
		     * ligne simultanément et strictement égal à 1 par rapport au jeton
		     * précédent et/ou suivant. S'arrête et retourne vrai lorsque le nombre de
		     * jetons trouvés dans un même groupe est égal à celui constituant une
		     * partie finie, sinon retourne faux.
		     * 
		     * @param plateauDeJeu
		     *            Le plateau de jeu (= la matrice de 2 dimensions)
		     * @param nbrDeJetonsPourGagner
		     *            Indique le nombre de jetons identiques et contigües à trouver
		     *            dans un même groupe pour finir la partie.
		     * @return Booléen à vrai si une partie est finie, sinon faux.
		     */
		    public static boolean chercheGroupesJetonsDiagonaux(int[][] plateauDeJeu, int nbrDeJetonsPourGagner) {
		       
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
		     * Cherche les groupes diagonaux de jetons identiques et tous contigüs à un
		     * autre du groupe sur la même diagonale (= ayant un écart de colonne et de
		     * ligne simultanément et strictement égal à 1 par rapport au jeton
		     * précédent et/ou suivant. Ici il s'agit d'itérer sur les diagonales
		     * descendantes situées au départ de la 1ère colonne uniquement et 2ème
		     * ligne jusqu'à l'avant-dernière ligne, puis celles situées au départ de la
		     * dernière colonne uniquement et 2ème ligne jusqu'à l'avant-dernière ligne
		     * ; cette méthode permet de compléter l'autre méthode cherchant les
		     * diagonales au départ de la 1ère ligne.
		     * 
		     * S'arrête et retourne vrai lorsque le nombre de jetons trouvés dans un
		     * même groupe est égal à celui constituant une partie finie, sinon retourne
		     * faux.
		     * 
		     * @param plateauDeJeu
		     *            Le plateau de jeu (= la matrice de 2 dimensions)
		     * @param nbrDeJetonsPourGagner
		     *            Indique le nombre de jetons identiques et contigües à trouver
		     *            dans un même groupe pour finir la partie.
		     * @return Booléen à vrai si une partie est finie, sinon faux.
		     */  
		  public static boolean chercheGroupesJetonsDiagonauxSpeciaux(int[][] plateauDeJeu, int nbrDeJetonsPourGagner) {
		   
		         
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
		
	


