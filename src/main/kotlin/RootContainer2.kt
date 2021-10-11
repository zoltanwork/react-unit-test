import RootStyles2.Table
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.css.*
import kotlinx.html.classes
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.*
import react.*
import react.dom.attrs
import styled.*

external interface RootContainer2Props: PropsWithChildren {
  var restClient: RestClient
}

val RootContainer2 = fc<RootContainer2Props>("RootContainer") { props  ->
  var response by useState<List<ResponseDto>>()
  val container = useRef<Element>(null)
  val chartDiv = useRef<HTMLDivElement>(null)
  var flag by useState(true)

  println("root container")
  useEffect(listOf<String>()) {
    MainScope().async {
      println("before response ----------------------------------------------------------------")
      response = props.restClient.getData()
      println("after response ----------------------------------------------------------------")
    }
  }
  styledDiv {
    styledDiv {
      ref = chartDiv
      attrs.id = "chart"
    }
    styledButton {
      attrs.onClickFunction = { flag = !flag }
      +"BUTTON"
    }
  }
  styledDiv {
    ref = container
    +"Hello world!"
    println("=== list size = ${response?.size}")
    styledTable {
      attrs.id = "table"
      attrs.classes = setOf("tableClass")
//      css { +Table } // IF I UNCOMMENT THIS LINE, THE TEST ClientTest FAILS
      styledTbody {
        response?.forEachIndexed { idx, item ->
          styledTr {
            attrs.id = "tr$idx"
            attrs.classes = setOf("trClass")
            styledTd {
              attrs.id = "td${idx}userId"
              styledDiv {
                attrs {
                  attributes["id"] = "div${idx}userId"
                }
                +item.userId.toString()
              }
            }
            styledTd {
              attrs.id = "td${idx}id"
              +item.id.toString()
            }
            styledTd {
              attrs.id = "td${idx}title"
              +item.title
            }
            styledTd {
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
//      println(" container first child=${it.children.asDynamic()[0].children[0]}")
//      println(" .tableClass=${it.querySelector(".tableClass")}")
    }
  }
}

fun RBuilder.rootContainer2(restClient: RestClient) = RootContainer2.invoke {
  attrs.restClient = restClient
}

object RootStyles2 : StyleSheet("CursorInfoPanel", isStatic = true) {
  val Table by css {
    width = 100.pct
    fontSize = 14.px
    tableLayout = TableLayout.fixed
  }
}
