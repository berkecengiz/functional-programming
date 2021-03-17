import Channel.{Subscribe, Video}
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.collection.mutable.ListBuffer

class User extends Actor
{
  override def receive: Receive =
    {
      case Video(title) => {
                              println(s"New video $title recived by user!")
                               }
      case _ => {
                  println("There is no video to watch!")
                  context.system.terminate
              }
    }
}

object User
{
  case class Video(title : String)
  private var id: Int = 0

  def props(channel : ActorRef) =
  {
    id += 1
    Props(classOf[Consumer], id - 1, channel)
  }

}

class Channel extends Actor {
  private var followers: ListBuffer[ActorRef] = ListBuffer[ActorRef]()

  override def receive: Receive = {
    case Channel.Subscribe(user) => {
      followers += user
      println(s"User $User subscribed channel!")
      sender ! Channel.Subscribe
    }
    case Channel.Revert(user) =>
    {
      println(s"User $User unsubscribed channel!")
      followers -= user
      sender ! Channel.Revert
    }

    case Channel.Video(title) => {
                            sender ! User.Video
                          }
  }
}


object Channel {

  case class Video(title: String)

  case class Subscribe(User: ActorRef)

  case class Revert(User: ActorRef)

  def props: Props = Props[User]
}

object Main
{
  def main(args: Array[String]) : Unit =
    {
      val actSys = ActorSystem()
      val channel  = actSys.actorOf(Channel.props)
      val channelOther = actSys.actorOf(Channel.props)

      val user1 = actSys.actorOf(User.props(channel))
      val user2 = actSys.actorOf(User.props(channel))

      channel ! Subscribe(user1)
      channelOther ! Subscribe(user1)
      channel ! Subscribe(user2)

      channel ! User.Video("Bullshit bullshit bullshit")
      channelOther ! User.Video("A new bullshit")

    }
}

/*
import akka.actor.{Actor, ActorRef, ActorSystem, Props}

 class Channel extends Actor {
   def receive: Receive = {
     case Channel.Video(title) => println(s"Video $title uploaded")
   }
 }
  object Channel {
    case class Video(title: String)
    def props:Props = Props[Channel]
  }

object MainChannel {
  def main(args: Array[String]): Unit = {
    val actSys = ActorSystem()
    val video: ActorRef = actSys.actorOf(Channel.props)

    video ! Channel.Video("Bullshit bullshit bullshit")
    video ! Channel.Video("A new bullshit")
  }

 */

