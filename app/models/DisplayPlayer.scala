package models

case class DisplayPlayer(
  val player: Player,
  val positions: Set[Position],
  val measurements: MeasurementSet,
  val percentiles: Percentiles
)