package week7

object caseStudyWaterPouringProblem05 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(98); 
  println("Welcome to the Scala worksheet");$skip(1143); 
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
val problem = new Pouring(Vector(4, 9));System.out.println("""problem  : week7.Pouring = """ + $show(problem ));$skip(14); val res$0 = 
problem.moves;System.out.println("""res0: scala.collection.immutable.IndexedSeq[Product with Serializable with week7.caseStudyWaterPouringProblem05.problem.Move] = """ + $show(res$0));$skip(34); val res$1 = 


problem.pathSets.take(3).toList;System.out.println("""res1: List[Set[week7.caseStudyWaterPouringProblem05.problem.Path]] = """ + $show(res$1));$skip(178); val res$2 = 

// this takes a long time with (4,9) sizes.
// we walk blindly through all states. this is not very efficient.
// exclude any states we have visited before.
problem.solution(6);System.out.println("""res2: Stream[week7.caseStudyWaterPouringProblem05.problem.Path] = """ + $show(res$2));$skip(45); 
val problem2 = new Pouring(Vector(4, 9, 19));System.out.println("""problem2  : week7.Pouring = """ + $show(problem2 ));$skip(22); val res$3 = 
problem2.solution(17);System.out.println("""res3: Stream[week7.caseStudyWaterPouringProblem05.problem2.Path] = """ + $show(res$3))}
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
/*
--------------------------
*/
}
