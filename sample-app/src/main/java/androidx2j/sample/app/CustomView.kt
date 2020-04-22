package androidx2j.sample.app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

/**
 * @author：chengwei 2018/8/25
 * @description
 */
class CustomView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private var mMixColor = 0
    fun setMixColor(mixColor: Int) {
        mMixColor = mixColor
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        when (mMixColor) {
            RED -> canvas.drawColor(Color.RED)
            BLACK -> canvas.drawColor(Color.BLACK)
            MIX -> {
                val clip = measuredWidth shr 1
                canvas.save()
                canvas.clipRect(0, 0, clip, measuredHeight)
                canvas.drawColor(Color.BLACK)
                canvas.restore()
                canvas.save()
                canvas.clipRect(clip, 0, measuredWidth, measuredHeight)
                canvas.drawColor(Color.RED)
                canvas.restore()
            }
            else -> {
            }
        }
    }

    companion object {
        const val RED = 1
        const val BLACK = 1 shl 1
        const val MIX = RED or BLACK
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0)
        val N = typedArray.length()
        var index: Int
        for (i in 0 until N) {
            when (typedArray.getIndex(i).also { index = it }) {
                R.styleable.CustomView_mixColor -> setMixColor(typedArray.getInteger(index, RED))
            }
        }
        typedArray.recycle()
    }
}