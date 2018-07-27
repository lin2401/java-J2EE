package fr.exemple;

public class Employe extends Personne {

	private String entreprise;
	private double salaire;
	private boolean statut;

	public Employe() {
		super(pNom, pPrenom, pAge, pTaille);

		public Employe(String pNom, String pPrenom, int pAge, double pTaille);



	}

	public Employe(String entreprise, double salaire, boolean statut) {
		super();
		this.entreprise = entreprise;
		this.salaire = salaire;
		this.statut = statut;
	}

}
