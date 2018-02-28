name := "load-gatling-example"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "com.typesafe" % "config" % "1.3.2"

val gatlingVersion = "2.3.0"

libraryDependencies ++= Seq(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion,
  "io.gatling" % "gatling-test-framework" % gatlingVersion
)

enablePlugins(GatlingPlugin)