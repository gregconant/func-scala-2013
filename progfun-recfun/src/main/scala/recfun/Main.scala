package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   * 
   *      1
   * 	 1 1
   *    1 2 1
   *   1 3 3 1
   *  1 4 6 4 1
   * 
   */
  
  def pascal(column: Int, row: Int): Int = {
    if(column == 0 || column == row) 1
    else pascal(column-1, row - 1)+ pascal(column, row-1)
  }
  /**
   * Exercise 2
   * 
   * Write a recursive function which verifies the balancing of parentheses in a string, which we represent as a List[Char] not a String. 
   * For example, the function should return true for the following strings:

		(if (zero? x) max (/ 1 x))
		I told him (that it’s not (yet) done). (But he wasn’t listening)
	
	The function should return false for the following strings:
		:-)
		())(
		
	The last example shows that it’s not enough to verify that a string contains the same number of opening and closing parentheses.
		
	Do this exercise by implementing the balance function in Main.scala. Its signature is as follows:
		
		def balance(chars: List[Char]): Boolean
	
	There are three methods on List[Char] that are useful for this exercise:
		
		chars.isEmpty: Boolean returns whether a list is empty
		chars.head: Char returns the first element of the list
		chars.tail: List[Char] returns the list without the first element
	
	Hint: you can define an inner function if you need to pass extra parameters to your function.
		
	Testing: You can use the toList method to convert from a String to a List[Char]: e.g. "(just an) example".toList.
   * 
   */
  def balance(chars: List[Char]): Boolean = {
    
    def balanceForString(chars: List[Char], openParenCount: Int): Boolean = {
      
      println("is " + chars.mkString + " balanced? openParenCount: " + openParenCount)
      if(chars.isEmpty) {
        return (openParenCount == 0)
      }
      
      if (chars.head == '(') {
        println("  chars.head = '('. tail: " + chars.tail.mkString)
        balanceForString(chars.tail, openParenCount + 1)
      }
      else if (chars.head == ')') {
        //println("  chars.head = ')'. tail: " + chars.tail.mkString)
        balanceForString(chars.tail, openParenCount - 1)
      }
    }
    balanceForString(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = ???
}
