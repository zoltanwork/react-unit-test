import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.browser.document
import kotlinx.coroutines.*
import org.w3c.dom.Element
import react.dom.render
import kotlin.js.Date.Companion.now
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.fail

val mockResult = """
[
  {
    "userId": 1,
    "id": 1,
    "title": "title1",
    "body": "body1"
  },
  {
    "userId": 2,
    "id": 2,
    "title": "title2",
    "body": "body2"
  }
]
""".trimIndent()

const val timeout = 10000

class ClientTest {
  private val scope = MainScope()

  @Test
  fun testTable() = scope.promise {
    val div = document.createElement("div")
    div.id = "div"
    launch {
      document.body?.appendChild(div)

      val mockEngine = MockEngine {
        respond(mockResult, headers = headersOf("Content-Type", ContentType.Application.Json.toString()))
      }
      render(div) {
        rootContainer2(RestClient(mockEngine))
      }
      println("\nbefore delay ${now()}\n")
      delay(100)
      println("\nafter delay ${now()}\n")
    }

    var elapsed = waitFor { document.querySelector("#div") }
    println("\n document.#div found in $elapsed ms ${now()}\n")
    elapsed = waitFor { div.querySelector(".tableClass") }
    println("\n .tableClass found in $elapsed ms ${now()}\n")

    elapsed = waitFor { document.querySelector("#tr0") }
    println("\n document.#tr0 found in $elapsed ms ${now()}\n")

    elapsed = waitFor { document.querySelector("#chart") }
    println("\n document.#chart found in $elapsed ms ${now()}\n")

    assertNotNull(div.querySelector(".tableClass"), ".tableClass not found")
    assertNotNull(div.querySelector("#tr1"), "#tr1 not found")
    assertNotNull(div.querySelector("#td1id"))
    assertNotNull(div.querySelector("#chart"))
  }

  private suspend fun waitFor(test: () -> Element?): Int {
    val then = now()
    var elapsed = 0
    var element: Element? = null
    console.log("**waitFor 1\n")
    while (elapsed < timeout && element == null) {
//      console.log("**waitFor 2\n")
      element = test()
      elapsed = (now() - then).toInt()
//      console.log("**waitFor 3\n")
      delay(20)
//      console.log("**waitFor 4\n")
    }
    if (element == null)
      fail("Element not found before timeout $timeout\n")
    return elapsed
  }
}
