package ru.kvitral

import io.gatling.core.Predef.scenario
import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class LeakSimulation extends Simulation {

  val httpConf = http.baseURL("http://localhost:8080/")

  val exampleScn =
    scenario("leakScenario")
      .exec(
        http("leak")
          .get("leak")
      )

  setUp(exampleScn.inject(rampUsers(150) over (30 seconds))).protocols(httpConf)

}
