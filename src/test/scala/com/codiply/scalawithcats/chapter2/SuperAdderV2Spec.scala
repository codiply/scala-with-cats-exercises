package com.codiply.scalawithcats.chapter2

import org.scalatest.PropSpec
import org.scalatest.prop.PropertyChecks

class SuperAdderV2Spec extends PropSpec with PropertyChecks {
  import cats.instances.all._
  import com.codiply.scalawithcats.chapter2.OrderMonoidInstances._

  property("add1 can add options") {
    forAll { (items: List[Option[Int]]) =>
      val actual = SuperAdderV2.add1(items)
      val expected = items.flatten match {
        case Nil => None
        case lst => Some(lst.sum)
      }

      assert(actual === expected)
    }
  }

  property("add2 can add options") {
    forAll { (items: List[Option[Int]]) =>
      val actual = SuperAdderV2.add2(items)
      val expected = items.flatten match {
        case Nil => None
        case lst => Some(lst.sum)
      }

      assert(actual === expected)
    }
  }

  property("add1 can add orders") {
    forAll { (totalCostsWithQuantities: List[(Double, Double)]) =>
      val orders = totalCostsWithQuantities.map { case (c, q) => Order(c, q) }

      val actual = SuperAdderV2.add1(orders)
      val expected = Order(totalCostsWithQuantities.map(_._1).sum, totalCostsWithQuantities.map(_._2).sum)

      assert(actual === expected)
    }
  }

  property("add2 can add orders") {
    forAll { (totalCostsWithQuantities: List[(Double, Double)]) =>
      val orders = totalCostsWithQuantities.map { case (c, q) => Order(c, q) }

      val actual = SuperAdderV2.add2(orders)
      val expected = Order(totalCostsWithQuantities.map(_._1).sum, totalCostsWithQuantities.map(_._2).sum)

      assert(actual === expected)
    }
  }

  property("add1 can add options of orders") {
    forAll { (totalCostsWithQuantities: List[Option[(Double, Double)]]) =>
      val orders = for {
        option <- totalCostsWithQuantities
        (c, q) <- option
      } yield Order(c, q)

      val actual = SuperAdderV2.add1(orders)

      val expected = Order(
          totalCostsWithQuantities.flatten.map(_._1).sum,
          totalCostsWithQuantities.flatten.map(_._2).sum)

      assert(actual === expected)
    }
  }

  property("add2 can add options of orders") {
    forAll { (totalCostsWithQuantities: List[Option[(Double, Double)]]) =>
      val orders = for {
        option <- totalCostsWithQuantities
        (c, q) <- option
      } yield Order(c, q)

      val actual = SuperAdderV2.add2(orders)

      val expected = Order(
        totalCostsWithQuantities.flatten.map(_._1).sum,
        totalCostsWithQuantities.flatten.map(_._2).sum)

      assert(actual === expected)
    }
  }
}
