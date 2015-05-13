package models

case class Measurement(
	val id: Int,
	val status: ContributionStatus,
	val playerId: Int,
	val measurable: Measurable,
	val measurement: Double,
	val source: Int
)
