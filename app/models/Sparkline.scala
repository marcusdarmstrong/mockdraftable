package models

case class Sparkline(val percentiles: Percentiles) {
	val percList = Measurables.all.map(percentiles.getPercentile(_)).flatten

  val xmax = 400
  val ymax = 100

  def polygon = {
    "0," + ymax + " " + percList.zipWithIndex.map(_ match {
      case(p, index) => (xmax / percList.length * index) + "," + (ymax * p / 100)
    }).mkString(" ") + " " + xmax + "," + ymax
  }
}