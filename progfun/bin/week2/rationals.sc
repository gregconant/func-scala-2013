package week2

object rationals {
  val x = new Rational(1,3)                       //> x  : week2.Rational = 1/3
  x.numer                                         //> res0: Int = 1
  x.denom                                         //> res1: Int = 3

  val y = new Rational(5,7)                       //> y  : week2.Rational = 5/7
  x.add(y)                                        //> res2: week2.Rational = 22/21
  val z = new Rational(3,2)                       //> z  : week2.Rational = 3/2

  val result = x.sub(y).sub(z)                    //> result  : week2.Rational = -79/42
  y.add(y)                                        //> res3: week2.Rational = 10/7
  x.less(y)                                       //> res4: Boolean = true
  x.max(y)                                        //> res5: week2.Rational = 5/7
  
  //val strange = new Rational(1,0)
  //strange.add(strange)
  val simpler = new Rational(2)                   //> simpler  : week2.Rational = 2/1
}

  class Rational(x: Int, y: Int) {
    require(y != 0, "denominator must be nonzero")
    
    def this(x: Int) = this(x,1)
    
    private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
//    private val g = gcd(x,y)
//    def numer = x / g
//    def denom = y / g
    def numer = x
    def denom = y
    
    // another way to calculate the GCD is when you retrieve the num or denom
    // so it doesn't calculate them needlessly or too often
    def numerAlternate = x / gcd(x, y)
    def denomAlternate = y / gcd(x, y)
    // also, if we make numer and denom vals, then will only be computed once
    
    
    //def less(that: Rational) = numer * that.denom < that.numer * denom
    def less(that: Rational) = this.numer * that.denom < that.numer * this.denom
      
    def max(that: Rational) = if(this.less(that)) that else this
    
    def add(that: Rational) =
      new Rational(
        numer * that.denom + that.numer * denom,
        denom * that.denom)
        
    override def toString = {
      val g = gcd(numer,denom)
      (numer / g) + "/" + (denom / g)
    }
    
    def neg: Rational = new Rational(-numer, denom)
      
    def sub(that: Rational) =
      add(that.neg)
    
  }
    