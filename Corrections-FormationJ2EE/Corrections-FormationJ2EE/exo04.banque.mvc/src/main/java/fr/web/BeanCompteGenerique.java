package fr.web;

import java.io.Serializable;

import fr.banque.Compte;
import fr.banque.ICompteASeuil;
import fr.banque.ICompteRemunere;

/**
 * Le bean qui represente un Compte, peut importe son type. <br>
 */
public class BeanCompteGenerique implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String libelle;
	private Double solde;
	private Double decouvert;
	private Double taux;

	/**
	 * Constructeur de l'objet. <br>
	 */
	public BeanCompteGenerique() {
		this(null, null, null, null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unId
	 *            l'id d'un compte
	 */
	public BeanCompteGenerique(Integer unId) {
		this(unId, null, null, null, null);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unCpt
	 *            un compte
	 */
	public BeanCompteGenerique(Compte unCpt) {
		if (unCpt != null) {
			this.setId(Integer.valueOf(unCpt.getNumero()));
			this.setLibelle(unCpt.getLibelle());
			this.setSolde(Double.valueOf(unCpt.getSolde()));
			if (unCpt instanceof ICompteASeuil) {
				this.setDecouvert(Double.valueOf(((ICompteASeuil) unCpt).getSeuil()));
			}
			if (unCpt instanceof ICompteRemunere) {
				this.setTaux(Double.valueOf(((ICompteRemunere) unCpt).getTaux()));
			}
		}
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unId
	 *            l'id d'un compte
	 * @param unLibelle
	 *            le libelle du compte
	 * @param unSolde
	 *            le solde du compte
	 * @param unDecouvert
	 *            le decouvert du compte
	 * @param unTaux
	 *            un taux
	 */
	public BeanCompteGenerique(Integer unId, String unLibelle, Double unSolde, Double unDecouvert, Double unTaux) {
		super();
		this.setId(unId);
		this.setLibelle(unLibelle);
		this.setSolde(unSolde);
		this.setDecouvert(unDecouvert);
		this.setTaux(unTaux);
	}

	/**
	 * Gets the id attribute.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Sets the id new value.
	 *
	 * @param pId
	 *            the new value for the id attribute
	 */
	public void setId(Integer pId) {
		this.id = pId;
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
	 * Sets the libelle new value.
	 *
	 * @param pLibelle
	 *            the new value for the libelle attribute
	 */
	public void setLibelle(String pLibelle) {
		this.libelle = pLibelle;
	}

	/**
	 * Gets the solde attribute.
	 *
	 * @return the solde
	 */
	public Double getSolde() {
		return this.solde;
	}

	/**
	 * Sets the solde new value.
	 *
	 * @param pSolde
	 *            the new value for the solde attribute
	 */
	public void setSolde(Double pSolde) {
		this.solde = pSolde;
	}

	/**
	 * Gets the decouvert attribute.
	 *
	 * @return the decouvert
	 */
	public Double getDecouvert() {
		return this.decouvert;
	}

	/**
	 * Sets the decouvert new value.
	 *
	 * @param pDecouvert
	 *            the new value for the decouvert attribute
	 */
	public void setDecouvert(Double pDecouvert) {
		this.decouvert = pDecouvert;
	}

	/**
	 * Gets the taux attribute.
	 *
	 * @return the taux
	 */
	public Double getTaux() {
		return this.taux;
	}

	/**
	 * Sets the taux new value.
	 *
	 * @param pTaux
	 *            the new value for the taux attribute
	 */
	public void setTaux(Double pTaux) {
		this.taux = pTaux;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getName());
		builder.append(" [id=");
		builder.append(this.getId());
		builder.append(", libelle=");
		builder.append(this.getLibelle());
		builder.append(", solde=");
		builder.append(this.getSolde());
		builder.append(", decouvert=");
		builder.append(this.getDecouvert());
		builder.append(", taux=");
		builder.append(this.getTaux());
		builder.append("]");
		return builder.toString();
	}

}