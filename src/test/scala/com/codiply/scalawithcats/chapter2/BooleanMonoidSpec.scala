package com.codiply.scalawithcats.chapter2

import org.scalatest.PropSpec
import org.scalatest.prop.PropertyChecks

class AndMonoidSpec extends PropSpec with PropertyChecks {
  import BooleanMonoidInstances.andMonoid

  val monoid = MyMonoid[Boolean]

  property("associative law") {
    forAll { (x: Boolean, y: Boolean, z: Boolean) =>
      val expression1 = monoid.combine(x, monoid.combine(y, z))
      val expression2 = monoid.combine(monoid.combine(x, y), z)

      assert(expression1 == expression2)
    }
  }

  property("identity law") {
    forAll { (x: Boolean) =>
      val expression1 = monoid.combine(monoid.empty, x)
      val expression2 = monoid.combine(x, monoid.empty)

      assert(expression1 == x)
      assert(expression2 == x)
    }
  }
}

class OrMonoidSpec extends PropSpec with PropertyChecks {
  import BooleanMonoidInstances.orMonoid

  val monoid = MyMonoid[Boolean]

  property("associative law") {
    forAll { (x: Boolean, y: Boolean, z: Boolean) =>
      val expression1 = monoid.combine(x, monoid.combine(y, z))
      val expression2 = monoid.combine(monoid.combine(x, y), z)

      assert(expression1 == expression2)
    }
  }

  property("identity law") {
    forAll { (x: Boolean) =>
      val expression1 = monoid.combine(monoid.empty, x)
      val expression2 = monoid.combine(x, monoid.empty)

      assert(expression1 == x)
      assert(expression2 == x)
    }
  }
}

class XorMonoidSpec extends PropSpec with PropertyChecks {
  import BooleanMonoidInstances.xorMonoid

  val monoid = MyMonoid[Boolean]

  property("associative law") {
    forAll { (x: Boolean, y: Boolean, z: Boolean) =>
      val expression1 = monoid.combine(x, monoid.combine(y, z))
      val expression2 = monoid.combine(monoid.combine(x, y), z)

      assert(expression1 == expression2)
    }
  }

  property("identity law") {
    forAll { (x: Boolean) =>
      val expression1 = monoid.combine(monoid.empty, x)
      val expression2 = monoid.combine(x, monoid.empty)

      assert(expression1 == x)
      assert(expression2 == x)
    }
  }
}

class XnorMonoidSpec extends PropSpec with PropertyChecks {
  import BooleanMonoidInstances.xnorMonoid

  val monoid = MyMonoid[Boolean]

  property("associative law") {
    forAll { (x: Boolean, y: Boolean, z: Boolean) =>
      val expression1 = monoid.combine(x, monoid.combine(y, z))
      val expression2 = monoid.combine(monoid.combine(x, y), z)

      assert(expression1 == expression2)
    }
  }

  property("identity law") {
    forAll { (x: Boolean) =>
      val expression1 = monoid.combine(monoid.empty, x)
      val expression2 = monoid.combine(x, monoid.empty)

      assert(expression1 == x)
      assert(expression2 == x)
    }
  }
}
