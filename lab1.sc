import scala.annotation.tailrec

def function(x : Int, n: Int) : Double = {
  def square(x: Double) = x*x
  def calculateOdd(n: Int): Double = (n-1)/2
  def calculateEven(n: Int): Double = n/2

  if(n%2==0) x*scala.math.pow(square(x),calculateOdd(n))
  else scala.math.pow(square(x), calculateEven(n))
}

function(2,3)
function(2,4)


def compare(x: Double, y: Double, precision: Double): Boolean = {
  if ((x - y).abs < precision) true else false
}

compare (1, 2, 0.0001)


def reverse[A](l: List[A]): List[A] = {
  @scala.annotation.tailrec
  def _reverse(res: List[A], rem: List[A]): List[A] = rem match {
    case Nil => res
    case h :: tail => _reverse(h :: res, tail)
  }
  _reverse(Nil, l)
}

reverse(List(1,2,3,5))

def reverse_[A](l: List[A]): List[A] = l match {
  case h :: tail => reverse_(tail) ::: List(h)
  case Nil => Nil
}

reverse_(List(1,2,3,4,5,6,7,8))


def merge[A](a : List[A], b: List[A]): List[A] = {
  if (a.isEmpty) return b
  if (b.isEmpty) return a
  else a.head::b.head::merge(a.tail, b.tail)
}

val x =List(1,2,3)
val y =List(3,4,5)

merge(x,y)