package fr.exo.jms.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet pour la consommation des messages.
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
		String jspDestination = "erreur_c.jsp";
		if (this.getJmsFactory() == null) {
			JmsConsumer.LOG.error("La factory n'est pas initialisee");
			request.setAttribute("erreur", "JMS Factory invalide !");
		} else {
			MessageConsumer consumer = null;
			Connection connection = null;
			Session session = null;
			try {
				// On recupere une connexion sur le serveur JMS
				connection = this.getJmsFactory().createConnection();
				// On l'ouvre'
				connection.start();
				// On cree une session
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				// On cree la queue (ou le topic)
				Destination destination = session.createQueue("jmsj2ee.queue");
				// On recupere un consommateur sur la destination
				consumer = session.createConsumer(destination);
				List<String> tousLesMessages = new ArrayList<String>();
				Message message = null;
				// On recupere tous les messages
				do {
					message = consumer.receive(500);
					if (message instanceof TextMessage) {
						TextMessage tm = (TextMessage) message;
						tousLesMessages.add(tm.getText());
					}
				} while (message != null);
				JmsConsumer.LOG.info("Recuperation de {} messages. ", Integer.valueOf(tousLesMessages.size()));
				jspDestination = "recevoir.jsp";
				// On les place dans la request
				request.setAttribute("messages", tousLesMessages);
			} catch (Exception e) {
				request.setAttribute("erreur", "Erreur JMS: " + e.getMessage());
				JmsConsumer.LOG.error("Erreur JMS", e);
			} finally {
				if (consumer != null) {
					try {
						consumer.close();
					} catch (JMSException e) {
						JmsConsumer.LOG.error("Erreur JMS", e);
					}
				}
				if (session != null) {
					try {
						session.close();
					} catch (JMSException e) {
						JmsConsumer.LOG.error("Erreur JMS", e);
					}
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (JMSException e) {
						JmsConsumer.LOG.error("Erreur JMS", e);
					}
				}
			}
		}
		request.getRequestDispatcher(jspDestination).forward(request, response);
		JmsConsumer.LOG.trace("doGet - end");
	}

}
