package com.codiply.scalawithcats.chapter5

import org.scalatest.{FlatSpec, Matchers}

class FormValidationSpec extends FlatSpec with Matchers {
  "readName()" should "return Left for missing name field" in {
    val form = Map("full-name" -> "John Smith", "age" -> "25")

    FormValidation.readName(form) shouldBe Left(List("Field name is not specified."))
  }

  it should "return Left for blank name field" in {
    val form = Map("name" -> "", "age" -> "25")

    FormValidation.readName(form) shouldBe Left(List("Field name is blank."))
  }

  it should "return Right for a non-empty field" in {
    val form = Map("name" -> "John", "age" -> "25")

    FormValidation.readName(form) shouldBe Right("John")
  }

  "readAge()" should "return Left for missing age field" in {
    val form = Map("name" -> "John", "years-old" -> "25")

    FormValidation.readAge(form) shouldBe Left(List("Field age is not specified."))
  }

  it should "return Left for blank age field" in {
    val form = Map("name" -> "John", "age" -> "")

    FormValidation.readAge(form) shouldBe Left(List("Field age is blank."))
  }

  it should "return Left for an age value that cannot be parsed to integer" in {
    val form = Map("name" -> "John", "age" -> "25 years old")

    FormValidation.readAge(form) shouldBe Left(List("Field age is not a valid integer."))
  }

  it should "return Left for an age value that is negative" in {
    val form = Map("name" -> "John", "age" -> "-1")

    FormValidation.readAge(form) shouldBe Left(List("Field age is negative."))
  }

  it should "return Right for a valid age" in {
    val form = Map("name" -> "John", "age" -> "25")

    FormValidation.readAge(form) shouldBe Right(25)
  }
}
