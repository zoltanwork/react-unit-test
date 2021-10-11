//import io.data2viz.charts.chart.chart
//import io.data2viz.charts.chart.mark.dot
//import io.data2viz.charts.chart.quantitative
//import io.data2viz.charts.core.*
//import io.data2viz.charts.viz.newVizContainer
//import io.data2viz.geom.Point
//import io.data2viz.geom.size
//import kotlinx.dom.clear
//import org.w3c.dom.HTMLDivElement
//import kotlin.random.Random
//
//fun addChart(chartDiv: HTMLDivElement) {
//  chartDiv.clear()
//  chartDiv.newVizContainer().apply {
//    size = size(200, 200)
//    val numPoint = 200
//    val randomPoints = (1 .. numPoint).map{ Point(Random.nextDouble(), Random.nextDouble() * 3 - 0.36) }.sortedBy { it.x }
//    chart(randomPoints) {
//      config {
//        // Disable user-input highlight & selection from the chart
//        events {
//          zoomMode = ZoomMode.XY
//          panMode = PanMode.XY
//          selectionMode = SelectionMode.Multiple
//          highlightMode = HighlightMode.None
//
//          getUserActionOnMouseDown = { event ->
//            when {
//              event.altKey                        -> UserAction.Zoom
//              else                                -> UserAction.Select
//            }
//          }
//        }
//      }
//
//      val xPosition = quantitative( { domain.x } )
//      val yPosition = quantitative( { domain.y } )
//
//      dot(xPosition, yPosition)
//    }
//  }
//}
//
