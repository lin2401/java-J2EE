package com.banque.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;

/**
 * Classe utilitaire qui va lire les fichiers afin d'en sortir les donnees sous
 * forme de List ou de Map.
 */
@Ignore
public final class BanqueFileReader {
	private static final Logger LOG = LogManager.getLogger();

	/** Chemin vers le fichier nom. */
	private static final String PATH_NOM = "noms.txt";
	/** Chemin vers le fichier prenom pour homes. */
	private static final String PATH_PRENOM_H = "prenomH.txt";
	/** Chemin vers le fichier prenom pour femmes. */
	private static final String PATH_PRENOM_F = "prenomF.txt";
	/** Chemin vers le fichier ville. */
	private static final String PATH_VILLE = "ville.txt";
	/** Chemin vers le fichier adresse. */
	private static final String PATH_ADDRESS = "address.txt";
	/** Chemin vers le fichier libelles. */
	private static final String PATH_LIBELLES = "libelles.txt";

	/** Charset a utiliser. */
	// private static final Charset CHARSET = Charset.forName("UTF-8");
	public static final Charset CHARSET = StandardCharsets.UTF_8;

	/**
	 * Constructeur.
	 */
	private BanqueFileReader() {
		throw new IllegalAccessError("Classe utilitaire");
	}

	/**
	 * Lecture des noms.
	 *
	 * @return les noms.
	 */
	public static List<String> lireNom() {
		try {
			return BanqueFileReader.lire(BanqueFileReader.PATH_NOM);
		} catch (IOException e) {
			BanqueFileReader.LOG.error("Erreur lors de la lecture du fichier {}", BanqueFileReader.PATH_NOM, e);
		}
		return Collections.emptyList();
	}

	/**
	 * Lecture des libelles de comptes.
	 *
	 * @return des libelles de comptes.
	 */
	public static List<String> lireLibellesComptes() {
		try {
			return BanqueFileReader.lire(BanqueFileReader.PATH_LIBELLES);
		} catch (IOException e) {
			BanqueFileReader.LOG.error("Erreur lors de la lecture du fichier {}", BanqueFileReader.PATH_LIBELLES, e);
		}
		return Collections.emptyList();
	}

	/**
	 * Lecture des prenoms hommes.
	 *
	 * @return les prenoms hommes.
	 */
	public static List<String> lirePrenomHomme() {
		try {
			return BanqueFileReader.lire(BanqueFileReader.PATH_PRENOM_H);
		} catch (IOException e) {
			BanqueFileReader.LOG.error("Erreur lors de la lecture du fichier {}", BanqueFileReader.PATH_PRENOM_H, e);
		}
		return Collections.emptyList();
	}

	/**
	 * Lecture des prenoms femmes.
	 *
	 * @return les prenoms femmes.
	 */
	public static List<String> lirePrenomFemmes() {
		try {
			return BanqueFileReader.lire(BanqueFileReader.PATH_PRENOM_F);
		} catch (IOException e) {
			BanqueFileReader.LOG.error("Erreur lors de la lecture du fichier {}", BanqueFileReader.PATH_PRENOM_F, e);
		}
		return Collections.emptyList();
	}

	/**
	 * Lecture des adresses.
	 *
	 * @return les adresses.
	 */
	public static List<String> lireAdresses() {
		try {
			return BanqueFileReader.lire(BanqueFileReader.PATH_ADDRESS);
		} catch (IOException e) {
			BanqueFileReader.LOG.error("Erreur lors de la lecture du fichier {}", BanqueFileReader.PATH_ADDRESS, e);
		}
		return Collections.emptyList();
	}

	/**
	 * Lecture des villes.
	 *
	 * @return les villes.
	 */
	public static Map<Integer, List<String>> lireVilles() {
		Map<Integer, List<String>> result = new HashMap<Integer, List<String>>();
		try (BufferedReader br = Files.newBufferedReader(
				Paths.get(ClassLoader.getSystemResource("data/" + BanqueFileReader.PATH_VILLE).toURI()),
				BanqueFileReader.CHARSET);) {
			String ligne = null;
			Integer dernierCp = null;
			int id = 0;
			while ((ligne = br.readLine()) != null) {
				if (id % 2 == 0) {
					// un cp
					try {
						dernierCp = Integer.valueOf(ligne);
					} catch (NumberFormatException e) {
						BanqueFileReader.LOG.error("Ligne {} : Le code postal {} n'est pas un chiffre ",
								Integer.valueOf(id), ligne, e);
						break;
					}
				} else {
					// une ville
					List<String> villes = result.get(dernierCp);
					if (villes == null) {
						villes = new ArrayList<String>();
						result.put(dernierCp, villes);
					}
					villes.add(ligne);
				}
				id++;
			}
		} catch (Exception e) {
			BanqueFileReader.LOG.error("Erreur lors de la lecture du fichier {}", BanqueFileReader.PATH_VILLE, e);
		}

		return result;
	}

	/**
	 * Lecture des fichiers ligne a ligne.
	 *
	 * @param unChemin
	 *            un chemin
	 * @return les lignes du fichier
	 * @throws IOException
	 *             si une erreur survient
	 */
	private static List<String> lire(String unChemin) throws IOException {
		List<String> result = new ArrayList<String>();
		Path unPath;
		try {
			unPath = Paths.get(ClassLoader.getSystemResource("data/" + unChemin).toURI());
		} catch (URISyntaxException e) {
			throw new IOException("Chemin " + unChemin + " invalide", e);
		}

		if (!Files.exists(unPath)) {
			throw new IOException("Fichier " + unPath + " introuvable");
		}
		if (!Files.isReadable(unPath)) {
			throw new IOException("Fichier " + unPath + " pas lisible");
		}
		if (!Files.isRegularFile(unPath)) {
			throw new IOException("Fichier " + unPath + " pas un fichier");
		}
		int id = 0;
		try (BufferedReader br = Files.newBufferedReader(unPath, BanqueFileReader.CHARSET);) {
			String ligne = null;
			while ((ligne = br.readLine()) != null) {
				result.add(ligne);
				id++;
			}
		} catch (IOException e) {
			throw new IOException("Erreur lors de la lecture du fichier " + unPath + " ligne " + id, e);
		}

		return result;
	}
}
