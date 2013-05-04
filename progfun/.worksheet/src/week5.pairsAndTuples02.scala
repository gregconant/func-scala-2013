package week5

object pairsAndTuples02 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(84); 
  println("Welcome to the Scala worksheet");$skip(379); 
  
  
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
  };System.out.println("""msort: (xs: List[Int])List[Int]""");$skip(461); 

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
    };System.out.println("""merge: (xs: List[Int], ys: List[Int])List[Int]""");$skip(169); 
/*
  splitAt returns 2 sublists
  elements up to the given index
  and elements after that
  elements are returned as Pairs (i.e. Tuples)
*/
  val pair = ("answer", 42);System.out.println("""pair  : (String, Int) = """ + $show(pair ));$skip(106); 
/*
  type of 'pair' is (String, Int)
  
  Can also pattern-match on pairs.
*/
  val (label, value) = pair;System.out.println("""label  : String = """ + $show(label ));System.out.println("""value  : Int = """ + $show(value ));$skip(751); 
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
 };System.out.println("""merge2: (xs: List[Int], ys: List[Int])List[Int]""");$skip(237); 

  def msort2(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if(n == 0) xs
    else {
//      def merge(xs: List[Int], ys: List[Int]) = ???
      val (fst, snd) = xs splitAt n
      merge2(msort2(fst), msort(snd))
    }
  };System.out.println("""msort2: (xs: List[Int])List[Int]""");$skip(29); 
 val nums = List(2,-4,5,7,1);System.out.println("""nums  : List[Int] = """ + $show(nums ));$skip(19); val res$0 = 
    
 msort2(nums);System.out.println("""res0: List[Int] = """ + $show(res$0))}
    
}
