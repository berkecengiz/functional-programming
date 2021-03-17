class CyclicBufferFullException(message: String = "Queue is Full!") extends Exception(message)

class CyclicBuffer[T] (capacity : Int) {

  private val len = capacity + 1
  private val arr = new Array[Any](len)

  private var head = 0
  private var tail = 0

  def _capacity: Int = this.capacity
  def size: Int = if(head>=tail) head-tail else len-tail+head
  def toList : List[Any] = arr.toList

  def isEmpty: Boolean = if (head == tail) true else false

  def enqueue(item: T): Unit = {
    var next = head+1
    next = if(next>=len) 0 else next
    if(next==tail) throw new CyclicBufferFullException()
    else {
      arr(head) = item
      head = next
    }
  }

  def dequeue(): Unit = {
    if (head == tail) None
    else {
      arr(tail) = null
      val next = tail + 1
      tail = if (next >= len) 0 else next
    }
  }
}

//Test

val buffer = new CyclicBuffer[Int](5)

buffer.toList
buffer.isEmpty

buffer.enqueue(1)
buffer.enqueue(22)
buffer.enqueue(5)
buffer.enqueue(64)
buffer.enqueue(6)
buffer.enqueue(6)

buffer.toList
buffer.size

buffer.dequeue()
buffer.dequeue()
buffer.toList

buffer.enqueue(3)
buffer.enqueue(4)
buffer.toList

buffer.dequeue()
buffer.dequeue()
buffer.toList
buffer.enqueue(24)
buffer.toList

buffer._capacity
buffer.size



