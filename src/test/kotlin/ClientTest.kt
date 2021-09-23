import io.ktor.client.engine.mock.*
import kotlinx.browser.document
import kotlinx.coroutines.*
import org.w3c.dom.Element
import react.dom.render
import kotlin.js.Date.Companion.now
import kotlin.js.Promise
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
  },
]
""".trimIndent()

const val timeout = 10000

class ClientTest {
  private val scope = MainScope()

  @Test
  fun testCoroutines() = scope.promise {
    val div = document.createElement("div")
    div.id = "div"
    launch {
      document.body?.appendChild(div)

      val mockEngine = MockEngine {
//      println("MockEngine ###############################################################################\n")
        respond(mockResult)
      }
      render(div) {
        rootContainer(RestClient(mockEngine))
      }
      println("\nbefore delay ${now()}\n")
      delay(100)
      println("\nafter delay ${now()}\n")
    }

    var elapsed = waitFor { document.querySelector("#div") }
    println("\n document.#div found in $elapsed ms ${now()}\n")
    elapsed = waitFor { div.querySelector(".tableClass") }
    println("\n .tableClass found in $elapsed ms ${now()}\n")

//    elapsed = waitFor { document.querySelector("#tr0") }
//    println("\n document.#tr0 found in $elapsed ms ${now()}\n")

    assertNotNull(div.querySelector(".tableClass"), ".tableClass not found")
//    assertNotNull(div.querySelector("#tr1"), "#tr1 not found")
//    assertNotNull(div.querySelector("#td1-id"))
  }

  private suspend fun waitFor(test: () -> Element?): Int {
    val then = now()
    var elapsed = 0
    var element: Element? = null
    while (elapsed < timeout && element == null) {
      element = test()
      elapsed = (now() - then).toInt()
      delay(20)
    }
    if (element == null)
      fail("Element not found before timeout $timeout")
    return elapsed
  }

//  @Test
//  fun tableIsRendered() = runAsyncTest { checkTableIsRendered() }
//
//  @Test
//  fun tableIsRendered2Async() = MainScope().async(block = { checkTableIsRendered() })
//
//  private suspend fun checkTableIsRendered() {
//    val div = document.createElement("div")
//    val mockEngine = MockEngine {
////      console.log("MockEngine ###############################################################################\n")
//      respond(mockResult)
//    }
//    render(div) {
//      rootContainer(RestClient(mockEngine))
//    }
//    console.log("\nbefore timeout ${kotlin.js.Date.now()}\n")
//    delay(1000)
//    console.log("\nafter timeout ${kotlin.js.Date.now()}\n")
//    assertNotNull(div.querySelector("tr.tr1"), "tr")
//    assertNotNull(div.querySelector("td.td1-id"))
//  }
//
}

fun runAsyncTest(block: suspend CoroutineScope.() -> Unit): Promise<Unit> = MainScope().async(
//  Dispatchers.Unconfined,
  block = block
).asPromise()


object ModelMockResponse {
  operator fun invoke(): String =
    "..." // This contains the mock JSON response for the specific resource.
}