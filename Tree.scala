import scala.annotation.tailrec

//define ADT
sealed trait Tree[+T]  //by using 'sealed' the user won't be able to inherit 'Tree' outside of the file
case object Leaf extends Tree[Nothing]
case class Node[T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]


object Tree {

  def insert[T <% Ordered[T]](tree: Tree[T], y: T): Node[T] = tree match {
    case Leaf => Node(y, Leaf, Leaf)
    case Node(x, l, r) if y == x => Node(x, l, r)
    case Node(x, l, r) if y > x => Node(x, l, insert(r, y))
    case Node(x, l, r) => Node(x, insert(l, y), r)
  }

  def build[T <% Ordered[T]](lst: List[T]): Tree[T] = {
    lst.foldLeft(Leaf: Tree[T])((a, b) => insert(a, b))
  }

  @tailrec
  def contains[T <% Ordered[T]](tree: Tree[T], y: T): Boolean = tree match {
    case Leaf => false
    case Node(x, l, r) if y == x => true
    case Node(x, l, r) if y < x => contains(l, y)
    case Node(x, l, r) if y > x => contains(r, y)
  }
}

  object main {
    def main(args: Array[String]): Unit = {
      var list = List(2, 1, 6, 4, 5, 7, 10, 8)

      var tree = Tree.build(list)
      println(Tree.build(list))

      println(Tree.insert(tree, 3))
      Tree.insert(tree, 3)
      println(Tree.insert(tree, 9))

      //contains
      println(Tree.contains(tree, 6))
      println(Tree.contains(tree, 9))

    }
  }