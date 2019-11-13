
package androidx2j.parser.view
  
import androidx2j.parser.AttrParser

object GestureOverlayView : IView {
    override val myParser = FrameLayout.myParser + AttrParser.androidBuilder()
        .add("android:eventsInterceptionEnabled") {
            // 
            todo()
        }
        .add("android:fadeDuration") {
            // 
            todo()
        }
        .add("android:fadeEnabled") {
            // 
            todo()
        }
        .add("android:fadeOffset") {
            // 
            todo()
        }
        .add("android:gestureColor") {
            // 
            todo()
        }
        .add("android:gestureStrokeAngleThreshold") {
            // 
            todo()
        }
        .add("android:gestureStrokeLengthThreshold") {
            // 
            todo()
        }
        .add("android:gestureStrokeSquarenessThreshold") {
            // 
            todo()
        }
        .add("android:gestureStrokeType") {
            // 
            todo()
        }
        .add("android:gestureStrokeWidth") {
            // 
            todo()
        }
        .add("android:orientation") {
            // 
            todo()
        }
        .add("android:uncertainGestureColor") {
            // 
            todo()
        }
        .build()

    
}
  