package jupposessho

object PrintableEx {

  final case class Cat(name: String, age: Int, color: String)

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

    implicit val catFormat = new Printable[Cat] {
      def format(a: Cat): String = s"${a.name} is a ${a.age.toString} year-old ${a.color} cat."
    }
  }

  object Print {
    implicit class PrintableOps[A](value: A) {
      def formatP(implicit printable: Printable[A]): String =
        printable.format(value)

      def printP(implicit printable: Printable[A]): Unit =
        println(printable.format(value))
    }
  }

  // object Print {
  //   def format[A](input: A)(implicit printer: Printable[A]): String = {
  //     printer.format(input)
  //   }
  //   def print[A](input: A)(implicit printer: Printable[A]): Unit = {
  //     println(format(input))
  //   }
  // }
}

object Main extends App {

  import PrintableEx.PrintDefaults._
  import PrintableEx.Print._
  import PrintableEx.Cat
  
  val cat = new Cat("Kitten", 3 , "red")

  cat.printP
}
