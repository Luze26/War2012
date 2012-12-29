package model.unite;

import java.util.ArrayList;
import java.util.List;

import model.jeu.Carte;
import model.jeu.Case;
import model.terrain.Terrain;

/**
 * Classe abstraite représentant un type d'unité volant, n'étant pas affecté par les coûts de déplacement sur les terrains.
 */
public abstract class Aerienne extends TypeUnite {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2527419248172977424L;
	
	/**
	 * Instantiates a new aerienne.
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
	public Aerienne(String n, int p, int at, int ar, int d, int pMin, int pMax,
			String[] imageNames1) {
		super(n, p, at, ar, d, pMin, pMax, imageNames1);
	}

	/* (non-Javadoc)
	 * @see model.unite.TypeUnite#isDeplacable(model.terrain.Terrain)
	 */
	@Override
	public boolean isDeplacable(Terrain terr) {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see model.unite.TypeUnite#getCasesAccessibles(model.jeu.Carte, model.jeu.Case)
	 */
	@Override
	public List<Chemin> getCasesAccessibles(Carte carte, Case caase) {
		List<Chemin> chemins = new ArrayList<Chemin>(); //Liste des chemins que l'unité peut suivre
		List<Chemin> aTraiter = new ArrayList<Chemin>();	//Liste des chemins dont on a pas encore atteint le bout
		int x, y, i, j, xi, yj, depl;
		
		//On met un chemin contenant la case de départ dans aTraiter
		List<Case> cases = new ArrayList<Case>();
		cases.add(caase);
		Chemin chemin = new Chemin(caase, cases, 0);
		aTraiter.add(chemin);
		
		if(deplacement != 0) {	//Si l'unité peut se déplacer
			while(!aTraiter.isEmpty()) {	//Tant qu'on a pas fini de parcourir tous les chemins
				chemin = aTraiter.get(0);	//On récupère le prochain chemin à traiter
				depl = deplacement - chemin.getCout();	//On récupère les points de mouvements restants à utiliser
				x = chemin.getCaseArrivee().getX();
				y = chemin.getCaseArrivee().getY();
				
				//On parcourt les cases voisines de la dernière case du chemin
				for(i=-1; i<2; i++) {
					for(j=-1+Math.abs(i); j<=1-Math.abs(i); j++) {
						
						if(i!=j) { //Si ce n'est pas une case d'une diagonale
							xi = x+i;
							yj = y+j;
							
							if(caseAccessible(carte, xi, yj) && !dejaTraite(chemins, xi, yj, chemin.getCout()+1)) { 
								//Si l'unité peut se déplacer sur la case parcouru, et qu'on a pas de plus court chemin pour l'atteindre on la triatre
								
								//On créé un nouveau chemin en ajoutant la case voisine
								Case caseArrivee = carte.getCase(xi, yj);
								cases = new ArrayList<Case>();
								cases.addAll(chemin.getCases());
								cases.add(caseArrivee);
								Chemin ch = new Chemin(caseArrivee, cases, chemin.getCout()+1);
								
								//Si l'unité peut encore se déplacer on le met dans les chemins à traiter sinon directement dans les chemins
								if(depl-1>0) {
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
	 * Case accessible.
	 *
	 * @param carte the carte
	 * @param xi the xi
	 * @param yj the yj
	 * @return true, if successful
	 */
	private boolean caseAccessible(Carte carte, int xi, int yj) {
		
		//On regarde si la case voisine fait partie de la carte
		if (xi>=0 && xi<carte.getLargeur() && yj >=0 && yj<carte.getHauteur()) {
			Case c = carte.getCase(xi, yj);
			
			//Si la carte est libre, que l'unité peut s'y déplacer dessus, on revnoi vrai
			if(!c.isOccupe() && isDeplacable(c.getTerrain())) {
				return true;
			}
		}
		
		//La case est soit en dehors de la map, soit occupée => l'unité ne peut pas s'y déplacer
		return false;
	}
}
