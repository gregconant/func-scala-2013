package week1

object session {
  def abs(x: Double) = if (x < 0) -x else x       //> abs: (x: Double)Double

	def sqrt(x: Double) = {

	  def sqrtIter(guess: Double, x: Double): Double =
	    if (isGuessGoodEnough(guess, x)) guess
	    else sqrtIter(improve(guess, x), x)
	
		def isGuessGoodEnough(guess: Double, x: Double): Boolean =
			// if guess squared is close enough to x, return true
			abs(guess * guess - x) < 0.001
	
		def improve(guess: Double, x: Double) =
			(guess + x / guess) / 2
	    
	  sqrtIter(1.0, x)
  
  }                                               //> sqrt: (x: Double)Double
  
  sqrt(2)                                         //> res0: Double = 1.4142156862745097
  sqrt(4)                                         //> res1: Double = 2.0000000929222947
  sqrt(1e-6)                                      //> res2: Double = 0.031260655525445276
 
 
   
}