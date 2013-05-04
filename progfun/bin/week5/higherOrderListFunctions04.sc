package week5

object higherOrderListFunctions04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

/*
---------------
Recurring patterns for computations on lists
  - transforming each element in a list in a certain way
  - retrieving all items of a list satisfying a certain criterion
  - combining the elements of a list using an operator.
We can do these with higher-order functions! Weee!

---------------
1. Transform:

*/
  def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
    case Nil => xs
    case y :: ys => y * factor :: scaleList(ys, factor)
  }                                               //> scaleList: (xs: List[Double], factor: Double)List[Double]
/*
This is the same as 'map', which is in the standard library.
*/
  def scaleListMap(xs: List[Double], factor: Double) =
    xs map(x => x * factor)                       //> scaleListMap: (xs: List[Double], factor: Double)List[Double]
/*
---------------
  a function that squares each element of a list and returns a result.
*/
  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case y :: ys => y * y :: squareList(ys)
  }                                               //> squareList: (xs: List[Int])List[Int]

  def squareListMap(xs: List[Int]): List[Int] =
    xs map(x => x * x)                            //> squareListMap: (xs: List[Int])List[Int]

  squareList(List(1,2,3,4,5,7))                   //> res0: List[Int] = List(1, 4, 9, 16, 25, 49)
  squareListMap(List(1,2,3,4,5,7))                //> res1: List[Int] = List(1, 4, 9, 16, 25, 49)

/*
---------------
Filtering
*/
  def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case y :: ys => if(y > 0) y :: posElems(ys) else posElems(ys)
  }                                               //> posElems: (xs: List[Int])List[Int]

/*
  // this won't compile because it's supposed to be in an abstract genericized class
  
  def filter(p: T => Boolean): List[T] = this match {
    case Nil => this
    case x :: xs => if (p(x)) x :: xs.filter(p) else xs.filter(p)
  }
*/
  def posElemsFilter(xs: List[Int]): List[Int] =
    xs filter(x => x > 0)                         //> posElemsFilter: (xs: List[Int])List[Int]

/*
---------------
Other methods
  xs filterNot p    -- same as xs filter (x => !p(x))

  xs partition p    -- same as (xs filter p, xs filterNot p) but computed
                        in a single traversal of the list xs
  
  xs takeWhile p    -- the longest prefix of list xs consisting of elements that
                        all satisfy the predicate p
  
  xs dropWhile p    -- the remainder of the list xs after any leading elements
                      satisfying p have been removed
  
  xs span p         -- same as (xs takeWhile p, xs dropWhile p) but
                        computed in a single traversal of the list xs

---------------
*/

  val nums = List(2,-4,5,7,1)                     //> nums  : List[Int] = List(2, -4, 5, 7, 1)
  val fruits = List("apple", "pineapple", "orange", "banana")
                                                  //> fruits  : List[String] = List(apple, pineapple, orange, banana)

  nums filter(x => x > 0)                         //> res2: List[Int] = List(2, 5, 7, 1)

  nums filterNot(x => x > 0)                      //> res3: List[Int] = List(-4)

  nums partition(x => x > 0)      // gives 2 lists//> res4: (List[Int], List[Int]) = (List(2, 5, 7, 1),List(-4))

  nums takeWhile(x => x > 0)                      //> res5: List[Int] = List(2)
  // longest prefix of the list such that x > 0. so stops traversing when the predicate is not true.
  
  nums dropWhile(x => x > 0)                      //> res6: List[Int] = List(-4, 5, 7, 1)
  
  nums span(x => x > 0)                           //> res7: (List[Int], List[Int]) = (List(2),List(-4, 5, 7, 1))
  // combines takeWhile and dropWhile

/*
---------------
write a function 'pack' that packs duplicates of list elements into sublists.
*/
val data = List("a","a","a","b", "c", "c", "a")   //> data  : List[String] = List(a, a, a, b, c, c, a)
  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, rest) = xs span (y => y == x)
      first :: pack(rest)
  }                                               //> pack: [T](xs: List[T])List[List[T]]

  pack(data)                                      //> res8: List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(a)
                                                  //| )

/*
---------------
  use 'pack' to write a function 'encode' that produces the run-length encoding of a list
  
  so encode(List("a","a","a","b", "c", "c", "a")) returns
     List(("a", 3), ("b", 1), ("c", 2), ("a", 1))
---------------
*/

                                                  
  def encode[T](xs: List[T]): List[(T, Int)] = {
		val packed = pack(xs)
		packed.map(ys => (ys.head, ys.length))
  }                                               //> encode: [T](xs: List[T])List[(T, Int)]

  encode(data)                                    //> res9: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))



}