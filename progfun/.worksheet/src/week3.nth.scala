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

object nth {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(1169); 
  def nth[T](n: Int, xs: List[T]): T =
    if (xs.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) xs.head
    else nth(n-1, xs.tail);System.out.println("""nth: [T](n: Int, xs: week3.List[T])T""");$skip(65); 
    
  val list = new Cons(1, new Cons(2, new Cons(3, new Nil)));System.out.println("""list  : week3.Cons[Int] = """ + $show(list ));$skip(18); val res$0 = 
  
  nth(2, list);System.out.println("""res0: Int = """ + $show(res$0));$skip(18); val res$1 = 
  
  nth(4, list);System.out.println("""res1: Int = """ + $show(res$1));$skip(16); val res$2 = 
  nth(-1, list);System.out.println("""res2: Int = """ + $show(res$2))}
}


object conslists {
	
	
}
