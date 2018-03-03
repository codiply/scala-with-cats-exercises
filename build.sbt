name := "Scala with Cats Exercises"
organization := "com.codiply"
version := "0.0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= {
  Seq(
    "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "org.typelevel" %% "cats-core" % "1.0.1"
  )
}
