package com.banque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.dao.IDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IEntity;

/**
 * DAO abstrait. <br/>
 *
 * Fait un acces a une base de donnees en faisant usage d'une data source. <br/>
 *
 * La data source est declaree dans le fichier context.xml qui se trouve dans
 * src/main/webapp/META-INF. <br/>
 *
 * Le lien se fait dans le fichier src/main/webapp/WEB-INF/web.xml
 *
 * @param <T>
 *            Type de l'entite
 */
public abstract class AbstractDAO<T extends IEntity> implements IDAO<T> {
	private static final Logger LOG = LogManager.getLogger();
	private static DataSource dataSource;

	/**
	 * Constructeur de l'objet.
	 */
	protected AbstractDAO() {
		super();
	}

	/**
	 * Retourne le nom de la table.
	 *
	 * @return le nom de la table.
	 */
	protected abstract String getTableName();

	/**
	 * Retourne la clef primaire de la table.
	 *
	 * @return la clef primaire de la table.
	 */
	protected String getPkName() {
		return "id";
	}

	/**
	 * Retourne la liste des colonnes de la table
	 *
	 * @return la liste des colonnes de la table
	 */
	protected abstract String getAllColumnNames();

	/**
	 * Transforme un resultset en objet
	 *
	 * @param rs
	 *            un resultset
	 * @return un objet a partir du resultset
	 * @throws SQLException
	 *             si un probleme survient
	 */
	protected abstract T fromResultSet(ResultSet rs) throws SQLException;

	/**
	 * A la responsabilite de creer un statement qui servira pour l'insertion.
	 *
	 * @param pUneEntite
	 *            une entite a inserer
	 * @param connexion
	 *            une connexion
	 * @return un statement adapte a l'insertion
	 * @throws SQLException
	 *             si une erreur survient
	 */
	protected abstract PreparedStatement buildStatementForInsert(T pUneEntite, Connection connexion)
			throws SQLException;

	/**
	 * A la responsabilite de creer un statement qui servira pour la mise a jour.
	 *
	 * @param pUneEntite
	 *            une entite a mettre a jour
	 * @param connexion
	 *            une connexion
	 * @return un statement adapte a la mise a jour
	 * @throws SQLException
	 *             si une erreur survient
	 */
	protected abstract PreparedStatement buildStatementForUpdate(T pUneEntite, Connection connexion)
			throws SQLException;

	/**
	 * Gere la fin de la transaction sur l'objet Connection.
	 *
	 * @param pConnexionCreated
	 *            indique si la connection est nouvelle ou non
	 * @param pDoCommit
	 *            indique si il faut commiter ou pas
	 * @param pStatement
	 *            le statement
	 * @param pResultSet
	 *            le resultset
	 * @param pConnexion
	 *            la connection
	 */
	public static void handleTransaction(boolean pConnexionCreated, boolean pDoCommit, Statement pStatement,
			ResultSet pResultSet, Connection pConnexion) {
		if (pConnexionCreated && pConnexion != null) {
			Integer ch = Integer.valueOf(pConnexion.hashCode());
			if (pDoCommit) {
				AbstractDAO.LOG.trace("-- commit sur connexion {}", ch);
				try {
					if (!pConnexion.getAutoCommit()) {
						pConnexion.commit();
					}
				} catch (SQLException e) {
					AbstractDAO.LOG.error("Impossible de faire un commit sur {}", ch, e);
				}
			} else {
				AbstractDAO.LOG.warn("-- rollback sur connexion {}", ch);
				try {
					if (!pConnexion.getAutoCommit()) {
						pConnexion.rollback();
					}
				} catch (SQLException e) {
					AbstractDAO.LOG.error("Impossible de faire un rollback sur {}", ch, e);
				}
			}
		}
		if (pResultSet != null) {
			try {
				pResultSet.close();
			} catch (SQLException e) {
				AbstractDAO.LOG.error("Impossible de fermer le resultset!", e);
			}
		}
		if (pStatement != null) {
			try {
				pStatement.close();
			} catch (SQLException e) {
				AbstractDAO.LOG.error("Impossible de fermer le statement!", e);
			}
		}
		if (pConnexionCreated && pConnexion != null) {
			Integer ch = Integer.valueOf(pConnexion.hashCode());
			try {
				AbstractDAO.LOG.trace("-- Fermeture de la connexion {}", ch);
				pConnexion.close();
			} catch (SQLException e) {
				AbstractDAO.LOG.error("Impossible de fermer le connexion sur {}", ch, e);
			}
		}
	}

	@Override
	public T insert(T pUneEntite, Connection connexion) throws ExceptionDao {
		if (pUneEntite == null) {
			return null;
		}
		boolean doCommit = false;
		boolean connexionCreated = connexion == null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (connexionCreated) {
				connexion = this.getConnexion();
				connexion.setAutoCommit(false);
			}
			ps = this.buildStatementForInsert(pUneEntite, connexion);

			ps.execute();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				pUneEntite.setId(Integer.valueOf(rs.getInt(1)));
			}
			doCommit = true;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(connexionCreated, doCommit, ps, rs, connexion);
		}

		return pUneEntite;
	}

	@Override
	public T update(T pUneEntite, Connection connexion) throws ExceptionDao {
		if (pUneEntite == null) {
			return null;
		}
		if (pUneEntite.getId() == null) {
			throw new ExceptionDao("L'entite n'a pas d'ID");
		}
		boolean doCommit = false;
		boolean connexionCreated = connexion == null;

		PreparedStatement ps = null;
		try {
			if (connexionCreated) {
				connexion = this.getConnexion();
				connexion.setAutoCommit(false);
			}

			ps = this.buildStatementForUpdate(pUneEntite, connexion);
			ps.execute();
			doCommit = true;
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(connexionCreated, doCommit, ps, null, connexion);
		}

		return pUneEntite;
	}

	@Override
	public boolean delete(T pUneEntite, Connection connexion) throws ExceptionDao {
		if (pUneEntite == null) {
			return false;
		}
		if (pUneEntite.getId() == null) {
			throw new ExceptionDao("L'entite n'a pas d'ID");
		}
		boolean doCommit = false;
		boolean connexionCreated = connexion == null;
		PreparedStatement ps = null;
		try {
			if (connexionCreated) {
				connexion = this.getConnexion();
				connexion.setAutoCommit(false);
			}

			ps = connexion
					.prepareStatement("delete from " + this.getTableName() + " where " + this.getPkName() + "=?;");
			ps.setInt(1, pUneEntite.getId().intValue());

			ps.execute();
			doCommit = true;

		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(connexionCreated, doCommit, ps, null, connexion);
		}

		return doCommit;
	}

	@Override
	public T select(int pUneClef, Connection connexion) throws ExceptionDao {
		T result = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean connexionCreated = connexion == null;
		try {
			if (connexionCreated) {
				connexion = this.getConnexion();
			}
			ps = connexion.prepareStatement("select " + this.getAllColumnNames() + " from " + this.getTableName()
					+ " where " + this.getPkName() + "=?;");
			ps.setInt(1, pUneClef);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = this.fromResultSet(rs);
			}
		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(connexionCreated, true, ps, rs, connexion);
		}

		return result;
	}

	@Override
	public List<T> selectAll(String pAWhere, String pAnOrderBy, Connection connexion) throws ExceptionDao {
		List<T> result = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean connexionCreated = connexion == null;
		try {
			if (connexionCreated) {
				connexion = this.getConnexion();
			}

			StringBuilder request = new StringBuilder();
			request.append("select ").append(this.getAllColumnNames()).append(" from ");
			request.append(this.getTableName());
			if (pAWhere != null) {
				request.append(" where ");
				request.append(pAWhere);
			}
			if (pAnOrderBy != null) {
				request.append(" order by ");
				request.append(pAnOrderBy);
			}
			request.append(';');
			AbstractDAO.LOG.debug("Requete: {}", request.toString());
			ps = connexion.prepareStatement(request.toString());

			rs = ps.executeQuery();
			while (rs.next()) {
				T ce = this.fromResultSet(rs);
				result.add(ce);
			}

		} catch (Exception e) {
			throw new ExceptionDao(e);
		} finally {
			AbstractDAO.handleTransaction(connexionCreated, true, ps, rs, connexion);
		}

		return result;
	}

	/**
	 * Recupere une connexion
	 *
	 * @return une connexion
	 * @throws ExceptionDao
	 */
	@Override
	public final synchronized Connection getConnexion() throws ExceptionDao {
		if (AbstractDAO.dataSource == null) {
			AbstractDAO.LOG.debug("Creation de la data source");
			try {
				Context context = new InitialContext();
				AbstractDAO.dataSource = (DataSource) context.lookup("java:comp/env/" + IDAO.DATA_SRC_NAME);
			} catch (Exception e) {
				AbstractDAO.LOG
						.error("Impossible de charger la data source, verifiez votre fichier MERA-INF/context.xml", e);
				throw new ExceptionDao(e);
			}
		}

		try {
			@SuppressWarnings("squid:S2095")
			Connection c = AbstractDAO.dataSource.getConnection();
			AbstractDAO.LOG.trace("Recuperation de la connextion {}", Integer.valueOf(c.hashCode()));
			return c;
		} catch (Exception e) {
			AbstractDAO.LOG.error("Erreur lors de l'acces a la base", e);
			throw new ExceptionDao(e);
		}
	}

	/**
	 * Place les elements dans la requete.
	 *
	 * @param ps
	 *            la requete
	 * @param gaps
	 *            les elements
	 * @throws SQLException
	 *             si un des elements ne rentre pas
	 */
	protected static final void setPrepareStatement(PreparedStatement ps, List<?> gaps) throws SQLException {
		Iterator<?> iter = gaps.iterator();
		int id = 0;
		while (iter.hasNext()) {
			id++;
			Object lE = iter.next();
			if (lE == null) {
				continue;
			}
			if (lE instanceof String) {
				ps.setString(id, (String) lE);
			} else if (lE instanceof java.sql.Date) {
				ps.setDate(id, (java.sql.Date) lE);
			} else if (lE instanceof java.util.Date) {
				ps.setDate(id, new java.sql.Date(((java.util.Date) lE).getTime()));
			} else if (lE instanceof Timestamp) {
				ps.setTimestamp(id, (Timestamp) lE);
			} else if (lE instanceof Integer) {
				ps.setInt(id, ((Integer) lE).intValue());
			} else if (lE instanceof Double) {
				ps.setDouble(id, ((Double) lE).doubleValue());
			} else {
				throw new SQLException("Type invalid '" + lE.getClass().getSimpleName() + "'");
			}

		}
	}

}