
name := "scala-jquery-dynamic"

organization := "only.hunam"

version := "0.1-SNAPSHOT"

libraryDependencies += {
  val liftVersion = "2.4"
  "net.liftweb" %% "lift-webkit"  % liftVersion
}

scalacOptions += "-Xexperimental"
