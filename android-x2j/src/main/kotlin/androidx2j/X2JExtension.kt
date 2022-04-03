package androidx2j

import java.util.regex.Pattern

open class X2JExtension {
    private var includes = mutableListOf(".*")
    private var excludes = mutableListOf<String>()
    private var isIncludesChanged = false

    fun includes(vararg patterns: String) {
        if (!isIncludesChanged) {
            includes.clear()
            isIncludesChanged = true
        }
        includes.addAll(patterns)
    }

    fun excludes(vararg patterns: String) {
        excludes.addAll(patterns)
    }

    internal fun matches(text: String): Boolean {
        return includes.any { matches(it, text) } && excludes.all { !matches(it, text) }
    }

    private fun matches(pattern: String, text: String): Boolean {
        return Pattern.matches(pattern, text)
    }
}