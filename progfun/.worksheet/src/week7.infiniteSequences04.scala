package week7

object infiniteSequences04 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(87); 
  println("Welcome to the Scala worksheet");$skip(201); 
/*
--------------------------
Infinite Streams

    All elements of streams except first are evaluated only when needed.
    
    Infinite streams!
*/
  def from(n: Int): Stream[Int] = n #:: from(n+1);System.out.println("""from: (n: Int)Stream[Int]""");$skip(67); 
/*
  The stream of all natural numbers:
  
*/
  val nats = from(0);System.out.println("""nats  : Stream[Int] = """ + $show(nats ));$skip(66); 
  
  // stream of all multiples of 4
  val m4s = nats map (_ * 4);System.out.println("""m4s  : scala.collection.immutable.Stream[Int] = """ + $show(m4s ));$skip(54); val res$0 = 
  
  // 1st 10 natural numbers
  nats.take(10).toList;System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(31); val res$1 = 
  
  
  (m4s take 1000).toList;System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(459); 
/*
--------------------------
Sieve of Eratosthenes (for prime #s)

  - Start will all ints from 2, the 1st prime #
  - Eliminate all multiples of 2.
  - The 1st element of the resulting list is 3, a prime #.
  - Eliminate all multiples of 3
  - Iterate forever. At each stem, the first number in the list is
    a prime and we eliminate all its multiples.
  
*/
  def sieve(s: Stream[Int]): Stream[Int] =
    s.head #:: sieve(s.tail filter(_ % s.head != 0));System.out.println("""sieve: (s: Stream[Int])Stream[Int]""");$skip(131); 
    
  // take 1st number and follow it with all numbers that are not multiples of that number.
    
  val primes = sieve(from(2));System.out.println("""primes  : Stream[Int] = """ + $show(primes ));$skip(29); val res$2 = 
  
  primes.take(100).toList;System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(424); 
/*
--------------------------
Back to Square Roots
  
  Always used isGoodEnough to tell when to terminate the iteration.
  
  Now we can express the concept of a converging sequence without worrying about
  when to terminate it:
  
*/
  def sqrtStream(x: Double): Stream[Double] = {
    def improve(guess: Double) = (guess + x / guess) / 2
    lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
    guesses
  };System.out.println("""sqrtStream: (x: Double)Stream[Double]""");$skip(226); val res$3 = 
/*
  seems weird because you're declaring a 'map' operation on the value you're in the middle
  of defining.

  not an infinite loop because of the lazy evaluation
--------------------------
*/
  sqrtStream(4).take(10).toList;System.out.println("""res3: List[Double] = """ + $show(res$3));$skip(141); 

/*
Termination:
--------------------------
*/
  def isGoodEnough(guess: Double, x: Double) =
    math.abs((guess * guess - x) / x) < 0.0001;System.out.println("""isGoodEnough: (guess: Double, x: Double)Boolean""");$skip(60); val res$4 = 

  sqrtStream(4).filter(isGoodEnough(_, 4)).take(10).toList;System.out.println("""res4: List[Double] = """ + $show(res$4));$skip(135); 
/*
--------------------------
Exercise:
  
  Consider 2 ways to express the infinite stream of multiples of a number N:
*/
  val N = 3;System.out.println("""N  : Int = """ + $show(N ));$skip(31); 
  val xs = from(1) map (_ * N);System.out.println("""xs  : scala.collection.immutable.Stream[Int] = """ + $show(xs ));$skip(42); 
  
  val ys = from(1) filter (_ % N == 0);System.out.println("""ys  : scala.collection.immutable.Stream[Int] = """ + $show(ys ))}
/*
  which generates its results faster?
  
  Answer: the one with 'map'.
    it takes each element one at a time and multiplies it by 3.
    
    The other one finds all the numbers and then has to filter them down by those divisible by 3.
    The 'Map' one is faster because it doesn't generate numbers it only later filters out.
--------------------------
*/
}
