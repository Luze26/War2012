package presenter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import model.jeu.Case;
import model.jeu.Jeu;
import model.jeu.Joueur;
import model.jeu.Unite;
import model.terrain.Terrain;
import model.unite.Chemin;
import view.CaseView;
import view.Fenetre;
import view.JeuView;

/**
 * La classe partie fait le lien entre la vue de la partie (jouable) et les données de la partie.
 */
public class Partie  implements Moteur {

	/** Le jeu. */
	private Jeu jeu;
	
	/** La vue de la partie. */
	private JeuView view;
	
	/** Les chemins affichés. */
	private List<Chemin> cheminsCourants;
	
	/** Le chemin que le joueur sélectionne. */
	private Chemin cheminCourant;
	
	/** La liste des cases attaquables affichée. */
	private List<Case> casesAttaquables;
	
	/** Si l'affichage des cases sélectionnées est persistant ou non au relachement du clique. */
	private boolean persistant;
	
	/** La case sélectionnée. */
	private Case caseCourante;
	
	/** Le nom de la sauvegarde. */
	private String nomSauvegarde;
	
	/**
	 * Instantiates a new partie.
	 *
	 * @param nomPartie le nom du fichier du jeu à charger
	 * @param sauvegarde si la sauvegarde est une aprtie en cours ou non
	 */
	public Partie(String nomPartie, boolean sauvegarde) {
		try {
			//On charge la sauvegarde ou la carte
			if(sauvegarde) {
				charger(nomPartie);
			}
			else {
				chargerCarte(nomPartie);
			}
			
			//On créé la vue
			view = new JeuView(this, jeu.getCarte());
			majInfoJoueurView();
			cheminsCourants = null;
			persistant = false;
			
		} catch (FileNotFoundException e) {
			Fenetre.showError("Terrain inconnu !", "Terrain non trouvé, vérifiez qu'il soit présent dans le dossier terrains.");
		} catch (IOException e) {
			Fenetre.showError("Terrain corrompu !", "La sauvegarde du terrain est corrompue !");
		} catch (ClassNotFoundException e) {
			Fenetre.showError("Terrain inconnu !", "Terrain non trouvé, vérifiez qu'il soit présent dans le dossier terrains.");
		}
	}

	/* (non-Javadoc)
	 * @see presenter.Moteur#caseClique(model.jeu.Case)
	 */
	@Override
	public void caseClique(Case caase1) {
	}

	/* (non-Javadoc)
	 * @see presenter.Moteur#casePressee(model.jeu.Case)
	 */
	@Override
	public void casePressee(Case caase1) {
		
		//Si la case pressée contient une unité
		if(caase1.isOccupe() && (caseCourante==null || !caase1.equals(caseCourante))) {	
			
			//Si on était en train de voir les déplacements d'une unité, on déseletionne les chemins
			if(cheminsCourants!=null) {
				//Déselectionne la zone de déplacement
				selectionnerDeplacement(-1);
				cheminsCourants.clear();
			}
			
			if(casesAttaquables != null && casesAttaquables.contains(caase1)) { //Si la case cliqué fait partis des cases attaquables, on fait l'attaque
				//Attaque
				if(jeu.getJoueurActif().getpA()!=0) {	//Si le joueur peut attaquer
					caseCourante.getUnite().attaque(caase1);
					majInfoJoueurView();
					majInfoUniteView(caase1);
				}
				else {
					Fenetre.showError("Action impossible !", "Vous ne pouvez plus attaquer ce tour ci (pa = 0)");
				}
				selectionnerAttaquables(-1);
				casesAttaquables = null;
				caseCourante = null;
				victoire();
			}
			else {	//Sinon si la case n'est pas une attaquable, on montre la zone de déplacement de l'untié cliqué
				selectionnerAttaquables(-1);
				casesAttaquables = null;
				caseCourante = caase1;
				Unite unite = caase1.getUnite();	//On récupère l'unité
				cheminsCourants = unite.getTypeUnite().getCasesAccessibles(jeu.getCarte(), caase1);
				selectionnerDeplacement(1);
				
				if(unite.getJoueur().equals(jeu.getJoueurActif())) {	//Si l'unité est une du joueur actif, on affiche aussi les cases attaquables
					persistant = true;
					casesAttaquables = unite.getTypeUnite().getCasesAttaquables(jeu.getCarte(), caase1, 1-jeu.getNumJoueurActif());
					selectionnerAttaquables(3);
				}
				else {
					persistant = false;
				}
			}
		}
		else if(cheminCourant != null) { //Sinon si on est sur un chemin on déplace l'unité sur la fin du chemin
			if(jeu.getJoueurActif().getpM()!=0) { //Si le joueur peut encore déplacer l'unité, on déplace
				caseCourante.deplacerUnite(caase1);
				view.getCarteView().getCaseView(caseCourante).repaint();
				majInfoJoueurView();
				majInfoUniteView(caase1);
			}
			else {	//Sinon on affiche un message d'erreur
				Fenetre.showError("Action impossible !" , "Vous ne pouvez plus déplacer d'unité (pm=0)");
			}
			selectionnerAttaquables(-1);
			casesAttaquables = null; 
			selectionnerDeplacement(-1);
			cheminsCourants = null;
			cheminCourant = null;
			caseCourante = null;
			victoire();
		} else {
			selectionnerAttaquables(-1);
			casesAttaquables = null; 
			selectionnerDeplacement(-1);
			cheminsCourants = null;
			cheminCourant = null;
			caseCourante = null;
		}
	}

	/* (non-Javadoc)
	 * @see presenter.Moteur#caseRelachee(model.jeu.Case)
	 */
	@Override
	public void caseRelachee(Case caase1) {
		if(!persistant && cheminsCourants != null) {
			selectionnerDeplacement(-1);
			cheminsCourants = null;
			caseCourante = null;
		}		
	}

	/* (non-Javadoc)
	 * @see presenter.Moteur#caseEntree(model.jeu.Case)
	 */
	@Override
	public void caseEntree(Case caase1) {
		//On trace le chemin si la case fait partie de la zone de mouvement
		int i;
		cheminCourant = null;
		if(persistant && cheminsCourants != null) {
			for(Chemin ch : cheminsCourants) {
				if(ch.getCaseArrivee().equals(caase1)) {
					cheminCourant = ch;
					for(i=1; i<ch.getCases().size(); i++) {
						Case c = ch.getCases().get(i);
						view.getCarteView().getCaseView(c).setSelecIndex(2);
						view.getCarteView().getCaseView(c).repaint();
					}
					break;
				}
			}
		}
		
		//Maj des infos du terrain
		Terrain terr = caase1.getTerrain();
		majInfoTerrainView(terr);
	
		//Maj des infos de l'unité
		majInfoUniteView(caase1);
	}

	/* (non-Javadoc)
	 * @see presenter.Moteur#caseSortie(model.jeu.Case)
	 */
	@Override
	public void caseSortie(Case caase1) {
		int i;
		
		if(cheminCourant != null) {
			for(i=1; i<cheminCourant.getCases().size(); i++) {
				Case c = cheminCourant.getCases().get(i);
				view.getCarteView().getCaseView(c).setSelecIndex(1);
				view.getCarteView().getCaseView(c).repaint();
			}
			cheminCourant = null;
		}
	}

	/**
	 * Charger la carte.
	 *
	 * @param nom le nom de la carte à charger.
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	private void chargerCarte(String nom) throws FileNotFoundException, IOException, ClassNotFoundException {
		jeu = outils.Outils.chargerJeu("terrains/" + nom);
	}
	
	/* (non-Javadoc)
	 * @see presenter.Moteur#charger(java.lang.String)
	 */
	@Override
	public void charger(String nom) {
		try {
			jeu = outils.Outils.chargerJeu("sauvegardes/" + nom);

			nomSauvegarde = nom;
			if(view != null) {	//On rafraichit al vue, si elle est déjà créée
				view.getCarteView().reconstruct(jeu.getCarte(), this);
				view.refresh();
				majInfoJoueurView();
			}
		} catch (FileNotFoundException e) {
			Fenetre.showError("Sauvegarde inconnu !", "Sauvegarde non trouvé, vérifiez qu'il soit présent dans le dossier sauvegardes.");
		} catch (IOException e) {
			Fenetre.showError("Sauvegarde corrompu !", "La sauvegarde du terrain est corrompue !");
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
			outils.Outils.sauvegarderJeu("sauvegardes/" + nomSauvegarde, jeu);
		} catch (IOException e) {
			Fenetre.showError("Erreur", "Impossible de sauvegarder la partie !");
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
	 * Selectionner les cases des chemins courants
	 *
	 * @param selecIndex le type de sélection à faire
	 */
	private void selectionnerDeplacement(int selecIndex) {
		if(cheminsCourants != null) {	//Si il y a des cases à sélectionenr, on les parcours et les sélectionnes
			for(Chemin ch : cheminsCourants) {
				CaseView c = view.getCarteView().getCaseView(ch.getCaseArrivee());
				c.setSelecIndex(selecIndex);
				c.repaint();
			}
		}
	}

	/**
	 * Selectionner les cases attaquables.
	 *
	 * @param selecIndex le type de la sélection
	 */
	private void selectionnerAttaquables(int selecIndex) {
		if(casesAttaquables != null) {
			for(Case c : casesAttaquables) {
				CaseView cv = view.getCarteView().getCaseView(c);
				cv.setSelecIndex(selecIndex);
				cv.repaint();
			}	
		}
	}
	
	/**
	 * Rafraichit les infos du joueur sur la vue.
	 */
	private void majInfoJoueurView() {
		Joueur j = jeu.getJoueurActif();
		view.majInfoJoueur("Joueur " + j.getNumero(), j.getColor(),  "PM: " + j.getpM(), "PA: " + j.getpA(), "Pays: " + j.getPays().getNom());
	}
	
	/**
	 * Rafraichit les infos de l'unité sur la vue.
	 *
	 * @param caase1 la case survolée
	 */
	private void majInfoUniteView(Case caase1) {
		Unite u = caase1.getUnite();
		if(u==null) {
			view.majInfoUnite("", null, "", "", "", "", "", "");
		}
		else {
			view.majInfoUnite(u.getNom(), u.getJoueur().getColor(), "PV: " + u.getPdv(), "PM: " + u.getDeplacement(), "Attaque :" + u.getAttaque(), "Armure: " + u.getArmure(), "P Min: " + u.getPorteeMin(), "P Max: " + u.getPorteeMax());
		}
	}
	
	/**
	 * Rafraichit les infos du terrain sur la vue.
	 *
	 * @param terr le terrain survolé
	 */
	private void majInfoTerrainView(Terrain terr) {
		view.majInfoTerrain(terr.getNom(), "Défense: " + terr.getDefense(), "Coût PM: " + terr.getCoutDeplacement());
	}
	
	/**
	 * Fin de tour.
	 */
	public void finDeTour() {
		if(!victoire()) {
			//Mis à jour des données
			jeu.finDeTour();
			
			//Remise à 0 du contexte
			selectionnerDeplacement(-1);
			selectionnerAttaquables(-1);
			casesAttaquables = null; 
			cheminsCourants = null;
			cheminCourant = null;
			caseCourante = null;
			
			//On rafraichit la vue
			majInfoJoueurView();
			view.refresh();
		}
	}
	
	/**
	 * Victoire. Regarde si le joueur vient de gagner.
	 *
	 * @return true, si le joueur à gagné.
	 */
	private boolean victoire() {
		boolean victoire = jeu.getContextVictoire().aGagner(jeu); 
		if(victoire) { //Si le joueur a gagné on affiche un pop up de victoire
			view.afficherVictoire("Le joueur " + jeu.getNumJoueurActif() + " à gagné !!!!");
		}
		return victoire;
	}
}
