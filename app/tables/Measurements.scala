package tables

import scala.slick.driver.PostgresDriver.simple._
import play.api.Play.current

import models.Measurement
import models.ContributionStatus
import models.ContributionStatusMapper.contributionStatusColumnType

class Measurements(tag: Tag) extends Table[Measurement](tag, "t_Measurement") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def status = column[ContributionStatus]("status")
  def playerId = column[Int]("player_id")
  def measurableId = column[Int]("measurable_id")
  def measurement = column[Double]("measurement")
  def source = column[Int]("source")

  def * = (id, status, playerId, measurableId, measurement, source) <> (Measurement.tupled, Measurement.unapply)
}

object Measurements {
  val db = play.api.db.slick.DB
  val Measurements = TableQuery[Measurements]

	def forPlayerId(id: Int) = db.withSession { implicit session =>
		Measurements.filter(_.playerId === id).list
	}
}