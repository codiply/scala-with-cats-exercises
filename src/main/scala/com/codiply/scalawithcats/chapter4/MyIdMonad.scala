package com.codiply.scalawithcats.chapter4

import cats.Id

object MyIdMonad extends MyMonad[Id] {
  override def pure[A](a: A): Id[A] = a

  override def flatMap[A, B](value: Id[A])(func: A => Id[B]): Id[B] = func(value)

  override def map[A, B](value: Id[A])(func: A => B): Id[B] = func(value)
}
