package outils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import model.jeu.Jeu;

/**
 * Classe offrant des outils utilisés partout dans l'application.
 */
public class Outils {

	/**
	 * Charge une image en mémoire à partir du nom donnée, si une erreur on créé une image avec les paramètres données
	 *
	 * @param nom le chemin vers l'image sur le disque
	 * @param largeur la largeur de l'image
	 * @param hauteur la hauteur de l'image
	 * @param c la couleur de l'image si erreur
	 * @param alpha la transparence de l'image si erreur
	 * @return l'image en buffer
	 */
	public static BufferedImage chargerImage (String nom, int largeur, int hauteur, Color c, float alpha) {
		BufferedImage image;
		File imageFile = new File(nom); //Ouverture du fichier
		
		try {
			image = (ImageIO.read(imageFile));	//On lit l'image
		} catch (IOException e) {
			//Si problème de chargement, on créé une image de dimension et couleur donnés en paramètre
			image = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = image.createGraphics();
			int rule = AlphaComposite.SRC_OVER;
	        Composite comp = AlphaComposite.getInstance(rule , alpha);
	        g2.setComposite(comp);
	        g2.setColor(c);
	        g2.fillRect(0, 0, largeur, hauteur);
		}
		
		return image;
	}

	/**
	 * Sauvegarder jeu.
	 *
	 * @param nom le chemin où enregistrer le jeu
	 * @param jeu le jeu
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void sauvegarderJeu(String nom, Jeu jeu) throws IOException {
		FileOutputStream fos;
		fos = new FileOutputStream(nom);
		ObjectOutputStream oos = new ObjectOutputStream(fos); 
		oos.writeObject(jeu); 
		oos.flush(); 
		oos.close(); 
	}

	/**
	 * Charger jeu.
	 *
	 * @param nom le nom du fichier à charger
	 * @return le jeu
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public static Jeu chargerJeu(String nom) throws FileNotFoundException, IOException, ClassNotFoundException {
		Jeu jeu;
		FileInputStream fis;
		
		fis = new FileInputStream(nom);
		ObjectInputStream ois = new ObjectInputStream(fis); 
		jeu = (Jeu)ois.readObject(); 
		ois.close(); 

		return jeu;
	}
	
	/**
	 * Affiche une boîte de dialogue, permettant de choisir entre les différents fichiers du répertoire donnée.
	 *
	 * @param fenetre la fenêtre mère
	 * @param dossier le dossier contenant les fichiers
	 * @param text le texte à écrire sur la boîte de dialogue
	 * @return le nom du fichiers choisi, null si aucun choix
	 * @throws FileNotFoundException the file not found exception
	 */
	public static String choisirFichier(JFrame fenetre, String dossier, String text) throws FileNotFoundException {
		File save = new File(dossier);
		File[] listeSave = save.listFiles();	//Fichier du dossier
		if(listeSave != null) {	//Si le dossier n'est pas vide, on affiche la fenêtre
			
			//On récupère les noms des fichiers
			String[] listeFichier = new String[listeSave.length];
			for(int i=0; i<listeSave.length;i++){
				listeFichier[i] = listeSave[i].getName();
			}

			//On affiche la boîte de dialogue
			String choix = (String)JOptionPane.showInputDialog(fenetre, text,
					"Customized Dialog",JOptionPane.PLAIN_MESSAGE, null, listeFichier, listeFichier[0]);

			if ((choix != null) && (choix.length() > 0)) {
				return choix;
			}
			else {
				return null;
			}
		}
		else {
			throw new FileNotFoundException();
		}
	}
}
