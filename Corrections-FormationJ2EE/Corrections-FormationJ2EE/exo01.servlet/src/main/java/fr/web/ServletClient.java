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
			buffer.append("</head>");
			buffer.append("");
			buffer.append("<body>");
			if (!lClients.isEmpty()) {
				buffer.append("<h1>Les Clients</h1>");
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
					buffer.append("<form action=\"ServletCompte\" method=\"post\">");
					buffer.append("<input type=\"hidden\" name=\"id\" value=\"").append(client.getNumero())
							.append("\">");
					buffer.append("<input type=\"submit\" value=\"Voir ses comptes\">");
					buffer.append("</form>");
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
