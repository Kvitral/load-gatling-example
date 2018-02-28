package ru.kvitral

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._


import scala.util.Random
import scala.concurrent.duration._

class SlowQuickSimulation extends Simulation {

  val httpConf = http.baseURL("http://localhost:8080/")

  def randomizeTicket = Random.nextInt()

  val exampleScn =
    scenario("slowQuickScenario")
      /*.exec(
        http("slow")
          .get("slow")
          .check(substring("slow"))
      )*/
      .exec(
        http("quick")
          .get("quick")
          .check(substring("quick"))
      )


  setUp(exampleScn.inject(rampUsers(100) over (30 seconds))).protocols(httpConf)

}
