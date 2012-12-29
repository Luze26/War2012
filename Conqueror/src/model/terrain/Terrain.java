package model.terrain;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;


/**
 * Classe abstraite représentant un terrain, c'est à dire le type d'une case.
 */
public abstract class Terrain implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3394983885592791928L;
	
	/** Le nom du terrain. */
	protected final String nom;
	
	/** Le coût du déplacement. */
	protected final int coutDeplacement;
	
	/** La défense procurant le terrain. */
	private final int defense;
	
	/** Le nom de l'image du terrain. */
	protected final String imageName;
	
	/** L'image en buffer. */
	protected transient BufferedImage  image;
	
	/**
	 * Instantiates a new terrain.
	 *
	 * @param nom1 le nom du terrain
	 * @param coutDeplacement1 le coût du déplacement sur le terrain
	 * @param def la défense procuré par le terrain
	 * @param imageName1 le nom de l'image du terrain
	 */
	public Terrain(String nom1, int coutDeplacement1, int def, String imageName1) {
		nom = nom1;
		coutDeplacement = coutDeplacement1;
		defense = def;
		imageName = imageName1;
		lireImage(); //Mise en buffer de l'image
	}
	
	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Gets the cout deplacement.
	 *
	 * @return the cout deplacement
	 */
	public int getCoutDeplacement() {
		return coutDeplacement;
	}

	/**
	 * Gets the defense.
	 *
	 * @return the defense
	 */
	public int getDefense() {
		return defense;
	}
	
	/**
	 * Gets l'image en buffer.
	 *
	 * @return l'image en buffer
	 */
	public BufferedImage getImage() {
		if(image == null) {	
			lireImage();
		}
		return image;
	}
	
	/**
	 * Lire image.
	 */
	private void lireImage() {
		image = outils.Outils.chargerImage("images/terrains/" + imageName, 40, 40, Color.GRAY, 1f);
	}
}
