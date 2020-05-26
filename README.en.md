# Android X2J

[![jitpack](https://jitpack.io/v/7hens/android-x2j.svg)](https://jitpack.io/#7hens/android-x2j)
[![license](https://img.shields.io/github/license/7hens/android-x2j.svg)](https://github.com/7hens/android-x2j/blob/master/LICENSE)
[![stars](https://img.shields.io/github/stars/7hens/android-x2j.svg?style=social)](https://github.com/7hens/android-x2j)

Write XML, run Java !!

Android X2J is a Gradle plugin that can translate your XML layouts into Java codes and inject them into Activity and custom layouts during compilation.

In the traditional case, XML is parsed at runtime (via LayoutInflater), while Android X2J can advance XML parsing to compile time, which can increase layout loading speed by 200%.

## Quick Start

1) in build/gradle.

last_version: [![jitpack](https://jitpack.io/v/7hens/android-x2j.svg)](https://jitpack.io/#7hens/android-x2j)

```groovy
buildscript {
    repositories {
        maven { url "https://jitpack.io" }
    }
    dependencies {
        classpath 'com.github.7hens:android-x2j:<last_version>'
    }
}

allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

2) in app/build.gradle.

```groovy
// make sure this line comes AFTER android {} block.
apply plugin: 'android-x2j'
```

> Android X2J supports both application and library modules.

3) Ok, let's hand it over to Android X2J.

## X2J and X2C

Android X2J is based on [X2C](https://github.com/iReaderAndroid/X2C).
__It even allows you to enjoy the X2C functionality without knowing the API and usage of X2C.__

First, Android X2J will automatically add X2C dependencies to your dependencies and generate the associated `@Xml` annotations.

Second, and most importantly, Android X2J uses the gradle transform api to automatically convert the java bytecode as follows during the APP build.

```plain
activity.setContentView(R.layout.activity_main)
=>> X2J.setContentView(activity, R.layout.activity_main)

layoutInflater.inflate(R.layout.view_item, parent)
=>> X2J.inflate(layoutInflater, R.layout.view_item, parent)

View.inflate(context, R.layout.view_item, parent)
=>> X2J.inflate(context, R.layout.view_item, parent)
```

> `The X2J` class has similar functionality to the `X2C` class.

The following is the source code of the MainActivity in the sample module:

```java
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater.from(this).inflate(R.layout.fragmetn_layout, null);
        LayoutInflater.from(this).inflate(R.layout.fragmetn_layout, null, false);
    }
}
```

The following is the corresponding code after apk decompilation using the X2J plugin package:

```java
public class MainActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        X2J.setContentView(this, R.layout.activity_main);
        X2J.inflate(LayoutInflater.from(this), (int) R.layout.fragmetn_layout, null);
        X2J.inflate(LayoutInflater.from(this), (int) R.layout.fragmetn_layout, null, false);
    }
}
```

