package androidx2j

import android.databinding.tool.ext.toCamelCase
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import com.android.utils.FileUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

@Suppress("UnstableApiUsage")
class X2JPlugin : Plugin<Project> {
    private val layoutFiles = hashMapOf<String, File>()
    private var isAndroidLibrary = false

    override fun apply(project: Project) {
        println(LOG_TAG + "hello, android-x2j")
        try {
            val androidApp = project.extensions.findByType(AppExtension::class.java)
            val androidLib = project.extensions.findByType(LibraryExtension::class.java)
            val android = androidApp ?: androidLib ?: run {
                System.err.println("X2J: not a android module")
                return
            }
            isAndroidLibrary = androidLib != null

            android.sourceSets.getByName("main").res.srcDirs.asSequence()
                    .flatMap { it.listFiles()?.asSequence() ?: emptySequence() }
                    .filter { it.name.startsWith("layout") }
                    .flatMap { it.listFiles()?.asSequence() ?: emptySequence() }
                    .forEach { layoutFiles[it.nameWithoutExtension] = it }

            val containsKapt = project.plugins.findPlugin("kotlin-kapt") != null
            val apt = if (containsKapt) "kapt" else "annotationProcessor"
            project.configurations.getByName(apt).dependencies
                    .add(project.dependencies.create("com.zhangyue.we:x2c-apt:1.1.2"))
            project.configurations.getByName("implementation").dependencies
                    .add(project.dependencies.create("com.zhangyue.we:x2c-lib:1.0.6"))

            android.registerTransform(X2JTransform(android))

            project.afterEvaluate {
                if (android is AppExtension) {
                    android.applicationVariants.forEach {
                        generateX2J(project, it)
                    }
                } else if (android is LibraryExtension) {
                    android.libraryVariants.forEach {
                        generateX2J(project, it)
                    }
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun generateX2J(project: Project, variant: BaseVariant) {
        val variantName = variant.name
        val applicationId = variant.applicationId
        val outputRootDir = File(project.buildDir, "generated/source/apt/$variantName")

        val genX2JTask = project.tasks.create("generate${variantName.toCamelCase()}X2J") {
            group = "build"
            doLast {
                println(LOG_TAG + "generate X2J file")
                val x2jFile = File(outputRootDir, "androidx2j/X2J.java")
                x2jFile.parentFile.mkdirs()
                x2jFile.delete()
                x2jFile.outputStream().writer().use { writer ->
                    val layouts = layoutFiles.keys.joinToString(", ") { "\"$it\"" }
                    writer.write(X2J_CODE
                            .replace("\"o_0_layouts\"", layouts)
                            .replace("o_0_applicationId", applicationId)
                            .replace("o_0_isAndroidLibrary", "" + isAndroidLibrary))
                }
            }
        }
        variant.registerJavaGeneratingTask(genX2JTask, outputRootDir)

        project.tasks.getByName("generate${variantName.toCamelCase()}Sources").doLast {
            println(LOG_TAG + "generate R file")
            val rFilePath = applicationId.replace(".", "/") + "/R.java"
            val rFile = File(project.buildDir, "generated/source/r/$variantName/$rFilePath")
            val rFile2 = File(project.buildDir, "generated/not_namespaced_r_class_sources/$variantName/process${variantName.toCamelCase()}Resources/r/$rFilePath")
            if (rFile.exists() || rFile2.exists()) {
                if (!rFile.exists()) {
                    rFile.parentFile.mkdirs()
                    FileUtils.copyFile(rFile2, rFile)
                }
                if (isAndroidLibrary) {
                    val r2File = File(outputRootDir, rFilePath.replace("R.java", "R2.java"))
                    rFile.inputStream().reader().use { reader ->
                        r2File.parentFile.mkdirs()
                        r2File.outputStream().writer().use { writer ->
                            writer.write(reader.readText()
                                    .replace("class R {", "class R2 {"))
                        }
                    }
                }
                println(LOG_TAG + "create R file success")
            } else {
                System.err.println(LOG_TAG + "R file not found, $rFile; $rFile2")
            }
        }

    }

    companion object {
        const val LOG_TAG = "@(android-x2j): "
    }
}

