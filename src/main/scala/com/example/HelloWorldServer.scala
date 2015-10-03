package com.example

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.logging.filter.{LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.logging.modules.LogbackModule

/**
 * Created by fayimora on 21/07/15.
 */

object HelloWorldServerMain extends HelloWorldServer

class HelloWorldServer extends HttpServer {
  override def modules = Seq(LogbackModule)

  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
//      .filter[HttpxToHttpFilter]
      .add[HelloWorldController]
  }
}
