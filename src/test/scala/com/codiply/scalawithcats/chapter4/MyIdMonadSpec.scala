package com.codiply.scalawithcats.chapter4

import org.scalatest.{FlatSpec, Matchers}

class MyIdMonadSpec extends FlatSpec with Matchers {
  behavior of "MyIdMonad"

  it should "map correctly" in {
    val x = MyIdMonad.pure(1)

    val actual = MyIdMonad.map(x)(_.toString)
    val expected = MyIdMonad.pure("1")

    actual should be (expected)
  }

  it should "flatMap correctly" in {
    val x = MyIdMonad.pure(1)

    val actual = MyIdMonad.flatMap(x)(_.toString)
    val expected = MyIdMonad.pure("1")

    actual should be (expected)
  }
}
