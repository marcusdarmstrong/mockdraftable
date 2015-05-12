package models

case class SearchOptions(
  year: Int, 
  pos: String, 
  name: Option[String], 
  page: Int
)