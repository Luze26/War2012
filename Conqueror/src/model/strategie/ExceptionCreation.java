package model.strategie;

/**
 * Excéption levée si la stratégie à créer n'existe pas
 */
public class ExceptionCreation extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7191339519032511636L;

	/**
	 * Instantiates a new exception creation.
	 *
	 * @param info the info
	 */
	public ExceptionCreation(String info) {
		super(info);
	}
}
