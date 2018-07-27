<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="128kb"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <title>Erreur - Production</title>
  </head>

  <body>
    Le Message : <textarea rows="5" cols="40" disabled="disabled"><c:out value="${requestScope.message}" escapeXml="false" /></textarea>
     n'a pas été envoyé, l'erreur est : <c:out value="${requestScope.erreur}" />.
    <br />
    <br />
    <a href="<c:url value="JmsProducer"/>">Réessayer</a>
    <br />
    <a href="<c:url value="JmsConsumer"/>">Recevoir les messages</a>
    <br />
    <br />
    <br />
    <br />
    <a href="http://localhost:8161/">URL d'administration Active MQ (admin/admin)</a>
  </body>
</html>