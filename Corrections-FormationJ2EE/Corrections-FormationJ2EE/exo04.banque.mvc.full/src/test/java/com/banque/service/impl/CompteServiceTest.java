package com.banque.service.impl;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.entity.ICompteEntity;
import com.banque.service.ICompteService;
import com.banque.service.ex.AucunDroitException;
import com.banque.service.ex.EntityIntrouvableException;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;

/**
 * Test sur la classe ICompteService.
 */
public class CompteServiceTest {
	// Nos services sont "singleton", on peut donc faire du static et BeforeClass
	private static ICompteService compteService;

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
	 */
	@BeforeClass
	public static void before() {
		CompteServiceTest.compteService = new CompteService();
	}

	/**
	 * Lance apres tous les tests. <br/>
	 */
	@AfterClass
	public static void after() {
		CompteServiceTest.compteService = null;
	}

	/**
	 * Test.
	 */
	@Test
	public void selectOk() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		ICompteEntity cpt = CompteServiceTest.compteService.select(unUtilisateurId, unCompteId);
		Assert.assertNotNull("Le compte ne doit pas etre null", cpt);
		Assert.assertEquals("L'id du compte doit etre " + unCompteId, unCompteId, cpt.getId().intValue());
	}

	/**
	 * Test.
	 */
	@Test(expected = AucunDroitException.class)
	public void selectKo1() throws FonctionnelleException, ErreurTechniqueException {
		// il n'y a pas d'utilisateur 100
		final int unUtilisateurId = 100;
		// il y a un compte 12
		final int unCompteId = 12;
		CompteServiceTest.compteService.select(unUtilisateurId, unCompteId);
	}

	/**
	 * Test.
	 */
	@Test(expected = EntityIntrouvableException.class)
	public void selectKo2() throws FonctionnelleException, ErreurTechniqueException {
		// il y a un utilisateur 1
		final int unUtilisateurId = 1;
		// il n'y a pas de compte 12000
		final int unCompteId = 12000;
		CompteServiceTest.compteService.select(unUtilisateurId, unCompteId);
	}

	/**
	 * Test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void selectKo3() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = -1;
		final int unCompteId = 120;
		CompteServiceTest.compteService.select(unUtilisateurId, unCompteId);
	}

	/**
	 * Test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void selectKo4() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteId = -1;
		CompteServiceTest.compteService.select(unUtilisateurId, unCompteId);
	}

	/** Test. */
	@Test
	public void selectAllOk() throws FonctionnelleException, ErreurTechniqueException {
		// il y a un utilisateur 1
		final int unUtilisateurId = 1;
		List<ICompteEntity> liste = CompteServiceTest.compteService.selectAll(unUtilisateurId);
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		for (ICompteEntity compteEntity : liste) {
			Assert.assertNotNull("Le compte ne doit pas etre null", compteEntity);
			Assert.assertEquals("Le compte doit avoir un user id = " + unUtilisateurId, unUtilisateurId,
					compteEntity.getUtilisateurId().intValue());
		}

	}

	/**
	 * Test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void selectAllKo() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = -1;
		CompteServiceTest.compteService.selectAll(unUtilisateurId);
	}

}
