package model.unite;

/**
 * Classe représentant le type d'unité Roi. Unité terrestre, pouvant être la cible pour gagner une partie dans la stratégie TUER ROi.
 * */
public class Roi extends Infanterie{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2760133693981160516L;
	
	/**
	 * Instantiates a new roi.
	 *
	 * @param imageNames les images suivant le joueur de l'unité
	 */
	public Roi(String[] imageNames) {
		super("Roi", 300, 55, 30, 3, 1, 1, imageNames);
	}	
}
