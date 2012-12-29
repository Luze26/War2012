package model.strategie;

import java.io.Serializable;

import model.jeu.Jeu;

/**
 * Classe représentant le contexte de victoire, contenant la stratégie à exéctuer pour savoir si le joueur est victorieux.
 */
public class Context implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7777763107701525809L;
	
	/** La stratégie de victoire en cours. */
	private StrategieVictoire strategie;
	
	/**
	 * Instantiates a new context.
	 *
	 * @param strat la stratégie de victoire
	 */
	public Context(StrategieVictoire strat) {
		strategie = strat;
	}
	
	/**
	 * Gets le nom de la stratégie.
	 *
	 * @return le nom de la stratégie
	 */
	public String getNomStrategie() {
		return strategie.getNom();
	}
	
	/**
	 * A gagner.
	 *
	 * @param jeu le jeu sur lequel exécuter la stratégie de victoire
	 * @return true, si le joueur actif à gagner
	 */
	public boolean aGagner(Jeu jeu) {
		return strategie.aGagne(jeu);
	}
	
	/**
	 * Sets la stratégie de victoire.
	 *
	 * @param strategie2 la nouvelle stratégie de victoire
	 */
	public void setStrategieVictoire(StrategieVictoire strategie2) {
		strategie = strategie2;		
	}
}
