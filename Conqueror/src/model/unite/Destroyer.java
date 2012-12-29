package model.unite;


/**
 * Classe représentant le type d'unité Destroyer. Unité navale au corps à corps.
 */
public class Destroyer extends Naval {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6711425931610193271L;

	/**
	 * Instantiates a new destroyer.
	 *
	 * @param imageNames1 les images suivant le joueur de l'unité
	 */
	public Destroyer(String[] imageNames1) {
		super("Destroyer", 150, 75, 40, 3, 1, 1, imageNames1);
	}

	
}
