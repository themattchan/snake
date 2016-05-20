package snake

import scala.util.Random
import scala.collection.immutable.Queue

import scala.swing._
import scala.swing.Reactions.Reaction
import scala.swing.event.Event
// TODO: try RXScala
// import rx.lang.scala.Observable
// import rx.lang.scala.Observer
// import rx.lang.scala.Subscription
// import rx.lang.scala.subscriptions.Subscription

object SnakeGui extends SimpleSwingApplication {

  override def startup(args: Array[String]) {
    val game =
      if (args.length == 2) new Game(args(0).toInt, args(1).toInt)
      else new Game(50, 50) // default

    val t = new MainFrame {
      title = "Snake"
      contents = new BorderPanel {
        layout(game) = Center
      }
    }

    if (t.size == new Dimension(0,0)) t.pack()
    t.visible = true
  }
}

case class Point(x: Int, y: Int) {
  def +(that: Vector) = Point(this.x+that.x, this.y+that.y)
}
case class Vector(x: Int, y: Int)

sealed trait Space {
  def isValid() = this match {
    case Empty => true
    case _     => false
  }
}
case class Wall() extends Space
case class Body() extends Space
case object Empty extends Space

sealed trait Direction {
  def toVector(): Vector = this match {
    case North => Vector(0,1)
    case East  => Vector(1,0)
    case South => Vector(0,-1)
    case West  => Vector(-1,0)
  }
}
case object North extends Direction
case object East  extends Direction
case object South extends Direction
case object West  extends Direction

case object GameOverExn extends Exception("Game Over!", null)

class Game(width: Int, height: Int) extends GridPanel(width,height) {
  type Board = Array[Array[Space]]

  val cellSize = 10 // px on each side of cell
  val drawSize = new Dimension(width * cellSize, height * cellSize)

  val board: Board = Array.fill(height)(Array.fill(width)(Empty))

  var snake = Queue[Point](Point(width/2, height/2))
  var sdir = North

  def move() {
    val (_, snake1) = snake.dequeue
    val (head @ Point(hx,hy)) = snake1.head+dirToVector(sdir)
    if (board(hx)(hy).isValid) {
      snake = head +: snake1
    } else throw GameOverExn
  }
}
