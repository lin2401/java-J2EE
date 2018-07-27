package fr.banque;

/**
 * Ma classe d'exception.
 */
public class BanqueException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public BanqueException() {
		super();
	}

	/**
	 * Constructeur.
	 *
	 * @param message
	 *            le message
	 */
	public BanqueException(String message) {
		super(message);
	}

	/**
	 * Constructeur.
	 *
	 * @param cause
	 *            la cause
	 */
	public BanqueException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructeur.
	 *
	 * @param message
	 *            le message
	 * @param cause
	 *            la cause
	 */
	public BanqueException(String message, Throwable cause) {
		super(message, cause);
	}
}
