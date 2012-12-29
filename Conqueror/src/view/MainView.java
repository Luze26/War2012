package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import model.jeu.Carte;

import presenter.Moteur;
import view.listener.BoutonListener;

/**
 * Classe abstraite créant une fenêtre de base avec la carte commun à l'éditeur et à une partie
 */
public abstract class MainView {
	
	/** La fenetre. */
	protected final JFrame fenetre;
	
	/** La vue de la carte. */
	protected final CarteView carteView;
	
	/** Le moteur. */
	protected final Moteur moteur;
	
	/**
	 * Instantiates a new main view.
	 *
	 * @param mot le moteur de jeu
	 * @param carte la carte
	 */
	public MainView(Moteur mot, Carte carte) {	
		moteur = mot;
		//Init de la fenêtre
		fenetre = Fenetre.getInstance();
		fenetre.getContentPane().removeAll();
		fenetre.getContentPane().setLayout(new GridBagLayout());
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setResizable(false);
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		//Init des zones de la fenêtre
		GridBagConstraints c = new GridBagConstraints();
		
		//Init du menu
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		fenetre.getContentPane().add(initMenuBar(), c);
		
		c.gridx = 0;
		c.gridy = 1;
		carteView = new CarteView(carte, moteur);
		fenetre.getContentPane().add(carteView, c);		
	}
	
	/**
	 * Inits le menu Fichier.
	 *
	 * @return le menu remplit
	 */
	private JMenuBar initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Fichier");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("Charger", KeyEvent.VK_C);
		menuItem.addActionListener(new BoutonListener<MainView>(this, "charger"));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.Event.CTRL_MASK));
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Sauvegarder", KeyEvent.VK_S);
		menuItem.addActionListener(new BoutonListener<MainView>(this, "sauvegarder"));
		menuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Sauvegarder sous", KeyEvent.VK_A);
		menuItem.addActionListener(new BoutonListener<MainView>(this, "sauvegarderSous"));
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Retour menu", KeyEvent.VK_R);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  { moteur.retourMenu(); }
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Quitter", KeyEvent.VK_Q);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  { moteur.quitter(); }
		});
		menu.add(menuItem);
		
		return menuBar;
	}


	/**
	 * Gets the carte view.
	 *
	 * @return the carte view
	 */
	public CarteView getCarteView() {
		return carteView;
	}
	

	/**
	 * Gets the fenetre.
	 *
	 * @return the fenetre
	 */
	public JFrame getFenetre() {
		return fenetre;
	}
	
	/**
	 * Refresh.
	 */
	public void refresh() {
		carteView.refresh();
		fenetre.pack();
	}
	
	/**
	 * Repack.
	 */
	public void repack() {
		fenetre.pack();
	}
	

	/**
	 * Ferme la fenêtre
	 */
	public void close() {
		fenetre.setVisible(false);		
	}
	
	/**
	 * Sauvegarder.
	 */
	public void sauvegarder(){
		if(moteur.getNomSauvegarde() != null) {
			moteur.sauvegarder();
		}
		else {
			sauvegarderSous();
		}
	}
	
	/**
	 * Sauvegarder sous.
	 */
	public void sauvegarderSous(){
		String nom = JOptionPane.showInputDialog(fenetre, "Nom de la carte : ", "", 1);
		if(nom != null) {
			moteur.sauvegarderSous(nom);
		}
	}
}
