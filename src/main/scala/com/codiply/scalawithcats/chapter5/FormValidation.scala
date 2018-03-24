package com.codiply.scalawithcats.chapter5

import cats.data.Validated
import cats.instances.list._
import cats.syntax.apply._
import cats.syntax.either._

import scala.util.Try

case class User(name: String, age: Int)

object FormValidation {
  type Form = Map[String, String]
  type Errors = List[String]
  type FailFast[A] = Either[Errors, A]
  type FailSlow[A] = Validated[Errors, A]

  private def getValue(fieldName: String)(form: Form): FailFast[String] =
    form.get(fieldName).toRight(List(s"Field $fieldName is not specified."))

  private def parseInt(fieldName: String)(value: String): FailFast[Int] =
    Try(value.toInt).toEither.leftMap(_ => List(s"Field $fieldName is not a valid integer."))

  private def nonBlank(fieldName: String)(value: String): FailFast[String] =
    value.asRight[Errors].ensure(List(s"Field $fieldName is blank."))(_.nonEmpty)

  private def nonNegative(fieldName: String)(value: Int): FailFast[Int] =
    value.asRight[Errors].ensure(List(s"Field $fieldName is negative."))(_ >= 0)

  def readName(form: Form): FailFast[String] = {
    val fieldName = "name"
    getValue(fieldName)(form)
      .flatMap(nonBlank(fieldName))
  }

  def readAge(form: Form): FailFast[Int] = {
    val fieldName = "age"
    getValue(fieldName)(form)
      .flatMap(nonBlank(fieldName))
      .flatMap(parseInt(fieldName))
      .flatMap(nonNegative(fieldName))
  }

  def readUser(form: Form): FailSlow[User] =
    (
      readName(form).toValidated,
      readAge(form).toValidated
    ).mapN[User]()
}
