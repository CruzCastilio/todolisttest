package controllers

import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject._

import models.Task
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index = Action {
    Redirect(routes.HomeController.tasks)
  }

  def tasks = Action {
    Ok(views.html.index(Task.all(), taskForm))
  }

  def newTask = Action {
    implicit request =>
      taskForm.bindFromRequest.fold(
        errors => BadRequest(views.html.index(Task.all(), errors)),
        x=>x match { case(label, who) => {
          val today = Calendar.getInstance().getTime()
          val timeFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
          val time = timeFormat.format(today)

          Task.create(label, who, time)
          Redirect(routes.HomeController.tasks)
        }
        }
      )
  }

  def deleteTask(id: Long) = TODO

  def completeTask(id: Long) = TODO

  val taskForm = Form(
    tuple (
      "label" -> nonEmptyText,
      "who" -> nonEmptyText
    )
  )

}