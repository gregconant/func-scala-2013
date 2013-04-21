package week3
import week3._    // imports everything in this package

object scratch {
}

object intsets {

val t1 = new NonEmpty(3, new Empty, new Empty)
val t2 = t1 incl 4

}

abstract class IntSet {
  def incl(x: Int): IntSet         // abstract
  def contains(x: Int): Boolean    // abstract
  def union(other: IntSet): IntSet // abstract
}


// creating something as an Object makes it a singleton/static object
//object Empty extends IntSet {
class Empty extends IntSet {
  def contains(x: Int): Boolean = false
//  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  override def toString = "."
  def union(other: IntSet): IntSet = other
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left.contains(x)
    else if (x > elem) right.contains(x)
    else true
  
  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  override def toString = "{" + left + elem + right + "}"
  
  def union(other: IntSet): IntSet = {
    ((left union right) union other) incl elem
  }
}