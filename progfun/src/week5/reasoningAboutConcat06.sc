package week5

object reasoningAboutConcat {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

/*
reminder: proof by induction
  to show a property P(n) for all integers n >= b,
    - Show that we have P(b) (the base case),
    - for all integers n >= b show the induction step:
      if one has P(n), then one also has P(n+1)
  
  
  Given:
    def factorial(n: Int): Int =
      if (n == 0) 1
      else n * factorial(n-1)
      
  Show that for all n >= 4
  factorial(n) >= power(2, n)  // 2 to the nth power
    Base case: 4
      factorial(4) = 24 >= 1 = power(2,4).
    Induction step: n+1
    for n >= 4
      factorial(n+1)
        >= (n+1) * factorial(n)     // by 2nd clause in factorial
        > 2 * factorial(n)          // by calculating
        >= 2 * power(2,n)           // by induction hypothesis
        = power(2, n + 1)           // by definition of power
          2 x 2^n = 2^n+1
-----------------
Referential Transparency
  Proofs can freely apply reduction steps as equalities to
  some part of a term.
  
  That works b/c pure functional programs don't have side
  effects; so that a term is equivalent to the term to which it reduces.
  
  This is referential transparency.
  
-----------------
Structural Induction
  Analagous to natural induction:
  
  To prove a property P(xs) for all lists xs,
    - show that P(Nil) holds (base case),
    - for a list xs and some element x, show the induction step:
      - if P(xs) holds, then P(x::xs) also holds.

  constructing lists, starting with empty list and cons-ing
  stuff to the front of that list

-----------------
Example:


-----------------
-----------------
-----------------
-----------------
*/

}