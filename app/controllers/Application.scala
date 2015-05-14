package controllers

import play.api._
import play.api.mvc._
import models._
import tables._

object Application extends Controller {

  def search(year: Int, pos: String, name: Option[String], sortd: Option[String], sorta: Option[String], page: Int) = Action {
  	val searchOptions = SearchOptions(year, pos, name, page);
  	val playerList = List();
    Ok(views.html.search(searchOptions, playerList))
  }

  def player(id: String, pos: Option[String]) = Action {
  	val player = Players.forCanonicalName(id)
  	val eligiblePositions = Eligibility.forPlayerId(player.id)
  	val measurements = BestMeasurements.forPlayer(player.id)
  	val primaryPosition = Positions.getDisplayPosition(eligiblePositions)
  	val population = BestMeasurements.forPosition(Positions.getPositionForAbbr(pos getOrElse primaryPosition.abbr).id)
  	val percentiles = Percentiles(measurements, population)
    val fullMeasurements = Measurements.forPlayerId(player.id)
    val positions = eligiblePositions.foldLeft(Set[Position]())((s, p) => s ++ Positions.getImpliedPositions(p.id))
  	val displayPlayer = DisplayPlayer(player, positions, fullMeasurements, percentiles)

  	val comparables = Comparisons.getComparisons(measurements, population)
      .map { p => 
        val compPlayer = Players.forId(p._1)
        val percentiles = Percentiles(population.find(_.playerId == p._1).get, population)
        ComparablePlayer(
          compPlayer, 
          Positions.getDisplayPosition(Eligibility.forPlayerId(compPlayer.id)), 
          percentiles, 
          p._2
        )
      }
    Ok(views.html.player(displayPlayer, primaryPosition, comparables))
  }

  def embed(id: String, show: String) = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def oldListing(year: Int) = Action {
    Redirect("/", MOVED_PERMANENTLY)
  }

  def oldPlayer(id: Int) = Action {
    Redirect("/", MOVED_PERMANENTLY)
  }

  def oldPlayerEmbed(id: Int) = Action {
    Redirect("/", MOVED_PERMANENTLY)
  }

  def oldPlayerEmbedGraph(id: Int) = Action {
    Redirect("/", MOVED_PERMANENTLY)
  }
}

