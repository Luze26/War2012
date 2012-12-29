package model.terrain;

import java.io.Serializable;

/**
 * Classe abstraite représentant un type de terrain maritimes.
 */
public abstract class Maritime extends Terrain implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -994990020169663318L;

	/**
	 * Instantiates a new maritime.
	 *
	 * @param nom le nom du terrain
	 * @param coutDeplacement le coût pour un déplacement sur ce terrain
	 * @param def la défense procurant le terrain
	 * @param imageName le nom de l'image du terrain
	 */
	public Maritime(String nom, int coutDeplacement, int def, String imageName) {
		super(nom, coutDeplacement, def, imageName);
	}

}
