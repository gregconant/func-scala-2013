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
    else pascal(column-1, row - 1) + pascal(column, row-1)
  }
  /**
   * Exercise 2
   * 
   * Write a recursive function which verifies the balancing of parentheses in a string, which we represent as a List[Char] not a String. 
   * For example, the function should return true for the following strings:

		(if (zero? x) max (/ 1 x))
		I told him (that it�s not (yet) done). (But he wasn�t listening)
	
	The function should return false for the following strings:
		:-)
		())(
		
	The last example shows that it�s not enough to verify that a string contains the same number of opening and closing parentheses.
		
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
    
    def balanceForString(chars: List[Char], openLeftParens: Int, level: Int = 0): Boolean = {
      if(chars.isEmpty) 
        (openLeftParens == 0)
      else {
	      if (chars.head == '(')
	        balanceForString(chars.tail, (openLeftParens + 1), level + 1)
	      else if (chars.head == ')') {
	        if(openLeftParens == 0)
	          false
	        else
	          balanceForString(chars.tail, (openLeftParens - 1), level + 1)
	      }
	      else
	        balanceForString(chars.tail, openLeftParens, level + 1)
      }

    }
    
    balanceForString(chars, 0, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    
      if(money <= 0) 0
      else if (coins.isEmpty) 0
      else {
        makeChangeWith(money, coins.sorted, 0)
	    }
  }
   def makeChangeWith(amount: Int, coins: List[Int], level: Int = 0): Int = {
    if(coins.isEmpty || amount - coins.head < 0) 0
    else if(amount - coins.head == 0) 1
	else makeChangeWith(amount - coins.head, coins) + makeChangeWith(amount, coins.tail)
   }
  /*
  def makeChangeWithDebugging(amount: Int, coins: List[Int], level: Int = 0): Int = {
	val spacing = " " * (level + 1)  
    //println(spacing + "trying to find change for " + amount + " using " + coins.toString + "...")
    if(coins.isEmpty) 0
    else if(amount - coins.head == 0) {
		//println(spacing + "  found change for " + amount + " with " + coins.head)
		1
	}
	
	  else if(amount - coins.head < 0) {
	    //println("  " * level + "  amount: " + amount + "   negative amount -- cannot make change for " + amount + " with " + coins.head)
	    0
	  }
	  else {
	    val changeWithCurrentList = (makeChangeWith(amount - coins.head, coins, level + 1))
	    //println("  " * level + "  amount: " + amount + " with " + coins.toString + ": " + changeWithCurrentList)
	    val changeWithRestOfList = makeChangeWith(amount, coins.tail, level + 1)
	    //println("  " * level + "  amount: " + amount + " with sub-list " + coins.tail.toString + ": " + changeWithRestOfList)
	    //println("  " * level + "  amount: " + amount + " all paths from here: " + (changeWithCurrentList + changeWithRestOfList))
	    changeWithCurrentList + changeWithRestOfList
	  }
    }
  */
}
