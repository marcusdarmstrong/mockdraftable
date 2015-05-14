package models

case class Percentiles(val sample: MeasurementSet, val pop: List[MeasurementSet])
{
  var cache = Map[Measurable,Option[Int]]()

  def getPercentile(m: Measurable): Option[Int] = {
    val cached = cache(m)
    if (cached.isDefined) {
      cached
    } else {
      val result = m match {
        case Measurables.height => getPercentile(sample.height, pop.map{_.height}.flatten, m.unit)
        case Measurables.weight => getPercentile(sample.weight, pop.map{_.weight}.flatten, m.unit)
        case Measurables.wingspan => getPercentile(sample.wingspan, pop.map{_.wingspan}.flatten, m.unit)
        case Measurables.armLength => getPercentile(sample.armLength, pop.map{_.armLength}.flatten, m.unit)
        case Measurables.handSize => getPercentile(sample.handSize, pop.map{_.handSize}.flatten, m.unit)
        case Measurables.time10 => getPercentile(sample.time10, pop.map{_.time10}.flatten, m.unit)
        case Measurables.time20 => getPercentile(sample.time20, pop.map{_.time20}.flatten, m.unit)
        case Measurables.time40 => getPercentile(sample.time40, pop.map{_.time40}.flatten, m.unit)
        case Measurables.benchPress => getPercentile(sample.benchPress, pop.map{_.benchPress}.flatten, m.unit)
        case Measurables.verticalJump => getPercentile(sample.verticalJump, pop.map{_.verticalJump}.flatten, m.unit)
        case Measurables.broadJump => getPercentile(sample.broadJump, pop.map{_.broadJump}.flatten, m.unit)
        case Measurables.cone3 => getPercentile(sample.cone3, pop.map{_.cone3}.flatten, m.unit)
        case Measurables.shuttle20 => getPercentile(sample.shuttle20, pop.map{_.shuttle20}.flatten, m.unit)
        case Measurables.shuttle60 => getPercentile(sample.shuttle60, pop.map{_.shuttle60}.flatten, m.unit)
        case _ => getPercentile(sample.wonderlic, pop.map{_.wonderlic}.flatten, m.unit)
      }
      cache = cache + (m -> result)
      result
    }
  }

  def getPercentile(measurement: Option[Double], pop: List[Double], unit: MeasurableUnit) : Option[Int] = {
    //percentile = (below + .5 * equal) / total, or None if total < 5
    if (pop.size < 5) {
      None
    } else {
      measurement map { m =>
        var equal = 0;
        var below = 0;
        pop.foreach { m2 =>
          if (m == m2) {
            equal += 1;
          } else if (unit == Seconds && m2 > m) {
            below += 1;
          } else if (unit != Seconds && m2 < m) {
            below += 1;
          }
        }
        math.round(100 * (below + 0.5 * equal) / pop.size).toInt
      }
    }
  }
}
