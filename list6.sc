import scala.collection.mutable.ListBuffer

//Task1
trait Toy {
  val colour : String
  val price : Int
}

class Car(val colour : String, val price : Int) extends Toy{
  override def toString: String = "Car is " + colour + " and " + price + " zl"
}

class Truck(override val colour : String, override val price : Int) extends Car(colour, price){
  override def toString: String = "Truck is " + colour + " and " + price + " zl"
}

class Doll(val colour : String, val price : Int) extends Toy{
  override def toString: String = "Doll is " + colour + " and " + price + " zl"
}

//Test
/*
val toyCar = new Car("Red", 50)
println(toyCar.toString)

val toyTruck = new Truck("Yellow", 100)
println(toyTruck.toString)

val toyDoll = new Doll("Black", 50)
println(toyDoll.toString)
*/

//Task2

trait Elf[-A] extends Toy {
  val specialization: String
  def makeToy() : Toy = new Toy {
    override val colour = "colour"
    override val price = 0
  }
}

class CarMakerElf extends Elf[Car] {
  val specialization: String = "Car"
  override val colour = "Red"
  override val price = 75
  override def makeToy(): Car = new Car(colour, price)
}

class TruckMakerElf extends Elf[Truck] {
  val specialization: String = "Truck"
  override val colour = "Yellow"
  override val price = 100
  override def makeToy(): Truck = new Truck(colour, price)
}

class DollMakerElf extends Elf[Doll] {
  val specialization: String = "Doll"
  override val colour = "Black"
  override val price = 50
  override def makeToy(): Doll = new Doll(colour, price)
}

/*Test2
val carMakerElf = new CarMakerElf()
carMakerElf.makeToy()
carMakerElf.colour
carMakerElf.price

val TruckMakerElf = new TruckMakerElf()
TruckMakerElf.makeToy()
TruckMakerElf.colour
TruckMakerElf.price

val DollMakerElf = new DollMakerElf()
DollMakerElf.makeToy()
DollMakerElf.colour
DollMakerElf.price
*/

class Workshop[Elf[-A]] {
  var employees = new ListBuffer[Any]
  var toys = new ListBuffer[Toy]

  def employ(name: String, specialization: String) : Unit = specialization match {
    case "Car" => {
      val name = new CarMakerElf
      employees += name
      toys += name.makeToy()
    }
    case "Truck" => {
      val name = new TruckMakerElf
      employees += name
      toys += name.makeToy()
    }
    case "Doll" => {
      val name = new DollMakerElf
      employees += name
      toys += name.makeToy()
    }
    case _ => println("try again!")
  }

  def makeToys(): List[Toy] = {
    val toyList = toys.toList
    toyList
  }
}

val myWorkshop = new Workshop()

myWorkshop.employ("elf1", "Car")
myWorkshop.employ("elf2", "Truck")
myWorkshop.employ("elf3", "Doll")
myWorkshop.employ("elf4", "Car")
myWorkshop.employ("elf5", "sth")


myWorkshop.employees
myWorkshop.makeToys()



