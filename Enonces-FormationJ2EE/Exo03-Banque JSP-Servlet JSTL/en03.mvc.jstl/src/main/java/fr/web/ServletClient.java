package fr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet qui va afficher tous les clients. <br/>
 */
@WebServlet(description = "Affiche tous les clients", urlPatterns = { "/ServletClient" })
public class ServletClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public ServletClient() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 - Intancie AccesBD
		// 2 - se connecte a la base de donnees
		// 3 - Recupere la liste de tous les utilisateurs
		// 4 - place cette liste en tant qu'attibut du scope request
		// 5 - quoi qu'il arrive se deconnecte
		// 6 - part vers la page clients.jsp
	}
}
