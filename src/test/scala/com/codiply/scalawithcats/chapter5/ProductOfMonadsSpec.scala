package com.codiply.scalawithcats.chapter5

import org.scalatest.{FlatSpec, Matchers}
import cats.instances.option._

class ProductOfMonadsSpec extends FlatSpec with Matchers {
  "product()" should "return Some of of the two values for two Some's" in {
    val x: Option[Int] = Some(1)
    val y: Option[String] = Some("some-string")

    ProductOfMonads.product(x, y) shouldBe Some((10, "some-string"))
  }
}
