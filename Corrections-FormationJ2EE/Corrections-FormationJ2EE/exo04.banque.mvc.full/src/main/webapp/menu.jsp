<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="128kb"%>
<%-- On declare le fait que l'on va utiliser la taglib JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />

  <title>Banque - Menu</title>
  <link href="<c:url value="/css/bootstrap.min.css"/>"  rel="stylesheet" />
  <link href="<c:url value="/css/banque.css"/>"         rel="stylesheet" />
  <link href="<c:url value="/css/jquery-ui.min.css"/>"  rel="stylesheet" />


  <!--[if lt IE 9]>
      <script src="<c:url value="/js/html5shiv.3.7.3.min.js"/>"></script>
      <script src="<c:url value="/js/respond.1.4.2.min.js"/>"></script>
  <![endif]-->

  <script src="<c:url value="/js/jquery.1.12.4.min.js"/>"></script>
  <script src="<c:url value="/js/jquery-ui.min.js"/>"></script>
  <script src="<c:url value="/js/i18n/datepicker-fr.js"/>"></script>
</head>

<body class="container-fluid elbody" data-gatling="menu">
  <span id="gatling_menu" style="display: none;"></span>
  <!-- jmeter:menu -->
  <div class="row">
    <header class="col-sm-12 hidden-xs">
      <img src="<c:url value="images/image-bienvenue.jpg"/>" alt="" class="img-responsive center-block" />
    </header>

    <section class="col-xs-12 col-sm-6 col-sm-offset-3" style="margin-top: 25px">

      <c:if test="${!empty requestScope.erreur}">
        <div class="alert alert-danger"><c:out value="${requestScope.erreur}" /></div>
      </c:if> <c:if test="${!empty requestScope.message}">
        <div class="alert alert-success"><c:out value="${requestScope.message}" /></div>
      </c:if>

      <div class="panel-body">
        <div class="alert alert-info">Bonjour <c:out value="${sessionScope.utilisateur.nom} ${sessionScope.utilisateur.prenom}" /></div>
        <div class="list-group">
          <a class="list-group-item" href="<c:url value="/ServletCompte"/>"><img src="<c:url value="/images/puce.gif"/>" width="13" height="18" alt="" /> Liste de vos comptes</a>
          <a class="list-group-item" href="<c:url value="/ServletVirement"/>"><img src="<c:url value="/images/puce.gif"/>" width="13" height="18" alt="" /> Virement</a>
          <a class="list-group-item" href="<c:url value="/ServletUtilisateursConnectes"/>"><img src="<c:url value="/images/puce.gif"/>" width="13" height="18" alt="" /> Utilisateurs connectés</a>
          <a class="list-group-item" href="<c:url value="/ServletLogout"/>"><img src="<c:url value="/images/puce.gif"/>" width="13" height="18" alt="" /> Se Deconnecter</a>
        </div>
      </div>
    </section>
  </div>

  <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</body>

</html>
