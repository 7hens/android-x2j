plugins {
    `kotlin-dsl`
//    id("com.github.dcendents.android-maven")
    id("maven-publish")
}

group = "com.github.7hens"
version = "-SNAPSHOT"

repositories {
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/public")
    google()
    mavenLocal()
    maven("https://jitpack.io")
}

gradlePlugin {
    plugins {
        register("android-x2j") {
            id = "android-x2j"
            implementationClass = "androidx2j.X2JPlugin"
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    compileOnly(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:3.5.1")
    implementation("org.javassist:javassist:3.22.0-GA")
    implementation("commons-io:commons-io:2.5")
}

afterEvaluate {
    val x2jInputFile = file("../x2c-compat/src/main/java/androidx2j/X2J.java")
    val x2jOutputFile = file("src/main/kotlin/androidx2j/X2J.kt")
    if (x2jInputFile.lastModified() > x2jOutputFile.lastModified()) {
        val x2jCode = x2jInputFile.readText()
        x2jOutputFile.writeText("package androidx2j\n\nconst val X2J_CODE = \"\"\"$x2jCode\"\"\"")
    }
}

