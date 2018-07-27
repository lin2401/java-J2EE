package fr.banque;

import java.util.Hashtable;
import java.util.Map;

public class Client {

	private String nom;
	private String prenom;
	private int age;
	private int numero;
	// private List<Compte> listeCompte = new ArrayList<>();

	private Map<Integer, Compte> listeCompte = new Hashtable<>();

	public Client() {
		this(null, null, -1, -1);
	}

	public Client(String nom, String prenom, int age, int numero) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.numero = numero;

	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
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
	 * @return the comptes
	 */
	/*
	 * blic Compte[] getComptes() { return this.listeCompte; }
	 */

	/**
	 * @param comptes the comptes to set
	 */
	/*
	 * public void setComptes(Compte[] comptes) { this.listeCompte = this.Comptes; }
	 */

	public void ajouterCompte(Compte compte) {

		this.listeCompte.put(this.getNumero(), compte);

		/*
		 * for (int i = 0; i < this.comptes.length; i++) {
		 *
		 * if (this.comptes[i] == null) {
		 *
		 * this.comptes[i] = compte; break;
		 *
		 * return; // casser la methode et si on utilise return on utlise pas else
		 *
		 * }
		 *
		 * else {
		 *
		 * System.out.println("pas possible d'ajouter un nouveau compte");
		 *
		 * }
		 *
		 * }
		 */

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [nom=");
		builder.append(this.nom);
		builder.append(", prenom=");
		builder.append(this.prenom);
		builder.append(", age=");
		builder.append(this.age);
		builder.append(", numero=");
		builder.append(this.numero);
		builder.append(", comptes=");
		builder.append(this.listeCompte);
		builder.append("]");
		return builder.toString();
	}

	public Compte getCompte(int numeroCompte) {

		for (int i = 0; i < this.listeCompte.size(); i++) {

			if (this.listeCompte.get(i) != null && this.listeCompte.get(i).getNumero() == numeroCompte) {

				// return this.comptes[i];
				System.out.println(this.listeCompte.get(i));
//System.out.print("pas trouve le compte " + numeroCompte + " pour le client " + this
				// return null;
			} else {
				System.out.println("attention");
			}
		}

		return null;
	}
}
