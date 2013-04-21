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

object nth {
  def nth[T](n: Int, xs: List[T]): T =
    if (xs.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) xs.head
    else nth(n-1, xs.tail)                        //> nth: [T](n: Int, xs: week3.List[T])T
    
  val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> list  : week3.Cons[Int] = week3.Cons@70d97ea4
  
  nth(2, list)                                    //> res0: Int = 3
  
  nth(4, list)                                    //> java.lang.IndexOutOfBoundsException
                                                  //| 	at week3.nth$$anonfun$main$1.nth$1(week3.nth.scala:45)
                                                  //| 	at week3.nth$$anonfun$main$1.apply$mcV$sp(week3.nth.scala:53)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week3.nth$.main(week3.nth.scala:43)
                                                  //| 	at week3.nth.main(week3.nth.scala)
  nth(-1, list)
}


object conslists {
	
	
}