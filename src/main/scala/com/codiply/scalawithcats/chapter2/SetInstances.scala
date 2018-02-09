package com.codiply.scalawithcats.chapter2

object SetInstances {
  implicit def unionMonoid[A] = new MyMonoid[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x.union(y)
    override def empty = Set.empty[A]
  }

  implicit def intersectionSemigroup[A] = new MySemigroup[Set[A]] {
    override def combine(x: Set[A], y: Set[A]): Set[A] = x.intersect(y)
  }
}
