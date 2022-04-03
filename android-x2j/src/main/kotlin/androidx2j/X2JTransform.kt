package androidx2j

import com.android.SdkConstants
import com.android.build.api.transform.*
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import com.google.common.io.Files
import javassist.ClassPool
import javassist.LoaderClassPath
import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

/**
 * @author 7hens
 */
@Suppress("UnstableApiUsage")
class X2JTransform(private val android: BaseExtension) : Transform() {
    override fun transform(transformInvocation: TransformInvocation) {
        super.transform(transformInvocation)
        MyLogger.log("transform start")
        val outputProvider = transformInvocation.outputProvider
        val classPool = AndroidClassPool(android)
        transformInvocation.inputs.asSequence()
                .flatMap { it.directoryInputs.asSequence() + it.jarInputs }
                .forEach { classPool.appendClassPath(it.file.absolutePath) }

        val classConverter = X2JClassConverter(classPool)
        outputProvider.deleteAll()
        transformInvocation.inputs.forEach { transformInput ->
            transformInput.directoryInputs.forEach { dirInput ->
                classConverter.convert(dirInput, outputProvider)
            }
            transformInput.jarInputs.forEach { jarInput ->
                classConverter.convert(jarInput, outputProvider)
            }
        }
    }

    private fun X2JClassConverter.convert(input: DirectoryInput, output: TransformOutputProvider) {
        for (file in FileUtils.getAllFiles(input.file)) {
            val outputFile = output.getOutputFile(input, file)
            Files.createParentDirs(outputFile)
            if (file != null && file.name.endsWith(SdkConstants.DOT_CLASS)) {
                convert(file, outputFile)
            } else {
                FileUtils.copyFile(file, outputFile)
            }
        }
    }

    private fun X2JClassConverter.convert(input: JarInput, output: TransformOutputProvider) {
        val zipFile = ZipFile(input.file)
        val outputFile = output.getOutputFile(input, input.file)
        Files.createParentDirs(outputFile)
        ZipOutputStream(outputFile.outputStream()).use { outputStream ->
            for (entry in zipFile.entries()) {
                outputStream.putNextEntry(ZipEntry(entry.name))
                zipFile.getInputStream(entry).use { inputStream ->
                    if (entry.isDirectory.not() && entry.name.endsWith(SdkConstants.DOT_CLASS)) {
                        convert(inputStream, outputStream)
                    } else {
                        inputStream.copyTo(outputStream)
                    }
                }
            }
        }
    }

    private fun TransformOutputProvider.getOutputFile(input: QualifiedContent, file: File): File {
        return File(getContentLocation(input), relativePossiblyNonExistingPath(file, input.file))
    }

    private fun relativePossiblyNonExistingPath(file: File, dir: File): String {
        return dir.toURI().relativize(file.toURI()).path
    }

    private fun TransformOutputProvider.getContentLocation(input: QualifiedContent): File {
        val format = if (input is JarInput) Format.JAR else Format.DIRECTORY
        return getContentLocation(input.name, input.contentTypes, input.scopes, format)
    }

    override fun getName(): String {
        return this::class.simpleName!!
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun isIncremental(): Boolean {
        return false
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return if (android is AppExtension) TransformManager.SCOPE_FULL_PROJECT else TransformManager.PROJECT_ONLY
    }
}