package week6

object translationOfFor05 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(86); 
  println("Welcome to the Scala worksheet");$skip(259); 
/*
--------------------------
  The syntax of 'for' is closely related to the higher-order functions
  map, flatMap, and filter.
  1. They all can be defined in terms of for:
*/
  def mapFun[T,U](xs: List[T], f: T=> U): List[U] =
    for (x <- xs) yield f(x);System.out.println("""mapFun: [T, U](xs: List[T], f: T => U)List[U]""");$skip(106); 
    
  def flatMap[T,U](xs: List[T], f: T => Iterable[U]): List[U] =
    for (x <- xs; y <- f(x)) yield y;System.out.println("""flatMap: [T, U](xs: List[T], f: T => Iterable[U])List[U]""");$skip(94); 
  
  def filter[T](xs: List[T], p: T => Boolean): List[T] =
    for (x <- xs if p(x)) yield x;System.out.println("""filter: [T](xs: List[T], p: T => Boolean)List[T]""");$skip(1095); 
  
/*
--------------------------
  In reality, the Scala compiler expresses for-expressions in terms
  of map, flatMap, and filter.
  
    for (x <- e1) yield e2
      // read as "for x taken from e1"
      
  is translated to
    e1.map(x => e2)
*/
/*
--------------------------
  2. A for-expression
    
    for (x <- e1 if f; s) yield e2
    
      where f is a filter and s is a (potentially empty) seq
      of generators and filters, is translated to:
    
    for (x <- e1.withFilter(x => f); s) yield e2
    
    withFilter is kind of a variant of filter that does not produce
    an intermediate list, but instead filters the following map or
    flatMap function application.
*/
/*
--------------------------
  3. A for-expression
        (using multiple generators)
        
    for (x <- e1; y <- e2; s) yield e2
    
      is translated into
    
    e1.flatMap(x => for (y <- e2; s) yield e3)
      
      and continues with translation.
    
  so eventually it simplifies down to a map as in case 1
*/

  def isPrime(n: Int) : Boolean = !(2 until n).forall(num => n % num != 0);System.out.println("""isPrime: (n: Int)Boolean""");$skip(13); 
  val n = 10;System.out.println("""n  : Int = """ + $show(n ));$skip(85); val res$0 = 
  for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
  } yield (i, j);System.out.println("""res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$0));$skip(147); val res$1 = 
                  
  // translated, this becomes:
  (1 until n) flatMap(i =>
    (1 until i).withFilter(j => isPrime(i + j))
    .map(j => (i,j)))

/*
--------------------------
  translate

    for (b <- books; a <- b.authors if a startsWith "Bird")
    yield b.title

  into higher-order functions.
*/
  case class Book(title: String, authors: List[String]);System.out.println("""res1: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$1));$skip(569); 

  val books: Set[Book] = Set(
    Book(title = "SICP", authors = List("Ableson, Harald", "Sussman, Gerald J.")),
    Book(title = "Intro to Functional Programming", authors = List("Bird, Richard", "Wadler, Phil")),
    Book(title = "Effective Java", authors = List("Bloch, Joshua")),
    Book(title = "Java Puzzlers", authors = List("Bloch, Joshua"))
  );System.out.println("""books  : Set[week6.translationOfFor05.Book] = """ + $show(books ));$skip(97); val res$2 = 

  // step 1
  books.flatMap(b =>
    for (a <- b.authors if a startsWith "Bird") yield b.title);System.out.println("""res2: scala.collection.immutable.Set[String] = """ + $show(res$2));$skip(111); val res$3 = 
  // step 2
  books.flatMap(b =>
    for (a <- b.authors withFilter(a => a.startsWith("Bird"))) yield b.title);System.out.println("""res3: scala.collection.immutable.Set[String] = """ + $show(res$3));$skip(170); val res$4 = 
  
  // step 3
  // only 1 generator now, so it translates to a map
  
  books.flatMap(b =>
  b.authors
    withFilter(a => a.startsWith("Bird"))
    map(y => b.title));System.out.println("""res4: scala.collection.immutable.Set[String] = """ + $show(res$4))}
  
/*
--------------------------
  to use the for syntax, you just need to define map, flatMap, and withFilter
  for whatver types you want to use it on.
  i.e. arrays, iterators, databases, XML data, optional values, parsers, etc.
 
  as long as the client interface to the database defines those methods, we can use
  the for syntax for querying the database.
    as used in ScalaQuery and Slick.
  And kind of like LINQ in .NET.
*/
/*
--------------------------
*/
/*
--------------------------
*/
}
