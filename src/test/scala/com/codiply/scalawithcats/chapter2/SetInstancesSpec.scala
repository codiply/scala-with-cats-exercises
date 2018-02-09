package com.codiply.scalawithcats.chapter2

import org.scalatest.PropSpec
import org.scalatest.prop.PropertyChecks

class UnionMonoidSpec extends PropSpec with PropertyChecks {
  import SetInstances.unionMonoid
  val monoid = MyMonoid[Set[Int]]

  property("associative law") {
    forAll { (x: Set[Int], y: Set[Int], z: Set[Int]) =>
      val expression1 = monoid.combine(x, monoid.combine(y, z))
      val expression2 = monoid.combine(monoid.combine(x, y), z)

      assert(expression1 == expression2)
    }
  }

  property("identity law") {
    forAll { (x: Set[Int]) =>
      val expression1 = monoid.combine(monoid.empty, x)
      val expression2 = monoid.combine(x, monoid.empty)

      assert(expression1 == x)
      assert(expression2 == x)
    }
  }
}

class IntersectionSemigroupSpec extends PropSpec with PropertyChecks {
  import SetInstances.intersectionSemigroup

  val monoid = MySemigroup[Set[Int]]

  property("associative law") {
    forAll { (x: Set[Int], y: Set[Int], z: Set[Int]) =>
      val expression1 = monoid.combine(x, monoid.combine(y, z))
      val expression2 = monoid.combine(monoid.combine(x, y), z)

      assert(expression1 == expression2)
    }
  }
}
