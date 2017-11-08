package waff.monad

import scala.language.higherKinds

object monad {
	
	def flatMap[F[_], A, B](value: F[A])(func: A => F[B]): F[B] = ??? 
	def pure[F[_], A](value: A): F[A] = ???

	def map[F[_], A, B](value: F[A])(func: A => B): F[B] = ???
}