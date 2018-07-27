package com.banque.web.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.web.Clefs;

/**
 * Servlet qui va gerer la deconnection. <br/>
 */
@WebServlet(urlPatterns = { "/ServletLogout" })
public class LogoutController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger();
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogoutController.LOG.debug("--> LogoutController.service");
		Integer utilisateurId = (Integer) request.getSession(true).getAttribute(Clefs.CLEF_AUTHENTIFICATION);
		LogoutController.LOG.debug("-- LogoutController.service uid={}", utilisateurId);
		HttpSession session = request.getSession(true);
		Enumeration<String> toutesLesClefs = session.getAttributeNames();
		while (toutesLesClefs != null && toutesLesClefs.hasMoreElements()) {
			String uneClef = toutesLesClefs.nextElement();
			session.removeAttribute(uneClef);
		}
		session.invalidate();
		// Permet de netoyer la session
		request.logout();

		this.forward(request, response, Clefs.PAGE_LOGIN);
	}

}
