package presenter;

import view.LauncherView;

/**
 * Classe exécutable, permettant de lancer le jeu et de naviguer entre les différentes parties du jeu (menu, éditeur, jeu).
 */
public class Launcher {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String [ ] args) {
		menu();
	}
	
	/**
	 * Lance l'éditeur.
	 */
	public static void editeur() {
		new Editeur();
	}

	/**
	 * Lance une partie.
	 *
	 * @param nom le nom du jeu à charger
	 * @param sauvegarde vrai, si le jeu est une partie déjà en cours, faux si c'est une carte de base
	 */
	public static void jouer(String nom, boolean sauvegarde) {
		new Partie(nom, sauvegarde);
	}
	
	/**
	 * Lance le menu.
	 */
	public static void menu(){
		new LauncherView();
	}
}
