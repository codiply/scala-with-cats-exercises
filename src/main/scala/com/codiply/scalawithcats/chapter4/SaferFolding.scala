package com.codiply.scalawithcats.chapter4

import cats.Eval

object SaferFolding {
  def foldRight[A, B](as: List[A], acc: B)(fn: (A, B) => B): B = {
    def loop(as: List[A], acc: Eval[B]): Eval[B] =
      as match {
        case head :: tail =>
          Eval.defer { loop(tail, acc).map { fn(head, _) } }
        case Nil => acc
      }
    loop(as, Eval.now(acc)).value
  }
}
