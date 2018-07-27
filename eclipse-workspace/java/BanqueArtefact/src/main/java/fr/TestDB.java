package fr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.banque.Client;

public class TestDB {

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
//2 -- ouvrir une connexion sur la base de données
		// !!!mes import viennent de java.sql

		Connection c = null;
		Statement st = null;
		ResultSet resu = null;

		try {
			c = DriverManager.getConnection(url, login, pwd);
			// 3 requette SQL
			st = c.createStatement();
			resu = st.executeQuery(
					"SELECT id, nom, prenom, dateDeNaissance, year(curdate())-year(dateDeNaissance) as age FROM banque.utilisateur"
							+ "");

			List<Client> maListeDeClient = new ArrayList<>();

			while (resu.next()) {
				int monId = resu.getInt("id");

//				Object maDate = resu.getObject("dateDeNaissance");
//				if (maDate != null) {
//					System.out.println(maDate.getClass());
//				}
				int age = resu.getInt("age");
				String nom = resu.getString("nom");
				String prenom = resu.getString("prenom");
				Client client = new Client(nom, prenom, age, monId);
				maListeDeClient.add(client);

				// System.out.println(nom + " " + prenom + " " + age);
				/*
				 * c.colse(); st.close(); resu.colse();// il faut suivre lordre ur les fermer
				 * dabord le resu, ensuite st, et enfin la connexion //les closes doivent etre
				 * fermées dans le bloc finally
				 */

			}
			System.out.println(maListeDeClient);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
				if (st != null) {
					st.close();
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
