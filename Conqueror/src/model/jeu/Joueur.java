package model.jeu;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un joueur du point de vue modèle.
 */
public class Joueur implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3519136560673617507L;
	
	/** Le numero du joueur. */
	private final int numero;
	
	/** La couleur du joueur. */
	private final Color color;
	
	/** Les unites du joueur. */
	private List<Unite> unites;
	
	/** Les points de mouvement du joueur. Nombre de fois qu'il peut déplacer des unités en un tour. */
	private int pM;
	
	/** Les points d'action du joueur. Nombre de fois qu'il peut attaquer des unités en un tour. */
	private int pA;
	
	/** Le pays du joueur. */
	private Pays pays;
	
	/**
	 * Instantiates a new joueur.
	 *
	 * @param num le numérodu joueur
	 * @param c la couleur du joueur
	 * @param pays2 le pays du joueur
	 */
	public Joueur(int num, Color c, Pays pays2) {
		numero = num;
		color = c;
		unites = new ArrayList<Unite>();
		pM = 5;
		pA = 3;
		pays = pays2;
	}
	
	/**
	 * Gets le numéro du joueur.
	 *
	 * @return le numéro du joueur
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Gets la couleur du joueur.
	 *
	 * @return la couleur du joueur
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Gets les unites du joueur.
	 *
	 * @return les unites du joueur
	 */
	public List<Unite> getUnites() {
		return unites;
	}
	
	/**
	 * Gets les points de mouvement du joueur.
	 *
	 * @return les points de mouvement du joueur
	 */
	public int getpM() {
		return pM;
	}
	
	/**
	 * Gets les points d'action du joueur.
	 *
	 * @return les points d'action du joueur
	 */
	public int getpA() {
		return pA;
	}
	
	/**
	 * Gets the pays.
	 *
	 * @return the pays
	 */
	public Pays getPays() {
		return pays;
	}
	
	/**
	 * Sets les points de mouvements du joueur.
	 *
	 * @param pM les points de mouvements du joueur
	 */
	private void setpM(int pM) {
		this.pM = pM;
	}

	/**
	 * Sets les points d'action du joueur.
	 *
	 * @param pA les points d'action du joueur
	 */
	private void setpA(int pA) {
		this.pA = pA;
	}

	/**
	 * Sets le pays.
	 *
	 * @param p le pays
	 */
	public void setPays(Pays p) {
		pays = p;
	}
	
	/**
	 * Ajouter unite.
	 *
	 * @param unite the unite
	 */
	public void ajouterUnite(Unite unite) {
		unites.add(unite);		
	}
	
	/**
	 * Supprime l'unité passé en paramètre des unités du joueur
	 *
	 * @param unite l'unité à enlever au joueur
	 */
	public void suppUnite(Unite unit) {
		unites.remove(unit);
	}

	/**
	 * Decremente les points de mouvement de 1.
	 */
	public void decrementPM() {
		pM -= 1;
	}
	
	/**
	 * Decremente les points d'action de 1.
	 */
	public void decrementPA() {
		pA -= 1;
	}
	
	/**
	 * Debut de tour. Remise des points de mouvements et actions au maximum.
	 */
	public void debutDeTour() {
		setpM(5);
		setpA(3);
	}
	
	/**
	 * Equals.
	 *
	 * @param j the le joueur à comparer
	 * @return true, si les deux joueurs sont les mêmes
	 */
	public boolean equals(Joueur j) {
		return j.getNumero() == numero;
	}
}
