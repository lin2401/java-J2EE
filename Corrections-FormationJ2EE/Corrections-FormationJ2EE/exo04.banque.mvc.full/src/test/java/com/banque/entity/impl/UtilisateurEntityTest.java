package com.banque.entity.impl;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.banque.entity.ESex;
import com.banque.entity.ICompteEntity;
import com.banque.entity.IOperationEntity;
import com.banque.entity.IUtilisateurEntity;

/**
 * Test sur la classe IUtilisateurEntity.
 */
public class UtilisateurEntityTest {

	// Nos entités ne sont pas "singleton", donc pas de static ni de BeforeClass
	private IUtilisateurEntity client1;

	@Before
	public void init() {
		this.client1 = new UtilisateurEntity(Integer.valueOf(5));
		this.client1.setNom("Smith");
		this.client1.setPrenom("Jhon");
		this.client1.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		this.client1.setPassword("bonjour");
		this.client1.setSex(ESex.HOMME);
	}

	/** Test. */
	@Test
	public void testEqualsOk() {
		IUtilisateurEntity unClient2 = new UtilisateurEntity();
		unClient2.setId(Integer.valueOf(5));
		unClient2.setNom("Smith");
		unClient2.setPrenom("Jhon");
		unClient2.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient2.setPassword("bonjour");
		unClient2.setSex(ESex.HOMME);

		Assert.assertEquals("Les deux utilisateurs sont egaux", this.client1, unClient2);
		Assert.assertEquals("Les deux utilisateurs sont egaux", unClient2, this.client1);
	}

	/** Test. */
	@Test
	public void testEqualsOk2() {
		IUtilisateurEntity unClient2 = null;
		Assert.assertNotEquals("Les deux utilisateurs ne sont pas egaux", this.client1, unClient2);
	}

	/** Test. */
	@Test
	public void testEqualsOk3() {
		IUtilisateurEntity unClient2 = this.client1;
		Assert.assertEquals("Les deux utilisateurs sont egaux", this.client1, unClient2);
		Assert.assertEquals("Les deux utilisateurs sont egaux", unClient2, this.client1);
	}

	/** Test. */
	@Test
	public void testEqualsKo() {
		IUtilisateurEntity unClient2 = new UtilisateurEntity();
		unClient2.setId(Integer.valueOf(15));
		unClient2.setNom("Smith");
		unClient2.setPrenom("Jhon");
		unClient2.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient2.setPassword("bonjour");
		unClient2.setSex(ESex.HOMME);

		Assert.assertNotEquals("Les deux utilisateurs sont differents", this.client1, unClient2);
		Assert.assertNotEquals("Les deux utilisateurs sont differents", unClient2, this.client1);
	}

	/** Test. */
	@Test
	public void testEqualsKo1() {
		IOperationEntity unObj = new OperationEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un utilisateur n'est pas une operation", this.client1, unObj);
		Assert.assertNotEquals("Une operation n'est pas un utilisateur", unObj, this.client1);
	}

	/** Test. */
	@Test
	public void testEqualsKo2() {
		ICompteEntity unObj = new CompteEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un utilisateur n'est pas un compte", this.client1, unObj);
		Assert.assertNotEquals("Un compte n'est pas un utilisateur", unObj, this.client1);
	}

	/** Test. */
	@Test
	public void testHashcodeOk() {
		IUtilisateurEntity unClient2 = new UtilisateurEntity();
		unClient2.setId(Integer.valueOf(5));
		unClient2.setNom("Smith");
		unClient2.setPrenom("Jhon");
		unClient2.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient2.setPassword("bonjour");
		unClient2.setSex(ESex.HOMME);

		Assert.assertEquals("Les deux utilisateurs ont le meme hashcode", this.client1.hashCode(),
				unClient2.hashCode());
	}

	/** Test. */
	@Test
	public void testHashcodeKo() {
		IUtilisateurEntity unClient2 = new UtilisateurEntity();
		unClient2.setId(Integer.valueOf(15));
		unClient2.setNom("Smith");
		unClient2.setPrenom("Jhon");
		unClient2.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
		unClient2.setPassword("bonjour");
		unClient2.setSex(ESex.HOMME);

		Assert.assertNotEquals("Les deux utilisateurs n'ont pas le meme hashcode", this.client1.hashCode(),
				unClient2.hashCode());
	}

	/** Test. */
	@Test
	public void testHashcodeKo1() {
		IOperationEntity unObj = new OperationEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode d'utilisateur ne doit pas etre identique a celui d'une operation",
				this.client1.hashCode(), unObj.hashCode());
	}

	/** Test. */
	@Test
	public void testHashcodeKo2() {
		ICompteEntity unObj = new CompteEntity(Integer.valueOf(5));

		Assert.assertNotEquals("Un hashcode d'utilisateur ne doit pas etre identique a celui d'un compte",
				this.client1.hashCode(), unObj.hashCode());
	}
}
