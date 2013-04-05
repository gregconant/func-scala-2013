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
   */
  def balance(chars: List[Char]): Boolean = ???

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = ???
}
