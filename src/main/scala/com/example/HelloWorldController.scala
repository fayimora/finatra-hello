package com.example

import javax.inject.Inject

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request._
import com.twitter.finatra.validation._
import com.twitter.util.Future

/**
 * Created by fayimora on 21/07/15.
 */

case class HelloRequest(@RequestInject req: Request, @NotEmpty @QueryParam name: Option[String])

class HelloWorldController @Inject()(dataHandler: OAuthDataHandler) extends Controller {

  get("/") { request: Request =>
    response.ok("Welcome, please visit /hello")
  }

  get("/hello") { req: HelloRequest =>
    info(s"I got here via a ${req.req.method} request!")
    val name = req.name.getOrElse("Human")
    Future.value(response.ok.json(Map("msg" -> s"Hi there $name !")))
  }

}
