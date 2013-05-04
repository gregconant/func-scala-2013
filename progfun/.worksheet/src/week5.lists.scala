package week5

object lists {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(73); 
  println("Welcome to the Scala worksheet");$skip(1140); 
  
  /*
  4.1 More functions on lists
  
    init -- all items except last one
    xs take n -- first n elements
    xs drop n -- all items except the first n
    
--------------------------
    
    Creating new lists
      xs ++ ys
      xs.reverse
      cs updated (n, x) -- list containing same elements as xs,
        except at index n where it contains x
    
    Finding
      xs indexOf x  -- index of 1st element in x equal to x, or -1 if DNE
      xs contains x -- same as xs indexOf x >= 0
    
    xs.length
    cs.last   -- last element, exception if xs is empty
    
--------------------------
    implementing last:
    
      def last[T](xs: List[T]): T = xs match {
        case List() => throw new Error("last of empty list")
        case List(x) => x
        case y :: ys => last(ys)
      }
    
    last takes # of steps proportional to length of list xs.
--------------------------
  implementing init:
*/
  def init[T](xs: List[T]): List[T] = xs match {
    case List() => throw new Error("last of empty list")
    case List(x) => List()         // length 1
    case y :: ys => y :: init(ys)  // length 2 or more
  };System.out.println("""init: [T](xs: List[T])List[T]""");$skip(221); 

/*
  patterns are matched in sequence
--------------------------
  Concatenation?
  
*/
 
  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case List() => ys
    case z :: zs => z :: concat(zs, ys)
  };System.out.println("""concat: [T](xs: List[T], ys: List[T])List[T]""");$skip(292); 

/*
  complexity of concat?
  need a call of concat for each element of left list
  |xs|             <- corresponds to size of xs
--------------------------
  reverse:
*/
  def reverse[T](xs: List[T]): List[T] = xs match {
    case List() => xs
    case y :: ys => reverse(ys) ++ List(y)
  };System.out.println("""reverse: [T](xs: List[T])List[T]""");$skip(213); 
/*
  complexity of reverse?
    n for concat * n for reversal, so n*n.

--------------------------
  remove nth element of a list xs
  
*/

  def removeAt[T](n: Int, xs: List[T]) = (xs take n) ::: (xs drop n + 1);System.out.println("""removeAt: [T](n: Int, xs: List[T])List[T]""");$skip(68); 
  
  val result = removeAt(1, List('a','b','c','d'));System.out.println("""result  : List[Char] = """ + $show(result ));$skip(16);  // List(a,c,d)
  println("hi");$skip(18); 
  println(result);$skip(258); 
/*
--------------------------
flatten:
*/
  def flatten(xs: List[Any]): List[Any] = xs match {
    case List() => List()
    case y :: List(x) => y :: flatten(List(x))
    case y :: ys => y :: flatten(ys)
    case List(x) :: z  => flatten(List(x)) ::: z
  };System.out.println("""flatten: (xs: List[Any])List[Any]""");$skip(26); val res$0 = 
  
  flatten(List(1,2,3));System.out.println("""res0: List[Any] = """ + $show(res$0));$skip(50); val res$1 = 
  flatten(List(List(1,1), 2, List(3, List(5,8))));System.out.println("""res1: List[Any] = """ + $show(res$1))}
  // >    res0: List[Any] = List(1,1,2,3,5,8)
}
