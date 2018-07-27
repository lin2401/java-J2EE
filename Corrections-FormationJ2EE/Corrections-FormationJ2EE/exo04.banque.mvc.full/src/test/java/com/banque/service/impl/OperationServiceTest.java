package com.banque.service.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.entity.IOperationEntity;
import com.banque.service.IOperationService;
import com.banque.service.ex.AucunDroitException;
import com.banque.service.ex.DecouvertException;
import com.banque.service.ex.EntityIntrouvableException;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;

/**
 * Test sur la classe IOperationService.
 */
public class OperationServiceTest {
	// Nos services sont "singleton", on peut donc faire du static et BeforeClass
	private static IOperationService operationService;

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
		OperationServiceTest.operationService = new OperationService();
	}

	/**
	 * Lance apres tous les tests. <br/>
	 */
	@AfterClass
	public static void after() {
		OperationServiceTest.operationService = null;
	}

	/** Test. */
	@Test
	public void selectOk() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		final int uneOperationId = 2;
		IOperationEntity op = OperationServiceTest.operationService.select(unUtilisateurId, unCompteId, uneOperationId);
		Assert.assertNotNull("L'operation ne doit pas etre null", op);
		Assert.assertEquals("L'id de l'operation doit etre " + uneOperationId, op.getId().intValue(), uneOperationId);
		Assert.assertEquals("L'id du compte de l'operation doit etre " + unCompteId, op.getCompteId().intValue(),
				unCompteId);
	}

	/**
	 * Test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void selectKo1() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = -1;
		final int unCompteId = 12;
		final int uneOperationId = 2;
		OperationServiceTest.operationService.select(unUtilisateurId, unCompteId, uneOperationId);
	}

	/**
	 * Test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void selectKo2() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteId = -12;
		final int uneOperationId = 2;
		OperationServiceTest.operationService.select(unUtilisateurId, unCompteId, uneOperationId);
	}

	/**
	 * Test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void selectKo3() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		final int uneOperationId = -2;
		OperationServiceTest.operationService.select(unUtilisateurId, unCompteId, uneOperationId);
	}

	/**
	 * Test.
	 */
	@Test(expected = AucunDroitException.class)
	public void selectKo4() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		// Le cpt 14 n'appartient pas au user 1
		final int unCompteId = 14;
		final int uneOperationId = 2;
		OperationServiceTest.operationService.select(unUtilisateurId, unCompteId, uneOperationId);
	}

	/**
	 * Test.
	 */
	@Test(expected = EntityIntrouvableException.class)
	public void selectKo5() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		final int uneOperationId = 2000000;
		OperationServiceTest.operationService.select(unUtilisateurId, unCompteId, uneOperationId);
	}

	/** Test. */
	@Test
	public void selectAllOk() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		List<IOperationEntity> liste = OperationServiceTest.operationService.selectAll(unUtilisateurId, unCompteId);
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		for (IOperationEntity uneEntity : liste) {
			Assert.assertNotNull("L'operation ne doit pas etre null", uneEntity);
			Assert.assertEquals("L'operation doit avoir un compte id = " + unCompteId, unCompteId,
					uneEntity.getCompteId().intValue());
		}
	}

	/**
	 * Test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void selectAllKo() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = -1;
		final int unCompteId = 12;
		OperationServiceTest.operationService.selectAll(unUtilisateurId, unCompteId);
	}

	/**
	 * Test.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void selectAllKo2() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteId = -12;
		OperationServiceTest.operationService.selectAll(unUtilisateurId, unCompteId);
	}

	/**
	 * Test.
	 */
	@Test(expected = AucunDroitException.class)
	public void selectAllKo3() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		// Le cpt 14 n'appartient pas au user 1
		final int unCompteId = 14;
		OperationServiceTest.operationService.selectAll(unUtilisateurId, unCompteId);
	}

	/**
	 * Test.
	 */
	@Test(expected = EntityIntrouvableException.class)
	public void selectAllKo4() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteId = 140000;
		OperationServiceTest.operationService.selectAll(unUtilisateurId, unCompteId);
	}

	/** Test. */
	@Test
	public void selectCritereOk() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		List<IOperationEntity> liste = OperationServiceTest.operationService.selectCritere(unUtilisateurId, unCompteId,
				null, null, true, false);
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		for (IOperationEntity uneEntity : liste) {
			Assert.assertNotNull("L'operation ne doit pas etre null", uneEntity);
			Assert.assertEquals("L'operation doit avoir un compte id = " + unCompteId, unCompteId,
					uneEntity.getCompteId().intValue());
			Assert.assertNotNull("L'operation doit avoir un montant", uneEntity.getMontant());
			Assert.assertTrue("L'operation doit etre un credit", uneEntity.getMontant().doubleValue() > 0);
		}
	}

	/** Test. */
	@Test
	public void selectCritereOk2() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteId = 12;
		final java.util.Date debut, fin;
		GregorianCalendar calendar = new GregorianCalendar();
		fin = calendar.getTime();
		calendar.set(Calendar.YEAR, 2014);
		debut = calendar.getTime();
		List<IOperationEntity> liste = OperationServiceTest.operationService.selectCritere(unUtilisateurId, unCompteId,
				debut, fin, false, true);
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		for (IOperationEntity uneEntity : liste) {
			Assert.assertNotNull("L'operation ne doit pas etre null", uneEntity);
			Assert.assertEquals("L'operation doit avoir un compte id = " + unCompteId, unCompteId,
					uneEntity.getCompteId().intValue());
			Assert.assertNotNull("L'operation doit avoir un montant", uneEntity.getMontant());
			Assert.assertTrue("L'operation doit etre un debit", uneEntity.getMontant().doubleValue() < 0);
			Assert.assertTrue("L'operation doit avoir une date apres debut", uneEntity.getDate().after(debut));
			Assert.assertTrue("L'operation doit avoir une date avant fin", uneEntity.getDate().before(fin));
		}
	}

	/** Test. */
	@Test
	public void faireVirementOk() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteSrcId = 12;
		final int unCompteDestId = 15;
		final double unMontant = 10d;
		List<IOperationEntity> liste = OperationServiceTest.operationService.faireVirement(unUtilisateurId,
				unCompteSrcId, unCompteDestId, unMontant);
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		Assert.assertEquals("La liste doit avoir deux elements ", 2, liste.size());
		for (IOperationEntity uneEntity : liste) {
			Assert.assertNotNull("L'operation ne doit pas etre null", uneEntity);
			Assert.assertNotNull("L'operation doit avoir un compte id non null", uneEntity.getCompteId());
			Assert.assertTrue("L'operation doit avoir un compte id = " + unCompteSrcId + " ou = " + unCompteDestId,
					uneEntity.getCompteId().intValue() == unCompteSrcId
							|| uneEntity.getCompteId().intValue() == unCompteDestId);
			Assert.assertNotNull("L'operation doit avoir un montant", uneEntity.getMontant());
			Assert.assertTrue("L'operation doit avoir un montant = " + unMontant,
					Math.abs(uneEntity.getMontant().doubleValue()) == unMontant);
		}
	}

	/**
	 * Test. Sur les mêmes comptes
	 */
	@Test(expected = IllegalArgumentException.class)
	public void faireVirementKo() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteSrcId = 12;
		final int unCompteDestId = 12;
		final double unMontant = 10d;
		OperationServiceTest.operationService.faireVirement(unUtilisateurId, unCompteSrcId, unCompteDestId, unMontant);
	}

	/**
	 * Test. Avec un compte qui n'appartient pas à l'utilisateur.
	 */
	@Test(expected = AucunDroitException.class)
	public void faireVirementKo2() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteSrcId = 12;
		final int unCompteDestId = 16;
		final double unMontant = 10d;
		OperationServiceTest.operationService.faireVirement(unUtilisateurId, unCompteSrcId, unCompteDestId, unMontant);
	}

	/**
	 * Test. Avec un montant negatif
	 */
	@Test(expected = IllegalArgumentException.class)
	public void faireVirementKo3() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 1;
		final int unCompteSrcId = 12;
		final int unCompteDestId = 15;
		final double unMontant = -10d;
		OperationServiceTest.operationService.faireVirement(unUtilisateurId, unCompteSrcId, unCompteDestId, unMontant);
	}

	/**
	 * Test. Avec un compte a seuil
	 */
	@Test(expected = DecouvertException.class)
	public void faireVirementKo4() throws FonctionnelleException, ErreurTechniqueException {
		final int unUtilisateurId = 2;
		final int unCompteSrcId = 13;
		final int unCompteDestId = 16;
		final double unMontant = 1000000d;
		OperationServiceTest.operationService.faireVirement(unUtilisateurId, unCompteSrcId, unCompteDestId, unMontant);
	}

}
