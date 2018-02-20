package com.codiply.scalawithcats.chapter4

import org.scalatest.{ FlatSpec, Matchers }

class MyOptionMonadSpec extends FlatSpec with Matchers {
  behavior of "MyOptionMonad"

  it should "map correctly" in {
    val option = MyOptionMonad.pure(1)

    val actual = MyOptionMonad.map(option)(_.toString)
    val expected = MyOptionMonad.pure("1")

    actual should be (expected)
  }
}
