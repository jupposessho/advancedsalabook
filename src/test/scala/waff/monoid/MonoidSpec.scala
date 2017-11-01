package waff.monoid

import org.scalatest.{Matchers, WordSpec}
import shapeless._

class MonoidSpec extends WordSpec with Matchers {

  "SuperAdder" should {

    import Monoids.SuperAdder

    import cats.instances.int._
    import cats.instances.string._
    import cats.instances.option._

    "sum an empty list" in {
      SuperAdder.add(List.empty[Int]) shouldBe 0
    }

    "sum a non-empty list of integers" in {
      SuperAdder.add(List(1, 2, 3)) shouldBe 6
    }

    "sum a non-empty list of options of integers" in {
      SuperAdder.add(List(Some(1), Some(2), None, Some(3))) shouldBe Some(6)
    }

    "sum a non-empty list of strings" in {
      SuperAdder.add(List("hello", " ", "world", "!")) shouldBe "hello world!"
    }

    "sum a non-empty list of orders" in {
      import Monoids.order._
      SuperAdder.add(List(Order(1.1, 2.2), Order(1.0, 2.0), Order(3.4, 4.7))) shouldBe Order(5.5, 8.9)
    }

    "sum of a non-empty hlist" in {
      import Monoids.generic._
      SuperAdder.add(
        List(
          1 :: "hello" :: HNil,
          3 :: " " :: HNil,
          2 :: "world!" :: HNil
        )) shouldBe 6 :: "hello world!" :: HNil
    }

    "sum a non-empty list of any case class" in {
			import cats.instances.double._
			import Monoids._
      import Monoids.generic._

      SuperAdder.add(List(Foo(1, "hello", 2.2, true), Foo(3, " ", 1.1, true), Foo(2, "world!", 3.3, true))) shouldBe Foo(6, "hello world!", 6.6, true)
      SuperAdder.add(List(Bar("hello", true), Bar(" world!", true))) shouldBe Bar("hello world!", true)
    }
  }
}
