/**
 * Created by fayimora on 19/07/15.
 */

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.QueryParam
import com.twitter.finatra.validation.NotEmpty

case class HelloRequest(@NotEmpty @QueryParam name: Option[String])

class HelloWorldController extends Controller {

  get("/") { request: Request =>
    response.ok.json("Welcome, please visit /hello")
  }

  get("/hello") { req: HelloRequest =>
    info("I got here!")
    val name = req.name.getOrElse("Human")
    response.ok.json(Map("msg" -> s"Hi there $name !"))
  }
}
