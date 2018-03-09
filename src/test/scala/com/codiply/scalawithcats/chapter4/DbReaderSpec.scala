package com.codiply.scalawithcats.chapter4

import org.scalatest.{FlatSpec, Matchers}

class DbReaderSpec extends FlatSpec with Matchers {
  behavior of "DbReader"

  import DbReaderExercise._

  val users = Map(
    1 -> "dade",
    2 -> "kate",
    3 -> "margo"
  )
  val passwords = Map(
    "dade"  -> "zerocool",
    "kate"  -> "acidburn",
    "margo" -> "secret")

  val db = Db(users, passwords)

  it should "return true for checkLogin with correct password" in {
    checkLogin(1, "zerocool").run(db) shouldBe true
  }
  it should "return false for checkLogin with incorrect password" in {
    checkLogin(1, "secret").run(db) shouldBe false
  }
  it should "return false for checkLogin with unknown user id" in {
    checkLogin(4, "secret").run(db) shouldBe false
  }
}
