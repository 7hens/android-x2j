package androidx2j.parser

import java.io.File
import java.util.*

/**
 * @author 7hens
 */
object RJavaParser {
    fun parse(rFile: File): HashMap<String, Int> {
        val map = HashMap<String, Int>()
        rFile.reader().buffered().use { reader ->
            var layoutStarted = false
            var line: String? = reader.readLine()
            while (line != null) {
                if (line.contains("public static final class layout")) {
                    layoutStarted = true
                } else if (layoutStarted) {
                    if (line.contains("}")) break
                    val lineSplit = line.substring(line.indexOf("int") + 3, line.indexOf(";"))
                            .replace(" ", "")
                            .split("=")
                    val layoutName = lineSplit[0]
                    val layoutId = Integer.decode(lineSplit[1])
                    map[layoutName] = layoutId
                }
                line = reader.readLine()
            }
        }
        return map
    }
}