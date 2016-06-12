package jupposessho.monoid

import cats.{Semigroup, Monoid}
import cats.syntax.semigroup._

object Monoids {
	implicit val booleanAndMonoid = new Monoid[Boolean] {
		def combine(a: Boolean, b: Boolean) = a && b
		def empty = true
	}

	implicit val booleanOrMonoid = new Monoid[Boolean] {
		def combine(a: Boolean, b: Boolean) = a || b
		def empty = false
	}

	implicit def setUnionMonoid[T] = new Monoid[Set[T]] {
		def combine(a: Set[T], b: Set[T]) = a union b
		def empty = Set.empty[T]
	}	

	implicit def setIntersectSemigroup[T] = new Semigroup[Set[T]] {
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

	object SuperAdder {

		// def add[T](items: List[T])(implicit monoid: Monoid[T]): T = items.foldLeft(monoid.empty)(_ |+| _)
		def add[T: Monoid](items: List[T]): T = items.foldLeft(Monoid[T].empty)(_ |+| _)
	}

}
