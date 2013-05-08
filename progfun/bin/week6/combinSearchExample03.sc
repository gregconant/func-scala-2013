package week6

object combinSearchExample03 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
/*
--------------------------
Sets as another functional type

  sets are very close to sequences
*/
  val fruit = Set("Apple", "Banana", "pair")      //> fruit  : scala.collection.immutable.Set[String] = Set(Apple, Banana, pair)
  val s = (1 to 6).toSet                          //> s  : scala.collection.immutable.Set[Int] = Set(5, 1, 6, 2, 3, 4)
/*
  
  most seq operations are available on sets
  
*/
  s map (_ + 2)   // Set(3,4,5,6,7,8)             //> res0: scala.collection.immutable.Set[Int] = Set(5, 6, 7, 3, 8, 4)
  // val filtered = fruit filter (_.startsWith == "App") // Set("Apple")
  
  s.nonEmpty                                      //> res1: Boolean = true
/*
--------------------------
  1. sets are unordered
  2. sets don't have duplicate elements
      s map (_ / 2) // Set(2,0,3,1)
  3. fundamental operation on sets is 'contains':
*/
    s contains 5                                  //> res2: Boolean = true
/*
--------------------------
Example: N-Queens
  place 8 queens on a chessboard so no queen is threatened
  by another.
  one way is to place a queen on each row.
  once we have placed k - 1 queens, one must place the kth queen
  where it's not 'in check' with any other queen.

--------------------------
  Can solve with a recursive algorithm:
  board = size n
  # queens: k -1
    - each solution is represented by a list of length (k - 1)
      containing the # of cols (between 0 and n - 1)
    - column # of the queen in the k-1th row comes first,
      then the column of the queen in row k-2, etc.
      (most recent are first)
    - solution is thus a list of sets, with one element for
      each solution.
    - now to place the kth queen, generate all possible extensions
      of each solution preceded by a new queen
*/
  def queens(n: Int): Set[List[Int]] = {
    
    def placeQueens(k: Int): Set[List[Int]] = {
      if(k == 0) Set(List())
      else
        // solve k - 1 queens
        for {
          queens <- placeQueens(k - 1)
          // try all possible columns
          col <- 0 until n
          // check that it doesn't threaten another queen
          if isSafe(col, queens)
        } yield col :: queens
    }
 
    placeQueens(n)
  }                                               //> queens: (n: Int)Set[List[Int]]
  def isSafe(col: Int, queens: List[Int]): Boolean = {
    val row = queens.length
    // transform list of queens into list of pairs
    // of the queens and their rows
    val queensWithRows = (row - 1 to 0 by -1) zip queens
    // now check if queen at (row, row) and (col, col)
    // is in check with other queens
      
    queensWithRows forall {
      case (r, c) => col != c && math.abs(col - c) != row - r
    }

  }                                               //> isSafe: (col: Int, queens: List[Int])Boolean
  
  queens(4)                                       //> res3: Set[List[Int]] = Set(List(1, 3, 0, 2), List(2, 0, 3, 1))
  
  // display solutions as actual chessboard
  
  def show(queens: List[Int]) = {
    val lines =
      for (col <- queens.reverse) // reverse b/c later queens come first
      
      // fill row with n elements of "* "
      // then update the element at position 'col' with an X.
      yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
      "\n" + (lines mkString "\n")
  }                                               //> show: (queens: List[Int])String

  queens(4) map show                              //> res4: scala.collection.immutable.Set[String] = Set("
                                                  //| * * X * 
                                                  //| X * * * 
                                                  //| * * * X 
                                                  //| * X * * ", "
                                                  //| * X * * 
                                                  //| * * * X 
                                                  //| X * * * 
                                                  //| * * X * ")
  (queens(8) take 3 map show) mkString "\n"       //> res5: String = "
                                                  //| * * * * * X * * 
                                                  //| * * * X * * * * 
                                                  //| * X * * * * * * 
                                                  //| * * * * * * * X 
                                                  //| * * * * X * * * 
                                                  //| * * * * * * X * 
                                                  //| X * * * * * * * 
                                                  //| * * X * * * * * 
                                                  //| 
                                                  //| * * * * X * * * 
                                                  //| * * * * * * X * 
                                                  //| * X * * * * * * 
                                                  //| * * * X * * * * 
                                                  //| * * * * * * * X 
                                                  //| X * * * * * * * 
                                                  //| * * X * * * * * 
                                                  //| * * * * * X * * 
                                                  //| 
                                                  //| * * * * * X * * 
                                                  //| * * X * * * * * 
                                                  //| * * * * * * X * 
                                                  //| * * * X * * * * 
                                                  //| X * * * * * * * 
                                                  //| * * * * * * * X 
                                                  //| * X * * * * * * 
                                                  //| * * * * X * * * "
                                                  
/*
*/
/*
--------------------------
--------------------------
--------------------------
--------------------------
*/
}