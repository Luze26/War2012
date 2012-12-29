package model.strategie;

import model.jeu.Jeu;

/**
 * The Interface StrategieVictoire. Interface que doivent implémenter les stratégies de victoire du jeu.
 */
public interface StrategieVictoire {

	/**
	 * Gets le nom de la stratégie.
	 *
	 * @return le nom de la stratégie
	 */
	public abstract String getNom();
	
	/**
	 * A gagne. Regarde si le joueur actif à gagné sur le jeu donnée en paramètre
	 *
	 * @param jeu le jeu
	 * @return true, si le joueur à gagner la aprtie
	 */
	public abstract boolean aGagne(Jeu jeu);
}
