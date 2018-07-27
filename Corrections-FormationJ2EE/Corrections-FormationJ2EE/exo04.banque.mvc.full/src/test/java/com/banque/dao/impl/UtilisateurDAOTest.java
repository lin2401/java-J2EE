package com.banque.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.dao.IUtilisateurDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.ESex;
import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.impl.UtilisateurEntity;

/**
 * Test sur la classe UtilisateurDAO.
 */
public class UtilisateurDAOTest {
	// Nos DAO sont "singleton", on peut donc faire du static et BeforeClass
	private static IUtilisateurDAO utilisateurDao;

	// -------------------------
	// Exemple, ne sert pas avec Log4j2 qui va chercher le fichier log4j2-test.xml
	// en automatique
	// -------------------------
	// @BeforeClass
	// public static void initLog() {
	// System.setProperty("log4j.configurationFile", "log4j2-test.xml");
	// }

	/**
	 * Lance avant tous les tests. <br/>
	 * Initialise l'objet teste.
	 */
	@BeforeClass
	public static void before() {
		UtilisateurDAOTest.utilisateurDao = new UtilisateurDAO();
	}

	/**
	 * Lance apres tous les tests. <br/>
	 */
	@AfterClass
	public static void after() {
		UtilisateurDAOTest.utilisateurDao = null;
	}

	/**
	 * Instancie un utilisateur.
	 *
	 * @return un utilisateur sans ID.
	 */
	private IUtilisateurEntity createUtilisateur() {
		IUtilisateurEntity unUt1 = new UtilisateurEntity();
		unUt1.setLogin("login-test");
		unUt1.setNom("Smith-test");
		unUt1.setPrenom("Jhon-test");
		unUt1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unUt1.setPassword("bonjour");
		unUt1.setSex(ESex.HOMME);
		unUt1.setAdresse("Quelque part dans le test");
		unUt1.setCodePostal(Integer.valueOf(78000));
		unUt1.setDateDeNaissance(1, 1, 1988);
		unUt1.setTelephone("0148759678");
		return unUt1;
	}

	/** Test. */
	@Test
	public void insertOk() throws ExceptionDao {
		IUtilisateurEntity unUt1 = this.createUtilisateur();

		IUtilisateurEntity unUt2 = UtilisateurDAOTest.utilisateurDao.insert(unUt1, null);
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt2);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt2.getId());
		Assert.assertEquals("Les deux utilisateurs doivent avoir le meme nom", unUt1.getNom(), unUt2.getNom());
	}

	/** Test. */
	@Test
	public void updateOk() throws ExceptionDao {
		IUtilisateurEntity unUt1 = this.createUtilisateur();

		IUtilisateurEntity unUt2 = UtilisateurDAOTest.utilisateurDao.insert(unUt1, null);

		final String p = "nouveau prénom";
		unUt2.setPrenom(p);
		IUtilisateurEntity unUt3 = UtilisateurDAOTest.utilisateurDao.update(unUt2, null);
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt3);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt3.getId());
		Assert.assertEquals("Les deux utilisateurs doivent avoir le meme id", unUt2.getId(), unUt3.getId());
		Assert.assertEquals("Les deux utilisateurs doivent avoir le meme nom", unUt2.getNom(), unUt3.getNom());
		Assert.assertEquals("Le prenom doit etre " + p, p, unUt3.getPrenom());
		Assert.assertEquals("Les deux utilisateurs doivent avoir un prenom different", unUt2.getPrenom(),
				unUt3.getPrenom());
	}

	/** Test. */
	@Test
	public void deleteOk() throws ExceptionDao {
		IUtilisateurEntity unUt1 = this.createUtilisateur();

		IUtilisateurEntity unUt2 = UtilisateurDAOTest.utilisateurDao.insert(unUt1, null);

		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt2);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt2.getId());

		boolean resu = UtilisateurDAOTest.utilisateurDao.delete(unUt2, null);
		Assert.assertTrue("L'utilisateur a du etre supprime", resu);
	}

	/** Test. */
	@Test
	public void selectOk() throws ExceptionDao {
		final String login = "df";
		IUtilisateurEntity unUt1 = UtilisateurDAOTest.utilisateurDao.selectLogin(login, null);
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());
		Assert.assertEquals("L'utilisateur doit avoir un login =" + login, login, unUt1.getLogin());
	}

	/** Test. */
	@Test
	public void selectAllOk() throws ExceptionDao {
		final String name = "Fargis";
		List<IUtilisateurEntity> liste = UtilisateurDAOTest.utilisateurDao.selectAll("nom='" + name + "'", null, null);
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		IUtilisateurEntity unUt1 = liste.get(0);
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", unUt1);
		Assert.assertNotNull("L'utilisateur doit avoir un id non null", unUt1.getId());
		Assert.assertEquals("L'utilisateur doit avoir un name = " + name, name, unUt1.getNom());
	}
}
