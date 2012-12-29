package view.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import model.unite.TypeUnite;
import presenter.Editeur;
import view.EditeurTypeView;

/**
 * Ecoute d'une vue correspondant à un type d'unité pour l'éditeur.
 *
 * @see TypeUniteEvent
 */
public class TypeUniteListener implements MouseListener {

	/** La vue écouté. */
	private EditeurTypeView typeUniteView;
	
	/** Les autres vues. */
	private List<EditeurTypeView> listeTypesView;
	
	/** Le type d'unité correspondant. */
	private TypeUnite typeUnite;
	
	/** Le numéro du joueur de l'unité. */
	private int numJoueur;
	
	/** L'éditeur. */
	private Editeur editeur;
	
	/**
	 * Instantiates a new type unite listener.
	 *
	 * @param view la vue écoutée
	 * @param listeView la liste des autres vues
	 * @param tU le type de l'unité
	 * @param num le numéro de l'unité
	 * @param e l'éditeur
	 */
	public TypeUniteListener(EditeurTypeView view, List<EditeurTypeView> listeView, TypeUnite tU, int num, Editeur e) {
		typeUniteView = view;
		listeTypesView = listeView;
		typeUnite = tU;
		numJoueur = num;
		editeur = e;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		boolean trouve = false;
		int i = 0;
		//On parcourt les vues pour déselectionner celle sélectionnée
		while(i<listeTypesView.size() && !trouve) {
			if(listeTypesView.get(i).getSelectionnee()) {
				listeTypesView.get(i).setSelectionnee(false);
				listeTypesView.get(i).repaint();
				trouve = true;
			}
			i++;
		}
		
		//On sélectionne la vue écoutée
		typeUniteView.setSelectionnee(true);
		typeUniteView.repaint();
		editeur.setTypeUnite(typeUnite, numJoueur);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {	
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {		
	}

}
