package androidx2j.parser

import androidx2j.MyLogger
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.BaseVariant
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import java.io.File
import javax.lang.model.element.Modifier

/**
 * @author 7hens
 */
class X2JTranslator(private val packageName: String) {
    fun translate(file: File, layoutId: Int, output: Appendable) {
        val cR = ClassName.get(packageName, "R")
        val name = file.nameWithoutExtension

        JavaFile.builder("dev.android.x2j", TypeSpec.classBuilder("X2J_$layoutId")
                .addSuperinterface(ClassName.get("dev.android.x2j", "X2J", "ViewCreator"))
                .addJavadoc("Automatically generated file. DO NOT MODIFY.\n")
                .addJavadoc("Translate from {@link \$T.layout.$name}\n", cR)
                .addMethod(MethodSpec.methodBuilder("createView")
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ClassName.get("android.content", "Context"), "context")
                        .returns(ClassName.get("android.view", "View"))
                        .addComment("\$T.layout.$name", cR)
                        .addCode(LayoutParser.parse(file))
                        .build())
                .build())

                .indent("    ")
                .build()
                .writeTo(output)
    }

    companion object {
        fun start(android: BaseExtension, variant: BaseVariant, rJavaFile: File, outputDir: File) {
            val rMap = RJavaParser.parse(rJavaFile)
            val valueFiles = mutableListOf<File>()
            val layoutFiles = mutableListOf<File>()

            android.sourceSets.getByName("main").res.srcDirs.asSequence()
                    .flatMap { it.listFiles()?.asSequence() ?: emptySequence() }
                    .forEach { dir ->
                        when (dir.name) {
                            "layout" -> dir.listFiles()?.let { layoutFiles.addAll(it) }
                            "values" -> dir.listFiles()?.let { valueFiles.addAll(it) }
                        }
                    }

            valueFiles.forEach { StyleParser.load(it) }

            val translator = X2JTranslator(variant.applicationId)
            val parentDir = File(outputDir, "dev/android/x2j")
            parentDir.mkdirs()
            layoutFiles.forEach { file ->
                val layoutId = rMap[file.nameWithoutExtension]!!
                File(parentDir, "X2J_$layoutId.java").writer().use { output ->
                    try {
                        translator.translate(file, layoutId, output)
                    } catch (e: Throwable) {
                        MyLogger.error("translate error: #$layoutId $file")
                        MyLogger.error(e)
                    }
                }
            }
        }
    }
}