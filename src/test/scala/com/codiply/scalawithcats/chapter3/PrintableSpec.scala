package com.codiply.scalawithcats.chapter3

import org.scalatest.{FlatSpec, Matchers}

class PrintableSpec extends FlatSpec with Matchers {
  behavior of "Printable"

  import com.codiply.scalawithcats.chapter3.PrintableInstances._

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
