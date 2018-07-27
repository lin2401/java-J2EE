package com.banque.entity.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.banque.entity.ICompteEntity;
import com.banque.entity.IOperationEntity;
import com.banque.entity.IUtilisateurEntity;

/**
 * Test sur la classe ICompteEntity.
 */
public class CompteEntityTest {
	// Nos entités ne sont pas "singleton", donc pas de static ni de BeforeClass
	private ICompteEntity cpt1;

	@Before
	public void init() {
		this.cpt1 = new CompteEntity(Integer.valueOf(5));
		this.cpt1.setDecouvert(Double.valueOf(0D));
		this.cpt1.setLibelle("Cpt 01");
		this.cpt1.setSolde(Double.valueOf(5000D));
		this.cpt1.setTaux(Double.valueOf(0.01D));
		this.cpt1.setUtilisateurId(Integer.valueOf(1));
	}

	/** Test. */
	@Test
	public void testEqualsOk() {
		ICompteEntity unCpt2 = new CompteEntity();
		unCpt2.setId(Integer.valueOf(5));
		unCpt2.setDecouvert(Double.valueOf(0D));
		unCpt2.setLibelle("Cpt 01");
		unCpt2.setSolde(Double.valueOf(5000D));
		unCpt2.setTaux(Double.valueOf(0.01D));
		unCpt2.setUtilisateurId(Integer.valueOf(1));

		Assert.assertEquals("Les deux comptes sont egaux", this.cpt1, unCpt2);
		Assert.assertEquals("Les deux comptes sont egaux", unCpt2, this.cpt1);
	}

	/** Test. */
	@Test
	public void testEqualsOk2() {
		ICompteEntity unCpt2 = null;
		Assert.assertNotEquals("Les deux comptes ne sont pas egaux", this.cpt1, unCpt2);
	}

	/** Test. */
	@Test
	public void testEqualsOk3() {
		ICompteEntity unCpt2 = this.cpt1;
		Assert.assertEquals("Les deux comptes sont egaux", this.cpt1, unCpt2);
		Assert.assertEquals("Les deux comptes sont egaux", unCpt2, this.cpt1);
	}

	/** Test. */
	@Test
	public void testEqualsKo() {
		ICompteEntity unCpt2 = new CompteEntity();
		unCpt2.setId(Integer.valueOf(15));
		unCpt2.setDecouvert(Double.valueOf(0D));
		unCpt2.setLibelle("Cpt 01");
		unCpt2.setSolde(Double.valueOf(5000D));
		unCpt2.setTaux(Double.valueOf(0.01D));
		unCpt2.setUtilisateurId(Integer.valueOf(1));

		Assert.assertNotEquals("Les deux comptes sont differents", this.cpt1, unCpt2);
		Assert.assertNotEquals("Les deux comptes sont differents", unCpt2, this.cpt1);
	}

	/** Test. */
	@Test
	public void testEqualsKo1() {
		IUtilisateurEntity unObj = new UtilisateurEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un compte n'est pas un utilisateur", this.cpt1, unObj);
		Assert.assertNotEquals("Un utilisateur n'est pas un compte", unObj, this.cpt1);
	}

	/** Test. */
	@Test
	public void testEqualsKo2() {
		IOperationEntity unObj = new OperationEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un compte n'est pas une operation", this.cpt1, unObj);
		Assert.assertNotEquals("Une operation n'est pas un compte", unObj, this.cpt1);
	}

	/** Test. */
	@Test
	public void testHashcodeOk() {
		ICompteEntity unCpt2 = new CompteEntity();
		unCpt2.setId(Integer.valueOf(5));
		unCpt2.setDecouvert(Double.valueOf(0D));
		unCpt2.setLibelle("Cpt 01");
		unCpt2.setSolde(Double.valueOf(5000D));
		unCpt2.setTaux(Double.valueOf(0.01D));
		unCpt2.setUtilisateurId(Integer.valueOf(1));

		Assert.assertEquals("Les deux comptes ont le meme hashcode", this.cpt1.hashCode(), unCpt2.hashCode());
	}

	/** Test. */
	@Test
	public void testHashcodeKo() {
		ICompteEntity unCpt2 = new CompteEntity();
		unCpt2.setId(Integer.valueOf(15));
		unCpt2.setDecouvert(Double.valueOf(0D));
		unCpt2.setLibelle("Cpt 01");
		unCpt2.setSolde(Double.valueOf(5000D));
		unCpt2.setTaux(Double.valueOf(0.01D));
		unCpt2.setUtilisateurId(Integer.valueOf(1));

		Assert.assertNotEquals("Les deux comptes n'ont pas le meme hashcode", this.cpt1.hashCode(), unCpt2.hashCode());
	}

	/** Test. */
	@Test
	public void testHashcodeKo1() {
		IOperationEntity unObj = new OperationEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode de compte ne doit pas etre identique a celui d'une operation",
				this.cpt1.hashCode(), unObj.hashCode());
	}

	/** Test. */
	@Test
	public void testHashcodeKo2() {
		IUtilisateurEntity unObj = new UtilisateurEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode d'utilisateur ne doit pas etre identique a celui d'un compte",
				this.cpt1.hashCode(), unObj.hashCode());
	}
}
