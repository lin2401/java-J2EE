package fr.exo.jms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet responsable de l'envoie des messages JMS.
 */
@WebServlet("/JmsProducer")
public class JmsProducer extends AbstractJmsServlet {
	private static final Logger LOG = LogManager.getLogger();
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	public JmsProducer() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JmsProducer.LOG.trace("doGet - start");
		// On respecte le MVC, on part vers la JSP envoyer
		request.getRequestDispatcher("envoyer.jsp").forward(request, response);
		JmsProducer.LOG.trace("doGet - end");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JmsProducer.LOG.trace("doPost - start");
		// On recupere une connexion sur le serveur JMS
		// On l'ouvre
		// On cree une session
		// On cree la queue (ou le topic)
		// On recupere un producteur sur la destination
		// On fait un SimpleDateFormat pour le formatage de la date
		// On fabrique le message
		// On envoie le message au serveur JMS
		// On place le message dans la request
		// On part vers la JSP confirmation_e.jsp
		// En cas d'erreur on place le message en request et on part vers la JSP
		// d'erreur erreur_e.jsp
		// Quoi qu'il arrive on ferme tous les objets relatives à JMS
		JmsProducer.LOG.trace("doPost - end");
	}

}
