package com.codiply.scalawithcats.chapter2

import cats.Monoid
import cats.instances.int._
import cats.syntax.semigroup._

object SuperAdderV1 {
  def add(items: List[Int]): Int = items.foldLeft(Monoid[Int].empty)(_ |+| _)
}

