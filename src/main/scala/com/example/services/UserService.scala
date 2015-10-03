package com.example.services

import javax.inject.Singleton

import com.twitter.finagle.{Httpx, httpx}

import scala.util.parsing.json.JSONObject

/**
 * Created by fayimora on 25/07/15.
 */

@Singleton
class UserService {
  val client = Httpx.newService("localhost:3000")

  def all() = {
    val request = httpx.Request(httpx.Method.Get, "/users")
    request.host = "localhost"
    request.setContentTypeJson()
    request.setContentString(JSONObject(Map("from" -> "me")).toString())
    client(request)
  }
}
