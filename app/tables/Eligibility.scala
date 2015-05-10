package tables

import scala.slick.driver.PostgresDriver.simple._
import play.api.Play.current
import play.api.data.Forms._

import models.PositionEligibility
import models.ContributionStatus
import models.ContributionStatusMapper.contributionStatusColumnType
import models.Positions

class Eligibility(tag: Tag) extends Table[PositionEligibility](tag, "t_position_eligibility") {
  def playerId = column[Int]("player_id")
  def positionId = column[Int]("position_id")
  def status = column[ContributionStatus]("status")
  def * = (playerId, positionId, status) <> (PositionEligibility.tupled, PositionEligibility.unapply)
}

object Eligibility {
  val db = play.api.db.slick.DB
  val positionEligibility = TableQuery[Eligibility]

  def forPlayerId(playerId: Int) = db.withSession { implicit session =>
    positionEligibility
      .filter(_.playerId === playerId)
      .list
      .flatMap(pe => Positions.getImpliedPositions(pe.positionId))
      .toSet
  }
}