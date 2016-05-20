package snake

import swing._
import swing.event._

object SnakeGui extends SimpleSwingApplication {

  val gui = new BorderPanel {
    minimumSize = new Dimension(500, 500)

    listenTo(keys)

    contents += new TextField

    reactions += {
      // keyPressed(_,key,_,_) match {
      //   case Up    => ???
      //   case Down  => ???
      //   case Left  => ???
      //   case Right => ???
      // }
    }
  }


  def top = new MainFrame {
    title = "Snake"
    contents = gui
  }
}
