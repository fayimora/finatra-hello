package com.example

import com.twitter.finagle.http.Request

/**
  * Created by fayimora on 08/02/2016.
  */
object UserContext {
  private val UserField = Request.Schema.newField[User]()

  implicit class UserContextSyntax(val request: Request) extends AnyVal {
    def user: User = request.ctx(UserField)
  }

  def setUser(request: Request, user: User): Unit = {
    request.ctx.update(UserField, user)
  }
}
