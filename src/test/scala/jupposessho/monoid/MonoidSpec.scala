package jupposessho.monoid

import org.scalatest.{Matchers, WordSpec}

class MonoidSpec extends WordSpec with Matchers {

	"SuperAdder" should {
		
		import Monoids.SuperAdder

		import cats.std.int._
		import cats.std.option._

		"sum an empty list" in {
			SuperAdder.add(List.empty[Int]) shouldBe 0
		}

		"sum a non-empty list of integers" in {
			SuperAdder.add(List(1, 2, 3)) shouldBe 6
		}

		"sum a non-empty list of options of integers" in {
			SuperAdder.add(List(Some(1), Some(2), None, Some(3))) shouldBe Some(6)
		}

		"sum a non-empty list of orders" in {
			import Monoids.order._
			SuperAdder.add(List(Order(1.1, 2.2), Order(1.0, 2.0), Order(3.4, 4.7))) shouldBe Order(5.5, 8.9)
		}
	}
}
