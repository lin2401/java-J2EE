package com.banque.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;

import com.banque.entity.ICompteEntity;
import com.banque.entity.impl.CompteEntity;

/**
 * Classe utilitaire qui va generer des comptes a partir des fichiers de
 * donnees.
 */
@Ignore
public final class CompteGenerator {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur.
	 */
	private CompteGenerator() {
		throw new IllegalAccessError("Classe utilitaire");
	}

	/**
	 * Genere les comptes.
	 *
	 * @param combien
	 *            le nombre de compte
	 * @param pourQui
	 *            l'id de l'utilisateur concerné
	 *
	 * @return la liste des comptes
	 */
	public static List<ICompteEntity> generateComptes(int combien, Integer pourQui) {
		List<String> libelles = BanqueFileReader.lireLibellesComptes();
		Random random = new Random();
		List<ICompteEntity> resultat = new ArrayList<ICompteEntity>(combien);
		for (int i = 0; i < combien; i++) {
			CompteGenerator.LOG.debug("Fabrication du compte {}/{}", String.valueOf(i), String.valueOf(combien));
			ICompteEntity cpt = new CompteEntity();
			cpt.setUtilisateurId(pourQui);
			cpt.setLibelle(libelles.get(random.nextInt(libelles.size())));
			while (cpt.getSolde() == null || cpt.getSolde().doubleValue() < 1000) {
				cpt.setSolde(Double.valueOf(random.nextInt(10000)));
			}
			if (random.nextBoolean()) {
				cpt.setDecouvert(Double.valueOf(50D + random.nextInt(300)));
			}
			if (random.nextBoolean()) {
				cpt.setTaux(Double.valueOf(random.nextDouble()));
			}
			resultat.add(cpt);
		}
		return resultat;
	}
}
