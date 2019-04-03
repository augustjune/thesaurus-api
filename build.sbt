name := "thesaurus-api"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-actor" % "2.5.14",
	"com.typesafe.akka" %% "akka-testkit" % "2.5.14" % Test
)

libraryDependencies ++= Seq(
	"org.scalactic" %% "scalactic" % "3.0.5",
	"org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

libraryDependencies += "com.softwaremill.sttp" %% "core" % "1.5.11"

libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.3"

libraryDependencies += "com.github.pureconfig" %% "pureconfig" % "0.10.2" % Test

