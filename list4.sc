//Pascal Triangle Functional Implementation

def PascalF(row: Int): List[Int] =
  row match {
    case 0 => List(1)
    case n: Int => 1 :: ((PascalF(n - 1) zip PascalF(n - 1).tail) map { case (a, b) => a + b }) ::: List(1)
  }

PascalF(4)
PascalF(5)
PascalF(6)

//Pascal Triangle Imperative Implementation

def fact(f: Int): Int = {
  var result=1
  for(i <-1 to f){
    result=result*i
  }
  result
}

def PascalI(row: Int): Array[Int] = {
  val Arr = new Array[Int](row+1)
  for (i <- 0 to row) {
    Arr(i) = fact(row)/(fact(i)*fact(row-i))
  }
  Arr
}

PascalI(3)
PascalI(4)
PascalI(5)






