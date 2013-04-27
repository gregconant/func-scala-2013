package week4

object lists {


/*
-------------------
  Lists vs. Arrays
  
  val fruit = new List("apples", "oranges", "pears")
  
  Lists are immutable, arrays are not.
  Lists are recursive, while arrays are flat.
-------------------
  val fruit = new List("apples", "oranges", "pears")
  val diag3 = List(List(1,0,0), List(0,1,0), List(0,0,1))

-------------------
  Lists and Arrays are both homogeneous
  val fruit = List[String]
  
  All lists are constructed from the empty list Nil
  the construction operation :: (cons)
  x :: xs gives a new list with the first element x
  followed by the element xs.
  
  fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
  empty = Nil
  
-------------------
Right Associativity
  Convention: all operators ending in ":" associate to the right.
  A :: B :: C is the same as A :: (B :: C)
  so you can omit the parens.
  
  val nums = 1 :: 2 :: 3 :: 4 :: Nil
  
  An operator ending in a colon is seen as method calls of the
  RIGHT HAND operand.
  so the expression above is equivalent to:
  
    Nil.::(4).::(3).::(2).::(1)

    so :: is the same as the prepend operation we defined last week.

-------------------
List operations

  head      first element
  tail      all elements except first
  isEmpty   true if list is empty, false otherwise.

  fruit.head == "apples"
  
  empty.head == throw new NoSuchElementException

-------------------
List Patterns

  Nil               Nil constant
  p :: ps           pattern matching a list with
                    a head matching p and a tail matching ps.
  List(p1,...,pn)   same as p1 :: ... :: pn :: Nil
  
  Example
  
    1 :: 2 :: xs      Lists that start with 1 and then 2
    x :: Nil          Lists of length 1
    List(x)           Same as x :: Nil
    List()            Empty list, same as Nil
    List(2 :: xs)     List that contains as its only element
                      another element that starts with 2.

-------------------
Consider the pattern x :: y :: List(xs, ys) :: zs

What is the condition that describes most accurately
the length L of the list it matches?

  L == 3
  L == 4
  L == 5
  L >= 3
  L >= 4
  L >= 5
  
  Answer: L >= 3
  It contains at least 3 elements, with zs as the tail. This may be empty.

-------------------
Sorting Lists
  one way to sort the List(7,3,9,2) is to sort the tail
  List(3, 9, 2) to obtain List(2,3,9)
  
  The next step is to insert the head 7 in the right place to obtain
  List(2,3,7,9)
  
  This idea describes Insertion Sort:
  
    def isort(xs: List[Int]): List[Int] = xs match {
      case List() => List()
      case y :: ys => insert(y, isort(ys))
    }
  
-------------------
Exercise
  Complete the insert function mentioned above:
*/
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => 
      if(x <= y) {
	      x :: xs
	    } else {
	      y :: insert(x, ys)
	    }
  }                                               //> insert: (x: Int, xs: List[Int])List[Int]
/*
  worst-case complexity of insertion sort relative to length
  of input list N?
  Answer: Proportional to N * N.
  
-------------------
-------------------
-------------------
-------------------
-------------------
-------------------
-------------------
-------------------









*/

}