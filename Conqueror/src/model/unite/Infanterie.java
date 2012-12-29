package model.unite;

import model.terrain.Terrain;
import model.terrain.Terrestre;

/**
 * Classe abstraite représentant un type d'unité se déplçant à pied sur le sol.
 */
public abstract class Infanterie extends TypeUnite {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1524078923977717526L;
	
	/**
	 * Instantiates a new infanterie.
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
	public Infanterie(String n, int p, int at, int ar, int d, int pMin,
			int pMax, String[] imageNames1) {
		super(n, p, at, ar, d, pMin, pMax, imageNames1);
	}
	
	/* (non-Javadoc)
	 * @see model.unite.TypeUnite#isDeplacable(model.terrain.Terrain)
	 */
	@Override
	public boolean isDeplacable(Terrain terr) {
		if(terr instanceof Terrestre) {
			return true;
		}
		else {
			return false;
		}
	}
}
