package models

case class MeasurementSet(
  val playerId: Int,
  val height: Option[Double],
  val weight: Option[Double],
  val wingspan: Option[Double],
  val armLength: Option[Double],
  val handSize: Option[Double],
  val time10: Option[Double],
  val time20: Option[Double],
  val time40: Option[Double],
  val benchPress: Option[Double],
  val verticalJump: Option[Double],
  val broadJump: Option[Double],
  val cone3: Option[Double],
  val shuttle20: Option[Double],
  val shuttle60: Option[Double],
  val wonderlic: Option[Double]
) {
  def forMeasurable(measurable: Measurable): Option[DisplayableMeasurement] = {
    val dataPoint = measurable match {
      case Measurables.height => height
      case Measurables.weight => weight
      case Measurables.wingspan => wingspan
      case Measurables.armLength => armLength
      case Measurables.handSize => handSize
      case Measurables.time10 => time10
      case Measurables.time20 => time20
      case Measurables.time40 => time40
      case Measurables.benchPress => benchPress
      case Measurables.verticalJump => verticalJump
      case Measurables.broadJump => broadJump
      case Measurables.cone3 => cone3
      case Measurables.shuttle20 => shuttle20
      case Measurables.shuttle60 => shuttle60
      case _ => None
    };

    dataPoint.flatMap((m) => Some(DisplayableMeasurement(measurable, m)))
  }
}