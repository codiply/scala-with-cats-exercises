package com.codiply.scalawithcats.chapter2

object BooleanMonoidInstances {
  implicit val andMonoid = new MyMonoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x && y
    override def empty: Boolean = true
  }

  implicit val orMonoid = new MyMonoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = x || y
    override def empty: Boolean = false
  }

  implicit val xorMonoid = new MyMonoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = (x && !y) || (!x && y)
    override def empty: Boolean = false
  }

  implicit val xnorMonoid = new MyMonoid[Boolean] {
    override def combine(x: Boolean, y: Boolean): Boolean = (!x || y) && (x || !y)
    override def empty: Boolean = true
  }
}
