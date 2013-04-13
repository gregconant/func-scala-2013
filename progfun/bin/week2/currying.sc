package week2

object currying {

  // 'sum' function - without anonymous functions
  // takes a function that takes an Int and returns an Int
  // and returns a function that takes 2 Ints and returns an Int
  def sumWithoutAnonymous(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    sumF
  }                                               //> sumWithoutAnonymous: (f: Int => Int)(Int, Int) => Int

  // 'sum' function - with Anonymous functions
  // 'sum' takes a function that takes an Int and returns an Int
  // and returns a function that takes 2 Ints and returns an Int
  def sum(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum(f)(a + 1, b)                  //> sum: (f: Int => Int)(a: Int, b: Int)Int
  
  sum(x => x + x)(3,4)                            //> res0: Int = 14
  
  // 'product' function that calculates the product of the values of a function
  // for the points on a given interval
  def product(f: Int => Int)(a: Int, b: Int): Int =
    if(a > b) 1
    else f(a) * product(f)(a+1, b)                //> product: (f: Int => Int)(a: Int, b: Int)Int
  
  product(x => x * x)(3,4)                        //> res1: Int = 144
    
  // write 'factorial' in terms of 'product'
  def fact(n: Int): Int = product(x => x)(1,n)    //> fact: (n: Int)Int
  
  fact(4)                                         //> res2: Int = 24
  
  // more general function that generalizes both 'sum' and 'product'
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zeroValue: Int)(a: Int, b: Int): Int =
    if (a > b) zeroValue
    else combine(f(a), mapReduce(f, combine, zeroValue)(a + 1, b))
                                                  //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zeroValue: Int)(a: I
                                                  //| nt, b: Int)Int
                                                  
  def productMapReduce(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x,y) => x * y, 1)(a,b)
                                                  //> productMapReduce: (f: Int => Int)(a: Int, b: Int)Int
  
  productMapReduce(x => x * x)(3,4)               //> res3: Int = 144

  def sumMapReduce(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x,y) => x + y, 0)(a,b)
                                                  //> sumMapReduce: (f: Int => Int)(a: Int, b: Int)Int
  
  sumMapReduce(x => x + x)(3,4)                   //> res4: Int = 14
}