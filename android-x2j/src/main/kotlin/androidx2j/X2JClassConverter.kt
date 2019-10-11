package androidx2j

import javassist.ClassPool
import java.io.DataOutputStream
import java.io.File
import java.io.InputStream
import java.io.OutputStream

/**
 * @author 7hens
 */
class X2JClassConverter(private val classPool: ClassPool) {
    private val hookedClassNames = arrayOf(
            "android.app.Activity",
            "android.view.LayoutInflater",
            "android.view.View")

    private val codeConverter by lazy {
        ObjectInvokeCodeConverter().apply {
            val cInt = classPool.get("int")
            val cBoolean = classPool.get("boolean")
            val cContext = classPool.get("android.content.Context")
            val cActivity = classPool.get("android.app.Activity")
            val cView = classPool.get("android.view.View")
            val cViewGroup = classPool.get("android.view.ViewGroup")
            val cLayoutInflater = classPool.get("android.view.LayoutInflater")
            val xX2J = classPool.get("androidx2j.X2J")

            redirectMethodInvokeToStatic(
                    cActivity.getDeclaredMethod("setContentView", arrayOf(cInt)),
                    xX2J.getDeclaredMethod("setContentView", arrayOf(cActivity, cInt)))
            redirectMethodInvokeToStatic(
                    cLayoutInflater.getDeclaredMethod("inflate", arrayOf(cInt, cViewGroup, cBoolean)),
                    xX2J.getDeclaredMethod("inflate", arrayOf(cLayoutInflater, cInt, cViewGroup, cBoolean)))
            redirectMethodInvokeToStatic(
                    cLayoutInflater.getDeclaredMethod("inflate", arrayOf(cInt, cViewGroup)),
                    xX2J.getDeclaredMethod("inflate", arrayOf(cLayoutInflater, cInt, cViewGroup)))
            redirectMethodInvokeToStatic(
                    cView.getDeclaredMethod("inflate", arrayOf(cContext, cInt, cViewGroup)),
                    xX2J.getDeclaredMethod("inflate", arrayOf(cContext, cInt, cViewGroup)))
        }
    }

    fun convert(input: InputStream, output: OutputStream) {
        val ctClass = classPool.makeClassIfNew(input)
        if (ctClass.name !in arrayOf("com.zhangyue.we.x2c.X2C", "androidx2j.X2J")
                && ctClass.refClasses.any { it in hookedClassNames }) {
            if (ctClass.isFrozen) {
                ctClass.defrost()
            }
            ctClass.instrument(codeConverter)
            ctClass.toBytecode(DataOutputStream(output))
            ctClass.detach()
        } else {
            ctClass.toBytecode(DataOutputStream(output))
        }
    }

    fun convert(input: File, output: File) {
        input.inputStream().use { inputStream ->
            output.outputStream().use { outputStream ->
                convert(inputStream, outputStream)
            }
        }
    }
}