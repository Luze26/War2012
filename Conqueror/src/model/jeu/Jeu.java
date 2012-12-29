package model.jeu;

import java.awt.Color;
import java.io.Serializable;

import model.strategie.Context;
import model.strategie.StrategieTousTues;
import model.terrain.TerrainBuilder;

/**
 * La classe jeu est la classe principale des données du jeu. Elle contient toutes les données d'une partie.
 */
public class Jeu implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8897576161325572763L;
	
	/** Les joueurs. */
	private Joueur[] joueurs;
	
	/** Le numéro du joueur actif. */
	private int joueurActif;
	
	/** La carte. */
	private final Carte carte; 
	
	/** Le context de victoire. */
	private final Context contextVictoire;
	
	/**
	 * Instantiates a new jeu.
	 *
	 * @param terrains les terrains disponibles
	 * @param l la largeur de la carte
	 * @param h la hauteur de la carte
	 */
	public Jeu(TerrainBuilder terrains,int l, int h) {
		initJoueur();
		carte = new Carte(terrains, l, h);
		contextVictoire = new Context(new StrategieTousTues("Combat à mort"));	//Stratégie par défaut
	}

	/**
	 * Initialise les joueurs
	 */
	private void initJoueur() {		
		//Création des joueurs
		
		//On récupère le premier pays, pays par défaut
		Pays pays = new PaysBuilder().getPays().get(0);
		
		joueurs = new Joueur[2];
		joueurs[0] = new Joueur(0, Color.RED, pays);
		joueurs[1] = new Joueur(1, Color.BLUE, pays);

		//Init du joueur actif
		joueurActif = 0;
	}
	
	/**
	 * Gets la carte.
	 *
	 * @return la carte
	 */
	public Carte getCarte() {
		return carte;
	}

	/**
	 * Gets le joueur de numéro passé en paramètre.
	 *
	 * @param numJoueur le numéro joueur à retourner
	 * @return le joueur
	 */
	public Joueur getJoueur(int numJoueur) {
		return joueurs[numJoueur];
	}

	/**
	 * Gets le joueur actif.
	 *
	 * @return le joueur actif
	 */
	public Joueur getJoueurActif() {
		return joueurs[joueurActif];
	}

	/**
	 * Gets le numéro du joueur actif.
	 *
	 * @return le numéro joueur actif
	 */
	public int getNumJoueurActif() {
		return joueurActif;
	}
	
	/**
	 * Fin de tour. Changement du joueur actif.
	 */
	public void finDeTour() {
		joueurActif = 1-joueurActif;
		joueurs[joueurActif].debutDeTour();
	}
	
	/**
	 * Gets le context de victoire.
	 *
	 * @return le context de victoire
	 */
	public Context getContextVictoire() {
		return contextVictoire;
	}
}
