package models

case class SearchOptions(
  year: Int, 
  pos: String, 
  name: Option[String],
  attr: Option[String], 
  sort: Option[String]
) {

  
  val displayMeasurable: Measurable = attr.flatMap((s) => Measurables.all.filter(_.name == s).headOption) match {
    case Some(m) => m
    case None => Measurables.time40
  }
}