package week5

object lists {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
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
  }                                               //> init: [T](xs: List[T])List[T]

/*
  patterns are matched in sequence
--------------------------
  Concatenation?
  
*/
 
  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case List() => ys
    case z :: zs => z :: concat(zs, ys)
  }                                               //> concat: [T](xs: List[T], ys: List[T])List[T]

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
  }                                               //> reverse: [T](xs: List[T])List[T]
/*
  complexity of reverse?
    n for concat * n for reversal, so n*n.

--------------------------
  remove nth element of a list xs
  
*/

  def removeAt[T](n: Int, xs: List[T]) = (xs take n) ::: (xs drop n + 1)
                                                  //> removeAt: [T](n: Int, xs: List[T])List[T]
  
  val result = removeAt(1, List('a','b','c','d')) // List(a,c,d)
                                                  //> result  : List[Char] = List(a, c, d)
  println("hi")                                   //> hi
  println(result)                                 //> List(a, c, d)
/*
--------------------------
flatten:
*/
  def flatten(xs: List[Any]): List[Any] = xs match {
    case List() => List()
    case y :: List(x) => y :: flatten(List(x))
    case y :: ys => y :: flatten(ys)
    case List(x) :: z  => flatten(List(x)) ::: z
  }                                               //> flatten: (xs: List[Any])List[Any]
  
  flatten(List(1,2,3))                            //> res0: List[Any] = List(1, 2, 3)
  flatten(List(List(1,1), 2, List(3, List(5,8)))) //> res1: List[Any] = List(List(1, 1), 2, List(3, List(5, 8)))
  // >    res0: List[Any] = List(1,1,2,3,5,8)
}