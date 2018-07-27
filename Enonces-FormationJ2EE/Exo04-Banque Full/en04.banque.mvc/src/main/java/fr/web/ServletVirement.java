package fr.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet qui va afficher les historiques d'operation d'un client. <br/>
 */
@WebServlet(urlPatterns = { "/ServletVirement" })
public class ServletVirement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public ServletVirement() {
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
		// Part vers la page comptes/virement.jsp
		// 3 - Si un probleme ou une erreur survient lors de la recuperation des
		// donnees
		// On place un message dans la request et on va vers la page menu
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 - Verifie que l'utilisateur est bien connecte
		// Regarde dans la session si l'information placee par la servlet login
		// est bien la
		// Si pas la => Direction page login avec un message
		// Sinon, on continue
		// 2 - Se connecte a la base de donnees via fr.bd.AccesBD
		// Recupere les 3 parametres de la request
		// Verifie qu'ils ne sont ni null ni vides
		// Verifie que les id de comptes ainsi que le montant sont des chiffres
		// > 0
		// Transforme les parametres de String vers Integer ou Double
		// Appel la methode faireVirement de AccesBD
		// Si tout va bien,
		// placer un message de confirmation dans request
		// placer les 3 parametres en tant qu'attribut de request pour pouvoir
		// les reafficher
		// si la liste des comptes etaient dans le scope request, aller la
		// rechercher par AccesBD et la placer dans la request
		// Part vers la page comptes/virement.jsp
		// 3 - Si un probleme ou une erreur survient lors du virement
		// On place un message dans la request et on va vers la page virement
		// placer les 3 parametres en tant qu'attribut de request pour pouvoir
		// les reafficher
		// si la liste des comptes etaient dans le scope request, aller la
		// rechercher par AccesBD et la placer dans la request
	}
}
