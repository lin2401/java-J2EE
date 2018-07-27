<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
  buffer="128kb"%>
<%-- On declare le fait que l'on va utiliser la taglib JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="fr">

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />

  <title>Liste de vos comptes.</title>
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
  <script>
  	$(function() {
  		// Gestion du click sur le table
  		$("#result tbody tr" ).on( "click", function() {
  		  go($(this).children("td").first().data("cpid"));
  		});
  	});
  	function go(idCpt) {
		document.getElementById("inNumeroCompte").value = idCpt;
		document.getElementById("frmListeCompte").submit();
  	}
  </script>
</head>

<body class="container-fluid elbody">
  <span id="gatling_liste" style="display: none;"></span>
  <!-- jmeter:liste -->
  <div class="row">
    <header class="col-sm-12 hidden-xs">
      <img src="<c:url value="/images/image-bienvenue.jpg"/>" alt="" class="img-responsive center-block" />
    </header>

    <section class="col-xs-12 col-sm-8 col-sm-offset-2" style="margin-top: 25px">

      <c:if test="${!empty requestScope.erreur}">
        <div class="alert alert-danger"><c:out value="${requestScope.erreur}" /></div>
      </c:if> <c:if test="${!empty requestScope.message}">
        <div class="alert alert-success"><c:out value="${requestScope.message}" /></div>
      </c:if>


      <div class="panel panel-default">
        <div class="panel-body">
          <form id="frmListeCompte" name="frmListeCompte" action="<c:url value="/ServletHistorique"/>" method="post">
            <input type="hidden" name="inNumeroCompte" id="inNumeroCompte" />
            <c:if test="${!empty listeCompte}">
              <div class="table-responsive">
                <table class="table table-bordered table-striped" id="result">
                  <caption>Liste de vos comptes sur Net Banque</caption>
                  <thead class="bg-info">
                    <tr>
                      <th class="text-center" style="width: 10%">Num&eacute;ro</th>
                      <th class="text-center" style="width: 50%">D&eacute;signation</th>
                      <th class="text-center" style="width: 10%">Taux</th>
                      <th class="text-center" style="width: 10%">D&eacute;couvert autoris&eacute;</th>
                      <th class="text-center" style="width: 20%">Solde</th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${listeCompte}" var="compte" varStatus="iter">
                    <tr class="ellignetableau<c:out value="${iter.count%2+1}"/>">
                      <%-- On fait passer l'id dans les data du td  --%>
                      <td class="ellibelletableau text-center" data-cpid="<c:out value="${compte.id}"/>">
                        <a href="javascript:go(<c:out value="${compte.id}"/>)"><c:out value="${compte.id}"/></a>
                      </td>
                      <td class="ellibelletableau">${compte.libelle}</td>
                      <c:if test="${!empty compte.taux}">
                      <td class="ellibelletableau text-center">${compte.taux} %</td>
                      </c:if>
                      <c:if test="${empty compte.taux}">
                      <td class="ellibelletableau text-center">--</td>
                      </c:if>
                      <c:if test="${!empty compte.decouvert}">
                      <td class="ellibelletableau text-center">${compte.decouvert} €</td>
                      </c:if>
                      <c:if test="${empty compte.decouvert}">
                      <td class="ellibelletableau text-center">--</td>
                      </c:if>
                      <td class="ellibelletableau text-center">${compte.solde} €</td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </c:if>
            <c:if test="${empty listeCompte}">Pas de compte pour cet utilisateur.</c:if>

            <div class="form-group">
              <div class="col-xs-2 col-xs-offset-4 col-sm-2 col-sm-offset-5">
                <a class="btn btn-primary" href="<c:url value="/ServletMenu"/>">Menu</a>
              </div>
            </div>
          </form>
        </div>
      </div>
    </section>

  </div>

  <script src="<c:url value="/js/bootstrap.min.js"/>"></script>

</body>

</html>
