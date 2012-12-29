package model.unite;

/**
 * Classe représentant le type d'unité de l'unité de base, qui es tune infanterie tirant au corps à corps.
 */
public class Soldat extends Infanterie {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2760133693981160516L;
	
	/**
	 * Instantiates a new soldat.
	 *
	 * @param imageNames les images suivant le joueur de l'unité
	 */
	public Soldat(String[] imageNames) {
		super("Soldat", 100, 50, 30, 3, 1, 1, imageNames);
	}

	

}
