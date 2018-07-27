package com.banque.service.ex;

/**
 * Erreur sur le decouvert
 */
public class DecouvertException extends FonctionnelleException {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de l'objet.
	 */
	public DecouvertException() {
		this("DecouvertException", null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 */
	public DecouvertException(String pMessage) {
		this(pMessage, null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pCause
	 */
	public DecouvertException(Throwable pCause) {
		this("DecouvertException", pCause);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 * @param pCause
	 */
	public DecouvertException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}