package tables

import scala.slick.driver.PostgresDriver.simple._
import play.api.Play.current
import play.api.data.Forms._

import models.MeasurementSet
import models.Positions

class BestMeasurements(tag: Tag) extends Table[MeasurementSet](tag, "v_best_measurements") {
  def playerId = column[Int]("player_id")
  def height = column[Option[Double]]("height")
  def weight = column[Option[Double]]("weight")
  def armLength = column[Option[Double]]("arm_length")
  def wingspan = column[Option[Double]]("wingspan")
  def handSize = column[Option[Double]]("hand_size")
  def time40 = column[Option[Double]]("time40")
  def time20 = column[Option[Double]]("time20")
  def time10 = column[Option[Double]]("time10")
  def benchPress = column[Option[Double]]("bench")
  def broadJump = column[Option[Double]]("broad")
  def verticalJump = column[Option[Double]]("vertical")
  def cone3 = column[Option[Double]]("cone3")
  def shuttle20 = column[Option[Double]]("shuttle20")
  def shuttle60 = column[Option[Double]]("shuttle60")
  def wonderlic = column[Option[Double]]("wonderlic")
  def * = (playerId, height, weight, armLength, wingspan, handSize, time40, time20, time10, benchPress, broadJump, verticalJump, cone3, shuttle20, shuttle60, wonderlic) <> (MeasurementSet.tupled, MeasurementSet.unapply)
}

object BestMeasurements {
	val db = play.api.db.slick.DB
	val bestMeasurements = TableQuery[BestMeasurements]
  val positionEligibility = TableQuery[Eligibility]

  def forPosition(positionId: Int): List[MeasurementSet] = db.withSession { implicit session =>
    val positionSet = Positions.getImpliedPositions(positionId).map(_.id)
    (for (
      (m, pe) <- bestMeasurements innerJoin positionEligibility.filter(_.positionId.inSetBind(positionSet)) on (_.playerId === _.playerId)
    ) yield m).list
  } 
	def forPlayer(playerId: Int): MeasurementSet = db.withSession{ implicit session =>
		bestMeasurements.filter(_.playerId === playerId).first
	}
}