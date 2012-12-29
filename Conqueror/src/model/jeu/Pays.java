package model.jeu;

import java.io.Serializable;

/**
 * La classe Pays représente un pays du point de vue modèle. Le pays donne des bonus/malus au joueur ayant sa nationnalité.
 */
public class Pays implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7480309335087242120L;
	
	/** Le nom du pays. */
	private final String nom;
	
	/** Le prefix à mettre devant le pays pour dire l'appartenance. */
	private final String prefix;
	
	/** Le bonus de vie. */
	private final int bonusVie;
	
	/** Le bonus d'attaque. */
	private final int bonusAttaque;
	
	/** Le bonus d'armure. */
	private final int bonusArmure;
	
	/** Le bonus de déplacement. */
	private final int bonusDeplacement;
	
	/**
	 * Instantiates a new pays.
	 *
	 * @param n le nom
	 * @param pref le préfixe
	 * @param bv le bonus de vie
	 * @param ba le bonus d'attaque
	 * @param bar le bonus d'armure
	 * @param bd le bonus de déplacement
	 */
	public Pays(String n, String pref, int bv, int ba, int bar, int bd) {
		nom = n;
		prefix = pref;
		bonusVie = bv;
		bonusAttaque = ba;
		bonusArmure = bar;
		bonusDeplacement = bd;
	}
	
	/**
	 * Gets le nom du pays.
	 *
	 * @return le nom du pays.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Gets le prefix.
	 *
	 * @return le prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * Gets le bonus de vie.
	 *
	 * @return le bonus de vie
	 */
	public int getBonusVie() {
		return bonusVie;
	}

	/**
	 * Gets le bonus d'attaque.
	 *
	 * @return le bonus d'attaque
	 */
	public int getBonusAttaque() {
		return bonusAttaque;
	}

	/**
	 * Gets le bonus d'armure.
	 *
	 * @return le bonus d'armure
	 */
	public int getBonusArmure() {
		return bonusArmure;
	}

	/**
	 * Gets le bonus de déplacement.
	 *
	 * @return le bonus de déplacement
	 */
	public int getBonusDeplacement() {
		return bonusDeplacement;
	}

	/**
	 * Gets la chaîne pour dire l'appartenance au pays.
	 *
	 * @return la chaîne pour dire l'appartenance au pays
	 */
	public String getString() {
		return prefix + nom;
	}
}
