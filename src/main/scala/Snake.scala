package snake

import scala.util.Random

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

trait Space
case class Wall() extends Space
case class Body() extends Space
case object Empty extends Space

class Game(width: Int, height: Int) extends GridPanel(width,height) {
  type Board = Array[Array[Space]]

  val cellSize = 10 // px on each side of cell
  val drawSize = new Dimension(width * cellSize, height * cellSize)

  val board = Array.fill(height)(Array.fill(width)(Empty)) //Array.ofDim[Space](width,height)


  var snake = new List()
}
