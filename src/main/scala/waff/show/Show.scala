package waff.show

import cats._, cats.instances.all._, cats.syntax.show._
import cats._
import cats.instances.all._
import cats.syntax.show._
	
object cat {
  final case class Cat(name: String, age: Int, color: String)

  object Cat {
    implicit val catShow = Show.show[Cat] { cat =>
    	s"${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat."
    }
  }
}
import cat._

object Main extends App {

	println(Cat("Malvin", 3, "red").show)
}