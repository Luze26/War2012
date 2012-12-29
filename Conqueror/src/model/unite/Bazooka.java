package model.unite;

/**
 * Classe représentant le type d'unité Bazooka. Infanterie tirant de loin.
 */
public class Bazooka extends Infanterie {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3623771626351565514L;

	/**
	 * Instantiates a new bazooka.
	 *
	 * @param imageNames les images suivant le joueur de l'unité
	 */
	public Bazooka(String[] imageNames) {
		super("Bazooka", 120, 80, 20, 3, 2, 3, imageNames);
	}

}
