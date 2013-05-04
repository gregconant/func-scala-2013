package week5

object reductionOfLists05 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(86); 
  println("Welcome to the Scala worksheet");$skip(4165); 
/*
---------------
Fold/Reduce Combinators
  insert / operator between adjacent elements
  
  combine elements using an operator

  ex:
    sum(List[x1,...,xn))      = 0 + x1 + ... + xn
    prod(List[x1,...,xn))     = 1 * x1 * ... * xn
    
    can do as usual, recursively:
      def sum(xs: List[Int]): Int = xs match {
        case Nil     => 0
        case y :: ys => y + sum(ys)
      }
---------------
ReduceLeft
  generalize the above with reduceLeft:
  
  inserts a given binary operator between adjacent elements of a list:
  
  List(x1,...,xn) reduceLeft op = (...(x1 op x2) op ... ) op xn
  
  So using reduceLeft, we can simplify:
  
  def sum(xs: List[Int])      = (0 :: xs) reduceLeft ((x,y) => x + y)
  def product(xs: List[Int])  = (1 :: xs) reduceLeft ((x,y) => x * y)
---------------
A Shorter Way to Write Functions
  Instead of ((x, y) => x * y)), we can also write:
  
    (_ * _)

  Every _ represents a new parameter, going from left to right.
  
  So sum and product can be defined as:

  def sum(xs: List[Int])      = (0 :: xs) reduceLeft (_ + _)
  def product(xs: List[Int])  = (1 :: xs) reduceLeft (_ * _)
---------------
FoldLeft
  like reduceLeft but takes an accumulator z as an additional parameter,
  which is returned when foldLeft is called on an empty list.

  (List(x1,...,xn) foldLeft z)(op) = (...(z op x1) op ... ) op xn
  
  So sum and product can also be defined as:

  def sum(xs: List[Int])      = (xs foldLeft 0) (_ + _)
  def product(xs: List[Int])  = (xs foldLeft 1) (_ * _)
---------------
  foldLeft and reduceLeft in the List class:
  
  abstract class List[T] {
    def reduceLeft(op: (T, T) => T): T = this match {
      case Nil => throw new Error("Nil.reduceLeft")
      case x :: xs => (xs foldLeft x)(op)
    }
    def foldLeft[U](z: U)(op: (U, T) => U): U = this match {
      case Nil => z
      case x :: xs => (xs foldLeft op(z,x))(op)
    }
    
  }
---------------
foldRight and reduceRight
  build trees that lean to the right
  combines first element of the list with the op applied to the rest of the list
  
  
  List(x1, ..., x{n-1}, xn) reduceRight op = x1 op ( ... (x{n-1} op xn) ... )
  
  def reduceRight(op: (T, T) => T): T = this match {
    case Nil => throw new Error("Nil.reduceRight")
    case x :: Nil => x
    case x :: xs => op(x, xs.reduceRight(op)) // op applied to first element of list and
                                              // recursive call to rest of the list
  }

  def foldRight[U](z: U)(op: (T,U) => U): U = this match {
    case Nil => z
    case x :: xs => op(x, (xs foldRight z)(op))
  }
                    op
                  /   \
                x1    op
	                   /   \
	                  x2   ...
    	                     \
	                         op
    	                   /   \
    	                  xn    z
	                    
  
  
---------------
Diff. between FoldLeft and FoldRight
  for operators that are associative and commutative,
  they are equivalent.
  But sometimes only one is appropriate.
  
  Ex: Concat
    def concat[T](xs: List[T], ys: List[T]): List[T] =
      (xs foldRight ys) (_ :: _)
      
  Here, we can't replace foldRight with foldLeft. Why?
    as foldRight:
                    ::      list of all X
                  /   \     followed by all Y.
                x1     ::
	                   /   \
	                  x2   ::
	                      /  \
	                     xn  ::
    	                    /   \
	                       y1   ::
    	                       /   \
    	                      y2   ::
    	                          /  \
    	                         yn  Nil
   This would not work because the types would not work out.
   The '::' operator is not applicable to arbitrary types (T), only to Lists.

---------------
complete the following definitions of the basic functions map and length on lists,
such that they use foldRight:
 
  def foldRight[U](z: U)(op: (T,U) => U): U = this match {
    case Nil => z
    case x :: xs => op(x, (xs foldRight z)(op))

*/
  def mapFun[T,U](xs: List[T], f: T => U): List[U] =
    (xs foldRight List[U]()) ( (x: T,y: List[U]) => f(x) :: y);System.out.println("""mapFun: [T, U](xs: List[T], f: T => U)List[U]""");$skip(188); 
    // starts foldRight with empty List[U], so add to this each time
    // type U in foldRight resolves to a list of U
  
  val result = mapFun[Int, Int](List(1,2,3,4,5), ((x) => x * 2));System.out.println("""result  : List[Int] = """ + $show(result ));$skip(18); 
  println(result);$skip(151); 
  
 
  def lengthFun[T](xs: List[T]): Int =
    (xs foldRight 0) ( (x, ys) => {
    println("x: " + x)
    println("ys: " + ys)
    1 + ys
    }
    );System.out.println("""lengthFun: [T](xs: List[T])Int""");$skip(169); 
    
  /*
    pass 1:
      x = 12, ys = 0
      ys is the accumulator value.
    pass 2:
      x = 10, ys = 1
 */
  
  val result2 = lengthFun(List(1,2,3,4,5,8,10,12));System.out.println("""result2  : Int = """ + $show(result2 ));$skip(19); 
  println(result2)}
 

/*
---------------
---------------
---------------
---------------
*/
}
