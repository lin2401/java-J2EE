package fr.banque;

public class Compte {

	private int numero;
	private double solde;

	public Compte() {
		this(-1, 0);
	}

	public Compte(int numero, double solde) {
		this.setNumero(numero);
		this.setSolde(solde);
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return this.numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return the solde
	 */
	public double getSolde() {
		return this.solde;
	}

	/**
	 * @param solde the solde to set
	 */
	public void setSolde(double solde) {
		this.solde = solde;
	}

	public void ajouter(double montant) {

//		this.solde = this.solde + montant;
		this.setSolde(this.getSolde() + montant);// la methode preferee
	}

	public void retirer(double montant) throws BanqueException {
		this.solde = this.solde - montant;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName());
		builder.append(", solde=");
		builder.append(this.solde);
		builder.append(", getNumero()=");
		builder.append(this.getNumero());
		builder.append("]");
		return builder.toString();
	}

}