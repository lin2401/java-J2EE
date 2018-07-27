package fr.exemple;

public class Employe extends Personne {

	String entreprise;
	double salaire;
	boolean statut;

	public Employe() {
		super();
	}

	public Employe(String entreprise, double salaire, boolean statut) {
		super();
		this.entreprise = entreprise;
		this.salaire = salaire;
		this.statut = statut;
	}

	public Employe(String pNom, String pPrenom, int pAge, double pTaille) {
		super(pNom, pPrenom, pAge, pTaille);
		// TODO Auto-generated constructor stub
	}

	// protected pas sur les attributs mais peut etre appliquée a des methodes

	// protected pour les enfants
	/*
	 * (non-Javadoc)
	 *
	 * @see fr.exemple.Personne#marcher()
	 */

	@Override

	public int marcher() {
		// TODO Auto-generated method stub
		int a = 10;
		a = a + super.marcher();
		return a;

	}

	/**
	 * @return the entreprise
	 */
	public String getEntreprise() {
		return entreprise;
	}

	/**
	 * @param entreprise the entreprise to set
	 */
	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	/**
	 * @return the salaire
	 */
	public double getSalaire() {
		return salaire;
	}

	/**
	 * @param salaire the salaire to set
	 */
	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	/**
	 * @return the statut
	 */
	public boolean isStatut() {
		return statut;
	}

	/**
	 * @param statut the statut to set
	 */
	public void setStatut(boolean statut) {
		this.statut = statut;
	}

}
