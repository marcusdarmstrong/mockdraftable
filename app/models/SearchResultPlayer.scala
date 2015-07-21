package models

case class SearchResultPlayer(
  val player: Player,
  val primaryPosition: Position,
  val percentiles: Percentiles,
  val measurement: Measurement
) {
  val sparkline = Sparkline(percentiles)
}