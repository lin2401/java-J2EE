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

  <title>Banque - Virement</title>
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
  <span id="gatling_virement" style="display: none;"></span>
  <!-- jmeter:virement -->
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
        <div class="panel-heading text-center">Veuillez entrer les informations associées à votre virement</div>

        <div class="panel-body">
          <form id="frmVirements" name="frmVirements" action="<c:url value="/ServletVirement"/>" method="post" class="form-horizontal">
            <div class="form-group">
              <label class="control-label col-sm-3 hidden-xs" for="inCmptEme"> Compte &eacute;metteur</label>
              <div class="col-sm-7 col-xs-12">
                <select name="inCmptEme" class="form-control" id="inCmptEme">
                  <option value="">-- Choix --</option>
                  <c:forEach items="${listeCompte}" var="compte">
                    <c:if test="${!empty pCmptEme && pCmptEme.equals(compte.id)}">
                      <option value="${compte.id}" selected>${compte.libelle}</option>
                    </c:if>
                    <c:if test="${empty pCmptEme || !pCmptEme.equals(compte.id)}">
                      <option value="${compte.id}">${compte.libelle}</option>
                    </c:if>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-sm-3 hidden-xs" for="inCmptDes"> Compte destinataire</label>
              <div class="col-sm-7 col-xs-12">
                <select name="inCmptDes" class="form-control" id="inCmptDes">
                  <option value="">-- Choix --</option>
                  <c:forEach items="${listeCompte}" var="compte">
                    <c:if test="${!empty pCmptDes && pCmptDes.equals(compte.id)}">
                      <option value="${compte.id}" selected>${compte.libelle}</option>
                    </c:if>
                    <c:if test="${empty pCmptDes || !pCmptDes.equals(compte.id)}">
                      <option value="${compte.id}">${compte.libelle}</option>
                    </c:if>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-sm-3 hidden-xs" for="inMontant"> Montant du virement</label>
              <div class="col-sm-7 col-xs-12">
                <input class="form-control" type="number" step="any" name="inMontant" id="inMontant" value="<c:out value="${pMontant}"/>"/>
              </div>
            </div>
            <div class="form-group">
              <div class="col-xs-2 col-xs-offset-1 col-sm-offset-3">
                <button class="btn btn-primary" type="submit">Valider</button>
              </div>
              <div class="col-xs-2 col-xs-offset-3">
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
