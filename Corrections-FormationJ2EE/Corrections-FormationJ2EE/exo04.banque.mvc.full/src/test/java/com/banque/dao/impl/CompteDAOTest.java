package com.banque.dao.impl;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.dao.ICompteDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.ICompteEntity;
import com.banque.entity.impl.CompteEntity;

/**
 * Test sur la classe CompteDAO.
 */
public class CompteDAOTest {
	// Nos DAO sont "singleton", on peut donc faire du static et BeforeClass
	private static ICompteDAO compteDao;

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
		CompteDAOTest.compteDao = new CompteDAO();
	}

	/**
	 * Lance apres tous les tests. <br/>
	 */
	@AfterClass
	public static void after() {
		CompteDAOTest.compteDao = null;
	}

	/**
	 * Instancie un compte.
	 *
	 * @return un compte sans ID associe a l'utilisateur 1.
	 */
	private ICompteEntity createCompte() {
		ICompteEntity unCpt1 = new CompteEntity();
		unCpt1.setDecouvert(Double.valueOf(0D));
		unCpt1.setLibelle("Test-Cpt 01");
		unCpt1.setSolde(Double.valueOf(5000D));
		unCpt1.setTaux(Double.valueOf(0.01D));
		unCpt1.setUtilisateurId(Integer.valueOf(1));
		return unCpt1;
	}

	/**
	 * Test.
	 *
	 * @throws ExceptionDao
	 */
	@Test
	public void insertOk() throws ExceptionDao {
		ICompteEntity unCpt1 = this.createCompte();

		ICompteEntity unCpt2 = CompteDAOTest.compteDao.insert(unCpt1, null);
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt2);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt2.getId());
		Assert.assertEquals("Les deux comptes doivent avoir le meme libelle", unCpt1.getLibelle(), unCpt2.getLibelle());
	}

	/**
	 * Test.
	 *
	 * @throws ExceptionDao
	 */
	@Test
	public void updateOk() throws ExceptionDao {
		ICompteEntity unCpt1 = this.createCompte();

		ICompteEntity unCpt2 = CompteDAOTest.compteDao.insert(unCpt1, null);

		final String p = "nouveau libelle";
		unCpt2.setLibelle(p);
		ICompteEntity unCpt3 = CompteDAOTest.compteDao.update(unCpt2, null);
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt2);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt2.getId());
		Assert.assertEquals("Les deux comptes doivent avoir le meme libelle", unCpt1.getLibelle(), unCpt2.getLibelle());
		Assert.assertEquals("Le libelle doit etre " + p, p, unCpt3.getLibelle());
	}

	/**
	 * Test.
	 *
	 * @throws ExceptionDao
	 */
	@Test
	public void deleteOk() throws ExceptionDao {
		ICompteEntity unCpt1 = this.createCompte();

		ICompteEntity unCpt2 = CompteDAOTest.compteDao.insert(unCpt1, null);

		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt2);
		Assert.assertNotNull("Le compte doit avoir un id non null", unCpt2.getId());
		boolean resu = CompteDAOTest.compteDao.delete(unCpt2, null);
		Assert.assertTrue("Le compte a du etre supprime", resu);
	}

	/**
	 * Test.
	 *
	 * @throws ExceptionDao
	 */
	@Test
	public void selectOk() throws ExceptionDao {
		ICompteEntity unCpt1 = CompteDAOTest.compteDao.select(12, null);
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt1);
		Assert.assertEquals("Le compte doit avoir un id = 12", 12, unCpt1.getId().intValue());
	}

	/**
	 * Test.
	 *
	 * @throws ExceptionDao
	 */
	@Test
	public void selectAllOk() throws ExceptionDao {
		List<ICompteEntity> liste = CompteDAOTest.compteDao.selectAll("id=12", null, null);
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		ICompteEntity unCpt1 = liste.get(0);
		Assert.assertNotNull("Le compte ne doit pas etre null", unCpt1);
		Assert.assertEquals("Le compte doit avoir un id = 12", 12, unCpt1.getId().intValue());
	}

}
