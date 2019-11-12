package androidx2j.parser

import org.junit.Test
import java.io.File

/**
 * @author 7hens
 */
class X2JTranslatorTest {
    @Test
    fun test() {
        StyleParser.load(File("../sample/src/main/res/values/styles.xml"))
        val xmlFile = File("../sample/src/main/res/layout/activity_main.xml")
        val translator = X2JTranslator("androidxj2.sample")
        val builder = StringBuilder()
        translator.translate(xmlFile, 123, builder)
        println(builder.toString())
    }
}