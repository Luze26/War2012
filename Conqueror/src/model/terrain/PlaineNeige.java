package model.terrain;

/**
 * Classe représentant le type de terrain plaine enneigée.
 */
public class PlaineNeige extends Terrestre {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2679691207680062426L;

	/**
	 * Instantiates a new plaine neige.
	 */
	public PlaineNeige() {
		super("Plaine enneigée", 2, 0, "neige.png");
	}

}
