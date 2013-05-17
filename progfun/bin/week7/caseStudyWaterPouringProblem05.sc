package week7

object caseStudyWaterPouringProblem05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
/*
--------------------------
Given a faucet, a sink, and 2 glasses (one of 4 deciliters and one of 9 deciliters)

Need to fit 6 deciliters into the 9-dL glass.

Can either:
  - completely fill a glass from sink
  - empty a glass completely into the sink
  - pour from a glass until it or the glass it pours into is empty
  
  We will generalize this so we can have an arbitrary # of glasses with
  arbitrary sizes, and an arbitrary targe amount.

*/
/*
--------------------------
States and Moves
  Glass: Int
  State: Vector[Int] (one entry per glass)
  
  Moves:
    - Empty(glass)
    - Fill(glass)
    - Pour(from, to)


  Start:
    0/0
      - Moves:
      
        - 4 / 0
          pour 0 -> 1
            0 / 4
      
        - 0 / 9
          pour 1 -> 0
            4 / 5
            empty 1
              4 / 0 (same as if we'd first just filled glass 0)
              

  find ideal moves?

  generate all possible move sequences (paths) until we find one where our state
  shows the right target.
  - start at initial state
  - generate all possible moves to new glasses
  keep going

*/
val problem = new Pouring(Vector(4, 9))           //> problem  : week7.Pouring = week7.Pouring@999c305
problem.moves                                     //> res0: scala.collection.immutable.IndexedSeq[Product with Serializable with 
                                                  //| week7.caseStudyWaterPouringProblem05.problem.Move] = Vector(Empty(0), Empty
                                                  //| (1), Fill(0), Fill(1), Pour(0,1), Pour(1,0))


problem.pathSets.take(3).toList                   //> res1: List[Set[week7.caseStudyWaterPouringProblem05.problem.Path]] = List(S
                                                  //| et(--> Vector(0, 0)), Set(Fill(0)--> Vector(4, 0), Fill(1)--> Vector(0, 9))
                                                  //| , Set(Fill(0) Fill(1)--> Vector(4, 9), Fill(0) Pour(0,1)--> Vector(0, 4), F
                                                  //| ill(1) Fill(0)--> Vector(4, 9), Fill(1) Pour(1,0)--> Vector(4, 5)))

// this takes a long time with (4,9) sizes.
// we walk blindly through all states. this is not very efficient.
// exclude any states we have visited before.
problem.solution(6)                               //> res2: Stream[week7.caseStudyWaterPouringProblem05.problem.Path] = Stream(Fi
                                                  //| ll(1) Pour(1,0) Empty(0) Pour(1,0) Empty(0) Pour(1,0) Fill(1) Pour(1,0)--> 
                                                  //| Vector(4, 6), ?)
val problem2 = new Pouring(Vector(4, 9, 19))      //> problem2  : week7.Pouring = week7.Pouring@361de2b1
problem2.solution(17)                             //> res3: Stream[week7.caseStudyWaterPouringProblem05.problem2.Path] = Stream(F
                                                  //| ill(0) Fill(1) Pour(0,2) Pour(1,2) Fill(0) Pour(0,2)--> Vector(0, 0, 17), ?
                                                  //| )
/*
--------------------------

guidelines:

  - name everything you can.
  - put operations into natural scopes.
  - keep degrees of freedom for future refinements.
*/
/*
--------------------------
*/
/*
--------------------------
*/
/*
--------------------------
*/
/*
--------------------------
*/
/*
--------------------------
*/
/*
--------------------------
*/
/*
--------------------------
*/
}