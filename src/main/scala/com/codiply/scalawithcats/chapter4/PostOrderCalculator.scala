package com.codiply.scalawithcats.chapter4

import cats.data.State
import cats.syntax.applicative._

object PostOrderCalculator {
  type CalcState[A] = State[List[Int], A]

  def evalOne(sym: String): CalcState[Int] = State[List[Int], Int] { oldStack =>
    sym match {
      case "+" => operate(_ + _, oldStack)
      case "-" => operate(_ - _, oldStack)
      case "*" => operate(_ * _, oldStack)
      case "/" => operate(_ / _, oldStack)
      case _ => {
        val x = sym.toInt
        (x :: oldStack, x)
      }
    }
  }

  def evalAll(input: List[String]): CalcState[Int] =
    input.foldLeft(0.pure[CalcState]) { (acc: CalcState[Int], sym) => acc.flatMap(_ => evalOne(sym)) }

  def evalInput(input: String): Int =
    evalAll(input.split(" ").toList).runA(Nil).value

  private[this] def operate(op: (Int, Int) => Int, stack: List[Int]): (List[Int], Int) = {
    stack match {
      case a :: b :: tail => {
        val answer = op(b, a)
        (answer :: tail, answer)
      }
      case _ => sys.error("Stack contains less than 2 numbers to operate on.")
    }
  }
}
