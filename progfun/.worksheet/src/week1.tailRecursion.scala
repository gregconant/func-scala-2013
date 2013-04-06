package week1

object tailRecursion {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(185); 
 
  	def factorial(n: Int): Int = {
			def loop(acc: Int, n: Int): Int =
  			if (n == 0) acc
  			else loop(acc * n, n - 1)
			
			loop(1, n)
  	};System.out.println("""factorial: (n: Int)Int""");$skip(21); val res$0 = 
  
  
  factorial(1);System.out.println("""res0: Int = """ + $show(res$0));$skip(15); val res$1 = 
  factorial(2);System.out.println("""res1: Int = """ + $show(res$1));$skip(15); val res$2 = 
  factorial(3);System.out.println("""res2: Int = """ + $show(res$2));$skip(15); val res$3 = 
  factorial(4);System.out.println("""res3: Int = """ + $show(res$3));$skip(15); val res$4 = 
  factorial(5);System.out.println("""res4: Int = """ + $show(res$4));$skip(15); val res$5 = 
  factorial(6);System.out.println("""res5: Int = """ + $show(res$5));$skip(15); val res$6 = 
  factorial(7);System.out.println("""res6: Int = """ + $show(res$6));$skip(16); val res$7 = 
  factorial(12);System.out.println("""res7: Int = """ + $show(res$7))}
}
