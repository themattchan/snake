name := "Snake"

version := "1.0"

libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.11" % "1.0.1"
libraryDependencies += "io.reactivex" %% "rxscala" % "0.26.1"

mainClass in (Compile,run) := Some("snake.SnakeGui")
