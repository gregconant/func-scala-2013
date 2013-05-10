import java.io.File
import scala.io.Source

package week6 {
	object allThePieces07 {
	  println("Welcome to the Scala worksheet")
                                                  //> Welcome to the Scala worksheet

/*
--------------------------
Design a program that converts telephone #s to sentences.
*/
  val mnem = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
    '6' -> "MNO", '7' -> "PRQS", '8' -> "TUV", '9' -> "WXYZ")
                                                  //> mnem  : scala.collection.immutable.Map[Char,String] = Map(8 -> TUV, 4 -> GHI
                                                  //| , 9 -> WXYZ, 5 -> JKL, 6 -> MNO, 2 -> ABC, 7 -> PRQS, 3 -> DEF)

/*
  Assume you are given a dictionary 'words' as a list of words.
  
  Design a method 'translate' such that
    translate(phoneNumber)
  produces all phrases of words that can serve as mnemonics for the
  phone number.
  
  Ex: The phone number "7225247386"
  should have the mnemonic "Scala is fun" as one element of the
  set of solution phrases.
  
  Example taken from Lutz Prechelt: An Empirical Comparison of Seven
  Programming Languages
    Tested with Tcl, Python, Perl, Rexx, Java, C++, C
    
    Code size medians:
    100 LOC for scripting languages
    200-300 LOC for others

--------------------------
*/
  val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")
                                                  //> in  : scala.io.BufferedSource = non-empty iterator
  
  val words = in.getLines.toList filter(w => w forall(chr => chr.isLetter))
                                                  //> words  : List[String] = List()

  /** Invert the mnem map to give a map from chars 'A' ... 'Z' to '2' ... '9' */
  val charCode: Map[Char, Char] =
    for ((digit, str) <- mnem; ltr <- str) yield ltr -> digit
                                                  //> charCode  : Map[Char,Char] = Map(E -> 3, X -> 9, N -> 6, T -> 8, Y -> 9, J 
                                                  //| -> 5, U -> 8, F -> 3, A -> 2, M -> 6, I -> 4, G -> 4, V -> 8, Q -> 7, L -> 
                                                  //| 5, B -> 2, P -> 7, C -> 2, H -> 4, W -> 9, K -> 5, R -> 7, O -> 6, D -> 3, 
                                                  //| Z -> 9, S -> 7)
    // 1st generator goes through all mappings
    // of char to str
    // 2nd generator goes through all letters
    // in whichever the current string is. (ltr taken from str)
    // yield binding that goes from the letter to the digit
  
  /** Maps a word to the digit string it can represent, e.g. "Java" -> "5282" */
  def wordCode(word: String): String =
    //word map charCode
    word.toUpperCase map charCode                 //> wordCode: (word: String)String
    // this works because Maps are functions
  wordCode("JAVA")                                //> res0: String = 5282
  // at fionly works so far on uppercase characters
  wordCode("Java")                                //> res1: String = 5282
  
  /**
  * A Map from digit strings to the words that represent them,
  * e.g. "5282" -> List("Java", "Kata", "Lava", ... )
  *
  *
  */
  // want to group lists of words that have the same code
  val wordsForNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue Seq() //> wordsForNum  : Map[String,Seq[String]] = Map()
    // some words have hyphens.
    // drop all that do not have hyphens.
  wordsForNum("JAVA")                             //> res2: Seq[String] = List()
  
  /** Return all ways to encode a number as a list of words */
  def encode(number: String): Set[List[String]] ={
    if (number.isEmpty) Set(List())
    else
      { for {
        split <- 1 to number.length
        // find out what first word must be
        word <- wordsForNum(number take split)
        // take first split characters for the number
        // apply wordsForNum (get all words that have this number)
        // let word range over that
        rest <- encode(number drop split)
      } yield word :: rest
    } toSet
  }                                               //> encode: (number: String)Set[List[String]]
      // first word followed by the rest
  
  encode("7225247386")                            //> res3: Set[List[String]] = Set()
  
  // present as complete phrases
  def translate(number: String): Set[String] =
    encode(number). map(_ mkString " ")           //> translate: (number: String)Set[String]
    
  translate("7225247386")                         //> res4: Set[String] = Set()
/*
--
------------------------

*/
type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]
  type Occurrences = List[(Char, Int)]

  def wordOccurrences(w: Word): Occurrences =
    w.groupBy(c => c.toLower)
     .map(m => (m._1, m._2.length)).toList        //> wordOccurrences: (w: week6.allThePieces07.Word)week6.allThePieces07.Occurre
                                                  //| nces
//    w.groupBy(char => w.count(char))
  
    
//    words.map(word => wordOccurrences(word), word)
  
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
}