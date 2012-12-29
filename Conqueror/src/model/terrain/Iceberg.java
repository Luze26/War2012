package model.terrain;

/**
 * Classe représentant le type de terrain iceberg.
 */
public class Iceberg extends Maritime {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 30001486429965852L;

	/**
	 * Instantiates a new iceberg.
	 */
	public Iceberg() {
		super("Iceberg", 2, 3, "iceberg.png");
	}
}
