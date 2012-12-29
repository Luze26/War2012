package presenter;

import model.jeu.Case;

/**
 * Interface moteur, définissant les fonctions communes à la partie jeu et à l'éditeur. Permettant notament à la vue de communiquer
 * les actions de l'utilisateur sur la carte, mais également les actions de sauvegarde, chargement et de fermeture.
 */
public interface Moteur {
		
	/**
	 * Clique sur une case de la carte.
	 *
	 * @param caase1 la cae cliquée
	 */
	public void caseClique(Case caase1);
	
	/**
	 * Pression sur une case de la carte.
	 *
	 * @param caase1 la case pressée.
	 */
	public void casePressee(Case caase1);
	
	/**
	 * Relache de la case de la carte.
	 *
	 * @param caase1 la case relachée
	 */
	public void caseRelachee(Case caase1);
	
	/**
	 * Indique que le curseur entre dans une case
	 *
	 * @param caase1 la case entrée
	 */
	public void caseEntree(Case caase1);
	
	/**
	 * Indique que le curseur sors d'une case
	 *
	 * @param caase1 la case quitée
	 */
	public void caseSortie(Case caase1);

	/**
	 * Charger.
	 *
	 * @param nom le nom du fichier à charger
	 */
	public void charger(String nom);
	
	/**
	 * Sauvegarder.
	 */
	public void sauvegarder();
	
	/**
	 * Sauvegarder sous.
	 *
	 * @param nom le nom du fichier où sauvegarder
	 */
	public void sauvegarderSous(String nom);
	
	/**
	 * Gets le nom de la sauvegarde en cours
	 *
	 * @return le nom de la sauvegarde
	 */
	public String getNomSauvegarde();
	
	/**
	 * Retour au menu.
	 */
	public void retourMenu();
	
	/**
	 * Quitter.
	 */
	public void quitter();
}
