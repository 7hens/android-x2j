package androidx2j.parser.view

import androidx2j.parser.AttrParser
import com.squareup.javapoet.ClassName

object AbsListView : IView, IView.Parent {
    private val cAbsListView = ClassName.get("android.widget", "AbsListView")

    override val myParser = ViewGroup.myParser + AttrParser.androidBuilder()
            .add("android:cacheColorHint") { line("$view.setCacheColorHint(\$L)", color(it)) }
            .add("android:choiceMode") { line("$view.setChoiceMode(\$T.CHOICE_MODE_\$L)", cAbsListView, constant(it)) }
            .add("android:drawSelectorOnTop") { line("$view.setDrawSelectorOnTop(\$L)", bool(it)) }
            .add("android:fastScrollEnabled") { line("$view.setFastScrollEnabled(\$L)", bool(it)) }
            .add("android:listSelector") { line("$view.setSelector(\$L)", drawable(it)) }
            .add("android:scrollingCache") { line("$view.setScrollingCacheEnabled(\$L)", bool(it)) }
            .add("android:smoothScrollbar") { line("$view.setSmoothScrollbarEnabled(\$L)", bool(it)) }
            .add("android:stackFromBottom") { line("$view.setStackFromBottom(\$L)", bool(it)) }
            .add("android:textFilterEnabled") { line("$view.setTextFilterEnabled(\$L)", bool(it)) }
            .add("android:transcriptMode") { line("$view.setTranscriptMode(\$T.TRANSCRIPT_MODE_\$L)", cAbsListView, constant(it)) }
            .build()


    override val childParser = ViewGroup.childParser
}
  