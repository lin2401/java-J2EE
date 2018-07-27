package com.banque.service.ex;

/**
 * Erreur d'authentification.
 */
public class UtilisateurInconnuException extends AuthentificationException {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de l'objet.
	 */
	public UtilisateurInconnuException() {
		this("UtilisateurInconnuException", null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 */
	public UtilisateurInconnuException(String pMessage) {
		this(pMessage, null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pCause
	 */
	public UtilisateurInconnuException(Throwable pCause) {
		this("UtilisateurInconnuException", pCause);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 * @param pCause
	 */
	public UtilisateurInconnuException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}