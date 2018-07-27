
package fr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet qui va afficher tous les comptes d'un client. <br/>
 */
@WebServlet(description = "Affiche tous les comptes d'un client", urlPatterns = { "/ServletCompte" })
public class ServletCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public ServletCompte() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Code me en faisant usage de fr.bd.AccesBD
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
	}
}
