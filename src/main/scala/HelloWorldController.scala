/**
 * Created by fayimora on 19/07/15.
 */

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class HelloWorldController extends Controller {

  get("/") { request: Request =>
    response.ok.json("Welcome, please visit /hello")
  }

  get("/hello") { request: Request =>
    info("I got here!")
    response.ok.json(Map("msg" -> "Hi there!"))
  }
}
