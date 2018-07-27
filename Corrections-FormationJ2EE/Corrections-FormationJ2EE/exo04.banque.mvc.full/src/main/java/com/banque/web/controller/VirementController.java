package com.banque.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.entity.ICompteEntity;
import com.banque.service.ICompteService;
import com.banque.service.IOperationService;
import com.banque.service.ex.ErreurTechniqueException;
import com.banque.service.ex.FonctionnelleException;
import com.banque.service.impl.CompteService;
import com.banque.service.impl.OperationService;
import com.banque.web.Clefs;
import com.banque.web.modele.VirementBean;

/**
 * Controller pour le virement. <br/>
 */
@WebServlet(urlPatterns = "/ServletVirement")
public class VirementController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger();
	private static final long serialVersionUID = 1L;

	// Nos services sont des objets 'stateless'
	private static final ICompteService SERVICE_COMPTE = new CompteService();
	private static final IOperationService SERVICE_OPERATION = new OperationService();

	/**
	 * Place la liste des comptes dans le model.
	 *
	 * @param request
	 *            ou placer la liste des comptes
	 * @param pUserId
	 *            l'id du user
	 * @throws ErreurTechniqueException
	 * @throws FonctionnelleException
	 */
	private void getAndSetListeComptes(HttpServletRequest request, Integer pUserId)
			throws FonctionnelleException, ErreurTechniqueException {
		VirementController.LOG.trace("-- Recuperation comptes pour uid={}", pUserId);
		List<ICompteEntity> listeCompte = VirementController.SERVICE_COMPTE.selectAll(pUserId.intValue());
		VirementController.LOG.trace("-- Recuperation comptes pour uid={} resultat={}", pUserId,
				Integer.valueOf(listeCompte.size()));
		// On replace la liste des comptes
		request.setAttribute("listeCompte", listeCompte);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = Clefs.PAGE_LOGIN;
		VirementController.LOG.debug("--> Passage dans VirementController.doGet");
		Integer utilisateurId = (Integer) request.getSession(true).getAttribute(Clefs.CLEF_AUTHENTIFICATION);
		VirementBean virementBean = new VirementBean(request);
		request.setAttribute("vbean", virementBean);
		try {
			this.getAndSetListeComptes(request, utilisateurId);
			destination = "comptes/virement.jsp";
		} catch (Exception e) {
			request.setAttribute(Clefs.CLEF_ERREUR, e.getMessage());
			VirementController.LOG.error("Erreur:", e);
		}
		this.forward(request, response, destination);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "comptes/virement.jsp";
		VirementController.LOG.debug("--> Passage dans VirementController.doPost");
		Integer utilisateurId = (Integer) request.getSession(true).getAttribute(Clefs.CLEF_AUTHENTIFICATION);
		VirementBean virementBean = new VirementBean(request);
		request.setAttribute("vbean", virementBean);
		try {
			this.getAndSetListeComptes(request, utilisateurId);
			VirementController.LOG.debug("-- doVirement uid={} cpSrc={} cpDest={} montant={}", utilisateurId,
					virementBean.getCptSrcId(), virementBean.getCptDstId(), virementBean.getMontant());
			if (virementBean.validate()) {
				VirementController.SERVICE_OPERATION.faireVirement(utilisateurId.intValue(),
						Integer.parseInt(virementBean.getCptSrcId()), Integer.parseInt(virementBean.getCptDstId()),
						Double.parseDouble(virementBean.getMontant()));
				request.setAttribute("message", "Virement OK");
				destination = "menu.jsp";
			} else {
				request.setAttribute(Clefs.CLEF_ERREUR, "Paramètres du virement incorrectes");
			}
		} catch (Exception e) {
			request.setAttribute(Clefs.CLEF_ERREUR, e.getMessage());
			VirementController.LOG.error("Erreur:", e);
		}
		this.forward(request, response, destination);
	}
}
