import kotlinx.browser.document
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.Element
import org.w3c.dom.HTMLCollection
import org.w3c.dom.HTMLTableSectionElement
import org.w3c.dom.get
import react.*
import react.dom.ReactHTML.div
import react.dom.ReactHTML.table
import react.dom.ReactHTML.tbody
import react.dom.ReactHTML.td
import react.dom.ReactHTML.tr
import react.dom.attrs
import react.dom.button
import react.dom.div
import styled.*

external interface RootContainerProps: PropsWithChildren {
  var restClient: RestClient
}

val RootContainer = fc<RootContainerProps>("RootContainer") { props  ->
  var response by useState<List<ResponseDto>>()
  val container = useRef<Element>(null)
  var flag by useState(true)

  println("root container")
  useEffect(listOf<String>()) {
    MainScope().async {
      println("before response ----------------------------------------------------------------")
      response = props.restClient.getData()
      println("after response ----------------------------------------------------------------")
    }
  }
  button {
    attrs.onClickFunction = { flag = !flag }
    +"BUTTON"
  }
  div {
    ref = container
    +"Hello world!"
    println("=== list size = ${response?.size}")
//    styledButton {
//      attrs.onClickFunction = { flag = !flag }
//      +"BUTTON"
//    }
    table {
      attrs {
        id = "table"
        className = "tableClass"
      }
//    css { +Table }
      tbody {
        response?.forEachIndexed { idx, item ->
          tr {
            attrs.id = "tr$idx"
            attrs.className = "trClass"
            td {
              attrs.id = "td${idx}userId"
              div {
                attrs {
                  attributes["id"] = "div${idx}userId"
                }
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
    console.log("container.current", container.current)
    container.current?.also { div ->
      val a = div.children.asDynamic()[0].children[0]
      console.log("a", a)
      if (a is HTMLTableSectionElement) {
//        console.log(a.children)
        document.querySelector("#root")?.also {
          val bb = it.children[1].asDynamic().children[0].id//.children[0].rows as HTMLCollection
          console.log(bb)
          val cc = document.querySelector(".tableClass")
          console.log(cc)
          val dd = document.querySelector("#div0userId")
          console.log("#div0userId", dd)
        }
//        console.log("b", b)
//        if (b != null) {
//          b.asDynamic().style.backgroundColor = "blue"
//        }
      }
//      println(" container first child=${it.children.asDynamic()[0].children[0]}")
//      println(" .tableClass=${it.querySelector(".tableClass")}")
    }
  }
}

fun RBuilder.rootContainer(restClient: RestClient) = RootContainer.invoke {
  attrs.restClient = restClient
}


object RootStyles : StyleSheet("CursorInfoPanel", isStatic = true) {
  val Table by css {
    width = 100.pct
    fontSize = 14.px
    tableLayout = TableLayout.fixed
  }

}
