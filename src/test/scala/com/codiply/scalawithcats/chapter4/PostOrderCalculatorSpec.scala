package com.codiply.scalawithcats.chapter4

import org.scalatest.{FlatSpec, Matchers}

class PostOrderCalculatorSpec extends FlatSpec with Matchers {
  behavior of "PostOrderCalculator"

  import PostOrderCalculator._

  it should "perform addition with evalOne" in {
    val program = for {
      _ <- evalOne("1")
      _ <- evalOne("2")
      ans <- evalOne("+")
    } yield ans

    program.runA(Nil).value shouldBe 3
  }

  it should "perform subtractions with evalOne" in {
    val program = for {
      _ <- evalOne("1")
      _ <- evalOne("2")
      ans <- evalOne("-")
    } yield ans

    program.runA(Nil).value shouldBe -1
  }

  it should "perform multiplication with evalOne" in {
    val program = for {
      _ <- evalOne("2")
      _ <- evalOne("3")
      ans <- evalOne("*")
    } yield ans

    program.runA(Nil).value shouldBe 6
  }

  it should "perform division with evalOne" in {
    val program = for {
      _ <- evalOne("6")
      _ <- evalOne("2")
      ans <- evalOne("/")
    } yield ans

    program.runA(Nil).value shouldBe 3
  }

  it should "perform a list of operations with evalAll" in {
    val program = for {
      _ <- evalAll(List("4", "2", "-"))
      _ <- evalAll(List("3", "4", "+"))
      ans <- evalOne("*")
    } yield ans

    program.runA(Nil).value shouldBe 14
  }

  it should "perform a list of operations from a string" in {
    evalInput("1 2 + 3 4 + *") shouldBe 21
  }
}
