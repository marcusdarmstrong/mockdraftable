package models

case class DisplayPlayer(
  val player: Player,
  val positions: Set[Position],
  val measurements: List[Measurement],
  val percentiles: Percentiles
)
{
  val spider = SpiderGraph(percentiles)
}