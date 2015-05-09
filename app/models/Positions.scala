package models

object Positions {
	val ATH = Position(1, "ATH", "Athlete", Group)

	def getImpliedPositions(positionId: Int) = Set(ATH)
}