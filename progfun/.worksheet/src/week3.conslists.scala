package week3

// generics
trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
//  class Cons(val head: T, val tail: List[T]) extends List[T]
//  class Nil[T] extends List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
  // don't need to implement Head and Tail because these
  // are parameters to the class and since they're named the same,
  // they count as implementation.
  
  // val: evaluated once, when initialized
  // def: evaluated whenever called
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head = throw new NoSuchElementException("Nil.head")
  def tail = throw new NoSuchElementException("Nil.tail")
}

/*
  functions can have type parameters

  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
  
  can then write:
    singleton[Int](1)
    singleton[Boolean](true)
    
  but compiler can usually determine types.
  singleton(1) <-- infers type Int
  singleton(true) <-- infers type Boolean

*/


object conslists {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(1365); 
	def nth(n: Int, myList: week3.List[Int]): Int = {
	  def findElementInList(n: Int, innerList: week3.List[Int], count: Int): Int =
	    if(n < 0 || innerList.isEmpty) throw new IndexOutOfBoundsException
	    else if (n == count) innerList.head
	    else findElementInList(n, innerList.tail, count + 1)
	
	  findElementInList(n, myList, 0)
	};System.out.println("""nth: (n: Int, myList: week3.List[Int])Int""")}
	
	
}
