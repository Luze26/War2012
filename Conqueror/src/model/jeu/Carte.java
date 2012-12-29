package model.jeu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.terrain.TerrainBuilder;


/**
 * Classe représentant la carte du point de vue modèle. La carte étant le damier constituant l'aire de jeu.
 */
public class Carte implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8562796862209054174L;
	
	/** la largeur. */
	private final int largeur;
	
	/** la hauteur. */
	private final int hauteur;
	
	/** les cases composants la carte */
	private List<Case> cases ;

	/**
	 * Instantiates a new carte.
	 *
	 * @param terrains les types de terrains disponibles
	 * @param l la largeur
	 * @param h la hauteur
	 */
	public Carte(TerrainBuilder terrains, int l, int h) {
		int x, y;
		
		cases = new ArrayList<Case>();
		largeur = l;
		hauteur = h;
		
		//On créé les cases de la carte avec le premier type de terrain comme terrain par défaut
		for(y=0; y<hauteur; y++) {
			for(x=0; x<largeur; x++) {
				Case caase = new Case(x, y, terrains.getTerrains().get(0));
				cases.add(caase);
			}
		}
	}

	/**
	 * Gets la case.
	 *
	 * @param x l'abscisse
	 * @param y l'ordonnée
	 * @return la case
	 */
	public Case getCase(int x, int y) {
		if(x<largeur && x>=0 && y<hauteur && y>=0) {
			return cases.get(x + y*largeur);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Gets la largeur.
	 *
	 * @return la largeur
	 */
	public int getLargeur() {
		return largeur;
	}

	/**
	 * Gets la hauteur.
	 *
	 * @return la hauteur
	 */
	public int getHauteur() {
		return hauteur;
	}
}
