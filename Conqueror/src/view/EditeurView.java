package view;

import javax.swing.*;

import presenter.Editeur;
import view.listener.SizeListener;
import view.listener.TerrainListener;
import view.listener.TypeUniteListener;

import model.jeu.Carte;
import model.jeu.Pays;
import model.jeu.PaysBuilder;
import model.strategie.StrategieFactory;
import model.terrain.Terrain;
import model.terrain.TerrainBuilder;
import model.unite.TypeUnite;
import model.unite.TypeUniteBuilder;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * La vue de l'éditeur
 */
public class EditeurView extends MainView {
	
	/** Champ largeur de la carte. */
	private JTextField largeurField;
	
	/** Champ hauteur de la carte. */
	private JTextField hauteurField;
	
	/** Menu déroulant des pays du joueur1. */
	private JComboBox choixPaysJ1;
	
	/** Menu déroulant des pays du joueur2. */
	private JComboBox choixPaysJ2;
	
	/**Menu déroulant du choix de la stratégie. */
	private JComboBox choixStrat;
	
	/**
	 * Instantiates a new editeur view.
	 *
	 * @param editeur l'editeur
	 * @param carte la carte
	 * @param terrainBuilder le terrain builder
	 * @param paysJ1 le pays du j1
	 * @param paysJ2 le pays du j2
	 * @param strat la stratégie de victoire de la partie
	 */
	public EditeurView(Editeur editeur, Carte carte, TerrainBuilder terrainBuilder, String paysJ1, String paysJ2, String strat) {
		super(editeur, carte);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		
		JPanel bottom = new JPanel(new BorderLayout());
		bottom.add(initTabbedPane(editeur, terrainBuilder), BorderLayout.WEST); //Ajout du choix des types d'unités et terrain
		bottom.add(initParametresFields(editeur, paysJ1, paysJ2, strat), BorderLayout.EAST);	//Ajout des champs des paramètres
		fenetre.getContentPane().add(bottom, c);
		
		//Affichage de la fenêtre
		fenetre.pack();
	}

	/**
	 * Inits the parametres fields.
	 *
	 * @param editeur l'editeur
	 * @param paysJ1 le pays du j1
	 * @param paysJ2 le pays du j2
	 * @param strat la stratégie de victoire
	 * @return le panel des paramètres
	 */
	private JPanel initParametresFields(Editeur editeur, String paysJ1, String paysJ2, String strat) {
		
		List<Pays> pays = new PaysBuilder().getPays(); //On récupère la liste des pays
		
		JPanel params = new JPanel(); //On créé le panel principal
		params.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		
		
		//Ajout des champs de redimensionement
		//
		
		//Ajout du label largeur
		c.gridx = 0;
		c.gridy = 0;
		params.add(new JLabel(" Largeur : "), c);
		//Création du champ pour la largeur
		largeurField = new JTextField(String.valueOf(carteView.getLargeur()));
		largeurField.setColumns(3);
		SizeListener listener = new SizeListener(largeurField, editeur, true);
		largeurField.getDocument().addDocumentListener(listener);
		largeurField.addActionListener(listener);
		largeurField.addFocusListener(listener);
		//Ajout du champ pour la largeur
		c.gridx = 1;
		c.gridy = 0;
		params.add(largeurField, c);
		
		//Ajout du label hauteur
		c.gridx = 2;
		c.gridy = 0;
		params.add(new JLabel("  Hauteur : "), c);
		//Création du champ pour la hauteur
		hauteurField = new JTextField(String.valueOf(carteView.getHauteur()));
		hauteurField.setColumns(3);
		listener = new SizeListener(hauteurField, editeur, false);
		hauteurField.getDocument().addDocumentListener(listener);
		hauteurField.addActionListener(listener);
		hauteurField.addFocusListener(listener);
		//Ajout du champ pour la hauteur
		c.gridx = 3;
		c.gridy = 0;
		params.add(hauteurField, c);
		
		
		//Ajout du choix des pays
		//
		
		//Création des comboBox pour le choix du pays du joueur 1 et 2
		choixPaysJ1 = new JComboBox();
		choixPaysJ2 = new JComboBox();
		//On remplit les comboBox avec les pays possibles
		int i=0;
		for(Pays p : pays) {
			choixPaysJ1.addItem(p.getNom());
			choixPaysJ2.addItem(p.getNom());
			if(p.getNom().equals(paysJ1)) {
				choixPaysJ1.setSelectedIndex(i);
			}
			if(p.getNom().equals(paysJ2)) {
				choixPaysJ2.setSelectedIndex(i);
			}
			i++;
		}
		//Ajout des écouteurs sur le choix des pays
		choixPaysJ1.addActionListener(new ChoixPaysListener(editeur, 0));
		choixPaysJ2.addActionListener(new ChoixPaysListener(editeur, 1));
		
		//On ajoute les éléments au panel principal
		c.gridx = 0;
		c.gridy = 1;
		params.add(new JLabel(" Pays du J1 : "), c);
		c.gridx = 1;
		c.gridy = 1;
		params.add(choixPaysJ1, c);
		c.gridx = 2;
		c.gridy = 1;
		params.add(new JLabel("  Pays du J2 : "), c);
		c.gridx = 3;
		c.gridy = 1;
		params.add(choixPaysJ2, c);
		
		
		//Ajout du choix de la stratégie
		//
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		params.add(new JLabel("Stratégie de victoire : "), c);
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2;
		choixStrat = new JComboBox(StrategieFactory.getNomsStrategies());
		choixStrat.setSelectedItem(strat);
		choixStrat.addActionListener(new ChoixStratListener(editeur));
		params.add(choixStrat, c);
		
		return params;
	}

	/**
	 * Inits the tabbed pane.
	 *
	 * @param editeur l'editeur
	 * @param terrainBuilder l terrain builder
	 * @return les onglets de choix d'unités et terrains
	 */
	private JTabbedPane initTabbedPane(Editeur editeur, TerrainBuilder terrainBuilder) {
		//Initialisation d'un panneau à onglets
		JTabbedPane tabbedPane = new JTabbedPane();
		
		//Lecture de l'image de séléction communes à tous les types
		BufferedImage imageSelection = outils.Outils.chargerImage("images/selection.png", 40, 40, Color.CYAN, 0.3f);
		List<EditeurTypeView> listeView = new ArrayList<EditeurTypeView>();	//Liste de tous les vues de choix de l'edtieur
		boolean selected = true;
		
		//Ajout de l'onglet de la liste des terrains
		JPanel listeTerrains = new JPanel(new GridLayout(2, 5, 10, 0));

		//Pour tous les terrains on l'ajoute à l'onglet
		for(Terrain t : terrainBuilder.getTerrains()) {
			//On créé la vue du terrain
			EditeurTypeView view = new EditeurTypeView(t.getImage(), t.getNom(), selected, imageSelection);
			selected = false;
			view.setPreferredSize(new Dimension(40, 40));
			view.addMouseListener(new TerrainListener(view, listeView, t, editeur));
			listeTerrains.add(view);
			listeView.add(view);
		}
		
		tabbedPane.addTab("Terrains", null, listeTerrains, "Liste des terrains");
		
		//Ajout de l'onglet des types d'unités
		List<TypeUnite> typesUnite = new TypeUniteBuilder().getTypesUnite();
		JPanel listeUnites = new JPanel(new GridLayout((typesUnite.size()*2+1)/5, 5, 10, 0));
		int i;
		
		//On parcourt la liste des types d'unité 2 fois pour chaque joueur
		for(i=0; i<2; i++) {
			for(TypeUnite tU : typesUnite) {
				EditeurTypeView view = new EditeurTypeView(tU.getImage(i), tU.getNom(), selected, imageSelection);
				view.setPreferredSize(new Dimension(40, 40));
				view.addMouseListener(new TypeUniteListener(view, listeView, tU, i, editeur));
				listeUnites.add(view);
				listeView.add(view);
			}
		}
		
		//Ajout de l'icone de suppression des unités
		BufferedImage imageSupp = outils.Outils.chargerImage("images/suppression.png", 40, 40, Color.black, 1f);
		EditeurTypeView view = new EditeurTypeView(imageSupp, "Supprimer", selected, imageSelection);
		view.addMouseListener(new TypeUniteListener(view, listeView, null, 0, editeur));
		listeUnites.add(view);
		listeView.add(view);
		
		tabbedPane.addTab("Unités", null, listeUnites, "Liste des unités");
		return tabbedPane;
	}

	/**
	 * Refresh.
	 *
	 * @param paysJ1 the pays j1
	 * @param paysJ2 the pays j2
	 * @param strat the strat
	 */
	public void refresh(String paysJ1, String paysJ2, String strat) {
		//Rafraichissement de la vue, carte + paramètres
	
		//On rafraichit le champ de la largeur
		largeurField.setText(String.valueOf(carteView.getLargeur()));
		largeurField.setBackground(Color.white);

		//On rafraichit le champ de la hauteur
		hauteurField.setText(String.valueOf(carteView.getHauteur()));
		hauteurField.setBackground(Color.white);

		//On rafraichit le choix des pays
		choixPaysJ1.setSelectedItem(paysJ1);
		choixPaysJ2.setSelectedItem(paysJ2);

		//On rafrachit la strat
		choixStrat.setSelectedItem(strat);
		
		super.refresh();		
	}

	/**
	 * Charger.
	 */
	public void charger(){
		try {
			//On affiche une fenêtre proposant les fichiers du dossier terrains
			String terrain = outils.Outils.choisirFichier(fenetre, "terrains", "Choisir le terrain à charger :\n");
			
			//Si un choix à été fait on charge le terrain
			if(terrain != null) {
				moteur.charger(terrain);	
			}
		} catch (FileNotFoundException e) {
			//Si aucun terrains n'est dans le dossier on récupère l'exceptione t on affiche un message d'erreur
			Fenetre.showError("Erreur", "Aucuns terrains disponibles.");
		}
	}
}

// Ecouteur de la comboBox du choix d'un pays
class ChoixPaysListener implements ActionListener {
	
	private Editeur editeur;
	private int indexJoueur;
	
	public ChoixPaysListener(Editeur e, int index) {
		editeur = e;
		indexJoueur= index;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
        String pays = (String)cb.getSelectedItem();
        editeur.changerPays(indexJoueur, pays);
	}
}


//Ecouteur de la comboBox du choix de la stratégie
class ChoixStratListener implements ActionListener {
	
	private Editeur editeur;
	
	public ChoixStratListener(Editeur e) {
		editeur = e;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
        String strat = (String)cb.getSelectedItem();
        editeur.changerStrat(strat);
	}
}