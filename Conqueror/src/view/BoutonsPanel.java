package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Classe contenant les boutons du menu principal
 */
public class BoutonsPanel extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1361602170490643561L;
	
	/** Le fond d menu. */
	private final BufferedImage fond;
	
	/**
	 * Instantiates a new boutons panel.
	 */
	public BoutonsPanel() {
		//Lecture du fond
		fond = outils.Outils.chargerImage("images/fond.png", 200, 400, Color.WHITE, 1f);
		setPreferredSize(new Dimension(200, 400));
		setSize(new Dimension(200, 400));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(fond, 0, 0, this);
		for(Component c : getComponents()) {
			c.repaint();
		}
	}
}