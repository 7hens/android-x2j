package androidx2j

import android.databinding.tool.ext.toCamelCase
import androidx2j.parser.X2JTranslator
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

@Suppress("UnstableApiUsage")
class X2JPlugin : Plugin<Project> {
    private val layoutFiles = hashMapOf<String, File>()
    private var isAndroidLibrary = false

    override fun apply(project: Project) {
        MyLogger.log("hello, android-x2j")
        try {
            val androidApp = project.extensions.findByType(AppExtension::class.java)
            val androidLib = project.extensions.findByType(LibraryExtension::class.java)
            val android = androidApp ?: androidLib ?: run {
                MyLogger.error("not a android module")
                return
            }
            isAndroidLibrary = androidLib != null

            android.sourceSets.getByName("main").res.srcDirs.asSequence()
                    .flatMap { it.listFiles()?.asSequence() ?: emptySequence() }
                    .filter { it.name.startsWith("layout") }
                    .flatMap { it.listFiles()?.asSequence() ?: emptySequence() }
                    .forEach { layoutFiles[it.nameWithoutExtension] = it }

            android.registerTransform(X2JTransform(android))

            project.afterEvaluate {
                if (android is AppExtension) {
                    android.applicationVariants.forEach {
                        generateX2J(project, android, it)
                    }
                } else if (android is LibraryExtension) {
                    android.libraryVariants.forEach {
                        generateX2J(project, android, it)
                    }
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun generateX2J(project: Project, android: BaseExtension, variant: BaseVariant) {
        val variantName = variant.name
        val yaVariantName = variantName.toCamelCase()
        val applicationId = variant.applicationId
        val rJavaDir = getRJavaDir(project, variant)
        val outputRootDir = getJavaOutputDir(project, variant)

        project.tasks.findByName("compile${yaVariantName}JavaWithJavac")!!.doFirst {
            MyLogger.log("generate X2J files")

            val x2jFile = File(outputRootDir, "dev/android/x2j/X2J.java")
            x2jFile.parentFile.mkdirs()
            x2jFile.outputStream().writer().use { writer ->
                writer.write(X2J_CODE
                        .replace("o_0_applicationId", applicationId)
                        .replace("o_0_isAndroidLibrary", "" + isAndroidLibrary))
            }

            val rFilePath = applicationId.replace(".", "/") + "/R.java"
            val rFile = File(rJavaDir, rFilePath)
            X2JTranslator.start(android, variant, rFile, outputRootDir)

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
        }

        val genX2JTask = project.tasks.create("generate${yaVariantName}X2J") {
            group = "build"
            doLast {
            }
        }
        variant.registerJavaGeneratingTask(genX2JTask, outputRootDir)
    }

    private fun getRJavaDir(project: Project, variant: BaseVariant): File {
        val yaVariantName = variant.name.toCamelCase()
        return project.tasks.getByName("process${yaVariantName}Resources").outputs.files.files
                .first { it.absolutePath.contains("generated") && it.absolutePath.contains(File.separator + "r") }
    }

    private fun getJavaOutputDir(project: Project, variant: BaseVariant): File {
        val yaVariantName = variant.name.toCamelCase()
        return project.tasks.getByName("compile${yaVariantName}JavaWithJavac").outputs.files.files
                .first { it.absolutePath.contains("generated") }
    }

    private fun printProjectTasks(project: Project) {
        project.tasks.forEach { task ->
            MyLogger.log("\n")
            MyLogger.log(task.name)
            MyLogger.log("output ===>")
            MyLogger.log(task.outputs.files.files)
        }
    }
}

