package week6

object otherCollections {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
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
  val xs = Array(1,2,3,44)                        //> xs  : Array[Int] = Array(1, 2, 3, 44)
  xs map(x => x * 2)                              //> res0: Array[Int] = Array(2, 4, 6, 88)

  val s = "Hello World"                           //> s  : String = Hello World
  s filter (c => c.isUpper)                       //> res1: String = HW

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
 s exists (c => c.isUpper)                        //> res2: Boolean = true
 s forall (c => c.isUpper)                        //> res3: Boolean = false
 
 val pairs = List(1,2,3) zip s                    //> pairs  : List[(Int, Char)] = List((1,H), (2,e), (3,l))
 pairs.unzip  // pair of 2 lists                  //> res4: (List[Int], List[Char]) = (List(1, 2, 3),List(H, e, l))

 // flatmap
 // applies collection-valued function f
 // to all elements of xs and concatenates the result
 // maps each element of collection xs to a collection of its own
 // and then concats the results
 
 s flatMap (c => List('.',c))                     //> res5: String = .H.e.l.l.o. .W.o.r.l.d
 
 // sum
 xs.sum                                           //> res6: Int = 50
 
 // product
 
 // max (an ordering must exist)
 xs.max                                           //> res7: Int = 44
 
 // min

 /*
--------------------------
Example: combinations

List all combinations of nums x and y where
x is drawn from 1..M and y is drawn from 1..N
say M is 10 and N is 5
*/

  (1 to 10) flatMap (x => (1 to 5) map (y => (x,y)))
                                                  //> res8: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((1,1), (1,
                                                  //| 2), (1,3), (1,4), (1,5), (2,1), (2,2), (2,3), (2,4), (2,5), (3,1), (3,2), (
                                                  //| 3,3), (3,4), (3,5), (4,1), (4,2), (4,3), (4,4), (4,5), (5,1), (5,2), (5,3),
                                                  //|  (5,4), (5,5), (6,1), (6,2), (6,3), (6,4), (6,5), (7,1), (7,2), (7,3), (7,4
                                                  //| ), (7,5), (8,1), (8,2), (8,3), (8,4), (8,5), (9,1), (9,2), (9,3), (9,4), (9
                                                  //| ,5), (10,1), (10,2), (10,3), (10,4), (10,5))

/*
--------------------------
Example: scalar products
sum of products of corresponding elements of 2 vectors xs and ys
*/

  def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
    (xs zip ys).map(xy => xy._1 * xy._2).sum      //> scalarProduct: (xs: Vector[Double], ys: Vector[Double])Double
    // combine into pairs
              // operation on each pair
val prods = scalarProduct(Vector(1,2,3,4,5), Vector(2,4,6,8,10))
                                                  //> prods  : Double = 110.0
/*
can also write this as pattern-matching function value
*/
  def scalarProduct2(xs: Vector[Double], ys: Vector[Double]): Double =
    (xs zip ys).map{ case (x,y) => x * y}.sum     //> scalarProduct2: (xs: Vector[Double], ys: Vector[Double])Double

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

  def isPrime(n: Int) : Boolean = !(2 until n).exists(num => n % num == 0)
                                                  //> isPrime: (n: Int)Boolean
  def isPrime2(n: Int) : Boolean = !(2 until n).forall(num => n % num != 0)
                                                  //> isPrime2: (n: Int)Boolean
  isPrime(10)                                     //> res9: Boolean = false
  isPrime(1)                                      //> res10: Boolean = true
  isPrime(2)                                      //> res11: Boolean = true
  isPrime(3)                                      //> res12: Boolean = true
  isPrime(4)                                      //> res13: Boolean = false
  isPrime(5)                                      //> res14: Boolean = true
  isPrime(6)                                      //> res15: Boolean = false
  isPrime(7)                                      //> res16: Boolean = true
  isPrime(8)                                      //> res17: Boolean = false
  isPrime(9)                                      //> res18: Boolean = false
  isPrime(10)                                     //> res19: Boolean = false
  isPrime(138)                                    //> res20: Boolean = false


}