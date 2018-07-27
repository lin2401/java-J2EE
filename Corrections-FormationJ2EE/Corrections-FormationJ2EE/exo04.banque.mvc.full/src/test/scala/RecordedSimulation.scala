
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://localhost:8080")
    .inferHtmlResources(BlackList(""".*\.js.*""", """.*\.css.*""", """.*\.gif.*""", """.*\.jpeg.*""", """.*\.jpg.*""", """.*\.ico""", """.*\.woff.*""", """.*\.(t|o)tf""", """.*\.png.*"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0")

  val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

  // Ajout d'une variable qui represent le context root de l'application J2EE
  val contextroot = "/exo04.banque.mvc.full"
  // Ajout d'une variable qui represent le temps de pause en seconde entre chaque appel
  val pauseTime = 1;
  val uri1 = "http://localhost:8080" + contextroot

  // Gestion du CSV :
  val feeder = csv("info_gatling.csv").random

  val scn = scenario("RecordedSimulation")
    .exec(http("P_Login")
      .get(contextroot + "/login.jsp")
      .check(status.is(200), css("span", "id").is("gatling_login"))
      .headers(headers_0))
    .pause(pauseTime)
    .feed(feeder)
    .exec(http("C_Login")
      .post(contextroot + "/ServletLogin")
      .check(status.is(200), css("span", "id").is("gatling_menu"))
      .headers(headers_0)
      .formParam("inLogin", "${login}")
      .formParam("inPass", "${password}"))
    .pause(pauseTime)
    .exec(http("C_Compte")
      .get(contextroot + "/ServletCompte")
      .check(status.is(200), css("span", "id").is("gatling_liste"))
      .headers(headers_0))
    .pause(pauseTime)
    .exec(http("C_Hist_Voir")
      .get(contextroot + "/ServletHistorique?inNumeroCompte=${idcpt1}")
      .check(status.is(200), css("span", "id").is("gatling_historique"))
      .headers(headers_0))
    .pause(pauseTime)
    .exec(http("C_Menu")
      .get(contextroot + "/ServletMenu")
      .check(status.is(200), css("span", "id").is("gatling_menu"))
      .headers(headers_0))
    .pause(pauseTime)
    .exec(http("C_Vir_Voir")
      .get(contextroot + "/ServletVirement")
      .check(status.is(200), css("span", "id").is("gatling_virement"))
      .headers(headers_0))
    .pause(pauseTime)
    .exec(http("C_Vir_Faire")
      .post(contextroot + "/ServletVirement")
      .headers(headers_0)
      .check(status.is(200), css("span", "id").is("gatling_menu"))
      .formParam("inCmptEme", "${idcpt1}")
      .formParam("inCmptDes", "${idcpt2}")
      .formParam("inMontant", "5"))
    .pause(pauseTime)
    .exec(http("C_Logout")
      .get(contextroot + "/ServletLogout")
      .check(status.is(200), css("span", "id").is("gatling_login"))
      .headers(headers_0))

  setUp(scn.inject(rampUsers(10) over (10 seconds)))
    .assertions(
      global.responseTime.mean.lt(100),
      global.successfulRequests.percent.gt(95))
    .protocols(httpProtocol)
}