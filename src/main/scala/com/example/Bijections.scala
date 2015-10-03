package com.example

/**
 * Created by fayimora on 28/07/15.
 */
import java.net.InetSocketAddress

import com.twitter.finagle.httpx.Request
import org.jboss.netty.handler.codec.http.HttpRequest

// TODO Use bijection-core when bijection.Conversion is contravariant in A.
// See: github.com/twitter/bijection/pull/180.

trait Injection[A, B] {
  def apply(a: A): B
}

object Bijections {
  def from[A, B](a: A)(implicit I: Injection[A, B]): B = I.apply(a)


  implicit val requestFromNetty = new Injection[HttpRequest, Request] {
    def apply(r: HttpRequest): Request = new Request {
      val httpRequest = r
      lazy val remoteSocketAddress = new InetSocketAddress(0)
    }
  }

  // Response

//  implicit val responseFromNetty = new Injection[HttpResponse, Response] {
//    def apply(r: HttpResponse): Response = Response(r)
//  }
//
//  implicit val responseToNetty = new Injection[Response, HttpResponse] {
//    def apply(r: Response): HttpResponse = r.httpResponse
//  }
}