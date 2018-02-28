package ru.kvitral

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.util.Random

class ExampleSimulation extends Simulation {

  val httpConf = http.baseURL("http://localhost:8080/")

  def randomizeTicket = Random.nextInt()

  val exampleScn =
    scenario("exampleScenario")
      .exec(session => session.set("rndTicket", randomizeTicket))
      .exec(
        http("ping")
          .get("ping")
          .queryParam("ticket", "${rndTicket}")
          .check(jsonPath("$.ticket") is "${rndTicket}")
      )
      .exec(
        http("ping_corrupted")
          .get("ping/corrupted")
          .queryParam("ticket_cor", "${rndTicket}")
          .check(jsonPath("$.ticket") is "${rndTicket}")
      )


  setUp(exampleScn.inject(rampUsers(100) over (30 seconds))).protocols(httpConf)

}
