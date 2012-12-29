package view.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import presenter.Moteur;
import model.jeu.Case;

/**
 * Classe pour écouter la souris sur la vue d'une case.
 *
 * @see CaseEvent
 */
public class CaseListener implements MouseListener {

	/** La caase partie modèle. */
	private Case caase;
	
	/** Le moteur. */
	private Moteur moteur;
	
	/**
	 * Instantiates a new case listener.
	 *
	 * @param c la case
	 * @param m le moteur
	 */
	public CaseListener(Case c, Moteur m) {
		caase = c;
		moteur = m;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		moteur.caseClique(caase);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent event) {
		moteur.caseEntree(caase);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent event) {
		moteur.caseSortie(caase);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent event) {
		moteur.casePressee(caase);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent event) {
		moteur.caseRelachee(caase);
	}
}
