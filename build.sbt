name := "Scala with Cats Exercises"
organization := "com.codiply"
version := "0.0.1"

scalaVersion := "2.12.3"

libraryDependencies ++= {
  Seq(
    "org.typelevel" %% "cats" % "0.9.0",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
}
