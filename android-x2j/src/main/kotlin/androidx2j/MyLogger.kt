package androidx2j

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
        System.err.println(LOG_TAG + when (msg) {
            null -> null
            is Throwable -> getStackTrace(msg)
            else -> msg.toString()
        })
    }

    fun getStackTrace(error: Throwable): String {
        val writer = PrintWriter(StringWriter())
        error.printStackTrace(writer)
        writer.flush()
        return writer.toString()
    }
}