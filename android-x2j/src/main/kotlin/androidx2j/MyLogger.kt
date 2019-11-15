package androidx2j

import java.io.PrintStream
import java.io.PrintWriter
import java.io.StringWriter

/**
 * @author 7hens
 */
object MyLogger {
    private const val LOG_TAG = "@(android-x2j): "

    fun log(msg: Any?) {
        println(LOG_TAG + msg)
    }

    fun error(msg: Any?) {
        System.err.print(LOG_TAG + when (msg) {
            null -> null
            is Throwable -> {
                val writer = PrintWriter(StringWriter())
                msg.printStackTrace(writer)
                writer.flush()
                writer.toString()
            }
            else -> msg.toString()
        })
        System.err.println()
    }
}