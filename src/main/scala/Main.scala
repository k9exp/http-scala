import scala.io.StdIn
import org.apache.pekko
import pekko.actor.typed.ActorSystem
import pekko.actor.typed.scaladsl.Behaviors
import pekko.http.scaladsl.Http
import pekko.http.scaladsl.model.*
import pekko.http.scaladsl.server.Directives.*
import scala.concurrent.ExecutionContextExecutor

@main def main(): Unit =
    implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "http-scala-app")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val route =
        path("") {
            get {
                complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "ABCD"))
            }
        }

    val host = "localhost"
    val port = 4000
    val bindingFuture = Http().newServerAt(host, port).bind(route)

    println(s"Listening on http://$host:$port")
    StdIn.readLine()
    bindingFuture
        .flatMap(_.unbind())
        .onComplete(_ => system.terminate())
