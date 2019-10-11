package androidx2j

import com.android.SdkConstants
import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.tools.r8.com.google.common.io.Files
import com.android.utils.FileUtils
import javassist.ClassPool
import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

/**
 * @author 7hens
 */
class X2JTransform(private val classPool: ClassPool) : Transform() {
    override fun transform(transformInvocation: TransformInvocation) {
        super.transform(transformInvocation)
        val outputProvider = transformInvocation.outputProvider
        transformInvocation.inputs.asSequence()
                .flatMap { it.directoryInputs.asSequence() + it.jarInputs }
                .forEach { classPool.appendClassPath(it.file.absolutePath) }
        val classConverter = X2JClassConverter(classPool)
        transformInvocation.inputs.forEach { transformInput ->
            transformInput.directoryInputs.forEach { directoryInput ->
                for (file in FileUtils.getAllFiles(directoryInput.file)) {
                    val outputFile = outputProvider.getOutputFile(directoryInput, file)
                    Files.createParentDirs(outputFile)
                    if (file != null && file.name.endsWith(SdkConstants.DOT_CLASS)) {
                        classConverter.convert(file, outputFile)
                    } else {
                        FileUtils.copyFile(file, outputFile)
                    }
                }
            }
            transformInput.jarInputs.forEach { jarInput ->
                val zipFile = ZipFile(jarInput.file)
                val outputFile = outputProvider.getOutputFile(jarInput, jarInput.file)
                Files.createParentDirs(outputFile)
                ZipOutputStream(outputFile.outputStream()).use { outputStream ->
                    for (entry in zipFile.entries()) {
                        outputStream.putNextEntry(ZipEntry(entry.name))
                        zipFile.getInputStream(entry).use { inputStream ->
                            if (entry.isDirectory.not() && entry.name.endsWith(SdkConstants.DOT_CLASS)) {
                                classConverter.convert(inputStream, outputStream)
                            } else {
                                inputStream.copyTo(outputStream)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun TransformOutputProvider.getOutputFile(input: QualifiedContent, file: File): File {
        return File(getContentLocation(input), FileUtils.relativePossiblyNonExistingPath(file, input.file))
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
        return TransformManager.SCOPE_FULL_PROJECT
    }
}