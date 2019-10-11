# Android X2J

[![jitpack](https://jitpack.io/v/7hens/android-x2j.svg)](https://jitpack.io/#7hens/android-x2j)
[![license](https://img.shields.io/github/license/7hens/android-x2j.svg)](https://github.com/7hens/android-x2j/blob/master/LICENSE)
[![stars](https://img.shields.io/github/stars/7hens/android-x2j.svg?style=social)](https://github.com/7hens/android-x2j)

让你的 XML 布局变成 Java 来运行 !!

Android X2J（XML to Java）是一个 gradle 插件，可以让你零成本使用 [X2C](https://github.com/iReaderAndroid/X2C) (将 XML 布局翻译成 Java 代码)。
__它甚至可以让你在完全不了解 X2C 的 API 和用法情况下，就可以享受到 X2C 的功能。__

首先，Android X2J 会自动为你在 dependencies 中添加 X2C 的相关依赖，生成相关的 `@Xml` 注解。

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

!> 注意，目前由于 X2C 自身的限制， Android X2J 并不支持 kotlin-kapt。

## 使用方法

1) 配置根目录的 build/gradle。

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

3) 好了，接下来 Android X2J 会自动把 XML 布局翻译成 Java 代码（当然，这归功于 X2C），并打包到 APK 中。
