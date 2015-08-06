package controllers

import play.api._
import play.api.mvc._
import models._
import tables._

object Application extends Controller {

  def search(year: Int, pos: String, name: Option[String], attr: Option[String], sort: Option[String]) = Action {
    val searchPosition = Positions.getPositionForAbbr(pos)
    val searchOptions = SearchOptions(year, searchPosition, name, attr, sort);
    val population = BestMeasurements.forPosition(searchPosition.id)
  	val playerList = Players.forSearchOptions(searchOptions).map(p => {
      val eligiblePositions = Eligibility.forPlayerId(p.id)
      val measurements = BestMeasurements.forPlayer(p.id)
      val primaryPosition = Positions.getDisplayPosition(eligiblePositions)
      val percentiles = Percentiles(measurements, population)

      SearchResultPlayer(p, primaryPosition, percentiles, measurements.forMeasurable(searchOptions.displayMeasurable))
    });

    val years = (1999 to 2015).toList diff List(searchOptions.year)
    val positions = Positions.getAllPositions.filter((pos: Position) => pos.positionType != Role).toList diff List(searchPosition)

    Ok(views.html.search(searchOptions, playerList, Measurables.all diff List(searchOptions.displayMeasurable), years, positions))
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

