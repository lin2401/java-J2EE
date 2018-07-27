package com.banque.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;

import com.banque.entity.IOperationEntity;
import com.banque.entity.impl.OperationEntity;

/**
 * Classe utilitaire qui va generer des operations.
 */
@Ignore
public final class OperationGenerator {
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructeur.
	 */
	private OperationGenerator() {
		throw new IllegalAccessError("Classe utilitaire");
	}

	/**
	 * Genere les operations.
	 *
	 * @param combien
	 *            le nombre d'operations
	 * @param surQui
	 *            id de compte
	 *
	 * @return la liste des comptes
	 */
	public static List<IOperationEntity> generateOperations(int combien, Integer surQui) {
		Random random = new Random();
		List<IOperationEntity> resultat = new ArrayList<IOperationEntity>(combien);
		for (int i = 0; i < combien; i++) {
			OperationGenerator.LOG.debug("Fabrication de l'operation {}/{}", String.valueOf(i),
					String.valueOf(combien));
			IOperationEntity op = new OperationEntity();
			op.setCompteId(surQui);
			boolean credit = random.nextBoolean();
			while (op.getMontant() == null || op.getMontant().doubleValue() < 10) {
				op.setMontant(Double.valueOf(random.nextInt(1000)));
			}
			if (credit) {
				op.setLibelle("Ajout de " + op.getMontant() + " sur le compte");
			} else {
				op.setLibelle("Retrait de " + op.getMontant() + " sur le compte");
				op.setMontant(Double.valueOf(-op.getMontant().doubleValue()));
			}
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(Calendar.YEAR, gc.get(Calendar.YEAR) - random.nextInt(20));
			gc.set(Calendar.DAY_OF_YEAR, random.nextInt(360) + 1);
			op.setDate(new Timestamp(gc.getTimeInMillis()));
			resultat.add(op);
		}
		return resultat;
	}
}
