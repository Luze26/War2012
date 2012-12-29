package model.unite;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.jeu.Carte;
import model.jeu.Case;
import model.terrain.Terrain;

/**
 * Classe abstraite représentant un type d'unité.
 */
public abstract class TypeUnite implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1935661916424252574L;
	
	/** Le nom de du type d'unité. */
	private String nom;
	
	/** Les points de vie du type. */
	private int pdv;
	
	/** L'attaque. */
	private int attaque;
	
	/** L'armure. */
	private int armure;
	
	/** Le déplacement. */
	protected int deplacement;
	
	/** La portée min. */
	private int porteeMin;
	
	/** La portée max. */
	private int porteeMax;
	
	/** Les images de l'unité suivant le numéro de joueur. */
	private String imageNames[];
	
	/** Les images en buffer. */
	private transient BufferedImage images[];
	
	/**
	 * Instantiates a new type unite.
	 *
	 * @param n le nom du type d'unité
	 * @param p les points de vies du type
	 * @param at l'attaque
	 * @param ar l'armure
	 * @param d le déplacement
	 * @param pMin la portée minimale
	 * @param pMax la portée maximale
	 * @param imageNames1 les images suivant le joueur de l'unité
	 */
	public TypeUnite(String n, int p, int at, int ar, int d, int pMin, int pMax, String[] imageNames1) {
		nom = n;
		pdv = p;
		armure =
		attaque = at;
		armure = ar;
		deplacement = d;
		porteeMin = pMin;
		porteeMax = pMax;
		imageNames = imageNames1;
		lireImages();
	}
	
	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Gets the pdv.
	 *
	 * @return the pdv
	 */
	public int getPdv() {
		return pdv;
	}

	/**
	 * Gets the armure.
	 *
	 * @return the armure
	 */
	public int getArmure() {
		return armure;
	}
	
	/**
	 * Gets the attaque.
	 *
	 * @return the attaque
	 */
	public int getAttaque() {
		return attaque;
	}
	
	/**
	 * Gets the deplacement.
	 *
	 * @return the deplacement
	 */
	public int getDeplacement() {
		return deplacement;
	}
	
	/**
	 * Gets the portee min.
	 *
	 * @return the portee min
	 */
	public int getPorteeMin() {
		return porteeMin;
	}
	
	/**
	 * Gets the portee max.
	 *
	 * @return the portee max
	 */
	public int getPorteeMax() {
		return porteeMax;
	}
	
	/**
	 * Gets l'image de l'unité.
	 *
	 * @param index le numéro du joueur
	 * @return l'image de l'unité pour le joueur
	 */
	public BufferedImage getImage(int index) {
		if (images == null) {
			lireImages();
		}
		return images[index];
	}
	
	/**
	 * Lire images. Met les images en buffer
	 */
	private void lireImages() {
		images = new BufferedImage[2];
		
		images[0] = outils.Outils.chargerImage("images/unites/" + imageNames[0], 40, 40, Color.RED, 0.5f);
		images[1] = outils.Outils.chargerImage("images/unites/" + imageNames[1], 40, 40, Color.BLUE, 0.5f);

	}

	/**
	 * Checks si l'unité peut se déplacer sur le terrain donnée.
	 *
	 * @param terr le terrain
	 * @return true, si l'unité peut se déplacer sur le terrain
	 */
	public abstract boolean isDeplacable(Terrain terr);
	
	/**
	 * Gets les cases attaquables par l'unité de la case.
	 *
	 * @param carte la carte
	 * @param caase la case où se trouve l'unité voulant attaquer
	 * @param joueurAdverse lenuméro du joueur adverse
	 * @return les cases attaquables par l'unité
	 */
	public List<Case> getCasesAttaquables(Carte carte, Case caase, int joueurAdverse) {
		List<Case> cases = new ArrayList<Case>(); //Liste des cases que l'unité peut attaquer
		int i, j, xi, yj,
		x = caase.getX(),
		y = caase.getY();
		int pMax = caase.getUnite().getPorteeMax();
		int pMin = caase.getUnite().getPorteeMin();
		
		//On parcourt la zone délimitée par la portée minimum et maximum
		for(i=-pMax; i<=pMax; i++) {
			for(j=-pMax+Math.abs(i); j<=pMax-Math.abs(i); j++) {
				if(Math.abs(i)+Math.abs(j)>=pMin) {
					xi = x+i;
					yj = y+j;
					
					//Si la case est dans la carte
					if(xi>=0 && xi<carte.getLargeur() && yj>=0 && yj<carte.getHauteur()) {
						Case c = carte.getCase(xi, yj);
						
						//Si une unité du joueur adverse se trouve sur la case on l'ajoute au cases attaquables
						if(c.isOccupe() && c.getUnite().getJoueur().getNumero()==joueurAdverse) {
							cases.add(c);
						}
					}
				}				
			}
		}
		return cases;
	}
	
	/**
	 * Gets les cases accessibles.
	 *
	 * @param carte la carte
	 * @param caase la case de départ
	 * @return les chemins les plus court que peut emprunter l'unité de la case
	 */
	public List<Chemin> getCasesAccessibles(Carte carte, Case caase) {
		List<Chemin> chemins = new ArrayList<Chemin>(); //Liste des chemins que l'unité peut suivre
		List<Chemin> aTraiter = new ArrayList<Chemin>();	//Liste des chemins dont on a pas encore atteint le bout
		int x, y, i, j, xi, yj, cout, depl, pm = caase.getUnite().getDeplacement();
		
		//On met un chemin contenant la case de départ dans aTraiter
		List<Case> cases = new ArrayList<Case>();
		cases.add(caase);
		Chemin chemin = new Chemin(caase, cases, 0);
		aTraiter.add(chemin);
		
		if(pm != 0) {	//Si l'unité peut se déplacer
			while(!aTraiter.isEmpty()) {	//Tant qu'on a pas fini de parcourir tous les chemins
				chemin = aTraiter.get(0);	//On récupère le prochain chemin à traiter
				depl = pm - chemin.getCout();	//On récupère les points de mouvements restants à utiliser
				x = chemin.getCaseArrivee().getX();
				y = chemin.getCaseArrivee().getY();
				
				//On parcourt les cases voisines de la dernière case du chemin
				for(i=-1; i<2; i++) {
					for(j=-1+Math.abs(i); j<=1-Math.abs(i); j++) {
						
						if(i!=j) { //Si ce n'est pas une case d'une diagonale
							xi = x+i;
							yj = y+j;
							cout = caseAccessible(carte, xi, yj, depl);	//Si l'unité peut aller sur la case voisine
							
							if(cout>=0 && !dejaTraite(chemins, xi, yj, chemin.getCout()+cout)) { //Si l'unité à assez de pm pour y aller et qu'on a pas de chemin plus court pour y aller
								
								//On créé un nouveau chemin en ajoutant la case voisine
								Case caseArrivee = carte.getCase(xi, yj);
								cases = new ArrayList<Case>();
								cases.addAll(chemin.getCases());
								cases.add(caseArrivee);
								Chemin ch = new Chemin(caseArrivee, cases, chemin.getCout()+cout);
								
								//Si l'unité peut encore se déplacer on le met dans les chemins à traiter sinon directement dans les chemins
								if(depl-cout>0) {
									aTraiter.add(ch);
								}
								else {
									chemins.add(ch);
								}
							}
							
						}
					}
				}
				
				//On met le chemin traité dans la liste des chemins sauf si c'est la case de départ, et on le supprime de aTraiter
				if(chemin.getCout()!=0) {
					chemins.add(chemin);
				}
				aTraiter.remove(0);
			}
		}
		
		
		return chemins;
	}
	
	/**
	 * Deja traite. Regarde si on a déjà trouvé un chemin plus court.
	 *
	 * @param chemins les chemins déjà trouvés
	 * @param x l'abscisse de la case d'arrivée
	 * @param y l'ordonnée de la case d'arrivée
	 * @param cout le coût pour le nouveau chemin
	 * @return true, si on a pas de chemin plus court pour la case de coordonnées donnée
	 */
	protected boolean dejaTraite(List<Chemin> chemins, int x, int y, int cout) {
		int i;
		
		//On parcourt tous les chemins
		for(i=0; i<chemins.size(); i++) {
			Chemin ch = chemins.get(i);
			
			//Si la case d'arrivée est la même, un chemin vers cette case à déjà été traité, on regarde ensuite le coût
			if(ch.getCaseArrivee().getX() == x && ch.getCaseArrivee().getY() == y) {
				//Si l'ancien chemin est plus long on le supprime et on indique qu'il faut ajouter le nouveau chemin, sinon on dit qu'on a déjà le chemin
				if(ch.getCout()>cout) {
					chemins.remove(i);
					return false;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Renvoi le coût pour aller sur la case donnée, -1 si elle est pas accesible
	 *
	 * @param carte la carte
	 * @param xi l'abscisse de la case à atteindre
	 * @param yj l'ordonnée de la case à atteindre
	 * @return le coût pour atteindre la case, -1 si innaccessible
	 */
	private int caseAccessible(Carte carte, int xi, int yj, int depl) {
		
		//On regarde si la case voisine fait partie de la carte
		if (xi>=0 && xi<carte.getLargeur() && yj >=0 && yj<carte.getHauteur()) {
			Case c = carte.getCase(xi, yj);
			
			//Si la carte est libre, que l'unité peut s'y déplacer dessus et que l'unité à encore assez de pm, on retourne le coût du déplacement
			if(!c.isOccupe() && isDeplacable(c.getTerrain()) && c.getTerrain().getCoutDeplacement() <= depl) {
				return c.getTerrain().getCoutDeplacement();
			}
		}
		return -1;
	}
}
