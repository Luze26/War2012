package model.strategie;

import java.io.Serializable;
import java.util.List;

import model.jeu.Jeu;
import model.jeu.Unite;

/**
 * Représente la stratégie Tuer Roi, donnant la victoire si le joueur adverse n'a plus de roi
 */
public class StrategieTuerRoi implements StrategieVictoire, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7571116906587260348L;

	/** Le nom de la stratégie. */
	private final String nom;
	
	/**
	 * Instantiates a new strategie tuer roi.
	 *
	 * @param nom1 lenom de la stratégie
	 */
	public StrategieTuerRoi(String nom1) {
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
		List<Unite> unites = jeu.getJoueur(1-jeu.getNumJoueurActif()).getUnites();
		boolean gagner = true;
		
		//On parcourt toutes les unités du joueur adverse, si il a plus de roi, le joueur à gagné.
		for(Unite u : unites) {
			if(u.getTypeUnite().getNom().equals("Roi")) {
				gagner = false;
				break;
			}
		}
		
		return gagner;
	}
}
