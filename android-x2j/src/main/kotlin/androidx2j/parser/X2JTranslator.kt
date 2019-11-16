package androidx2j.parser

import androidx2j.MyLogger
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.BaseVariant
import com.squareup.javapoet.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.io.File
import javax.lang.model.element.Modifier

/**
 * @author 7hens
 */
class X2JTranslator(private val packageName: String) {
    fun translate(file: File, className: String, output: Appendable) {
        val cR = ClassName.get(packageName, "R")
        val name = file.nameWithoutExtension

        JavaFile.builder("dev.android.x2j", TypeSpec.classBuilder(className)
                .addSuperinterface(ClassName.get("dev.android.x2j", "X2J", "ViewCreator"))
                .addJavadoc("Automatically generated file. DO NOT MODIFY.\n")
                .addJavadoc("Translate from {@link \$T.layout.$name}\n", cR)
                .addMethod(MethodSpec.methodBuilder("createView")
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ClassName.get("android.content", "Context"), "context")
                        .addParameter(ClassName.get("android.view", "ViewGroup"), "root")
                        .addParameter(TypeName.BOOLEAN, "attach")
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
        suspend fun start(android: BaseExtension, variant: BaseVariant, rJavaFile: File, outputDir: File): Map<Int, String> {
            val result = mutableMapOf<Int, String>()
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

            supervisorScope {
                valueFiles.forEach { file ->
                    launch(Dispatchers.IO) {
                        MyLogger.log("translate $file")
                        StyleParser.load(file)
                    }
                }
            }

            supervisorScope {
                val translator = X2JTranslator(variant.applicationId)
                val parentDir = File(outputDir, "dev/android/x2j")
                parentDir.mkdirs()
                layoutFiles.forEach { file ->
                    launch(Dispatchers.IO) {
                        val className = "X2JL_" + file.nameWithoutExtension
                        MyLogger.log("translate $file")
                        File(parentDir, "$className.java").writer().use { output ->
                            try {
                                translator.translate(file, className, output)
                                val layoutId = rMap[file.nameWithoutExtension]!!
                                result[layoutId] = className
                            } catch (e: Throwable) {
                                output.write("/** translate error: $file" +
                                        "\n" +
                                        "\n" + MyLogger.getStackTrace(e) +
                                        "\n */")
                                MyLogger.error("translate error: $file")
                                MyLogger.error(e)
                            }
                        }
                    }
                }
            }
            return result
        }
    }
}