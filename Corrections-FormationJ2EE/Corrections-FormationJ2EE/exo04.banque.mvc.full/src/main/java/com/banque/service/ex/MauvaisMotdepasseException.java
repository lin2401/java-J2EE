package com.banque.service.ex;

/**
 * Erreur d'authentification.
 */
public class MauvaisMotdepasseException extends AuthentificationException {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de l'objet.
	 */
	public MauvaisMotdepasseException() {
		this("MauvaisMotdepasseException", null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 */
	public MauvaisMotdepasseException(String pMessage) {
		this(pMessage, null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pCause
	 */
	public MauvaisMotdepasseException(Throwable pCause) {
		this("MauvaisMotdepasseException", pCause);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 * @param pCause
	 */
	public MauvaisMotdepasseException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}