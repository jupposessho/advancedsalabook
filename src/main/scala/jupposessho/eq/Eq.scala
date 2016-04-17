package jupposessho.eq

import cats._
import cats.Eq
import cats.syntax.eq._
	
object cat {
  final case class Cat(name: String, age: Int, owner: Option[String])

  object Cat {
    implicit val catEqual = Eq.instance[Cat] { (cat1, cat2) => 
    	import cats.std.int._
		import cats.std.string._
		import cats.std.option._

    	(cat1.name === cat2.name) &&
    	(cat1.age === cat2.age) &&
    	(cat1.owner === cat2.owner)
	}
  }
}
import cat._

object Main extends App {

	val cat1 = Cat("Garfield", 35, Some("orange and black")) 
	val cat2 = Cat("Garfield", 35, Some("orange and black1"))

	println(cat1 === cat2)
}