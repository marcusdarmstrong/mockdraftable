package tables

import scala.slick.driver.PostgresDriver.simple._
import play.api.Play.current

import models.ContributionStatus
import models.ContributionStatusMapper.contributionStatusColumnType
import models.Player
import models.Positions
import models.SearchOptions
import tables.TimeConverters.dateMapper

import java.time.LocalDate

class Players(tag: Tag) extends Table[Player](tag, "t_player") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def oldid = column[Int]("oldid")
  def status = column[ContributionStatus]("status")
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  def birthdate = column[Option[LocalDate]]("birthdate")
  def draftYear = column[Int]("draft_year")
  def canonicalName = column[String]("canonical_name")
  
  def * = (id, oldid, status, firstName, lastName, birthdate, draftYear, canonicalName) <> (Player.tupled, Player.unapply)
}

object Players {
  val db = play.api.db.slick.DB
  val players = TableQuery[Players]
  val positionEligibility = TableQuery[Eligibility]

	def forId(id: Int) = db.withSession { implicit session =>
		players.filter(_.id === id).first
	}

  def forCanonicalName(id: String) = db.withSession { implicit session =>
    players.filter(_.canonicalName === id).first
  }

  def forSearchOptions(opts: SearchOptions) = db.withSession { implicit session =>
    val positionIdSet = Positions.getChildPositions(opts.pos.id).map(_.id)
    val playersAtPosition = positionEligibility
      .filter(_.positionId inSet positionIdSet)
      .groupBy(_.playerId)
      .map { case (playerId, group) => playerId }

    var query = players
      .filter(p => p.draftYear === opts.year)
      .filter(_.id in playersAtPosition)

    if (opts.name.isDefined) {
      val searchTerm = "%" + opts.name + "%"
      query = query.filter(p => (p.firstName like searchTerm) || (p.lastName like searchTerm))
    }

    //val pageSize = 10;
    query
      .sortBy(p => (p.lastName, p.firstName, p.id))
      //.drop(pageSize*(opts.page - 1))
      //.take(pageSize)
      .list    
  }
}