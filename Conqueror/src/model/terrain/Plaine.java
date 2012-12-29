package model.terrain;

import java.io.Serializable;

/**
 * Classe représentant le type de terrain plaine.
 */
public class Plaine extends Terrestre implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7146583503306430341L;

	/**
	 * Instantiates a new plaine.
	 */
	public Plaine() {
		super("Plaine", 1, 0, "plaine.png");
	}

}
