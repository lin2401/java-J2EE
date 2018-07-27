package com.banque.dao;

import java.sql.Connection;
import java.util.List;

import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IEntity;

/**
 * Interface mere de tous les DAO. <br/>
 *
 * La data source est declaree dans le fichier context.xml qui se trouve dans
 * src/main/webapp/META-INF. <br/>
 *
 * Le lien se fait dans le fichier src/main/webapp/WEB-INF/web.xml
 *
 * @param <T>
 *            Le type d'element
 */
public interface IDAO<T extends IEntity> {
	/** Nom de la data source. */
	public static final String DATA_SRC_NAME = "jdbc/netbankPool";

	/**
	 * Ajoute un element dans la base.
	 *
	 * @param pUneEntite
	 *            un element
	 * @param connexion
	 *            une connection
	 * @return l'objet ajoute
	 * @throws ExceptionDao
	 *             en cas de probleme
	 */
	public abstract T insert(T pUneEntite, Connection connexion) throws ExceptionDao;

	/**
	 * Met un element a jour dans la base.
	 *
	 * @param pUneEntite
	 *            un element
	 * @param connexion
	 *            une connection
	 * @return l'objet mis a jour
	 * @throws ExceptionDao
	 *             en cas de probleme
	 */
	public abstract T update(T pUneEntite, Connection connexion) throws ExceptionDao;

	/**
	 * Suprime un compte de la base.
	 *
	 * @param pUneEntite
	 *            un objet compte
	 * @param connexion
	 *            une connection
	 * @return true si il a ete supprime, false sinon
	 * @throws ExceptionDao
	 *             en cas de probleme
	 */
	public abstract boolean delete(T pUneEntite, Connection connexion) throws ExceptionDao;

	/**
	 * Selectionne un compte dans la base en fonction de sa clef.
	 *
	 * @param pUneClef
	 *            une clef
	 * @param connexion
	 *            une connection
	 * @return l'objet compte trouve, null sinon
	 * @throws ExceptionDao
	 *             en cas de probleme
	 */
	public abstract T select(int pUneClef, Connection connexion) throws ExceptionDao;

	/**
	 * Selectionne tous les comptes dans la base.
	 *
	 * @param pAWhere
	 *            une condition where (sans le mot clef where)
	 * @param pAnOrderBy
	 *            une condition d'order by (sans le mot clef order by)
	 * @param connexion
	 *            une connection
	 * @return la liste des comptes trouves, une liste vide sinon
	 * @throws ExceptionDao
	 *             en cas de probleme
	 */
	public abstract List<T> selectAll(String pAWhere, String pAnOrderBy, Connection connexion) throws ExceptionDao;

	/**
	 * Recupere une connexion
	 *
	 * @return une connexion
	 * @throws ExceptionDao
	 */
	public abstract Connection getConnexion() throws ExceptionDao;

}