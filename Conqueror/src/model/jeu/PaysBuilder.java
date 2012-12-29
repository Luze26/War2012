package model.jeu;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant la construction des différents pays disponibles
 */
public class PaysBuilder {

	/** La lise des pays du jeu. */
	private final List<Pays> pays;
	
	/**
	 * Instantiates a new pays builder. Créé la liste des pays du jeu.
	 */
	public PaysBuilder() {
		pays = new ArrayList<Pays>();
		
		//Ajout des pays
		pays.add(new Pays("USA", " des ", 10, 10, 5, 0));
		pays.add(new Pays("URSS", " de l'", 30, 0, -5, 0));
		pays.add(new Pays("Japon", " du ", 0, 5, 0, 2));
		pays.add(new Pays("France", " de ", 0, 8, 8, 0));
	}
	
	/**
	 * Gets la liste des pays.
	 *
	 * @return la liste des pays
	 */
	public List<Pays> getPays() {
		return pays;
	}
}
