package model.terrain;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant la construction des différents types de terrain.
 */
public class TerrainBuilder {

	/** Les terrains disponibles. */
	private List<Terrain> terrains;
	
	/**
	 * Instantiates a new terrain builder.
	 */
	public TerrainBuilder() {
		terrains = new ArrayList<Terrain>();
		
		//Ajout des terrains
		terrains.add(new Plaine());
		terrains.add(new PlaineNeige());
		terrains.add(new Mer());
		terrains.add(new Montagne());
		terrains.add(new Foret());
		terrains.add(new ForetNeige());
		terrains.add(new Recif());
		terrains.add(new PetitRecif());
		terrains.add(new Iceberg());
	}
	
	/**
	 * Gets les terrains.
	 *
	 * @return les terrains
	 */
	public List<Terrain> getTerrains() {
		return terrains;
	}
}
