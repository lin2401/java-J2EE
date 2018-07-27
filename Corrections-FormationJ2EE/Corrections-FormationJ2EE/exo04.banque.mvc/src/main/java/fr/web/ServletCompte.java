package fr.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(urlPatterns = { "/ServletCompte" })
public class ServletCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// On recupere l'id qui est dans la session
			Integer idUtilisateur = (Integer) request.getSession(true).getAttribute("idUtilisateur");

			if (idUtilisateur == null) {
				// Si il n'est pas la = on n'est pas passe par authentification
				// Part en erreur sur la page login
				request.setAttribute("erreur", "Merci de vous authentifier");
				// On passe la main
				request.getRequestDispatcher("login.jsp").forward(request, response);
				// On fait un return pour etre certain que rien d'autre ne doit
				// arriver
				return;
			} else {
				// Sinon, tout va bien, on va chercher ses comptes
				List<Compte> listeCompte = AccesBD.getInstance().selectCompte(idUtilisateur);
				List<BeanCompteGenerique> listeCg = new ArrayList<>();
				for (Compte compte : listeCompte) {
					listeCg.add(new BeanCompteGenerique(compte));
				}
				// On les places dans la request
				request.setAttribute("listeCompte", listeCg);
				// On part vers la page qui liste les comptes
				request.getRequestDispatcher("comptes/liste.jsp").forward(request, response);
				// On fait un return pour etre certain que rien d'autre ne doit
				// arriver
				return;
			}
		} catch (SQLException e) {
			// Part en erreur la page login
			request.setAttribute("erreur", "Probleme lors de la recuperation des comptes (" + e.getMessage() + ")");
			// On passe la main
			request.getRequestDispatcher("login.jsp").forward(request, response);
			// On fait un return pour etre certain que rien d'autre ne doit
			// arriver
			return;
		}
	}
}
