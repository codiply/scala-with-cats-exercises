package com.codiply.scalawithcats.chapter2

import cats.Monoid

object OrderMonoidInstances {
  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    def combine(x: Order, y: Order): Order = Order(x.totalCost + y.totalCost, x.quantity + y.quantity)
    def empty: Order = Order(0, 0)
  }
}
