package fr.exo.jms.servlet;

import javax.jms.ConnectionFactory;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet JMS abstraite servant au consommatteur et producteur. <br/>
 *
 * N'oubliez pas d'installer et de demarrer ActiveMQ. <br/>
 */
public class AbstractJmsServlet extends HttpServlet {
	private static final Logger LOG = LogManager.getLogger();
	/** Url de connexion pour le service JMS. */
	public static final String JMS_URI = "tcp://localhost:61616?wireFormat.maxInactivityDuration=0";
	private ConnectionFactory jmsFactory;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur.
	 */
	protected AbstractJmsServlet() {
		super();
		AbstractJmsServlet.LOG.debug("N'oubliez pas d'installer et démarrer ActiveMQ");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		AbstractJmsServlet.LOG.trace("Init - start");
		super.init(config);
		this.jmsFactory = new ActiveMQConnectionFactory(AbstractJmsServlet.JMS_URI);
		AbstractJmsServlet.LOG.trace("Init - ok");
	}

	/**
	 * Donne acces a la factory JMS.
	 *
	 * @return la factory JMS
	 */
	protected final ConnectionFactory getJmsFactory() {
		return this.jmsFactory;
	}
}
