package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.jeu.Case;

/**
 * La vue d'une case
 */
public class CaseView extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1828455807212833957L;
	
	/** La case correspondante dans le modèle. */
	private final Case caase;
	
	/** La sélection. */
	private int selecIndex;
	
	/** Les images de selectionne. */
	private final BufferedImage selectionnee[];
	
	/**
	 * Instantiates a new case view.
	 *
	 * @param c la case
	 * @param image les sélections
	 */
	public CaseView(Case c, BufferedImage image[]) {
		caase = c;
		selecIndex = -1;
		selectionnee = image;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(caase.getTerrain().getImage(), 0, 0, this);	//On dessn l'image du terrain
		
		if(caase.isOccupe()) {	//Si la case est occupée, on affiche l'unité
			g.drawImage(caase.getUnite().getImageUnite(), 0, 0, this);
			g.setColor(Color.GREEN);
			g.fillRect(0, 34, 39, 4);
			
			//Affiche de la barre de vie
			float ratio = (float)caase.getUnite().getPdv()/(float)caase.getUnite().getMaxPdv();
			if(ratio<1) {
				int x = (int) (39*ratio);
				g.setColor(Color.WHITE);
				g.fillRect(x, 34, 39-x, 4);
			}
			
			g.setColor(Color.BLACK);
			g.drawLine(0, 38, 38, 38);
		}
		
		if(selecIndex!=-1) {	//affichage de la sélection
			g.drawImage(selectionnee[selecIndex], 0, 0, this);
		}
	}
	
	/**
	 * Sets la sélection.
	 *
	 * @param index le numéro de la sélection
	 */
	public void setSelecIndex(int index) {
		selecIndex = index;
	}
}
