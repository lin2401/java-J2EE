package com.banque.web.modele;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Form pour le virement. <br/>
 */
public class VirementBean implements Serializable {
	private static final Logger LOG = LogManager.getLogger();
	private static final long serialVersionUID = 1L;

	private String cptSrcId;
	private String cptDstId;
	private String montant;

	/**
	 * Constructeur de l'objet.
	 */
	public VirementBean() {
		super();
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param request
	 *            la request
	 */
	public VirementBean(HttpServletRequest request) {
		super();
		this.setCptSrcId(request.getParameter("inCmptEme"));
		this.setCptDstId(request.getParameter("inCmptDes"));
		this.setMontant(request.getParameter("inMontant"));
	}

	/**
	 * Indique si il n'y a acune information dans le bean.
	 *
	 * @return true si tous les champs du beans sont null
	 */
	public boolean empty() {
		return this.cptSrcId == null && null == this.cptDstId && this.montant == null;
	}

	/**
	 * Recupere la propriete <i>cptSrcId</i>.
	 *
	 * @return the cptSrcId la valeur de la propriete.
	 */
	public String getCptSrcId() {
		return this.cptSrcId;
	}

	/**
	 * Recupere l'id du compte source sous forme d'entier.
	 *
	 * @return l'id du compte source sous forme d'entier.
	 */
	public Integer getCptSrcIdAsInt() {
		try {
			return Integer.valueOf(this.cptSrcId);
		} catch (Exception e) {
			VirementBean.LOG.trace("Erreur sur id src", e);
		}
		return null;
	}

	/**
	 * Recupere l'id du compte destination sous forme d'entier.
	 *
	 * @return l'id du compte destination sous forme d'entier.
	 */
	public Integer getCptDstIdInt() {
		try {
			return Integer.valueOf(this.cptDstId);
		} catch (Exception e) {
			VirementBean.LOG.trace("Erreur sur id dst", e);
		}
		return null;

	}

	/**
	 * Fixe la propriete <i>cptSrcId</i>.
	 *
	 * @param pCptSrcId
	 *            la nouvelle valeur pour la propriete cptSrcId.
	 */
	public void setCptSrcId(String pCptSrcId) {
		if (pCptSrcId == null || pCptSrcId.trim().length() == 0) {
			this.cptSrcId = null;
		} else {
			this.cptSrcId = pCptSrcId;
		}
	}

	/**
	 * Recupere la propriete <i>cptDstId</i>.
	 *
	 * @return the cptDstId la valeur de la propriete.
	 */
	public String getCptDstId() {
		return this.cptDstId;
	}

	/**
	 * Fixe la propriete <i>cptDstId</i>.
	 *
	 * @param pCptDstId
	 *            la nouvelle valeur pour la propriete cptDstId.
	 */
	public void setCptDstId(String pCptDstId) {
		if (pCptDstId == null || pCptDstId.trim().length() == 0) {
			this.cptDstId = null;
		} else {
			this.cptDstId = pCptDstId;
		}
	}

	/**
	 * Recupere la propriete <i>montant</i>.
	 *
	 * @return the montant la valeur de la propriete.
	 */
	public String getMontant() {
		return this.montant;
	}

	/**
	 * Recupere la propriete <i>montant</i>.
	 *
	 * @return the montant la valeur de la propriete.
	 */
	public Double getMontantAsDouble() {
		try {
			return Double.valueOf(this.montant);
		} catch (Exception e) {
			VirementBean.LOG.trace("Erreur sur montant", e);
		}
		return null;
	}

	/**
	 * Fixe la propriete <i>montant</i>.
	 *
	 * @param pMontant
	 *            la nouvelle valeur pour la propriete montant.
	 */
	public void setMontant(String pMontant) {
		if (pMontant == null || pMontant.trim().length() == 0) {
			this.montant = null;
		} else {
			this.montant = pMontant;
		}
	}

	/**
	 * Validation simple du bean.
	 * 
	 * @return true si tout est ok, false sinon
	 */
	public boolean validate() {
		return this.getCptDstIdInt() != null && this.getCptSrcIdAsInt() != null && this.getMontantAsDouble() != null
				&& !this.getCptDstIdInt().equals(this.getCptSrcIdAsInt())
				&& this.getMontantAsDouble().doubleValue() > 0;
	}

}
