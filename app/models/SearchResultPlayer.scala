package models

case class SearchResultPlayer(
  val player: Player,
  val primaryPosition: Position,
  val percentiles: Percentiles,
  val measurement: Option[DisplayableMeasurement]
) {
  val sparkline = Sparkline(percentiles)
}