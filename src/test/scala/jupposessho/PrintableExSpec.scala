package jupposessho

import org.scalatest.{Matchers, WordSpec}
import PrintableEx.PrintDefaults._
import PrintableEx.Print._
import PrintableEx.Cat

class SuiteResolverResponseSpec  extends WordSpec with Matchers {
  "Printable" should {
    "format a string" in {
      "hello".formatP shouldEqual "hello"
    }
    "format an integer" in {
      123.formatP shouldEqual "123"
    }
    "format a Cat" in {
      new Cat("Marvin", 2, "black").formatP shouldEqual "Marvin is a 2 year-old black cat."
    }
  }
}
