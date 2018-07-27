<!DOCTYPE html>
<html lang="fr">

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />

  <title>Banque - Login</title>
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
  <body class="container-fluid elbody">
    <span id="gatling_login" style="display: none;"></span>
    <!-- jmeter:login -->
    <div class="row">
      <aside class="col-sm-2 hidden-xs">
        <img src="images/image-femme.jpg" alt="" class="img-responsive" />
      </aside>

      <div class="col-xs-10">
        <div class="row">
          <header class="col-sm-12 hidden-xs">
            <img src="images/titre.jpg" alt="" class="img-responsive" />
          </header>

          <section class="col-xs-12 col-sm-6" style="margin-top: 25px">
            <!-- Exemple de messages -->
            <div class="alert alert-danger"> <%= request.getAttribute("maClef") %>  </div>
            <div class="alert alert-success">Tout va bien</div>

            <div class="panel panel-default">
              <div class="panel-heading text-center">Veuillez entrer votre nom d'utilisateur et votre mot de passe</div>
              <div class="panel-body">
                <form id="frmLogin" name="frmLogin" action="ServletLogin" method="post" class="form-horizontal">
                  <div class="form-group">
                    <label class="control-label col-sm-4 hidden-xs" for="inLogin">Nom d'utilisateur</label>
                    <div class="col-sm-8 col-xs-12">
                      <input class="form-control" type="text" value="" placeholder="Login" name="inLogin" id="inLogin" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-4 hidden-xs" for="inPass">Mot de passe</label>
                    <div class="col-sm-8 col-xs-12">
                      <input class="form-control" type="password" value="" placeholder="Pwd" name="inPass" id="inPass" />
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-xs-2 col-sm-4 col-sm-offset-5">
                      <button class="btn btn-primary" type="submit">Valider</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </section>

        </div>
      </div>
    </div>

    <script src="./js/bootstrap.min.js"></script>
  </body>

</html>
