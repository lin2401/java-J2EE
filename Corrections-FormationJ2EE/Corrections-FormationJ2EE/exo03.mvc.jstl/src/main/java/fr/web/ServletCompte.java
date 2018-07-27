
package fr.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.banque.Compte;
import fr.bd.AccesBD;

/**
 * Servlet qui va afficher tous les comptes d'un client. <br/>
 */
@WebServlet(description = "Affiche tous les comptes d'un client", urlPatterns = { "/ServletCompte" })
public class ServletCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/banque?useSSL=false";
	private static final String DB_LOGIN = "root";
	private static final String DB_PWD = "";

	/**
	 * Constructeur.
	 */
	public ServletCompte() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccesBD bd = null;
		try {
			// Recuperation des clients
			bd = new AccesBD(ServletCompte.DB_DRIVER);
			bd.seConnecter(ServletCompte.DB_URL, ServletCompte.DB_LOGIN, ServletCompte.DB_PWD);

			// Recuperation de l'id de l'utilisateur qui se trouve en tant que
			// parametre de request
			String idUtilisateur = request.getParameter("id");
			if (idUtilisateur == null || idUtilisateur.trim().isEmpty()) {
				// Erreur ....
				request.setAttribute("erreur", "Id utilisateur absent.");
			} else {
				Integer idUt = null;
				try {
					idUt = Integer.valueOf(idUtilisateur);
				} catch (Exception e) {
					request.setAttribute("erreur", "Id utilisateur absent incorrecte");
				}
				if (idUt != null) {
					List<Compte> lCompte = bd.selectCompte(idUt);
					// on place le resultat dans le scope request
					// car on va s'en servir uniquement dans l'ecran qui suit
					request.setAttribute("listeComptes", lCompte);
				}
			}

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
		request.getRequestDispatcher("/comptes.jsp").forward(request, response);
	}
}
