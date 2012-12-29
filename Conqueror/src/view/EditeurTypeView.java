package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Vue de d'un type dans l'éditeur. Type unité ou terrain
 */
public class EditeurTypeView extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -526284246042097717L;
	
	/** L'image du type. */
	private final BufferedImage image;
	
	/** Type sélectionné ou non. */
	private boolean selectionnee;
	
	/** L'image de la sélection. */
	private final BufferedImage selection;
	
	/**
	 * Instantiates a new editeur type view.
	 *
	 * @param bufferedImage l'image en buffer
	 * @param nom le nom du type
	 * @param b sélection
	 * @param selec l'image de sélection
	 */
	public EditeurTypeView(BufferedImage bufferedImage, String nom, boolean b, BufferedImage selec) {
		image = new BufferedImage(40, 40, BufferedImage.TYPE_3BYTE_BGR);
		Graphics graph = image.createGraphics();
		graph.setColor(new Color(240, 240, 240));
		graph.fillRect(0, 0, 40, 40);
		graph.drawImage(bufferedImage, 0, 0, null);
		selectionnee = b;
		selection = selec;
		setToolTipText(nom);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
		if(selectionnee) {
			g.drawImage(selection, 0, 0, this);
		}
	}

	/**
	 * Sets la sélection.
	 *
	 * @param b la sélection
	 */
	public void setSelectionnee(boolean b) {
		selectionnee = b;		
	}
	
	/**
	 * Gets la sélection.
	 *
	 * @return la sélection
	 */
	public boolean getSelectionnee() {
		return selectionnee;
	}
}
