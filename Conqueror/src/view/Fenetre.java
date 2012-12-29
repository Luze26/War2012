package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe singleton contenant la fenêtre du jeu
 */
public class Fenetre extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3657961643966951695L;
	
	/** The Constant instance. */
	private static final Fenetre instance = new Fenetre();

	/**
	 * Instantiates a new fenetre.
	 */
	private Fenetre() {
		this.getContentPane().setPreferredSize(new Dimension(200, 400));
		this.pack();
		this.setLocationRelativeTo(null);
		this.getContentPane().setPreferredSize(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		JFrame.setDefaultLookAndFeelDecorated(true);
	}

	/**
	 * Gets the single instance of Fenetre.
	 *
	 * @return single instance of Fenetre
	 */
	public static Fenetre getInstance() {
		return instance;
	}
	
	/**
	 * Show error.
	 *
	 * @param titre le titre de l'erreur
	 * @param message le message de l'erreur
	 */
	public static void showError(String titre, String message) {
		JOptionPane.showMessageDialog(instance,
			    message,
			    titre,
			    JOptionPane.ERROR_MESSAGE);
	}
}
