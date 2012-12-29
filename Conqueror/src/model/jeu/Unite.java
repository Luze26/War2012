package model.jeu;

import java.awt.Image;
import java.io.Serializable;

import model.unite.TypeUnite;

/**
 * La classe unité représente une unité du point de vue modèle. Une unité appartient à un joueur et est d'un certain type. C'est un pion sur le damier.
 */
public class Unite implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7318301136104664297L;
	
	/** Le joueur maître de l'unité. */
	private final Joueur joueur;
	
	/** Le type de l'unite. */
	private final TypeUnite typeUnite;
	
	/** Les points de vie de l'unité. */
	private int pdv;
	
	/**
	 * Instantiates a new unite.
	 *
	 * @param j le joueur contrôlant l'unité
	 * @param t le type de l'unité
	 */
	public Unite(Joueur j, TypeUnite t) {
		joueur = j;
		typeUnite = t;
		pdv = typeUnite.getPdv();
		joueur.ajouterUnite(this);	//On ajoute l'unité au joueur
	}
	
	/**
	 * Gets le joueur contrôlant l'unité.
	 *
	 * @return le joueur contrôlant l'unité
	 */
	public Joueur getJoueur() {
		return joueur;
	}
	
	/**
	 * Gets le type de l'unité.
	 *
	 * @return le type de l'unité
	 */
	public TypeUnite getTypeUnite() {
		return typeUnite;
	}
	
	/**
	 * Gets the pdv.
	 *
	 * @return the pdv
	 */
	public int getPdv() {
		return pdv + joueur.getPays().getBonusVie();
	}
	
	/**
	 * Gets le nom de l'unité, étant celui du nom de son type plus celui de l'appartenance à son pays.
	 *
	 * @return le nom décrivant l'unité
	 */
	public String getNom() {
		return typeUnite.getNom() + joueur.getPays().getString();
	}

	/**
	 * Gets l'armure.
	 *
	 * @return l'armure
	 */
	public int getArmure() {
		return typeUnite.getArmure() + joueur.getPays().getBonusArmure();
	}
	
	/**
	 * Gets l'attaque.
	 *
	 * @return l'attaque
	 */
	public int getAttaque() {
		return typeUnite.getAttaque() + joueur.getPays().getBonusAttaque();
	}
	
	/**
	 * Gets le déplacement.
	 *
	 * @return le déplacement
	 */
	public int getDeplacement() {
		return typeUnite.getDeplacement() + joueur.getPays().getBonusDeplacement();
	}
	
	/**
	 * Gets la portée minimale.
	 *
	 * @return la portée minimale
	 */
	public int getPorteeMin() {
		return typeUnite.getPorteeMin();
	}
	
	/**
	 * Gets la portée maximale.
	 *
	 * @return la portée maximale
	 */
	public int getPorteeMax() {
		return typeUnite.getPorteeMax();
	}
	
	/**
	 * Gets l'image en buffer de l'unité.
	 *
	 * @return l'image de unité
	 */
	public Image getImageUnite() {
		return typeUnite.getImage(joueur.getNumero());
	}
	
	/**
	 * Gets le maximum de points de vie de l'unité (points de vie par défaut).
	 *
	 * @return le maximum de points de vie de l'unité (points de vie par défaut)
	 */
	public int getMaxPdv() {
		return typeUnite.getPdv() + joueur.getPays().getBonusVie();
	}

	/**
	 * Reduire les points de vie de l'unité des dégats indiqués.
	 *
	 * @param degats les dégats à infliger à l'unité
	 * */
	public void reduirePdv(int degats) {
		pdv -= degats;
		if(getPdv()<=0) {
			joueur.suppUnite(this);
		}
	}
	
	/**
	 * Attaque de l'unité sur la case donnée.
	 *
	 * @param caase1 la case que l'unité attaque
	 */
	public void attaque(Case caase1) {
		joueur.decrementPA();
		Unite unit = caase1.getUnite();
		
		int force = getAttaque() - caase1.getTerrain().getDefense() - unit.getArmure();
		if(force > 0) {	//Si l'unité est assez puissante pour infliger des dégâts, on les infliges
			unit.reduirePdv(force);
			if(caase1.getUnite().getPdv()<=0) {	//Si on a tué l'unité, on l'enlève de la case
				caase1.setUnite(null);
			}	
		}
	}
}
