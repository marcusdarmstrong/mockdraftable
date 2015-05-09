package models

object Positions {
	val ATH = Position(1, "ATH", "Athlete", Group)
	val EDGE = Position(2, "EDGE", "Edge Defender", Group)
	val `34B` = Position(3, "34B", "3-4 Outside Linebacker", Primary)
	val DE = Position(4, "DE", "Defensive End", Primary)

	val tree = Map(
		ATH -> Set(ATH, EDGE, `34B`, DE),
		EDGE -> Set(`34B`, DE)
	)

	val ids = tree.get(ATH).getOrElse(Set()).map(p => p.id -> p).toMap

	def getImpliedPositions(positionId: Int) = tree.get(getPosition(positionId)) getOrElse Set()
	def getPosition(positionId: Int) = ids.get(positionId) getOrElse ATH
}