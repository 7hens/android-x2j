plugins {
    `kotlin-dsl`
    id("com.github.dcendents.android-maven")
    id("maven-publish")
}

group = "com.github.7hens"
version = "-SNAPSHOT"

repositories {
    google()
    jcenter()
    mavenLocal()
}

//gradlePlugin {
//    plugins {
//        register("android-x2j") {
//            id = "android-x2j"
//            implementationClass = "androidx2j.X2JPlugin"
//        }
//    }
//}

dependencies {
    testImplementation("junit:junit:4.12")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    compileOnly(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:3.5.1")
    implementation("org.javassist:javassist:3.22.0-GA")
    implementation("com.squareup:javapoet:1.11.1")
    implementation("commons-io:commons-io:2.5")
    implementation("com.zhangyue.we:x2c-apt:1.1.2")
}

afterEvaluate {
    val x2jFile = rootProject.file("x2c-compat/src/main/java/androidx2j/X2J.java")
    x2jFile.inputStream().reader().use { reader ->
        val text = reader.readText()
        file("src/main/kotlin/androidx2j/X2J.kt").outputStream().writer().use { writer ->
            writer.write("package androidx2j\n\nconst val X2J_CODE = \"\"\"$text\"\"\"")
        }
    }
}

