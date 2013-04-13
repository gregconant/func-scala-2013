package week2

object currying {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(372); 

  // 'sum' function - without anonymous functions
  // takes a function that takes an Int and returns an Int
  // and returns a function that takes 2 Ints and returns an Int
  def sumWithoutAnonymous(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    sumF
  };System.out.println("""sumWithoutAnonymous: (f: Int => Int)(Int, Int) => Int""");$skip(276); 

  // 'sum' function - with Anonymous functions
  // 'sum' takes a function that takes an Int and returns an Int
  // and returns a function that takes 2 Ints and returns an Int
  def sum(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum(f)(a + 1, b);System.out.println("""sum: (f: Int => Int)(a: Int, b: Int)Int""");$skip(26); val res$0 = 
  
  sum(x => x + x)(3,4);System.out.println("""res0: Int = """ + $show(res$0));$skip(226); 
  
  // 'product' function that calculates the product of the values of a function
  // for the points on a given interval
  def product(f: Int => Int)(a: Int, b: Int): Int =
    if(a > b) 1
    else f(a) * product(f)(a+1, b);System.out.println("""product: (f: Int => Int)(a: Int, b: Int)Int""");$skip(30); val res$1 = 
  
  product(x => x * x)(3,4);System.out.println("""res1: Int = """ + $show(res$1));$skip(97); 
    
  // write 'factorial' in terms of 'product'
  def fact(n: Int): Int = product(x => x)(1,n);System.out.println("""fact: (n: Int)Int""");$skip(13); val res$2 = 
  
  fact(4);System.out.println("""res2: Int = """ + $show(res$2));$skip(262); 
  
  // more general function that generalizes both 'sum' and 'product'
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zeroValue: Int)(a: Int, b: Int): Int =
    if (a > b) zeroValue
    else combine(f(a), mapReduce(f, combine, zeroValue)(a + 1, b));System.out.println("""mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zeroValue: Int)(a: Int, b: Int)Int""");$skip(149); 
                                                  
  def productMapReduce(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x,y) => x * y, 1)(a,b);System.out.println("""productMapReduce: (f: Int => Int)(a: Int, b: Int)Int""");$skip(39); val res$3 = 
  
  productMapReduce(x => x * x)(3,4);System.out.println("""res3: Int = """ + $show(res$3));$skip(95); 

  def sumMapReduce(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x,y) => x + y, 0)(a,b);System.out.println("""sumMapReduce: (f: Int => Int)(a: Int, b: Int)Int""");$skip(35); val res$4 = 
  
  sumMapReduce(x => x + x)(3,4);System.out.println("""res4: Int = """ + $show(res$4))}
}
