package tables

import scala.slick.driver.PostgresDriver.simple._
import play.api.Play.current
import play.api.data.Forms._

import models.PositionEligibility
import models.ContributionStatus
import models.ContributionStatusMapper.contributionStatusColumnType

class Eligibility(tag: Tag) extends Table[PositionEligibility](tag, "t_position_eligibility") {
  def playerId = column[Int]("player_id")
  def positionId = column[Int]("position_id")
  def status = column[ContributionStatus]("status")
  def * = (playerId, positionId, status) <> (PositionEligibility.tupled, PositionEligibility.unapply)
}