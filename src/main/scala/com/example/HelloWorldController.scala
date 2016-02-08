package com.example

import javax.inject.Inject

import com.example.services.UserService
import com.twitter.finagle.oauth2.OAuthError
import com.twitter.finagle.OAuth2
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request._
import com.twitter.finatra.validation._
import com.twitter.util.Future

/**
 * Created by fayimora on 21/07/15.
 */

case class HelloRequest(@RequestInject req: Request, @NotEmpty @QueryParam name: Option[String])

class HelloWorldController @Inject()(userService: UserService,
                                     dataHandler: OAuthDataHandler)
  extends Controller with OAuth2 {

  get("/") { request: Request =>
    response.ok.json("Welcome, please visit /hello")
  }

  get("/oauth2/token") {req: Request =>
    issueAccessToken(req, dataHandler) flatMap { token =>
      val jsonResponse = collection.mutable.Map(
        "access_token" -> token.accessToken,
        "token_type" -> token.tokenType
      ) ++ token.expiresIn.map(
        "expires_in" -> _
      ) ++ token.refreshToken.map(
        "refresh_token" -> _
      ) ++ token.scope.map(
        "scope" -> _
      )
      Future.value(response.ok.json(jsonResponse))
    } handle {
      case e:OAuthError =>
        val jsonError = Map(
          "status" -> e.statusCode,
          "error" -> e.errorType,
          "description" -> e.description
        )
        response.status(e.statusCode).json(jsonError)
    }
  }

  get("/hello") { req: HelloRequest =>
    authorize(req.req, dataHandler) flatMap { authInfo =>
      info(s"I got here via a ${req.req.method} request!")
      val name = req.name.getOrElse("Human")
      Future.value(response.ok.json(Map("msg" -> s"Hi there $name !")))
    } handle {
      case e:OAuthError =>
        val jsonError = Map(
          "status" -> e.statusCode,
          "error" -> e.errorType,
          "description" -> e.description
        )
        response.status(e.statusCode).json(jsonError)
    }
  }

  post("/fetch") { req: Request =>
    userService.all
  }
}
