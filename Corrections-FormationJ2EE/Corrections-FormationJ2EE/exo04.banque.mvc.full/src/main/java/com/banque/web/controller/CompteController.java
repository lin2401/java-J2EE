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
import com.banque.service.impl.CompteService;
import com.banque.web.Clefs;

/**
 * Controller qui liste les comptes <br/>
 */
@WebServlet(urlPatterns = "/ServletCompte")
public class CompteController extends AbstractController {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger();

	// Nos services sont des objets 'stateless'
	private static final ICompteService SERVICE = new CompteService();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = Clefs.PAGE_LOGIN;
		CompteController.LOG.debug("--> Passage dans CompteController.service");
		Integer utilisateurId = (Integer) request.getSession(true).getAttribute(Clefs.CLEF_AUTHENTIFICATION);
		try {
			CompteController.LOG.debug("-- CompteController.service pour {}", utilisateurId);
			List<ICompteEntity> listeCompte = CompteController.SERVICE.selectAll(utilisateurId.intValue());
			request.setAttribute("listeCompte", listeCompte);
			CompteController.LOG.debug("-- CompteController.service pour {} trouve {}", utilisateurId,
					Integer.valueOf(listeCompte.size()));
			destination = "comptes/liste.jsp";
		} catch (Exception e) {
			request.setAttribute(Clefs.CLEF_ERREUR, e.getMessage());
			CompteController.LOG.error("Erreur:", e);
		}
		this.forward(request, response, destination);
	}
}
