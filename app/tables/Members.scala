package tables

import scala.slick.driver.PostgresDriver.simple._
import play.api.Play.current

import models.Member

class Members(tag: Tag) extends Table[Member](tag, "t_member") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def email = column[String]("email")
  def passHash = column[String]("pass_hash")
  def status = column[Int]("status")
  def joinedOn = column[Int]("joined_on")
  def permissions = column[Int]("permissions")
  def * = (id, email, passHash, status, joinedOn, permissions) <> (Member.tupled, Member.unapply)
}

object Members {
  val db = play.api.db.slick.DB
  val members = TableQuery[Members]

	def forId(id: Int) = db.withSession { implicit session =>
		members.filter(_.id === id).first
	}
}