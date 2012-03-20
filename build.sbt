
name := "scala-jquery-dynamic"

organization := "only.hunam"

version := "0.1-SNAPSHOT"

libraryDependencies += {
  val liftVersion = "2.4"
  "net.liftweb" %% "lift-webkit"  % liftVersion
}

libraryDependencies += {
  "org.specs2" %% "specs2" % "1.8.2" % "test"
}

scalacOptions += "-Xexperimental"
