package com.banque.service.ex;

/**
 * Erreur fontionnelle.
 */
public class FonctionnelleException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de l'objet.
	 */
	public FonctionnelleException() {
		this("FonctionnelleException", null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 */
	public FonctionnelleException(String pMessage) {
		this(pMessage, null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pCause
	 */
	public FonctionnelleException(Throwable pCause) {
		this("FonctionnelleException", pCause);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 * @param pCause
	 */
	public FonctionnelleException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

}
