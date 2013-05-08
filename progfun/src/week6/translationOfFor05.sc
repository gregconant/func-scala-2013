package week6

object translationOfFor05 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
/*
--------------------------
  The syntax of 'for' is closely related to the higher-order functions
  map, flatMap, and filter.
  1. They all can be defined in terms of for:
*/
  def mapFun[T,U](xs: List[T], f: T=> U): List[U] =
    for (x <- xs) yield f(x)                      //> mapFun: [T, U](xs: List[T], f: T => U)List[U]
    
  def flatMap[T,U](xs: List[T], f: T => Iterable[U]): List[U] =
    for (x <- xs; y <- f(x)) yield y              //> flatMap: [T, U](xs: List[T], f: T => Iterable[U])List[U]
  
  def filter[T](xs: List[T], p: T => Boolean): List[T] =
    for (x <- xs if p(x)) yield x                 //> filter: [T](xs: List[T], p: T => Boolean)List[T]
  
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

  def isPrime(n: Int) : Boolean = !(2 until n).forall(num => n % num != 0)
                                                  //> isPrime: (n: Int)Boolean
  val n = 10                                      //> n  : Int = 10
  for {
    i <- 1 until n
    j <- 1 until i
    if isPrime(i + j)
  } yield (i, j)                                  //> res0: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((3,1), (4,
                                                  //| 2), (5,1), (5,3), (5,4), (6,2), (6,3), (6,4), (7,1), (7,2), (7,3), (7,5), (
                                                  //| 8,1), (8,2), (8,4), (8,6), (8,7), (9,1), (9,3), (9,5), (9,6), (9,7))
                  
  // translated, this becomes:
  (1 until n) flatMap(i =>
    (1 until i).withFilter(j => isPrime(i + j))
    .map(j => (i,j)))                             //> res1: scala.collection.immutable.IndexedSeq[(Int, Int)] = Vector((3,1), (4,
                                                  //| 2), (5,1), (5,3), (5,4), (6,2), (6,3), (6,4), (7,1), (7,2), (7,3), (7,5), (
                                                  //| 8,1), (8,2), (8,4), (8,6), (8,7), (9,1), (9,3), (9,5), (9,6), (9,7))

/*
--------------------------
  translate

    for (b <- books; a <- b.authors if a startsWith "Bird")
    yield b.title

  into higher-order functions.
*/
  case class Book(title: String, authors: List[String])

  val books: Set[Book] = Set(
    Book(title = "SICP", authors = List("Ableson, Harald", "Sussman, Gerald J.")),
    Book(title = "Intro to Functional Programming", authors = List("Bird, Richard", "Wadler, Phil")),
    Book(title = "Effective Java", authors = List("Bloch, Joshua")),
    Book(title = "Java Puzzlers", authors = List("Bloch, Joshua"))
  )                                               //> books  : Set[week6.translationOfFor05.Book] = Set(Book(SICP,List(Ableson, H
                                                  //| arald, Sussman, Gerald J.)), Book(Intro to Functional Programming,List(Bird
                                                  //| , Richard, Wadler, Phil)), Book(Effective Java,List(Bloch, Joshua)), Book(J
                                                  //| ava Puzzlers,List(Bloch, Joshua)))

  // step 1
  books.flatMap(b =>
    for (a <- b.authors if a startsWith "Bird") yield b.title)
                                                  //> res2: scala.collection.immutable.Set[String] = Set(Intro to Functional Prog
                                                  //| ramming)
  // step 2
  books.flatMap(b =>
    for (a <- b.authors withFilter(a => a.startsWith("Bird"))) yield b.title)
                                                  //> res3: scala.collection.immutable.Set[String] = Set(Intro to Functional Prog
                                                  //| ramming)
  
  // step 3
  // only 1 generator now, so it translates to a map
  
  books.flatMap(b =>
  b.authors
    withFilter(a => a.startsWith("Bird"))
    map(y => b.title))                            //> res4: scala.collection.immutable.Set[String] = Set(Intro to Functional Prog
                                                  //| ramming)
  
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