
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
		// 1 - Recupere le parametre qui contient l'id de l'utilisateur
		// 2 - verifie qu'il n'est ni null ni vide
		// 3 - le transforme en chiffre
		// 4 - Intancie AccesBD
		// 5 - se connecte a la base de donnees
		// 6 - Recupere la liste des comptes pour l'id utilisateur recupere
		// 7 - place cette liste en tant qu'attibut du scope request
		// 8 - quoi qu'il arrive se deconnecte
		// 9 - part vers la page comptes.jsp
	}
}
