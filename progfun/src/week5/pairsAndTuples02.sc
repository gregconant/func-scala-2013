package week5

object pairsAndTuples02 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  /*
---------------
Merge Sort
separate list into 2 sub-lists, each about half of original list.
sort each in turn.
merge into one list
*/
  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if(n == 0) xs
    else {
//      def merge(xs: List[Int], ys: List[Int]) = ???
      val (fst, snd) = xs splitAt n
      merge(msort(fst), msort(snd))
    }
  }                                               //> msort: (xs: List[Int])List[Int]

/*
---------------
  Simple implementation of merge
*/
  def merge(xs: List[Int], ys: List[Int]): List[Int] =
    xs match {
      case Nil =>
        ys
      case x :: xs1 =>
        ys match {
          case Nil =>
            xs
          case y :: ys1 =>
          // this mean we have 2 head elements (x and y)
          // and 2 tail elements (xs1 and ys1)
            if (x < y) x :: merge(xs1, ys)
            else y :: merge(xs, ys1)
        }
    }                                             //> merge: (xs: List[Int], ys: List[Int])List[Int]
/*
  splitAt returns 2 sublists
  elements up to the given index
  and elements after that
  elements are returned as Pairs (i.e. Tuples)
*/
  val pair = ("answer", 42)                       //> pair  : (String, Int) = (answer,42)
/*
  type of 'pair' is (String, Int)
  
  Can also pattern-match on pairs.
*/
  val (label, value) = pair                       //> label  : String = answer
                                                  //| value  : Int = 42
/*
---------------
  A tuple type is an abbreviation of the parameterized type scala.Tuplen[T1,...,Tn]
---------------
  Tuples are case classes
  
  fields of a tuple can be accessed with _1, _2
  
  so instead of pattern binding
    val (label, value) = pair
    
  one can also write
    val label = pair._1
    val value = pair._2
---------------
  Merge uses a nested pattern match.
  Rewrite merge using a pattern matching over pairs:
  
  
*/
  def merge2(xs: List[Int], ys: List[Int]): List[Int] = {
    (xs, ys) match {
      case (Nil, right) => right
      case (left, Nil) => left
      case(lhead :: ltail, rhead :: rtail) => {
        if(lhead < rhead) lhead :: merge2(ltail, ys)
        else rhead :: merge2(rtail, xs)
      }
    }
 }                                                //> merge2: (xs: List[Int], ys: List[Int])List[Int]

  def msort2(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if(n == 0) xs
    else {
//      def merge(xs: List[Int], ys: List[Int]) = ???
      val (fst, snd) = xs splitAt n
      merge2(msort2(fst), msort(snd))
    }
  }                                               //> msort2: (xs: List[Int])List[Int]
 val nums = List(2,-4,5,7,1)                      //> nums  : List[Int] = List(2, -4, 5, 7, 1)
    
 msort2(nums)                                     //> res0: List[Int] = List(-4, 1, 2, 5, 7)
    
}