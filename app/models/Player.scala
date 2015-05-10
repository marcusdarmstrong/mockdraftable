package models

import java.time.LocalDate

case class Player(
  val id: Int, 
  val oldid: Int,
  val status: ContributionStatus, 
  val firstName: String,
  val lastName: String,
  val birthdate: Option[LocalDate],
  val draftYear: Int,
  val canonicalName: String
)
