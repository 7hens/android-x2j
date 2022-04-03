package androidx2j

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.util.*

@Suppress("UnstableApiUsage", "DefaultLocale")
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

            project.extensions.create("androidX2J", X2JExtension::class.java)
            findLayoutFiles(android)
            implementationX2JDependencies(project, android)
            android.registerTransform(X2JTransform(android))

            val outputDir = File(project.buildDir, "generated/source/x2j/main")
            android.sourceSets.findByName("main")!!.java.srcDir(outputDir)
            project.afterEvaluate {
                (androidApp?.applicationVariants ?: androidLib?.libraryVariants)?.forEach { variant ->
                    resolveX2JExtension(project.extensions.getByType(X2JExtension::class.java))
                    generateX2JFile(project, outputDir, variant)
                    generateRFile(project, outputDir, variant)
                }
            }
        } catch (e: Throwable) {
            MyLogger.error(e)
        }
    }

    private fun findLayoutFiles(android: BaseExtension) {
        android.sourceSets.getByName("main").res.srcDirs.asSequence()
            .flatMap { it.listFiles()?.asSequence() ?: emptySequence() }
            .filter { it.name.startsWith("layout") }
            .flatMap { it.listFiles()?.asSequence() ?: emptySequence() }
            .forEach { layoutFiles[it.nameWithoutExtension] = it }
    }

    private fun resolveX2JExtension(x2JExtension: X2JExtension) {
        val filteredFiles = layoutFiles.filterKeys { x2JExtension.matches(it) }
        layoutFiles.clear()
        layoutFiles.putAll(filteredFiles)
    }

    private fun implementationX2JDependencies(project: Project, android: BaseExtension) {
        val hasKaptPlugin = project.pluginManager.hasPlugin("kotlin-kapt")
        val apt = if (hasKaptPlugin) "kapt" else "annotationProcessor"
        val x2cVersion = "-SNAPSHOT"
        project.configurations.getByName(apt).dependencies
            .add(project.dependencies.create("com.github.7hens.X2C:x2c-apt:$x2cVersion"))
        project.configurations.getByName("implementation").dependencies
            .add(project.dependencies.create("com.github.7hens.X2C:x2c-lib:$x2cVersion"))
        if (android.dataBinding.isEnabled) {
            project.configurations.getByName("implementation").dependencies
                .add(project.dependencies.create("com.github.7hens.X2C:x2c-binding:$x2cVersion"))
        }
    }

    private fun generateX2JFile(project: Project, outputRootDir: File, variant: BaseVariant) {
        val variantName = variant.name.capitalize()
        val applicationId = variant.applicationId

        val genX2JTask = project.tasks.create("generate${variantName}X2J") {
            group = "build"
            doLast {
                MyLogger.log("generate X2J file")
                val x2jFile = File(outputRootDir, "androidx2j/X2J.java")
                x2jFile.parentFile.mkdirs()
                x2jFile.delete()
                x2jFile.outputStream().writer().use { writer ->
                    val layouts = layoutFiles.keys.joinToString(", ") { "\"$it\"" }
                    writer.write(
                        X2J_CODE
                            .replace("\"o_0_layouts\"", layouts)
                            .replace("o_0_applicationId", applicationId)
                            .replace("o_0_isAndroidLibrary", "" + isAndroidLibrary)
                    )
                }
            }
        }
        variant.registerJavaGeneratingTask(genX2JTask, outputRootDir)
    }

    private fun generateRFile(project: Project, outputRootDir: File, variant: BaseVariant) {
        val variantName = variant.name.capitalize()
        val applicationId = variant.applicationId

        val sep = File.separator
        val rFilePath = applicationId.replace(".", sep) + sep + "R.java"
        val x2jRFile = File(outputRootDir, rFilePath.replace("R.java", "X2J_R.java"))
        val x2jRGenerator = X2JRFileGenerator(applicationId, x2jRFile)

        if (isAndroidLibrary) {
            project.tasks.getByName("generate${variantName}RFile").doLast {
                MyLogger.log("generate X2J_R.java file")
                val rTxtFile = outputs.files.files.first { it.absolutePath.endsWith("R.txt") }
                x2jRGenerator.fromRTxt(rTxtFile)
            }
        } else {
            project.tasks.getByName("process${variantName}Resources").doLast {
                MyLogger.log("generate X2J_R.java file")
                outputs.files.files.onEach { MyLogger.log(it) }
                val rJavaDir = outputs.files.files.firstOrNull {
                    it.absolutePath.contains("generated") &&
                            it.absolutePath.contains(sep + "r") &&
                            it.isDirectory
                }
                if (rJavaDir != null) {
                    val rJavaFile = File(rJavaDir, rFilePath)
                    x2jRGenerator.fromRJava(rJavaFile)
                } else {
                    val rTxtFile = outputs.files.files.first { it.absolutePath.endsWith("R.txt") }
                    x2jRGenerator.fromRTxt(rTxtFile)
                }
            }
        }
    }
}

