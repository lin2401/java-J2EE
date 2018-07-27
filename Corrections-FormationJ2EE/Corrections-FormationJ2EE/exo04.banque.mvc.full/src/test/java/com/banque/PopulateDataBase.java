package com.banque;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;

import com.banque.dao.ICompteDAO;
import com.banque.dao.IOperationDAO;
import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.impl.AbstractDAO;
import com.banque.dao.impl.CompteDAO;
import com.banque.dao.impl.OperationDAO;
import com.banque.dao.impl.UtilisateurDAO;
import com.banque.entity.ICompteEntity;
import com.banque.entity.IOperationEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.utils.BanqueFileReader;
import com.banque.utils.CompteGenerator;
import com.banque.utils.OperationGenerator;
import com.banque.utils.UtilisateurGenerator;

/**
 * Test qui va remplir la base de données avec des utilisateurs, des comptes,
 * des opérations.
 */
@Ignore
public class PopulateDataBase {
	private static final Logger LOG = LogManager.getLogger();
	private IOperationDAO operationDAO;
	private ICompteDAO compteDAO;
	private IUtilisateurDAO utilisateurDAO;

	/**
	 * Constructeur.
	 */
	public PopulateDataBase() {
		// Log4j2 configuration
		System.setProperty("log4j.configurationFile", "log4j2-test.xml");
		this.operationDAO = new OperationDAO();
		this.compteDAO = new CompteDAO();
		this.utilisateurDAO = new UtilisateurDAO();
	}

	/**
	 * Va generer des utilisateurs pour la base de données. <br/>
	 *
	 * @param nbUser
	 *            nombre d'utilisateur
	 * @param nbCpParUser
	 *            nombre de compte par utilisateur
	 * @param nbOpParCp
	 *            nombre d'operation par compte
	 * @throws Exception
	 *             si un probleme survient
	 */
	public void populateDataBase(int nbUser, int nbCpParUser, int nbOpParCp) throws Exception {

		Connection c = null;
		try {
			c = this.utilisateurDAO.getConnexion();
			c.setAutoCommit(false);
			List<IOperationEntity> lesOperations = new ArrayList<IOperationEntity>(nbCpParUser * nbOpParCp);
			List<IUtilisateurEntity> lesUtilisateurs = UtilisateurGenerator.generateUtilisateurs(nbUser);
			for (IUtilisateurEntity unUtilisateur : lesUtilisateurs) {
				// On insert l'utilisateur en premier
				unUtilisateur = this.utilisateurDAO.insert(unUtilisateur, c);
				Assert.assertNotNull("Id ne doit pas etre null", unUtilisateur.getId());
				List<ICompteEntity> lesComptes = CompteGenerator.generateComptes(nbCpParUser, unUtilisateur.getId());
				for (ICompteEntity unCp : lesComptes) {
					unCp = this.compteDAO.insert(unCp, c);
					Assert.assertNotNull("Id ne doit pas etre null", unCp.getId());
					lesOperations.addAll(OperationGenerator.generateOperations(nbOpParCp, unCp.getId()));
				}
				c.commit();
			}
			for (IOperationEntity uneOp : lesOperations) {
				uneOp = this.operationDAO.insert(uneOp, c);
				Assert.assertNotNull("Id ne doit pas etre null", uneOp.getId());
			}
		} finally {
			AbstractDAO.handleTransaction(true, true, null, null, c);
		}
	}

	/**
	 * Va generer un fichier CSV avec toutes les informations necessaires à JMeter
	 *
	 * @param where
	 *            ou ecrire le fichier
	 * @param separator
	 *            le separateur de colonne
	 * @throws Exception
	 *             si un probleme survient
	 */
	public void generateCsvForJMeter(String where, String separator) throws Exception {
		// Format : login;pwd;idcpt1;idcpt2
		List<String> lignes = new ArrayList<String>();
		Connection c = null;
		try {
			c = this.utilisateurDAO.getConnexion();
			List<IUtilisateurEntity> lesUtilisateurs = this.utilisateurDAO.selectAll(null, null, c);
			for (IUtilisateurEntity unUtilisateur : lesUtilisateurs) {
				StringBuilder buff = new StringBuilder();
				buff.append(unUtilisateur.getLogin()).append(separator);
				buff.append(unUtilisateur.getPassword()).append(separator);
				List<ICompteEntity> comptes = this.compteDAO.selectAll("utilisateurId=" + unUtilisateur.getId(), null,
						c);
				if (comptes != null && comptes.size() >= 2) {
					Collections.shuffle(comptes);
					buff.append(comptes.get(0).getId()).append(separator);
					buff.append(comptes.get(1).getId());
				} else {
					continue;
				}
				lignes.add(buff.toString());
			}
		} finally {
			AbstractDAO.handleTransaction(true, true, null, null, c);
		}
		// Ecriture du fichier
		Files.write(Paths.get(where), lignes, BanqueFileReader.CHARSET);
	}

	/**
	 * Va generer un fichier CSV avec toutes les informations necessaires à Gatling
	 *
	 * @param where
	 *            ou ecrire le fichier
	 * @param separator
	 *            le separateur de colonne
	 * @throws Exception
	 *             si un probleme survient
	 */
	public void generateCsvForGatling(String where, String separator) throws Exception {
		// Format : login,password,idcpt1,idcpt2
		List<String> lignes = new ArrayList<String>();
		Connection c = null;
		try {
			c = this.utilisateurDAO.getConnexion();
			List<IUtilisateurEntity> lesUtilisateurs = this.utilisateurDAO.selectAll(null, null, c);
			// La premiere ligne ddoit definir le nom des elements
			lignes.add("login" + separator + "password" + separator + "idcpt1" + separator + "idcpt2");
			for (IUtilisateurEntity unUtilisateur : lesUtilisateurs) {
				StringBuilder buff = new StringBuilder();
				buff.append(unUtilisateur.getLogin()).append(separator);
				buff.append(unUtilisateur.getPassword()).append(separator);
				List<ICompteEntity> comptes = this.compteDAO.selectAll("utilisateurId=" + unUtilisateur.getId(), null,
						c);
				if (comptes != null && comptes.size() >= 2) {
					Collections.shuffle(comptes);
					buff.append(comptes.get(0).getId()).append(separator);
					buff.append(comptes.get(1).getId());
				} else {
					continue;
				}
				lignes.add(buff.toString());
			}
		} finally {
			AbstractDAO.handleTransaction(true, true, null, null, c);
		}
		// Ecriture du fichier
		Files.write(Paths.get(where), lignes, BanqueFileReader.CHARSET);
	}

	/**
	 * Lancement.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		PopulateDataBase.LOG.warn("Remplissage de la base");
		final int nbUser = 100;
		final int nbCpParUser = 3;
		final int nbOpParCp = 10;
		PopulateDataBase pdb = new PopulateDataBase();
		try {
			pdb.populateDataBase(nbUser, nbCpParUser, nbOpParCp);
		} catch (Exception e) {
			PopulateDataBase.LOG.error("Erreur lors du remplissage de la base.", e);
		}

		final String separatorJMeter = ";";
		final String whereJMeter = "src/test/resources/data/info_jmeter.csv";
		try {
			pdb.generateCsvForJMeter(whereJMeter, separatorJMeter);
		} catch (Exception e) {
			PopulateDataBase.LOG.error("Erreur lors de la generation du fichier.", e);
		}

		final String separatorGatling = ","; // Utilise une , par defaut
		final String whereGatling = "src/test/resources/data/info_gatling.csv";
		try {
			pdb.generateCsvForGatling(whereGatling, separatorGatling);
		} catch (Exception e) {
			PopulateDataBase.LOG.error("Erreur lors de la generation du fichier.", e);
		}
	}
}
