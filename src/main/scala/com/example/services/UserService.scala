package com.example.services

import javax.inject.Singleton

/**
 * Created by fayimora on 25/07/15.
 */

@Singleton
class UserService {

  def all() = {
    Seq()
//    val request = httpx.Request(httpx.Method.Get, "/users")
//    request.host = "localhost"
//    request.setContentTypeJson()
//    request.setContentString(JSONObject(Map("from" -> "me")).toString())
//    client(request)
  }
}
