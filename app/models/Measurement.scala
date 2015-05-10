package models

case class Measurement(
	val id: Int,
	val status: ContributionStatus,
	val playerId: Int,
	val measurableId: Int,
	val measurement: Double,
	val source: Int
)
