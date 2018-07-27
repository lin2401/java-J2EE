package fr.banque;

public class CompteRemunere extends Compte implements ICompteRemunere {

	private double taux;

	public CompteRemunere(int numero, double solde) {
		super(numero, solde);

	}

	public CompteRemunere(int numero, double solde, double taux) {
		super(numero, solde);
		this.taux = taux;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.delete(builder.length() - 1, builder.length()); // pour enlever le [
		builder.append(", taux=");
		builder.append(this.taux);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public double calculerInteret() {
		return this.taux * this.getSolde();
	}

	@Override
	public void verserInteret() {
		this.ajouter(this.calculerInteret());

	}

	@Override
	public double getTaux() {
		return this.taux;
	}

	@Override
	public void setTaux(double taux) {
		this.taux = taux;
	}

}