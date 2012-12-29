package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;

import presenter.Launcher;
import view.listener.BoutonListener;

/**
 * Vue du menu principal du jeu
 */
public class LauncherView {

	/** La fenetre. */
	private final JFrame fenetre;
	
	/** Le panel contenant les boutons. */
	private final BoutonsPanel boutons;
	

	/**
	 * Instantiates a new launcher view.
	 */
	public LauncherView() {		

		fenetre = Fenetre.getInstance();
		fenetre.getContentPane().removeAll();
		
		//init des boutons
		boutons = new BoutonsPanel();
		boutons.setLayout(null);

		this.menuAccueil();
		fenetre.pack();
		fenetre.setVisible(true);
	}
	
	
	/**
	 * Menu accueil.
	 */
	public void menuAccueil(){
		int largeur = 200;
		//Taille bouton
		int lar = 30, lon = 90;
		
		//On supprime les anciens boutons.
		boutons.removeAll();
		boutons.revalidate();
		
		JButton jouer = new JButton("Jouer");
		jouer.setBounds(((largeur/2)-lon/2), 170, lon, lar);
		jouer.addActionListener( new BoutonListener<LauncherView>(this, "menuSuite"));
		
		boutons.add(jouer);
		
		JButton editeur = new JButton("Editeur");
		editeur.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) { Launcher.editeur(); }
		});
		editeur.setBounds(((largeur/2)-lon/2), 215, lon, lar);
		boutons.add(editeur);
		
		JButton quitter = new JButton("Quitter");
		quitter.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) { System.exit(0); }
		});
		quitter.setBounds(((largeur/2)-lon/2), 260, lon, lar);
		boutons.add(quitter);
		boutons.repaint();	
		
		fenetre.getContentPane().add(boutons);		
	}
	
	
	/**
	 * Menu de début de partie.
	 */
	public void menuSuite(){

		//Taille fenetre hauteur=400
		int largeur = 200;
		//Taille bouton
		int lar = 30, lon = 150;

		//On supprime les anciens boutons.
		boutons.removeAll();
		boutons.revalidate();

		JButton nouvPartie = new JButton("Nouvelle Partie");
		nouvPartie.setBounds(((largeur/2)-lon/2), 170, lon, lar);
		nouvPartie.addActionListener( new BoutonListener<LauncherView>(this, "chargerTerrain"));

		boutons.add(nouvPartie);

		JButton chargPartie = new JButton("Charger Partie");
		chargPartie.addActionListener( new BoutonListener<LauncherView>(this, "chargerPartie"));
		chargPartie.setBounds(((largeur/2)-lon/2), 215, lon, lar);
		boutons.add(chargPartie);

		JButton quitter = new JButton("Annuler");
		quitter.addActionListener( new BoutonListener<LauncherView>(this, "menuAccueil"));
		quitter.setBounds(((largeur/2)-lon/2), 260, lon, lar);
		boutons.add(quitter);
		boutons.repaint();

		fenetre.getContentPane().add(boutons);
	}
	
	/**
	 * Charger partie.
	 */
	public void chargerPartie() {
		try {
			String sauvegarde = outils.Outils.choisirFichier(fenetre, "sauvegardes", "Choisir la sauvegarde à charger :\n");
			if(sauvegarde != null) {
				Launcher.jouer(sauvegarde, true);
			}
		} catch (FileNotFoundException e) {
			Fenetre.showError("Erreur", "Aucunes parties disponibles.");
		}
	}
	
	/**
	 * Charger terrain.
	 */
	public void chargerTerrain() {
		try {
			String terrain = outils.Outils.choisirFichier(fenetre, "terrains", "Choisir le terrain à charger :\n");
			if(terrain != null) {
				Launcher.jouer(terrain, false);					
			}
		} catch (FileNotFoundException e) {
			Fenetre.showError("Erreur", "Aucuns terrains disponibles.");
		}
	}
}



