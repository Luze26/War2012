package model.unite;

import java.util.ArrayList;
import java.util.List;

import model.jeu.Carte;
import model.jeu.Case;

/**
 * Classe représentant le type d'unité Tank, qui est un har motorisé se déplaçant qu'en ligne droite.
 */
public class Tank extends Motorise {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5567198946410604682L;

	/**
	 * Instantiates a new tank.
	 *
	 * @param imageNames les images suivant le joueur de l'unité
	 */
	public Tank(String[] imageNames) {
		super("Tank", 200, 70, 50, 4, 1, 1, imageNames);
	}
	
	/* (non-Javadoc)
	 * @see model.unite.TypeUnite#getCasesAccessibles(model.jeu.Carte, model.jeu.Case)
	 */
	public List<Chemin> getCasesAccessibles(Carte carte, Case caase) {
		List<Chemin> chemins = new ArrayList<Chemin>();	//Les chemins accesibles par l'unités
		List<List<Case>> cases = new ArrayList<List<Case>>();	//La liste des cases servant à construire les chemins
		int i, j,
		x = caase.getX(),
		y = caase.getY();
		int[] pm = new int[4]; //Les pm restant à utiliser pour chaque direction
		
		//Initialisation de la liste des cases pour les 4 directions
		for(i=0; i<4; i++) {
			cases.add(new ArrayList<Case>());
			cases.get(i).add(caase);
			pm[i] = deplacement;
		}
		
		for(i=1; i<deplacement; i++) { //On se déplace de 1 de plus
			for(j=0; j<4; j++) {	//On regarde dans les quatres directions
				Case c;
				switch(j) {
					case 0:
						c = carte.getCase(x+i, y);
						break;
					case 1:
						c = carte.getCase(x-i, y);
						break;
					case 2:
						c = carte.getCase(x, y+i);
						break;
					default:
						c = carte.getCase(x, y-i);
				}
				
				if(c!=null) {
					pm[j] -= c.getTerrain().getCoutDeplacement();
					if(!c.isOccupe() && isDeplacable(c.getTerrain()) && pm[j]>=0) {
						cases.get(j).add(c);
						List<Case> cs = new ArrayList<Case>();
						cs.addAll(cases.get(j));
						chemins.add(new Chemin(c, cs, c.getTerrain().getCoutDeplacement()));
					}
				}
			}
			
		}
		return chemins;
	}
}
