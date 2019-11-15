package androidx2j.parser.view

import androidx2j.parser.AttrParser
import androidx2j.parser.view.View.tintMode

object AbsSeekBar : IView {
    override val myParser = ProgressBar.myParser + AttrParser.androidBuilder()
            .add("thumbTint") { line("$view.setThumbTintList(\$L)", colorStateList(it)) }
            .add("thumbTintMode") { line("$view.setThumbTintMode(\$L)", tintMode(it)) }
            .add("tickMarkTint") { line("$view.setTickMarkTintList(\$L)", colorStateList(it)) }
            .add("tickMarkTintMode") { line("$view.setTickMarkTintMode(\$L)", tintMode(it)) }
            .build()
}
  