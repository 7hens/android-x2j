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

    private fun getStackTrace(error: Throwable): String {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        error.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }
}