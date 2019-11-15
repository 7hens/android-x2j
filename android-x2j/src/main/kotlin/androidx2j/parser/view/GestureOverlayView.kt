
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object GestureOverlayView : IView {
    override val myParser = FrameLayout.myParser + AttrParser.androidBuilder()
        .add("eventsInterceptionEnabled") {
            // 
            todo()
        }
        .add("fadeDuration") {
            // 
            todo()
        }
        .add("fadeEnabled") {
            // 
            todo()
        }
        .add("fadeOffset") {
            // 
            todo()
        }
        .add("gestureColor") {
            // 
            todo()
        }
        .add("gestureStrokeAngleThreshold") {
            // 
            todo()
        }
        .add("gestureStrokeLengthThreshold") {
            // 
            todo()
        }
        .add("gestureStrokeSquarenessThreshold") {
            // 
            todo()
        }
        .add("gestureStrokeType") {
            // 
            todo()
        }
        .add("gestureStrokeWidth") {
            // 
            todo()
        }
        .add("orientation") {
            // 
            todo()
        }
        .add("uncertainGestureColor") {
            // 
            todo()
        }
        .build()

    
}
  