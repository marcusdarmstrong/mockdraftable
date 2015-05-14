package models

case class SpiderGraph(percentiles: Percentiles)
{
  case class MeasWithPerc(m: Measurable, p: Int)
  case class Axis(m: Measurable, l: String)
  case class Coord(x: Double, y: Double)

  val mapping = Measurables.all.map(m => {
    percentiles.getPercentile(m) match {
      case Some(p) => Some(MeasWithPerc(m, p))
      case None => None
    }
  }).flatten

  val n = mapping.size;
  val scale = 200;
  val offset = 40;
  val angleIncrement = 360.0 / n;
  var currentAngle = 270.0;

  var points = (0 until n).map( i => {
    val a = currentAngle + i * angleIncrement
    Coord(math.cos(math.toRadians(a)), math.sin(math.toRadians(a)))
  })

  val axes = (mapping zip points).map { a =>
    a match {
      case (MeasWithPerc(meas, perc), point) => 
        Axis(meas, "M" + (math.round(100*(point.x * scale + scale + offset))/100.0) + " "
          + (math.round(100*(point.y * scale + scale + offset))/100.0) + "L " 
          + (scale+offset) + " " + (scale+offset) + " Z") 
    }
  }
  
  def genGrid(percent: Double) = {
    points.map( i => {
      val x = math.round((percent * scale * i.x + scale + offset) * 100) / 100.0
      val y = math.round((percent * scale * i.y + scale + offset) * 100) / 100.0
      x + "," + y
    }).mkString(" ")
  }

  def divider = genGrid(1.0)

  def grid = {
    List(
      genGrid(0.2),
      genGrid(0.4),
      genGrid(0.6),
      genGrid(0.8)
    )
  }

  def polygon = {
    (mapping zip points).map( z => {
      z match {
        case (MeasWithPerc(_, perc), p) => {
          val dperc = perc / 100.0
          val x = math.round((dperc * scale * p.x + scale + offset) * 100) / 100.0
          val y = math.round((dperc * scale * p.y + scale + offset) * 100) / 100.0
          x + "," + y
        }
      }
    }).mkString(" ")
  }
}

