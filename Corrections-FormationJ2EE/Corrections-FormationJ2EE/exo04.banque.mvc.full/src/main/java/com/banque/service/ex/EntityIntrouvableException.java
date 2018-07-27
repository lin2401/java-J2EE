package com.banque.service.ex;

/**
 * Erreur sur une entite introuvable.
 */
public class EntityIntrouvableException extends FonctionnelleException {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de l'objet.
	 */
	public EntityIntrouvableException() {
		this("EntityIntrouvableException", null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 */
	public EntityIntrouvableException(String pMessage) {
		this(pMessage, null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pCause
	 */
	public EntityIntrouvableException(Throwable pCause) {
		this("EntityIntrouvableException", pCause);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 * @param pCause
	 */
	public EntityIntrouvableException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}