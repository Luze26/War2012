package model.strategie;

/**
 * Une factory pour construire les objets de stratégies à partir de leur nom.
 */
public class StrategieFactory {	
	
	/**
	 * Gets les noms des stratégies.
	 *
	 * @return les noms des stratégies
	 */
	public static String[] getNomsStrategies() {
		String[] strats = new String[2];
		
		strats[0] = "Combat à mort";
		strats[1] = "Tuer le Roi";
		
		return strats;
	}
	
	/**
	 * Gets la stratégie créée à partir du nom donnée.
	 *
	 * @param nom le nom de la stratégie à créer
	 * @return la stratégie créée
	 * @throws ExceptionCreation l'exception si le nom correspond à aucune stratégie connue
	 */
	public StrategieVictoire getStrategie(String nom) throws ExceptionCreation {
		if(nom.equals("Combat à mort")) {
			return new StrategieTousTues("Combat à mort");
		}
		else if(nom.equals("Tuer le Roi")) {
			return new StrategieTuerRoi("Tuer le Roi");
		}
		
		//Aucune stratégie correspondante, on léve l'exception
		throw new ExceptionCreation("Impossible de créer la stratégie " + nom); 
	}
}
