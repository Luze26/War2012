package model.jeu;

import java.io.Serializable;

import model.terrain.Terrain;

/**
 * Classe représentant une case du point de vue modèle faisant partie de la carte.
 */
public class Case implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 380542579350615615L;
	
	/** L'abscisse. */
	private final int x;
	
	/** L'oronnée. */
	private final int y;	
	
	/** L'unité occupant la case, si il y en a une. */
	private Unite unite;
	
	/** Le type du terrain de la case. */
	private Terrain terrain;

	/**
	 * Instantiates a new case.
	 *
	 * @param x1 l'abscisse
	 * @param y1 l'ordonnée
	 * @param terr le type de terrain
	 */
	public Case(int x1, int y1, Terrain terr) {
		x = x1;
		y = y1;
		unite = null;
		terrain = terr;
	}

	/**
	 * Gets l'abscisse.
	 *
	 * @return l'abscisse
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets l'ordonnée.
	 *
	 * @return l'ordonnée
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gets l'unite.
	 *
	 * @return l'unite
	 */
	public Unite getUnite() {
		return unite;
	}
	
	/**
	 * Gets le terrain.
	 *
	 * @return le terrain
	 */
	public Terrain getTerrain() {
		return terrain;
	}
	
	/**
	 * Sets l'unite.
	 *
	 * @param unite1 l'unite à mettre sur la case
	 */
	public void setUnite(Unite unite1) {
		if(unite1 == null || unite1.getTypeUnite().isDeplacable(terrain)) {		//Si la case est libre et que l'unité peut y aller dessus, on l'ajoute
			unite = unite1;
		}
	}
	
	/**
	 * Sets le terrain.
	 *
	 * @param terr le terrain
	 */
	public void setTerrain(Terrain terr) {
		if(unite!=null && !unite.getTypeUnite().isDeplacable(terr)) {	//Si une unité est sur la case, on regarde si elle est toujours compatible
			unite = null;
		}
		terrain = terr;
	}
	
	/**
	 * Checks si la case est occupée pas une unité
	 *
	 * @return true, si la case est occupé par une unité
	 */
	public boolean isOccupe() {
		return unite!=null;
	}
	
	/**
	 * Deplacer 'unite.
	 *
	 * @param caase1 la case de destination de l'unité
	 */
	public void deplacerUnite(Case caase1) {
		caase1.setUnite(unite);
		unite.getJoueur().decrementPM();
		unite = null;
	}
	
	/**
	 * Equals.
	 *
	 * @param caase case à comparer
	 * @return true, si les deux cases sont les mêmes
	 */
	public boolean equals(Case caase) {
		return x==caase.getX() && y==caase.getY();
	}
}
