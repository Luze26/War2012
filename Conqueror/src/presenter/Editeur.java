package presenter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import view.CaseView;
import view.EditeurView;
import view.Fenetre;
import model.jeu.Case;
import model.jeu.Jeu;
import model.jeu.Pays;
import model.jeu.PaysBuilder;
import model.jeu.Unite;
import model.strategie.ExceptionCreation;
import model.strategie.StrategieFactory;
import model.terrain.Terrain;
import model.terrain.TerrainBuilder;
import model.unite.TypeUnite;

/**
 * Classe faisant le lien entre les données et la vue pour l'éditeur.
 */
public class Editeur implements Moteur {

	/** Les terrains disponibles. */
	private TerrainBuilder terrainBuilder;
	
	/** Le jeu. */
	private Jeu jeu;
	
	/** La vue. */
	private EditeurView view;
	
	/** Le terrain sélectionné dans l'éditeur à ajouter à la prochaine sélection. */
	private Terrain terrain;
	
	/** Le type d'unité sélectionné dans l'éditeur à ajouter à la prochaine sélection. */
	private TypeUnite typeUnite;
	
	/** Le numéro de joueur de l'unité à ajouter à la prochaine sélection. */
	private int numJoueur;
	
	/** La case point de départ de la sélection. */
	private Case caase;
	
	/** La dernière case sélectionnée. */
	private Case derniereCase;
	
	/** Les cases selecetionnées. */
	private List<Case> casesSelec;
	
	/** Les vues des cases. */
	private List<CaseView> casesView;
	
	/** Le nom de la sauvegarde. */
	private String nomSauvegarde;
	
	/**
	 * Instantiates a new editeur.
	 */
	public Editeur() {
		terrainBuilder = new TerrainBuilder();	//Création des terrains
		
		jeu = new Jeu(terrainBuilder, 15, 8);	//Création du jeu.
		
		terrain = terrainBuilder.getTerrains().get(0);	//Terrain sélectionné par défaut
		typeUnite = null;
		caase = null;
		derniereCase = null;
		casesSelec = new ArrayList<Case>();
		casesView = new ArrayList<CaseView>();
		nomSauvegarde = null;
		
		//Création de la vue
		view = new EditeurView(this, jeu.getCarte(), terrainBuilder, jeu.getJoueur(0).getPays().getNom(), jeu.getJoueur(1).getPays().getNom(), jeu.getContextVictoire().getNomStrategie());
	}

	/**
	 * Sets le terrain seléctionné.
	 *
	 * @param terr le terrain seléctionné
	 */
	public void setTerrain(Terrain terr) {
		terrain = terr;
		typeUnite = null;
	}
	
	/**
	 * Sets le type d'unité sélectionné
	 *
	 * @param tU  le type d'unité sélectionné
	 * @param num le numéro du joueur du "type de l'unité"
	 */
	public void setTypeUnite(TypeUnite tU, int num) {
		typeUnite = tU;
		numJoueur = num;
		terrain = null;
	}
	
	/* (non-Javadoc)
	 * @see presenter.Moteur#caseClique(model.jeu.Case)
	 */
	public void caseClique(Case caase1) {
		traiterCase(caase1);
		view.getCarteView().refresh(caase1.getX(), caase1.getY());
	}
	
	/* (non-Javadoc)
	 * @see presenter.Moteur#casePressee(model.jeu.Case)
	 */
	public void casePressee(Case caase1) {
		caase = caase1;
	}
	
	/* (non-Javadoc)
	 * @see presenter.Moteur#caseRelachee(model.jeu.Case)
	 */
	public void caseRelachee(Case caase1) {
		if(caase != null) {
			for(Case c : casesSelec) {
				traiterCase(c);
			}
			
			view.getCarteView().refresh();
			casesSelec.clear();
			deselectionne();
			caase = null;
		}
	}
	
	/* (non-Javadoc)
	 * @see presenter.Moteur#caseEntree(model.jeu.Case)
	 */
	@Override
	public void caseEntree(Case caase1) {
		if(caase!=null && (derniereCase == null || !caase1.equals(derniereCase))) { //Si on fait une sélection
			deselectionne(); //On déselectionne pour faire une nnouvelle sélection
			derniereCase = caase1;
			
			//On détermine les coordonnées de la case en haut à gauche et de celle en bas à droite
			int xDeb, yDeb, xFin, yFin, i, j,
			x = caase.getX(),
			x1 = derniereCase.getX(),
			y = caase.getY(),
			y1 = derniereCase.getY();
			
			if(x < x1) {
				xDeb = x;
				xFin = x1;
			} else {
				xDeb = x1;
				xFin = x;
			}
			
			if(y < y1) {
				yDeb = y;
				yFin = y1;
			} else {
				yDeb = y1;
				yFin = y;
			}

			//On parcourt les cases à sélectionner
			for(i=xDeb; i<=xFin; i++) {
				for(j=yDeb; j<=yFin; j++) {
					casesSelec.add(jeu.getCarte().getCase(i, j));
					CaseView c = view.getCarteView().getCaseView(i, j);
					casesView.add(c);
					c.setSelecIndex(0);
				}
			}
			
			view.getCarteView().refresh();
		}
	}

	/* (non-Javadoc)
	 * @see presenter.Moteur#caseSortie(model.jeu.Case)
	 */
	@Override
	public void caseSortie(Case caase1) {		
	}

	/* (non-Javadoc)
	 * @see presenter.Moteur#charger(java.lang.String)
	 */
	@Override
	public void charger(String nom) {
		try {
			jeu = outils.Outils.chargerJeu("terrains/" + nom);

			nomSauvegarde = nom;	//On met à jour le nomd e la sauvegarde
			view.getCarteView().reconstruct(jeu.getCarte(), this);	//On reconstruit la carte
			
			//On rafraichit la vue
			view.refresh(jeu.getJoueur(0).getPays().getNom(), jeu.getJoueur(1).getPays().getNom(), jeu.getContextVictoire().getNomStrategie());

		} catch (FileNotFoundException e) {
			Fenetre.showError("Terrain inconnu !", "Terrain non trouvé, vérifiez qu'il soit présent dans le dossier terrains.");
		} catch (IOException e) {
			Fenetre.showError("Terrain corrompu !", "La sauvegarde du terrain est corrompue !");
		} catch (ClassNotFoundException e) {
			Fenetre.showError("Erreur", "Erreur du stupide développeur !");
		} 
	}

	/* (non-Javadoc)
	 * @see presenter.Moteur#sauvegarder()
	 */
	@Override
	public void sauvegarder() {
		try {
			outils.Outils.sauvegarderJeu("terrains/" + nomSauvegarde, jeu);
		} catch (IOException e) {
			Fenetre.showError("Erreur", "Impossible de sauvegarder le terrain");
		}
	}

	/* (non-Javadoc)
	 * @see presenter.Moteur#sauvegarderSous(java.lang.String)
	 */
	@Override
	public void sauvegarderSous(String nom) {
		nomSauvegarde = nom;
		sauvegarder();		
	}

	/* (non-Javadoc)
	 * @see presenter.Moteur#getNomSauvegarde()
	 */
	@Override
	public String getNomSauvegarde() {
		return nomSauvegarde;
	}
	
	/* (non-Javadoc)
	 * @see presenter.Moteur#retourMenu()
	 */
	@Override
	public void retourMenu() {
		Launcher.menu();
	}

	/* (non-Javadoc)
	 * @see presenter.Moteur#quitter()
	 */
	@Override
	public void quitter() {
		System.exit(0);
	}

	/**
	 * Resize largeur.
	 *
	 * @param l the l
	 */
	public void resizeLargeur(int l) {
		if(l != jeu.getCarte().getLargeur()) {
			jeu = new Jeu(terrainBuilder, l, jeu.getCarte().getHauteur());
			view.getCarteView().reconstruct(jeu.getCarte(), this);
			view.repack();
		}
	}

	/**
	 * Resize hauteur.
	 *
	 * @param h la nouvelle hauteur de la carte
	 */
	public void resizeHauteur(int h) {
		if(h != jeu.getCarte().getHauteur()) {
			jeu = new Jeu(terrainBuilder, jeu.getCarte().getLargeur(), h);
			view.getCarteView().reconstruct(jeu.getCarte(), this);
			view.repack();
		}
	}

	/**
	 * Deselectionne, les cases sélectionnées
	 */
	private void deselectionne() {
		for(CaseView cV : casesView) {
			cV.setSelecIndex(-1);
			cV.repaint();
		}
		casesView.clear();
		casesSelec.clear();
	}
	
	/**
	 * Fait le traitement, sur les cases sélectionnées : changement de terrain / d'unité.
	 *
	 * @param c la case
	 */
	private void traiterCase(Case c) {
		if(terrain != null) {	//Si un type de terrain est sélectionné, on change le terrain de la case.
			c.setTerrain(terrain);
		} else {
			if(typeUnite == null) {	//Sinon si un type d'unité est sélectionné, on créé l'unité sur la case.
				Unite unit = c.getUnite();
				if(unit != null) {
					unit.getJoueur().suppUnite(unit);
					c.setUnite(null);
				}
			}
			else {	//Sinon on supprime l'unité de la case
				c.setUnite(new Unite(jeu.getJoueur(numJoueur), typeUnite));
			}
		}
	}

	/**
	 * Change le pays d'un joueur.
	 *
	 * @param indexJoueur le numéro du joueur changeant de nationnalité
	 * @param pays le nouveau pays du joueur
	 */
	public void changerPays(int indexJoueur, String pays) {
		List<Pays> listPays = new PaysBuilder().getPays();	//On récupère la liste des pays
		Pays paysChoisi = null;
		
		//On parcourt la liste des pays pour trouver celui de même nom
		for(Pays p : listPays) {
			if(p.getNom().equals(pays)) {
				paysChoisi = p;
				break;
			}
		}
		
		if(paysChoisi!=null) {
			jeu.getJoueur(indexJoueur).setPays(paysChoisi);
		}		
	}

	/**
	 * Change la stratégie de victoire de la partie
	 *
	 * @param strat la nouvelle stratégie de victoire
	 */
	public void changerStrat(String strat) {
		StrategieFactory facto = new StrategieFactory();
		try {
			jeu.getContextVictoire().setStrategieVictoire(facto.getStrategie(strat));
		} catch (ExceptionCreation e) {
			Fenetre.showError("Erreur", e.getMessage());
		}
		
	}
}
