package models

case class MeasurementSet(
	val playerId: Int,
	val height: Option[Double],
	val weight: Option[Double],
	val armLength: Option[Double],
	val wingspan: Option[Double],
	val handSize: Option[Double],
	val time40: Option[Double],
	val time20: Option[Double],
	val time10: Option[Double],
	val benchPress: Option[Double],
	val broadJump: Option[Double],
	val verticalJump: Option[Double],
	val cone3: Option[Double],
	val shuttle20: Option[Double],
	val shuttle60: Option[Double],
	val wonderlic: Option[Double]
)