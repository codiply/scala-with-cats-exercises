package com.codiply.scalawithcats.chapter3

import org.scalatest.{FlatSpec, Matchers}

class PrintableSpec extends FlatSpec with Matchers {
  behavior of "Printable"

  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if(value) "yes" else "no"
    }

  implicit val stringPrintable: Printable[String] =
    new Printable[String] {
      def format(value: String): String =
        "\"" + value + "\""
    }

  implicit def boxPrintble[A](implicit printable: Printable[A]): Printable[Box[A]] =
    printable.contramap(_.value)

  final case class Box[A](value: A)

  it should "format a Box of a string" in {
    val box = Box("some string")
    val actual = Printable.format(box)
    val expected = "\"some string\""

    actual should be (expected)
  }

  it should "format a Box of a boolean" in {
    val box = Box(true)
    val actual = Printable.format(box)
    val expected = "yes"

    actual should be (expected)
  }
}
