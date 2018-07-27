<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="128kb"%>
<%-- On declare le fait que l'on va utiliser la taglib JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="fr">

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />

  <title>Banque - Liste des utilisateurs</title>
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

<body class="container-fluid elbody">
  <span id="gatling_listeutilisateur" style="display: none;"></span>
  <!-- jmeter:listeutilisateur -->
  <div class="row">
    <header class="col-sm-12 hidden-xs">
      <img src="<c:url value="images/image-bienvenue.jpg"/>" alt="" class="img-responsive center-block" />
    </header>

    <section class="col-xs-12 col-sm-8 col-sm-offset-2" style="margin-top: 25px">

      <c:if test="${!empty requestScope.erreur}">
        <div class="alert alert-danger">
          <c:out value="${requestScope.erreur}" />
        </div>
      </c:if>
      <c:if test="${!empty requestScope.message}">
        <div class="alert alert-success">
          <c:out value="${requestScope.message}" />
        </div>
      </c:if>

      <div class="panel panel-default">
        <div class="panel-body">
          <c:if test="${!empty applicationScope.mapUtilisateurs}">
          <div class="table-responsive">
            <table class="table table-bordered table-striped">
              <caption>Liste des utilisateurs connectés (<c:out value="${applicationScope.mapUtilisateurs.size() }" />)</caption>
              <thead class="bg-info">
                <tr>
                  <th class="text-center" style="width: 20%">Date</th>
                  <th class="text-center" style="width: 10%">Id</th>
                  <th class="text-center" style="width: 25%">Nom</th>
                  <th class="text-center" style="width: 25%">Pr&eacute;nom</th>
                  <th class="text-center" style="width: 20%">IP</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${applicationScope.mapUtilisateurs.values()}" var="utilisateur" varStatus="iter">
                <tr class="ellignetableau<c:out value="${iter.count%2+1}"/>">
                  <td class="ellibelletableau"><fmt:formatDate value="${utilisateur.date}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                  <td class="ellibelletableau">${utilisateur.id}</td>
                  <td class="ellibelletableau">${utilisateur.nom}</td>
                  <td class="ellibelletableau">${utilisateur.prenom}</td>
                  <td class="ellibelletableau">${utilisateur.ip}</td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
          </c:if>
          <c:if test="${empty applicationScope.mapUtilisateurs}">Pas d'utilisateur.</c:if>

          <div class="form-group">
            <div class="col-xs-2 col-xs-offset-4 col-sm-2 col-sm-offset-5">
              <a class="btn btn-primary" href="<c:url value="/ServletMenu"/>">Menu</a>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
  <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</body>
</html>