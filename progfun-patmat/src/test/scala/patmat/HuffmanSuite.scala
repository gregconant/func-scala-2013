package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val oneNodeTree = Leaf('a',1)
  }

  test("weight of one-node tree") {
    new TestTrees {
      assert(weight(oneNodeTree) === 1)
    }
  }

  test("chars of one-node tree") {
    new TestTrees {
      assert(chars(oneNodeTree) === List('a'))
    }
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("times for list of one character") {
    new TestTrees {
      val timesList = times(List('a'))
      assert(timesList === List(('a', 1)))
    }
  }

  test("times for three-char list") {
    new TestTrees {
      val timesList = times(List('a', 'b', 'c'))
      assert(timesList.length == 3)
      assert(timesList.contains('a', 1))
      assert(timesList.contains('b', 1))
      assert(timesList.contains('c', 1))
    }
  }

  test("times for two-char list with same character") {
    new TestTrees {
      val timesList = times(List('a', 'a'))
      assert(timesList.length == 1)
      assert(timesList.contains('a', 2))
    }
  }
  
  test("times for three-char list with repeated characters") {
    new TestTrees {
      val timesList = times(List('a', 'b', 'a'))
      assert(timesList.length == 2)
      assert(timesList.contains('a', 2))
      assert(timesList.contains('b', 1))
    }
  }
  
  test("times for list with multiple chars, some of which have repeats") {
    new TestTrees {
	    var chars = List('a','b','c','d','b','b','a','a','a','a','a')
	    val result = times(chars)
	    assert(result.length == 4)
	    assert(result.contains('a', 6))
	    assert(result.contains('b', 3))
	    assert(result.contains('c', 1))
	    assert(result.contains('d', 1))
    }
    
  }
  
  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("singleton for lists of trees") {
    new TestTrees {
      val treeWithTwoLeaves = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
      val nodeTreeList = List(treeWithTwoLeaves, oneNodeTree)
	    assert(singleton(List(oneNodeTree)) == true)
	    assert(singleton(nodeTreeList) == false)
	}
  }

  test("combine of leaf list with 2 nodes") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2), List('e', 't'), 3)))
  }

  
  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    val combined = combine(leaflist)
    assert(combined === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }
  
  test("combine of nested leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    val combined = combine(leaflist)
    val combinedAgain = combine(combined)
    
    val newLeft = Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3)
    val newRight = Leaf('x', 4)
    assert(combinedAgain === List(Fork(newLeft, newRight, List('e', 't', 'x'), 7 )))
  }

  test("test the until function") {
    val leafList = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    val combinedListFunc = until(singleton, combine)_
    val result = combinedListFunc(leafList)
    assert(result === List(
        Fork(Fork(Leaf('e',1),Leaf('t',2), List('e','t'),3),
        Leaf('x', 4), List('e','t','x'), 7)))
  }

  test("test the until function with 4 nodes") {
    val leafList = List(Leaf('d',1), Leaf('c',1), Leaf('b',3), Leaf('a',6))
    val combinedListFunc = until(singleton, combine)_
    val result = combinedListFunc(leafList)
    assert(result === List(
        Fork(
            Fork(
                Fork(Leaf('d',1), Leaf('c', 1), List('d','c'), 2),
                Leaf('b', 3), List('d','c','b'), 5
            ),
            Leaf('a', 6), List('d','c','b','a'), 11
        )))
  }

  test("test the createCodeTree function") {
    var chars = List('a','b','c','d','b','b','a','a','a','a','a')
    val result = createCodeTree(chars)
    // orderedTuples: List(Leaf(d,1), Leaf(c,1), Leaf(b,3), Leaf(a,6))
    println(result)
    assert(result === 
        Fork(
            Fork(
                Fork(Leaf('d',1), Leaf('c', 1), List('d','c'), 2),
                Leaf('b', 3), List('d','c','b'), 5
            ),
            Leaf('a', 6), List('d','c','b','a'), 11
        ))
  }


  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
}
