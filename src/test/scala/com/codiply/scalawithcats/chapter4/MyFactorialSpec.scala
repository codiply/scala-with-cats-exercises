package com.codiply.scalawithcats.chapter4

import org.scalatest.{FlatSpec, Matchers}

class MyFactorialSpec extends FlatSpec with Matchers {
  behavior of "MyFactorial"

  it should "log intermediate calculations" in {
    val n = 3

    val (actualLog, actualAns) = MyFactorial.factorial(3).run
    val expectedLog = Vector("fact 0 1", "fact 1 1", "fact 2 2", "fact 3 6")
    val expectedAns = 6

    actualLog should be (expectedLog)
    actualAns should be (expectedAns)
  }
}
