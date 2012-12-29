package model.terrain;

/**
 * Classe abstraite représentant un type de terrain terrestre.
 */
public abstract class Terrestre extends Terrain {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7819570678717773225L;

	/**
	 * Instantiates a new terrestre.
	 *
	 * @param nom le nom du terrain
	 * @param coutDeplacement le coût du déplacement sur le terrain
	 * @param def la défense du terrain
	 * @param imageName le nom de l'image du terrain
	 */
	public Terrestre(String nom, int coutDeplacement, int def, String imageName) {
		super(nom, coutDeplacement, def, imageName);
	}

}
