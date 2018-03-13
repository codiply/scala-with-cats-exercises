package com.codiply.scalawithcats.chapter5

import cats.data.EitherT
import cats.instances.future._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

class Autobots(powerLevels: Map[String, Int]) {
  import scala.concurrent.ExecutionContext.Implicits.global

  type Response[A] = EitherT[Future, String, A]

  def getPowerLevel(autobot: String): Response[Int] =
    powerLevels.get(autobot) match {
      case Some(level) => EitherT.right(Future(level))
      case None => EitherT.left(Future(s"$autobot unreachable"))
    }

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] =
    for {
      power1 <- getPowerLevel(ally1)
      power2 <- getPowerLevel(ally2)
    } yield power1 + power2 > 15

  def tacticalReport(ally1: String, ally2: String): String =
    Await.result(canSpecialMove(ally1, ally2).value, 3.seconds) match {
      case Right(true) => s"$ally1 and $ally2 are ready to roll out!"
      case Right(false) => s"$ally1 and $ally2 need a recharge."
      case Left(error) => s"Comms error: $error"
    }
}
