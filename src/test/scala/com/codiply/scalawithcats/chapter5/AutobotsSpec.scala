package com.codiply.scalawithcats.chapter5

import org.scalatest.{AsyncFlatSpec, Matchers}

class AutobotsSpec extends AsyncFlatSpec with Matchers {
  behavior of "Autobots"

  val powerLevels = Map(
    "Jazz" -> 6,
    "Bumblebee" -> 8,
    "Hot Rod" -> 10)

  val autobots = new Autobots(powerLevels)

  it should "return the power levels of an existing autobot as right" in {
    autobots.getPowerLevel("Bumblebee").value.map { either =>
      either shouldBe Right(8)
    }
  }
  it should "return the power levels of an non-existing autobot as left" in {
    autobots.getPowerLevel("some-robot-that-does-not-exist").value.map { either =>
      either.isLeft shouldBe true
    }
  }

  it should "return false for canSpecialMove for total power of 14" in {
    autobots.canSpecialMove("Jazz", "Bumblebee").value.map { either =>
      either shouldBe Right(false)
    }
  }
  it should "return true for canSpecialMove for total power of 16" in {
    autobots.canSpecialMove("Jazz", "Hot Rod").value.map { either =>
      either shouldBe Right(true)
    }
  }
  it should "return Left for canSpecialMove for total power of 16" in {
    autobots.canSpecialMove("Jazz", "some-robot-that-does-not-exist").value.map { either =>
      either.isLeft shouldBe true
    }
  }

  it should "return the right tacticalReport for allies that cannot do special move" in {
    val expected = "Jazz and Bumblebee need a recharge."
    val actual = autobots.tacticalReport("Jazz", "Bumblebee")
    actual shouldBe expected
  }
  it should "return the right tacticalReport for allies that can do special move" in {
    val expected = "Bumblebee and Hot Rod are ready to roll out!"
    val actual = autobots.tacticalReport("Bumblebee", "Hot Rod")
    actual shouldBe expected
  }
  it should "return the right tacticalReport for non-existing ally" in {
    val expected = "Comms error: I-do-not-exist unreachable"
    val actual = autobots.tacticalReport("Bumblebee", "I-do-not-exist")
    actual shouldBe expected
  }
}
