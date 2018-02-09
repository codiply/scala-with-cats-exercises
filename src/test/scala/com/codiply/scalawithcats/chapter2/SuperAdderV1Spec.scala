package com.codiply.scalawithcats.chapter2

import org.scalatest.PropSpec
import org.scalatest.prop.PropertyChecks

class SuperAdderV1Spec extends PropSpec with PropertyChecks {
  property("add does the right thing") {
    forAll { (items: List[Int]) =>
      val actual = SuperAdderV1.add(items)
      val expected = items.sum

      assert(actual == expected)
    }
  }
}
