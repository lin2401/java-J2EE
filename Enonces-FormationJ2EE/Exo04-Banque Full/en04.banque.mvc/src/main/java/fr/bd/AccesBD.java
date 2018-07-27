package fr.bd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.CompteASeuil;
import fr.banque.CompteASeuilRemunere;
import fr.banque.CompteRemunere;
import fr.banque.Operation;

/**
 * Cette classe fait un acces a une base de donnees. <br/>
 * Cette classe est un <a href="https://fr.wikipedia.org/wiki/Singleton_(patron_de_conception)">singleton</a>. <br/>
 *
 * Les informations relatives à l'accès de la base de données sont dans le fichier <b>src\main\webapp\META-INF\context.xml</b>. <br/>
 * <br/>
 * Exemple d'utilisation : <br/>
 *
 * <pre>
 * AccesBD.getInstance().authentifier("df", "df");
 * </pre>
 *
 */
public final class AccesBD {
	/** Nom de la data source. */
	public static final String DATA_SRC_NAME = "jdbc/netbankPool";
	private static AccesBD instance;

	private volatile DataSource dataSource;

	/**
	 * Constructeur private car singleton.
	 */
	private AccesBD() {
		super();
		// Singleton
	}

	/**
	 * Donne l'instance du singleton.
	 *
	 * @return l'instance du singleton.
	 */
	public static synchronized AccesBD getInstance() {
		if (AccesBD.instance == null) {
			AccesBD.instance = new AccesBD();
		}
		return AccesBD.instance;
	}

	/**
	 * Recupere une connexion
	 *
	 * @return une connexion
	 * @throws SQLException
	 *             si un probleme survient
	 */
	public final Connection getConnexion() throws SQLException {
		if (this.dataSource == null) {
			try {
				Context context = new InitialContext();
				this.dataSource = (DataSource) context.lookup("java:comp/env/" + AccesBD.DATA_SRC_NAME);
			} catch (Exception e) {
				throw new SQLException(e);
			}
		}

		return this.dataSource.getConnection();
	}

	/**
	 * Methode qui verifie que le login et le password vont bien ensemble. <br/>
	 *
	 * @param unLogin
	 *            un login
	 * @param unMdp
	 *            un mot de passe
	 * @return
	 * 		<ul>
	 *         <li>-1: si un probleme provient du login</li>
	 *         <li>-2: si un probleme provient du mot de passe</li>
	 *         <li>l'id du client si tout se passe bien</li>
	 *         </ul>
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public int authentifier(String unLogin, String unMdp) throws SQLException {
		Connection cxt = this.getConnexion();
		if (!cxt.isClosed()) {
			PreparedStatement request = null;
			ResultSet resultat = null;
			try {
				// Creation de l'objet de requete
				request = cxt.prepareStatement("select id, password from utilisateur where login=?");
				request.setString(1, unLogin);

				// Envoie de la requete et recuperation du resultat
				resultat = request.executeQuery();

				// Parcours du resultat, toujours commencer par un .next
				while (resultat.next()) {
					int id = resultat.getInt("id");
					boolean noid = resultat.wasNull();
					String password = resultat.getString("password");
					if (noid) {
						return -1;
					}
					return password == unMdp || password.equals(unMdp) ? id : -2;
				}
				return -1;
			} finally {
				this.closeAll(cxt, request, resultat);
			}
		} else {
			throw new SQLException("Connexion invalide!");
		}
	}

	/**
	 * Methode qui sélectionne un utilisateur en fonction de son ID. <br/>
	 *
	 * @param unId
	 *            un id
	 * @return
	 * 		<ul>
	 *         <li>l'utilisateur trouve</li>
	 *         <li>lève une exception sinon</li>
	 *         </ul>
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public Client selectClient(int unId) throws SQLException {
		Connection cxt = this.getConnexion();
		if (!cxt.isClosed()) {
			PreparedStatement request = null;
			ResultSet resultat = null;
			try {
				// Creation de l'objet de requete
				request = cxt.prepareStatement("select * from utilisateur where id=?");
				request.setInt(1, unId);

				// Envoie de la requete et recuperation du resultat
				resultat = request.executeQuery();

				// Parcours du resultat, toujours commencer par un .next
				if (resultat.next()) {
					Client client = new Client();
					int id = resultat.getInt("id");
					java.sql.Date date = resultat.getDate("dateDeNaissance");
					String nom = resultat.getString("nom");
					String prenom = resultat.getString("prenom");
					client.setNumero(id);
					client.setNom(nom);
					client.setPrenom(prenom);
					if (date != null) {
						GregorianCalendar calendar = new GregorianCalendar();
						int yearNow = calendar.get(Calendar.YEAR);
						calendar.setTime(date);
						int yearBd = calendar.get(Calendar.YEAR);
						int age = yearNow - yearBd;
						client.setAge(age);
					}
					return client;
				} else {
					throw new SQLException("Pas d'utilisateur ayant l'id " + unId);
				}
			} finally {
				this.closeAll(cxt, request, resultat);
			}
		} else {
			throw new SQLException("Connexion invalide!");
		}
	}

	/**
	 * Ferme tout quoi qu'il arrive.
	 *
	 * @param ctx
	 *            une connexion
	 * @param request
	 *            un statement
	 * @param resultat
	 *            le resultset
	 */
	private final void closeAll(Connection ctx, Statement request, ResultSet resultat) {
		// Tres IMPORTANT, on ferme tout
		if (resultat != null) {
			try {
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (request != null) {
			try {
				request.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ctx != null) {
			try {
				ctx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Methode qui recupere toutes les operations d'un compte.
	 *
	 * @param unCompteId
	 *            un id de compte
	 * @return la liste de ses operations, une liste vide si aucune
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public List<Operation> selectOperation(int unCompteId) throws SQLException {
		return this.selectOperation(unCompteId, null, null, null);
	}

	/**
	 * Methode qui recupere les operations d'un compte en fonction des criteres
	 * indiques.
	 *
	 * @param unCompteId
	 *            un id de compte
	 * @param dateDeb
	 *            date debut
	 * @param dateFin
	 *            date fin
	 * @param creditDebit
	 *            TRUE = credit, FALSE = debit, null = les deux
	 * @return la liste de ses operations, une liste vide si aucune
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public List<Operation> selectOperation(int unCompteId, Date dateDeb, Date dateFin, Boolean creditDebit)
			throws SQLException {
		List<Operation> listeOperation = new ArrayList<Operation>();
		Connection cxt = this.getConnexion();
		if (!cxt.isClosed()) {
			PreparedStatement request = null;
			ResultSet resultat = null;
			try {
				StringBuilder requete = new StringBuilder();
				requete.append("select * from operation where compteId=?");
				if (dateDeb != null) {
					requete.append(" and date >= ?");
				}
				if (dateFin != null) {
					// Probleme sur la date de fin car MySQL a des dates en
					// 2016-08-26 18:38:22
					// Mais nous on lui donne des date en 2016-08-26 00:00:00
					// Du coup, on doit gerer la date de fin en faisant +1 jour
					// Le < (et pas <=) evite d'avoir l'operation du lendemain a
					// 00:00:00
					requete.append(" and date < DATE_ADD(?, INTERVAL 1 DAY)");
				}
				if (creditDebit != null) {
					if (creditDebit.booleanValue()) {
						requete.append(" and montant >= 0");
					} else {
						requete.append(" and montant <= 0");
					}
				}
				requete.append(" order by date desc");
				// Recuperation de tous les clients
				request = cxt.prepareStatement(requete.toString());
				int idParam = 1;
				request.setInt(idParam, unCompteId);
				idParam++;
				if (dateDeb != null) {
					request.setDate(idParam, dateDeb);
					idParam++;
				}
				if (dateFin != null) {
					request.setDate(idParam, dateFin);
					idParam++;
				}
				resultat = request.executeQuery();
				while (resultat.next()) {
					int id = resultat.getInt("id");
					String libelle = resultat.getString("libelle");
					double montant = resultat.getDouble("montant");
					Timestamp date = resultat.getTimestamp("date");
					int compteId = resultat.getInt("compteId");
					listeOperation.add(new Operation(id, libelle, montant, date, compteId));
				}

			} finally {
				this.closeAll(cxt, request, resultat);
			}
		} else {
			throw new SQLException("Connexion invalide!");
		}
		return listeOperation;
	}

	/**
	 * Methode qui recupere tous les comptes d'un utilisateur.
	 *
	 * @param unUtilisateurId
	 *            un id d'utilisateur
	 * @return la liste de ses comptes, une liste vide si aucun
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public List<Compte> selectCompte(Integer unUtilisateurId) throws SQLException {
		List<Compte> listeCompte = new ArrayList<Compte>();
		Connection cxt = this.getConnexion();
		if (!cxt.isClosed()) {
			PreparedStatement request = null;
			ResultSet resultat = null;
			try {
				// Recuperation de tous les clients
				request = cxt.prepareStatement("select * from compte where utilisateurId=?");
				request.setInt(1, unUtilisateurId.intValue());
				resultat = request.executeQuery();
				while (resultat.next()) {
					int id = resultat.getInt("id");
					// String libelle = resultat.getString("libelle");
					double solde = resultat.getDouble("solde");
					double decouvert = resultat.getDouble("decouvert");
					// Par defaut, le getDouble renvoie 0 si la valeur est null
					// Si on veut tester le null il faut faire
					boolean pasDeDecouver = resultat.wasNull();
					double taux = resultat.getDouble("taux");
					boolean pasDeTaux = resultat.wasNull();
					Compte cpt = null;
					if (pasDeDecouver && pasDeTaux) {
						cpt = new Compte(id, solde);
					} else if (pasDeDecouver && !pasDeTaux) {
						cpt = new CompteRemunere(id, solde, taux);
					} else if (!pasDeDecouver && pasDeTaux) {
						cpt = new CompteASeuil(id, solde, decouvert);
					} else {
						cpt = new CompteASeuilRemunere(id, solde, taux, decouvert);
					}
					cpt.setLibelle(resultat.getString("libelle"));
					listeCompte.add(cpt);
				}

			} finally {
				this.closeAll(cxt, request, resultat);
			}
		} else {
			throw new SQLException("Connexion invalide!");
		}
		return listeCompte;
	}

	/**
	 * Effectue un virement entre deux comptes.
	 *
	 * @param cptSrc
	 *            le compte source
	 * @param cptDest
	 *            le compte destination
	 * @param unMontant
	 *            le montant qui sera retire du compte source et ajoute au compte
	 *            destination
	 * @throws SQLException
	 *             si une erreur survient
	 */
	public void faireVirement(Integer cptSrc, Integer cptDest, Double unMontant) throws SQLException {
		Connection cxt = this.getConnexion();
		if (!cxt.isClosed()) {
			cxt.setAutoCommit(false);
			PreparedStatement request = null;
			try {
				// Recuperation de tous les clients
				request = cxt.prepareStatement("update compte set solde=(solde-?) where id=?");
				request.setDouble(1, unMontant.doubleValue());
				request.setInt(2, cptSrc.intValue());
				request.executeUpdate();
				request.close();
				request = cxt.prepareStatement("update compte set solde=(solde+?) where id=?");
				request.setDouble(1, unMontant.doubleValue());
				request.setInt(2, cptDest.intValue());
				request.executeUpdate();
				request.close();
				request = cxt.prepareStatement(
						"insert into operation (libelle, montant, date, compteId) values ('Virement',?,NOW(),?)");
				request.setDouble(1, -unMontant.doubleValue());
				request.setInt(2, cptSrc.intValue());
				request.executeUpdate();
				request.close();
				request = cxt.prepareStatement(
						"insert into operation (libelle, montant, date, compteId) values ('Virement',?,NOW(),?)");
				request.setDouble(1, unMontant.doubleValue());
				request.setInt(2, cptDest.intValue());
				request.executeUpdate();
				cxt.commit();
			} catch (SQLException sql) {
				cxt.rollback();
				// On la relance
				throw sql;
			} finally {
				cxt.setAutoCommit(true);
				this.closeAll(cxt, request, null);
			}
		} else {
			throw new SQLException("Connexion invalide!");
		}
	}

}