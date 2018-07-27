<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
  buffer="128kb"%>
<%-- On declare le fait que l'on va utiliser la taglib JSTL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="fr">

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />

  <title>Historique de vos operations.</title>
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
    $("#inDateDebut").datepicker({
      showOn: "button",
      buttonImage: "<c:url value="/images/date_icon.gif"/>",
      buttonImageOnly: true,
      buttonText: "Selectionner une date",
      dateFormat: "yy/mm/dd",
      regional: "fr",
      showWeek: true,
      firstDay: 1
    });
    $("#inDateFin").datepicker({
      showOn: "button",
      buttonImage: "<c:url value="/images/date_icon.gif"/>",
      buttonImageOnly: true,
      buttonText: "Selectionner une date",
      dateFormat: "yy/mm/dd",
      regional: "fr",
      showWeek: true,
      firstDay: 1
    });
  });
  </script>

</head>

<body class="container-fluid elbody">
  <span id="gatling_historique" style="display: none;"></span>
  <!-- jmeter:historique -->
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
        <div class="panel-heading text-center">Indiquez vos critères de recherche</div>
        <div class="panel-body">
          <form id="frmListeOperations" name="frmListeOperations" action="<c:url value="/ServletHistorique"/>" method="post">

            <input type="hidden" name="inNumeroCompte" value="<c:out value="${requestScope.hbean.cptId}"/>" />

            <table class="table table-bordered jaune">
              <thead class="bg-info">
                <tr>
                  <th class="text-center" style="width: 60%">Date</th>
                  <th class="text-center" style="width: 40%">Type</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="text-center">
                    <label for="inDateDebut">Du</label> <input type="text" id="inDateDebut" name="inDateDebut" maxlength="10" size="10" value="<c:out value="${requestScope.hbean.dateDebut}"/>" style="margin-right: 5px" />
                    <label for="inDateFin" style="margin-left: 20px">Au</label> <input type="text" name="inDateFin" id="inDateFin" maxlength="10" size="10" value="${requestScope.hbean.dateFin}" style="margin-right: 5px"/>
                  </td>
                  <td class="text-center">
                    <label for="inCredit">Crédit</label>
                    <input type="checkbox" name="inCredit" id="inCredit" <c:if test="${!empty requestScope.hbean && requestScope.hbean.credit}">checked</c:if> value="true"/>
                    <br/>
                    <label for="inDebit">Débit</label>
                    <input type="checkbox" name="inDebit" id="inDebit" <c:if test="${!empty requestScope.hbean && requestScope.hbean.debit}">checked</c:if> value="true"/>
                  </td>
                </tr>
              </tbody>
            </table>
            <div class="form-group">
              <div class="col-xs-2 col-xs-offset-3 col-sm-offset-5">
                <button class="btn btn-primary" type="submit">Rechercher</button>
              </div>
            </div>
          </form>

        </div>
      </div>
    </section>

    <section class="col-xs-12 col-sm-8 col-sm-offset-2">

      <div class="panel panel-default">
        <div class="panel-heading text-center">Historique de vos op&eacute;rations effectu&eacute;es sur le compte n&deg; <c:out value="${requestScope.hbean.cptId}" /></div>
        <div class="panel-body">
          <c:if test="${!empty listeOperations}">
            <div class="table-responsive">
              <table class="table table-bordered table-striped">
                <caption></caption>
                <thead class="bg-info">
                  <tr>
                    <th class="text-center" style="width: 25%">Date</th>
                    <th class="text-center" style="width: 60%">Libell&eacute;</th>
                    <th class="text-center" style="width: 15%">Montant</th>
                  </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
                <c:forEach items="${listeOperations}" var="operation" varStatus="iter">
                  <tr class="ellignetableau<c:out value="${iter.count%2+1}"/>">
                    <td class="ellibelletableau text-center"><fmt:formatDate value="${operation.date}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
                    <td class="ellibelletableau">${operation.libelle}</td>
                    <td class="ellibelletableau text-center">${operation.montant} €</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </c:if>
          <c:if test="${empty listeOperations}"> Pas d'opération pour ce compte. </c:if>

          <div class="form-group"  style="margin-top: 15px">
            <div class="col-xs-2 col-xs-offset-1 col-sm-offset-3">
              <a class="btn btn-primary" href="<c:url value="/ServletMenu"/>">Menu</a>
            </div>
            <div class="col-xs-2 col-xs-offset-3">
              <a class="btn btn-primary" href="<c:url value="/ServletCompte"/>">Liste</a>
            </div>
          </div>
        </div>
      </div>
    </section>

  </div>

  <script src="<c:url value="/js/bootstrap.min.js"/>"></script>

</body>

</html>
