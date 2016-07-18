package com.example

import javax.inject.Inject

import com.example.services.UserService
import com.twitter.finagle.oauth2.{OAuthError, OAuthErrorInJson}
import com.twitter.finagle.{OAuth2, Service, SimpleFilter}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.inject.Logging
import com.twitter.util.Future

/**
  * Created by fayimora on 08/02/2016.
  */
class AuthFilter @Inject()(dataHandler: OAuthDataHandler,
                           userService: UserService)
  extends SimpleFilter[Request, Response] with OAuth2 with OAuthErrorInJson with Logging {

  override def apply(request: Request, service: Service[Request, Response]): Future[Response] = {
    authorize(request, dataHandler) flatMap { authInfo =>
      UserContext.setUser(request, authInfo.user)
      service(request)
    } handle {
      case e: OAuthError => handleError(e)
    }
  }
}
