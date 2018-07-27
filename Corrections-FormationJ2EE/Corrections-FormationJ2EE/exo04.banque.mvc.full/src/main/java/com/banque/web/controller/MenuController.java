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
 * Controller qui va afficher le menu. <br/>
 */
@WebServlet(urlPatterns = "/ServletMenu")
public class MenuController extends AbstractController {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger();

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuController.LOG.debug("--> MenuController.service");
		Integer utilisateurId = (Integer) request.getSession(true).getAttribute(Clefs.CLEF_AUTHENTIFICATION);
		MenuController.LOG.debug("-- MenuController.service uid={}", utilisateurId);
		this.forward(request, response, "menu.jsp");
	}
}
