package models

sealed trait MeasurableUnit
case object Inches extends MeasurableUnit
case object Seconds extends MeasurableUnit
case object Reps extends MeasurableUnit
case object Pounds extends MeasurableUnit
case object Score extends MeasurableUnit

case class Measurable(val id: Int, val name: String, val unit: MeasurableUnit)

object Measurables {
	val height = Measurable(1, "Height", Inches)
	val weight = Measurable(2, "Weight", Pounds)
	val wingspan = Measurable(3, "Wingspan", Inches)
	val armLength = Measurable(4, "Arm Length", Inches)
	val handSize = Measurable(5, "Hand Size", Inches)
	val time10 = Measurable(6, "10 Yard Split", Seconds)
	val time20 = Measurable(7, "20 Yard Split", Seconds)
	val time40 = Measurable(8, "40 Yard Dash", Seconds)
	val benchPress = Measurable(9, "Bench Press", Reps)
	val verticalJump = Measurable(10, "Vertical Jump", Inches)
	val broadJump = Measurable(11, "Broad Jump", Inches)
	val cone3 = Measurable(12, "3-Cone Drill", Seconds)
	val shuttle20 = Measurable(13, "20 Yard Shuttle", Seconds)
	val shuttle60 = Measurable(14, "60 Yard Shuttle", Seconds)
	val wonderlic = Measurable(15, "Wonderlic", Score)
}
