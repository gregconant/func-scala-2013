package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 101))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    
    val intsBetweenZeroAndTen: Set = (x) => x <= 10 && x > 0
    val evens: Set = (x) => x % 2 == 0
    val odds: Set = (x) => x % 2 == 1
    
    val evenFilter: (Int => Boolean) = (x) => x % 2 == 0
    val fromOneToOneHundred: Set = (x) => x <= 100 && x > 0
    
    val fromNegativeToPositiveOneThousand: Set = (x) => x >= -1000 && x <= 1000
    val absoluteLessThanOneThousand: (Int => Boolean) = (x) => Math.abs(x) <= 1000

    val tensFromZeroToOneHundred: Set = (x) => x % 10 == 0
	val lessThanOneHundred: Set = (x) => x < 100
	val evensLessThanTen: Set = (x) => x == 2 || x == 4 || x == 6 || x == 8
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  //ignore("singletonSet(1) contains 1") {
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton s1 contains 1")
      assert(contains(s2, 2), "Singleton s2 contains 2")
      assert(contains(s3, 3), "Singleton s3 contains 3")
      assert(!contains(s1, 3), "Singleton s1 does not contain 3")
      assert(!contains(s2, 1), "Singleton s2 does not contain 1")
      assert(!contains(s3, 2), "Singleton s3 does not contain 2")
      assert(!contains(s1, 4), "Singleton s1 does not contain 4")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val unioned = union(s1, s2)
      assert(contains(unioned, 1), "Union 1")
      assert(contains(unioned, 2), "Union 2")
      assert(!contains(unioned, 3), "Union 3")
    }
  }

  test("intersect contains elements in both") {
    new TestSets {
      val intersected = intersect(intsBetweenZeroAndTen, evens)
      assert(contains(intersected, 2), "2 is in intersect")
      assert(contains(intersected, 4), "4 is in intersect")
      assert(contains(intersected, 6), "6 is in intersect")
      assert(!contains(intersected, 1), "1 is not in intersect")
      assert(!contains(intersected, 3), "3 is not in intersect")
      assert(!contains(intersected, 11), "11 is not in intersect")
    }
  }

  test("diff contains elements in a but not in b") {
    new TestSets {
      val diffed = diff(intsBetweenZeroAndTen, evens)
      assert(!contains(diffed , 0), "0 is not in diff")
      assert(!contains(diffed , 2), "2 is not in diff")
      assert(contains(diffed , 3), "3 is in diff")
      assert(contains(diffed , 9), "9 is in diff")
      assert(!contains(diffed , 10), "10 is not in diff")
      assert(!contains(diffed , 11), "11 is not in diff")
    }
  }

  test("filter applies correctly to a set") {
    new TestSets {
      val filtered = filter(fromOneToOneHundred, evenFilter)
      assert(contains(filtered, 2), "2 is in filter")
      assert(contains(filtered, 20), "20 is in filter")
      assert(contains(filtered, 50), "50 is in filter")
      assert(contains(filtered, 80), "80 is in filter")
      assert(contains(filtered, 100), "100 is in filter")
      assert(!contains(filtered, 1), "1 is not in filter")
      assert(!contains(filtered, 11), "11 is not in filter")
      assert(!contains(filtered, 51), "51 is in filter")
      assert(!contains(filtered, 101), "101 is not in filter")
      assert(!contains(filtered, 0), "0 is not in filter")
      assert(!contains(filtered, -1), "-1 is not in filter")
    }
  }

  test("forall tests correctly") {
    new TestSets {
      assert(!forall(intsBetweenZeroAndTen, evens), "all ints between zero and ten are not even")
      assert(forall(evensLessThanTen, evens), "all evens between zero and ten are even")
      assert(forall(tensFromZeroToOneHundred, evens), "every ten numbers between zero and ten is even")
      assert(forall(intsBetweenZeroAndTen, lessThanOneHundred), "all ints between zero and ten are less than one hundred")
    }
  }

  test("exists tests correctly") {
    new TestSets {
      val greaterThanOneHundred: Set = (x) => x > 100
      val intIsThree: (Int => Boolean) = (x) => x == 3
      val intIsThreeHundredAndFour: (Int => Boolean) = (x) => x == 304

      assert(exists(evens, intIsThreeHundredAndFour), "there is an integer, 304, in the set of odd numbers.")
      assert(exists(odds, intIsThree), "there is an integer, 3, in the set of odd numbers.")
      assert(!exists(evensLessThanTen, greaterThanOneHundred), "there is not an integer in the set of even numbers < 10 that is greater than 100.")
      assert(!exists(evens, odds), "there are no evens that are odd")
    }
  }
}
