package androidx2j

/**
 * @author 7hens
 */
object MyLogger {
    private const val LOG_TAG = "@(android-x2j): "

    fun log(msg: Any?) {
        println(LOG_TAG + msg)
    }

    fun error(msg: Any?) {
        System.err.println(LOG_TAG + msg)
    }
}