package fr.banque;

public class CompteASeuil extends Compte implements ICompteASeuil {
	private double seuil;

	public CompteASeuil(double seuil) {
		this(-1, 0, seuil);
	}

	public CompteASeuil(int numero, double solde, double pS) {
		super(numero, solde);
		// TODO Auto-generated constructor stub
		this.seuil = pS;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.delete(builder.length() - 1, builder.length()); // pour enlever le [
		builder.append(", seuil=");
		builder.append(this.seuil);
		builder.append("]");
		return builder.toString();
	}

	public CompteASeuil() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getSeuil() {
		return this.seuil;
	}

	@Override
	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}

	@Override
	public void retirer(double montant) throws BanqueException {
		if (this.getSolde() - montant < this.getSeuil()) {
			// rien
			throw new BanqueException("pas possible");
		}
		super.retirer(montant);
	}

}
