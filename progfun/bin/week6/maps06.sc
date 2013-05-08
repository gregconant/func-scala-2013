package week6

object maps06 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
/*
--------------------------
  Maps are both Iterables and Functions
  
  A Map of type Map[Key, Value] associates keys of type Key
  with values of type Value
*/
  // type Map[String, Int]
  val romanNumerals = Map("I" -> 1, "V" -> 5, "X" -> 10)
                                                  //> romanNumerals  : scala.collection.immutable.Map[String,Int] = Map(I -> 1, V 
                                                  //| -> 5, X -> 10)
  // type Map[String, String]
  val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")
                                                  //> capitalOfCountry  : scala.collection.immutable.Map[String,String] = Map(US -
                                                  //| > Washington, Switzerland -> Bern)
/*
--------------------------
Maps are Functions
  Map also extends the function type Key => Value, so maps
  can be used everywhere functions can.

  Maps can be applied to key arguments:
*/
  capitalOfCountry("US")                          //> res0: String = Washington
  // result: Washington
/*
--------------------------
  Applying map to non-existing key gives you an error:
  capitalOfCountry("Andorra")
  NoSuchElementException
  
  we can call a "get" method on the map
*/
  capitalOfCountry get "andorra"                  //> res1: Option[String] = None
  capitalOfCountry get "US"                       //> res2: Option[String] = Some(Washington)

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
  }                                               //> showCapital: (country: String)String

  showCapital("US")                               //> res3: String = Washington
  showCapital("Andorra")                          //> res4: String = missing data

/*
  Maps also support map, flatMap, and filter, and other things
--------------------------
Sorted and GroupBy
*/
  val fruit = List("apple", "pear", "orange", "pineapple")
                                                  //> fruit  : List[String] = List(apple, pear, orange, pineapple)
  fruit sortWith (_.length < _.length)            //> res5: List[String] = List(pear, apple, orange, pineapple)
  fruit.sorted                                    //> res6: List[String] = List(apple, orange, pear, pineapple)
/*
--------------------------
  GroupBy
    partitions a collection into a map of collections
    according to a 'discriminator function' f
*/

  fruit groupBy(_.head)                           //> res7: scala.collection.immutable.Map[Char,List[String]] = Map(p -> List(pea
                                                  //| r, pineapple), a -> List(apple), o -> List(orange))
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
  }
    
  val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
                                                  //> p1  : week6.maps06.Poly = 6.2x^5 + 4.0x^3 + 2.0x^1
  val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))      //> p2  : week6.maps06.Poly = 7.0x^3 + 3.0x^0
  p1 + p2                                         //> res8: week6.maps06.Poly = 12.4x^5 + 11.0x^3 + 4.0x^1 + 3.0x^0
/*
--------------------------
  this is a lot of computation. is there a simpler way?

Default Values
  previously, maps we saw were partial functions (could lead to exception)
  What if they were total functions?
  use withDefaultValue on a Map

*/
  val cap1 = capitalOfCountry withDefaultValue "<unknown>"
                                                  //> cap1  : scala.collection.immutable.Map[String,String] = Map(US -> Washingto
                                                  //| n, Switzerland -> Bern)
  cap1("Andorra")                                 //> res9: String = <unknown>
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
  }
  val p3 = new Poly2(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
                                                  //> p3  : week6.maps06.Poly2 = 6.2x^5 + 4.0x^3 + 2.0x^1
  val p4 = new Poly2(Map(0 -> 3.0, 3 -> 7.0))     //> p4  : week6.maps06.Poly2 = 7.0x^3 + 3.0x^0
  p3 + p4                                         //> res10: week6.maps06.Poly2 = 12.4x^5 + 11.0x^3 + 4.0x^1 + 3.0x^0
  p3.terms(7)                                     //> res11: Double = 0.0
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
  }
  val p5 = new Poly3(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)//> p5  : week6.maps06.Poly3 = 6.2x^5 + 4.0x^3 + 2.0x^1
  val p6 = new Poly3(0 -> 3.0, 3 -> 7.0)          //> p6  : week6.maps06.Poly3 = 7.0x^3 + 3.0x^0
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
  }
  val p7 = new Poly3(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)//> p7  : week6.maps06.Poly3 = 6.2x^5 + 4.0x^3 + 2.0x^1
  val p8 = new Poly3(0 -> 3.0, 3 -> 7.0)          //> p8  : week6.maps06.Poly3 = 7.0x^3 + 3.0x^0
  p7+p8                                           //> res12: week6.maps06.Poly3 = 12.4x^5 + 11.0x^3 + 4.0x^1 + 3.0x^0
/*
  This is more efficient because it doesn't have to create the intermediate
  data structure of the other list of terms (other.terms map addTerm),
  it can just add them directly in the addTerm function.
--------------------------
*/
}