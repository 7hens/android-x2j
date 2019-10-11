# AndroidX2J

[![jitpack](https://jitpack.io/v/7hens/android-x2j.svg)](https://jitpack.io/#7hens/android-x2j)
[![license](https://img.shields.io/github/license/7hens/android-x2j.svg)](https://github.com/7hens/android-x2j/blob/master/LICENSE)
[![stars](https://img.shields.io/github/stars/7hens/android-x2j.svg?style=social)](https://github.com/7hens/android-x2j)

The missing gradle plugin for [X2C](https://github.com/iReaderAndroid/X2C).

## Setting up the dependency

in build/gradle

```groovy
buildscript {
    repositories {
        // ...
        maven { url "https://jitpack.io" }
    }
    dependencies {
        classpath 'com.github.7hens:android-x2j:<last_version>'
    }
}
```

in app/build.gradle

```groovy
android {
}

// make sure this line comes AFTER android block
apply plugin: 'android-x2j'
```


