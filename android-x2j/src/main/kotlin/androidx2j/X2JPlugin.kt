package androidx2j

import android.databinding.tool.ext.toCamelCase
import com.android.build.gradle.AppExtension
import com.android.utils.FileUtils
import javassist.ClassPool
import javassist.LoaderClassPath
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

@Suppress("UnstableApiUsage")
class X2JPlugin : Plugin<Project> {
    private val layoutFiles = hashMapOf<String, File>()

    override fun apply(project: Project) {
        try {
            val android = project.extensions.getByType(AppExtension::class.java)
            val resourceDirs = android.sourceSets.getByName("main").res.srcDirs
            val sdkDirectory = android.sdkDirectory
            val compileSdkVersion = android.compileSdkVersion
            resourceDirs.asSequence()
                    .flatMap { it.listFiles()?.asSequence() ?: emptySequence() }
                    .filter { it.name.startsWith("layout") }
                    .flatMap { it.listFiles()?.asSequence() ?: emptySequence() }
                    .forEach { layoutFiles[it.nameWithoutExtension] = it }

            val containsKapt = project.plugins.findPlugin("kotlin-kapt") != null
            val apt = if (containsKapt) "kapt" else "annotationProcessor"
            project.configurations.getByName("annotationProcessor").dependencies
                    .add(project.dependencies.create("com.zhangyue.we:x2c-apt:1.1.2"))
            project.configurations.getByName("implementation").dependencies
                    .add(project.dependencies.create("com.zhangyue.we:x2c-lib:1.0.6"))

            val classPool = ClassPool(null).apply {
                val contextClassLoader = Thread.currentThread().contextClassLoader
                val androidJar = File(sdkDirectory, "platforms/$compileSdkVersion/android.jar")
                appendClassPath(LoaderClassPath(contextClassLoader))
                appendClassPath(androidJar.absolutePath)
            }
            android.registerTransform(X2JTransform(classPool))

            generateX2J(project, android, containsKapt)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun generateX2J(project: Project, android: AppExtension, containsKapt: Boolean) {
        project.afterEvaluate {
            android.applicationVariants.forEach { variant ->
                val variantName = variant.name
                val applicationId = variant.applicationId
                val apt = if (containsKapt) "kapt" else "apt"
                val outputRootDir = File("${project.buildDir}/generated/source/apt/$variantName")
                android.sourceSets.getByName("main").java.srcDirs(outputRootDir.absolutePath)

                val genX2JTask = project.tasks.create("generate${variantName.toCamelCase()}X2J") {
                    group = "build"
                    doLast {
                        val outputFile = File(outputRootDir, "androidx2j/X2J.java")
                        outputFile.parentFile.mkdirs()
                        outputFile.delete()
                        outputFile.outputStream().writer().use { writer ->
                            writer.write(X2J_CODE.replace("\"o_0_layout\"", run {
                                layoutFiles.keys.joinToString(", ") { "\"$it\"" }
                            }))
                        }

                        val rFilePath = applicationId.replace(".", "/") + "/R.java"
                        val rFile = File(project.buildDir, "generated/source/r/$variantName/$rFilePath")
                        // not_namespaced_r_class_sources\debug\processDebugResources
                        val rFile2 = File(project.buildDir, "generated/not_namespaced_r_class_sources/$variantName/process${variantName.toCamelCase()}Resources/r/$rFilePath")
                        if (rFile.exists().not()) {
                            if (rFile2.exists()) {
                                rFile.parentFile.mkdirs()
                                FileUtils.copyFile(rFile2, rFile)
                            } else {
                                System.err.println("R file not found: $rFile;$rFile2")
                            }
                        }
                    }
                }
                variant.registerJavaGeneratingTask(genX2JTask, outputRootDir)
            }
        }
    }
}

