package com.codiply.scalawithcats.chapter3

import cats.Functor

object TreeFunctorInstances {
  implicit val treeFunctor = new Functor[Tree] {
    def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = tree match {
      case Branch(t1, t2) => Branch(map(t1)(f), map(t2)(f))
      case Leaf(v) => Leaf(f(v))
    }
  }
}
