package tables

import scala.slick.driver.PostgresDriver.simple._
import play.api.Play.current

import models.ContributionStatus
import models.ContributionStatusMapper.contributionStatusColumnType
import models.Player
import tables.TimeConverters.dateMapper

import java.time.LocalDate

class Players(tag: Tag) extends Table[Player](tag, "t_player") {
  def id = column[Int]("id")
  def status = column[ContributionStatus]("status")
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  def birthdate = column[Option[LocalDate]]("birthdate")
  def draftYear = column[Int]("draft_year")
  def canonicalName = column[String]("canonical_name")

  def * = (id, status, firstName, lastName, birthdate, draftYear, canonicalName) <> (Player.tupled, Player.unapply)
}

object Players {
  val db = play.api.db.slick.DB
  val Players = TableQuery[Players]

	def forId(id: Int) = db.withSession { implicit session =>
		Players.filter(_.id === id).first
	}
}