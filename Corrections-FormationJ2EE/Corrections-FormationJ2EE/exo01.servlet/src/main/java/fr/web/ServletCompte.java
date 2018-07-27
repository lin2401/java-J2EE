package fr.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.banque.Compte;
import fr.banque.ICompteASeuil;
import fr.banque.ICompteRemunere;
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
			StringBuilder buffer = new StringBuilder();
			buffer.append("<!DOCTYPE html>");
			buffer.append("<html lang=\"fr\">");
			buffer.append("<head>");
			buffer.append("<meta charset=\"utf-8\" />");
			buffer.append("<title>ServletCompte</title>");
			buffer.append("<style>");
			buffer.append("h1 { text-align: center; }");
			buffer.append("td, th {");
			buffer.append("text-align: center;");
			buffer.append("border: 1px solid black;");
			buffer.append("}");
			buffer.append("th {");
			buffer.append("text-transform: capitalize;");
			buffer.append("text-shadow: 2px 2px lightgray;");
			buffer.append("font-size: 1.5em;");
			buffer.append("}");
			buffer.append("table {");
			buffer.append("margin: auto;");
			buffer.append("width: 70%;");
			buffer.append("border: 1px solid black;");
			buffer.append("}");
			buffer.append("</style>");
			buffer.append("</head>");
			buffer.append("<body>");

			// Recuperation de l'id de l'utilisateur qui se trouve en tant que
			// parametre de request
			String idUtilisateur = request.getParameter("id");
			if (idUtilisateur == null || idUtilisateur.trim().isEmpty()) {
				// Erreur ....
				buffer.append("Id utilisateur absent.");
			} else {
				Integer idUt = null;
				try {
					idUt = Integer.valueOf(idUtilisateur);
				} catch (Exception e) {
					buffer.append("Id utilisateur absent incorrecte");
				}
				if (idUt != null) {
					List<Compte> lCompte = bd.selectCompte(idUt);
					if (!lCompte.isEmpty()) {
						buffer.append("<h1>Les Comptes de ce client</h1>");
						buffer.append("<table>");
						buffer.append("<thead>");
						buffer.append("<tr>");
						buffer.append("<th>Solde</th>");
						buffer.append("<th>Taux</th>");
						buffer.append("<th>Seuil</th>");
						buffer.append("</tr>");
						buffer.append("</thead>");
						buffer.append("<tbody>");
						Iterator<Compte> iterCpt = lCompte.iterator();
						while (iterCpt.hasNext()) {
							Compte compte = iterCpt.next();
							// Fabrication de la page
							buffer.append("<tr>");
							buffer.append("<td>").append(compte.getSolde()).append("</td>");
							// On remarque ici que si on fait passer la methode
							// getTaux dans l'interface ICompteRemunere, alors
							// le code deviendrait beaucoup plus simple
							if (compte instanceof ICompteRemunere) {
								buffer.append("<td>").append(((ICompteRemunere) compte).getTaux()).append("</td>");
							} else {
								buffer.append("<td>--</td>");
							}
							// On remarque ici que si on fait passer la methode
							// getSeuil dans l'interface ICompteASeuil, alors
							// le code deviendrait beaucoup plus simple
							if (compte instanceof ICompteASeuil) {
								buffer.append("<td>").append(((ICompteASeuil) compte).getSeuil()).append("</td>");
							} else {
								buffer.append("<td>--</td>");
							}
							buffer.append("</tr>");
						}
						buffer.append("</table>");
					} else {
						buffer.append("Aucun compte.");
					}
				}
			}
			buffer.append("</body>");
			buffer.append("</html>");

			// On ecrit le flux de retour
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(buffer.toString());

		} catch (SQLException e) {
			// NE JAMAIS FAIRE EN WEB
			// e.printStackTrace();
			response.getWriter().print("Erreur dans la servlet (" + e.getMessage() + ")");
		} finally {
			if (bd != null) {
				bd.seDeconnecter();
			}
		}

	}
}
