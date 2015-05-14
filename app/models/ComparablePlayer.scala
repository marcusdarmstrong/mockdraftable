package models

case class ComparablePlayer(
  val player: Player,
  val primaryPosition: Position,
  val percentiles: Percentiles,
  val score: Double
) {
  val sparkline = Sparkline(percentiles)
}