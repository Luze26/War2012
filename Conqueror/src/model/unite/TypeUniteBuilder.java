package model.unite;

import java.util.ArrayList;
import java.util.List;

/**
 * Constructeur des différents types d'unité disponibles.
 */
public class TypeUniteBuilder {

	/** Les types d'unités. */
	private List<TypeUnite> typesUnite;
	
	/**
	 * Instantiates a new type unite builder.
	 */
	public TypeUniteBuilder() {
		typesUnite = new ArrayList<TypeUnite>();
		
		//Construction des différents type d'unité
		
		//Soldat
		String[] imageNames = new String[2];
		imageNames[0] = "soldatR.png";
		imageNames[1] = "soldatB.png";
		typesUnite.add(new Soldat(imageNames));
		
		//Bazooka
		imageNames = new String[2];
		imageNames[0] = "bazookaR.png";
		imageNames[1] = "bazookaB.png";
		typesUnite.add(new Bazooka(imageNames));
		
		//Tank
		imageNames = new String[2];
		imageNames[0] = "tankR.png";
		imageNames[1] = "tankB.png";
		typesUnite.add(new Tank(imageNames));
		
		//Lance Missile
		imageNames = new String[2];
		imageNames[0] = "lanceMissileR.png";
		imageNames[1] = "lanceMissileB.png";
		typesUnite.add(new LanceMissile(imageNames));
		
		//Destroyer
		imageNames = new String[2];
		imageNames[0] = "destroyerR.png";
		imageNames[1] = "destroyerB.png";
		typesUnite.add(new Destroyer(imageNames));
		
		//Hélico
		imageNames = new String[2];
		imageNames[0] = "helicoR.png";
		imageNames[1] = "helicoB.png";
		typesUnite.add(new Helico(imageNames));
		
		//Roi
		imageNames = new String[2];
		imageNames[0] = "";
		imageNames[1] = "";
		typesUnite.add(new Roi(imageNames));
	}
	
	/**
	 * Gets les types d'unités.
	 *
	 * @return les types d'unités
	 */
	public List<TypeUnite> getTypesUnite() {
		return typesUnite;
	}
}
