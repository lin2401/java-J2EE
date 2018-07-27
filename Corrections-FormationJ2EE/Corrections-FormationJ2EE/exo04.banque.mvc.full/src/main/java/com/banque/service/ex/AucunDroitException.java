package com.banque.service.ex;

/**
 * Erreur d'authentification.
 */
public class AucunDroitException extends FonctionnelleException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de l'objet.
	 */
	public AucunDroitException() {
		this("AucunDroitException", null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 */
	public AucunDroitException(String pMessage) {
		this(pMessage, null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pCause
	 */
	public AucunDroitException(Throwable pCause) {
		this("AucunDroitException", pCause);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 * @param pCause
	 */
	public AucunDroitException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

}
