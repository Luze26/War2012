package model.strategie;

import java.io.Serializable;

import model.jeu.Jeu;

/**
 * Représente la stratégie correspondant à éliminer toutes les unités énemies pour gagner.
 */
public class StrategieTousTues implements StrategieVictoire, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -564000695865736546L;

	/** Le nom de la stratégie. */
	private final String nom;
	
	/**
	 * Instantiates a new strategie tous tues.
	 *
	 * @param nom1 le nom de la stratégie
	 */
	public StrategieTousTues(String nom1) {
		nom = nom1;
	}
	
	/* (non-Javadoc)
	 * @see model.strategie.StrategieVictoire#getNom()
	 */
	@Override
	public String getNom() {
		return nom;
	}
	
	/* (non-Javadoc)
	 * @see model.strategie.StrategieVictoire#aGagne(model.jeu.Jeu)
	 */
	@Override
	public boolean aGagne(Jeu jeu) {
		return jeu.getJoueur(1-jeu.getNumJoueurActif()).getUnites().isEmpty();
	}
}
