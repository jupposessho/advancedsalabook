package jupposessho


trait Printable[A] {
  def format(a: A): String
}

object PrintDefaults {
  implicit val stringFormat = new Printable[String] {
    def format(a: String): String = a
  }

  implicit val intFormat = new Printable[Int] {
    def format(a: Int): String = a.toString
  }
}

object PrintSyntax {
  implicit class PrintableOps[A](value: A) {
    def formatP(implicit printable: Printable[A]): String =
      printable format value

    def printP(implicit printable: Printable[A]): Unit =
      println(printable format value)
  }
}

object cat {
  final case class Cat(name: String, age: Int, color: String)

  object Cat {
    import PrintDefaults._
    import PrintSyntax._

    implicit val catFormat = new Printable[Cat] {
      def format(a: Cat): String = s"${a.name.formatP} is a ${a.age.formatP} year-old ${a.color.formatP} cat."
    }
  }
}

import cat._
import PrintSyntax._

object Main extends App {

  Cat("Kitten", 3 , "red").printP
}
