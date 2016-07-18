package com.example

import javax.inject.Inject

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.oauth2.OAuthError
import com.twitter.finatra.http.exceptions.ExceptionMapper
import com.twitter.finatra.http.response.ResponseBuilder
import com.twitter.inject.Logging

/**
  * Created by fayimora on 09/07/2016.
  */
class OAuthExceptionMapper @Inject()(response: ResponseBuilder)
  extends ExceptionMapper[OAuthError] with Logging {

  override def toResponse(request: Request, exception: OAuthError): Response = {
//    response.status(exception.statusCode).body(exception.errorType)
    exception.toHttpResponse
  }
}