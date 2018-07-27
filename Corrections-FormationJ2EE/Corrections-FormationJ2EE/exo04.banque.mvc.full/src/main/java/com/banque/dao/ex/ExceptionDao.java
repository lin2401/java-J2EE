package com.banque.dao.ex;

import com.banque.service.ex.ErreurTechniqueException;

/**
 * Erreur propre au DAO.
 */
public class ExceptionDao extends ErreurTechniqueException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de l'objet.
	 */
	public ExceptionDao() {
		this("ExceptionDao", null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 */
	public ExceptionDao(String pMessage) {
		this(pMessage, null);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pCause
	 */
	public ExceptionDao(Throwable pCause) {
		this("ExceptionDao", pCause);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param pMessage
	 * @param pCause
	 */
	public ExceptionDao(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

}
