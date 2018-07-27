<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="128kb"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <title>Exemple JMS - Consommateur</title>
  </head>

  <body>
    <c:if test="${!empty requestScope.messages}">
      <c:forEach items="${requestScope.messages}" var="msg">
  			Message : <textarea rows="5" cols="40" name="textMessage" disabled="disabled"><c:out value="${msg}" /></textarea><br />
      </c:forEach>
    </c:if>
    <c:if test="${empty requestScope.messages}">Aucun Message.<br /></c:if>
    <br />
    <a href="<c:url value="JmsProducer"/>">Envoyer un message</a>
    <br />
    <a href="<c:url value="JmsConsumer"/>">Recevoir les messages</a>
    <br />
    <br />
    <br />
    <br />
    <a href="http://localhost:8161/">URL d'administration Active MQ (admin/admin)</a>
  </body>
</html>