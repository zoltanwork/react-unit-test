//import kotlinx.html.InputType
//import kotlinx.html.js.onChangeFunction
//import org.w3c.dom.HTMLInputElement
//import react.*
//import react.dom.attrs
//import styled.css
//import styled.styledDiv
//import styled.styledInput
//
//external interface WelcomeProps : PropsWithChildren {
//  var name: String
//}
//
//data class WelcomeState(val name: String) : RState
//
//@JsExport
//class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {
//
//  init {
//    state = WelcomeState(props.name)
//  }
//
//  override fun RBuilder.render() {
//    styledDiv {
//      css {
//        +WelcomeStyles.textContainer
//      }
//      +"Hello, ${state.name}"
//    }
//    styledInput {
//      css {
//        +WelcomeStyles.textInput
//      }
//      attrs {
//        type = InputType.text
//        value = state.name
//        onChangeFunction = { event ->
//          setState(
//            WelcomeState(name = (event.target as HTMLInputElement).value)
//          )
//        }
//      }
//    }
//  }
//}
