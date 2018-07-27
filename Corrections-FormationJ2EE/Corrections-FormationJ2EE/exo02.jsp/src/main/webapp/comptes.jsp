<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="fr.bd.AccesBD, java.util.*, fr.banque.*, java.sql.*"%>
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
  <title>Tous les comptes</title>
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
  <h1>Les Comptes du client</h1>
  <%
  	String idUtilisateur = request.getParameter("id");
  	if ((idUtilisateur == null) || idUtilisateur.trim().isEmpty()) {
  		out.print("Id utilisateur absent.");
  	} else {
  		Integer idUt = null;
  		try {
  			idUt = Integer.valueOf(idUtilisateur);
  		} catch (Exception e) {
  			out.print("Id utilisateur invalide.");
  			return;
  		}
  		if (idUt != null) {
  			AccesBD bd = null;
  			List<Compte> lCompte = null;
  			// Recuperation des clients
  			try {
  				bd = new AccesBD(dbDriver);
  				bd.seConnecter(dbUrl, dbLogin, dbPwd);					
  				lCompte = bd.selectCompte(idUt);
  			} catch (SQLException e) {
  				out.print("Erreur dans la JSP (" + e.getMessage() + ")");
  				return;
  			} finally {
  				if (bd != null) {
  					bd.seDeconnecter();
  				}
  			}					
  			if (lCompte!=null && !lCompte.isEmpty()) {
  %>
  <table>
    <thead>
      <tr>
        <th>Solde</th>
        <th>Taux</th>
        <th>Seuil</th>
      </tr>
    </thead>
  
    <tbody>
  	<%
  		for(Compte compte : lCompte) {
  	%>
  	<tr> 
  		<td><%=compte.getSolde()%></td>
  		<% if (compte instanceof ICompteRemunere) { %>
  		<td><%=((ICompteRemunere) compte).getTaux()%></td>
  		<% } else { %>
  		<td>--</td>
  		<% }
  		   if (compte instanceof ICompteASeuil) {
  		%>
  		<td><%=((ICompteASeuil) compte).getSeuil()%></td>
  		<% } else { %>
  		<td>--</td>
  		<%} %>
  	</tr>
  	<%
  		} // fin du while
  	%>
        <tbody>
  </table>
  <%
  		} else {
  			out.print("Pas de compte pour cet utilisateur.");
  		}
  	} else {
  		out.print("Id utilisateur invalide.");
  	}
  } // Fin du else pas d'id
  %>
</body>
</html>
