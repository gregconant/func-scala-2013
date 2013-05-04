package week5

import math.Ordering

object implicitParameters03 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(110); 
  println("Welcome to the Scala worksheet");$skip(798); 

/*
Making sort more general
  how can we parameterize msort from last video so it can be used for
  non-Int lists?
  
  just using a generic won't work because the comparison has to still be valid.
  
  make sort function polymorphic and pass the comparison operation as an additional parameter.
*/
  def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    val n = xs.length / 2
    if(n == 0) xs
    else {
		  def merge(xs: List[T], ys: List[T]): List[T] =  (xs, ys) match {
		    case (Nil, right) => right
		    case (left, Nil) => left
		    case(lhead :: ltail, rhead :: rtail) => {
			    if(lt(lhead, rhead)) lhead :: merge(ltail, ys)
			    else rhead :: merge(rtail, xs)
		    }
		  }
		 
     val (fst, snd) = xs splitAt n
     merge(msort(fst)(lt), msort(snd)(lt))
    }
  };System.out.println("""msort: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]""");$skip(31); 

  val nums = List(2,-4,5,7,1);System.out.println("""nums  : List[Int] = """ + $show(nums ));$skip(62); 
  val fruits = List("apple", "pineapple", "orange", "banana");System.out.println("""fruits  : List[String] = """ + $show(fruits ));$skip(127); val res$0 = 
 // can leave out types because they will be inferred
// msort(nums)((x: Int, y: Int) => x < y)
  msort(nums)((x, y) => x < y);System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(62); val res$1 = 
  msort(fruits)((x: String, y: String) => x.compareTo(y) < 0);System.out.println("""res1: List[String] = """ + $show(res$1));$skip(723); 
/*
---------------
there is a class that represents orderings
  scala.math.Ordering[T]

instead of parameterizing with lt function directly, as we did above,
we can pass in an 'ordering' parameter to do it for us.
*/
  def msort2[T](xs: List[T])(ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if(n == 0) xs
    else {
		  def merge2(xs: List[T], ys: List[T]): List[T] =  (xs, ys) match {
		    case (Nil, right) => right
		    case (left, Nil) => left
		    case(lhead :: ltail, rhead :: rtail) => {
			    if(ord.lt(lhead, rhead)) lhead :: merge2(ltail, ys)
			    else rhead :: merge2(rtail, xs)
		    }
		  }
		 
     val (fst, snd) = xs splitAt n
     merge2(msort2(fst)(ord), msort2(snd)(ord))
    }
  };System.out.println("""msort2: [T](xs: List[T])(ord: scala.math.Ordering[T])List[T]""");$skip(32); val res$2 = 
  
  msort2(nums)(Ordering.Int);System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(34); val res$3 = 
  msort2(fruits)(Ordering.String);System.out.println("""res3: List[String] = """ + $show(res$3))}

/*
---------------
  Passing around the 'lt' or 'ord' is cumbersome.
  Let's make it an implicit parameter.
  
  
  def msort2[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
  ...
  }
  
  This works because the compiler figures out the type or Ordering to use
  based on the inferred types.
*/



}
