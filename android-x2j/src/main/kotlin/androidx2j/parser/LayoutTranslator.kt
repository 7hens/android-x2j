package androidx2j.parser

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import java.io.File
import javax.lang.model.element.Modifier
import javax.xml.parsers.SAXParserFactory

/**
 * @author 7hens
 */
class LayoutTranslator(private val packageName: String) {
    val rClass = ClassName.get(packageName, "R")

    fun translate(xmlFile: File, layoutId: Int) {
        val source = StringBuilder()
        JavaFile.builder("dev.android.x2j", TypeSpec.classBuilder("X2J_$layoutId")
                .addSuperinterface(ClassName.get("dev.android.x2j", "X2J", "ViewCreator"))
                .addJavadoc("Automatically generated file. DO NOT MODIFY.\n")
                .addJavadoc("Translate from {@link \$T.layout.${xmlFile.nameWithoutExtension}}\n", ClassName.get(packageName, "R"))
                .addMethod(MethodSpec.methodBuilder("createView")
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ClassName.get("android.content", "Context"), "context")
                        .returns(ClassName.get("android.view", "View"))
                        .also { method ->
                            val parser = SAXParserFactory.newInstance().newSAXParser()
                            parser.parse(xmlFile, XmlParser(method))
                        }
                        .build())
                .build())
                .indent("    ")
                .build()
                .writeTo(source)
        println(source)

    }
}