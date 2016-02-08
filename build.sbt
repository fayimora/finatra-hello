name := "finatra-hello"

organization := "com.example"

version := "0.0.1"

scalaVersion := "2.11.7"

herokuAppName in Compile := "finatra-hello"

herokuProcessTypes in Compile := Map(
  "web" -> "target/universal/stage/bin/finatra-hello -http.port=:$PORT"
)

libraryDependencies ++= Seq(
  "com.twitter.finatra" %% "finatra-http" % "2.1.2",
  "com.twitter.finatra" %% "finatra-slf4j" % "2.1.2",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.github.finagle" %% "finagle-oauth2" % "0.1.5",

  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "org.scalatest" %% "scalatest" % "2.2.3" % "test",
  "org.specs2" %% "specs2" % "2.3.12" % "test"
)

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  "Twitter Maven" at "https://maven.twttr.com/",
  DefaultMavenRepository
)

javaOptions ++= Seq()

