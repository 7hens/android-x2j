package androidx2j

import java.io.File
import java.util.regex.Pattern

/**
 * @author 7hens
 */
class X2JRFileGenerator(private val applicationId: String, private val output: File) {
    private val outputClassName = output.nameWithoutExtension

    fun fromRTxt(input: File) {
        if (!input.exists()) {
            MyLogger.error("R.txt not found, $input")
            return
        }
        input.inputStream().reader().use { reader ->
            output.parentFile.mkdirs()
            if (output.exists()) output.delete()
            output.outputStream().writer().use { writer ->
                val pattern = Pattern.compile("""^(\w+(\[])?)\s+(\w+)\s+(\w+)\s+(.+)?$""", Pattern.MULTILINE)
                writer.apply {
                    var lastRes = ""

                    write("package $applicationId;")
                    write("\n")
                    write("\npublic final class $outputClassName {")
                    reader.forEachLine { line ->
                        val matcher = pattern.matcher(line)
                        if (matcher.matches()) {
                            val type = matcher.group(1)
                            val res = matcher.group(3)
                            val name = matcher.group(4)
                            val value = matcher.group(5)
                            if (res != lastRes) {
                                if (lastRes.isNotEmpty()) write("\n    }")
                                write("\n    public static final class $res {")
                                lastRes = res
                            }
                            write("\n        public static final $type $name = $value;")
                        }
                    }
                    if (lastRes.isNotEmpty()) write("\n    }")
                    write("\n}")
                }
            }
        }
        MyLogger.log("create $outputClassName.java success")
    }

    fun fromRJava(input: File) {
        if (!input.exists()) {
            MyLogger.error("R.java not found, $input")
            return
        }
        input.inputStream().reader().use { reader ->
            output.parentFile.mkdirs()
            output.outputStream().writer().use { writer ->
                writer.write(reader.readText()
                        .replace("class R {", "class $outputClassName {"))
            }
        }
        MyLogger.log("create $outputClassName.java success")
    }
}