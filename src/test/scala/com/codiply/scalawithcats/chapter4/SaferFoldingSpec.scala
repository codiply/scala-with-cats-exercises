package com.codiply.scalawithcats.chapter4

import org.scalatest.{ FlatSpec, Matchers }

class SaferFoldingSpec extends FlatSpec with Matchers {
  behavior of "SaferFolding"

  it should "fold without stack overflow" in {
    val n = 10000
    val list = (1 to n).map(_ => 1).toList

    val actual = SaferFolding.foldRight(list, 0)((x, total) => total + x)
    val expected = n

    actual should be (expected)
  }

  it should "fold right" in {
    val list = List(1, 2, 3, 4)

    val actual = SaferFolding.foldRight(list, "")((x, acc) => if (acc.isEmpty) x.toString else s"($x + $acc)")
    val expected = "(1 + (2 + (3 + 4)))"

    actual should be (expected)
  }
}
