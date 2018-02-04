package com.codiply.scalawithcats.chapter1

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

object ShowInstances {

  implicit val catShow = Show.show { cat: Cat =>
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show

    s"$name is a $age year-old $color cat."
  }
}

object ShowMain {
  def main(args: Array[String]): Unit = {
    import ShowInstances._

    val cat = Cat("Tiger", 3, "brown")

    println(cat.show)
  }
}
