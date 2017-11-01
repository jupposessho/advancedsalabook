name         := "advancedscalabook"
organization := "waff"
version      := "1.0"
scalaVersion := "2.12.4"

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
  val scalaTestV  = "3.0.1"
  Seq(
    "org.typelevel" %% "cats"      % "0.9.0",
    "com.chuusai"   %% "shapeless" % "2.3.2",
    "org.scalatest" %% "scalatest" % scalaTestV % "test"
    )
}

// Improved incremental compilation
incOptions := incOptions.value.withNameHashing(true)

// Improved dependency management
updateOptions := updateOptions.value.withCachedResolution(true)

// Download and create Eclipse source attachments for library dependencies
// EclipseKeys.withSource := true

// Uncomment to enable offline mode
// offline := true

showSuccess := true

showTiming := true

shellPrompt := { state =>
  import scala.Console.{CYAN,RESET}
  val p = Project.extract(state)
  val name = p.getOpt(sbt.Keys.name) getOrElse p.currentProject.id
  s"[$CYAN$name$RESET] $$ "
}
