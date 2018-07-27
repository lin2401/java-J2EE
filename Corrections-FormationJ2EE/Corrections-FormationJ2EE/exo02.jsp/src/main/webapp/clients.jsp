<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="fr.bd.AccesBD, java.util.*, fr.banque.Client, java.sql.*"%>
<%
	final String dbDriver = "com.mysql.jdbc.Driver";
	final String dbUrl = "jdbc:mysql://localhost/banque?useSSL=true";
	final String dbLogin = "root";
	final String dbPwd = "";
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="utf-8" />
  <title>Tous les clients</title>
  <style>
    h1 { text-align: center; }
    td, th {
      text-align: center;
      border: 1px solid black;
    }
    th {
       text-transform: capitalize;
       text-shadow: 2px 2px lightgray;
       font-size: 1.5em;
    }
    table {
      margin: auto;
      width: 70%;
      border: 1px solid black;
    }
  </style>  
</head>

<body>
  <h1>Les Clients</h1>
  <%
  	AccesBD bd = null;
  	List<Client> lClients = null;
  	try {
  		bd = new AccesBD(dbDriver);
  		bd.seConnecter(dbUrl, dbLogin, dbPwd);
  		// Recuperation des clients
  		lClients = bd.selectUtilisateur();
  	} catch (SQLException e) {
  		out.print("Erreur dans la JSP (" + e.getMessage() + ")");
  		return;
  	} finally {
  		if (bd != null) {
  			bd.seDeconnecter();
  		}
  	}
  	if (lClients!=null && !lClients.isEmpty()) {
  %>
  <table>
        <thead>
          <tr>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Age</th>
            <th></th>
          </tr>
        </thead>
  
        <tbody>
  	<%
  		for(Client client : lClients) {
  	%>
  	<tr>
  		<td><%=client.getNom()%></td>
  		<td><%=client.getPrenom()%></td>
  		<td><%=client.getAge() > 0 ? String.valueOf(client.getAge()) : ""%></td>
  		<td>
  			<form action="comptes.jsp" method="post">
  				<input type="hidden" name="id" value="<%=client.getNumero()%>"> <input type="submit" value="Voir ses comptes">
  			</form>
  		</td>
  	</tr>
  	<%
  		} // fin du foreach
  	%>
        <tbody>      
  </table>
  <% } else { %>
  Pas de client.
  <% } %>
</body>
</html>
