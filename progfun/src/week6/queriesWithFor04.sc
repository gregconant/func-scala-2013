package week6

object queriesWithFor04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
/*
--------------------------
A mini-database
*/

  case class Book(title: String, authors: List[String])

  val books: Set[Book] = Set(
    Book(title = "SICP", authors = List("Ableson, Harald", "Sussman, Gerald J.")),
    Book(title = "Intro to Functional Programming", authors = List("Bird, Richard", "Wadler, Phil")),
    Book(title = "Effective Java", authors = List("Bloch, Joshua")),
    Book(title = "Java Puzzlers", authors = List("Bloch, Joshua"))
  )                                               //> books  : Set[week6.queriesWithFor04.Book] = Set(Book(SICP,List(Ableson, Hara
                                                  //| ld, Sussman, Gerald J.)), Book(Intro to Functional Programming,List(Bird, Ri
                                                  //| chard, Wadler, Phil)), Book(Effective Java,List(Bloch, Joshua)), Book(Java P
                                                  //| uzzlers,List(Bloch, Joshua)))
 /*
  find titles of books whose authors name is "Bird":
 */
  for (b <- books; a <- b.authors if a startsWith "Bird, ")
  yield (b.title)                                 //> res0: scala.collection.immutable.Set[String] = Set(Intro to Functional Progr
                                                  //| amming)
/*
  all books which have "Program" in their title
  for (b <- books if b.title indexOf "Program" >= 0)
  yield b.title
*/
/*
--------------------------
  All authors who have written at least 2 books:
    for {
      b1 <- books
      b2 <- books   // 2 iterators for books
      if b1 != b2
      a1 <- b1.authors
      a2 <- b2.authors
      if a1 == a2
    } yield a1

    this will return that author twice
    because we will have repeats of book lists (differently ordered)
    
    so one way to fix it is to do
      if b1.title < b2.title
    intead so it doesn't get the same books twice
    
    what happens if an author has published three books?
      they will be printed 3 times
      because we have 3 possible pairs of books
      
              B1
             /  \
            B2 - B3
      reomve duplicate authors who are in the results list twice with
      a 'distinct' clause.
		    puts { for {
			      b1 <- books
			      b2 <- books   // 2 iterators for books
			      if b1 != b2
			      a1 <- b1.authors
			      a2 <- b2.authors
			      if a1 == a2
			    } yield a1
		    }.distinct
      */

/*
  If we make the 'database' of books above a Set instead of a List,
  we ensure that order doesn't matter and duplicates are eliminated.
--------------------------
*/
}