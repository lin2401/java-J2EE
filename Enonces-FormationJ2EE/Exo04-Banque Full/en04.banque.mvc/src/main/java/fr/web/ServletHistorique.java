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
@WebServlet(urlPatterns = { "/ServletHistorique" })
public class ServletHistorique extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public ServletHistorique() {
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
		// Recupere l'id du compte qui est en parametre
		// Verifie qu'il n'est ni null ni vide
		// Verifie que c'est bien un chiffre > 0
		// Transforme le parametre de String vers Integer
		// Appel la methode selectOperation de AccesBD
		// Si tout va bien,
		// placer la liste des operations dans request
		// placer l'id du compte en tant qu'attribut de request ou session pour
		// pouvoir l'afficher et le reutiliser
		// Part vers la page comptes/historique.jsp
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
		// Recupere les parametres (id du compte, date debut, date fin, credit,
		// debit), a noter que l'id du compte est peut etre en tant qu'attribut
		// de session selon ce que vous avez fait en doGet
		// Verifie qu'ils ne sont ni null ni vides
		// Verifie que l'id est bien un chiffre (si en parametre), que les dates
		// sont correctes si elles sont la
		// Transforme le parametre de String vers Integer, java.sql.Date
		// Appel la methode selectOperation de AccesBD
		// Si tout va bien,
		// placer la liste des operations dans request
		// placer l'id du compte en tant qu'attribut de request ou session pour
		// pouvoir l'afficher et le reutiliser
		// placer les dates et le credit/debit en tant qu'attribut de request
		// pour pouvoir les afficher
		// Part vers la page comptes/historique.jsp
		// 3 - Si un probleme ou une erreur survient lors de la recuperation des
		// donnees
		// On place un message dans la request et on va vers la page menu
	}
}
