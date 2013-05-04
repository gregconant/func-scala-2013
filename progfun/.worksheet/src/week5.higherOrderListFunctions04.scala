package week5

object higherOrderListFunctions04 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(94); 
  println("Welcome to the Scala worksheet");$skip(486); 

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
  };System.out.println("""scaleList: (xs: List[Double], factor: Double)List[Double]""");$skip(150); 
/*
This is the same as 'map', which is in the standard library.
*/
  def scaleListMap(xs: List[Double], factor: Double) =
    xs map(x => x * factor);System.out.println("""scaleListMap: (xs: List[Double], factor: Double)List[Double]""");$skip(217); 
/*
---------------
  a function that squares each element of a list and returns a result.
*/
  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case y :: ys => y * y :: squareList(ys)
  };System.out.println("""squareList: (xs: List[Int])List[Int]""");$skip(72); 

  def squareListMap(xs: List[Int]): List[Int] =
    xs map(x => x * x);System.out.println("""squareListMap: (xs: List[Int])List[Int]""");$skip(33); val res$0 = 

  squareList(List(1,2,3,4,5,7));System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(35); val res$1 = 
  squareListMap(List(1,2,3,4,5,7));System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(177); 

/*
---------------
Filtering
*/
  def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case y :: ys => if(y > 0) y :: posElems(ys) else posElems(ys)
  };System.out.println("""posElems: (xs: List[Int])List[Int]""");$skip(315); 

/*
  // this won't compile because it's supposed to be in an abstract genericized class
  
  def filter(p: T => Boolean): List[T] = this match {
    case Nil => this
    case x :: xs => if (p(x)) x :: xs.filter(p) else xs.filter(p)
  }
*/
  def posElemsFilter(xs: List[Int]): List[Int] =
    xs filter(x => x > 0);System.out.println("""posElemsFilter: (xs: List[Int])List[Int]""");$skip(687); 

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

  val nums = List(2,-4,5,7,1);System.out.println("""nums  : List[Int] = """ + $show(nums ));$skip(62); 
  val fruits = List("apple", "pineapple", "orange", "banana");System.out.println("""fruits  : List[String] = """ + $show(fruits ));$skip(27); val res$2 = 

  nums filter(x => x > 0);System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(30); val res$3 = 

  nums filterNot(x => x > 0);System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(52); val res$4 = 

  nums partition(x => x > 0);System.out.println("""res4: (List[Int], List[Int]) = """ + $show(res$4));$skip(30); val res$5 =       // gives 2 lists

  nums takeWhile(x => x > 0);System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(133); val res$6 = 
  // longest prefix of the list such that x > 0. so stops traversing when the predicate is not true.
  
  nums dropWhile(x => x > 0);System.out.println("""res6: List[Int] = """ + $show(res$6));$skip(27); val res$7 = 
  
  nums span(x => x > 0);System.out.println("""res7: (List[Int], List[Int]) = """ + $show(res$7));$skip(188); 
  // combines takeWhile and dropWhile

/*
---------------
write a function 'pack' that packs duplicates of list elements into sublists.
*/
val data = List("a","a","a","b", "c", "c", "a");System.out.println("""data  : List[String] = """ + $show(data ));$skip(174); 
  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    case x :: xs1 =>
      val (first, rest) = xs span (y => y == x)
      first :: pack(rest)
  };System.out.println("""pack: [T](xs: List[T])List[List[T]]""");$skip(14); val res$8 = 

  pack(data);System.out.println("""res8: List[List[String]] = """ + $show(res$8));$skip(410); 

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
  };System.out.println("""encode: [T](xs: List[T])List[(T, Int)]""");$skip(16); val res$9 = 

  encode(data);System.out.println("""res9: List[(String, Int)] = """ + $show(res$9))}



}
