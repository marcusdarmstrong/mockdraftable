name := """mockdraftable"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

scalacOptions ++= Seq("-feature")

libraryDependencies ++= Seq(
  cache,
  ws,
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "com.typesafe.play" %% "play-slick" % "0.8.1"
)
