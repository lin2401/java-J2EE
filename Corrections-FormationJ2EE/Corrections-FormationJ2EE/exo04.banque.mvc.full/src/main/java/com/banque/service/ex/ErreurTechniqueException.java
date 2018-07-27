package com.banque.service.ex;

/**
 * Erreur tehcnique.
 */
public class ErreurTechniqueException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de l'objet.
	 */
	public ErreurTechniqueException() {
		this("ErreurTechniqueException", null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 */
	public ErreurTechniqueException(String pMessage) {
		this(pMessage, null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pCause
	 */
	public ErreurTechniqueException(Throwable pCause) {
		this("ErreurTechniqueException", pCause);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 * @param pCause
	 */
	public ErreurTechniqueException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

}
