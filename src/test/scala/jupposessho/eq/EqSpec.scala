package jupposessho.eq

import org.scalatest.{Matchers, WordSpec}
import cat._

class EqSpec  extends WordSpec with Matchers {
	val garfield = Cat("Garfield", 35, Some("uncle Joe"))

	"Eq" should {
		
		"return true if the Cats are equal" in {
			Cat("Garfield", 35, Some("uncle Joe")) === garfield shouldBe true
		}

		"return false if name is different" in {
			garfield.copy(name = "not Garfield") === garfield shouldBe false
		}

		"return false if age is different" in {
			garfield.copy(age = 31) === garfield shouldBe false
		}

		"return false if owner is different" in {
			garfield.copy(owner = Some("Joe")) === garfield shouldBe false
		}

		"return false if owner is none" in {
			garfield.copy(owner = None) === garfield shouldBe false
		}

		"return true w/ same options" in {
			Some(garfield) === Some(garfield) shouldBe true
		}

		"return false w/ some vs none" in {
			Some(garfield) === None shouldBe false
		}
	}
}
