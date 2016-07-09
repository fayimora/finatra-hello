package com.example

import java.util.Date
import javax.inject.{Singleton}

import com.twitter.finagle.oauth2.{AccessToken, AuthInfo, DataHandler}
import com.twitter.inject.Logging
import com.twitter.util.Future
import org.joda.time.DateTime

/**
 * Created by fayimora on 27/07/15.
 */

@Singleton
class OAuthDataHandler extends DataHandler[User] with Logging {
  def userAccessToken = AccessToken(TokenGenerator.generateToken, Some(TokenGenerator.generateToken), None, Some(3600), DateTime.now().toDate)

  def validateClient(clientId: String, clientSecret: String, grantType: String) = {
    debug(s" ===== validateClient($clientId, $clientSecret, $grantType)")
    Future.value(true)
  }

  def findUser(username: String, password: String): Future[Option[User]] = {
    debug(s" ===== findUser($username)")
    Future.value(Some(User(1, "fayi")))
  }

  def createAccessToken(authInfo: AuthInfo[User]) = {
    debug(s" ===== createAccessToken($authInfo)")
    Future.value(userAccessToken)
  }

  def findAuthInfoByCode(code: String): Future[Option[AuthInfo[User]]] = {
    debug(s" ===== findAuthInfoByCode($code)")
    Future.value(Some(AuthInfo(User(1, "fayi"), "clientid", None, None)))
  }

  def findAuthInfoByRefreshToken(refreshToken: String): Future[Option[AuthInfo[User]]] = {
    debug(s" ===== findAuthInfoByRefreshToken($refreshToken)")
    Future.value(None)
  }

  def findClientUser(clientId: String, clientSecret: String, scope: Option[String]): Future[Option[User]] = {
    debug(s"findClientUser($clientId, $clientSecret, $scope")
    Future.value(None)
  }

  def findAccessToken(token: String): Future[Option[AccessToken]] = {
    debug(s" ===== findAccessToken($token)")
    if(token == "token1") Future.value(Some(userAccessToken))
    else Future.value(None)
  }

  def findAuthInfoByAccessToken(accessToken: AccessToken): Future[Option[AuthInfo[User]]] = {
    debug(s" ===== findAuthInfoByAccessToken($accessToken)")
    if(accessToken.token == "token1") {
      val u = User(10000, "username")
      Future.value(Some(AuthInfo(u, "client_id", None, None)))
    } else Future.value(None)
  }
  
  def getStoredAccessToken(authInfo: AuthInfo[User]): Future[Option[AccessToken]] = {
    debug(s" ===== getStoredAccessToken($authInfo)")
    Future.value(None)
  }

  def refreshAccessToken(authInfo: AuthInfo[User], refreshToken: String): Future[AccessToken] = {
    debug(s" ===== refreshAccessToken($authInfo, $refreshToken)")
    Future.value(AccessToken("", Some(""), Some(""), Some(0L), new Date()))
  }
}