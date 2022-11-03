package androidx2j

import com.android.build.gradle.BaseExtension
import javassist.ClassPool
import javassist.CtClass
import javassist.LoaderClassPath
import java.io.File

class AndroidClassPool(private val android: BaseExtension) : ClassPool(false) {
    init {
        val contextClassLoader = Thread.currentThread().contextClassLoader
        appendClassPath(LoaderClassPath(contextClassLoader))
        android.bootClasspath.forEach { appendClassPath(it.absolutePath) }
//        appendClassPath(getAndroidJar().absolutePath)
    }

    private fun getAndroidJar(): File {
        val sdkDirectory = android.sdkDirectory
        val compileSdkVersion = android.compileSdkVersion
        return File(sdkDirectory, "platforms/$compileSdkVersion/android.jar")
    }

    override fun get0(classname: String?, useCache: Boolean): CtClass? {
        var cls = super.get0(classname, useCache)
        if (cls == null && isCalledFromFixTypes2(Throwable())) {
            cls = makeClass(classname)
            if (useCache) cacheCtClass(cls.name, cls, false)
        }
        return cls
    }

    private fun isCalledFromFixTypes2(newThrowable: Throwable): Boolean {
        for (stackTraceElement in newThrowable.stackTrace) {
            if (stackTraceElement.methodName == "fixTypes2") {
                return true
            }
        }
        return false
    }
}