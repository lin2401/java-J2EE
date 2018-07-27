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

import fr.banque.Client;
import fr.bd.AccesBD;

/**
 * Servlet qui va afficher tous les clients. <br/>
 *
 * Dans cette solution, on ne fait pas un formulaire par ligne, on fait un
 * seul et unique formulaire et on fait usage du JavaScript pour pousser
 * l'information avant de le soumettre.
 */
@WebServlet(description = "Affiche tous les clients", urlPatterns = { "/ServletClientJs" })
public class ServletClientJs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/banque?useSSL=false";
	private static final String DB_LOGIN = "root";
	private static final String DB_PWD = "";

	/**
	 * Constructeur.
	 */
	public ServletClientJs() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccesBD bd = null;
		try {
			// Recuperation des clients
			bd = new AccesBD(ServletClientJs.DB_DRIVER);
			bd.seConnecter(ServletClientJs.DB_URL, ServletClientJs.DB_LOGIN, ServletClientJs.DB_PWD);
			List<Client> lClients = bd.selectUtilisateur();
			StringBuilder buffer = new StringBuilder();
			buffer.append("<!DOCTYPE html>");
			buffer.append("<html lang=\"fr\">");
			buffer.append("<head>");
			buffer.append("<meta charset=\"utf-8\" />");
			buffer.append("<title>Tous les clients</title>");
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
			buffer.append("<script>\n");
			buffer.append("function goToCompte(idUser) {\n");
			buffer.append("	document.getElementById(\"id\").value = idUser;\n");
			buffer.append("	document.getElementById(\"formCpt\").submit();\n");
			buffer.append("}\n");
			buffer.append("</script>\n");
			buffer.append("</head>");
			buffer.append("<body>");
			if (!lClients.isEmpty()) {
				buffer.append("<h1>Les Clients</h1>");
				buffer.append("<form id=\"formCpt\" action=\"ServletCompte\" method=\"post\">");
				buffer.append("<input type=\"hidden\" name=\"id\" id=\"id\" value=\"\">");
				buffer.append("</form>");
				buffer.append("<table>");
				buffer.append("<thead>");
				buffer.append("<tr>");
				buffer.append("<th>Nom</th>");
				buffer.append("<th>Prenom</th>");
				buffer.append("<th>Age</th>");
				buffer.append("<th></th>");
				buffer.append("</tr>");
				buffer.append("</thead>");
				buffer.append("<tbody>");
				Iterator<Client> iterClient = lClients.iterator();
				while (iterClient.hasNext()) {
					Client client = iterClient.next();
					// Fabrication de la page
					buffer.append("<tr>");
					buffer.append("<td>").append(client.getNom()).append("</td>");
					buffer.append("<td>").append(client.getPrenom()).append("</td>");
					if (client.getAge() > 0) {
						buffer.append("<td>").append(client.getAge()).append("</td>");
					} else {
						buffer.append("<td>&nbsp;</td>");
					}
					buffer.append("<td>");
					buffer.append("  <button type=\"button\" onclick=\"goToCompte(").append(client.getNumero())
							.append(");\">Voir ses comptes</button>");
					buffer.append("</td>");
					buffer.append("</tr>");
				}
				buffer.append("</tbody>");
				buffer.append("</table>");
			} else {
				buffer.append("Aucun client.");
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
