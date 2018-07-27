package com.banque.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.banque.web.Clefs;

/**
 * Va gerer la securisation de nos servlet et JSP. <br/>
 *
 * On filtre TOUS les URLs et on fait des if sur les ressources qui ne doivent
 * pas etre filtrees.<br/>
 *
 * Il n'y a plus de gestion dans les servlet et les JSPs, tout ce passe
 * ici.<br/>
 *
 * Une autre maniere de faire : filtrer uniquement les URL qui doivent l'etre
 * (les controleurs sauf login) et placer les JSP dans le repertoire
 * WEB-INF<br/>
 */
@WebFilter(urlPatterns = { "/*" })
public class SecurityFilter implements Filter {
	private static final Logger LOG = LogManager.getLogger();
	private static final String[] EXCLUDE_PATTERN = { "/ServletLogin", "login", "/images/", "/css/", "/js/", "fonts" };

	/**
	 * Constructeur.
	 */
	public SecurityFilter() {
		super();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		SecurityFilter.LOG.trace("Passage dans le filtre de securite");
		// C'est un ServletRequest qui est en parametre, nous avons besoin d'un
		// HttpServletRequest
		boolean foundPattern = false;
		if (request instanceof HttpServletRequest) {
			String uri = ((HttpServletRequest) request).getRequestURI();
			uri = uri.toLowerCase();
			for (String pattern : SecurityFilter.EXCLUDE_PATTERN) {
				if (uri.contains(pattern)) {
					SecurityFilter.LOG.trace("URI={} : on ne fait pas de filtre, on passe la ressource demande", uri);
					foundPattern = true;
					break;
				}
			} // For pattern
			if (!foundPattern) {
				SecurityFilter.LOG.trace("URI={} : on doit etre connecte, verification ...", uri);
				// On doit etre connecte
				Integer iduser = (Integer) ((HttpServletRequest) request).getSession(true)
						.getAttribute(Clefs.CLEF_AUTHENTIFICATION);
				if (iduser == null) {
					SecurityFilter.LOG.error("URI={} : on n'est pas connecte", uri);
					request.setAttribute(Clefs.CLEF_ERREUR, "Merci de vous authentifier");
					// On part vers login
					request.getRequestDispatcher(Clefs.PAGE_LOGIN).forward(request, response);
					// On ne passe pas la main
					return;
				}
				SecurityFilter.LOG.trace("URI={} : on n'est connecte {}", uri, iduser);
			}
		} else {
			SecurityFilter.LOG.error("Ne sait pas gerer les objet du type {}",
					request != null ? request.getClass().getName() : "null");
		}
		// On passe a la suite
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		SecurityFilter.LOG.trace("destroy du filtre de securite : Ne fait rien");
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		SecurityFilter.LOG.trace("init du filtre de securite : Ne fait rien");
	}

}
