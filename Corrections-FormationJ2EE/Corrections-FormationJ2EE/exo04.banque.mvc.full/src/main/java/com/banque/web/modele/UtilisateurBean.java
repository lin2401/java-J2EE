package com.banque.web.modele;

import java.io.Serializable;
import java.util.Date;

import com.banque.entity.IUtilisateurEntity;

/**
 * Objet qui represente un utilisateur afin de garder une liste des personnes
 * connectes.
 */
public class UtilisateurBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nom;
	private String prenom;
	private String ip;
	private Date date;

	/**
	 * Constructeur.
	 */
	public UtilisateurBean() {
		super();
	}

	/**
	 * Remplit les attributs grace aux parametres.
	 *
	 * @param unUser
	 *            un utilisateur
	 * @param ip
	 *            l'ip du demandeur
	 */
	public void remplir(IUtilisateurEntity unUser, String ip) {
		this.setId(unUser.getId());
		this.setNom(unUser.getNom());
		this.setPrenom(unUser.getPrenom());
		this.setIp(ip);
		this.setDate(new Date());
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param id
	 *            la nouvelle valeur pour l'attribut id
	 */
	protected void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param nom
	 *            la nouvelle valeur pour l'attribut nom
	 */
	protected void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param prenom
	 *            la nouvelle valeur pour l'attribut prenom
	 */
	protected void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param ip
	 *            la nouvelle valeur pour l'attribut ip
	 */
	protected void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param date
	 *            la nouvelle valeur pour l'attribut date
	 */
	protected void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete prenom
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete ip
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * Recupere la valeur de l'attribut.
	 *
	 * @return la propriete date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Recupere le full name (nom prenom)
	 *
	 * @return le full name (nom prenom)
	 */
	public String getFullname() {
		return this.getNom() + " " + this.getPrenom();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName());
		builder.append(" [id=");
		builder.append(this.getId());
		builder.append(", nom=");
		builder.append(this.getNom());
		builder.append(", prenom=");
		builder.append(this.getPrenom());
		builder.append(", ip=");
		builder.append(this.getIp());
		builder.append(", date=");
		builder.append(this.getDate());
		builder.append("]");
		return builder.toString();
	}

}
