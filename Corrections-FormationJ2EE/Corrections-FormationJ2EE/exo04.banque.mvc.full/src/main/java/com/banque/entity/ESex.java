package com.banque.entity;

/**
 * Enumeration qui represente les valeurs pour le sex de l'utilisateur.
 */
public enum ESex {
	/** Femme. */
	FEMME(0, "Femme"),
	/** Homme. */
	HOMME(1, "Homme"),
	/** Autre. */
	AUTRE(2, "Aute");

	private final byte value;
	private final String libelle;

	private ESex(int pValue, String pLibelle) {
		this.value = (byte) pValue;
		this.libelle = pLibelle;
	}

	/**
	 * Gets the value attribute.
	 *
	 * @return the value
	 */
	public byte getValue() {
		return this.value;
	}

	/**
	 * Gets the libelle attribute.
	 *
	 * @return the libelle
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * Donne la valeur de l'enumeration a partir du chiffre.
	 * 
	 * @param pValue
	 *            une valeur (0,1,2)
	 * @return le ESex associe
	 */
	public static ESex fromValue(byte pValue) {
		switch (pValue) {
		case 0:
			return ESex.HOMME;
		case 1:
			return ESex.FEMME;
		default:
			return ESex.AUTRE;
		}
	}
}
