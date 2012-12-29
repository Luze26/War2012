package model.unite;

/**
 * Classe représentant le type d'unité Helico. Unité aérienne.
 */
public class Helico extends Aerienne {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3239253093851445407L;

	/**
	 * Instantiates a new helico.
	 *
	 * @param imageNames1 les images suivant le joueur de l'unité
	 */
	public Helico(String[] imageNames1) {
		super("Hélicoptère", 150, 60, 10, 4, 1, 1, imageNames1);
	}
}
