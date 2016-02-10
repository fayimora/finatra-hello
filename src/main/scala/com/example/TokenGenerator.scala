package com.example

import java.security.SecureRandom

import scala.annotation.tailrec

/**
  * Created by fayimora on 08/02/2016.
  *
  * Generates a Bearer Token according to the specification RFC6750 (http://http://tools.ietf.org/html/rfc6750)
  */
object TokenGenerator {
  val TOKEN_LENGTH = 32
  val TOKEN_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-._"
  val secureRandom = new SecureRandom()

  def generateToken: String =
    generateToken1(TOKEN_LENGTH)

  def generateToken(tokenLength: Int): String = tokenLength match {
    case 0 => ""
    case _ =>
      val idx: Int = secureRandom.nextInt(TOKEN_CHARS.length())
      TOKEN_CHARS(idx) + generateToken(tokenLength - 1)
  }

  def generateToken1(tokenLength: Int): String = {
    @tailrec
    def iter(acc: String, counter: Int): String = counter match {
      case 0 => acc
      case _ =>
        val idx = secureRandom.nextInt(TOKEN_CHARS.length())
        iter(acc + TOKEN_CHARS(idx), counter - 1)
    }
    iter("", tokenLength)
  }
}
