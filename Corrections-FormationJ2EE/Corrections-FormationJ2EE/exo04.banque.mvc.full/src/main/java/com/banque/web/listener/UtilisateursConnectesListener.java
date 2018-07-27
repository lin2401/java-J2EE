package com.banque.web.listener;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.entity.IUtilisateurEntity;
import com.banque.web.Clefs;
import com.banque.web.modele.UtilisateurBean;

/**
 * Listener qui va nous permettre de gerer une liste d'utilisateur connectes.
 * <br/>
 *
 * Lors de l'ajout de l'attibut CLEF_UTILISATEUR dans la session on ajoute
 * l'utilisateur dans la liste des utilisateurs connectes. <br/>
 *
 * Lors de la supression de l'attibut CLEF_UTILISATEUR dans la session on retire
 * l'utilisateur dans la liste des utilisateurs connectes. <br/>
 *
 * ServletRequestListener : nous permet de lier la request à notre listener, de
 * cette manière on peut y faire appel pour récupérer l'ip de l'utilisateur.
 * <br/>
 *
 * Remarque : ce listener doit aussi gerer le cas de la session qui tombe en
 * time out, c'est pour cela qu'il implemente aussi l'interface
 * HttpSessionListener. <br/>
 */
@WebListener
public class UtilisateursConnectesListener implements HttpSessionAttributeListener, HttpSessionListener {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur.
	 */
	public UtilisateursConnectesListener() {
		super();
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// Clef utilise pour l'attribut de session
		String eventName = event.getName();
		// Valeur utilise pour l'attribut de session
		Object eventValue = event.getValue();
		HttpSession session = event.getSession();
		UtilisateursConnectesListener.LOG
				.trace("[E-attributeAdded] Ajout dans la session d'un attribut sous la clef {}", eventName);
		if (Clefs.CLEF_UTILISATEUR.equals(eventName)) {
			IUtilisateurEntity user = (IUtilisateurEntity) eventValue;
			this.addToList(session, user);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String eventName = event.getName();
		Object eventValue = event.getValue();
		HttpSession session = event.getSession();
		UtilisateursConnectesListener.LOG.trace(
				"[E-attributeRemoved] Supression dans la session d'un attribut sous la clef {} valeur {}", eventName,
				eventValue);
		if (Clefs.CLEF_UTILISATEUR.equals(eventName)) {
			this.removeFromList(session);
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// Ne fait rien car les session sont crees lors du premier appel a une
		// JSP et pas lors de l'authentification
		UtilisateursConnectesListener.LOG.trace("[E-sessionCreated] ne fait rien");

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// Nous devons gerer le cas ou l'utilisateur aura sa session qui part en
		// timeout
		HttpSession session = event.getSession();
		UtilisateursConnectesListener.LOG.trace("[E-sessionDestroyed] Destruction dans la session {}", session.getId());
		this.removeFromList(session);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		String eventName = event.getName();
		Object eventValue = event.getValue();
		HttpSession session = event.getSession();
		UtilisateursConnectesListener.LOG.trace(
				"[E-attributeReplaced] Remplacement dans la session d'un attribut sous la clef {} valeur {}", eventName,
				eventValue);
		if (Clefs.CLEF_UTILISATEUR.equals(eventName)) {
			this.removeFromList(session);
			IUtilisateurEntity user = (IUtilisateurEntity) eventValue;
			this.addToList(session, user);
		}
	}

	/**
	 * Retire de la liste des utilisateurs celui qui est concerne.
	 *
	 * @param session
	 *            la session
	 */
	private synchronized void removeFromList(HttpSession session) {
		if (session == null) {
			return;
		}
		ServletContext context = session.getServletContext();
		UtilisateursConnectesListener.LOG.debug("Supression de l'utilisateur ayant l'id de session {}",
				session.getId());
		// On cherche la liste dans le context
		@SuppressWarnings("unchecked")
		Map<String, UtilisateurBean> liste = (Map<String, UtilisateurBean>) context
				.getAttribute(Clefs.CLEF_UTILISATEURS_MAP);
		if (liste == null) {
			// rien a faire
		} else {
			UtilisateurBean bu = liste.remove(session.getId());
			if (bu != null) {
				UtilisateursConnectesListener.LOG.debug("Utilisateur {} avec id de session {} retire de la map", bu,
						session.getId());
			}
		}
	}

	/**
	 * Ajoute a la liste des utilisateurs celui qui est concerne.
	 *
	 * @param session
	 *            la session
	 */
	private synchronized void addToList(HttpSession session, IUtilisateurEntity eventValue) {
		if (session == null) {
			return;
		}
		ServletContext context = session.getServletContext();
		// On cherche la liste dans le context
		@SuppressWarnings("unchecked")
		Map<String, UtilisateurBean> liste = (Map<String, UtilisateurBean>) context
				.getAttribute(Clefs.CLEF_UTILISATEURS_MAP);
		if (liste == null) {
			UtilisateursConnectesListener.LOG.trace("La map utilisateurs va être créée");
			// premiere personne qui se connecte sur le site, la liste
			// n'existe pas encore
			// Il est important de faire usage d'un objet thread safe
			liste = new Hashtable<>();
			context.setAttribute(Clefs.CLEF_UTILISATEURS_MAP, liste);
		}

		UtilisateurBean bu = new UtilisateurBean();
		String ip = (String) session.getAttribute(Clefs.CLEF_UTILISATEUR_IP);
		bu.remplir(eventValue, ip);
		UtilisateursConnectesListener.LOG.debug("IP={} pour utilisateur={}", ip, eventValue.getId());
		UtilisateurBean bu2 = liste.put(session.getId(), bu);
		if (bu2 == null) {
			UtilisateursConnectesListener.LOG.debug("Ajout d'un nouvel utilisateur {} ayant l'id de session {}", bu,
					session.getId());
		} else {
			UtilisateursConnectesListener.LOG.warn("Ecrasement de l'utilisateur {} ayant l'id de session {}", bu,
					session.getId());
		}
	}
}
