package week5

import math.Ordering

object implicitParameters03 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

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
  }                                               //> msort: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]

  val nums = List(2,-4,5,7,1)                     //> nums  : List[Int] = List(2, -4, 5, 7, 1)
  val fruits = List("apple", "pineapple", "orange", "banana")
                                                  //> fruits  : List[String] = List(apple, pineapple, orange, banana)
 // can leave out types because they will be inferred
// msort(nums)((x: Int, y: Int) => x < y)
  msort(nums)((x, y) => x < y)                    //> res0: List[Int] = List(-4, 1, 2, 5, 7)
  msort(fruits)((x: String, y: String) => x.compareTo(y) < 0)
                                                  //> res1: List[String] = List(apple, banana, orange, pineapple)
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
  }                                               //> msort2: [T](xs: List[T])(ord: scala.math.Ordering[T])List[T]
  
  msort2(nums)(Ordering.Int)                      //> res2: List[Int] = List(-4, 1, 2, 5, 7)
  msort2(fruits)(Ordering.String)                 //> res3: List[String] = List(apple, banana, orange, pineapple)

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