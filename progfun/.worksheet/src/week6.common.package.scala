package week6

import java.io.File
import scala.io.Source

package object common {

  /** An alias for the `Nothing` type.
   *  Denotes that the type should be filled in.
   */
  type ??? = Nothing

  /** An alias for the `Any` type.
   *  Denotes that the type should be filled in.
   */
  type *** = Any;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(554); 

  
  /**
   * Get a child of a file. For example,
   *
   *   subFile(homeDir, "b", "c")
   *
   * corresponds to ~/b/c
   */
  def subFile(file: File, children: String*) = {
    children.foldLeft(file)((file, child) => new File(file, child))
  };System.out.println("""subFile: (file: java.io.File, children: String*)java.io.File""");$skip(622); 

  /**
   * Get a resource from the `src/main/resources` directory. Eclipse does not copy
   * resources to the output directory, then the class loader cannot find them.
   */
  def resourceAsStreamFromSrc(resourcePath: List[String]): Option[java.io.InputStream] = {
    val classesDir = new File(getClass.getResource(".").toURI)
    val projectDir = classesDir.getParentFile.getParentFile.getParentFile.getParentFile
    val resourceFile = subFile(projectDir, ("src" :: "main" :: "resources" :: resourcePath): _*)
    if (resourceFile.exists)
      Some(new java.io.FileInputStream(resourceFile))
    else
      None
  };System.out.println("""resourceAsStreamFromSrc: (resourcePath: List[String])Option[java.io.InputStream]""")}
}
package object forcomp {
  val dictionaryPath = List("forcomp", "linuxwords.txt")

  def loadDictionary = {
    val wordstream = Option {
      getClass.getClassLoader.getResourceAsStream(dictionaryPath.mkString("/"))
    } orElse {
      common.resourceAsStreamFromSrc(dictionaryPath)
    } getOrElse {
      sys.error("Could not load word list, dictionary file not found")
    }
    try {
      val s = io.Source.fromInputStream(wordstream)
      s.getLines.toList
    } catch {
      case e: Exception =>
        println("Could not load word list: " + e)
        throw e
    } finally {
      wordstream.close()
    }
  }

}

object allThePieces07 {
  println("Welcome to the Scala worksheet")
/*
--------------------------
Design a program that converts telephone #s to sentences.
*/
  val mnem = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
    '6' -> "MNO", '7' -> "PRQS", '8' -> "TUV", '9' -> "WXYZ")

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
  
  val words = in.getLines.toList filter(w => w forall(chr => chr.isLetter))

  /** Invert the mnem map to give a map from chars 'A' ... 'Z' to '2' ... '9' */
  val charCode: Map[Char, Char] =
    for ((digit, str) <- mnem; ltr <- str) yield ltr -> digit
    // 1st generator goes through all mappings
    // of char to str
    // 2nd generator goes through all letters
    // in whichever the current string is. (ltr taken from str)
    // yield binding that goes from the letter to the digit
  
  /** Maps a word to the digit string it can represent, e.g. "Java" -> "5282" */
  def wordCode(word: String): String =
    //word map charCode
    word.toUpperCase map charCode
    // this works because Maps are functions
  wordCode("JAVA")
  // at fionly works so far on uppercase characters
  wordCode("Java")
  
  /**
  * A Map from digit strings to the words that represent them,
  * e.g. "5282" -> List("Java", "Kata", "Lava", ... )
  *
  *
  */
  // want to group lists of words that have the same code
  val wordsForNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue Seq()
    // some words have hyphens.
    // drop all that do not have hyphens.
  wordsForNum("JAVA")
  
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
  }
      // first word followed by the rest
  
  encode("7225247386")
  
  // present as complete phrases
  def translate(number: String): Set[String] =
    encode(number). map(_ mkString " ")
    
  translate("7225247386")
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
     .map(m => (m._1, m._2.length)).toList
//    w.groupBy(char => w.count(char))
  
    
    dictionary.map(word => wordOccurrences(word), word)
  
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
