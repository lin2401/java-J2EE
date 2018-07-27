<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="128kb"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8" />
    <title>Exemple JMS - Producteur</title>
  </head>

  <body>
    <form action="<c:url value="JmsProducer"/>" method="post">
      Message : <textarea rows="5" cols="40" name="textMessage"></textarea><br />
      <input type="submit" value="Envoyer" />
    </form>
    <br />
    <a href="<c:url value="JmsConsumer"/>">Recevoir les messages</a>
    <br />
    <br />
    <br />
    <br />
    <a href="http://localhost:8161/">URL d'administration Active MQ (admin/admin)</a>
  </body>
</html>