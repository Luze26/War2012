package view.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import presenter.Editeur;
import view.EditeurTypeView;

import model.terrain.Terrain;

/**
 * Classe d'écoute sur le choix du terrain dans l'éditeur.
 */
public class TerrainListener implements MouseListener {

	/** La vue du terrain écouté. */
	private EditeurTypeView terrainView;
	
	/** La liste des différents choix. */
	private List<EditeurTypeView> listeTypes;
	
	/** Le terrain associé. */
	private Terrain terrain;
	
	/** L'éditeur. */
	private Editeur editeur;
	
	/**
	 * Instantiates a new terrain listener.
	 *
	 * @param view la vue du choix
	 * @param listeView les choix
	 * @param t le terrain du choix
	 * @param e l'éditeur
	 */
	public TerrainListener(EditeurTypeView view, List<EditeurTypeView> listeView, Terrain t, Editeur e) {
		terrainView = view;
		listeTypes = listeView;
		terrain = t;
		editeur = e;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent event) {
		editeur.setTerrain(terrain);
		
		boolean trouve = false;
		int i = 0;
		//On cherche la vue sélectionné avant pour la déselectionner
		while(i<listeTypes.size() && !trouve) {
			if(listeTypes.get(i).getSelectionnee()) {
				listeTypes.get(i).setSelectionnee(false);
				listeTypes.get(i).repaint();
				trouve = true;
			}
			i++;
		}

		terrainView.setSelectionnee(true);
		terrainView.repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent event) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent event) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent event) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent event) {
	}
}
