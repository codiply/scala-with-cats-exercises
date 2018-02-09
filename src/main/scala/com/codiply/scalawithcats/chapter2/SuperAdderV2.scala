package com.codiply.scalawithcats.chapter2

import cats.Monoid
import cats.syntax.semigroup._

object SuperAdderV2 {
  def add1[A](items: List[A])(implicit monoid: Monoid[A]): A = items.foldLeft(monoid.empty)(_ |+| _)

  def add2[A: Monoid](items: List[A]): A = items.foldLeft(Monoid[A].empty)(_ |+| _)
}
