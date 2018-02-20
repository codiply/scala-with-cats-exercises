package com.codiply.scalawithcats.chapter4

import scala.language.higherKinds

object MyOptionMonad extends MyMonad[Option] {
  def pure[A](a: A): Option[A] = Option(a)

  def flatMap[A, B](value: Option[A])(func: A => Option[B]): Option[B] = value.flatMap(func)
}
