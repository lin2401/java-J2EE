package com.banque.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.banque.dao.IOperationDAO;
import com.banque.dao.ex.ExceptionDao;
import com.banque.entity.IOperationEntity;
import com.banque.entity.impl.OperationEntity;

/**
 * Test sur la classe OperationDAO.
 */
public class OperationDAOTest {
	// Nos DAO sont "singleton", on peut donc faire du static et BeforeClass
	private static IOperationDAO operationDao;

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
		OperationDAOTest.operationDao = new OperationDAO();
	}

	/**
	 * Lance apres tous les tests. <br/>
	 */
	@AfterClass
	public static void after() {
		OperationDAOTest.operationDao = null;
	}

	/**
	 * Instancie une operation.
	 *
	 * @return un operation sans ID associe au compte 12.
	 */
	private IOperationEntity createOperation() {
		IOperationEntity uneOp1 = new OperationEntity();
		uneOp1.setCompteId(Integer.valueOf(12));
		uneOp1.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp1.setLibelle("Test-Op");
		uneOp1.setMontant(Double.valueOf(500D));
		return uneOp1;
	}

	/** Test. */
	@Test
	public void insertOk() throws ExceptionDao {
		IOperationEntity uneOp1 = this.createOperation();

		IOperationEntity uneOp2 = OperationDAOTest.operationDao.insert(uneOp1, null);
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp2);
		Assert.assertNotNull("L'operation doit avoir un id non null", uneOp2.getId());
		Assert.assertEquals("Les deux operations doivent avoir le meme libelle", uneOp1.getLibelle(),
				uneOp2.getLibelle());
	}

	/** Test. */
	@Test
	public void updateOk() throws ExceptionDao {
		IOperationEntity uneOp1 = this.createOperation();

		IOperationEntity uneOp2 = OperationDAOTest.operationDao.insert(uneOp1, null);

		final String p = "nouveau libelle";
		uneOp2.setLibelle(p);
		IOperationEntity uneOp3 = OperationDAOTest.operationDao.update(uneOp2, null);
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp2);
		Assert.assertNotNull("L'operation doit avoir un id non null", uneOp2.getId());
		Assert.assertEquals("Le libelle doit etre " + p, p, uneOp3.getLibelle());
		Assert.assertEquals("Les deux operations doivent avoir le meme libelle", uneOp2.getLibelle(),
				uneOp3.getLibelle());

	}

	/** Test. */
	@Test
	public void deleteOk() throws ExceptionDao {
		IOperationEntity uneOp1 = this.createOperation();

		IOperationEntity uneOp2 = OperationDAOTest.operationDao.insert(uneOp1, null);

		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp2);
		Assert.assertNotNull("L'operation doit avoir un id non null", uneOp2.getId());

		boolean resu = OperationDAOTest.operationDao.delete(uneOp2, null);
		Assert.assertTrue("L'operation a du etre supprime", resu);
	}

	/** Test. */
	@Test
	public void selectOk() throws ExceptionDao {
		IOperationEntity uneOp1 = OperationDAOTest.operationDao.select(1, null);
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp1);
		Assert.assertEquals("L'operation doit avoir un id = 1", 1, uneOp1.getId().intValue());

	}

	/** Test. */
	@Test
	public void selectAllOk() throws ExceptionDao {
		List<IOperationEntity> liste = OperationDAOTest.operationDao.selectAll("id=1", null, null);
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
		IOperationEntity uneOp1 = liste.get(0);
		Assert.assertNotNull("L'operation ne doit pas etre null", uneOp1);
		Assert.assertEquals("L'operation doit avoir un id = 1", 1, uneOp1.getId().intValue());
	}

	/** Test. */
	@Test
	public void selectCriteriaOk() throws ExceptionDao {
		List<IOperationEntity> liste = OperationDAOTest.operationDao.selectCriteria(12, null, null, null, null);
		Assert.assertNotNull("La liste ne doit pas etre null", liste);
		Assert.assertFalse("La liste ne doit pas etre vide", liste.isEmpty());
	}

}
