package androidx2j.parser

import com.squareup.javapoet.*
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
                .addSuperinterface(Codes.X2J.nestedClass("ViewCreator"))
                .addJavadoc("Automatically generated file. DO NOT MODIFY.\n")
                .addJavadoc("Translate from {@link \$T.layout.${xmlFile.nameWithoutExtension}}\n", ClassName.get(packageName, "R"))
                .addMethod(MethodSpec.methodBuilder("createView")
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ClassName.get("android.content", "Context"), "context")
                        .returns(ClassName.get("android.view", "View"))
                        .also { method ->
                            val codes = CodeBlock.builder()
                            val parser = SAXParserFactory.newInstance().newSAXParser()
                            parser.parse(xmlFile, XmlParser(codes))
                            method.addCode(codes.build())
                        }
                        .build())
                .build())
                .indent("    ")
                .build()
                .writeTo(source)
        println(source)
    }
}