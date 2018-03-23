package com.schibsted.exercises.chapter3

import scala.util.Try

object FunctorVariance {
  case class Box[T](value: T)

  trait Ordered[A] { self =>
    def compare(x: A, y: A): Int

    def contramap[B](func: B => A): Ordered[B] = new Ordered[B] {
      override def compare(x: B, y: B): Int = self.compare(func(x), func(y))
    }
  }

  def compare[A](x: A, y: A)(implicit ordered: Ordered[A]) : Int = {
    ordered.compare(x, y)
  }

  implicit val intCompare: Ordered[Int] = new Ordered[Int] {
    override def compare(x: Int, y: Int): Int = x - y
  }

  implicit def boxCompare[T](implicit ordered: Ordered[T]): Ordered[Box[T]] = {
    ordered.contramap[Box[T]](_.value)
  }

  trait Codec[A] { self =>
    def encode(value: A): String

    def decode(value: String): Option[A]

    def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
      def encode(value: B): String = self.encode(enc(value))
      def decode(value: String): Option[B] = self.decode(value).map(dec)
    }
  }

  def encode[A](value: A)(implicit c: Codec[A]): String =
    c.encode(value)
  def decode[A](value: String)(implicit c: Codec[A]): Option[A] =
    c.decode(value)

  implicit val intCodec: Codec[Int] = new Codec[Int] {
    override def encode(value: Int): String = value.toString
    override def decode(value: String): Option[Int] = Try(value.toInt).toOption
  }

  implicit def boxCodec[T](implicit codec: Codec[T]): Codec[Box[T]] = codec.imap(Box(_), _.value)

  def main(args: Array[String]): Unit = {
    println(compare(Box(Box(1)), Box(Box(2))))

    println(decode[Box[Int]]("10"))
    println(decode[Box[Int]]("hello"))
    println(encode(Box(20)))
  }
}
