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
@WebServlet(urlPatterns = { "/ServletCompte" })
public class ServletCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public ServletCompte() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1 - Verifie que l'utilisateur est bien connecte
		// Regarde dans la session si l'information placee par la servlet login
		// est bien la
		// Si pas la => Direction page login avec un message
		// Sinon, on continue
		// 2 - Se connecte a la base de donnees via fr.bd.AccesBD
		// Recupere la liste des comptes via la methode selectCompte de AccesBD
		// Place la liste de compte dans le scope adapte
		// Part vers la page comptes/liste.jsp
		// 3 - Si un probleme ou une erreur survient lors de la recuperation des
		// donnees
		// On place un message dans la request et on va vers la page menu
	}
}
