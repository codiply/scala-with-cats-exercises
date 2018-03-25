package com.codiply.scalawithcats.chapter5

import cats.Monad

object ProductOfMonads {
  def product[M[_]: Monad, A, B](x: M[A], y: M[B]): M[(A, B)] =
   for {
     a <- x
     b <- y
   } yield (a, b)
}
