<!DOCTYPE html>
<html lang="fr">

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />

  <title>Banque - Menu</title>
  <link href="./css/bootstrap.min.css"  rel="stylesheet" />
  <link href="./css/banque.css"         rel="stylesheet" />
  <link href="./css/jquery-ui.min.css"  rel="stylesheet" />


  <!--[if lt IE 9]>
      <script src="./js/html5shiv.3.7.3.min.js"></script>
      <script src="./js/respond.1.4.2.min.js"></script>
  <![endif]-->

  <script src="./js/jquery.1.12.4.min.js"></script>
  <script src="./js/jquery-ui.min.js"></script>
  <script src="./js/i18n/datepicker-fr.js"></script>
</head>

<body class="container-fluid elbody" data-gatling="menu">
  <span id="gatling_menu" style="display: none;"></span>
  <!-- jmeter:menu -->
  <div class="row">
    <header class="col-sm-12 hidden-xs">
      <img src="images/image-bienvenue.jpg" alt="" class="img-responsive center-block" />
    </header>

    <section class="col-xs-12 col-sm-6 col-sm-offset-3" style="margin-top: 25px">

      <div class="panel-body">
        <div class="alert alert-info">Bonjour Fargis Denis</div>
        <div class="list-group">
          <a class="list-group-item" href="./comptes/liste.html"><img src="./images/puce.gif" width="13" height="18" alt="" /> Liste de vos comptes</a>
          <a class="list-group-item" href="./comptes/virement.html"><img src="./images/puce.gif" width="13" height="18" alt="" /> Virement</a>
          <!--
          <a class="list-group-item" href="#"><img src="./images/puce.gif" width="13" height="18" alt="" /> Utilisateurs connectés </a>
          <a class="list-group-item" href="#"><img src="./images/puce.gif" width="13" height="18" alt="" /> Se Deconnecter </a>
           -->
        </div>
      </div>
    </section>
  </div>

  <script src="./js/bootstrap.min.js"></script>
</body>

</html>
