package androidx2j.parser

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.BaseVariant
import com.squareup.javapoet.*
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
        fun start(android: BaseExtension, variant: BaseVariant, outputDir: File) {

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
            parentDir.deleteRecursively()
            layoutFiles.forEachIndexed { index, file ->
                File(parentDir, "X2J_$index.java").writer().use { output ->
                    translator.translate(file, index, output)
                }
            }
        }
    }
}