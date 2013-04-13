package week2

object rationals {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(61); 
  val x = new Rational(1,3);System.out.println("""x  : week2.Rational = """ + $show(x ));$skip(10); val res$0 = 
  x.numer;System.out.println("""res0: Int = """ + $show(res$0));$skip(10); val res$1 = 
  x.denom;System.out.println("""res1: Int = """ + $show(res$1));$skip(29); 

  val y = new Rational(5,7);System.out.println("""y  : week2.Rational = """ + $show(y ));$skip(11); val res$2 = 
  x.add(y);System.out.println("""res2: week2.Rational = """ + $show(res$2));$skip(28); 
  val z = new Rational(3,2);System.out.println("""z  : week2.Rational = """ + $show(z ));$skip(32); 

  val result = x.sub(y).sub(z);System.out.println("""result  : week2.Rational = """ + $show(result ));$skip(11); val res$3 = 
  y.add(y);System.out.println("""res3: week2.Rational = """ + $show(res$3));$skip(12); val res$4 = 
  x.less(y);System.out.println("""res4: Boolean = """ + $show(res$4));$skip(11); val res$5 = 
  x.max(y);System.out.println("""res5: week2.Rational = """ + $show(res$5));$skip(96); 
  
  //val strange = new Rational(1,0)
  //strange.add(strange)
  val simpler = new Rational(2);System.out.println("""simpler  : week2.Rational = """ + $show(simpler ))}
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
    