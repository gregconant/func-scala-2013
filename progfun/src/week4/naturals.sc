package week4

// implement these representing non-negative numbers
// don't use standard primitives; use sub-object and sub-class

// Peano numbers

trait List[+T] {
  def isEmpty: Boolean = ???
  def head: T = ???
  def tail: List[T] = ???
  def prepend [U >: T] (elem: U): List[U] = new Cons(elem, this)
}

class Cons[T](override val head: T, override val tail: List[T]) extends List[T] {
  override def isEmpty = false
}

object Nil extends List[Nothing] {
  def isEmpty: Boolean = true                     //> isEmpty: => Boolean
  def head: Nothing = throw new NoSuchElementException("Nil.head")
                                                  //> head: => Nothing
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
                                                  //> tail: => Nothing
  
}

object test {
  val x: List[String] = Nil
  //def f(xs: List[NonEmpty], x: Empty) = xs prepend x
  // this is a List[IntSet] as result type
}

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
  
}

object Zero extends Nat {
  def isZero = true
  def predecessor = throw new Error("0.predecessor")
  def + (that: Nat) = that
  def - (that: Nat) = if(that.isZero) this else throw new Error("negative number")
}

class Succ(n: Nat) extends Nat {
  def isZero = false
  def predecessor = n
  def + (that: Nat) = new Succ(n + that)
  def - (that: Nat) = if(that.isZero) this else n - that.predecessor
  /*
    because N is one smaller than current Nat #,
    and predecessor is 1 smaller than the argument 'that'
    // but if the argument is Zero, return number itself.
  */
}


/*

  Lecture 4.3 - Subtyping and Generics
  
    we may have 2 subtypes of IntSet: Empty, and NonEmpty.
  
    def assertAllPos[S <: IntSet](r: S): S = ...
    
    <: is an upper bound of the type parameter S.
    It means S can be instantiated only to types that conform to IntSet.
    
    S <: T means S is a subtype of T, and
    s >: T means S is a supertype of T, or T is a subtype of S.

    so an input of type EmptySet returns EmptySets and
    an input of type NonEmpty returns NonEmpty.
    
    Can mix bounds as well:
    
    [S >: NonEmpty <: IntSet]
      restricts S on any type on the interval between NonEmpty and IntSet.

  ---------------------------
    NonEmpty <: IntSet
    so, is
    List[NonEmpty] <: List[IntSet] ?
    Yes, because a list of non-empty sets is a special case of a  list
    of arbitrary sets.
    
    types for which this relationship holds are 'covariant' because
    their subtyping relationship varies with the type parameter.
    
    So List is/should be covariant.
 -------------------------------
  an array of T elements in Java is written as T[]
  scala, it would be                           Array[T]
  java arrays are covariant, so one would have
  NonEmpty[] <: IntSet[]    (is subtype of)
  
  But scala arrays are NOT covariant.
  
-------------------------
  Liskov Substitution Principle
  if A <: B, then everything one can do with a value of B, you should
  be able to to with a value of A.
-------------------------
  
-------------------------

-------------------------
*/

  