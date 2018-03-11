package com.codiply.scalawithcats.chapter4

import org.scalatest.{FlatSpec, Matchers}
import cats.syntax.functor._
import cats.syntax.flatMap._
import com.codiply.scalawithcats.chapter3.Tree._
import com.codiply.scalawithcats.chapter4.TreeMonad._

class TreeMonadSpec extends FlatSpec with Matchers {
  behavior of "TreeMonad"

  it should "do the right thing when flatMap-ped (1)" in {
    val inputTree = branch(leaf(100), leaf(200))

    val actual = inputTree.flatMap { x => branch(leaf(x - 1), leaf(x + 1)) }

    val expected = branch(branch(leaf(99), leaf(101)), branch(leaf(199), leaf(201)))

    actual shouldBe expected
  }

  it should "do the right thing when flatMap-ped (2)" in {
    val actual = for {
      a <- branch(leaf(100), leaf(200))
      b <- branch(leaf(a - 10), leaf(a + 10))
      c <- branch(leaf(b - 1), leaf(b + 1))
    } yield c

    val expected = branch(
      branch(
        branch(leaf(89), leaf(91)),
        branch(leaf(109), leaf(111))),
      branch(
        branch(leaf(189), leaf(191)),
        branch(leaf(209), leaf(211))))

    actual shouldBe expected
  }
}
