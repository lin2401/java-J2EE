package com.banque.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;

import com.banque.entity.ESex;
import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.impl.UtilisateurEntity;

/**
 * Classe utilitaire qui va generer des utilisateurs a partir des fichiers de
 * donnees.
 */
@Ignore
public final class UtilisateurGenerator {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur.
	 */
	private UtilisateurGenerator() {
		throw new IllegalAccessError("Classe utilitaire");
	}

	/**
	 * Genere les utilisateurs.
	 *
	 * @param combien
	 *            le nombre d'utilisateur
	 * @return la liste des utilisateurs
	 */
	public static List<IUtilisateurEntity> generateUtilisateurs(int combien) {
		List<String> noms = BanqueFileReader.lireNom();
		List<String> prenomsH = BanqueFileReader.lirePrenomHomme();
		List<String> prenomsF = BanqueFileReader.lirePrenomFemmes();
		List<String> adresse = BanqueFileReader.lireAdresses();
		Map<Integer, List<String>> villes = BanqueFileReader.lireVilles();
		Random random = new Random();
		List<IUtilisateurEntity> resultat = new ArrayList<IUtilisateurEntity>(combien);
		for (int i = 0; i < combien; i++) {
			UtilisateurGenerator.LOG.debug("Fabrication de l'utilisateur {}/{}", String.valueOf(i),
					String.valueOf(combien));
			IUtilisateurEntity utilisateur = new UtilisateurEntity();
			utilisateur.setNom(noms.get(random.nextInt(noms.size())));
			int sex = random.nextInt(2);
			if (sex == 0) {
				utilisateur.setSex(ESex.HOMME);
				utilisateur.setPrenom(prenomsH.get(random.nextInt(prenomsH.size())));
			} else {
				utilisateur.setSex(ESex.FEMME);
				utilisateur.setPrenom(prenomsF.get(random.nextInt(prenomsF.size())));
			}
			utilisateur.setAdresse(adresse.get(random.nextInt(adresse.size())));
			// 1000 a 98890
			List<String> nomVilles = null;
			int cp = -1;
			while (nomVilles == null) {
				cp = random.nextInt(98890 + 1);
				nomVilles = villes.get(Integer.valueOf(cp));
			}
			utilisateur.setCodePostal(Integer.valueOf(cp));
			utilisateur.setAdresse(utilisateur.getAdresse() + " " + nomVilles.get(random.nextInt(nomVilles.size())));
			utilisateur.setLogin(UtilisateurGenerator.generateLogin(utilisateur.getNom(), utilisateur.getPrenom()));
			if (random.nextInt(2) == 0) {
				utilisateur.setDerniereConnection(new Timestamp(System.currentTimeMillis()));
			}
			utilisateur.setPassword(utilisateur.getLogin());
			if (random.nextInt(2) == 0) {
				utilisateur.setDateDeNaissance(random.nextInt(31) + 1, random.nextInt(12) + 1,
						random.nextInt(55) + 1950);
			}
			if (random.nextInt(2) == 0) {
				StringBuilder nu = new StringBuilder();
				for (int j = 0; j < 10; j++) {
					nu.append(random.nextInt(10));
				}
				utilisateur.setTelephone(nu.toString());
			}
			resultat.add(utilisateur);
		}
		return resultat;
	}

	/**
	 * Genere un login
	 *
	 * @param unNom
	 *            le nom
	 * @param unPrenom
	 *            le prenom
	 * @return un login
	 */
	private static String generateLogin(String unNom, String unPrenom) {
		StringBuilder login = new StringBuilder();
		if (unPrenom.length() > 3) {
			login.append(unPrenom.substring(0, 3));
		} else {
			login.append(unPrenom);
		}
		login.append('.');
		if (unNom.length() > 3) {
			login.append(unNom.substring(0, 3));
		} else {
			login.append(unNom);
		}
		Random random = new Random();
		login.append(String.valueOf(random.nextInt(100) + 10));
		while (login.length() < 8) {
			login.append(String.valueOf(random.nextInt(10)));
		}
		return login.toString();
	}
}
