
import com.sun.tools.javac.util.List
import java.util

import com.sun.tools.javac.main.Option

sealed trait Operator
case class Val(v: Double) extends Operator
case object Sum extends Operator
case object Diff extends Operator
case object Prod extends Operator
case object Div extends Operator

type RPNExpr = List[Operator]

val stack = new util.Stack[Double]

def evaluate(expr: Operator): Double = expr match {
  case Sum =>
    val right = stack.pop()
    val left = stack.pop()
    stack.push(right + left)
  case Diff =>
    val right = stack.pop()
    val left = stack.pop()
    stack.push(right - left)
  case Prod =>
    val right = stack.pop()
    val left = stack.pop()
    stack.push(right * left)
  case Div =>
    val right = stack.pop()
    val left = stack.pop()
    stack.push(right / left)
  case Val(v) =>
    stack.push(v)
  case _ => throw new IllegalArgumentException("You can not do that!")
}

evaluate(Val(3.0))
evaluate(Val(6.0))
evaluate(Val(2.0))
evaluate(Sum)
println(stack)
evaluate(Sum)
println(stack)
evaluate(Val(2.0))
evaluate(Prod)
println(stack)
evaluate(Val(3.0))
println(stack)
evaluate(Div)
println(stack)
println(stack)
evaluate(Val(100.0))
evaluate(Prod)
println(stack)


