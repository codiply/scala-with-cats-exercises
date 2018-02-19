package com.codiply.scalawithcats.chapter3

import cats.syntax.functor._
import org.scalatest.{FlatSpec, Matchers}

class TreeFunctorSpec extends FlatSpec with Matchers {
  import TreeFunctorInstances._

  behavior of "Tree functor"

  it should "map a leaf correctly" in {
    val leaf = Tree.leaf(5)

    val actual = leaf.map(_.toString)
    val expected = Tree.leaf("5")

    actual should be (expected)
  }

  it should "map a branch correctly" in {
    val tree = Tree.branch(
      Tree.branch(Tree.leaf(2), Tree.leaf(3)), Tree.leaf(4))

    val actual = tree.map(_ * 10)
    val expected = Tree.branch(
      Tree.branch(Tree.leaf(20), Tree.leaf(30)), Tree.leaf(40))

    actual should be (expected)

  }
}
