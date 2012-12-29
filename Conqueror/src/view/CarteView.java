package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import presenter.Moteur;
import view.listener.CaseListener;

import model.jeu.Carte;
import model.jeu.Case;

/**
 * La vue de la carte.
 */
public class CarteView extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4463187438257226343L;
		
	/** Les vues des cases. */
	private List<CaseView> casesView;
	
	/** La largeur de la carte. */
	private int largeur;
	
	/** La hauteur de la carte. */
	private int hauteur;
	
	/** Les images de selection des cases en buffer. */
	private final BufferedImage[] selections;
	
	/**
	 * Instantiates a new carte view.
	 *
	 * @param carte la carte
	 * @param moteur le moteur
	 */
	public CarteView(Carte carte, Moteur moteur) {
		super();
		
		//Init des images de sélection des cases
		selections = new BufferedImage[4];
		
		selections[0] = outils.Outils.chargerImage("images/selecktion.png", 40, 40, Color.CYAN, 0.3f);
		selections[1] = outils.Outils.chargerImage("images/selectionDeplacement.png", 40, 40, Color.CYAN, 0.5f);
		selections[2] = outils.Outils.chargerImage("images/selectionDeplacement2.png", 40, 40, Color.BLUE, 0.5f);
		selections[3] = outils.Outils.chargerImage("images/selectionAttaque.png", 40, 40, Color.RED, 0.5f);
			
		largeur = carte.getLargeur();
		hauteur = carte.getHauteur();
		casesView = new ArrayList<CaseView>();
		
		//Création de la carte
		construct(carte, moteur, hauteur, largeur, casesView);
	}
	
	/**
	 * Gets the case view.
	 *
	 * @param x l'abscisse
	 * @param y l'ordonnée
	 * @return a vue de la case
	 */
	public CaseView getCaseView(int x, int y) {
		return casesView.get(x + y*largeur);
	}
	
	/**
	 * Gets la largeur
	 *
	 * @return la largeur
	 */
	public int getLargeur() {
		return largeur;
	}
	
	/**
	 * Gets la hauteur.
	 *
	 * @return la hauteur
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * Gets la vue de la case
	 *
	 * @param caase la caase
	 * @return la vue de la case
	 */
	public CaseView getCaseView(Case caase) {
		return getCaseView(caase.getX(), caase.getY());		
	}

	/**
	 * Refresh.
	 */
	public void refresh() {
		for(CaseView c : casesView) {
			c.repaint();
		}
	}
	
	/**
	 * Refresh.
	 *
	 * @param x l'abscisse
	 * @param y l'ordonnée
	 */
	public void refresh(int x, int y) {
		casesView.get(x + y*largeur).repaint();
	}
	
	/**
	 * Reconstruct.
	 *
	 * @param carte la carte
	 * @param moteur le moteur
	 */
	public void reconstruct(Carte carte, Moteur moteur) {
		largeur = carte.getLargeur();
		hauteur = carte.getHauteur();
		casesView.clear();
		removeAll();
		construct(carte, moteur, hauteur, largeur, casesView);
		revalidate();
	}
	

	/**
	 * Construct.
	 *
	 * @param carte la carte
	 * @param moteur le moteur
	 * @param hauteur la hauteur
	 * @param largeur la largeur
	 * @param casesView la liste des cases
	 */
	public void construct(Carte carte, Moteur moteur, int hauteur, int largeur, List<CaseView> casesView) {
		int x, y;
		setLayout(new GridLayout(getHauteur(), getLargeur(), 0, 0));
		
		//On créé la vue des cases
		for (y = 0; y < hauteur; y++) {
			for (x = 0; x < largeur; x++) {
				Case caase = carte.getCase(x, y);
				CaseView caseView = new CaseView(caase, selections);
				caseView.setPreferredSize(new Dimension(40, 40));
				caseView.addMouseListener(new CaseListener(caase, moteur));
				add(caseView);
				casesView.add(caseView);
			}
		}
	}
}
