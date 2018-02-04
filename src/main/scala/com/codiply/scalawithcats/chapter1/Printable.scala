package com.codiply.scalawithcats.chapter1

trait Printable[A] {
  def format(value: A): String
}

object Printable {
  def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)

  def print[A](value: A)(implicit printable: Printable[A]): Unit = println(printable.format(value))
}

object PrintableInstances {
  implicit val printableInt: Printable[Int] = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }

  implicit val printableString: Printable[String] = new Printable[String] {
    override def format(value: String): String = value
  }

  implicit val printableCat: Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat): String =
      s"${value.name} is a ${value.age} year-old ${value.color} cat."
  }

  implicit def optionPrintable[A](implicit printable: Printable[A]): Printable[Option[A]] =
    new Printable[Option[A]] {
      override def format(option: Option[A]): String = option match {
        case Some(value) => s"Some(${printable.format(value)})"
        case None => "None"
      }
    }
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit printable: Printable[A]): String = printable.format(value)

    def print(implicit printable: Printable[A]): Unit = println(printable.format(value))
  }
}

object PrintableMain {
  def main(args: Array[String]): Unit = {
    import PrintableInstances._
    import PrintableSyntax._

    val x = 123
    val str = "this is a string"
    val cat = Cat("Tiger", 3, "brown")

    Printable.print(x)
    Printable.print(str)
    Printable.print(cat)

    x.print
    str.print
    cat.print

    //Printable.print(Some(cat))
  }
}
