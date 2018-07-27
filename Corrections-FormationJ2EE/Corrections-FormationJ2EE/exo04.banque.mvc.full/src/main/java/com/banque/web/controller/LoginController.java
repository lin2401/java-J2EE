package com.banque.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.entity.IUtilisateurEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ex.FonctionnelleException;
import com.banque.service.impl.AuthentificationService;
import com.banque.web.Clefs;
import com.banque.web.modele.LoginBean;

/**
 * Controller de login. <br/>
 */
@WebServlet(urlPatterns = "/ServletLogin")
public class LoginController extends AbstractController {
	private static final Logger LOG = LogManager.getLogger();
	private static final long serialVersionUID = 1L;

	// Nos services sont des objets 'stateless'
	private static final IAuthentificationService SERVICE = new AuthentificationService();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginController.LOG.debug("--> Passage dans LoginController.doGet");
		LoginBean loginBean = new LoginBean(request);
		request.setAttribute("lbean", loginBean);
		this.forward(request, response, Clefs.PAGE_LOGIN);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = Clefs.PAGE_LOGIN;
		LoginController.LOG.debug("--> Passage dans LoginController.doPost");

		LoginBean loginBean = new LoginBean(request);
		request.setAttribute("lbean", loginBean);
		try {
			LoginController.LOG.debug("-- LoginController.doPost login={}", loginBean.getLogin());
			if (loginBean.validate()) {
				IUtilisateurEntity utilisateur = LoginController.SERVICE.authentifier(loginBean.getLogin(),
						loginBean.getPassword());
				LoginController.LOG.debug("-- LoginController.doPost login={} resultat={}", loginBean.getLogin(),
						utilisateur);
				HttpSession session = request.getSession(true);
				// Attention a bien mettre l'ip avant car c'est l'ajout de CLEF_UTILISATEUR qui
				// construit la liste des utilisateurs connetes
				session.setAttribute(Clefs.CLEF_UTILISATEUR_IP, request.getRemoteAddr());
				session.setAttribute(Clefs.CLEF_UTILISATEUR, utilisateur);
				session.setAttribute(Clefs.CLEF_AUTHENTIFICATION, utilisateur.getId());
				destination = "menu.jsp";
			} else {
				request.setAttribute(Clefs.CLEF_ERREUR, "Paramêtre d'authentification incorrectes.");
			}
		} catch (FonctionnelleException e) {
			request.setAttribute(Clefs.CLEF_ERREUR, "Problème d'authentification");
			LoginController.LOG.error("Erreur", e);
		} catch (Exception e) {
			request.setAttribute(Clefs.CLEF_ERREUR, e.getMessage());
			LoginController.LOG.error("Erreur", e);
		}
		this.forward(request, response, destination);
	}

}
