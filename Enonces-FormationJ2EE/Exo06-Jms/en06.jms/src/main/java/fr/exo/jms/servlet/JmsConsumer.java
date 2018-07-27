package fr.exo.jms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet pour la consommation des messages JMS.
 */
@WebServlet("/JmsConsumer")
public class JmsConsumer extends AbstractJmsServlet {
	private static final Logger LOG = LogManager.getLogger();
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public JmsConsumer() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JmsConsumer.LOG.trace("doGet - start");
		// On recupere une connexion sur le serveur JMS
		// On l'ouvre
		// On cree une session
		// On cree la queue (ou le topic)
		// On recupere un consommateur sur la destination
		// On recupere tous les messages
		// On les place dans le scope request
		// On part vers la JSP recevoir.jsp
		// En cas d'erreur on place le message en request et on part vers la JSP
		// d'erreur erreur_c.jsp
		// Quoi qu'il arrive on ferme tous les objets relatives à JMS
		JmsConsumer.LOG.trace("doGet - end");
	}

}
