package models

case class PositionEligibility(
	val playerId: Int,
	val positionId: Int,
	val status: ContributionStatus
)