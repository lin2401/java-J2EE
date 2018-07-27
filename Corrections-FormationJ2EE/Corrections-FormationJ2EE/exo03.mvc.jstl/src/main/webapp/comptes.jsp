<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="128kb"%>
<%-- On declare le fait que l'on va utiliser la taglib JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
  <h3><c:out value="${erreur}" /></h3>
  <table>
    <thead>
      <tr>
        <th>Solde</th>
        <th>Taux</th>
        <th>Seuil</th>
      </tr>
    </thead>

    <tbody>
  	<%-- Le code presente ici est tres mauvais car il fait usage du nom long de la classe sous forme de chaine de caracteres --%>
  	<%-- L'affichage ici est trop 'intelligent' --%>
  	<%-- Le controleur (notre servlet) devrait transformer nos objets Compte en autre chose adapte a l'affichafe = un modele --%>						
  
  	<c:if test="${!empty listeComptes}">
  		<c:forEach items="${listeComptes}" var="compte">
  			<tr>
  				<td><c:out value="${compte.solde}" /></td>
  				<c:if test="${compte.getClass().name =='fr.banque.CompteASeuilRemunere' || compte.getClass().name =='fr.banque.CompteRemunere'}">
  					<td><c:out value="${compte.taux}" /></td>
  				</c:if>
  				<c:if test="${compte.getClass().name !='fr.banque.CompteASeuilRemunere' && compte.getClass().name !='fr.banque.CompteRemunere'}">
  					<td>--</td>
  				</c:if>
  				
  				<c:if test="${compte.getClass().name =='fr.banque.CompteASeuilRemunere' || compte.getClass().name =='fr.banque.CompteASeuil'}">
  					<td><c:out value="${compte.seuil}" /></td>
  				</c:if>
  				<c:if test="${compte.getClass().name !='fr.banque.CompteASeuilRemunere' && compte.getClass().name !='fr.banque.CompteASeuil'}">
  					<td>--</td>
  				</c:if>
  			</tr>
  		</c:forEach>
  	</c:if>
    </tbody>
  </table>
  <c:if test="${empty listeComptes}">
  	Pas de compte pour cet utilisateur.
  </c:if>
</body>
</html>
