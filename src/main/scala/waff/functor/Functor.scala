package waff.functor

import cats.Functor

object resultFunct {

	sealed trait Result[+A]
	final case class Success[A](value: A) extends Result[A]
	final case class Warning[A](value: A, message: String) extends Result[A]
	final case class Failure(message: String) extends Result[Nothing]

	implicit val resultFunctor = new Functor[Result] {
		def map[A, B](value: Result[A])(func: A => B): Result[B] = value match {
			case Success(v) => Success(func(v))
			case Warning(v, m) => Warning(func(v), m)
			case Failure(m) => Failure(m)
		}
	}

	def success[A](value: A): Result[A] = Success(value)
	def warning[A](value: A, message: String): Result[A] = Warning(value, message)
	def failure[A](message: String): Result[A] = Failure(message)	
}
