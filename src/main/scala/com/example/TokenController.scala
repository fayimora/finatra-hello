package com.example

import javax.inject.Inject

import com.twitter.finagle.OAuth2
import com.twitter.finagle.http.Request
import com.twitter.finagle.oauth2.{OAuthError, OAuthErrorInJson, OAuthTokenInJson}
import com.twitter.finatra.http.Controller
import com.twitter.util.Future

/**
  * Created by fayimora on 18/07/2016.
  */
class TokenController @Inject()(dataHandler: OAuthDataHandler)
  extends Controller with OAuth2 with OAuthTokenInJson with OAuthErrorInJson {

  post("/oauth2/token") {req: Request =>
    issueAccessToken(req, dataHandler) flatMap { token =>
      Future(convertToken(token))
    } handle {
      case e: OAuthError => handleError(e)
    }
  }

}
