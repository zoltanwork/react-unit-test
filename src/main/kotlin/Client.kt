import io.ktor.client.engine.js.*
import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
  val container = document.getElementById("root")
  window.onload = {
    render(container) {
      rootContainer(RestClient(JsClient().create()))
    }
  }
}

// https://jsonplaceholder.typicode.com/posts