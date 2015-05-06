package models

import scala.slick.driver.PostgresDriver.simple._
import play.api.Play.current
import play.api.data.Forms._

case class Testing(my_content: String)

class Testings(tag: Tag) extends Table[Testing](tag, "testings") {
  def my_content = column[String]("my_content")
  def * = (my_content) <> (Testing, Testing.unapply)
}

object Testings {
	val db = play.api.db.slick.DB
	val testings = TableQuery[Testings]
	def all: List[Testing] = db.withSession { implicit session =>
		testings.list
	}
	def first: Testing = db.withSession{ implicit session =>
		testings.first
	}
}