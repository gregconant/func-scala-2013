import java.io.File
import scala.io.Source

package week6 {
	object allThePieces07 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(129); 
	  println("Welcome to the Scala worksheet");$skip(232); 

/*
--------------------------
Design a program that converts telephone #s to sentences.
*/
  val mnem = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
    '6' -> "MNO", '7' -> "PRQS", '8' -> "TUV", '9' -> "WXYZ");System.out.println("""mnem  : scala.collection.immutable.Map[Char,String] = """ + $show(mnem ));$skip(736); 

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
  val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt");System.out.println("""in  : scala.io.BufferedSource = """ + $show(in ));$skip(79); 
  
  val words = in.getLines.toList filter(w => w forall(chr => chr.isLetter));System.out.println("""words  : List[String] = """ + $show(words ));$skip(178); 

  /** Invert the mnem map to give a map from chars 'A' ... 'Z' to '2' ... '9' */
  val charCode: Map[Char, Char] =
    for ((digit, str) <- mnem; ltr <- str) yield ltr -> digit;System.out.println("""charCode  : Map[Char,Char] = """ + $show(charCode ));$skip(420); 
    // 1st generator goes through all mappings
    // of char to str
    // 2nd generator goes through all letters
    // in whichever the current string is. (ltr taken from str)
    // yield binding that goes from the letter to the digit
  
  /** Maps a word to the digit string it can represent, e.g. "Java" -> "5282" */
  def wordCode(word: String): String =
    //word map charCode
    word.toUpperCase map charCode;System.out.println("""wordCode: (word: String)String""");$skip(64); val res$0 = 
    // this works because Maps are functions
  wordCode("JAVA");System.out.println("""res0: String = """ + $show(res$0));$skip(71); val res$1 = 
  // at fionly works so far on uppercase characters
  wordCode("Java");System.out.println("""res1: String = """ + $show(res$1));$skip(293); 
  
  /**
  * A Map from digit strings to the words that represent them,
  * e.g. "5282" -> List("Java", "Kata", "Lava", ... )
  *
  *
  */
  // want to group lists of words that have the same code
  val wordsForNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue Seq();System.out.println("""wordsForNum  : Map[String,Seq[String]] = """ + $show(wordsForNum ));$skip(96); val res$2 = 
    // some words have hyphens.
    // drop all that do not have hyphens.
  wordsForNum("JAVA");System.out.println("""res2: Seq[String] = """ + $show(res$2));$skip(545); 
  
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
  };System.out.println("""encode: (number: String)Set[List[String]]""");$skip(67); val res$3 = 
      // first word followed by the rest
  
  encode("7225247386");System.out.println("""res3: Set[List[String]] = """ + $show(res$3));$skip(123); 
  
  // present as complete phrases
  def translate(number: String): Set[String] =
    encode(number). map(_ mkString " ");System.out.println("""translate: (number: String)Set[String]""");$skip(31); val res$4 = 
    
  translate("7225247386")
/*
--
------------------------

*/
type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]
  type Occurrences = List[(Char, Int)];System.out.println("""res4: Set[String] = """ + $show(res$4));$skip(285); 

  def wordOccurrences(w: Word): Occurrences =
    w.groupBy(c => c.toLower)
     .map(m => (m._1, m._2.length)).toList;System.out.println("""wordOccurrences: (w: week6.allThePieces07.Word)week6.allThePieces07.Occurrences""")}
//    w.groupBy(char => w.count(char))
  
    
//    words.map(word => wordOccurrences(word), word)
  
}
}
