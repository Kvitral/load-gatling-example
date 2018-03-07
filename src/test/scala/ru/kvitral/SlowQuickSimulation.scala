package ru.kvitral

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._


import scala.util.Random
import scala.concurrent.duration._

class SlowQuickSimulation extends Simulation {

  val httpConf = http.baseURL("http://localhost:8080/")
    .extraInfoExtractor(e =>
      List(e.response.body.toString, "wow"))

  def randomizeTicket = Random.nextInt()

  val feeder = Array(
    Map("hello" -> 1),
    Map("goodbye" -> 2)
  ).queue.circular

  val exampleScn =
    scenario("slowQuickScenario").feed(feeder)
      .exec(
        http("slow")
          .get("slow")
          .check(substring("slow"))
      )
      .exec(
        http("quick")
          .get("quick")
          .check(substring("quick123"))
      )

  setUp(exampleScn.inject(rampUsers(100) over (30 seconds))).protocols(httpConf)

}
