package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.jeu.Carte;
import presenter.Partie;

/**
 * La classe de la vue du jeu
 */
public class JeuView extends MainView {

	/** Le nom joueur du joueur actif. */
	private JLabel nomJoueur;
	
	/** PM du joueur. */
	private JLabel pm;
	
	/** PA du joueur. */
	private JLabel pa;
	
	/** Le pays du joueur. */
	private JLabel pays;
	
	/** Le nom du terrain. */
	private JLabel nomTerrain;
	
	/** La défense du terrain. */
	private JLabel defenseTerrain;
	
	/** Le coût de deplacement. */
	private JLabel coutDeplacement;
	
	/** Le nom de l'unite. */
	private JLabel nomUnite;
	
	/** Les pdv. */
	private JLabel pdv;
	
	/** The deplacement. */
	private JLabel deplacement;
	
	/** The attaque. */
	private JLabel attaque;
	
	/** The armure. */
	private JLabel armure;
	
	/** La portée min. */
	private JLabel pMin;
	
	/** La portée max. */
	private JLabel pMax;
	
	/**
	 * Instantiates a new jeu view.
	 *
	 * @param partie le presenter partie (moteur)
	 * @param carte la carte
	 */
	public JeuView(final Partie partie, Carte carte) {
		super(partie, carte);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		fenetre.add(initBottom(partie), c);
		fenetre.pack();
	}
	
	/**
	 * Inits the bottom.
	 *
	 * @param partie la partie (moteur)
	 * @return panel conteant les infos du joueur/unité/terrain et bouton fin de tour
	 */
	private JPanel initBottom(final Partie partie) {
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());

		//Ajout de la zone d'info sur le joueur à gauche
		JPanel infoJoueur = new JPanel();
		infoJoueur.setPreferredSize(new Dimension(110, 90));
		infoJoueur.setLayout(new BoxLayout(infoJoueur, BoxLayout.Y_AXIS));
		
		nomJoueur = new JLabel();
		infoJoueur.add(nomJoueur);
		
		pm = new JLabel();
		infoJoueur.add(pm);
		
		pa = new JLabel();
		infoJoueur.add(pa);

		pays = new JLabel();
		infoJoueur.add(pays);
		
		bottom.add(infoJoueur);
		
		//Ajout de la zone d'info sur le terrain
		JPanel infoTerrain = new JPanel();
		infoTerrain.setPreferredSize(new Dimension(90, 90));
		infoTerrain.setLayout(new BoxLayout(infoTerrain, BoxLayout.Y_AXIS));

		nomTerrain = new JLabel();
		infoTerrain.add(nomTerrain);
		
		defenseTerrain = new JLabel();
		infoTerrain.add(defenseTerrain);
		
		coutDeplacement = new JLabel();
		infoTerrain.add(coutDeplacement);
		
		bottom.add(infoTerrain);
		
		//Ajout de la zone d'info sur l'unité
		JPanel infoUnite = new JPanel();
		infoUnite.setPreferredSize(new Dimension(110, 90));
		infoUnite.setLayout(new BoxLayout(infoUnite, BoxLayout.Y_AXIS));
		
		nomUnite = new JLabel();
		infoUnite.add(nomUnite);
		
		Font font = new Font("Dialog", Font.PLAIN, 10);
		pdv = new JLabel();
		pdv.setFont(font);
		infoUnite.add(pdv);
		
		deplacement = new JLabel();
		deplacement.setFont(font);
		infoUnite.add(deplacement);
		
		attaque = new JLabel();
		attaque.setFont(font);
		infoUnite.add(attaque);
		
		armure = new JLabel();
		armure.setFont(font);
		infoUnite.add(armure);
		
		pMin = new JLabel();
		pMin.setFont(font);
		infoUnite.add(pMin);
		
		pMax = new JLabel();
		pMax.setFont(font);
		infoUnite.add(pMax);
		
		bottom.add(infoUnite);
		
		//Ajout du bouton "fin de tour" à droite
		JButton finDeTour = new JButton("Fin de tour");
		finDeTour.setMargin(new Insets(0, 0, 0, 0));
		finDeTour.setPreferredSize(new Dimension(85, 80));
		finDeTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  { partie.finDeTour(); }
		});
		bottom.add(finDeTour);
		
		return bottom;
	}

	/**
	 * Maj info joueur.
	 *
	 * @param nom le nom du joueur
	 * @param c la couleur du joueur
	 * @param pm1 les pm restants du joueur
	 * @param pa1 les pa restants du joueur
	 * @param pays1 lepays du joueur
	 */
	public void majInfoJoueur(String nom, Color c, String pm1, String pa1, String pays1) {
		nomJoueur.setText(nom);
		nomJoueur.setForeground(c);

		pm.setText(pm1);
		pa.setText(pa1);
		pays.setText(pays1);		
	}
	
	/**
	 * Maj info terrain.
	 *
	 * @param nom le nom du terrain
	 * @param def la défense procuré par le terrain
	 * @param cout le coût de déplacement
	 */
	public void majInfoTerrain(String nom, String def, String cout) {
		nomTerrain.setText(nom);
		defenseTerrain.setText(def);
		coutDeplacement.setText(cout);
	}
	
	/**
	 * Maj info unite.
	 *
	 * @param nom le nom
	 * @param c la couleur du joueur de l'unité
	 * @param pv les points de vie
	 * @param depl le déplacement
	 * @param att l'attaque
	 * @param ar l'armure
	 * @param pMi la portée min
	 * @param pMa la portée max
	 */
	public void majInfoUnite(String nom, Color c, String pv, String depl, String att, String ar, String pMi, String pMa) {
		nomUnite.setText(nom);
		nomUnite.setForeground(c);
		
		pdv.setText(pv);
		deplacement.setText(depl);
		attaque.setText(att);
		armure.setText(ar);
		pMin.setText(pMi);
		pMax.setText(pMa);
	}
	
	/**
	 * Afficher le message de victoire.
	 *
	 * @param message le message à affciher
	 */
	public void afficherVictoire(String message) {
		JOptionPane.showMessageDialog(fenetre, message);
	}
	
	/**
	 * Clique sur le bouton charger
	 */
	public void charger(){
		try {
			String sauvegarde = outils.Outils.choisirFichier(fenetre, "sauvegardes", "Choisir la sauvegarde à charger :\n");
			if(sauvegarde != null) {
				moteur.charger(sauvegarde);					
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(fenetre, "Aucunes sauvegardes disponibles.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
