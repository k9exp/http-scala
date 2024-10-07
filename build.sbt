val scala3Version = "3.5.1"
val pekkoVersion = "1.1.0"
val pekkoHttpVersion = "1.0.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "http-scala",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
        "org.scalameta" %% "munit" % "1.0.0" % Test,
        "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
        "org.apache.pekko" %% "pekko-stream" % pekkoVersion,
        "org.apache.pekko" %% "pekko-http" % pekkoHttpVersion
    )
  )
