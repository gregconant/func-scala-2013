package week7

object streams02 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(77); 
  println("Welcome to the Scala worksheet");$skip(647); 
/*
--------------------------
Collections and Combinatorial Search
  To find 2nd prime # between 1000 and 10000:
  
    ((1000 to 10000) filter isPrime)(1)
  
  much shorter than the recursive alternative.
--------------------------
  This is inefficient, though.
  We construct ALL primes in this range, but only care about the second one.
  
*/
/*
--------------------------
Delayed Evaluation

  Avoid computing the tail of a sequence until it is needed for
  the evaluation result (whihch might be never)
  
  STREAM
    similar to lists, but the tail is evaluated only on demand.
  
*/
  val xs = Stream.cons(1, Stream.cons(2, Stream.empty));System.out.println("""xs  : Stream.Cons[Int] = """ + $show(xs ));$skip(16); val res$0 = 
  Stream(1,2,3);System.out.println("""res0: scala.collection.immutable.Stream[Int] = """ + $show(res$0));$skip(26); val res$1 = 
  
  (1 to 1000).toStream;System.out.println("""res1: scala.collection.immutable.Stream[Int] = """ + $show(res$1));$skip(257); 
/*
--------------------------
Stream ranges

  to write a function that returns (lo until hi).toStream directly:
  
*/
  def streamRange(lo: Int, hi: Int): Stream[Int] = {
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange(lo + 1, hi))
  };System.out.println("""streamRange: (lo: Int, hi: Int)Stream[Int]""");$skip(170); 
/*
  Compare to the same function that returns a list:
*/
  def listRange(lo: Int, hi: Int): List[Int] = {
    if (lo >= hi) Nil
    else lo :: listRange(lo + 1, hi)
  };System.out.println("""listRange: (lo: Int, hi: Int)List[Int]""");$skip(1409); 
/*
  These 2 functions are isomorphic
  BUT!
    - listRange creates the whole list at once.
    - streamRange builds the list as you need it.
--------------------------
Methods on Streams
  - generally the same as list methods

  - to find the 2nd prime # as above:
  
    ((1000 to 10000).toStream filter isPrime)(1)

*/
/*
--------------------------
Exception: Cons

  x :: xs always produces a list, never a stream
  
  Alternative: #::
  produces a stream
  
    x #:: xs == Stream.cons(x, xs)
  
  #:: can be used in expressions as well as patterns.
--------------------------
Implementation of Streams
  trait 'Stream':
  
    trait Stream[+A] extends Seq[A] {
      def isEmpty: Boolean
      def head: A
      def tail: Stream[A]
      ...
    }
    
  As for lists, all other methods can be defined in terms of these three.
*/
/*
--------------------------
  Concrete implementations of streams:
  
    defined in the Stream companion object.
    
    'empty' and 'cons' are both defined on Stream
    
    Tail of a stream in 'cons' is call-by-name, not call-by-value.
    So the tail is not evaluated when you call the 'cons' method.
*/
/*
--------------------------
Exercise
  
  Consider this modification of streamRange:
*/
    def streamRange2(lo: Int, hi: Int): Stream[Int] = {
      print(lo + " ")
      if (lo >= hi) Stream.empty
      else Stream.cons(lo, streamRange2(lo + 1, hi))
    };System.out.println("""streamRange2: (lo: Int, hi: Int)Stream[Int]""");$skip(39); val res$2 = 
    streamRange2(1, 10).take(3).toList;System.out.println("""res2: List[Int] = """ + $show(res$2))}
}
