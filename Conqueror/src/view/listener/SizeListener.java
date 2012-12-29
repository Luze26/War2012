package view.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import presenter.Editeur;

/**
 * Classe d'écoute des champs de redimensionement de la carte dans l'éditeur.
 *
 * @see SizeEvent
 */
public class SizeListener implements DocumentListener, FocusListener, ActionListener {

	/** Le champs texte. */
	private JTextField textField;
	
	/** L'éditeur. */
	private Editeur editeur;
	
	/** Largeur ou non. */
	private boolean largeur;

	/**
	 * Instantiates a new size listener.
	 *
	 * @param field le champs texte
	 * @param e l'éditeur
	 * @param l si c'est la largeur
	 */
	public SizeListener(JTextField field, Editeur e, boolean l) {
		textField = field;
		editeur = e;
		largeur = l;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void changedUpdate(DocumentEvent evt) {		
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void insertUpdate(DocumentEvent evt) {
		fieldCorrect();
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void removeUpdate(DocumentEvent evt) {
		fieldCorrect();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent evt) {	
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent arg0) {
		resize();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		resize();
	}
	
	/**
	 * Vérifie que la valeur entrée dans le champ de texte soit cohérent avec nos attentes, sinon on ne fait rien et on surligne le fond en rouge.
	 */
	private void fieldCorrect() {
		try {
			int n = Integer.parseInt(textField.getText().trim());
			if(n<1) {
				textField.setBackground(Color.red);
			}
			else {
				textField.setBackground(Color.white);
			}
		}
		catch (Exception e) {
			textField.setBackground(Color.red);
		}
	}
	
	/**
	 * Demande le redimensionement au moteur.
	 */
	private void resize() {
		int n = Integer.parseInt(textField.getText().trim());
		if(largeur) {
			editeur.resizeLargeur(n);
		}
		else {
			editeur.resizeHauteur(n);
		}
	}
}
