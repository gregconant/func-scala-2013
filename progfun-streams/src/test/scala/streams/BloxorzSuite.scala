package streams

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Bloxorz._

@RunWith(classOf[JUnitRunner])
class BloxorzSuite extends FunSuite {

  trait SolutionChecker extends GameDef with Solver with StringParserTerrain {
    /**
     * This method applies a list of moves `ls` to the block at position
     * `startPos`. This can be used to verify if a certain list of moves
     * is a valid solution, i.e. leads to the goal.
     */
    def solve(ls: List[Move]): Block =
      ls.foldLeft(startBlock) { case (block, move) => move match {
        case Left => block.left
        case Right => block.right
        case Up => block.up
        case Down => block.down
      }
    }
  }

  trait miscFunctions extends SolutionChecker {
    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

      def aTest = (false && {println("something"); true})
  }
  
  trait Level1 extends SolutionChecker {
      /* terrain for level 1*/

    val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

    val optsolution = List(Right, Right, Down, Right, Right, Right, Down)
  }

  test("simple terrainFunction test") {
    new miscFunctions {
	  val levelVector = Vector(Vector('S','T'), Vector('o','o'), Vector('o','o')) 
	  val func = terrainFunction(levelVector)
	  val validPos = func(Pos(2,1))
	  assert(validPos, true)
	  val invalidPos = func(Pos(2,8))
	  assert(invalidPos == false)
    }
  }
  
  test("terrain function level 1") {
    new Level1 {
      assert(terrain(Pos(0,0)), "0,0")
      assert(!terrain(Pos(4,11)), "4,11")
    }
  }

  test("findChar level 1") {
    new Level1 {
      assert(startPos == Pos(1,1))
    }
  }
  
  test("neighborsWithHistory for level 1") {
    new Level1 {
      val thisBlock = Block(Pos(1,1), Pos(1,1))
      val thisHistory = List(Left,Up)
      
      val expected = Set(
		  (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
		  (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
		)
      assert(neighborsWithHistory(thisBlock, thisHistory).toSet == expected)
    }
  }

  test("newNeighborsOnly for level 1") {
    new Level1 {
      val neighbs = Set(
	    (Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up)),
	    (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
	  ).toStream

	  val explored = Set(Block(Pos(1,2),Pos(1,3)), Block(Pos(1,1),Pos(1,1)))
      
      val expected = Set(
	      (Block(Pos(2,1),Pos(3,1)), List(Down,Left,Up))
	    ).toStream
	  
      val result = newNeighborsOnly(neighbs, explored)
      //println("result: " + result)
      //println("expected: " + expected)
	  assert(result == expected)
    }
  }

  test("from -- simple case") {
    new Level1 {
      val initial = Set((Block(Pos(1,2),Pos(1,3)), List(Right,Left,Up))).toStream

	  val explored = Set[Block]()
      val r = from(initial, explored).toList
      //println("result: " + r)
      val pFromS = pathsFromStart.toList
    }
  }
  
  test("optimal solution for level 1") {
    new Level1 {
      assert(solve(solution) == Block(goal, goal))
    }
  }

  test("optimal solution moves for level 1") {
    new Level1 {
      println("should be: " + optsolution)
      println("       is: " + solution)
      assert(solution == optsolution)
    }
  }

  test("optimal solution length for level 1") {
    new Level1 {
      assert(solution.length == optsolution.length)
    }
  }
}
