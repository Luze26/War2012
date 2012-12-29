package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import view.Fenetre;

/**
 * Classe générique d'écoute des boutons, éxécutant la méthode passée en paramètre.
 *
 * @see BoutonEvent
 */
public class BoutonListener<T> implements ActionListener{
	
	/** La vue. */
	private T vue;
	
	/** The method. */
	private Method method;
	
	/**
	 * Instantiates a new bouton listener.
	 *
	 * @param o the o
	 * @param nomMeth the nom meth
	 */
	public BoutonListener(T v, String nomMeth){
		vue = v;	
		try {
			method = v.getClass().getMethod(nomMeth);
		} catch (Exception e1) {
			Fenetre.showError("Erreur", "Erreur interne : " + e1.getMessage());
		} 
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			method.invoke(vue);
		} catch (Exception e1) {
			Fenetre.showError("Erreur", "Erreur interne : " + e1.getMessage());
		} 
	}

}