package week6

object otherCollections {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(84); 
  println("Welcome to the Scala worksheet");$skip(1356); 
/*
--------------------------
Other sequences

lists are linear: access to 1st element is much faster than
access to the middle or end of the list.

Alternative: Vector (more balanced access patterns than List)
(very shallow trees)
up to 32 elements are stored as a simple array
larger than 32 you get arrays of 32 elements, each of which can have 32 elements.
(2 ^ 10 elements)
3 levels: 2 ^ 15
4 levels: 2 ^ 20
etc.

--------------------------
Retrieving an element at some index in a vector:
  # of accesses are the depth of the vector
  
  depth = log base 32 (N) where N is size of vector.
  
  vector better for bulk operations

--------------------------
Changing between vectors and lists

  val nums = Vector(1,2,3,-88)
  
  same ops (map, fold, tail, etc.)
  
  no cons
  
  vectors have +: (add to left)
  and :+ (add to right)
  
  the : always points to the existing sequence/vector

--------------------------
adding a new element means
creating a new vector for each level of the old vector
since they are immutable.

--------------------------
Seq is a base class of List and Vector.
Seq itself is a subclass of Iterable.
--------------------------
Arrays and Strings
support same ops as Seq and can be implicitly converted
to sequences as needed
but they aren't really sequences. because they come from
Java

*/
  val xs = Array(1,2,3,44);System.out.println("""xs  : Array[Int] = """ + $show(xs ));$skip(21); val res$0 = 
  xs map(x => x * 2);System.out.println("""res0: Array[Int] = """ + $show(res$0));$skip(25); 

  val s = "Hello World";System.out.println("""s  : String = """ + $show(s ));$skip(28); val res$1 = 
  s filter (c => c.isUpper);System.out.println("""res1: String = """ + $show(res$1));$skip(448); val res$2 = 

/*
--------------------------
Ranges
  sequence of evenly-spaced Ints
  (lower bound, upper bound, step value)
  
  val r: Range = 1 until 5    // 1, 2, 3, 4
  val s: Range = 1 to 5       // 1, 2, 3, 4, 5
  1 to 10 by 3                // 1, 4, 7, 10
  6 to 1 by -2                // 6, 4, 2
  
  
--------------------------
other sequence ops:
  exists
 
  forall
  zip     -- combines matching elements from 2 seqs
 */
 s exists (c => c.isUpper);System.out.println("""res2: Boolean = """ + $show(res$2));$skip(27); val res$3 = 
 s forall (c => c.isUpper);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(33); 
 
 val pairs = List(1,2,3) zip s;System.out.println("""pairs  : List[(Int, Char)] = """ + $show(pairs ));$skip(33); val res$4 = 
 pairs.unzip;System.out.println("""res4: (List[Int], List[Char]) = """ + $show(res$4));$skip(239); val res$5 =   // pair of 2 lists

 // flatmap
 // applies collection-valued function f
 // to all elements of xs and concatenates the result
 // maps each element of collection xs to a collection of its own
 // and then concats the results
 
 s flatMap (c => List('.',c));System.out.println("""res5: String = """ + $show(res$5));$skip(18); val res$6 = 
 
 // sum
 xs.sum;System.out.println("""res6: Int = """ + $show(res$6));$skip(57); val res$7 = 
 
 // product
 
 // max (an ordering must exist)
 xs.max;System.out.println("""res7: Int = """ + $show(res$7));$skip(236); val res$8 = 
 
 // min

 /*
--------------------------
Example: combinations

List all combinations of nums x and y where
x is drawn from 1..M and y is drawn from 1..N
say M is 10 and N is 5
*/

  (1 to 10) flatMap (x => (1 to 5) map (y => (x,y)));System.out.println("""res8: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$8));$skip(240); 

/*
--------------------------
Example: scalar products
sum of products of corresponding elements of 2 vectors xs and ys
*/

  def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
    (xs zip ys).map(xy => xy._1 * xy._2).sum;System.out.println("""scalarProduct: (xs: Vector[Double], ys: Vector[Double])Double""");$skip(131); 
    // combine into pairs
              // operation on each pair
val prods = scalarProduct(Vector(1,2,3,4,5), Vector(2,4,6,8,10));System.out.println("""prods  : Double = """ + $show(prods ));$skip(178); 
/*
can also write this as pattern-matching function value
*/
  def scalarProduct2(xs: Vector[Double], ys: Vector[Double]): Double =
    (xs zip ys).map{ case (x,y) => x * y}.sum;System.out.println("""scalarProduct2: (xs: Vector[Double], ys: Vector[Double])Double""");$skip(392); 

/*
  generally, the function value
    { case pi => e1 ... case pn => en }
  is equivalent to
    x => x match {case p1 => e1 ... case pn => en }

  so if you have just one parameter to match on, this will work.
--------------------------
  Exercise:
    High-level way to write a test for primality of numbers?
*/

  def isPrime(n: Int) : Boolean = !(2 until n).exists(num => n % num == 0);System.out.println("""isPrime: (n: Int)Boolean""");$skip(76); 
  def isPrime2(n: Int) : Boolean = !(2 until n).forall(num => n % num != 0);System.out.println("""isPrime2: (n: Int)Boolean""");$skip(14); val res$9 = 
  isPrime(10);System.out.println("""res9: Boolean = """ + $show(res$9));$skip(13); val res$10 = 
  isPrime(1);System.out.println("""res10: Boolean = """ + $show(res$10));$skip(13); val res$11 = 
  isPrime(2);System.out.println("""res11: Boolean = """ + $show(res$11));$skip(13); val res$12 = 
  isPrime(3);System.out.println("""res12: Boolean = """ + $show(res$12));$skip(13); val res$13 = 
  isPrime(4);System.out.println("""res13: Boolean = """ + $show(res$13));$skip(13); val res$14 = 
  isPrime(5);System.out.println("""res14: Boolean = """ + $show(res$14));$skip(13); val res$15 = 
  isPrime(6);System.out.println("""res15: Boolean = """ + $show(res$15));$skip(13); val res$16 = 
  isPrime(7);System.out.println("""res16: Boolean = """ + $show(res$16));$skip(13); val res$17 = 
  isPrime(8);System.out.println("""res17: Boolean = """ + $show(res$17));$skip(13); val res$18 = 
  isPrime(9);System.out.println("""res18: Boolean = """ + $show(res$18));$skip(14); val res$19 = 
  isPrime(10);System.out.println("""res19: Boolean = """ + $show(res$19));$skip(15); val res$20 = 
  isPrime(138);System.out.println("""res20: Boolean = """ + $show(res$20))}


}
