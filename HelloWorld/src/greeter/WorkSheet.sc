package greeter

object WorkSheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val x = 4                                       //> x  : Int = 4
  def increase(i: Int) = i + 1                    //> increase: (i: Int)Int
  increase(x)                                     //> res0: Int = 5
}