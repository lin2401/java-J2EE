<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- On declare le fait que l'on va utiliser la taglib JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
  <h3><c:out value="${erreur}" /></h3>
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
  	<c:if test="${!empty listeClients}">
  		<c:forEach items="${listeClients}" var="client">
  			<tr>
  				<td><c:out value="${client.nom}" /></td>
  				<td><c:out value="${client.prenom}" /></td>
  				<td>
  					<c:if test="${client.age > 0 }">
  						<c:out value="${client.age}" />
  					</c:if>
  					<c:if test="${client.age <= 0 }">
  						&nbsp;
  					</c:if>
  				</td>
  				<td>
  					<form action="<c:url value="/ServletCompte"/>" method="post">
  						<input type="hidden" name="id" value="<c:out value="${client.numero}"/>"> <input type="submit" value="Voir ses comptes">
  					</form>
  				</td>
  			</tr>
  		</c:forEach>
  	</c:if>
    </tbody>
  </table>
  <c:if test="${empty listeClients}">
  	Pas de client.
  </c:if>
</body>
</html>
