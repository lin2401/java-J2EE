package com.banque.web;

/**
 * Clefs pour les pages et les Servlets
 */
@SuppressWarnings("squid:S1214")
public interface Clefs {
	/**
	 * Clef utilisee lors de l'authentification. Contiendra l'id de l'utilisateur.
	 */
	public static final String CLEF_AUTHENTIFICATION = "idUtilisateur";
	/** Clef utilisee pour l'utilisateur. Contiendra l'utilisateur. */
	public static final String CLEF_UTILISATEUR = "utilisateur";
	/** Clef utilisee pour contenir l'ip de l'utilisateur. */
	public static final String CLEF_UTILISATEUR_IP = "utilisateurIp";
	/** Clef utilisee pour les messages d'erreurs. */
	public static final String CLEF_ERREUR = "erreur";
	/** Clef utilisee pour la map des utilisateurs. */
	public static final String CLEF_UTILISATEURS_MAP = "mapUtilisateurs";

	/** L'URI de la page login. */
	public static final String PAGE_LOGIN = "login.jsp";

}
