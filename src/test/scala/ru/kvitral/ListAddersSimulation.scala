package ru.kvitral

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import scala.concurrent.duration._

import scala.util.Random

class ListAddersSimulation extends Simulation {

  val httpConf = http.baseURL("http://localhost:8080/")

  def randomInt = Random.nextInt(1000)

  val properScn =
    scenario("properAdderScenario")
      .exec(session => session.set("element", randomInt))
      .exec(
        http("proper")
          .get("adder/proper")
          .queryParam("element", "${element}")
      )

  val slowerScn =
    scenario("slowerAdderScenario")
      .exec(session => session.set("element", randomInt))
      .exec(
        http("slower")
          .get("adder/slower")
          .queryParam("element", "${element}")
      )

  setUp(
    properScn.inject(rampUsers(50) over (30 seconds)),
    slowerScn.inject(rampUsers(150) over (30 seconds))
  ).protocols(httpConf)
}
