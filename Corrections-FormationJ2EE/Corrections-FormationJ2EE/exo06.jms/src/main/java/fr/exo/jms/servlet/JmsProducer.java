package fr.exo.jms.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
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
		String jspDestination = "erreur_e.jsp";
		if (this.getJmsFactory() == null) {
			JmsProducer.LOG.error("La factory n'est pas initialisee");
			request.setAttribute("erreur", "JMS Factory invalide !");
		} else {
			Connection connection = null;
			Session session = null;
			MessageProducer producer = null;
			try {
				// On recupere une connexion sur le serveur JMS
				connection = this.getJmsFactory().createConnection();
				// On l'ouvre
				connection.start();
				// On cree une session
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				// On cree la queue (ou le topic)
				Destination destination = session.createQueue("jmsj2ee.queue");
				// On recupere un producteur sur la destination
				producer = session.createProducer(destination);
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
				// Pour le formatage de la date
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				// On fabrique le message
				String text = request.getParameter("textMessage") + " [" + sdf.format(new Date()) + "]";
				TextMessage message = session.createTextMessage(text);
				// On Envoie le message au serveur JMS
				producer.send(message);
				JmsProducer.LOG.info("Message: {} envoye.", text);
				jspDestination = "confirmation_e.jsp";
				// On replace le message dans la request
				request.setAttribute("message", text);
			} catch (Exception e) {
				request.setAttribute("erreur", "Erreur JMS: " + e.getMessage());
				JmsProducer.LOG.error("Erreur JMS", e);
			} finally {
				if (producer != null) {
					try {
						producer.close();
					} catch (JMSException e) {
						JmsProducer.LOG.error("Erreur JMS", e);
					}
				}
				if (session != null) {
					try {
						session.close();
					} catch (JMSException e) {
						JmsProducer.LOG.error("Erreur JMS", e);
					}
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (JMSException e) {
						JmsProducer.LOG.error("Erreur JMS", e);
					}
				}
			}
		}
		request.getRequestDispatcher(jspDestination).forward(request, response);
		JmsProducer.LOG.trace("doPost - end");
	}

}
