package model.unite;

/**
 * Classe représentant le type d'unité LanceMissile. Unité motorisée tirant de loin.
 */
public class LanceMissile extends Motorise {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6111702598296907713L;

	/**
	 * Instantiates a new lance missile.
	 *
	 * @param imageNames1 les images suivant le joueur de l'unité
	 */
	public LanceMissile(String[] imageNames1) {
		super("Lance Missile", 150, 80, 40, 4, 2, 3, imageNames1);
	}

}
