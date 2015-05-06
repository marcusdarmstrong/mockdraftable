package controllers

import play.api._
import play.api.mvc._
import models.Testings;

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def hello = Action {
    Ok(views.html.hello(Testings.first.my_content))
  }

}