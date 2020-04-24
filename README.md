# Android X2J

[![jitpack](https://jitpack.io/v/7hens/android-x2j.svg)](https://jitpack.io/#7hens/android-x2j)
[![license](https://img.shields.io/github/license/7hens/android-x2j.svg)](https://github.com/7hens/android-x2j/blob/master/LICENSE)
[![stars](https://img.shields.io/github/stars/7hens/android-x2j.svg?style=social)](https://github.com/7hens/android-x2j)

Language - [English](README.en.md)

让你的 XML 布局直接变成 Java 来运行 !!

Android X2J（XML to Java）是一个 Gradle 插件，它可以在 APK 编译期间 __自动__ 将你的 XML 布局翻译成 Java 代码，并 __自动__ 注入到 Activity 和自定义布局中。

传统的布局加载方案都是在运行时解析 XML（通过 LayoutInflater），而 Android X2J 则可以将 XML 的解析提前到编译时，从而可以让布局加载速度提升 200%。

## 使用方法

1) 配置根目录的 build/gradle。

last_version: [![jitpack](https://jitpack.io/v/7hens/android-x2j.svg)](https://jitpack.io/#7hens/android-x2j)

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

2) 在 app/build.gradle 中使用插件。

```groovy
// 必须在 android {} 代码块之后添加插件。
// 如果你不想使用 X2J 了，把这行注释掉就行了。
apply plugin: 'android-x2j'
```

> Android X2J 同时支持 application 和 library 两种 module 方式。

3) 好了，接下来就交给 Android X2J 吧。

## X2J 和 X2C

Android X2J 基于 [X2C](https://github.com/iReaderAndroid/X2C)。
__它甚至可以让你在完全不了解 X2C 的 API 和用法情况下，就可以享受到 X2C 的功能。__

首先，Android X2J 会自动为你在 dependencies 中添加 X2C 的相关依赖，并生成相关的 `@Xml` 注解。

其次，当然也是最重要的一点，Android X2J 使用了 gradle transform api，在 APP 构建期间自动将 java 字节码做如下转换。

```plain
activity.setContentView(R.layout.activity_main)
=>> X2J.setContentView(activity, R.layout.activity_main)

layoutInflater.inflate(R.layout.view_item, parent)
=>> X2J.inflate(layoutInflater, R.layout.view_item, parent)

View.inflate(context, R.layout.view_item, parent)
=>> X2J.inflate(context, R.layout.view_item, parent)
```

> `X2J` 类是对 `X2C` 类的封装。

下面是 sample 模块里面的 MainActivity 的源码：

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

而下面则是使用了 X2J 插件打包后的 apk 反编译后的对应代码。

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

最后，如果你已经使用了 Android X2J 插件然后又想弃坑，这很容易就能做到。
因为它只不过是一个 gradle 插件而已，没有任何侵入性。


