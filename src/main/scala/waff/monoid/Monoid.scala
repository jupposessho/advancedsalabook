package waff.monoid

import cats.{Semigroup, Monoid}
import cats.syntax.semigroup._
import shapeless._

object Monoids {
	implicit val booleanAndMonoid = new Monoid[Boolean] {
		def combine(a: Boolean, b: Boolean) = a && b
		def empty = true
	}

//	implicit val booleanOrMonoid = new Monoid[Boolean] {
//		def combine(a: Boolean, b: Boolean) = a || b
//		def empty = false
//	}

	implicit def setUnionMonoid[T] = new Monoid[Set[T]] {
		def combine(a: Set[T], b: Set[T]) = a union b
		def empty = Set.empty[T]
	}	

	implicit def setIntersectSemigrouo[T] = new Semigroup[Set[T]] {
		def combine(a: Set[T], b: Set[T]) = a intersect b
	}

	object order {
		case class Order(totalCost: Double, quantity: Double)
		 
		 object Order {
		 	implicit val monoid: Monoid[Order] = new Monoid[Order] {
		 		def combine(o1: Order, o2: Order) = new Order(o1.totalCost + o2.totalCost, o1.quantity + o2.quantity)
		 		def empty = new Order(0.0, 0.0)
		 	}
		 }
	}

	object generic {
		final case class Foo(i: Int, s: String, d: Double, b: Boolean)
		final case class Bar(s: String, b: Boolean)

		implicit val hnilMonoid: Monoid[HNil] = new Monoid[HNil] {
			def combine(oo: HNil, o2: HNil) = HNil
		 	def empty = HNil
		}

		implicit def hlistMonoid[H, T <: HList](
			implicit 
			hMonoid: Monoid[H],
			tMonoid: Monoid[T]
		): Monoid[H :: T] = new Monoid[H :: T] {
			def combine(o1: H :: T, o2: H :: T) = (o1, o2) match {
				case (h1 :: t1, h2 :: t2) =>
					hMonoid.combine(h1, h2) :: tMonoid.combine(t1, t2)
			}
		 	def empty = hMonoid.empty :: tMonoid.empty
		}

		implicit def genMonoid[A, R](
		    implicit
		    gen: Generic.Aux[A, R],
		    mon: Monoid[R]
		  ): Monoid[A] = new Monoid[A] {
            def empty: A = gen.from(mon.empty)
			def combine(a: A, b: A): A = {
				gen.from(mon.combine(gen.to(a), gen.to(b)))
			}
		}
	}

	object SuperAdder {

		// def add[T](items: List[T])(implicit monoid: Monoid[T]): T = items.foldLeft(monoid.empty)(_ |+| _)
		def add[T: Monoid](items: List[T]): T = items.foldLeft(Monoid[T].empty)(_ |+| _)
	}

}
