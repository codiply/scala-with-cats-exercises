package com.codiply.scalawithcats

import scala.language.higherKinds
import cats.Apply

object Summary {
  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  trait Monoid[A] {
    def empty: A

    // Semigroup
    def combine(x: A, y: A): A
  }

  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

  trait ContravariantFunctor[F[_]] {
    def contramap[A, B](fa: F[A])(f: B => A): F[B]
  }

  trait InvariantFunctor[F[_]] {
    def imap[A, B](fa: F[A])(f: A => B)(g: B => A): F[B]
  }

  trait Monad[F[_]] {
    def pure[A](value: A): F[A]
    def flatMap[A, B](value: F[A])(func: A => F[B]): F[B]

    // Functor
    def map[A, B](fa: F[A])(f: A => B): F[B] = flatMap(fa)(x => pure(f(x)))
  }

  trait Semigroupal[F[_]] {
    def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]
  }

  trait Apply[F[_]] {
    def ap[A, B](ff: F[A => B])(fa: F[A]): F[B]

    // Functor
    def map[A, B](fa: F[A])(f: A => B): F[B]

    // Semigroupal
    def product[A, B](fa: F[A], fb: F[B]): F[(A, B)] =
      ap(map(fa)(a => (b: B) => (a, b)))(fb)
  }

  trait Applicative[F[_]] extends Apply[F] {
    def pure[A](a: A): F[A]
  }

  trait Traverse[F[_]] {
    def traverse[G[_]: Applicative, A, B](inputs: F[A])(func: A => G[B]): G[F[B]]

    def sequence[G[_]: Applicative, B](inputs: F[G[B]]): G[F[B]] = traverse(inputs)(identity)
  }
}
