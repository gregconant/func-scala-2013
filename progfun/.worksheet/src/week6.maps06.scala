package week6

object maps06 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(74); 
  println("Welcome to the Scala worksheet");$skip(248); 
/*
--------------------------
  Maps are both Iterables and Functions
  
  A Map of type Map[Key, Value] associates keys of type Key
  with values of type Value
*/
  // type Map[String, Int]
  val romanNumerals = Map("I" -> 1, "V" -> 5, "X" -> 10);System.out.println("""romanNumerals  : scala.collection.immutable.Map[String,Int] = """ + $show(romanNumerals ));$skip(106); 
  // type Map[String, String]
  val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern");System.out.println("""capitalOfCountry  : scala.collection.immutable.Map[String,String] = """ + $show(capitalOfCountry ));$skip(217); val res$0 = 
/*
--------------------------
Maps are Functions
  Map also extends the function type Key => Value, so maps
  can be used everywhere functions can.

  Maps can be applied to key arguments:
*/
  capitalOfCountry("US");System.out.println("""res0: String = """ + $show(res$0));$skip(243); val res$1 = 
  // result: Washington
/*
--------------------------
  Applying map to non-existing key gives you an error:
  capitalOfCountry("Andorra")
  NoSuchElementException
  
  we can call a "get" method on the map
*/
  capitalOfCountry get "andorra";System.out.println("""res1: Option[String] = """ + $show(res$1));$skip(28); val res$2 = 
  capitalOfCountry get "US";System.out.println("""res2: Option[String] = """ + $show(res$2));$skip(730); 

/*
  this gives us either None or Some Option types

--------------------------
  Option Type
    The Option type is defined as:
    
      trait Option[+A]
      case class Some[+A](value: A) extends Option[A]
      object None extends Option[Nothing]

    The expression 'map get key' returns:
      - None if map does not contain that key
      - Some(x) if map associates given key with value x.
    
    So an Option value can be one of two things (None or Some)
*/
/*
--------------------------
Decomposing Option
  Since they are case classes, you can use pattern matching:
*/
  def showCapital(country: String) = capitalOfCountry.get(country) match {
    case None => "missing data"
    case Some(capital) => capital
  };System.out.println("""showCapital: (country: String)String""");$skip(21); val res$3 = 

  showCapital("US");System.out.println("""res3: String = """ + $show(res$3));$skip(25); val res$4 = 
  showCapital("Andorra");System.out.println("""res4: String = """ + $show(res$4));$skip(175); 

/*
  Maps also support map, flatMap, and filter, and other things
--------------------------
Sorted and GroupBy
*/
  val fruit = List("apple", "pear", "orange", "pineapple");System.out.println("""fruit  : List[String] = """ + $show(fruit ));$skip(39); val res$5 = 
  fruit sortWith (_.length < _.length);System.out.println("""res5: List[String] = """ + $show(res$5));$skip(15); val res$6 = 
  fruit.sorted;System.out.println("""res6: List[String] = """ + $show(res$6));$skip(168); val res$7 = 
/*
--------------------------
  GroupBy
    partitions a collection into a map of collections
    according to a 'discriminator function' f
*/

  fruit groupBy(_.head)
  // associates each head character (p, a, o)
  // with a list of those fruits' name
  // Map(p -> List(pear, pineapple),
  //     a -> List(apple),
  //     o -> List(orange))
/*
--------------------------
Map example
  Polynomials
    map from exponents to coefficients:
    
      x^3 - 2x + 5
        represented as:
      Map(0 -> 5, 1 -> -2, 3 -> 1)
  
*/
  class Poly(val terms: Map[Int, Double]) {
    // need to merge coefficients that have the same exponent
    
    // this is not correct because we need the sum of like terms from both polynomials
    //def + (other: Poly) = new Poly(terms ++ other.terms)
    def + (other: Poly) = new Poly(terms ++ other.terms map adjust )
    def adjust(term: (Int, Double)): (Int, Double) = {
      // does key of this term (exponent) exist in left terms
      val (exp, coeff) = term
      terms get exp match {
        case Some(coeff1) => exp -> (coeff + coeff1)
        case None => exp -> coeff
      }
    }
      
    override def toString = {
      (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
    }
  };System.out.println("""res7: scala.collection.immutable.Map[Char,List[String]] = """ + $show(res$7));$skip(1169); 
    
  val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2));System.out.println("""p1  : week6.maps06.Poly = """ + $show(p1 ));$skip(45); 
  val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0));System.out.println("""p2  : week6.maps06.Poly = """ + $show(p2 ));$skip(10); val res$8 = 
  p1 + p2;System.out.println("""res8: week6.maps06.Poly = """ + $show(res$8));$skip(309); 
/*
--------------------------
  this is a lot of computation. is there a simpler way?

Default Values
  previously, maps we saw were partial functions (could lead to exception)
  What if they were total functions?
  use withDefaultValue on a Map

*/
  val cap1 = capitalOfCountry withDefaultValue "<unknown>";System.out.println("""cap1  : scala.collection.immutable.Map[String,String] = """ + $show(cap1 ));$skip(18); val res$9 = 
  cap1("Andorra")
  // to demonstrate:
  class Poly2(terms0: Map[Int, Double]) {
    val terms = terms0 withDefaultValue 0.0
    def + (other: Poly2) = new Poly2(terms ++ other.terms map adjust )
    def adjust(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      exp -> (coeff + terms(exp))
    }
      
    override def toString = {
      (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
    }
  };System.out.println("""res9: String = """ + $show(res$9));$skip(504); 
  val p3 = new Poly2(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2));System.out.println("""p3  : week6.maps06.Poly2 = """ + $show(p3 ));$skip(46); 
  val p4 = new Poly2(Map(0 -> 3.0, 3 -> 7.0));System.out.println("""p4  : week6.maps06.Poly2 = """ + $show(p4 ));$skip(10); val res$10 = 
  p3 + p4;System.out.println("""res10: week6.maps06.Poly2 = """ + $show(res$10));$skip(14); val res$11 = 
  p3.terms(7)
/*
  is there a nicer way we can create Poly objects?
  
    use repeated parameters
--------------------------
*/
  class Poly3(terms0: Map[Int, Double]) {
    def this(bindings: (Int, Double)*) =
      this(bindings.toMap)   // repeated params
    val terms = terms0 withDefaultValue 0.0
    def + (other: Poly3) = new Poly3(terms ++ other.terms map adjust )
    def adjust(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      exp -> (coeff + terms(exp))
    }
      
    override def toString = {
      (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
    }
  };System.out.println("""res11: Double = """ + $show(res$11));$skip(682); 
  val p5 = new Poly3(1 -> 2.0, 3 -> 4.0, 5 -> 6.2);System.out.println("""p5  : week6.maps06.Poly3 = """ + $show(p5 ));$skip(41); 
  val p6 = new Poly3(0 -> 3.0, 3 -> 7.0)
/*
--------------------------
Exercise:
  the concat of terms ++ other.terms map adjust
  can be done with a foldLeft. do it.
*/
  class Poly4(terms0: Map[Int, Double]) {
    def this(bindings: (Int, Double)*) =
      this(bindings.toMap)   // repeated params

    val terms = terms0 withDefaultValue 0.0

    def + (other: Poly4) =
      new Poly4((other.terms foldLeft terms)(addTerm))

    def addTerm(terms: Map[Int, Double], term: (Int, Double)): Map[Int, Double] = {
      val (exp, coeff) = term
      terms + (exp -> (coeff + terms(exp)))
    }

    override def toString = {
      (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
    }
  };System.out.println("""p6  : week6.maps06.Poly3 = """ + $show(p6 ));$skip(743); 
  val p7 = new Poly3(1 -> 2.0, 3 -> 4.0, 5 -> 6.2);System.out.println("""p7  : week6.maps06.Poly3 = """ + $show(p7 ));$skip(41); 
  val p8 = new Poly3(0 -> 3.0, 3 -> 7.0);System.out.println("""p8  : week6.maps06.Poly3 = """ + $show(p8 ));$skip(8); val res$12 = 
  p7+p8;System.out.println("""res12: week6.maps06.Poly3 = """ + $show(res$12))}
/*
  This is more efficient because it doesn't have to create the intermediate
  data structure of the other list of terms (other.terms map addTerm),
  it can just add them directly in the addTerm function.
--------------------------
*/
}
