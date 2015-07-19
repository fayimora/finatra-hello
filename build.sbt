name := "finatra-hello"

organization := "com.example"

version := "0.0.1"

scalaVersion := "2.11.7"

herokuAppName in Compile := "finatra-hello"

herokuProcessTypes in Compile := Map(
  "web" -> "target/universal/stage/bin/finatra-hello -http.port=:$PORT"
)

libraryDependencies ++= Seq(
  "com.twitter.finatra" %% "finatra-http" % "2.0.0.M2",
  "com.twitter.finatra" %% "finatra-logback" % "2.0.0.M2",
  "ch.qos.logback" % "logback-classic" % "1.1.3"
)

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  "Twitter Maven" at "https://maven.twttr.com/",
  DefaultMavenRepository
)

enablePlugins(JavaAppPackaging)
