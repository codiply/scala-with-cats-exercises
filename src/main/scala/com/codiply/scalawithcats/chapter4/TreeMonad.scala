package com.codiply.scalawithcats.chapter4

import cats.Monad
import com.codiply.scalawithcats.chapter3.{Branch, Leaf, Tree}

object TreeMonad {
  implicit val treeMonad = new Monad[Tree] {
    def flatMap[A, B](tree: Tree[A])(fn: A => Tree[B]): Tree[B] =
      tree match {
        case Leaf(a) => fn(a)
        case Branch(a, b) => Branch(flatMap(a)(fn), flatMap(b)(fn))
      }

    def pure[A](a: A): Tree[A] = Leaf(a)

    def tailRecM[A, B](a: A)(fn: A => Tree[Either[A, B]]): Tree[B] =
      fn(a) match {
        case Leaf(Left(x)) => tailRecM(x)(fn)
        case Leaf(Right(x)) => Leaf(x)
        case Branch(a, b) =>
          Branch(flatMap(a) {
            case Left(x) => tailRecM(x)(fn)
            case Right(x) => Leaf(x)
          }, flatMap(b) {
            case Left(x) => tailRecM(x)(fn)
            case Right(x) => Leaf(x)
          })
      }
  }
}
