package fr.web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.banque.Operation;
import fr.bd.AccesBD;

/**
 * Servlet qui va afficher les historiques d'operation d'un client. <br/>
 */
@WebServlet(urlPatterns = { "/ServletHistorique" })
public class ServletHistorique extends HttpServlet {
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
				// Sinon, on cherche l'id du compte dans la request en tant que
				// parametre (url ou formulaire)
				String idCpt = request.getParameter("inNumeroCompte");
				Integer compteId = null;
				try {
					compteId = Integer.valueOf(idCpt);
				} catch (Exception e1) {
					request.setAttribute("erreur", "L'id du compte est invalide (" + e1.getMessage() + ")");
					request.getRequestDispatcher("menu.jsp").forward(request, response);
					return;
				}
				request.setAttribute("cptId", compteId);
				// On regarde si on a des criteres pour les operations
				String inDateDebut = request.getParameter("inDateDebut");
				String inDateFin = request.getParameter("inDateFin");
				String inCredit = request.getParameter("inCredit");
				String inDebit = request.getParameter("inDebit");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				// Pas de calcul heuristique
				sdf.setLenient(false);

				Date dateDeb = null;
				if (inDateDebut != null && !inDateDebut.trim().isEmpty()) {
					try {
						dateDeb = new Date(sdf.parse(inDateDebut.trim()).getTime());
					} catch (Exception e) {
						request.setAttribute("erreur",
								"Votre date de debut n'est pas correcte (" + e.getMessage() + ")");
						request.getRequestDispatcher("comptes/historique.jsp").forward(request, response);
						return;
					}
				}

				Date dateFin = null;
				if (inDateFin != null && !inDateFin.trim().isEmpty()) {
					try {
						dateFin = new Date(sdf.parse(inDateFin.trim()).getTime());
					} catch (ParseException e) {
						request.setAttribute("erreur", "Votre date de fin n'est pas correcte (" + e.getMessage() + ")");
						request.getRequestDispatcher("comptes/historique.jsp").forward(request, response);
						return;
					}
				}

				if (dateFin != null && dateDeb != null && dateFin.before(dateDeb)) {
					Date tmp = dateFin;
					dateFin = dateDeb;
					dateDeb = tmp;
				}

				// Par defaut un checkbox vaut "true" (car on a placé un value dessus, sinon
				// c'est 'on') si il est coche ou null si il ne l'est pas
				Boolean crediDebit = null;
				if (inCredit != null && inDebit == null) {
					crediDebit = Boolean.TRUE;
					request.setAttribute("pCredit", "true");
				} else if (inCredit == null && inDebit != null) {
					crediDebit = Boolean.FALSE;
					request.setAttribute("pDebit", "true");
				} else {
					request.setAttribute("pCredit", "true");
					request.setAttribute("pDebit", "true");
				}

				List<Operation> listeOperations = AccesBD.getInstance().selectOperation(compteId.intValue(), dateDeb,
						dateFin, crediDebit);
				// On les places dans la request
				request.setAttribute("listeOperations", listeOperations);
				// On replace l'id et les parametres afin de pouvoir les
				// reafficher

				request.setAttribute("pDateDebut", inDateDebut);
				request.setAttribute("pDateFin", inDateFin);

				// On part vers la page qui liste les operations
				request.getRequestDispatcher("comptes/historique.jsp").forward(request, response);
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
