package fr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet qui va afficher le menu. <br/>
 */
@WebServlet(urlPatterns = { "/ServletMenu" })
public class ServletMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public ServletMenu() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1 - Verifie que l'utilisateur est bien connecte
		// Regarde dans la session si l'information placee par la servlet login
		// est bien la
		// Si pas la => Direction page login avec un message
		// Sinon, on part vers la page menu
	}
}
