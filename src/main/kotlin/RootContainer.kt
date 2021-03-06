import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import org.w3c.dom.*
import react.*
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.tr

external interface RootContainerProps: PropsWithChildren {
  var restClient: RestClient
}

val RootContainer = fc<RootContainerProps>("RootContainer") { props  ->
  var response by useState<List<ResponseDto>>()
  val container = useRef<Element>(null)
  val chartDiv = useRef<HTMLDivElement>(null)
  val flag by useState(true)

  println("root container")
  useEffect(listOf<String>()) {
    MainScope().async {
      println("before response ----------------------------------------------------------------")
      response = props.restClient.getData()
      println("after response ----------------------------------------------------------------")
    }
  }
  div {
    div {
      ref = chartDiv
      attrs.id = "chart"
    }
    button {
//      attrs.onClickFunction = { flag = !flag }
      +"BUTTON"
    }
  }
  div {
    ref = container
    +"Hello world!"
    println("=== list size = ${response?.size}")
    table {
      attrs.id = "table"
      attrs.className = "tableClass"
      tbody {
        response?.forEachIndexed { idx, item ->
          tr {
            attrs.id = "tr$idx"
            attrs.className = "trClass"
            td {
              attrs.id = "td${idx}userId"
              div {
                attrs.id = "div${idx}userId"
                +item.userId.toString()
              }
            }
            td {
              attrs.id = "td${idx}id"
              +item.id.toString()
            }
            td {
              attrs.id = "td${idx}title"
              +item.title
            }
            td {
              attrs.id = "td${idx}body"
              +item.body
              +" "
              +flag.toString()
            }
          }
        }
      }
    }

//    chartDiv.current?.also { addChart(it) }

    container.current?.also { div ->
      val a = div.children.asDynamic()[0].children[0]
      console.log("\na\n", a)
      if (a is HTMLTableSectionElement) {
        document.querySelector("#root")?.also {
          val bb = it.children[1].asDynamic().children[0].id//.children[0].rows as HTMLCollection
          console.log(bb)
          val cc = document.querySelector(".tableClass")
          console.log(cc)
          val dd = document.querySelector("#div0userId")
          console.log("#div0userId", dd)
        }
      }
    }
  }
}

fun RBuilder.rootContainer(restClient: RestClient) = RootContainer.invoke {
  attrs.restClient = restClient
}
