package week4

object patternmatching {
/*

  case classes:

*/
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(n1: Expr, n2: Expr) extends Expr
/*
  case classes implicitly define companion objects
  with Apply methods.

  
  object Number {
    def apply(n: Int) = new Number(n)
  }
  
  object Sum {
    def apply(e1: Expr, e2: Expr) = new Sum(e1, e2)
  }

  so you can write Number(1) instead of new Number(1).
  
  These classes are empty. How can we access the members?
  ----------------------
  

  Pattern Matching: use the keyword Match
  a generalization of the switch statement in C-type languages
  
  def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
    }

  ----------------------
  
  Syntax
    match is followed by a series of cases, pat => expr.
    Each case associates with an expression expr with a pattern pat.
    A MatchError exception is thrown if no pattern matches
    the value of the selector.
    
    e match {
      case pat1 => expr1
      ...
      case pat2 => expr2
    }
  
  ----------------------
  
  Patterns
  
    constructed from
      constructors
      variables
      wildcard patterns _,
        can use "_" like "Number(_) meaning "any number"
      constants, e.g. 1, true
    
    This is a valid pattern:
      Sum(Number(1), Var(x))
      Meaning that it matches objects which are Sums
       whose left operand has to be the number "1",
      while the second has to be a node of type var,
      and the name field (x) can be anything.
      x is used on the right hand side of the expression to refer
      to that field.
    
    same variable name can only appear once in a pattern.
    So, Sum(x,x) is not a valid pattern.
  
    variables always begin with a lowercase letter.
    Constants should begin with a capital letter
    (except reserved words null, true, false)
  
  ----------------------
  What do patterns match?

  A constructor pattern C(p1,...pn) matches all values
  of type C (or subtypes) that have been constructed
  with arguments matching the patterns p1,...pn.
  
  A variable pattern x matches any value, and binds the name
  of the variable to this value.
  
  A constant pattern C matches values that are equal to C.
  
  ----------------------
  Example
  
  eval(Sum(Number(1), Number(2))
  ->
    Sum(Number(1), Number(2)) match {
      case Number(n) => n                     // does not match
      case Sum(e1, e2) => eval(e1) + eval(e2) // does match
    }
  ->
    eval(Number(1)) + eval(Number(2))
  ->
    Number(1) match {
      case Number(n) => n
      case Sum(e1, e2) => eval(e1) + eval(e2)
    } + eval(Number(2)
  ->
    1 + eval(Number(2))
  ->
    3
    
  ----------------------
  Pattern Matching and Methods
  
  it's possible to define evaluation function as a
  method of the base trait.
  
  trait Expr {
    def eval: Int = this match {
      case Number(n) => n
      case Sum(e1, e2) => e1.eval + e2.eval
                        // use dot notation because
                        // eval is now a method of the trait
    }
  }
    
  ----------------------
  Write a function show that uses pattern matching to return
  the representation of a given expression as a string.
*/

object exprs {
  def show(e: Expr): String = e match {
    case Number(x) => x.toString
    case Sum(l, r) => show(l) + " + " + show(r)
  }

  show(Sum(Number(1), Number(44)))
}
  
/*
  ----------------------
  Optional exercise:
  
    add case classes Var for variables x and Prod
    for products x * y as discussed previously.
    Change show function so it also deals with products.
    Make sure to get parentheses right.
    
    Example
      Sum(Prod(2, Var("x")), Var("y"))
    should print as
      "2 * x + y".
    But
      Prod(Sum(2, Var("x")), Var("y"))
    should print as
      "(2 * x) + y".
  
  ----------------------

*/
  case class Var(n: String) extends Expr
  case class Prod(n1: Expr, n2: Expr) extends Expr
  
  // need to think about this some more.
}