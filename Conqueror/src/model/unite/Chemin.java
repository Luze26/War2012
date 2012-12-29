package model.unite;

import java.util.List;

import model.jeu.Case;

/**
 * Classe représentant un chemin, qu'une unité peut emprunter.
 */
public class Chemin {

	/** La case d'arrivée du chemin. */
	private final Case caseArrivee;
	
	/** La liste des cases constituants le chemin. */
	private final List<Case> cases;
	
	/** Le coût de déplacement sur le chemin. */
	private final int cout;
	
	/**
	 * Instantiates a new chemin.
	 *
	 * @param c la case d'arrivée
	 * @param cs la liste des cases du chemin
	 * @param co le coût de déplacement sur le chemin
	 */
	public Chemin(Case c, List<Case> cs, int co) {
		caseArrivee = c;
		cases = cs;
		cout = co;
	}

	/**
	 * Gets le coût de déplacement du chemin.
	 *
	 * @return le coût de déplacement du chemin
	 */
	public int getCout() {
		return cout;
	}

	/**
	 * Gets la case d'arrivée.
	 *
	 * @return la case d'arrivée
	 */
	public Case getCaseArrivee() {
		return caseArrivee;
	}

	/**
	 * Gets les cases du chemins.
	 *
	 * @return les cases du chemin
	 */
	public List<Case> getCases() {
		return cases;
	}
}
