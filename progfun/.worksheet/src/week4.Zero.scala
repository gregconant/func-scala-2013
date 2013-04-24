package week4

// implement these representing non-negative numbers
// don't use standard primitives; use sub-object and sub-class

// Peano numbers

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
  
}

object Zero extends Nat {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(355); 
  def isZero = true;System.out.println("""isZero: => Boolean""");$skip(53); 
  def predecessor = throw new Error("0.predecessor");System.out.println("""predecessor: => Nothing""");$skip(27); 
  def + (that: Nat) = that;System.out.println("""+ : (that: week4.Nat)week4.Nat""");$skip(83); 
  def - (that: Nat) = if(that.isZero) this else throw new Error("negative number");System.out.println("""- : (that: week4.Nat)week4.Nat""")}
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

  