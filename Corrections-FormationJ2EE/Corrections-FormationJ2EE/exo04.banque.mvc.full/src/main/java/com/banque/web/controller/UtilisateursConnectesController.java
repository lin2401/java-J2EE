package com.banque.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.web.Clefs;

/**
 * Servlet qui va gerer les utilisateurs connectes. <br/>
 */
@WebServlet(urlPatterns = { "/ServletUtilisateursConnectes" })
public class UtilisateursConnectesController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger();
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateursConnectesController.LOG.debug("--> UtilisateursConnectesController.service");
		Integer utilisateurId = (Integer) request.getSession(true).getAttribute(Clefs.CLEF_AUTHENTIFICATION);
		UtilisateursConnectesController.LOG.debug("-- UtilisateursConnectesController.service uid={}", utilisateurId);
		// Rien de particulier a faire puisque la liste est dans le scope
		// application
		this.forward(request, response, "listeUtilisateurs.jsp");
	}
}
