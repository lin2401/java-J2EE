package com.banque.entity.impl;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.banque.entity.ICompteEntity;
import com.banque.entity.IOperationEntity;
import com.banque.entity.IUtilisateurEntity;

/**
 * Test sur la classe IOperationEntity.
 */
public class OperationEntityTest {
	// Nos entités ne sont pas "singleton", donc pas de static ni de BeforeClass
	private IOperationEntity op1;

	@Before
	public void init() {
		this.op1 = new OperationEntity(Integer.valueOf(5));
		this.op1.setCompteId(Integer.valueOf(1));
		this.op1.setDate(new Timestamp(System.currentTimeMillis()));
		this.op1.setLibelle("Op1");
		this.op1.setMontant(Double.valueOf(500D));
	}

	/** Test. */
	@Test
	public void testEqualsOk() {
		IOperationEntity uneOp2 = new OperationEntity();
		uneOp2.setId(Integer.valueOf(5));
		uneOp2.setCompteId(Integer.valueOf(1));
		uneOp2.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp2.setLibelle("Op1");
		uneOp2.setMontant(Double.valueOf(500D));

		Assert.assertEquals("Les deux operations sont egales", this.op1, uneOp2);
		Assert.assertEquals("Les deux operations sont egales", uneOp2, this.op1);
	}

	/** Test. */
	@Test
	public void testEqualsOk2() {
		IOperationEntity uneOp2 = null;
		Assert.assertNotEquals("Les deux operations ne sont pas egales", this.op1, uneOp2);
	}

	/** Test. */
	@Test
	public void testEqualsOk3() {
		IOperationEntity uneOp2 = this.op1;
		Assert.assertEquals("Les deux operations sont egales", this.op1, uneOp2);
		Assert.assertEquals("Les deux operations sont egales", uneOp2, this.op1);
	}

	/** Test. */
	@Test
	public void testEqualsKo() {
		IOperationEntity uneOp2 = new OperationEntity();
		uneOp2.setId(Integer.valueOf(15));
		uneOp2.setCompteId(Integer.valueOf(1));
		uneOp2.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp2.setLibelle("Op1");
		uneOp2.setMontant(Double.valueOf(500D));

		Assert.assertNotEquals("Les deux operations sont differents", this.op1, uneOp2);
		Assert.assertNotEquals("Les deux operations sont differents", uneOp2, this.op1);
	}

	/** Test. */
	@Test
	public void testEqualsKo1() {
		IUtilisateurEntity unObj = new UtilisateurEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Une operation n'est pas un utilisateur", this.op1, unObj);
		Assert.assertNotEquals("Un utilisateur n'est pas une operation", unObj, this.op1);
	}

	/** Test. */
	@Test
	public void testEqualsKo2() {
		ICompteEntity unObj = new CompteEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Une operation n'est pas un compte", this.op1, unObj);
		Assert.assertNotEquals("Un compte n'est pas une operation", unObj, this.op1);
	}

	/** Test. */
	@Test
	public void testHashcodeOk() {
		IOperationEntity uneOp2 = new OperationEntity();
		uneOp2.setId(Integer.valueOf(5));
		uneOp2.setCompteId(Integer.valueOf(1));
		uneOp2.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp2.setLibelle("Op1");
		uneOp2.setMontant(Double.valueOf(500D));

		Assert.assertEquals("Les deux operations ont le meme hashcode", this.op1.hashCode(), uneOp2.hashCode());
	}

	/** Test. */
	@Test
	public void testHashcodeKo() {
		IOperationEntity uneOp2 = new OperationEntity();
		uneOp2.setId(Integer.valueOf(15));
		uneOp2.setCompteId(Integer.valueOf(1));
		uneOp2.setDate(new Timestamp(System.currentTimeMillis()));
		uneOp2.setLibelle("Op1");
		uneOp2.setMontant(Double.valueOf(500D));

		Assert.assertNotEquals("Les deux operations n'ont pas le meme hashcode", this.op1.hashCode(),
				uneOp2.hashCode());
	}

	/** Test. */
	@Test
	public void testHashcodeKo1() {
		ICompteEntity unObj = new CompteEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode de operation ne doit pas etre identique a celui d'un compte",
				this.op1.hashCode(), unObj.hashCode());
	}

	/** Test. */
	@Test
	public void testHashcodeKo2() {
		IUtilisateurEntity unObj = new UtilisateurEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode d'utilisateur ne doit pas etre identique a celui d'une operation",
				this.op1.hashCode(), unObj.hashCode());
	}
}
