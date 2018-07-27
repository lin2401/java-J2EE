package fr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.banque.Compte;
import fr.banque.CompteASeuil;
import fr.banque.CompteASeuilRemunere;
import fr.banque.CompteRemunere;

public class TestDB3 {

	public static void main(String[] args) {
		String login = "root";
		String pwd = "";
		// un driver est un nom de classe java
		String driver = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost/banque";

		// 1 -- charge le driver en memeoire, cette etape nest pas oblgatoire mais
		// conseillée
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		// 2 -- ouvrir une connexion sur la base de données
		// !!!mes import viennent de java.sql

		Connection c = null;
		PreparedStatement preparedStatement = null;
		ResultSet resu = null;

		try {
			c = DriverManager.getConnection(url, login, pwd);
			// 3 requette SQL
			preparedStatement = c.prepareStatement("SELECT * FROM banque.compte where utilisateurId=?");
			preparedStatement.setInt(1, 2);
			resu = preparedStatement.executeQuery();

			List<Compte> maListeDeCompte = new ArrayList<>();

			while (resu.next()) {
				int monId = resu.getInt("id");

//					Object maDate = resu.getObject("dateDeNaissance");
//					if (maDate != null) {
//						System.out.println(maDate.getClass());
//					}
				double solde = resu.getDouble("solde");
				double seuil = resu.getDouble("decouvert");
				boolean tauxEstNull = false;
				boolean seuilEstNull = false;
				if (resu.wasNull()) {
					seuilEstNull = true;
				}
				boolean decouvertEstNull = false;

				double taux = resu.getDouble("taux");
				if (resu.wasNull()) {
					tauxEstNull = true;
				}

				Compte compte = null;

				if (decouvertEstNull && tauxEstNull) {
					compte = new Compte(monId, solde);

				} else if (decouvertEstNull && !tauxEstNull) {
					compte = new CompteRemunere(monId, solde, taux);

				} else if (!decouvertEstNull && tauxEstNull) {
					compte = new CompteASeuil(monId, solde, seuil);

				} else {
					compte = new CompteASeuilRemunere(monId, solde, seuil, taux);
				}

				maListeDeCompte.add(compte);

			}

			// System.out.println(nom + " " + prenom + " " + age);
			/*
			 * c.colse(); st.close(); resu.colse();// il faut suivre lordre ur les fermer
			 * dabord le resu, ensuite st, et enfin la connexion //les closes doivent etre
			 * fermées dans le bloc finally
			 */

			System.out.println(maListeDeCompte);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				if (resu != null) {
					resu.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}