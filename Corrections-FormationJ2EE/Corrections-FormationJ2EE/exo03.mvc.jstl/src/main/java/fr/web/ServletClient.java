
package fr.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.banque.Client;
import fr.bd.AccesBD;

/**
 * Servlet qui va afficher tous les clients. <br/>
 */
@WebServlet(description = "Affiche tous les clients", urlPatterns = { "/ServletClient" })
public class ServletClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/banque?useSSL=false";
	private static final String DB_LOGIN = "root";
	private static final String DB_PWD = "";

	/**
	 * Constructeur.
	 */
	public ServletClient() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccesBD bd = null;
		try {
			// Recuperation des clients
			bd = new AccesBD(ServletClient.DB_DRIVER);
			bd.seConnecter(ServletClient.DB_URL, ServletClient.DB_LOGIN, ServletClient.DB_PWD);
			List<Client> lClients = bd.selectUtilisateur();
			// on place le resultat dans le scope request
			// car on va s'en servir uniquement dans l'ecran qui suit
			request.setAttribute("listeClients", lClients);
		} catch (SQLException e) {
			// NE JAMAIS FAIRE EN WEB
			// e.printStackTrace();
			request.setAttribute("erreur", "Erreur dans la servlet (" + e.getMessage() + ")");
		} finally {
			if (bd != null) {
				bd.seDeconnecter();
			}
		}
		// On part vers la jsp, on fait suivre
		request.getRequestDispatcher("/clients.jsp").forward(request, response);
	}
}
