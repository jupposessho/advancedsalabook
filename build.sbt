name         := "advancedscalabook"
organization := "waff"
version      := "1.0"
scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-Ywarn-dead-code",
  "-Xlint",
  "-Xfatal-warnings"
)

libraryDependencies ++= {
  val scalaTestV  = "3.0.0-M15"
  Seq(
    "org.scalatest"         %% "scalatest"                         % scalaTestV % "test"
    )
}
