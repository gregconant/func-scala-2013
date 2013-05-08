package week6

object combinatorialSearch02 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(89); 
  println("Welcome to the Scala worksheet");$skip(76); 

  def isPrime(n: Int) : Boolean = !(2 until n).forall(num => n % num != 0);System.out.println("""isPrime: (n: Int)Boolean""");$skip(874); 
/*
--------------------------
Handling nested sequences
  extend usage of higher-order functions on sequences
  
  given a positive int n,
  find all pairs of positive ints i and j such that
  1 <= j < i < n such that i + j is prime.
  
  for example, if n = 7, the sought pairs are:
    i | 2 3 4 4 5 6 6
    j | 1 2 1 3 2 1 5
    -------------------------------
  i+j | 3 5 5 7 7 7 11
  
  in an imperative language, we'd probably use nested for loops.
  
  
--------------------------
Functional way to do this
  - Generate all pairs such that 1 <= j < i < n
  - filter pairs for which i + j is prime.
  
  so, to generate pairs:
    - generate all ints between 1 and n (excluded)
    - for each integer i, generate the list of pairs (i, 1),  ..., (i, i - 1)
    
  using until and map:
    (1 until n) map (i =>
      (1 until i) map (j => (i, j)))
      
*/
 val n = 7;System.out.println("""n  : Int = """ + $show(n ));$skip(70); 
 val xss = (1 until n) map (i =>
      (1 until i) map (j => (i, j)));System.out.println("""xss  : scala.collection.immutable.IndexedSeq[scala.collection.immutable.IndexedSeq[(Int, Int)]] = """ + $show(xss ));$skip(247); val res$0 = 
    // gives you a vector of vectors
    // this step gave a seq of seqs, call it xss.
    // can combine all of these using foldRight with ++
    
//    (xss foldRight Seq[Int]())(_ ++ _)
    
    // or, equivalently, use flatten
    xss.flatten;System.out.println("""res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$0));$skip(259); 
    // gives you one vector
/*
--------------------------
Generate Pairs (2)
  useful law:
    xs flatMap f = (xs map f).flatten
    
  map followed by flatten is same as flatMap
  */
 val xss2 = (1 until n) flatMap (i =>
      (1 until i) map (j => (i, j)));System.out.println("""xss2  : scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(xss2 ));$skip(110); val res$1 = 
  
  /*
    Still need to filter to where sums are prime
*/
  xss2.filter(pair => isPrime(pair._1 + pair._2));System.out.println("""res1: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$1));$skip(1410); val res$2 = 

/*
  simpler way to organize?
*/


/*
--------------------------
  sometimes the level of abstraction required makes the
  problem hard to understand.
  
  solution: for expressions
--------------------------
  case class Person(name: String, age: Int)
  
  To get names of persons over 20 years old:
  
  for ( p <- persons if p.age > 20 ) yield p.name
  
  which is equivalent to
  
  persons filter (p => p.age > 20) map (p => p.name)
  
  this returns a new result list, as opposed to a for loop
  which has side effects.
--------------------------
for expression syntax
  for ( s ) yield e
  
  where s is a sequence of generators and filters, and e is an
  expression whose value is returned by an iteration.
  
  - a generator is of the form p <- e, where p is a pattern
  and e is an expression whose value is a collection
  
  - a filter is of the form 'if f' where f is a boolean expression
  - sequence must start with a generator
  - if several generators, the last vary faster than the first.
    (so successive generators operate on each element of the items of the first)
  - instead of ( s ), braces { s } can be used, and then the sequence
  of generators and filters can be written on multiple lines without
  requiring semicolons.
  
--------------------------
Use of for
  prime example as above:
 */
  for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
  
  } yield (i, j);System.out.println("""res2: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$2));$skip(158); 
/*
  ex 2: scalar product
*/
  def scalarProduct(xs: List[Double], ys: List[Double]): Double =
    (for {
      (x,y) <- (xs zip ys)
    } yield (x * y)).sum;System.out.println("""scalarProduct: (xs: List[Double], ys: List[Double])Double""")}
/*
--------------------------
--------------------------
--------------------------
--------------------------
*/
}
