package com.banque.service.impl;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;
import com.banque.service.ex.MauvaisMotdepasseException;
import com.banque.service.ex.UtilisateurInconnuException;

/**
 * Test sur la classe IAuthentificationService.
 */
public class UtilisateurServiceTest {
	// Nos services sont "singleton", on peut donc faire du static et BeforeClass
	private static IAuthentificationService authentificationService;

	// -------------------------
	// Exemple, ne sert pas avec Logj2 qui va chercher le fichier log4j2-test.xml en
	// automatique
	// -------------------------
	// @BeforeClass
	// public static void initLog() {
	// System.setProperty("log4j.configurationFile", "log4j2-test.xml");
	// }

	/**
	 * Lance avant tous les tests. <br/>
	 * Initialise l'objet teste.
	 *
	 * @throws FonctionnelleException,
	 *             ErreurTechniqueException si un probleme survient.
	 */
	@BeforeClass
	public static void before() {
		UtilisateurServiceTest.authentificationService = new AuthentificationService();
	}

	/**
	 * Lance apres tous les tests. <br/>
	 */
	@AfterClass
	public static void after() {
		UtilisateurServiceTest.authentificationService = null;
	}

	/** Test. */
	@Test
	public void testAuthentifierOk() throws FonctionnelleException, ErreurTechniqueException {
		final String login = "df";
		final String pwd = "df";
		IUtilisateurEntity user = UtilisateurServiceTest.authentificationService.authentifier(login, pwd);
		Assert.assertNotNull("L'utilisateur ne doit pas etre null", user);
		Assert.assertEquals("Le login de l'utilisateur est " + login, login, user.getLogin());
	}

	/**
	 * Test.
	 */
	@Test(expected = UtilisateurInconnuException.class)
	public void testAuthentifierKo1() throws FonctionnelleException, ErreurTechniqueException {
		final String login = "dfd";
		final String pwd = "df";
		UtilisateurServiceTest.authentificationService.authentifier(login, pwd);
	}

	/**
	 * Test.
	 */
	@Test(expected = MauvaisMotdepasseException.class)
	public void testAuthentifierKo2() throws FonctionnelleException, ErreurTechniqueException {
		final String login = "df";
		final String pwd = "dfd";
		UtilisateurServiceTest.authentificationService.authentifier(login, pwd);
	}

	/**
	 * Test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAuthentifierKo3() throws FonctionnelleException, ErreurTechniqueException {
		final String login = null;
		final String pwd = "df";
		UtilisateurServiceTest.authentificationService.authentifier(login, pwd);
	}

	/**
	 * Test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAuthentifierKo4() throws FonctionnelleException, ErreurTechniqueException {
		final String login = "df";
		final String pwd = null;
		UtilisateurServiceTest.authentificationService.authentifier(login, pwd);
	}

	/**
	 * Test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAuthentifierKo5() throws FonctionnelleException, ErreurTechniqueException {
		final String login = "";
		final String pwd = "df";
		UtilisateurServiceTest.authentificationService.authentifier(login, pwd);
	}

	/**
	 * Test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAuthentifierKo6() throws FonctionnelleException, ErreurTechniqueException {
		final String login = "df";
		final String pwd = "";
		UtilisateurServiceTest.authentificationService.authentifier(login, pwd);
	}
}
