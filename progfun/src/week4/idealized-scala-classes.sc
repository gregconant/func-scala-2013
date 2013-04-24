package idealized.scala

object true extends Boolean {
  def ifThenElse[T](t: => T, e: => T) = t
}

object false extends Boolean {
  def ifThenElse[T](t: => T, e: => T) = e
}

abstract class Boolean {
  def ifThenElse[T](t: => T, e: => T): T
  // takes 2 expressions
  // if (cond) then t, else e
  // cond.ifThenElse(t, e)
  
  def && (x: => Boolean): Boolean = ifThenElse(x, idealized.scala.false)
    // if boolean itself is true, return x. otherwise, return false

  //def || (x: => Boolean): Boolean = ifThenElse(true, x)
    // if boolean itself is true, return true. otherwise, return x
    // (whatever right-hand-side operation is)

  //def unary_!: Boolean = ifThenElse(false, true)
  
  // def == (x: Boolean): Boolean = ifThenElse(x, x.unary_!)
  
  // def != (x: Boolean): Boolean = ifThenElse(x.unary_!, x)
    // current boolean is false, so result is negation of argument.
  
  //
  
  // assumes false < true
    // if current boolean is true:
    //    go to Then part. LHS true, and can never be < other one. so return false.
    // if current boolean is false:
    //    Less operation yields true, if argument is true, false otherwise.
    // so same as argument itself..
  def < (x: => Boolean): Boolean = ifThenElse(false, x)
}

// if (true) t else e